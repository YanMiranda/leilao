-- Inserção de dados na tabela 'empresa'
INSERT INTO empresa (raz_soc_emp, cnpj_emp, log_emp, mun_emp, num_emp, com_emp, bai_emp, cep_emp, tel_emp, ema_emp, sit_emp, usu_emp, sen_emp, cre_at_emp, upd_at_emp)
VALUES
('Empresa A', '01732959000184', 'Rua A', 'Cidade A', '123', 'Complemento A', 'Bairro A', '86050640', '31987653542', 'email1@example.com', 'ativo', 'usuario1', 'senha1', NOW(), NOW()),
('Empresa B', '35219062000173', 'Rua B', 'Cidade B', '456', 'Complemento B', 'Bairro B', '69905116', '11987654321', 'email2@example.com', 'ativo', 'usuario2', 'senha2', NOW(), NOW()),
('Empresa C', '83611863000122', 'Rua C', 'Cidade C', '789', 'Complemento C', 'Bairro C', '64033655', '21987654321', 'email3@example.com', 'ativo', 'usuario3', 'senha3', NOW(), NOW()),
('Empresa D', '00953059000102', 'Rua D', 'Cidade D', '1011', 'Complemento D', 'Bairro D', '66635545', '31987654321', 'email4@example.com', 'ativo', 'usuario4', 'senha4', NOW(), NOW()),
('Empresa E', '65723847000140', 'Rua E', 'Cidade E', '1213', 'Complemento E', 'Bairro E', '35430299', '41987654321', 'email5@example.com', 'ativo', 'usuario5', 'senha5', NOW(), NOW()),
('Empresa F', '23124537000169', 'Rua F', 'Cidade F', '1415', 'Complemento F', 'Bairro F', '14406394', '51987654321', 'email6@example.com', 'ativo', 'usuario6', 'senha6', NOW(), NOW()),
('Empresa G', '98370992000143', 'Rua G', 'Cidade G', '1617', 'Complemento G', 'Bairro G', '96081235', '61987654321', 'email7@example.com', 'ativo', 'usuario7', 'senha7', NOW(), NOW()),
('Empresa H', '69000322000137', 'Rua H', 'Cidade H', '1819', 'Complemento H', 'Bairro H', '68371610', '71987654321', 'email8@example.com', 'ativo', 'usuario8', 'senha8', NOW(), NOW()),
('Empresa I', '44813950000100', 'Rua I', 'Cidade I', '2021', 'Complemento I', 'Bairro I', '97504800', '81987654321', 'email9@example.com', 'ativo', 'usuario9', 'senha9', NOW(), NOW()),
('Empresa J', '17836965000111', 'Rua J', 'Cidade J', '2223', 'Complemento J', 'Bairro J', '69905014', '91987654321', 'email10@example.com', 'ativo', 'usuario10', 'senha10', NOW(), NOW());


-- Inserção de dados na tabela 'leilao'
INSERT INTO leilao (cod_lei, des_lei, ven_lei, ini_pre_lei, cre_at_lei, upd_at_lei)
VALUES
(1001, 'Leilão 1', 1000, NOW(), NOW(), NOW()),
(1002, 'Leilão 2', 2000, NOW(), NOW(), NOW()),
(1003, 'Leilão 3', 3000, NOW(), NOW(), NOW()),
(1004, 'Leilão 4', 4000, NOW(), NOW(), NOW()),
(1005, 'Leilão 5', 5000, NOW(), NOW(), NOW()),
(1006, 'Leilão 6', 6000, NOW(), NOW(), NOW()),
(1007, 'Leilão 7', 7000, NOW(), NOW(), NOW()),
(1008, 'Leilão 8', 8000, NOW(), NOW(), NOW()),
(1009, 'Leilão 9', 9000, NOW(), NOW(), NOW()),
(1010, 'Leilão 10', 10000, NOW(), NOW(), NOW());


-- Inserção de dados na tabela 'lote'
INSERT INTO lote (num_lot, des_lot, qtd_lot, val_ini_lot, val_ult_lot, id_lei, cre_at_lot, upd_at_lot)
VALUES
(1, 'Lote 1', 10, 100.00, 'Valor Último Lote 1', 1, NOW(), NOW()),
(2, 'Lote 2', 20, 200.00, 'Valor Último Lote 2', 1, NOW(), NOW()),
(3, 'Lote 3', 30, 300.00, 'Valor Último Lote 3', 2, NOW(), NOW()),
(4, 'Lote 4', 40, 400.00, 'Valor Último Lote 4', 2, NOW(), NOW()),
(5, 'Lote 5', 50, 500.00, 'Valor Último Lote 5', 3, NOW(), NOW()),
(6, 'Lote 6', 60, 600.00, 'Valor Último Lote 6', 3, NOW(), NOW()),
(7, 'Lote 7', 70, 700.00, 'Valor Último Lote 7', 4, NOW(), NOW()),
(8, 'Lote 8', 80, 800.00, 'Valor Último Lote 8', 4, NOW(), NOW()),
(9, 'Lote 9', 90, 900.00, 'Valor Último Lote 9', 5, NOW(), NOW()),
(10, 'Lote 10', 100, 1000.00, 'Valor Último Lote 10', 5, NOW(), NOW());


-- Inserção de dados na tabela 'comprador'
INSERT INTO comprador (id_lei, id_emp)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);


-- Inserção de dados na tabela 'unidade'
INSERT INTO unidade (nom_uni, cre_at_uni, upd_at_uni)
VALUES
('Unidade A', NOW(), NOW()),
('Unidade B', NOW(), NOW()),
('Unidade C', NOW(), NOW()),
('Unidade D', NOW(), NOW()),
('Unidade E', NOW(), NOW()),
('Unidade F', NOW(), NOW()),
('Unidade G', NOW(), NOW()),
('Unidade H', NOW(), NOW()),
('Unidade I', NOW(), NOW()),
('Unidade J', NOW(), NOW());

