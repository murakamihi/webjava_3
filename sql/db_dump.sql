--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 10.10

-- Started on 2019-09-30 07:34:01

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2807 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.items (
    item_id integer NOT NULL,
    name text NOT NULL,
    price integer NOT NULL
);


ALTER TABLE public.items OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16402)
-- Name: session_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session_items (
    item_id integer NOT NULL,
    num integer NOT NULL
);


ALTER TABLE public.session_items OWNER TO postgres;

--
-- TOC entry 2798 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.items (item_id, name, price) FROM stdin;
2	商品2	6845
3	商品3	548
4	商品4	85485
5	商品5	65458
6	商品6	745
7	商品7	697
8	商品8	7745
9	商品9	78954
10	商品10	873
1	商品1	548
\.


--
-- TOC entry 2799 (class 0 OID 16402)
-- Dependencies: 197
-- Data for Name: session_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.session_items (item_id, num) FROM stdin;
\.


--
-- TOC entry 2674 (class 2606 OID 16401)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (item_id);


--
-- TOC entry 2676 (class 2606 OID 16406)
-- Name: session_items session_items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_items
    ADD CONSTRAINT session_items_pkey PRIMARY KEY (item_id);


-- Completed on 2019-09-30 07:34:01

--
-- PostgreSQL database dump complete
--

