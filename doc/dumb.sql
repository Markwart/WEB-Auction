--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2019-01-11 19:17:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 225 (class 1259 OID 27165)
-- Name: admin_communication; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin_communication (
    id integer NOT NULL,
    theme character varying NOT NULL,
    user_from_id integer NOT NULL,
    text text NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.admin_communication OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 27163)
-- Name: admin_communication_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admin_communication_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_communication_id_seq OWNER TO postgres;

--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 224
-- Name: admin_communication_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admin_communication_id_seq OWNED BY public.admin_communication.id;


--
-- TOC entry 214 (class 1259 OID 27104)
-- Name: auction_duration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auction_duration (
    id integer NOT NULL,
    day integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.auction_duration OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 27102)
-- Name: auction_duration_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auction_duration_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_duration_id_seq OWNER TO postgres;

--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 213
-- Name: auction_duration_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auction_duration_id_seq OWNED BY public.auction_duration.id;


--
-- TOC entry 223 (class 1259 OID 27152)
-- Name: auction_rule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auction_rule (
    id integer NOT NULL,
    index character varying NOT NULL,
    theme character varying NOT NULL,
    text text NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.auction_rule OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 27150)
-- Name: auction_rule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auction_rule_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_rule_id_seq OWNER TO postgres;

--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 222
-- Name: auction_rule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auction_rule_id_seq OWNED BY public.auction_rule.id;


--
-- TOC entry 216 (class 1259 OID 27114)
-- Name: bid; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bid (
    id integer NOT NULL,
    item_id integer NOT NULL,
    price_bid numeric NOT NULL,
    user_bid_id integer NOT NULL,
    status_bid character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.bid OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 27112)
-- Name: bid_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bid_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_id_seq OWNER TO postgres;

--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 215
-- Name: bid_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bid_id_seq OWNED BY public.bid.id;


--
-- TOC entry 204 (class 1259 OID 27041)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 27039)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 203
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- TOC entry 206 (class 1259 OID 27054)
-- Name: composition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.composition (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.composition OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 27052)
-- Name: composition_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.composition_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.composition_id_seq OWNER TO postgres;

--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 205
-- Name: composition_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.composition_id_seq OWNED BY public.composition.id;


--
-- TOC entry 202 (class 1259 OID 27028)
-- Name: condition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.condition (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.condition OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 27026)
-- Name: condition_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.condition_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.condition_id_seq OWNER TO postgres;

--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 201
-- Name: condition_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.condition_id_seq OWNED BY public.condition.id;


--
-- TOC entry 221 (class 1259 OID 27139)
-- Name: country_origin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country_origin (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.country_origin OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 27137)
-- Name: country_origin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_origin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_origin_id_seq OWNER TO postgres;

--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 220
-- Name: country_origin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_origin_id_seq OWNED BY public.country_origin.id;


--
-- TOC entry 227 (class 1259 OID 27176)
-- Name: deferred_bid; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deferred_bid (
    id integer NOT NULL,
    item_id integer NOT NULL,
    price_bid numeric NOT NULL,
    user_bid_id integer NOT NULL,
    status_bid character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.deferred_bid OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 27174)
-- Name: deferred_bid_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.deferred_bid_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.deferred_bid_id_seq OWNER TO postgres;

--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 226
-- Name: deferred_bid_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.deferred_bid_id_seq OWNED BY public.deferred_bid.id;


--
-- TOC entry 212 (class 1259 OID 27093)
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feedback (
    id integer NOT NULL,
    item_id integer NOT NULL,
    user_from_id integer NOT NULL,
    user_whom_id integer NOT NULL,
    communication integer NOT NULL,
    shipping_time integer NOT NULL,
    shipping_charges integer NOT NULL,
    item_description integer NOT NULL,
    comment text NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.feedback OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 27091)
-- Name: feedback_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.feedback_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.feedback_id_seq OWNER TO postgres;

--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 211
-- Name: feedback_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.feedback_id_seq OWNED BY public.feedback.id;


--
-- TOC entry 199 (class 1259 OID 27009)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id integer NOT NULL,
    name character varying NOT NULL,
    auction_end timestamp without time zone NOT NULL,
    duration_id integer NOT NULL,
    starting_price numeric NOT NULL,
    category_id integer NOT NULL,
    year integer NOT NULL,
    country_origin_id integer NOT NULL,
    condition_id integer NOT NULL,
    composition_id integer NOT NULL,
    image character varying NOT NULL,
    text text NOT NULL,
    seller_id integer NOT NULL,
    status_auction character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 27007)
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_id_seq OWNER TO postgres;

