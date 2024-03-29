create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, data_atualizacao datetime not null, data_cadastro datetime not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2) not null, cozinha_id bigint, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into cozinha (id, nome) values (3, 'Italiana')
insert into estado (id, nome) values (1, 'São Paulo')
insert into estado (id, nome) values (2, 'Rio Grande do Sul')
insert into estado (id, nome) values (3, 'Amazonas')
insert into cidade (id, nome, estado_id) values (1,'São Paulo', 1)
insert into cidade (id, nome, estado_id) values (2,'Rio Claro', 1)
insert into cidade (id, nome, estado_id) values (3,'Porto Alegre', 2)
insert into cidade (id, nome, estado_id) values (4, 'Manaus', 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values (1, 'Arroz', 'Arroz Soltinho bem temperado', 18, true, 1, utc_timestamp, utc_timestamp)
insert into forma_pagamento (id, descricao) values (1, 'Cartão Crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão Débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_PRODUTO', 'Permite ao usuário consultar Produtos')
insert into permissao (id, nome, descricao) values (2, 'CONSULTAR_RESTAURANTE', 'Permite ao usuário consultar Restaurantes')
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMA_PAGAMENTO', 'Permite ao usuário consultar Formas de Pagamento')
insert into permissao (id, nome, descricao) values (4, 'EDITAR_PRODUTO', 'Permite ao usuário editar Produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3) 
insert into grupo (id, nome) values (1, 'Grupo 1')
insert into grupo (id, nome) values (2, 'Grupo 2')
insert into grupo_permissao (grupo_id, permissao_id) values (1,1), (1,2), (1,3), (2,1), (2,3), (2,4)
insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'User 1', 'user1@loxas.com', '111', utc_timestamp)
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'User 2', 'user2@loxas.com', '222', utc_timestamp)
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'User 3', 'user3@loxas.com', '333', utc_timestamp)
insert into usuario_grupo (usuario_id, grupo_id) values (1,1), (1,2), (2,1), (3,1)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, data_atualizacao datetime not null, data_cadastro datetime not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2) not null, cozinha_id bigint, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into cozinha (id, nome) values (3, 'Italiana')
insert into estado (id, nome) values (1, 'São Paulo')
insert into estado (id, nome) values (2, 'Rio Grande do Sul')
insert into estado (id, nome) values (3, 'Amazonas')
insert into cidade (id, nome, estado_id) values (1,'São Paulo', 1)
insert into cidade (id, nome, estado_id) values (2,'Rio Claro', 1)
insert into cidade (id, nome, estado_id) values (3,'Porto Alegre', 2)
insert into cidade (id, nome, estado_id) values (4, 'Manaus', 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values (1, 'Arroz', 'Arroz Soltinho bem temperado', 18, true, 1, utc_timestamp, utc_timestamp)
insert into forma_pagamento (id, descricao) values (1, 'Cartão Crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão Débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_PRODUTO', 'Permite ao usuário consultar Produtos')
insert into permissao (id, nome, descricao) values (2, 'CONSULTAR_RESTAURANTE', 'Permite ao usuário consultar Restaurantes')
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMA_PAGAMENTO', 'Permite ao usuário consultar Formas de Pagamento')
insert into permissao (id, nome, descricao) values (4, 'EDITAR_PRODUTO', 'Permite ao usuário editar Produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3) 
insert into grupo (id, nome) values (1, 'Grupo 1')
insert into grupo (id, nome) values (2, 'Grupo 2')
insert into grupo_permissao (grupo_id, permissao_id) values (1,1), (1,2), (1,3), (2,1), (2,3), (2,4)
insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'User 1', 'user1@loxas.com', '111', utc_timestamp)
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'User 2', 'user2@loxas.com', '222', utc_timestamp)
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'User 3', 'user3@loxas.com', '333', utc_timestamp)
insert into usuario_grupo (usuario_id, grupo_id) values (1,1), (1,2), (2,1), (3,1)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, data_atualizacao datetime not null, data_cadastro datetime not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2) not null, cozinha_id bigint, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into cozinha (id, nome) values (3, 'Italiana')
insert into estado (id, nome) values (1, 'São Paulo')
insert into estado (id, nome) values (2, 'Rio Grande do Sul')
insert into estado (id, nome) values (3, 'Amazonas')
insert into cidade (id, nome, estado_id) values (1,'São Paulo', 1)
insert into cidade (id, nome, estado_id) values (2,'Rio Claro', 1)
insert into cidade (id, nome, estado_id) values (3,'Porto Alegre', 2)
insert into cidade (id, nome, estado_id) values (4, 'Manaus', 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values (1, 'Arroz', 'Arroz Soltinho bem temperado', 18, true, 1, utc_timestamp, utc_timestamp)
insert into forma_pagamento (id, descricao) values (1, 'Cartão Crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão Débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_PRODUTO', 'Permite ao usuário consultar Produtos')
insert into permissao (id, nome, descricao) values (2, 'CONSULTAR_RESTAURANTE', 'Permite ao usuário consultar Restaurantes')
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMA_PAGAMENTO', 'Permite ao usuário consultar Formas de Pagamento')
insert into permissao (id, nome, descricao) values (4, 'EDITAR_PRODUTO', 'Permite ao usuário editar Produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3) 
insert into grupo (id, nome) values (1, 'Grupo 1')
insert into grupo (id, nome) values (2, 'Grupo 2')
insert into grupo_permissao (grupo_id, permissao_id) values (1,1), (1,2), (1,3), (2,1), (2,3), (2,4)
insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'User 1', 'user1@loxas.com', '111', utc_timestamp)
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'User 2', 'user2@loxas.com', '222', utc_timestamp)
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'User 3', 'user3@loxas.com', '333', utc_timestamp)
insert into usuario_grupo (usuario_id, grupo_id) values (1,1), (1,2), (2,1), (3,1)
