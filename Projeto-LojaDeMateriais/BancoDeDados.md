# Comandos do Banco de Dados do Projeto

CREATE TABLE IF NOT EXISTS public.formatosrel
(
loja character varying(3) COLLATE pg_catalog."default" NOT NULL,
usuario character varying(5) COLLATE pg_catalog."default" NOT NULL,
nomerel character varying(100) COLLATE pg_catalog."default" NOT NULL,
nomeformato character varying(200) COLLATE pg_catalog."default" NOT NULL,
config text COLLATE pg_catalog."default",
grusuario character varying(3) COLLATE pg_catalog."default" NOT NULL DEFAULT ' '::character varying,
status character varying(1) COLLATE pg_catalog."default",
formatoconfig text COLLATE pg_catalog."default"
)

CREATE TABLE produtos (
prod_codigo NUMERIC(10),
prod_descricao VARCHAR(50),
prod_marca VARCHAR(30),
prod_grupo VARCHAR(30),
prod_dpto VARCHAR(30),
prod_embalagem VARCHAR(2),
prod_qembalagem NUMERIC(10),
prod_preconvenda NUMERIC(10),
prod_precocompra NUMERIC(10),
prod_estoque NUMERIC(10),
prod_dtultcompra DATE,
prod_dtultvenda DATE
);


CREATE TABLE cliente (
clie_codigo NUMERIC(10),
clie_situacao VARCHAR(1) NOT NULL,
clie_nome VARCHAR(50) NOT NULL,
clie_tipo VARCHAR(1) NOT NULL,
clie_cpfcnpj VARCHAR(14),
clie_rua VARCHAR(50),
clie_bairro VARCHAR(50),
clie_numero VARCHAR(10),
clie_cep VARCHAR(8),
clie_muni_codigo INT,
clie_muni_nome VARCHAR(50),
clie_fone VARCHAR(15)
);

CREATE TABLE fornecedores (
forn_codigo NUMERIC(10),
forn_situacao VARCHAR(1) NOT NULL,
forn_nome VARCHAR(50),
forn_tipo VARCHAR(1),
forn_cpfcnpj VARCHAR(14) UNIQUE,
forn_rua VARCHAR(50),
forn_bairro VARCHAR(50),
forn_numero VARCHAR(10),
forn_cep VARCHAR(8),
forn_muni_codigo INT,
forn_muni_nome VARCHAR(50),
forn_fone VARCHAR(15)
);

CREATE TABLE municipios (
muni_codigo NUMERIC(10),
muni_nome VARCHAR(50) NOT NULL,
muni_uf VARCHAR(2) NOT NULL
);

CREATE TABLE vendedores (
vend_codigo NUMERIC(10),
vend_nome VARCHAR(50),
vend_comissao NUMERIC(10)
);

CREATE TABLE usuarios (
usua_codigo NUMERIC(10),
usua_nome VARCHAR(50) NOT NULL,
usua_cadastros VARCHAR(1),
usua_entradas VARCHAR(1),
usua_saidas VARCHAR(1),
usua_cancel VARCHAR(1),
usua_relat VARCHAR(1),
usua_config VARCHAR(1)
);

CREATE TABLE movprodutosc (
mvpc_transacao VARCHAR(20) NOT NULL,
mvpc_numdcto VARCHAR(10) NOT NULL,
mvpc_datamvto DATE NOT NULL,
mvpc_status VARCHAR(1) NOT NULL,
mvpc_es VARCHAR(1) NOT NULL,
mvpc_tipoentidade VARCHAR(1) NOT NULL,
mvpc_codentidade INT,
mvpc_vend_codigo INT,
mvpc_totalprod NUMERIC(10,5),
mvpc_totaldesc NUMERIC(10,5),
mvpc_totalacres NUMERIC(10,5),
mvpc_totaloutros NUMERIC(10,5),
mvpc_totaldcto NUMERIC(10,5)
);

CREATE TABLE movprodutosd (
mvpd_transacao VARCHAR(20) NOT NULL,
mvpd_status VARCHAR(1) NOT NULL,
mvpd_prod_codigo INT NOT NULL,
mvpd_qtde NUMERIC(10,5),
mvpd_valordesc NUMERIC(10,5),
mvpd_valoracres NUMERIC(10,5),
mvpd_valoroutros NUMERIC(10,5),
mvpd_valortotal NUMERIC(10,5)
);


CREATE TABLE configuracoes(
conf_codigo INTEGER,
conf_nomeempresa VARCHAR(50) NOT NULL,
conf_percdescontos NUMERIC(10) NOT NULL,
conf_validasaidas VARCHAR(1) NOT NULL,
conf_validafornec VARCHAR(1) NOT NULL,
conf_validacliente VARCHAR(1) NOT NULL
);

CREATE TABLE logoperacoes(
log_codigo NUMERIC(10),
log_data DATE,
log_hora VARCHAR(8),
log_usua_codigo NUMERIC(10) NOT NULL,
log_descricao VARCHAR(255)
)

ALTER TABLE logoperacoes ADD COLUMN log_agrupamento VARCHAR(255);