--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 198
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_id_seq OWNED BY public.item.id;


--
-- TOC entry 219 (class 1259 OID 27128)
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id integer NOT NULL,
    item_id integer NOT NULL,
    theme character varying NOT NULL,
    user_from_id integer NOT NULL,
    user_whom_id integer NOT NULL,
    text text NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.message OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 27126)
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_id_seq OWNER TO postgres;

--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 218
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- TOC entry 210 (class 1259 OID 27080)
-- Name: payment_method; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_method (
    id integer NOT NULL,
    name character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.payment_method OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 27078)
-- Name: payment_method_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_method_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_method_id_seq OWNER TO postgres;

--
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 209
-- Name: payment_method_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_method_id_seq OWNED BY public.payment_method.id;


--
-- TOC entry 200 (class 1259 OID 27018)
-- Name: personal_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personal_data (
    id integer NOT NULL,
    username character varying NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    adress character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.personal_data OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 27067)
-- Name: shipping_method; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shipping_method (
    id integer NOT NULL,
    name character varying NOT NULL,
    delivery_time character varying NOT NULL,
    cost numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.shipping_method OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 27065)
-- Name: shipping_method_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shipping_method_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shipping_method_id_seq OWNER TO postgres;

--
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 207
-- Name: shipping_method_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shipping_method_id_seq OWNED BY public.shipping_method.id;


--
-- TOC entry 229 (class 1259 OID 27187)
-- Name: step_block; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.step_block (
    id integer NOT NULL,
    name character varying NOT NULL,
    step_1 integer NOT NULL,
    step_2 integer NOT NULL,
    step_3 integer NOT NULL,
    step_4 integer NOT NULL,
    step_5 integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.step_block OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 27185)
-- Name: step_block_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.step_block_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.step_block_id_seq OWNER TO postgres;

--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 228
-- Name: step_block_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.step_block_id_seq OWNED BY public.step_block.id;


--
-- TOC entry 217 (class 1259 OID 27123)
-- Name: user_2_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_2_item (
    item_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.user_2_item OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 26996)
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    id integer NOT NULL,
    role character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 26994)
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_account_id_seq OWNER TO postgres;

--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 196
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;


--
-- TOC entry 2797 (class 2604 OID 27168)
-- Name: admin_communication id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_communication ALTER COLUMN id SET DEFAULT nextval('public.admin_communication_id_seq'::regclass);


--
-- TOC entry 2792 (class 2604 OID 27107)
-- Name: auction_duration id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction_duration ALTER COLUMN id SET DEFAULT nextval('public.auction_duration_id_seq'::regclass);


--
-- TOC entry 2796 (class 2604 OID 27155)
-- Name: auction_rule id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction_rule ALTER COLUMN id SET DEFAULT nextval('public.auction_rule_id_seq'::regclass);


--
-- TOC entry 2793 (class 2604 OID 27117)
-- Name: bid id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bid ALTER COLUMN id SET DEFAULT nextval('public.bid_id_seq'::regclass);


