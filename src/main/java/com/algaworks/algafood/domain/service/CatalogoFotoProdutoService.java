package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String novoNomeArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		if(fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		/**
		 * Estes são dados da foto que vão para o banco.
		 * Melhor tratar o insert no banco ANTES de armazenar em disco,
		 * em caso de erro, o roolback cuida do banco e não precisa tratar o armazenamento.
		 */
		foto.setNomeArquivo(novoNomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		/**
		 * Este é o armazenamento do stream "físico" da Foto no local desejado 
		 * ( pode ser em disco ou na S3, por exemplo ).
		 */
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.size(foto.getTamanho())
				.inputStream(dadosArquivo)
				.build();
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return foto; 
	}

	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
	    return produtoRepository.findFotoById(restauranteId, produtoId)
	            .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
	    FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);
	    
	    produtoRepository.delete(foto);
	    produtoRepository.flush();

	    fotoStorageService.remover(foto.getNomeArquivo());
	}
	
}
