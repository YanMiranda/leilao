--Tabela Empresa
CREATE SEQUENCE seq_empresa INCREMENT 1 MINVALUE 1 MAXVALUE 9999999 START 10 CACHE 1;
CREATE TABLE empresa (
    id_emp BIGSERIAL PRIMARY KEY,
    raz_soc_emp VARCHAR(64) NOT NULL,
    cnpj_emp VARCHAR(32) NOT NULL UNIQUE,
    log_emp VARCHAR(64),
    mun_emp VARCHAR(64),
    num_emp VARCHAR(10),
    com_emp VARCHAR(64),
    bai_emp VARCHAR(64),
    cep_emp VARCHAR(16),
    tel_emp VARCHAR(32),
    ema_emp VARCHAR(254) NOT NULL,
    sit_emp VARCHAR(254),
    usu_emp VARCHAR(20) NOT NULL UNIQUE,
    sen_emp VARCHAR(128),
    cre_at_emp TIMESTAMP NOT NULL,
    upd_at_emp TIMESTAMP NOT NULL
);
-- Adicionar índice na coluna id_emp
CREATE INDEX idx_id_emp ON empresa (id_emp);
-- Adicionar índice na coluna cnpj_emp
CREATE INDEX idx_cnpj_emp ON empresa (cnpj_emp);

--Tabela Leilao
CREATE SEQUENCE seq_leilao INCREMENT 1 MINVALUE 1 MAXVALUE 9999999 START 10 CACHE 1;
CREATE TABLE leilao (
    id_lei BIGSERIAL PRIMARY KEY,
    cod_lei INTEGER,
    des_lei VARCHAR(128) NOT NULL,
    ven_lei INTEGER NOT NULL,
    ini_pre_lei TIMESTAMP NOT NULL,
    cre_at_lei TIMESTAMP NOT NULL,
    upd_at_lei TIMESTAMP NOT NULL
);
-- Adicionar índice na coluna id_lei
CREATE INDEX idx_id_lei ON leilao (id_lei);

--Tabela Lote
-- CREATE SEQUENCE seq_lote INCREMENT 1 MINVALUE 1 MAXVALUE 9999999 START 10 CACHE 1;
CREATE TABLE lote (
    id_lot BIGSERIAL NOT NULL,
    num_lot INTEGER,
    des_lot VARCHAR(60) NOT NULL,
    qtd_lot NUMERIC NOT NULL,
    val_ini_lot NUMERIC,
    val_ult_lot VARCHAR(128) NOT NULL,
    id_lei INTEGER NOT NULL,
    cre_at_lot TIMESTAMP NOT NULL,
    upd_at_lot TIMESTAMP NOT NULL,
    CONSTRAINT fk_leilao FOREIGN KEY (id_lei) REFERENCES leilao (id_lei),

    PRIMARY KEY (id_lot)
);
-- Adicionar índice na coluna id_lot
CREATE INDEX idx_id_lot ON lote (id_lot);

--Tabela Comprador
CREATE TABLE comprador (
    id_lei INTEGER NOT NULL,
    id_emp INTEGER NOT NULL,
    PRIMARY KEY (id_lei, id_emp),
    FOREIGN KEY (id_lei) REFERENCES leilao (id_lei),
    FOREIGN KEY (id_emp) REFERENCES empresa (id_emp)
);

--Tabela Unidade
CREATE SEQUENCE seq_unidade INCREMENT 1 MINVALUE 1 MAXVALUE 9999999 START 10 CACHE 1;
CREATE TABLE unidade (
    id_uni BIGSERIAL PRIMARY KEY,
    nom_uni VARCHAR(128) NOT NULL,
    cre_at_uni TIMESTAMP NOT NULL,
    upd_at_uni TIMESTAMP NOT NULL
);

-- Adicionar índice na coluna id_uni
CREATE INDEX idx_id_uni ON unidade (id_uni);