--
-- TOC entry 2787 (class 2604 OID 27044)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- TOC entry 2788 (class 2604 OID 27057)
-- Name: composition id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composition ALTER COLUMN id SET DEFAULT nextval('public.composition_id_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 27031)
-- Name: condition id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.condition ALTER COLUMN id SET DEFAULT nextval('public.condition_id_seq'::regclass);


--
-- TOC entry 2795 (class 2604 OID 27142)
-- Name: country_origin id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country_origin ALTER COLUMN id SET DEFAULT nextval('public.country_origin_id_seq'::regclass);


--
-- TOC entry 2798 (class 2604 OID 27179)
-- Name: deferred_bid id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deferred_bid ALTER COLUMN id SET DEFAULT nextval('public.deferred_bid_id_seq'::regclass);


--
-- TOC entry 2791 (class 2604 OID 27096)
-- Name: feedback id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback ALTER COLUMN id SET DEFAULT nextval('public.feedback_id_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 27012)
-- Name: item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item ALTER COLUMN id SET DEFAULT nextval('public.item_id_seq'::regclass);


--
-- TOC entry 2794 (class 2604 OID 27131)
-- Name: message id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- TOC entry 2790 (class 2604 OID 27083)
-- Name: payment_method id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method ALTER COLUMN id SET DEFAULT nextval('public.payment_method_id_seq'::regclass);


--
-- TOC entry 2789 (class 2604 OID 27070)
-- Name: shipping_method id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shipping_method ALTER COLUMN id SET DEFAULT nextval('public.shipping_method_id_seq'::regclass);


--
-- TOC entry 2799 (class 2604 OID 27190)
-- Name: step_block id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step_block ALTER COLUMN id SET DEFAULT nextval('public.step_block_id_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 26999)
-- Name: user_account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);


--
-- TOC entry 3024 (class 0 OID 27165)
-- Dependencies: 225
-- Data for Name: admin_communication; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3013 (class 0 OID 27104)
-- Dependencies: 214
-- Data for Name: auction_duration; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3022 (class 0 OID 27152)
-- Dependencies: 223
-- Data for Name: auction_rule; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3015 (class 0 OID 27114)
-- Dependencies: 216
-- Data for Name: bid; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3003 (class 0 OID 27041)
-- Dependencies: 204
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3005 (class 0 OID 27054)
-- Dependencies: 206
-- Data for Name: composition; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3001 (class 0 OID 27028)
-- Dependencies: 202
-- Data for Name: condition; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3020 (class 0 OID 27139)
-- Dependencies: 221
-- Data for Name: country_origin; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3026 (class 0 OID 27176)
-- Dependencies: 227
-- Data for Name: deferred_bid; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3011 (class 0 OID 27093)
-- Dependencies: 212
-- Data for Name: feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2998 (class 0 OID 27009)
-- Dependencies: 199
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3018 (class 0 OID 27128)
-- Dependencies: 219
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3009 (class 0 OID 27080)
-- Dependencies: 210
-- Data for Name: payment_method; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2999 (class 0 OID 27018)
-- Dependencies: 200
-- Data for Name: personal_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.personal_data (id, username, first_name, last_name, adress, created, updated) VALUES (1, '', '', '', '', '2019-01-11 18:49:11.82', '2019-01-11 18:49:11.82');
INSERT INTO public.personal_data (id, username, first_name, last_name, adress, created, updated) VALUES (3, '', '', '', '', '2019-01-11 18:58:38.796', '2019-01-11 18:58:38.796');
INSERT INTO public.personal_data (id, username, first_name, last_name, adress, created, updated) VALUES (2, '', '', '', '', '2019-01-11 18:58:14.207', '2019-01-11 18:59:21.251');


--
-- TOC entry 3007 (class 0 OID 27067)
-- Dependencies: 208
-- Data for Name: shipping_method; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3028 (class 0 OID 27187)
-- Dependencies: 229
-- Data for Name: step_block; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3016 (class 0 OID 27123)
-- Dependencies: 217
-- Data for Name: user_2_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2996 (class 0 OID 26996)
-- Dependencies: 197
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_account (id, role, email, password, created, updated) VALUES (1, 'admin', 'watashi_m@list.ru', 'wMIY/qLAB4B97AB/mD+LtCRxekwmp+xbSza99qYu3RM=', '2019-01-11 18:49:11.82', '2019-01-11 18:49:11.82');
INSERT INTO public.user_account (id, role, email, password, created, updated) VALUES (3, 'user', 'nadezad2004fedor@mail.ru', 'wMIY/qLAB4B97AB/mD+LtCRxekwmp+xbSza99qYu3RM=', '2019-01-11 18:58:38.796', '2019-01-11 18:58:38.796');
INSERT INTO public.user_account (id, role, email, password, created, updated) VALUES (2, 'moderator', 'deuisiku@mail.ru', 'wMIY/qLAB4B97AB/mD+LtCRxekwmp+xbSza99qYu3RM=', '2019-01-11 18:58:14.207', '2019-01-11 18:59:21.251');


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 224
-- Name: admin_communication_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admin_communication_id_seq', 1, false);


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 213
-- Name: auction_duration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auction_duration_id_seq', 1, false);


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 222
-- Name: auction_rule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auction_rule_id_seq', 1, false);


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 215
-- Name: bid_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bid_id_seq', 1, false);


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 203
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 1, false);


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 205
-- Name: composition_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.composition_id_seq', 1, false);


--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 201
-- Name: condition_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.condition_id_seq', 1, false);


--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 220
-- Name: country_origin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_origin_id_seq', 1, false);


--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 226
-- Name: deferred_bid_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.deferred_bid_id_seq', 1, false);


--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 211
-- Name: feedback_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.feedback_id_seq', 1, false);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 198
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_id_seq', 1, false);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 218
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.message_id_seq', 1, false);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 209
-- Name: payment_method_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_method_id_seq', 1, false);


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 207
-- Name: shipping_method_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shipping_method_id_seq', 1, false);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 228
-- Name: step_block_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.step_block_id_seq', 1, false);


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 196
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_account_id_seq', 3, true);


--
-- TOC entry 2847 (class 2606 OID 27173)
-- Name: admin_communication admin_communication_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_communication
    ADD CONSTRAINT admin_communication_pk PRIMARY KEY (id);


--
-- TOC entry 2831 (class 2606 OID 27111)
-- Name: auction_duration auction_duration_day_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction_duration
    ADD CONSTRAINT auction_duration_day_key UNIQUE (day);


--
-- TOC entry 2833 (class 2606 OID 27109)
-- Name: auction_duration auction_duration_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction_duration
    ADD CONSTRAINT auction_duration_pk PRIMARY KEY (id);


--
-- TOC entry 2843 (class 2606 OID 27162)
-- Name: auction_rule auction_rule_index_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction_rule
    ADD CONSTRAINT auction_rule_index_key UNIQUE (index);


--
-- TOC entry 2845 (class 2606 OID 27160)
-- Name: auction_rule auction_rule_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction_rule
    ADD CONSTRAINT auction_rule_pk PRIMARY KEY (id);


--
-- TOC entry 2835 (class 2606 OID 27122)
-- Name: bid bid_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bid
    ADD CONSTRAINT bid_pk PRIMARY KEY (id);


--
-- TOC entry 2813 (class 2606 OID 27051)
-- Name: category category_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_name_key UNIQUE (name);


--
-- TOC entry 2815 (class 2606 OID 27049)
-- Name: category category_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pk PRIMARY KEY (id);


--
-- TOC entry 2817 (class 2606 OID 27064)
-- Name: composition composition_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composition
    ADD CONSTRAINT composition_name_key UNIQUE (name);


--
-- TOC entry 2819 (class 2606 OID 27062)
-- Name: composition composition_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composition
    ADD CONSTRAINT composition_pk PRIMARY KEY (id);


--
-- TOC entry 2809 (class 2606 OID 27038)
-- Name: condition condition_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.condition
    ADD CONSTRAINT condition_name_key UNIQUE (name);


--
-- TOC entry 2811 (class 2606 OID 27036)
-- Name: condition condition_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.condition
    ADD CONSTRAINT condition_pk PRIMARY KEY (id);


--
-- TOC entry 2839 (class 2606 OID 27149)
-- Name: country_origin country_origin_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country_origin
    ADD CONSTRAINT country_origin_name_key UNIQUE (name);


--
-- TOC entry 2841 (class 2606 OID 27147)
-- Name: country_origin country_origin_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country_origin
    ADD CONSTRAINT country_origin_pk PRIMARY KEY (id);


--
-- TOC entry 2849 (class 2606 OID 27184)
-- Name: deferred_bid deferred_bid_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deferred_bid
    ADD CONSTRAINT deferred_bid_pk PRIMARY KEY (id);


--
-- TOC entry 2829 (class 2606 OID 27101)
-- Name: feedback feedback_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pk PRIMARY KEY (id);


--
-- TOC entry 2805 (class 2606 OID 27017)
-- Name: item item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pk PRIMARY KEY (id);


--
-- TOC entry 2837 (class 2606 OID 27136)
-- Name: message message_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pk PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2606 OID 27090)
-- Name: payment_method payment_method_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_name_key UNIQUE (name);


