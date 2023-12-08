INSERT INTO usuario (nome, login, senha, perfil, imageName)
VALUES ('Administrador', 'admin', '670O1PvrZN/9M4jaH8LZGBXvu+O2HeAPE1IAH8iSzSY+JbkNaFdyizUFaKOZMNRhDzj97kONYDRA8ZsuA9/6pg==', 1, 'profile-user_64572.png');

INSERT INTO usuario (nome, sobrenome, cpf, rg, login, senha, perfil, imageName)
VALUES  ('Werbton', 'Carvalho', '123.456.789-01', '1234567-8', 'werbton98@gmail.com', 'PEVo2OQjeN+6+Wfsk5h9C65ROwcgSyCKH8lmSiprI7XsDkpElUMTXmQ5wXkypwgHm5MfR27v1wlmgodmje5HLA==', 2, 'profile-user_64572.png');

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

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (1, 1, 'Placa de Vídeo RTX 3060 1-Click OC PCI-e Galax, 8 GB GDDR6, 128BIT', 1, '2021-02-25', 3, 1, 'Como todas as placas gráficas da linha RTX 30, a série RTX 3060 suporta todas as inovações de jogos da GeForce, tais quais: NVIDIA DLSS, NVIDIA REFLEX e NVIDIA BROADCAST, com aceleração de performance e adicional de aumento de qualidade de imagem. Juntos com o sistema ray tracing, estas tecnologias são a base da plataforma de jogos da linha geforce, que traz desempenho e recursos incomparáveis para jogos e jogadores em todos os lugares.

', '3060_gx_box_p_oc_600_1.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (2, 2, 'SSD 1TB Samsung EVO SATA III 2.5"', 2, '2021-04-15', 2, 1, 'O SSD Samsung 860 EVO faz parte da série de modelos SSD SATA mais vendidos no mundo.* Especialmente concebido para PCs e computadores portáteis convencionais, é equipado com a tecnologia V-NAND e com um controlador baseado num algoritmo robusto. Este SSD é rápido e fiável e está disponível em diferentes capacidades.', 'pt-860-evo-sata-3-2-5-ssd-mz-76e1t0b-eu-frontblack-90605299.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (3, 3, 'Fonte de Alimentação Corsair 750W 80 PLUS Bronze', 3, '2022-05-10', 1, 1, 'As fontes de alimentação CV da CORSAIR são ideais para fornecer energia para seu novo PC doméstico ou de trabalho, com eficiência 80 PLUS Bronze garantida para proporcionar máxima potência para seu sistema continuamente.', 'CX750_02.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (4, 4, 'Placa Mãe ASUS ROG Strix Z590 LGA 1200', 4, '2022-07-20', 3, 1, 'Placa-mãe Intel ® Z590 LGA 1200 ATX com PCIe 4.0, 14 + 2 fases de alimentação agrupadas, Two-Way AI Noise Cancelation, AI Overclocking, AI Cooling, AI Networking, WiFi 6E (802.11ax), Ethernet Intel dual ® 2,5 Gb, quatro slots M.2 com dissipadores de calor, USB 3.2 Gen 2x2 USB Tipo-C®, iluminação SATA e Aura Sync RGB', 'h525.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (5, 5, 'Memória RAM DDR4 Crucial 16GB 2400MHz DIMM', 5, '2022-08-15', 3, 1, 'Desenvolvida para ajudar o sistema a operar com mais velocidade e consistência, a Memória de notebook Crucial é uma das formas mais fáceis e acessíveis para melhorar o desempenho do seu sistema.', 'image.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (6, 6, 'Mouse Logitech G Pro Wireless Gaming', 6, '2022-09-05', 3, 1, 'O PRO Wireless foi projetado para ser o melhor mouse para jogos para profissionais de eSports. Em um período de 2 anos, a Logitech G colaborou com mais de 50 jogadores profissionais para encontrar a forma, o peso e a sensação perfeitos, todos combinados com nossas tecnologias de sensores sem fio LIGHTSPEED e HERO de 25k. O resultado é um mouse para jogos com desempenho e precisão inigualáveis, oferecendo as ferramentas e a confiança necessárias para vencer.', 'pro-wireless.png');

INSERT INTO hardware (id, id_marca, modelo, id_fabricante, lancamento, categoria, status, descricao, imageName)
VALUES (7, 7, 'Processador AMD Ryzen 9 5900X 12-core, 24-thread', 7, '2022-10-10', 2, 1, 'O Ryzen 9 5900X conta com 12 núcleos para alimentar jogos, streaming e muito mais. Os processadores AMD Ryzen série 5000 capacitam a próxima geração de jogos exigentes, proporcionando experiências imersivas únicas e dominando qualquer tarefa multithread como 3D e renderização de vídeo e compilação de software.', 'processador-amd-ryzen-9-5900x-cache-70mb-3-8ghz-4-7ghz-max-turbo-am4-100-100000061wof_1604585280_gg.jpg');

INSERT INTO estado (nome, sigla)
VALUES ('Tocantins', 'TO');

INSERT INTO cidade (nome, id_estado)
VALUES ('Palmas', 1);

INSERT INTO endereco (nome, sobrenome, cep, endereco, numero, bairro, complemento, id_cidade, telefone)
VALUES ('Werbton', 'Carvalho', '12345-678', 'Avenida das Flores e das Árvores Frondosas', '456', 'Centro da Cidade', 'Apartamento 301, Bloco C, Torre Alta', 1, '(555) 123-4567');

INSERT INTO endereco (nome, sobrenome, cep, endereco, numero, bairro, complemento, id_cidade, telefone)
VALUES ('Werbton', 'Carvalho', '98765-432', 'Praça Histórica da Serenidade e dos Antigos Casarões', '101', 'Vila Histórica', 'Sem complemento', 1, '(555) 234-5678');

INSERT INTO usuario_endereco (id_usuario, id_endereco)
VALUES (2, 1);

INSERT INTO usuario_endereco (id_usuario, id_endereco)
VALUES (2, 2);

INSERT INTO cartao (tipo, numero, cvv, validade, titular, cpf)
VALUES (1, '1234 5678 9012 3456', '123', '2023-12-01', 'WERBTON C R FILHO', '123.456.789-01');

INSERT INTO usuario_cartao (id_usuario, id_cartao)
VALUES (2, 1);