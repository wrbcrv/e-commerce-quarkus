/* 
INSERT INTO estado (nome, sigla) 
VALUES ('Maranhão', 'MA');
INSERT INTO estado (nome, sigla) 
VALUES ('São Paulo', 'SP');
INSERT INTO estado (nome, sigla) 
VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO estado (nome, sigla) 
VALUES ('Minas Gerais', 'MG');
INSERT INTO estado (nome, sigla) 
VALUES ('Bahia', 'BA');
INSERT INTO estado (nome, sigla) 
VALUES ('Paraná', 'PR');
INSERT INTO estado (nome, sigla) 
VALUES ('Ceará', 'CE');
INSERT INTO estado (nome, sigla) 
VALUES ('Rio Grande do Sul', 'RS');

INSERT INTO cidade (nome, id_estado)
VALUES ('São Luís', 1);
INSERT INTO Cidade (nome, id_estado) 
VALUES ('São Paulo', 2);
INSERT INTO Cidade (nome, id_estado) 
VALUES ('Rio de Janeiro', 3); 
INSERT INTO Cidade (nome, id_estado) 
VALUES ('Belo Horizonte', 4);
INSERT INTO Cidade (nome, id_estado) 
VALUES ('Salvador', 5);
INSERT INTO Cidade (nome, id_estado) 
VALUES ('Curitiba', 6);
INSERT INTO Cidade (nome, id_estado) 
VALUES ('Fortaleza', 7); 
INSERT INTO Cidade (nome, id_estado) 
VALUES ('Porto Alegre', 8);

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

INSERT INTO fabricante (nome, site) 
VALUES ('Intel', 'https://www.intel.com');
INSERT INTO fabricante (nome, site) 
VALUES ('AMD', 'https://www.amd.com');
INSERT INTO fabricante (nome, site) 
VALUES ('NVIDIA', 'https://www.nvidia.com');
INSERT INTO fabricante (nome, site) 
VALUES ('Samsung', 'https://www.samsung.com');
INSERT INTO fabricante (nome, site) 
VALUES ('Dell', 'https://www.dell.com');
INSERT INTO fabricante (nome, site) 
VALUES ('HP', 'https://www.hp.com');
INSERT INTO fabricante (nome, site) 
VALUES ('Asus', 'https://www.asus.com');
INSERT INTO fabricante (nome, site) 
VALUES ('Lenovo', 'https://www.lenovo.com');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Galax', 'https://www.galax.com', '1987-05-15', 'Shenzhen, China');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Gigabyte', 'https://www.gigabyte.com', '1986-04-09', 'Taipei, Taiwan');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('MSI', 'https://www.msi.com', '1986-08-04', 'New Taipei City, Taiwan');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('ASUS', 'https://www.asus.com', '1989-04-02', 'Taipei, Taiwan');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('EVGA', 'https://www.evga.com', '1999-07-16', 'Brea, California, USA');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Corsair', 'https://www.corsair.com', '1994-01-01', 'Fremont, California, USA');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Logitech', 'https://www.logitech.com', '1981-10-02', 'Lausanne, Switzerland');
INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Razer', 'https://www.razer.com', '2005-11-01', 'Singapore'); 
*/