--
-- TOC entry 2827 (class 2606 OID 27088)
-- Name: payment_method payment_method_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_pk PRIMARY KEY (id);


--
-- TOC entry 2807 (class 2606 OID 27025)
-- Name: personal_data personal_data_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal_data
    ADD CONSTRAINT personal_data_pk PRIMARY KEY (id);


--
-- TOC entry 2821 (class 2606 OID 27077)
-- Name: shipping_method shipping_method_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shipping_method
    ADD CONSTRAINT shipping_method_name_key UNIQUE (name);


--
-- TOC entry 2823 (class 2606 OID 27075)
-- Name: shipping_method shipping_method_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shipping_method
    ADD CONSTRAINT shipping_method_pk PRIMARY KEY (id);


--
-- TOC entry 2851 (class 2606 OID 27197)
-- Name: step_block step_block_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step_block
    ADD CONSTRAINT step_block_name_key UNIQUE (name);


--
-- TOC entry 2853 (class 2606 OID 27195)
-- Name: step_block step_block_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step_block
    ADD CONSTRAINT step_block_pk PRIMARY KEY (id);


--
-- TOC entry 2801 (class 2606 OID 27006)
-- Name: user_account user_account_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_email_key UNIQUE (email);


--
-- TOC entry 2803 (class 2606 OID 27004)
-- Name: user_account user_account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pk PRIMARY KEY (id);


