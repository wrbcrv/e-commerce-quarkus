INSERT INTO usuario (login, senha, perfil)
VALUES ('admin', '670O1PvrZN/9M4jaH8LZGBXvu+O2HeAPE1IAH8iSzSY+JbkNaFdyizUFaKOZMNRhDzj97kONYDRA8ZsuA9/6pg==', 1);

INSERT INTO fornecedor (nome) 
VALUES ('NVidia');

INSERT INTO fornecedor (nome) 
VALUES ('Samsung');

INSERT INTO fornecedor (nome) 
VALUES ('Corsair');

INSERT INTO fornecedor (nome) 
VALUES ('ASUS');

INSERT INTO fornecedor (nome) 
VALUES ('Crucial');

INSERT INTO fornecedor (nome) 
VALUES ('Logitech');

INSERT INTO fornecedor (nome) 
VALUES ('AMD');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Galax', 'https://www.galax.com/', '01-01-1994', 'Hong Kong');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Samsung', 'https://www.samsung.com/', '01-03-1938', 'Seul, Coreia do Sul');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Corsair', 'https://www.corsair.com/', '01-01-1994', 'Fremont, Califórnia, EUA');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('ASUS', 'https://www.asus.com/', '02-04-1989', 'Taipé, Taiwan');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Crucial', 'https://www.crucial.com/', '01-01-1996', 'Meridian, Idaho, EUA');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('Logitech', 'https://www.logitech.com/', '02-10-1981', 'Lausanne, Suíça');

INSERT INTO marca (nome, site, fundacao, sede) 
VALUES ('AMD', 'https://www.amd.com/', '01-05-1969', 'Santa Clara, Califórnia, EUA');

INSERT INTO fabricante (nome, site) 
VALUES ('NVidia', 'https://www.nvidia.com/');

INSERT INTO fabricante (nome, site) 
VALUES ('Samsung Electronics', 'https://www.samsung.com/br/');

INSERT INTO fabricante (nome, site) 
VALUES ('Corsair', 'https://www.corsair.com/br/');

INSERT INTO fabricante (nome, site) 
VALUES ('ASUS', 'https://www.asus.com/br/');

INSERT INTO fabricante (nome, site) 
VALUES ('Crucial', 'https://www.crucial.com/');

INSERT INTO fabricante (nome, site) 
VALUES ('Logitech', 'https://www.logitech.com/br-br/');

INSERT INTO fabricante (nome, site) 
VALUES ('AMD', 'https://www.amd.com/pt');

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('Placa de Vídeo', 1799.99, 10, 1);

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('SSD 1TB Samsung EVO', 249.99, 20, 2);

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('Fonte de Alimentação Corsair 750W', 199.99, 15, 3);

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('Placa Mãe ASUS ROG Strix Z590', 299.99, 5, 4);

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('Memória RAM DDR4 Crucial 16GB', 79.99, 30, 5);

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('Mouse Logitech G Pro Wireless', 149.99, 25, 6);

INSERT INTO produto (nome, preco, estoque, id_fornecedor) 
VALUES ('Processador AMD Ryzen 9 5900X', 349.99, 15, 7);

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (1, 1, 'Placa de Vídeo RTX 3060 1-Click OC PCI-e Galax, 8 GB GDDR6, 128BIT', 1, '2021-02-25', 3, 1, '3060_gx_box_p_oc_600_1.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (2, 2, 'SSD 1TB Samsung EVO SATA III 2.5"', 2, '2021-04-15', 2, 1, 'pt-860-evo-sata-3-2-5-ssd-mz-76e1t0b-eu-frontblack-90605299.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (3, 3, 'Fonte de Alimentação Corsair 750W 80 PLUS Bronze', 3, '2022-05-10', 1, 1, 'CX750_02.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (4, 4, 'Placa Mãe ASUS ROG Strix Z590 LGA 1200', 4, '2022-07-20', 3, 1, 'h525.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (5, 5, 'Memória RAM DDR4 Crucial 16GB 2400MHz DIMM', 5, '2022-08-15', 3, 1, 'image.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (6, 6, 'Mouse Logitech G Pro Wireless Gaming', 6, '2022-09-05', 3, 1, 'pro-wireless.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, imageName)
VALUES (7, 7, 'Processador AMD Ryzen 9 5900X 12-core, 24-thread', 7, '2022-10-10', 2, 1, 'processador-amd-ryzen-9-5900x-cache-70mb-3-8ghz-4-7ghz-max-turbo-am4-100-100000061wof_1604585280_gg.jpg');

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