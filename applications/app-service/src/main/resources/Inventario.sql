-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.buy_id_seq;

CREATE SEQUENCE public.buy_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE public.buy_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.buy_id_seq TO postgres;

-- DROP SEQUENCE public.detailbuy_id_seq;

CREATE SEQUENCE public.detailbuy_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE public.detailbuy_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.detailbuy_id_seq TO postgres;

-- DROP SEQUENCE public.product_id_seq;

CREATE SEQUENCE public.product_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE public.product_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.product_id_seq TO postgres;
-- public.buy definition

-- Drop table

-- DROP TABLE public.buy;

CREATE TABLE public.buy (
	id serial4 NOT NULL,
	"date" timestamp NULL,
	idtype varchar(50) NOT NULL,
	dni varchar(50) NOT NULL,
	clientname varchar(50) NOT NULL,
	CONSTRAINT buy_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.buy OWNER TO postgres;
GRANT ALL ON TABLE public.buy TO postgres;


-- public.detailbuy definition

-- Drop table

-- DROP TABLE public.detailbuy;

CREATE TABLE public.detailbuy (
	id serial4 NOT NULL,
	product int4 NOT NULL,
	buy int4 NOT NULL,
	quantity int4 NOT NULL,
	CONSTRAINT detailbuy_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.detailbuy OWNER TO postgres;
GRANT ALL ON TABLE public.detailbuy TO postgres;


-- public.product definition

-- Drop table

-- DROP TABLE public.product;

CREATE TABLE public.product (
	id serial4 NOT NULL,
	"name" varchar(50) NOT NULL,
	ininventory int4 NOT NULL,
	enabled bool NOT NULL,
	min int4 NOT NULL,
	max int4 NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.product OWNER TO postgres;
GRANT ALL ON TABLE public.product TO postgres;




-- Permissions

GRANT ALL ON SCHEMA public TO pg_database_owner;
GRANT USAGE ON SCHEMA public TO public;