--
-- TOC entry 2871 (class 2606 OID 27283)
-- Name: admin_communication admin_communication_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_communication
    ADD CONSTRAINT admin_communication_fk0 FOREIGN KEY (user_from_id) REFERENCES public.user_account(id);


--
-- TOC entry 2864 (class 2606 OID 27248)
-- Name: bid bid_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bid
    ADD CONSTRAINT bid_fk0 FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 2865 (class 2606 OID 27253)
-- Name: bid bid_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bid
    ADD CONSTRAINT bid_fk1 FOREIGN KEY (user_bid_id) REFERENCES public.user_account(id);


--
-- TOC entry 2872 (class 2606 OID 27288)
-- Name: deferred_bid deferred_bid_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deferred_bid
    ADD CONSTRAINT deferred_bid_fk0 FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 2873 (class 2606 OID 27293)
-- Name: deferred_bid deferred_bid_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deferred_bid
    ADD CONSTRAINT deferred_bid_fk1 FOREIGN KEY (user_bid_id) REFERENCES public.user_account(id);


--
-- TOC entry 2861 (class 2606 OID 27233)
-- Name: feedback feedback_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_fk0 FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 2862 (class 2606 OID 27238)
-- Name: feedback feedback_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_fk1 FOREIGN KEY (user_from_id) REFERENCES public.user_account(id);


--
-- TOC entry 2863 (class 2606 OID 27243)
-- Name: feedback feedback_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_fk2 FOREIGN KEY (user_whom_id) REFERENCES public.user_account(id);


--
-- TOC entry 2854 (class 2606 OID 27198)
-- Name: item item_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fk0 FOREIGN KEY (duration_id) REFERENCES public.auction_duration(id);


--
-- TOC entry 2855 (class 2606 OID 27203)
-- Name: item item_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fk1 FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2856 (class 2606 OID 27208)
-- Name: item item_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fk2 FOREIGN KEY (country_origin_id) REFERENCES public.country_origin(id);


--
-- TOC entry 2857 (class 2606 OID 27213)
-- Name: item item_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fk3 FOREIGN KEY (condition_id) REFERENCES public.condition(id);


--
-- TOC entry 2858 (class 2606 OID 27218)
-- Name: item item_fk4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fk4 FOREIGN KEY (composition_id) REFERENCES public.composition(id);


--
-- TOC entry 2859 (class 2606 OID 27223)
-- Name: item item_fk5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fk5 FOREIGN KEY (seller_id) REFERENCES public.user_account(id);


--
-- TOC entry 2868 (class 2606 OID 27268)
-- Name: message message_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_fk0 FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 2869 (class 2606 OID 27273)
-- Name: message message_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_fk1 FOREIGN KEY (user_from_id) REFERENCES public.user_account(id);


--
-- TOC entry 2870 (class 2606 OID 27278)
-- Name: message message_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_fk2 FOREIGN KEY (user_whom_id) REFERENCES public.user_account(id);


--
-- TOC entry 2860 (class 2606 OID 27228)
-- Name: personal_data personal_data_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal_data
    ADD CONSTRAINT personal_data_fk0 FOREIGN KEY (id) REFERENCES public.user_account(id);


--
-- TOC entry 2866 (class 2606 OID 27258)
-- Name: user_2_item user_2_item_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_2_item
    ADD CONSTRAINT user_2_item_fk0 FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 2867 (class 2606 OID 27263)
-- Name: user_2_item user_2_item_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_2_item
    ADD CONSTRAINT user_2_item_fk1 FOREIGN KEY (user_id) REFERENCES public.user_account(id);


-- Completed on 2019-01-11 19:17:10

--
-- PostgreSQL database dump complete
--

