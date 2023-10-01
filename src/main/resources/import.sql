-- Inserir dados na tabela "usuario"
INSERT INTO usuario (nome, login, senha, cpf) VALUES ('João Silva', 'joao123', 'senha123', '123.456.789-01');
INSERT INTO usuario (nome, login, senha, cpf) VALUES ('Maria Souza', 'maria456', 'senha456', '234.567.890-12');

-- Inserir dados na tabela "telefone"
INSERT INTO telefone (ddd, numero) VALUES ('111', '2223333');
INSERT INTO telefone (ddd, numero) VALUES ('444', '5556666');

-- Inserir dados na tabela "endereco"
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) VALUES ('Rua X', '456', 'Apto 101', 'Centro', '12345-678');
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep) VALUES ('Avenida Y', '789', 'Casa 2', 'Bairro Novo', '98765-432');

-- Inserir dados na tabela de junção "usuario_telefone"
INSERT INTO usuario_telefone (id_usuario, id_telefone) VALUES (1, 1);
INSERT INTO usuario_telefone (id_usuario, id_telefone) VALUES (2, 2);

-- Inserir dados na tabela de junção "usuario_endereco"
INSERT INTO usuario_endereco (id_usuario, id_endereco) VALUES (1, 1);
INSERT INTO usuario_endereco (id_usuario, id_endereco) VALUES (2, 2);