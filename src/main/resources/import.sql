-- Inserir dados na tabela "cupom"
INSERT INTO cupom (descricao, codigo, inicio, termino, desconto)
VALUES ('Cupom 1', 'ABC123', '2023-10-04', '2023-10-10', 10);

INSERT INTO cupom (descricao, codigo, inicio, termino, desconto)
VALUES ('Cupom 2', 'DEF456', '2023-10-05', '2023-10-11', 15);

INSERT INTO cupom (descricao, codigo, inicio, termino, desconto)
VALUES ('Cupom 3', 'GHI789', '2023-10-06', '2023-10-12', 20);

INSERT INTO cupom (descricao, codigo, inicio, termino, desconto)
VALUES ('Cupom 4', 'JKL012', '2023-10-07', '2023-10-13', 25);

INSERT INTO cupom (descricao, codigo, inicio, termino, desconto)
VALUES ('Cupom 5', 'MNO345', '2023-10-08', '2023-10-14', 30);

-- Inserir dados na tabela "usuario"
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Ana Pereira', 'ana789', 'senha789', '345.678.901-23');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Pedro Santos', 'pedro001', 'senha001', '456.789.012-34');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Carla Oliveira', 'carla2022', 'senha2022', '567.890.123-45');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Mariana Lima', 'mariana22', 'senha22', '678.901.234-56');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Lucas Costa', 'lucas77', 'senha77', '789.012.345-67');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Julia Rodrigues', 'julia888', 'senha888', '890.123.456-78');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Rafaela Alves', 'rafaela09', 'senha09', '901.234.567-89');
INSERT INTO usuario (nome, login, senha, cpf) 
VALUES ('Fernando Silva', 'fernando25', 'senha25', '012.345.678-90');

-- Inserir dados na tabela "telefone"
INSERT INTO telefone (ddd, numero) 
VALUES ('111', '2223333');
INSERT INTO telefone (ddd, numero) 
VALUES ('222', '3334444');
INSERT INTO telefone (ddd, numero) 
VALUES ('333', '4445555');
INSERT INTO telefone (ddd, numero) 
VALUES ('444', '5556666');
INSERT INTO telefone (ddd, numero) 
VALUES ('555', '6667777');
INSERT INTO telefone (ddd, numero) 
VALUES ('666', '7778888');
INSERT INTO telefone (ddd, numero) 
VALUES ('777', '8889999');
INSERT INTO telefone (ddd, numero) 
VALUES ('888', '9990000');
INSERT INTO telefone (ddd, numero) 
VALUES ('111', '2223333');
INSERT INTO telefone (ddd, numero) 
VALUES ('444', '5556666');

-- Inserir dados na tabela "endereco"
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Rua A', '123', 'Casa 1', 'Centro', '11111-111');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Avenida B', '456', 'Apartamento 202', 'Bairro Novo', '22222-222');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Rua C', '789', 'Casa 3', 'Centro', '33333-333');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Avenida D', '101', 'Apartamento 404', 'Bairro Novo', '44444-444');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Rua E', '202', 'Casa 5', 'Centro', '55555-555');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Avenida F', '303', 'Apartamento 606', 'Bairro Novo', '66666-666');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Rua G', '404', 'Casa 7', 'Centro', '77777-777');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Avenida H', '505', 'Apartamento 808', 'Bairro Novo', '88888-888');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Rua X', '456', 'Apto 101', 'Centro', '12345-678');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) 
VALUES ('Avenida Y', '789', 'Casa 2', 'Bairro Novo', '98765-432');

-- Inserir dados na tabela de junção "usuario_telefone"
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (1, 1);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (2, 2);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (3, 3);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (4, 4);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (5, 5);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (6, 6);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (7, 7);
INSERT INTO usuario_telefone (id_usuario, id_telefone) 
VALUES (8, 8);

-- Inserir dados na tabela de junção "usuario_endereco"
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (1, 1);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (2, 2);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (3, 3);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (4, 4);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (5, 5);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (6, 6);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (7, 7);
INSERT INTO usuario_endereco (id_usuario, id_endereco) 
VALUES (8, 8);