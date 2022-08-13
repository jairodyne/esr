insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Italiana');

insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Rio Grande do Sul');
insert into estado (id, nome) values (3, 'Amazonas');

insert into cidade (id, nome, estado_id) values (1,'São Paulo', 1);
insert into cidade (id, nome, estado_id) values (2,'Rio Claro', 1);
insert into cidade (id, nome, estado_id) values (3,'Porto Alegre', 2);
insert into cidade (id, nome, estado_id) values (4, 'Manaus', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into produto (id, nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values (1, 'Arroz', 'Arroz Soltinho bem temperado', 18, true, 1, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Cartão Crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão Débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_PRODUTO', 'Permite ao usuário consultar Produtos');
insert into permissao (id, nome, descricao) values (2, 'CONSULTAR_RESTAURANTE', 'Permite ao usuário consultar Restaurantes');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMA_PAGAMENTO', 'Permite ao usuário consultar Formas de Pagamento');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_PRODUTO', 'Permite ao usuário editar Produtos');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3) ;

insert into grupo (id, nome) values (1, 'Grupo 1');
insert into grupo (id, nome) values (2, 'Grupo 2');

insert into grupo_permissao (grupo_id, permissao_id) values (1,1), (1,2), (1,3), (2,1), (2,3), (2,4);

insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'User 1', 'user1@loxas.com', '111', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'User 2', 'user2@loxas.com', '222', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'User 3', 'user3@loxas.com', '333', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1,1), (1,2), (2,1), (3,1);
