--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2023-10-01 18:11:14

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
-- TOC entry 6 (class 2615 OID 25421)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 941 (class 1247 OID 25424)
-- Name: entity_hash_set; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.entity_hash_set AS (
	id integer,
	hash text
);


ALTER TYPE public.entity_hash_set OWNER TO postgres;

--
-- TOC entry 308 (class 1255 OID 26377)
-- Name: check_duplicate(regclass, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.check_duplicate(_entity regclass, _hash text) RETURNS SETOF public.entity_hash_set
    LANGUAGE plpgsql
    AS $$
begin
	return QUERY execute 'select 1 as id, entity_hash as hash from ' || _entity || ' where entity_hash = ' || '''' || _hash || '''' ;
end;
$$;


ALTER FUNCTION public.check_duplicate(_entity regclass, _hash text) OWNER TO postgres;

--
-- TOC entry 320 (class 1255 OID 26378)
-- Name: get_user_roles(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_user_roles(_user_id integer, OUT result text) RETURNS text
    LANGUAGE plpgsql
    AS $$
begin
	select row_to_json(row) into result from (select * from user_roles where user_id = _user_id) row;
end;
$$;


ALTER FUNCTION public.get_user_roles(_user_id integer, OUT result text) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 239 (class 1259 OID 25593)
-- Name: beneficiary_profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.beneficiary_profiles (
    id bigint NOT NULL,
    beneficiary_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint,
    full_name text,
    full_name_native text,
    country text,
    state text,
    city text,
    address1 text,
    address2 text,
    zip_code text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    nationality text,
    biz_registration_no text,
    legal_person_name text,
    beneficiary_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.beneficiary_profiles OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 25592)
-- Name: beneficiary_profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.beneficiary_profiles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.beneficiary_profiles_id_seq OWNER TO postgres;

--
-- TOC entry 4078 (class 0 OID 0)
-- Dependencies: 238
-- Name: beneficiary_profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.beneficiary_profiles_id_seq OWNED BY public.beneficiary_profiles.id;


--
-- TOC entry 240 (class 1259 OID 25610)
-- Name: beneficiary_profiles_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.beneficiary_profiles_log (
    id bigint,
    merchant_id bigint,
    full_name text,
    full_name_native text,
    country text,
    state text,
    city text,
    address1 text,
    address2 text,
    zip_code text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    nationality text,
    biz_registration_no text,
    legal_person_name text,
    beneficiary_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.beneficiary_profiles_log OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25437)
-- Name: category_roots; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category_roots (
    id bigint NOT NULL,
    category_root_id uuid DEFAULT public.uuid_generate_v4(),
    category_type text,
    sub_type text,
    category_code text,
    description text,
    parent_type text,
    parent_code text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone
);


ALTER TABLE public.category_roots OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 25436)
-- Name: category_roots_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_roots_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_roots_id_seq OWNER TO postgres;

--
-- TOC entry 4079 (class 0 OID 0)
-- Dependencies: 216
-- Name: category_roots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_roots_id_seq OWNED BY public.category_roots.id;


--
-- TOC entry 242 (class 1259 OID 25629)
-- Name: category_roots_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category_roots_log (
    id bigint NOT NULL,
    category_root_id text,
    category_type text,
    sub_type text,
    category_code text,
    description text,
    parent_type text,
    parent_code text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.category_roots_log OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 25628)
-- Name: category_roots_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_roots_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_roots_log_id_seq OWNER TO postgres;

--
-- TOC entry 4080 (class 0 OID 0)
-- Dependencies: 241
-- Name: category_roots_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_roots_log_id_seq OWNED BY public.category_roots_log.id;


--
-- TOC entry 219 (class 1259 OID 25450)
-- Name: documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documents (
    id bigint NOT NULL,
    doc_id uuid DEFAULT public.uuid_generate_v4(),
    doc_type text,
    sub_type text,
    source_type text,
    source_id text,
    association_type text,
    association_id text,
    doc_path text,
    doc_name text,
    doc_alias text,
    doc_id_number text,
    issue_date date,
    start_date date,
    expiry_date date,
    issuing_country text,
    issuing_place text,
    issuing_authority text,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone
);


ALTER TABLE public.documents OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25449)
-- Name: documents_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documents_id_seq OWNER TO postgres;

--
-- TOC entry 4081 (class 0 OID 0)
-- Dependencies: 218
-- Name: documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.documents_id_seq OWNED BY public.documents.id;


--
-- TOC entry 221 (class 1259 OID 25463)
-- Name: error_codes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.error_codes (
    id bigint NOT NULL,
    error_code text,
    message text,
    description text,
    module_id text,
    lang text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.error_codes OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25462)
-- Name: error_codes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.error_codes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.error_codes_id_seq OWNER TO postgres;

--
-- TOC entry 4082 (class 0 OID 0)
-- Dependencies: 220
-- Name: error_codes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.error_codes_id_seq OWNED BY public.error_codes.id;


--
-- TOC entry 244 (class 1259 OID 25644)
-- Name: error_possible_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.error_possible_actions (
    id bigint NOT NULL,
    error_code text,
    possible_actions text,
    lang text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.error_possible_actions OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 25643)
-- Name: error_possible_actions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.error_possible_actions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.error_possible_actions_id_seq OWNER TO postgres;

--
-- TOC entry 4083 (class 0 OID 0)
-- Dependencies: 243
-- Name: error_possible_actions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.error_possible_actions_id_seq OWNED BY public.error_possible_actions.id;


--
-- TOC entry 223 (class 1259 OID 25477)
-- Name: merchants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants (
    id bigint NOT NULL,
    merchant_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_type text,
    email_id text,
    phone_code text,
    phone_number text,
    incorporation_country text,
    business_name text,
    business_name_native text,
    business_type text,
    industry_type text,
    product_type text,
    business_nature text,
    incorporation_date date,
    bizz_reg_no text,
    corp_reg_no text,
    business_profile text,
    postal_code text,
    address1 text,
    address2 text,
    city text,
    website text,
    currency_preference text,
    approx_txn_monthly_volume integer,
    approx_txn_yearly_volume integer,
    kyc_status text,
    kyc_remarks text,
    rba_status text,
    rba_remarks text,
    compliance_status text,
    compliance_remarks text,
    doc_path text,
    notification_method text,
    preferred_date_format text,
    preferred_time_zone text,
    security_stamp uuid DEFAULT public.uuid_generate_v4(),
    terms_conditions_accepted boolean DEFAULT false NOT NULL,
    privacy_policy_accepted boolean DEFAULT false NOT NULL,
    pricing_policy_accepted boolean DEFAULT false NOT NULL,
    marketing_email_subscription boolean DEFAULT false NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone
);


ALTER TABLE public.merchants OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 25661)
-- Name: merchants_bank_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_bank_details (
    id bigint NOT NULL,
    merchant_bank_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    bank_id text,
    account_name text,
    account_number text,
    swift_code text,
    bic_code text,
    ifsc_code text,
    iban_cbu_card_number text,
    is_verified boolean DEFAULT false NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_bank_details OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 25660)
-- Name: merchants_bank_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_bank_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_bank_details_id_seq OWNER TO postgres;

--
-- TOC entry 4084 (class 0 OID 0)
-- Dependencies: 245
-- Name: merchants_bank_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_bank_details_id_seq OWNED BY public.merchants_bank_details.id;


--
-- TOC entry 247 (class 1259 OID 25679)
-- Name: merchants_bank_details_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_bank_details_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    bank_id text,
    account_name text,
    account_number text,
    swift_code text,
    bic_code text,
    ifsc_code text,
    iban_cbu_card_number text,
    is_verified boolean DEFAULT false NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_bank_details_log OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 25694)
-- Name: merchants_directors_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_directors_details (
    id bigint NOT NULL,
    merchant_director_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    nationality text,
    dob date,
    phone_code text,
    phone_number text,
    email_id text,
    residence_country text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_directors_details OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 25693)
-- Name: merchants_directors_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_directors_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_directors_details_id_seq OWNER TO postgres;

--
-- TOC entry 4085 (class 0 OID 0)
-- Dependencies: 248
-- Name: merchants_directors_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_directors_details_id_seq OWNED BY public.merchants_directors_details.id;


--
-- TOC entry 250 (class 1259 OID 25711)
-- Name: merchants_directors_details_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_directors_details_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    nationality text,
    dob date,
    phone_code text,
    phone_number text,
    email_id text,
    residence_country text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_directors_details_log OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 25725)
-- Name: merchants_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_documents (
    id bigint NOT NULL,
    merchant_document_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    document_type text,
    document_path text,
    document_name text,
    document_alias text,
    document_id_number text,
    issue_date date,
    expiry_date date,
    issue_place text,
    issuing_authority text,
    document_map_id text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_documents OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 25724)
-- Name: merchants_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_documents_id_seq OWNER TO postgres;

--
-- TOC entry 4086 (class 0 OID 0)
-- Dependencies: 251
-- Name: merchants_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_documents_id_seq OWNED BY public.merchants_documents.id;


--
-- TOC entry 253 (class 1259 OID 25742)
-- Name: merchants_documents_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_documents_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    document_type text,
    document_path text,
    document_name text,
    document_alias text,
    document_id_number text,
    issue_date date,
    expiry_date date,
    issue_place text,
    issuing_authority text,
    document_map_id text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_documents_log OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25476)
-- Name: merchants_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_id_seq OWNER TO postgres;

--
-- TOC entry 4087 (class 0 OID 0)
-- Dependencies: 222
-- Name: merchants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_id_seq OWNED BY public.merchants.id;


--
-- TOC entry 254 (class 1259 OID 25755)
-- Name: merchants_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_log (
    id bigint NOT NULL,
    merchant_type text,
    email_id text,
    phone_code text,
    phone_number text,
    incorporation_country text,
    business_name text,
    business_name_native text,
    business_type text,
    industry_type text,
    product_type text,
    business_nature text,
    incorporation_date date,
    bizz_reg_no text,
    corp_reg_no text,
    business_profile text,
    postal_code text,
    address1 text,
    address2 text,
    city text,
    website text,
    currency_preference text,
    approx_txn_monthly_volume integer,
    approx_txn_yearly_volume integer,
    kyc_status text,
    kyc_remarks text,
    rba_status text,
    rba_remarks text,
    compliance_status text,
    compliance_remarks text,
    doc_path text,
    notification_method text,
    preferred_date_format text,
    preferred_time_zone text,
    security_stamp uuid DEFAULT public.uuid_generate_v4(),
    terms_conditions_accepted boolean DEFAULT false NOT NULL,
    privacy_policy_accepted boolean DEFAULT false NOT NULL,
    pricing_policy_accepted boolean DEFAULT false NOT NULL,
    marketing_email_subscription boolean DEFAULT false NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_log OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 25774)
-- Name: merchants_owners_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_owners_details (
    id bigint NOT NULL,
    merchant_owener_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    role text,
    nationality text,
    residence_country text,
    phone_code text,
    phone_number text,
    email_id text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_owners_details OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 25773)
-- Name: merchants_owners_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_owners_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_owners_details_id_seq OWNER TO postgres;

--
-- TOC entry 4088 (class 0 OID 0)
-- Dependencies: 255
-- Name: merchants_owners_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_owners_details_id_seq OWNED BY public.merchants_owners_details.id;


--
-- TOC entry 257 (class 1259 OID 25791)
-- Name: merchants_owners_details_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_owners_details_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    role text,
    nationality text,
    residence_country text,
    phone_code text,
    phone_number text,
    email_id text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_owners_details_log OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 25805)
-- Name: merchants_representative_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_representative_details (
    id bigint NOT NULL,
    merchant_representative_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    first_name text,
    middle_name text,
    last_name text,
    full_name text,
    full_name_native text,
    designation text,
    nationality text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    residence_country text,
    postal_code text,
    address1 text,
    address2 text,
    city text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_representative_details OWNER TO postgres;

--
-- TOC entry 258 (class 1259 OID 25804)
-- Name: merchants_representative_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_representative_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_representative_details_id_seq OWNER TO postgres;

--
-- TOC entry 4089 (class 0 OID 0)
-- Dependencies: 258
-- Name: merchants_representative_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_representative_details_id_seq OWNED BY public.merchants_representative_details.id;


--
-- TOC entry 260 (class 1259 OID 25822)
-- Name: merchants_representative_details_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_representative_details_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    first_name text,
    middle_name text,
    last_name text,
    full_name text,
    full_name_native text,
    designation text,
    nationality text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    residence_country text,
    postal_code text,
    address1 text,
    address2 text,
    city text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_representative_details_log OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 25836)
-- Name: merchants_service_preferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_service_preferences (
    id bigint NOT NULL,
    merchant_service_preference_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    service_type text,
    service_id text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_service_preferences OWNER TO postgres;

--
-- TOC entry 261 (class 1259 OID 25835)
-- Name: merchants_service_preferences_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_service_preferences_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_service_preferences_id_seq OWNER TO postgres;

--
-- TOC entry 4090 (class 0 OID 0)
-- Dependencies: 261
-- Name: merchants_service_preferences_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_service_preferences_id_seq OWNED BY public.merchants_service_preferences.id;


--
-- TOC entry 263 (class 1259 OID 25853)
-- Name: merchants_service_preferences_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_service_preferences_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    service_type text,
    service_id text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_service_preferences_log OWNER TO postgres;

--
-- TOC entry 265 (class 1259 OID 25867)
-- Name: merchants_stockholders_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_stockholders_details (
    id bigint NOT NULL,
    merchant_stockholder_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    nationality text,
    dob date,
    phone_code text,
    phone_number text,
    email_id text,
    residence_country text,
    percentage_of_share double precision,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_stockholders_details OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 25866)
-- Name: merchants_stockholders_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_stockholders_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_stockholders_details_id_seq OWNER TO postgres;

--
-- TOC entry 4091 (class 0 OID 0)
-- Dependencies: 264
-- Name: merchants_stockholders_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_stockholders_details_id_seq OWNED BY public.merchants_stockholders_details.id;


--
-- TOC entry 266 (class 1259 OID 25884)
-- Name: merchants_stockholders_details_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_stockholders_details_log (
    id bigint NOT NULL,
    merchant_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    nationality text,
    dob date,
    phone_code text,
    phone_number text,
    email_id text,
    residence_country text,
    percentage_of_share double precision,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_stockholders_details_log OWNER TO postgres;

--
-- TOC entry 268 (class 1259 OID 25898)
-- Name: merchants_wallets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_wallets (
    id bigint NOT NULL,
    wallet_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint,
    wallet_type text,
    account_number text,
    virtual_account text,
    currency_code text,
    balance numeric(18,2),
    risk_score integer,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    last_transaction_date timestamp without time zone,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.merchants_wallets OWNER TO postgres;

--
-- TOC entry 267 (class 1259 OID 25897)
-- Name: merchants_wallets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchants_wallets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchants_wallets_id_seq OWNER TO postgres;

--
-- TOC entry 4092 (class 0 OID 0)
-- Dependencies: 267
-- Name: merchants_wallets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchants_wallets_id_seq OWNED BY public.merchants_wallets.id;


--
-- TOC entry 269 (class 1259 OID 25919)
-- Name: merchants_wallets_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchants_wallets_log (
    id bigint,
    merchant_id bigint,
    wallet_type text,
    account_number text,
    virtual_account text,
    currency_code text,
    balance numeric(18,2),
    risk_score integer,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    last_transaction_date timestamp without time zone,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.merchants_wallets_log OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25497)
-- Name: message_queue; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message_queue (
    id bigint NOT NULL,
    message_queue_id uuid DEFAULT public.uuid_generate_v4(),
    message_type text,
    priority text,
    contact_info text,
    cc text,
    bcc text,
    content text,
    subject text,
    schedule_time timestamp without time zone,
    schedule_time_utc timestamp without time zone,
    reference text,
    association_id text,
    association_type text,
    purpose_id text,
    purpose_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.message_queue OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25496)
-- Name: message_queue_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_queue_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_queue_id_seq OWNER TO postgres;

--
-- TOC entry 4093 (class 0 OID 0)
-- Dependencies: 224
-- Name: message_queue_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.message_queue_id_seq OWNED BY public.message_queue.id;


--
-- TOC entry 227 (class 1259 OID 25510)
-- Name: message_templates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message_templates (
    id bigint NOT NULL,
    message_tempate_id uuid DEFAULT public.uuid_generate_v4(),
    template_type text NOT NULL,
    purpose text NOT NULL,
    template text NOT NULL,
    description text,
    valid_from_date timestamp without time zone,
    valid_from_date_utc timestamp without time zone,
    valid_to_date timestamp without time zone,
    valid_to_date_utc timestamp without time zone,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone,
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    subject text
);


ALTER TABLE public.message_templates OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25509)
-- Name: message_templates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_templates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_templates_id_seq OWNER TO postgres;

--
-- TOC entry 4094 (class 0 OID 0)
-- Dependencies: 226
-- Name: message_templates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.message_templates_id_seq OWNED BY public.message_templates.id;


--
-- TOC entry 229 (class 1259 OID 25523)
-- Name: otp; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.otp (
    id bigint NOT NULL,
    otp_id uuid DEFAULT public.uuid_generate_v4(),
    otp_type text,
    purpose text,
    contact_info text,
    otp_code text,
    otp_expire_time timestamp without time zone,
    otp_expire_time_utc timestamp without time zone,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.otp OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 25522)
-- Name: otp_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.otp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.otp_id_seq OWNER TO postgres;

--
-- TOC entry 4095 (class 0 OID 0)
-- Dependencies: 228
-- Name: otp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.otp_id_seq OWNED BY public.otp.id;


--
-- TOC entry 231 (class 1259 OID 25536)
-- Name: partners; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partners (
    id bigint NOT NULL,
    partner_id uuid DEFAULT public.uuid_generate_v4(),
    partner_name text,
    partner_name_native text,
    biz_reg_number text,
    email_id text,
    phone_code text,
    phone_number text,
    settlement_currency_code text,
    website text,
    country_code text,
    prefered_language_code text,
    biz_license_type text,
    city text,
    street_address text,
    postal_code text,
    business_model text,
    partner_type text,
    contract_date date,
    contract_expiry_date date,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone
);


ALTER TABLE public.partners OWNER TO postgres;

--
-- TOC entry 271 (class 1259 OID 25938)
-- Name: partners_bank_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partners_bank_details (
    id bigint NOT NULL,
    partner_id bigint,
    bank_code text,
    bank_name text,
    bank_account_number text,
    bank_account_name text,
    bank_bic text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.partners_bank_details OWNER TO postgres;

--
-- TOC entry 270 (class 1259 OID 25937)
-- Name: partners_bank_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.partners_bank_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.partners_bank_details_id_seq OWNER TO postgres;

--
-- TOC entry 4096 (class 0 OID 0)
-- Dependencies: 270
-- Name: partners_bank_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.partners_bank_details_id_seq OWNED BY public.partners_bank_details.id;


--
-- TOC entry 272 (class 1259 OID 25956)
-- Name: partners_bank_details_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partners_bank_details_log (
    id bigint NOT NULL,
    partner_id bigint NOT NULL,
    bank_code text,
    bank_name text,
    bank_account_number text,
    bank_account_name text,
    bank_bic text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.partners_bank_details_log OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 25535)
-- Name: partners_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.partners_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.partners_id_seq OWNER TO postgres;

--
-- TOC entry 4097 (class 0 OID 0)
-- Dependencies: 230
-- Name: partners_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.partners_id_seq OWNED BY public.partners.id;


--
-- TOC entry 273 (class 1259 OID 25974)
-- Name: partners_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partners_log (
    id integer,
    partner_name text,
    partner_name_native text,
    biz_reg_number text,
    email_id text,
    phone_code text,
    phone_number text,
    settlement_currency_code text,
    website text,
    country_code text,
    prefered_language_code text,
    biz_license_type text,
    city text,
    street_address text,
    postal_code text,
    business_model text,
    partner_type text,
    contract_date date,
    contract_expiry_date date,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.partners_log OWNER TO postgres;

--
-- TOC entry 275 (class 1259 OID 25988)
-- Name: partners_pocs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partners_pocs (
    id bigint NOT NULL,
    partner_id bigint,
    full_name text,
    full_name_native text,
    phone_code text,
    phone_number text,
    email_id text,
    roles text,
    country text,
    city text,
    address1 text,
    postal_code text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.partners_pocs OWNER TO postgres;

--
-- TOC entry 274 (class 1259 OID 25987)
-- Name: partners_pocs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.partners_pocs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.partners_pocs_id_seq OWNER TO postgres;

--
-- TOC entry 4098 (class 0 OID 0)
-- Dependencies: 274
-- Name: partners_pocs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.partners_pocs_id_seq OWNED BY public.partners_pocs.id;


--
-- TOC entry 276 (class 1259 OID 26004)
-- Name: partners_pocs_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partners_pocs_log (
    poc_id bigint NOT NULL,
    partner_id bigint NOT NULL,
    full_name text,
    full_name_native text,
    phone_code text,
    phone_number text,
    email_id text,
    roles text,
    country text,
    city text,
    address1 text,
    postal_code text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.partners_pocs_log OWNER TO postgres;

--
-- TOC entry 303 (class 1259 OID 26326)
-- Name: password_reset_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.password_reset_requests (
    id bigint NOT NULL,
    user_id bigint,
    token text NOT NULL,
    token_expiration_timestamp timestamp with time zone,
    request_timestamp timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    request_ip_address text,
    reset_timestamp timestamp with time zone,
    reset_ip_address text,
    reset_method text,
    reset_source text,
    additional_info text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.password_reset_requests OWNER TO postgres;

--
-- TOC entry 302 (class 1259 OID 26325)
-- Name: password_reset_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.password_reset_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.password_reset_requests_id_seq OWNER TO postgres;

--
-- TOC entry 4099 (class 0 OID 0)
-- Dependencies: 302
-- Name: password_reset_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.password_reset_requests_id_seq OWNED BY public.password_reset_requests.id;


--
-- TOC entry 304 (class 1259 OID 26345)
-- Name: receivers_receipt_methods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.receivers_receipt_methods (
    id bigint NOT NULL,
    transaction_id bigint,
    payment_method text,
    wallet_id bigint,
    bank_code text,
    bank_branch text,
    account_name text,
    account_name_native text,
    account_number text,
    account_type text,
    routing_number text,
    swift_bic text,
    payment_method_provider text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.receivers_receipt_methods OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 25551)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    role_id uuid DEFAULT public.uuid_generate_v4(),
    role_name text,
    role_description text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone,
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 278 (class 1259 OID 26023)
-- Name: roles_claims; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles_claims (
    id bigint NOT NULL,
    role_id integer,
    claim_type text,
    claim_value integer,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.roles_claims OWNER TO postgres;

--
-- TOC entry 277 (class 1259 OID 26022)
-- Name: roles_claims_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_claims_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_claims_id_seq OWNER TO postgres;

--
-- TOC entry 4100 (class 0 OID 0)
-- Dependencies: 277
-- Name: roles_claims_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_claims_id_seq OWNED BY public.roles_claims.id;


--
-- TOC entry 232 (class 1259 OID 25550)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 4101 (class 0 OID 0)
-- Dependencies: 232
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 280 (class 1259 OID 26045)
-- Name: roles_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles_log (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.roles_log OWNER TO postgres;

--
-- TOC entry 279 (class 1259 OID 26044)
-- Name: roles_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_log_id_seq OWNER TO postgres;

--
-- TOC entry 4102 (class 0 OID 0)
-- Dependencies: 279
-- Name: roles_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_log_id_seq OWNED BY public.roles_log.id;


--
-- TOC entry 235 (class 1259 OID 25564)
-- Name: roles_permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles_permissions (
    id bigint NOT NULL,
    slug text,
    display_name text,
    main_group text,
    sub_group text,
    group_display_order integer,
    sub_group_display_order integer,
    permission_status text,
    display_icon text,
    menu_group_type text,
    menu_category text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.roles_permissions OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 25563)
-- Name: roles_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_permissions_id_seq OWNER TO postgres;

--
-- TOC entry 4103 (class 0 OID 0)
-- Dependencies: 234
-- Name: roles_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_permissions_id_seq OWNED BY public.roles_permissions.id;


--
-- TOC entry 306 (class 1259 OID 26361)
-- Name: senders_payment_methods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.senders_payment_methods (
    id bigint NOT NULL,
    transaction_id bigint,
    payment_method text,
    wallet_id bigint,
    bank_code text,
    bank_branch text,
    account_name text,
    account_name_native text,
    account_number text,
    routing_number text,
    swift_bic text,
    payment_method_provider text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.senders_payment_methods OWNER TO postgres;

--
-- TOC entry 305 (class 1259 OID 26360)
-- Name: senders_payment_methods_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.senders_payment_methods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.senders_payment_methods_id_seq OWNER TO postgres;

--
-- TOC entry 4104 (class 0 OID 0)
-- Dependencies: 305
-- Name: senders_payment_methods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.senders_payment_methods_id_seq OWNED BY public.senders_payment_methods.id;


--
-- TOC entry 282 (class 1259 OID 26060)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    id bigint NOT NULL,
    transaction_id uuid DEFAULT public.uuid_generate_v4(),
    merchant_id bigint,
    s_agent_id bigint,
    r_agent_id bigint,
    s_payment_method text,
    r_receipt_method text,
    invoice_number text,
    invoice_path text,
    s_country text,
    r_country text,
    collection_currency text,
    collection_amount numeric(18,2),
    settlement_currency text,
    settlement_amount numeric(18,2),
    exchange_rate numeric(18,6),
    exchange_quote_id text,
    service_charge numeric(18,2),
    commission numeric(18,2),
    wire_charges numeric(18,2),
    txn_cost numeric(18,2),
    txn_gain numeric(18,2),
    txn_source text,
    txn_category text,
    txn_auth_code text,
    txn_risk_score integer,
    promo_code text,
    pass_through_info text,
    txn_status text,
    txn_purpose text,
    txn_type text,
    control_number text,
    external_code text,
    txn_hash text,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    processing_date timestamp without time zone,
    processing_date_utc timestamp without time zone,
    posted_status text,
    posted_reference text,
    posted_date timestamp without time zone,
    posted_date_utc timestamp without time zone,
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 281 (class 1259 OID 26059)
-- Name: transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_id_seq OWNER TO postgres;

--
-- TOC entry 4105 (class 0 OID 0)
-- Dependencies: 281
-- Name: transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;


--
-- TOC entry 283 (class 1259 OID 26077)
-- Name: transactions_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions_log (
    id bigint NOT NULL,
    transaction_id text,
    merchant_id bigint,
    s_agent_id bigint,
    r_agent_id bigint,
    s_payment_method text,
    r_receipt_method text,
    invoice_number text,
    invoice_path text,
    s_country text,
    r_country text,
    collection_currency text,
    collection_amount numeric(18,2),
    settlement_currency text,
    settlement_amount numeric(18,2),
    exchange_rate numeric(18,6),
    exchange_quote_id text,
    service_charge numeric(18,2),
    commission numeric(18,2),
    wire_charges numeric(18,2),
    txn_cost numeric(18,2),
    txn_gain numeric(18,2),
    txn_source text,
    txn_category text,
    txn_auth_code text,
    txn_risk_score integer,
    promo_code text,
    pass_through_info text,
    txn_status text,
    txn_purpose text,
    txn_type text,
    control_number text,
    external_code text,
    txn_hash text,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    processing_date timestamp without time zone,
    processing_date_utc timestamp without time zone,
    posted_status text,
    posted_reference text,
    posted_date timestamp without time zone,
    posted_date_utc timestamp without time zone,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.transactions_log OWNER TO postgres;

--
-- TOC entry 285 (class 1259 OID 26096)
-- Name: transactions_receivers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions_receivers (
    id bigint NOT NULL,
    transaction_id bigint,
    beneficiary_id bigint,
    merchant_id bigint,
    full_name text,
    full_name_native text,
    country text,
    state text,
    city text,
    address1 text,
    address2 text,
    zip_code text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    nationality text,
    biz_registration_no text,
    legal_person_name text,
    receiver_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.transactions_receivers OWNER TO postgres;

--
-- TOC entry 284 (class 1259 OID 26095)
-- Name: transactions_receivers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactions_receivers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_receivers_id_seq OWNER TO postgres;

--
-- TOC entry 4106 (class 0 OID 0)
-- Dependencies: 284
-- Name: transactions_receivers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactions_receivers_id_seq OWNED BY public.transactions_receivers.id;


--
-- TOC entry 286 (class 1259 OID 26122)
-- Name: transactions_receivers_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions_receivers_log (
    id bigint NOT NULL,
    transaction_id bigint,
    beneficiary_id bigint,
    merchant_id bigint,
    full_name text,
    full_name_native text,
    country text,
    state text,
    city text,
    address1 text,
    address2 text,
    zip_code text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    nationality text,
    biz_registration_no text,
    legal_person_name text,
    receiver_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.transactions_receivers_log OWNER TO postgres;

--
-- TOC entry 288 (class 1259 OID 26151)
-- Name: transactions_senders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions_senders (
    id bigint NOT NULL,
    transaction_id integer,
    full_name text,
    full_name_native text,
    country text,
    state text,
    city text,
    address1 text,
    address2 text,
    zip_code text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    nationality text,
    sender_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.transactions_senders OWNER TO postgres;

--
-- TOC entry 287 (class 1259 OID 26150)
-- Name: transactions_senders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactions_senders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_senders_id_seq OWNER TO postgres;

--
-- TOC entry 4107 (class 0 OID 0)
-- Dependencies: 287
-- Name: transactions_senders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactions_senders_id_seq OWNED BY public.transactions_senders.id;


--
-- TOC entry 289 (class 1259 OID 26167)
-- Name: transactions_senders_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions_senders_log (
    id bigint NOT NULL,
    transaction_id integer,
    full_name text,
    full_name_native text,
    country text,
    state text,
    city text,
    address1 text,
    address2 text,
    zip_code text,
    phone_code text,
    phone_number text,
    email_id text,
    dob date,
    nationality text,
    sender_type text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.transactions_senders_log OWNER TO postgres;

--
-- TOC entry 291 (class 1259 OID 26186)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    user_id uuid DEFAULT public.uuid_generate_v4(),
    username text,
    email_id text,
    user_type text,
    password text,
    txn_pwd text,
    security_stamp text,
    phone_code text,
    phone_number text,
    salutation text,
    first_name text,
    middle_name text,
    last_name text,
    full_name text,
    full_name_native text,
    gender text,
    dob date,
    country text,
    address1 text,
    address2 text,
    profile_image text,
    language_preference text,
    notification_preference text,
    source_id text,
    source_type text,
    association_id text,
    association_type text,
    session_timeout_period integer,
    pwd_warning_days integer,
    pwd_expiry_days integer,
    is_email_id_verified boolean DEFAULT false NOT NULL,
    is_force_pwd_change boolean DEFAULT false NOT NULL,
    is_2fa_enabled boolean DEFAULT false NOT NULL,
    access_failed_count integer DEFAULT 0,
    is_api_user boolean DEFAULT false NOT NULL,
    roles text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    last_login_date timestamp without time zone,
    last_login_date_utc timestamp without time zone,
    last_ip_address text,
    last_pwd_changed_date timestamp without time zone,
    last_pwd_changed_date_utc timestamp without time zone,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone,
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 293 (class 1259 OID 26208)
-- Name: users_activity_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_activity_log (
    id bigint NOT NULL,
    user_id bigint,
    activity_timestamp timestamp with time zone,
    activity_type text,
    activity_description text,
    activity_duration interval,
    related_entity_id text,
    additional_info text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text DEFAULT 'system'::text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.users_activity_log OWNER TO postgres;

--
-- TOC entry 292 (class 1259 OID 26207)
-- Name: users_activity_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_activity_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_activity_log_id_seq OWNER TO postgres;

--
-- TOC entry 4108 (class 0 OID 0)
-- Dependencies: 292
-- Name: users_activity_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_activity_log_id_seq OWNED BY public.users_activity_log.id;


--
-- TOC entry 295 (class 1259 OID 26226)
-- Name: users_blocked_devices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_blocked_devices (
    device_id bigint NOT NULL,
    user_id bigint,
    device_type text,
    device_name text,
    ip_adress text,
    mac_address text,
    block_reason text,
    device_location text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text DEFAULT 'system'::text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.users_blocked_devices OWNER TO postgres;

--
-- TOC entry 294 (class 1259 OID 26225)
-- Name: users_blocked_devices_device_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_blocked_devices_device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_blocked_devices_device_id_seq OWNER TO postgres;

--
-- TOC entry 4109 (class 0 OID 0)
-- Dependencies: 294
-- Name: users_blocked_devices_device_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_blocked_devices_device_id_seq OWNED BY public.users_blocked_devices.device_id;


--
-- TOC entry 290 (class 1259 OID 26185)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4110 (class 0 OID 0)
-- Dependencies: 290
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 296 (class 1259 OID 26243)
-- Name: users_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_log (
    id bigint,
    user_id text,
    username text,
    email_id text,
    user_type text,
    password text,
    txn_pwd text,
    security_stamp text,
    phone_code text,
    phone_number text,
    salutation text,
    first_name text,
    middle_name text,
    last_name text,
    full_name text,
    full_name_native text,
    gender text,
    dob date,
    country text,
    address1 text,
    address2 text,
    profile_image text,
    language_preference text,
    notification_preference text,
    source_id text,
    source_type text,
    association_id text,
    association_type text,
    session_timeout_period integer,
    pwd_warning_days integer,
    pwd_expiry_days integer,
    is_email_id_verified boolean DEFAULT false NOT NULL,
    is_force_pwd_change boolean DEFAULT false NOT NULL,
    is_2fa_enabled boolean DEFAULT false NOT NULL,
    access_failed_count integer DEFAULT 0,
    is_api_user boolean DEFAULT false NOT NULL,
    roles text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    ext_map_id_1 text,
    ext_map_id_2 text,
    ext_map_id_3 text,
    ref_col_1 text,
    ref_col_2 text,
    ref_col_3 text,
    ref_col_4 text,
    ref_col_5 text,
    last_login_date timestamp without time zone,
    last_login_date_utc timestamp without time zone,
    last_ip_address text,
    last_pwd_changed_date timestamp without time zone,
    last_pwd_changed_date_utc timestamp without time zone,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.users_log OWNER TO postgres;

--
-- TOC entry 298 (class 1259 OID 26262)
-- Name: users_login_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_login_history (
    id bigint NOT NULL,
    user_id bigint,
    login_timestamp timestamp with time zone,
    login_status boolean DEFAULT false NOT NULL,
    ip_address text,
    user_agent text,
    session_id text,
    login_method text,
    location text,
    device_type text,
    browser_version text,
    operating_system text,
    login_duration interval,
    logout_timestamp timestamp with time zone,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text DEFAULT 'system'::text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.users_login_history OWNER TO postgres;

--
-- TOC entry 297 (class 1259 OID 26261)
-- Name: users_login_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_login_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_login_history_id_seq OWNER TO postgres;

--
-- TOC entry 4111 (class 0 OID 0)
-- Dependencies: 297
-- Name: users_login_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_login_history_id_seq OWNED BY public.users_login_history.id;


--
-- TOC entry 300 (class 1259 OID 26281)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    approved_by text,
    approved_date timestamp without time zone,
    approved_date_utc timestamp without time zone,
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- TOC entry 299 (class 1259 OID 26280)
-- Name: users_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_roles_id_seq OWNER TO postgres;

--
-- TOC entry 4112 (class 0 OID 0)
-- Dependencies: 299
-- Name: users_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_roles_id_seq OWNED BY public.users_roles.id;


--
-- TOC entry 301 (class 1259 OID 26302)
-- Name: users_roles_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles_log (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text)
);


ALTER TABLE public.users_roles_log OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 25578)
-- Name: users_signup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_signup (
    id bigint NOT NULL,
    user_signup_id uuid DEFAULT public.uuid_generate_v4(),
    source_type text,
    incorporation_country text,
    contact_info text,
    phone_code text,
    phone_number text,
    is_email_otp_verified boolean DEFAULT false NOT NULL,
    is_sms_otp_verified boolean DEFAULT false NOT NULL,
    ip_address text,
    signup_source text,
    remarks text,
    status text NOT NULL,
    is_active boolean DEFAULT false NOT NULL,
    entity_hash text,
    created_by text NOT NULL,
    created_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_date_utc timestamp with time zone DEFAULT (now() AT TIME ZONE 'UTC'::text),
    updated_by text,
    updated_date timestamp without time zone,
    updated_date_utc timestamp without time zone,
    association_type text,
    contact_type text
);


ALTER TABLE public.users_signup OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 25577)
-- Name: users_signup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_signup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_signup_id_seq OWNER TO postgres;

--
-- TOC entry 4113 (class 0 OID 0)
-- Dependencies: 236
-- Name: users_signup_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_signup_id_seq OWNED BY public.users_signup.id;


--
-- TOC entry 3501 (class 2604 OID 25596)
-- Name: beneficiary_profiles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.beneficiary_profiles ALTER COLUMN id SET DEFAULT nextval('public.beneficiary_profiles_id_seq'::regclass);


--
-- TOC entry 3441 (class 2604 OID 25440)
-- Name: category_roots id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_roots ALTER COLUMN id SET DEFAULT nextval('public.category_roots_id_seq'::regclass);


--
-- TOC entry 3509 (class 2604 OID 25632)
-- Name: category_roots_log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_roots_log ALTER COLUMN id SET DEFAULT nextval('public.category_roots_log_id_seq'::regclass);


--
-- TOC entry 3446 (class 2604 OID 25453)
-- Name: documents id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documents ALTER COLUMN id SET DEFAULT nextval('public.documents_id_seq'::regclass);


--
-- TOC entry 3451 (class 2604 OID 25466)
-- Name: error_codes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.error_codes ALTER COLUMN id SET DEFAULT nextval('public.error_codes_id_seq'::regclass);


--
-- TOC entry 3513 (class 2604 OID 25647)
-- Name: error_possible_actions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.error_possible_actions ALTER COLUMN id SET DEFAULT nextval('public.error_possible_actions_id_seq'::regclass);


--
-- TOC entry 3455 (class 2604 OID 25480)
-- Name: merchants id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants ALTER COLUMN id SET DEFAULT nextval('public.merchants_id_seq'::regclass);


--
-- TOC entry 3517 (class 2604 OID 25664)
-- Name: merchants_bank_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_bank_details ALTER COLUMN id SET DEFAULT nextval('public.merchants_bank_details_id_seq'::regclass);


--
-- TOC entry 3527 (class 2604 OID 25697)
-- Name: merchants_directors_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_directors_details ALTER COLUMN id SET DEFAULT nextval('public.merchants_directors_details_id_seq'::regclass);


--
-- TOC entry 3535 (class 2604 OID 25728)
-- Name: merchants_documents id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_documents ALTER COLUMN id SET DEFAULT nextval('public.merchants_documents_id_seq'::regclass);


--
-- TOC entry 3551 (class 2604 OID 25777)
-- Name: merchants_owners_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_owners_details ALTER COLUMN id SET DEFAULT nextval('public.merchants_owners_details_id_seq'::regclass);


--
-- TOC entry 3559 (class 2604 OID 25808)
-- Name: merchants_representative_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_representative_details ALTER COLUMN id SET DEFAULT nextval('public.merchants_representative_details_id_seq'::regclass);


--
-- TOC entry 3567 (class 2604 OID 25839)
-- Name: merchants_service_preferences id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_service_preferences ALTER COLUMN id SET DEFAULT nextval('public.merchants_service_preferences_id_seq'::regclass);


--
-- TOC entry 3575 (class 2604 OID 25870)
-- Name: merchants_stockholders_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_stockholders_details ALTER COLUMN id SET DEFAULT nextval('public.merchants_stockholders_details_id_seq'::regclass);


--
-- TOC entry 3583 (class 2604 OID 25901)
-- Name: merchants_wallets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets ALTER COLUMN id SET DEFAULT nextval('public.merchants_wallets_id_seq'::regclass);


--
-- TOC entry 3465 (class 2604 OID 25500)
-- Name: message_queue id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_queue ALTER COLUMN id SET DEFAULT nextval('public.message_queue_id_seq'::regclass);


--
-- TOC entry 3470 (class 2604 OID 25513)
-- Name: message_templates id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_templates ALTER COLUMN id SET DEFAULT nextval('public.message_templates_id_seq'::regclass);


--
-- TOC entry 3475 (class 2604 OID 25526)
-- Name: otp id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.otp ALTER COLUMN id SET DEFAULT nextval('public.otp_id_seq'::regclass);


--
-- TOC entry 3480 (class 2604 OID 25539)
-- Name: partners id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners ALTER COLUMN id SET DEFAULT nextval('public.partners_id_seq'::regclass);


--
-- TOC entry 3591 (class 2604 OID 25941)
-- Name: partners_bank_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_bank_details ALTER COLUMN id SET DEFAULT nextval('public.partners_bank_details_id_seq'::regclass);


--
-- TOC entry 3601 (class 2604 OID 25991)
-- Name: partners_pocs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_pocs ALTER COLUMN id SET DEFAULT nextval('public.partners_pocs_id_seq'::regclass);


--
-- TOC entry 3679 (class 2604 OID 26329)
-- Name: password_reset_requests id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_requests ALTER COLUMN id SET DEFAULT nextval('public.password_reset_requests_id_seq'::regclass);


--
-- TOC entry 3485 (class 2604 OID 25554)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 3608 (class 2604 OID 26026)
-- Name: roles_claims id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_claims ALTER COLUMN id SET DEFAULT nextval('public.roles_claims_id_seq'::regclass);


--
-- TOC entry 3612 (class 2604 OID 26048)
-- Name: roles_log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_log ALTER COLUMN id SET DEFAULT nextval('public.roles_log_id_seq'::regclass);


--
-- TOC entry 3490 (class 2604 OID 25567)
-- Name: roles_permissions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_permissions ALTER COLUMN id SET DEFAULT nextval('public.roles_permissions_id_seq'::regclass);


--
-- TOC entry 3687 (class 2604 OID 26364)
-- Name: senders_payment_methods id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.senders_payment_methods ALTER COLUMN id SET DEFAULT nextval('public.senders_payment_methods_id_seq'::regclass);


--
-- TOC entry 3616 (class 2604 OID 26063)
-- Name: transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);


--
-- TOC entry 3624 (class 2604 OID 26099)
-- Name: transactions_receivers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers ALTER COLUMN id SET DEFAULT nextval('public.transactions_receivers_id_seq'::regclass);


--
-- TOC entry 3631 (class 2604 OID 26154)
-- Name: transactions_senders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_senders ALTER COLUMN id SET DEFAULT nextval('public.transactions_senders_id_seq'::regclass);


--
-- TOC entry 3638 (class 2604 OID 26189)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3648 (class 2604 OID 26211)
-- Name: users_activity_log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_activity_log ALTER COLUMN id SET DEFAULT nextval('public.users_activity_log_id_seq'::regclass);


--
-- TOC entry 3653 (class 2604 OID 26229)
-- Name: users_blocked_devices device_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_blocked_devices ALTER COLUMN device_id SET DEFAULT nextval('public.users_blocked_devices_device_id_seq'::regclass);


--
-- TOC entry 3666 (class 2604 OID 26265)
-- Name: users_login_history id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_login_history ALTER COLUMN id SET DEFAULT nextval('public.users_login_history_id_seq'::regclass);


--
-- TOC entry 3672 (class 2604 OID 26284)
-- Name: users_roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles ALTER COLUMN id SET DEFAULT nextval('public.users_roles_id_seq'::regclass);


--
-- TOC entry 3494 (class 2604 OID 25581)
-- Name: users_signup id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_signup ALTER COLUMN id SET DEFAULT nextval('public.users_signup_id_seq'::regclass);


--
-- TOC entry 4004 (class 0 OID 25593)
-- Dependencies: 239
-- Data for Name: beneficiary_profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.beneficiary_profiles (id, beneficiary_id, merchant_id, full_name, full_name_native, country, state, city, address1, address2, zip_code, phone_code, phone_number, email_id, dob, nationality, biz_registration_no, legal_person_name, beneficiary_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4005 (class 0 OID 25610)
-- Dependencies: 240
-- Data for Name: beneficiary_profiles_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.beneficiary_profiles_log (id, merchant_id, full_name, full_name_native, country, state, city, address1, address2, zip_code, phone_code, phone_number, email_id, dob, nationality, biz_registration_no, legal_person_name, beneficiary_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 3982 (class 0 OID 25437)
-- Dependencies: 217
-- Data for Name: category_roots; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category_roots (id, category_root_id, category_type, sub_type, category_code, description, parent_type, parent_code, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc, approved_by, approved_date, approved_date_utc) FROM stdin;
1	e54117a3-e921-4f6d-9d9d-552c7659bd89	KYCREJECTIONREASONS	\N	Invalid Identification	Invalid Identification	\N	\N	\N	\N	\N	\N	\N	The submitted identification documents, such as passports, driver's licenses, or ID cards, are expired, forged, or do not meet the organization's standards.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
2	f124c85f-7017-45ea-a1c6-83976af8ed14	KYCREJECTIONREASONS	\N	Incomplete Documentation	Incomplete Documentation	\N	\N	\N	\N	\N	\N	\N	The customer has not provided all the required documents or information for identity verification.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
3	3e720e82-edb3-4240-bdf3-50110807dd31	KYCREJECTIONREASONS	\N	Failure to Provide Additional Information	Failure to Provide Additional Information	\N	\N	\N	\N	\N	\N	\N	The customer was requested to provide additional information or clarifications, but they did not comply within the specified timeframe.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
4	53041b85-29a9-4ba6-8323-29b030de753d	COUNTRY	\N	AF	Afghanistan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
5	e5072e05-decd-4ea5-9b2a-49549bdb121d	USERSTATUS	\N	LOCK	LOCK	\N	\N	\N	\N	\N	\N	\N	Locking a user account temporarily restricts the user from accessing the system. This action is often taken in response to multiple failed login attempts or security concerns	ACTIVE	t	\N	system	2023-09-18 12:40:10.323	2023-09-18 06:55:10.323+05:45	\N	\N	\N	\N	\N	\N
6	3a15e40a-4f0c-4346-9722-54afa2eac0ba	USERSTATUS	\N	UNLOCK	UNLOCK	\N	\N	\N	\N	\N	\N	\N	If a user account has been locked, administrators can unlock it to restore the user access.	ACTIVE	t	\N	system	2023-09-18 12:43:46.101	2023-09-18 06:58:46.101+05:45	\N	\N	\N	\N	\N	\N
7	9adf377b-a0c4-4598-8b0c-f2d142e0a584	USERSTATUS	\N	SUSPEND	SUSPEND	\N	\N	\N	\N	\N	\N	\N	Suspending a user account temporarily disables it without permanently deleting it. It is often used when a user actions require investigation or when a user is temporarily inactive	ACTIVE	t	\N	system	2023-09-18 12:43:46.101	2023-09-18 06:58:46.101+05:45	\N	\N	\N	\N	\N	\N
8	a2510507-b04c-4a0e-bb67-56a7e0aaa54d	USERSTATUS	\N	REACTIVATE	REACTIVATE	\N	\N	\N	\N	\N	\N	\N	If a users account has been suspended or deactivated, administrators can reactivate it to restore access.	ACTIVE	t	\N	system	2023-09-18 12:43:46.101	2023-09-18 06:58:46.101+05:45	\N	\N	\N	\N	\N	\N
9	90f87994-d18d-49fd-97e2-a7ac75f813b4	SALUTATION	\N	Miss	Miss	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:48:24.913	2023-09-18 07:03:24.913+05:45	\N	\N	\N	\N	\N	\N
10	cac0428e-f1a9-4a02-aeac-73423911853a	SALUTATION	\N	Mrs.	Mrs.	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:48:24.913	2023-09-18 07:03:24.913+05:45	\N	\N	\N	\N	\N	\N
11	6e192d11-a185-4729-86eb-5d5a01248c8f	SALUTATION	\N	Mr.	Mr.	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:48:24.913	2023-09-18 07:03:24.913+05:45	\N	\N	\N	\N	\N	\N
12	cb5dcb10-d665-4297-b82d-0dec65111af3	DESIGNATIONS	\N	Supervisor	Supervisor	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
13	7bdb856a-da2a-4983-ab8c-7b96a5ed478a	DESIGNATIONS	\N	Director	Director	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
14	ec01cf0a-eba7-4f13-839b-83a122c29e55	DESIGNATIONS	\N	Manager	Manager	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
15	89901314-0d02-4c1b-aa55-6f1fb89d6ab9	DESIGNATIONS	\N	Sales Representative	Sales Representative	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
16	95420060-be03-4ba2-bdc6-a27c53a8a06c	DESIGNATIONS	\N	Account Executive	Account Executive	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
17	e2c18a9c-dc71-4e9b-92fb-9043165c02c0	DESIGNATIONS	\N	Chief Operating Officer (COO)	Chief Operating Officer (COO)	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
18	cdd5504c-d363-47c3-82b9-89a11dec500e	DESIGNATIONS	\N	Chief Executive Officer (CEO)	Chief Executive Officer (CEO)	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
19	310cba18-7ba5-4203-9807-353174abdb58	DESIGNATIONS	\N	Chief Financial Officer (CFO)	Chief Financial Officer (CFO)	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 12:58:10.483	2023-09-18 07:13:10.483+05:45	\N	\N	\N	\N	\N	\N
20	d94fee74-039a-4399-8f4a-64f2b093de73	KYCREJECTIONREASONS	\N	Data Quality Issues	Data Quality Issues	\N	\N	\N	\N	\N	\N	\N	Issues related to data quality, such as illegible documents, incomplete forms, or poor-quality scans or photographs.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
21	4595eb2b-3671-4f3a-bd94-07f242c7b496	KYCREJECTIONREASONS	\N	Unverifiable Information	Unverifiable Information	\N	\N	\N	\N	\N	\N	\N	The information provided by the customer cannot be verified through reliable sources, such as government databases or credit bureaus.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
22	718841b6-c30a-4d81-b1a9-6f972327822d	KYCREJECTIONREASONS	\N	Non-Compliance with Regulations	Non-Compliance with Regulations	\N	\N	\N	\N	\N	\N	\N	The customer's information or documentation does not comply with legal and regulatory requirements, including anti-money laundering (AML) and counter-terrorism financing (CTF) regulations.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
23	74333cef-e852-4733-ba87-45c5417ce9e8	KYCREJECTIONREASONS	\N	Failure to Meet Thresholds	Failure to Meet Thresholds	\N	\N	\N	\N	\N	\N	\N	The customer's financial transactions or activities have exceeded certain thresholds that trigger a more rigorous KYC review.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
24	8491cd91-98da-491c-96e7-6081c72a1bab	KYCREJECTIONREASONS	\N	Expired KYC Information	Expired KYC Information	\N	\N	\N	\N	\N	\N	\N	The customer's previously verified KYC information has expired, and they have not updated it within the required timeframe.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
25	c57c9b6a-ef93-42bd-82a4-11bdffb3bc5d	KYCREJECTIONREASONS	\N	Mismatched Information	Mismatched Information	\N	\N	\N	\N	\N	\N	\N	There are inconsistencies or discrepancies in the information provided by the customer. This can include differences in names, addresses, or other personal details.	ACTIVE	t	\N	system	2023-09-18 13:08:35.71	2023-09-18 07:23:35.71+05:45	\N	\N	\N	\N	\N	\N
26	c3410b7a-9a00-4968-ad91-69d220d6c9fe	COUNTRY	\N	AL	Albania	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
27	eda17701-ffc1-4b48-8c2e-9be4e595a4a6	COUNTRY	\N	DZ	Algeria	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
28	c22a80ee-a81d-401d-ac31-2333344fa567	COUNTRY	\N	AS	American Samoa	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
29	9ace2bb2-8f87-4896-9ff9-7845b870d92e	COUNTRY	\N	AD	Andorra	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
30	b5a93906-517c-4df9-a0c5-54aa167e4025	COUNTRY	\N	AO	Angola	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
31	b90f0380-9a73-4e38-80d3-3e01349ba6d5	COUNTRY	\N	AI	Anguilla	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
32	9602a454-eda5-47e7-8833-a17203937389	COUNTRY	\N	AQ	Antarctica	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
33	986d871b-4c2b-4836-ab32-f7900810c461	COUNTRY	\N	AG	Antigua and Barbuda	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
34	f6911065-165c-4ba9-9063-8d1428503eb9	COUNTRY	\N	AR	Argentina	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
35	90f2058b-5dc1-46dd-91ef-650947f6fcd1	COUNTRY	\N	AM	Armenia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
36	e36aa029-8e95-4512-b4b2-571483e8f16a	COUNTRY	\N	AW	Aruba	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
37	e9f07ed0-55f2-46fd-aa99-5e5739ee0b7d	COUNTRY	\N	AU	Australia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
38	b8b9e3a0-ae54-4a87-935d-1af9ee974466	COUNTRY	\N	AT	Austria	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
39	ee1e86a2-ad8c-48f1-9527-ea46fa1f8832	COUNTRY	\N	AZ	Azerbaijan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
40	e3ff4082-0abc-49a1-a721-20f8bc7dc48b	COUNTRY	\N	BS	Bahamas	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
41	a923164d-e93a-4ff2-810a-39edca125033	COUNTRY	\N	BH	Bahrain	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
42	e53643ac-50ea-4dd9-950c-6088dbca7059	COUNTRY	\N	BD	Bangladesh	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
43	5212a46d-dd24-4793-9a9b-895fc6cb59a2	COUNTRY	\N	BB	Barbados	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
44	641487c1-6642-4923-b9e3-b43ab51df984	COUNTRY	\N	BY	Belarus	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
45	988227e5-822a-42ef-a684-09d87ca43afd	COUNTRY	\N	BE	Belgium	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
46	2b8bf4cf-e0f6-4093-bcec-809b3b944102	COUNTRY	\N	BZ	Belize	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
47	b5bf1621-3033-438c-807b-03d89858d663	COUNTRY	\N	BJ	Benin	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
48	8c8d160e-b7de-4411-b4e3-618e58bb92e5	COUNTRY	\N	BM	Bermuda	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
49	2e811d97-7684-46ab-a322-de6f0d26874d	COUNTRY	\N	BT	Bhutan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
50	eb245bfe-6776-4e5b-95b0-3b4a571ff09c	COUNTRY	\N	BO	Bolivia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
51	ab0156ac-0fba-4347-ac35-d41f0f2d677f	COUNTRY	\N	BA	Bosnia and Herzegovina	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
52	2dce9b18-e1dd-42bc-9bd9-2dd637a4cdad	COUNTRY	\N	BW	Botswana	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
53	d14750bf-862d-4be1-8d76-dbeaa1ad5d1d	COUNTRY	\N	BV	Bouvet Island	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
54	8475ce66-66f9-4d96-82a1-0e9db9d1f098	COUNTRY	\N	BR	Brazil	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
55	8a3e4d17-3388-49f9-8d74-00e89747d25d	COUNTRY	\N	IO	British Indian Ocean Territory	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
56	72755db7-67eb-49e4-832d-f7140e12ec41	COUNTRY	\N	BN	Brunei Darussalam	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
57	42a50f5a-e9af-4f4f-a891-27946d6c0bec	COUNTRY	\N	BG	Bulgaria	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
58	7ca96b6c-d43f-4d58-b1d0-08c50f4e2364	COUNTRY	\N	BF	Burkina Faso	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
59	386b4a52-7196-4c87-a16a-dc3473c27276	ACCOUNTTYPE	\N	Business	Business	\N	\N	\N	\N	\N	\N	\N	This option is for users who represent a business or company.	ACTIVE	t	\N	system	2023-09-18 14:32:01.173	2023-09-18 08:47:01.173+05:45	\N	\N	\N	\N	\N	\N
60	cebe2914-4702-4c14-98cb-f1ada5b27bf6	ACCOUNTTYPE	\N	Individual	Individual	\N	\N	\N	\N	\N	\N	\N	This option is for users who want to use the platform for freelancing activities	ACTIVE	t	\N	system	2023-09-18 14:32:01.173	2023-09-18 08:47:01.173+05:45	\N	\N	\N	\N	\N	\N
61	207c2316-559d-45ce-8d3c-54a491e7afda	BUSINESSTYPE	\N	Sole Proprietorship	Sole Proprietorship	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:38:48.573	2023-09-18 08:53:48.573+05:45	\N	\N	\N	\N	\N	\N
62	50100283-4422-4196-8507-09f4ad8606a6	COUNTRY	\N	BI	Burundi	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
63	32414728-cc95-40d7-bc00-e008b0e7e291	BUSINESSTYPE	\N	Limited Company	Limited Company	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:38:48.573	2023-09-18 08:53:48.573+05:45	\N	\N	\N	\N	\N	\N
64	f19d4501-16e1-4288-b8fc-bac783a11c7e	BUSINESSTYPE	\N	Public Company	Public Company	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:38:48.573	2023-09-18 08:53:48.573+05:45	\N	\N	\N	\N	\N	\N
65	5d0155d6-db68-4f1a-82e8-a9d397ed857e	BUSINESSTYPE	\N	Partnership Company	Partnership Company	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:38:48.573	2023-09-18 08:53:48.573+05:45	\N	\N	\N	\N	\N	\N
66	69835a4f-8dad-419f-9e92-f85770fe6779	BUSINESSTYPE	\N	Others	Others	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:38:48.573	2023-09-18 08:53:48.573+05:45	\N	\N	\N	\N	\N	\N
67	ba7011ad-c9da-408a-b2f9-6d10a57b2bb0	PRODUCTTYPE	\N	Cosmetics	Cosmetics	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:45:55.371	2023-09-18 09:00:55.371+05:45	\N	\N	\N	\N	\N	\N
68	6cb418a2-5e79-4ae0-a31e-76e1e0c291cd	PRODUCTTYPE	\N	Clothing	Clothing	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:45:55.371	2023-09-18 09:00:55.371+05:45	\N	\N	\N	\N	\N	\N
69	07646c3e-b9d1-4518-935c-1c1465b7fb49	PRODUCTTYPE	\N	Utensils	Utensils	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:45:55.371	2023-09-18 09:00:55.371+05:45	\N	\N	\N	\N	\N	\N
70	52260136-aea3-4fe4-ac4c-bddbcbd1c181	PRODUCTTYPE	\N	Fashion	Fashion	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:45:55.371	2023-09-18 09:00:55.371+05:45	\N	\N	\N	\N	\N	\N
71	21275199-3664-434e-860c-b8f2b0b56d8c	PRODUCTTYPE	\N	Others	Others	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:45:55.371	2023-09-18 09:00:55.371+05:45	\N	\N	\N	\N	\N	\N
72	aaeb0b69-1be8-439a-8dcd-8ccf38d295e7	PRODUCTTYPE	\N	Food	Food	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:45:55.371	2023-09-18 09:00:55.371+05:45	\N	\N	\N	\N	\N	\N
73	c1eda155-16d5-4e58-80bd-ea331fbab762	INDUSTRYTYPE	\N	E-commerce Seller	E-commerce Seller	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
74	b0b071be-522b-445d-9bac-788793f44ba2	INDUSTRYTYPE	\N	Accommodation	Accommodation	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
75	350fc6f8-1071-42bb-adec-0d9742a8e54e	INDUSTRYTYPE	\N	Retailers	Retailers	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
76	0ea4e464-edcc-45a9-8f54-72c2a513a2b1	INDUSTRYTYPE	\N	Digital Marketer	Digital Marketer	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
77	20eed669-f037-4f1b-ad41-91674ec5c965	INDUSTRYTYPE	\N	Special Service	Special Service	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
78	c6c3eb48-8e5b-4029-9488-75f6eb0e20a2	INDUSTRYTYPE	\N	Trader	Trader	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
79	64b75817-4ff0-4e5e-9dda-36b45c92f799	INDUSTRYTYPE	\N	Vendors	Vendors	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
80	f316ff8a-827f-4137-b6dd-ab45c00ec7c2	INDUSTRYTYPE	\N	Suppliers	Suppliers	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-18 14:42:36.425	2023-09-18 08:57:36.425+05:45	\N	\N	\N	\N	\N	\N
81	9c23008b-aaf6-49fd-bb83-5a4177dcb26d	MAKEPAYMENTS	\N	Settlements	Settlements	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:11:55.414	2023-09-25 08:26:55.414+05:45	\N	\N	\N	\N	\N	\N
82	d7f7e404-87bb-46f9-b057-b76cc756b07f	MAKEPAYMENTS	\N	Service Providers	Service Providers	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:11:55.414	2023-09-25 08:26:55.414+05:45	\N	\N	\N	\N	\N	\N
83	a85e5b88-e7dd-4c6f-aaf9-ac26857c0703	MAKEPAYMENTS	\N	Import/Export	Import/Export	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:11:55.414	2023-09-25 08:26:55.414+05:45	\N	\N	\N	\N	\N	\N
84	d178c695-b49a-45ac-b5a5-7a2ff99b0aae	MAKEPAYMENTS	\N	Vendors & Suppliers	Vendors & Suppliers	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:11:55.414	2023-09-25 08:26:55.414+05:45	\N	\N	\N	\N	\N	\N
85	61a49c1f-dac6-4a70-8dac-a41dfec2be23	GETPAID	\N	Marketplaces	Marketplaces	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:13:34.971	2023-09-25 08:28:34.971+05:45	\N	\N	\N	\N	\N	\N
86	e4560b05-fb3b-4ef3-9069-0f3e1133cfd6	GETPAID	\N	Settlements	Settlements	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:13:34.971	2023-09-25 08:28:34.971+05:45	\N	\N	\N	\N	\N	\N
87	7142832f-94eb-4a9b-87cc-a29ded2737c6	GETPAID	\N	Direct Sales	Direct Sales	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:13:34.971	2023-09-25 08:28:34.971+05:45	\N	\N	\N	\N	\N	\N
88	ed33cdea-5bae-447a-b453-ae0d35145c91	GETPAID	\N	Import/Export	Import/Export	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:13:34.971	2023-09-25 08:28:34.971+05:45	\N	\N	\N	\N	\N	\N
89	a0ee277e-c806-40ad-9ad7-0c79d97b7ecc	GETPAID	\N	Service Providers	Service Providers	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:13:34.971	2023-09-25 08:28:34.971+05:45	\N	\N	\N	\N	\N	\N
90	886747f6-b20d-427b-a522-4cf3b7421aba	COUNTRY	\N	KH	Cambodia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
91	f2d743dc-0fd8-4020-9533-3709418cb823	COUNTRY	\N	CM	Cameroon	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
92	40ba3934-4545-499a-bc7e-244c25f8da58	COUNTRY	\N	CA	Canada	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
93	9da66b90-acae-49a4-b087-20c1b38f914e	COUNTRY	\N	CV	Cape Verde	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
94	9552a5b3-4023-4be6-85f8-eff12c581343	COUNTRY	\N	KY	Cayman Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
95	8c641176-9847-4d59-bec2-846c2287b288	COUNTRY	\N	CF	Central African Republic	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
96	7ba8e532-fa02-4e9e-a338-4d0d164f0b8f	COUNTRY	\N	TD	Chad	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
97	1445ccfa-3132-4968-8aa9-6dae6bd42d54	COUNTRY	\N	CL	Chile	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
98	0259f7e5-1562-4716-9ae6-740d2c919fa0	COUNTRY	\N	CN	China	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
99	dfb17cb8-e3ae-42d6-a887-5028f4b79b3c	COUNTRY	\N	CX	Christmas Island	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
100	ac35d2f4-ceee-424d-a25b-a737cbb87980	COUNTRY	\N	CC	Cocos (Keeling) Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
101	1c0c3747-827b-4113-98d0-3c06d777601f	COUNTRY	\N	CO	Colombia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
102	f496f34f-2764-4654-bcd0-937349d8d0fc	COUNTRY	\N	KM	Comoros	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
103	485a89fa-80eb-4fdb-b73b-b7bc3783f127	GETPAID	\N	Vendors & Suppliers	Vendors & Suppliers	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-25 14:13:34.971	2023-09-25 08:28:34.971+05:45	\N	\N	\N	\N	\N	\N
104	a75c7a08-4641-418c-8831-e3695dcddde0	COUNTRY	\N	CG	Congo	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
105	f71366c3-87a2-4820-9de5-e1256c41c5d5	COUNTRY	\N	CD	Congo, the Democratic Republic of the	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
106	c21abe10-fcbf-441c-877e-c7c0df35a88a	COUNTRY	\N	CK	Cook Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
107	2e840c8b-fb21-4a51-9655-856a41297bf6	COUNTRY	\N	CR	Costa Rica	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
108	4ea0bb72-1143-44a2-b17b-9424868bb04f	COUNTRY	\N	CI	Cote D'Ivoire	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
109	35953ddd-8019-4ad3-b6aa-9c16e5d796e4	COUNTRY	\N	HR	Croatia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
110	678505fe-7dee-48c2-aed3-863386b8872e	COUNTRY	\N	CU	Cuba	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
111	7d0c9581-bb57-49c4-9dfc-0b48171209b1	COUNTRY	\N	CY	Cyprus	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
112	087627c5-b718-46f8-bb6b-5231ba06554f	COUNTRY	\N	CZ	Czech Republic	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
113	c3d96217-3779-4a69-aa75-1a822c081a82	COUNTRY	\N	DK	Denmark	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
114	6d3f6b2c-4a7e-4665-94ec-d8da53f752d0	COUNTRY	\N	DJ	Djibouti	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
115	78ae961c-a861-4fd3-abe3-9a2d36b84d80	COUNTRY	\N	DM	Dominica	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
116	f378985f-ce8c-472d-8178-3ade2f681285	COUNTRY	\N	DO	Dominican Republic	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
117	10f941a5-4690-4bff-b554-86254e3a3574	COUNTRY	\N	EC	Ecuador	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
118	c73a34f5-947d-4fed-bbe6-10baaa6006d2	COUNTRY	\N	EG	Egypt	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
119	3a4fa29f-3e0b-4630-815b-6ebaebdce859	COUNTRY	\N	SV	El Salvador	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
120	57fbeedf-1ce6-4c33-8dfc-c9221bbd870c	COUNTRY	\N	GQ	Equatorial Guinea	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
121	f90b7571-c3ac-4f70-882f-9541f776f5f0	COUNTRY	\N	ER	Eritrea	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
122	544fd03b-bfdd-4a00-8226-3e3707089a1f	COUNTRY	\N	EE	Estonia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
123	0afc17e3-6233-4da9-ba70-13fa9c3cc807	COUNTRY	\N	ET	Ethiopia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
124	72e0f3c0-6d6b-4d66-8707-1eff613aedfe	COUNTRY	\N	FK	Falkland Islands (Malvinas)	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
125	0549f043-7fb5-4d21-a9de-14bcde3275a1	COUNTRY	\N	FO	Faroe Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
126	c85c80c2-435f-45ac-a10e-e43d653cdc3b	COUNTRY	\N	FJ	Fiji	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
127	52b42fea-7119-4f4b-bd59-4f53dbd1aa03	COUNTRY	\N	FI	Finland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
128	9f8579ed-9035-4bd1-8e50-fe6197ecf4fe	COUNTRY	\N	FR	France	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
129	255236a3-a066-49b5-862d-ea3cf33c8260	COUNTRY	\N	GF	French Guiana	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
130	d4e4a30a-b147-47c2-9cfd-eb2433ceebb6	COUNTRY	\N	PF	French Polynesia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
131	21adb117-3509-419e-836d-dc74b527c0fc	COUNTRY	\N	TF	French Southern Territories	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
132	6cd39d44-920a-4385-b95a-189cb46479c5	COUNTRY	\N	GA	Gabon	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
133	fc262b0b-77da-4ceb-a60e-6d4c52bb2a9e	COUNTRY	\N	GM	Gambia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
134	0d59e602-d742-40a4-9eb0-a3f55a2fd717	COUNTRY	\N	GE	Georgia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
135	8a123980-5411-40aa-a68e-6b9dea954356	COUNTRY	\N	DE	Germany	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
136	7528108f-96e8-481c-be56-33406949157e	COUNTRY	\N	GH	Ghana	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
137	7cf09330-2289-446e-8461-71c1de1da6a0	COUNTRY	\N	GI	Gibraltar	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
138	b3881d1a-20fc-4792-ad13-6e115e1c2992	COUNTRY	\N	GR	Greece	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
139	13cc57f9-6beb-4cc5-8477-396e707ab322	COUNTRY	\N	GL	Greenland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
140	8ba1f3b2-79b3-4514-be69-101c324e6232	COUNTRY	\N	GD	Grenada	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
141	b6461eab-8378-45a5-b7c2-03a16b4f0751	COUNTRY	\N	GP	Guadeloupe	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
142	f2b49a49-6149-454d-8135-ac2d799008d1	COUNTRY	\N	GU	Guam	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
143	7a63ca96-466a-49dc-8307-715c896a9332	COUNTRY	\N	GT	Guatemala	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
144	b7a2b667-01e9-41c4-a621-a94abb652bcf	COUNTRY	\N	GN	Guinea	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
145	746b695a-147a-4b0b-8319-f1cb3b58db3b	COUNTRY	\N	GW	Guinea-Bissau	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
146	bdb2222f-d58d-4612-a73d-72b589eb2566	COUNTRY	\N	GY	Guyana	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
147	489fdba6-0a61-4866-bb6c-df21635be6b7	COUNTRY	\N	HT	Haiti	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
148	7f924fc7-040a-4303-832c-685f20608176	COUNTRY	\N	HM	Heard Island and Mcdonald Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
149	df0a76b4-33a6-41e7-b0d6-b4d509f8cad7	COUNTRY	\N	VA	Holy See (Vatican City State)	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
150	873f519b-ada0-4c87-ba5a-deb4edc09291	COUNTRY	\N	HN	Honduras	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
151	f351028c-d455-4451-83c5-420320f95008	COUNTRY	\N	HK	Hong Kong	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
152	7803f80b-8f6c-4c3c-b174-f13694d86af7	COUNTRY	\N	HU	Hungary	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
153	eb08a851-668e-4003-9465-ce49abd1c98a	COUNTRY	\N	IS	Iceland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
154	6f9335af-efd8-47d7-87a6-6a38fdcef6b9	COUNTRY	\N	IN	India	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
155	2bfc1e66-9a0f-4ae8-84ed-ca39e5492d2f	COUNTRY	\N	ID	Indonesia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
156	41f2c33c-257b-4eb2-9733-1254ac51d611	COUNTRY	\N	IR	Iran, Islamic Republic of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
157	56e20abe-7f8a-4c10-9c3a-2b35bdc0fc58	COUNTRY	\N	IQ	Iraq	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
158	9198522d-1b0c-4ee3-a6b0-e92d92055f2a	COUNTRY	\N	IE	Ireland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
159	1787111c-7c17-4bd5-a03a-c5f7bb8d257d	COUNTRY	\N	IL	Israel	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
160	321b330f-a45c-405a-87ca-4ff520fcb191	COUNTRY	\N	IT	Italy	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
161	eda1e88b-971d-4ab0-b75b-77bddf632de4	COUNTRY	\N	JM	Jamaica	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
162	e43d850f-2e96-4f4e-b1ce-490271c57ea0	COUNTRY	\N	JP	Japan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
163	cb99d110-a01a-4816-8b39-df1f5ab646cb	COUNTRY	\N	JO	Jordan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
164	53cabb0b-4189-40a4-a3b0-ee9cd2172468	COUNTRY	\N	KZ	Kazakhstan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
165	2c2df7dc-a18a-4489-b498-4a2d22d726a0	COUNTRY	\N	KE	Kenya	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
166	3d118d87-31b2-490b-9dad-9bad4bc34caa	COUNTRY	\N	KI	Kiribati	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
167	ad262ed9-e68f-4293-a837-e58c471d34b2	COUNTRY	\N	KP	Korea, Democratic People's Republic of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
168	74cd0240-642a-4b6b-b28d-2f7832b35b88	COUNTRY	\N	KR	Korea, Republic of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
169	c3f25b36-8886-4f64-8a74-22c384c3c41b	COUNTRY	\N	KW	Kuwait	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
170	d32c543f-9817-498e-af06-e4835a16d76a	COUNTRY	\N	KG	Kyrgyzstan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
171	d8f3e0b9-8584-4cdb-bc81-7ad2438d7dcc	COUNTRY	\N	LA	Lao People's Democratic Republic	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
172	7a7008d3-4b74-4350-ac5a-c7ed2b1e50b4	COUNTRY	\N	LV	Latvia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
173	910dd601-4ca3-402b-b06b-134604de97e2	COUNTRY	\N	LB	Lebanon	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
174	6f9b3f10-99c3-4db7-b36b-f773ec41881a	COUNTRY	\N	LS	Lesotho	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
175	d85a6094-cad7-4982-87ee-c561beae7d28	COUNTRY	\N	LR	Liberia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
176	93fc00f5-709e-4c40-ae72-294e31e427d4	COUNTRY	\N	LY	Libyan Arab Jamahiriya	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
177	0e6b23c7-dc42-41e8-876f-c80ee754f857	COUNTRY	\N	LI	Liechtenstein	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
178	86081cda-9788-40d8-9e42-a16c640fccd3	COUNTRY	\N	LT	Lithuania	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
179	8b90b4e2-a248-4780-a0f7-d2c3f2cbe9de	COUNTRY	\N	LU	Luxembourg	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
180	33ae528c-eac4-4b9b-9fb6-08dbfb8eff18	COUNTRY	\N	MO	Macao	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
181	c9902721-7c7f-427b-ba95-3ec4a7c4c517	COUNTRY	\N	MK	Macedonia, the Former Yugoslav Republic of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
182	7fc95e32-8341-4446-b9b1-e7c4e2f42f17	COUNTRY	\N	MG	Madagascar	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
183	853c4a4f-f881-40dd-be03-b045d210380c	COUNTRY	\N	MW	Malawi	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
184	abfb499a-da87-45c0-bb72-5e7a6f425068	COUNTRY	\N	MY	Malaysia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
185	f1cea587-6011-419e-a7a5-f74a9bc42733	COUNTRY	\N	MV	Maldives	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
186	9bb3852f-20ee-4596-a7db-3b942e4b5f16	COUNTRY	\N	ML	Mali	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
187	fcebde85-1747-450e-9d0c-c8cb0a079053	COUNTRY	\N	MT	Malta	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
188	93db6378-70c7-486e-b62d-09df054d7e6a	COUNTRY	\N	MH	Marshall Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
189	e2637264-3b03-4a18-912e-6a5a591865e7	COUNTRY	\N	MQ	Martinique	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
190	c5fd4bd2-eb2a-4d90-8c3b-097f9351cbc4	COUNTRY	\N	MR	Mauritania	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
191	7aab5289-248c-4ff0-9b1a-849e7d892b67	COUNTRY	\N	MU	Mauritius	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
192	f2591375-3217-4334-bf41-a188f134e016	COUNTRY	\N	YT	Mayotte	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
193	2cfb88ad-dab2-42db-b985-5b6ca293d180	COUNTRY	\N	MX	Mexico	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
194	3f83e6e2-d44e-4d0b-92de-2b82a19dd618	COUNTRY	\N	FM	Micronesia, Federated States of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
195	ad49ce58-1a9f-4dbe-97fb-a1087e1facc5	COUNTRY	\N	MD	Moldova, Republic of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
196	880a4cae-f850-4d3f-b834-36b7eb74e085	COUNTRY	\N	MC	Monaco	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
197	e3bcb23d-27da-4c6f-a702-bb04a76cc2a3	COUNTRY	\N	MN	Mongolia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
198	3205fd61-311e-44ff-8ecc-1bfb73bb0b0d	COUNTRY	\N	MS	Montserrat	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
199	699af80f-b835-404b-af67-a8a4644b2fde	COUNTRY	\N	MA	Morocco	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
200	49244a86-d69d-4cd7-8496-7ab5e963b24a	COUNTRY	\N	MZ	Mozambique	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
201	08847fad-92cc-47d3-b195-4b9e8eab54c7	COUNTRY	\N	MM	Myanmar	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
202	0086abda-7374-457a-a9b6-aae5d14877ba	COUNTRY	\N	NA	Namibia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
203	7feaaa04-2925-4ca8-95fa-ef30300034bd	COUNTRY	\N	NR	Nauru	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
204	4b82c4ce-c0e0-4c4b-92f7-cb3137211da6	COUNTRY	\N	NP	Nepal	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
205	463420b3-125e-4bf0-9928-b884ebbe58bd	COUNTRY	\N	NL	Netherlands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
206	04851df8-e252-487f-884c-3ebd4041662f	COUNTRY	\N	AN	Netherlands Antilles	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
207	232c36a1-6c01-4940-9ce7-65a4c9506d74	COUNTRY	\N	NC	New Caledonia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
208	b12eaf45-6b04-4136-a5cd-6d56c4632cdc	COUNTRY	\N	NZ	New Zealand	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
209	c675d82a-3126-4ef5-bd41-a7d29039545e	COUNTRY	\N	NI	Nicaragua	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
210	4cc9b45c-494d-4e16-a91b-1f3f1f25537e	COUNTRY	\N	NE	Niger	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
211	070b44b4-2386-48d1-9711-b32e2feddfa8	COUNTRY	\N	NG	Nigeria	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
212	b63dcd64-a9c7-4efa-97bb-866a3631895a	COUNTRY	\N	NU	Niue	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
213	1a088af6-abb2-49ed-8e9c-aac39e26bb1d	COUNTRY	\N	NF	Norfolk Island	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
214	dbac9791-79ce-4b53-82bd-2f1990be05de	COUNTRY	\N	MP	Northern Mariana Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
215	a941a8b0-4c09-4501-bcdf-3d85a7e9cd37	COUNTRY	\N	NO	Norway	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
216	a46b9119-52df-4537-a922-6a8806058646	COUNTRY	\N	OM	Oman	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
217	ab5d650d-2c1b-4672-8d0f-9054bd749e3e	DOCUMENT	\N	Photographs	Photographs	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
218	a2593c2c-9319-4b68-b5e5-6b68844ac627	DOCUMENT	\N	Signature Samples	Signature Samples	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
219	bddaca15-089d-4be6-a254-d82e121aa365	DOCUMENT	\N	Tax Identification Number	Tax Identification Number	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
220	98e9c529-e26e-4d3e-8f95-fff9093da743	DOCUMENT	\N	Proof of Identity	Proof of Identity	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
221	736bb209-ea17-4568-8f6a-506bc85c2985	DOCUMENT	\N	Regulatory Licenses and Permits	Regulatory Licenses and Permits	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
222	5c136e56-c325-4640-b7b3-30893fb87c35	DOCUMENT	\N	Proof of Address	Proof of Address	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
223	d94308a8-cedf-44d7-bc9a-e930f1e7706a	DOCUMENT	\N	Financial Documents	Financial Documents	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
224	df088f88-0cd7-413b-a233-31b89a37e5ec	DOCUMENT	\N	Ownership or Control Documents	Ownership or Control Documents	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
225	c91b35ca-4904-423b-9dfd-873a5e67159b	DOCUMENT	\N	Business Documents	Business Documents	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:04:17.544	2023-09-27 02:19:17.544+05:45	\N	\N	\N	\N	\N	\N
226	eb5a8dbf-6ba1-4a0b-b14b-41d1b6f7c49c	POI	\N	PAN Card	PAN Card	DOCUMENT	Proof of Identity	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:08:24.061	2023-09-27 02:23:24.061+05:45	\N	\N	\N	\N	\N	\N
227	5bddad5d-40bf-4ae7-b788-ac5879db3193	POI	\N	Voter ID Card	Voter ID Card	DOCUMENT	Proof of Identity	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:08:24.061	2023-09-27 02:23:24.061+05:45	\N	\N	\N	\N	\N	\N
228	9738c620-2a9b-4aa6-90b5-4a292f30a3c2	POI	\N	Driver's License	Driver's License	DOCUMENT	Proof of Identity	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:08:24.061	2023-09-27 02:23:24.061+05:45	\N	\N	\N	\N	\N	\N
229	bdfc0fe0-bef1-475a-bd97-a9a872d54de2	POI	\N	National Identity Card	National Identity Card	DOCUMENT	Proof of Identity	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:08:24.061	2023-09-27 02:23:24.061+05:45	\N	\N	\N	\N	\N	\N
230	a7afc0fa-f33b-4098-a7e6-f25c6714e969	POI	\N	Passport	Passport	DOCUMENT	Proof of Identity	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-27 08:08:24.061	2023-09-27 02:23:24.061+05:45	\N	\N	\N	\N	\N	\N
231	a822d15f-700b-4caa-83f7-b389dda9bfce	COUNTRY	\N	PK	Pakistan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
232	880d167c-5d68-4d44-993d-330fc3c74f5d	COUNTRY	\N	PW	Palau	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
233	8fa10a91-9c5f-4621-bc73-63ab72028dca	COUNTRY	\N	PS	Palestinian Territory, Occupied	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
234	c2198073-ba74-4793-b7b8-34ac237c575e	COUNTRY	\N	PA	Panama	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
235	4c93b94a-fe8f-45cc-a54d-67e4d25633de	COUNTRY	\N	PG	Papua New Guinea	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
236	abf6b2ad-83d1-46e5-a938-cf7fdbd02957	COUNTRY	\N	PY	Paraguay	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
237	5c4648f5-046f-4dcb-a83f-5f1590b01adb	COUNTRY	\N	PE	Peru	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
238	4ee3ac4f-148e-4ca2-9ce6-ec12f5df0773	COUNTRY	\N	PH	Philippines	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
239	72f51755-c83e-4f7d-95c0-d625334e52d7	COUNTRY	\N	PN	Pitcairn	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
240	c47b116d-8c79-4815-842c-1c88fd01e395	COUNTRY	\N	PL	Poland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
241	a9e0e748-620a-4ea5-b5bc-c8274c0a8709	COUNTRY	\N	PT	Portugal	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
242	7fd10dc8-f26e-4713-b237-9ad9233aaa55	COUNTRY	\N	PR	Puerto Rico	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
243	c4a2d717-d8c0-41d0-942c-1921da58ae29	COUNTRY	\N	QA	Qatar	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
244	6edd45ae-0aa8-498d-aa12-8f5da893246e	COUNTRY	\N	RE	Reunion	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
245	088b17d7-9fa1-441d-8725-6b7f3149e573	COUNTRY	\N	RO	Romania	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
246	3d950844-c764-4bd0-9daa-f3ac06386625	COUNTRY	\N	RU	Russian Federation	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
247	3783bc56-fb13-4272-9938-b4ba89ba169c	COUNTRY	\N	RW	Rwanda	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
248	76cbd0ff-e106-400f-ac4c-6e4afea99724	COUNTRY	\N	SH	Saint Helena	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
249	b5dcd706-e34a-4c57-b779-53da0f69e79d	COUNTRY	\N	KN	Saint Kitts and Nevis	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
250	5bd54038-10d3-4220-8bc2-e768b92eaba6	COUNTRY	\N	LC	Saint Lucia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
251	6bc6c6f3-b028-4c98-875e-b8d050385869	COUNTRY	\N	PM	Saint Pierre and Miquelon	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
252	ca4f8bf7-3447-4d73-8bd8-8eb143cb842c	COUNTRY	\N	VC	Saint Vincent and the Grenadines	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
253	06d73399-33aa-4b39-adad-59ec0ac83806	COUNTRY	\N	WS	Samoa	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
254	78c0e192-d34a-4c7c-baff-13d7e7bf62c4	COUNTRY	\N	SM	San Marino	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
255	08c78c66-37df-45f9-bfff-5aac403d6683	COUNTRY	\N	ST	Sao Tome and Principe	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
256	8f6dc1da-d776-44ef-8875-0eeef3271846	COUNTRY	\N	SA	Saudi Arabia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
257	797deebe-ff6c-4568-a276-7e06915ae4c8	COUNTRY	\N	SN	Senegal	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
258	e50f43f6-2387-42cd-afee-dd523d087f58	COUNTRY	\N	CS	Serbia and Montenegro	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
259	eed5ea7f-5b5d-41ea-8183-6af5d64bb7d0	COUNTRY	\N	SC	Seychelles	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
260	ce5d1619-2eb6-4069-90bf-d11b2d59ca4f	COUNTRY	\N	SL	Sierra Leone	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
261	f5102ca1-235a-42eb-96ba-396d5fd75ef2	COUNTRY	\N	SG	Singapore	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
262	3ad08695-e530-4914-97a3-c6969c98609b	COUNTRY	\N	SK	Slovakia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
263	4b49dc14-976c-46b6-a700-1a8d4b711bf7	COUNTRY	\N	SI	Slovenia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
264	4c53c43c-7f63-43fa-9ccc-b82e6009a39d	COUNTRY	\N	SB	Solomon Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
265	f8f3e061-fa5b-4aec-9acf-f9f089bde63f	COUNTRY	\N	SO	Somalia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
266	0941d0a5-23ed-4789-a8d9-f592c42ed5d8	COUNTRY	\N	ZA	South Africa	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
267	96d45608-3039-4fff-a2bf-fbfe32a2b13f	COUNTRY	\N	GS	South Georgia and the South Sandwich Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
268	bce4dbc6-4a8b-4f3e-835d-aecf9d38fa5d	COUNTRY	\N	ES	Spain	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
269	f32f2fd6-2f1e-427f-a6c6-54d5e94d72cc	COUNTRY	\N	LK	Sri Lanka	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
270	e3d1fed3-6ec7-4a60-85e8-8d1ce036c31e	COUNTRY	\N	SD	Sudan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
271	95bcede3-feaa-473f-adaf-0bfef3b1e1b1	COUNTRY	\N	SR	Suriname	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
272	c5072656-fa34-4565-8bc1-9ded7d0bf5c9	COUNTRY	\N	SJ	Svalbard and Jan Mayen	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
273	058bb960-7607-46b7-8d35-5ffba1553b9b	COUNTRY	\N	SZ	Swaziland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
274	7c10ab3a-86c1-4f91-961c-7bb9f4ce39bd	COUNTRY	\N	SE	Sweden	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
275	9b029903-8fd0-4b91-916f-0cb20a4e9062	COUNTRY	\N	CH	Switzerland	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
276	0542d151-a00f-4ce6-8b26-4206c8708557	COUNTRY	\N	SY	Syrian Arab Republic	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
277	3a927afa-f5c6-44a6-98f9-011619bea842	COUNTRY	\N	TW	Taiwan, Province of China	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
278	1d2b5371-565f-4731-8f8d-068f0ff69111	COUNTRY	\N	TJ	Tajikistan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
279	52e504a2-efb6-492d-a1f2-e66c1b58d1ec	COUNTRY	\N	TZ	Tanzania, United Republic of	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
280	19d5599f-ae62-4c93-99e5-e4d65f85cc1f	COUNTRY	\N	TH	Thailand	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
281	37cf6b0c-b81a-4bba-93d9-82f61b57df0c	COUNTRY	\N	TL	Timor-Leste	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
282	c0b7a0a4-819f-4577-8701-233a62b3ecba	COUNTRY	\N	TG	Togo	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
283	eaca27f0-5b38-4d84-8388-53e1892481a7	COUNTRY	\N	TK	Tokelau	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
284	ae2382e3-6cbe-43e1-8920-213cb45c2c25	COUNTRY	\N	TO	Tonga	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
285	4bff484e-b882-446d-821a-5836c161454f	COUNTRY	\N	TT	Trinidad and Tobago	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
286	1b0c2edb-d570-4fe6-876d-55cd5ba5a8eb	COUNTRY	\N	TN	Tunisia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
287	a1d0ba56-5221-4f73-9f1e-4e6ca37e8b9f	COUNTRY	\N	TR	Turkey	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
288	7a74d934-9037-4f09-b93f-262d8fc019a6	COUNTRY	\N	TM	Turkmenistan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
289	bf799a55-bbb8-4934-be2e-325de55a1cfa	COUNTRY	\N	TC	Turks and Caicos Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
290	307a60ac-dd8a-4d15-b7cf-0769de548d02	COUNTRY	\N	TV	Tuvalu	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
291	915162b4-c330-4f67-8ca6-8a122896064d	COUNTRY	\N	UG	Uganda	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
292	2fa853f0-7309-436b-b444-ec9c96a0c029	COUNTRY	\N	UA	Ukraine	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
293	f9c0bc54-706f-48c2-801b-aeee8f84fd80	COUNTRY	\N	AE	United Arab Emirates	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
294	4ec5f9b5-47a2-4d83-9761-fb2fefe04c6d	COUNTRY	\N	GB	United Kingdom	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
295	b3637d88-a56c-4b19-805c-c4260bf81666	COUNTRY	\N	US	United States	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
296	0a202327-e5a8-4a23-9b58-95c32871556b	COUNTRY	\N	UM	United States Minor Outlying Islands	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
297	1f3a00fd-910d-4a0d-817e-19ac69b4da2f	COUNTRY	\N	UY	Uruguay	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
298	b69021a2-25c3-4ac6-a1f0-7d00d1ca82d5	COUNTRY	\N	UZ	Uzbekistan	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
299	561ec9d4-7288-4a20-96bc-c47179357ecc	COUNTRY	\N	VU	Vanuatu	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
300	84b45040-a045-4471-b443-595e26049569	COUNTRY	\N	VE	Venezuela	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
301	b6ab994e-ec8e-462e-8800-4976bce8cee4	COUNTRY	\N	VN	Viet Nam	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
302	a6915d5b-7d27-4fec-a8c4-edd31d7a0bfd	COUNTRY	\N	VG	Virgin Islands, British	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
303	0bc5c2d4-cfb8-4da9-b37a-b33c07454579	COUNTRY	\N	VI	Virgin Islands, U.s.	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
304	83c564dc-9b25-4bf4-9ad2-45c859bfebe5	COUNTRY	\N	WF	Wallis and Futuna	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
305	f7f0bcc8-85e5-472c-a690-0148a7c7816d	COUNTRY	\N	EH	Western Sahara	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
306	ad492e45-9b37-48c8-b761-ee2ff0b8578e	COUNTRY	\N	YE	Yemen	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
307	93a76dff-1d07-4090-91ad-f29e2b5df868	COUNTRY	\N	ZM	Zambia	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
308	0722516e-84ff-4c63-a07e-9b4b015604c6	COUNTRY	\N	ZW	Zimbabwe	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:40:49.954	2023-09-29 07:40:49.954+05:45	\N	\N	\N	\N	\N	\N
309	f13b5499-6237-404e-b2bb-e4f0ecb25233	PHONECODE	\N	93	93	COUNTRY	AF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
310	a7e3c8d9-0d81-41a6-bf9a-84c506c52df8	PHONECODE	\N	355	355	COUNTRY	AL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
311	deb8eeda-da84-4c06-935a-32365e80c177	PHONECODE	\N	213	213	COUNTRY	DZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
312	1218e446-8d95-410e-927b-55fb965bb02c	PHONECODE	\N	1684	1684	COUNTRY	AS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
313	80fe5667-463d-4b28-99d7-3a283411aecd	PHONECODE	\N	376	376	COUNTRY	AD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
314	bf1fab76-d63a-4b3b-b75b-1e820a60c260	PHONECODE	\N	244	244	COUNTRY	AO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
315	65d679e6-0620-447e-957e-b032673253e1	PHONECODE	\N	1264	1264	COUNTRY	AI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
316	7b93c0aa-b1b0-4f9d-936b-71f8a205a19d	PHONECODE	\N	0	0	COUNTRY	AQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
317	6291c1f0-f975-466c-98b1-79b6ff038597	PHONECODE	\N	1268	1268	COUNTRY	AG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
318	6bb0ce25-e513-4efd-b6c4-72e58c2dac40	PHONECODE	\N	54	54	COUNTRY	AR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
319	ce3b70ba-67fe-4f96-80bc-3fa50236e107	PHONECODE	\N	374	374	COUNTRY	AM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
320	fbb48b61-2b88-45ab-a189-8b7706074bef	PHONECODE	\N	297	297	COUNTRY	AW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
321	9ef54093-0624-4a95-b8f7-2897a75c6d0a	PHONECODE	\N	61	61	COUNTRY	AU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
322	b7584d39-1afb-4d40-9b63-5e7c6780cd69	PHONECODE	\N	43	43	COUNTRY	AT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
323	a8ceb07a-88da-476a-a3ce-831697e7cfb2	PHONECODE	\N	994	994	COUNTRY	AZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
324	ae899745-4aa5-4451-a852-bf7d8cf10db9	PHONECODE	\N	1242	1242	COUNTRY	BS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
325	65048ab8-1928-4a6e-98b5-acb511891376	PHONECODE	\N	973	973	COUNTRY	BH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
326	eaa4acc1-be69-4ed1-aa9e-e42bd98be521	PHONECODE	\N	880	880	COUNTRY	BD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
327	528b24ea-6cb7-4090-afac-d636318ab646	PHONECODE	\N	1246	1246	COUNTRY	BB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
328	96d366f7-fa26-467e-bc2e-6e09f3591840	PHONECODE	\N	375	375	COUNTRY	BY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
329	22d4d7b9-c30e-4eeb-81e6-72071ac82373	PHONECODE	\N	32	32	COUNTRY	BE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
330	5f3fe790-b7d0-4dc8-b126-98724555d97d	PHONECODE	\N	501	501	COUNTRY	BZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
331	a7d1b805-bec7-47f3-9c52-ef6b8258d323	PHONECODE	\N	229	229	COUNTRY	BJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
332	315de6c6-f64c-49f9-b5dd-cb2b61a13c3d	PHONECODE	\N	1441	1441	COUNTRY	BM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
333	de2de83f-0174-4457-8bad-b653d62ad538	PHONECODE	\N	975	975	COUNTRY	BT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
334	70f7276d-ab97-4dea-91bf-8c0ae53eb193	PHONECODE	\N	591	591	COUNTRY	BO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
335	4ce01394-a102-414b-9ed4-a70d3de69e75	PHONECODE	\N	387	387	COUNTRY	BA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
336	c0c69dc0-4661-4471-8b9a-5bb791fcad33	PHONECODE	\N	267	267	COUNTRY	BW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
337	b026f63a-a236-448b-b802-06873713eea5	PHONECODE	\N	0	0	COUNTRY	BV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
338	57ed27e1-7601-415c-8c95-214d9a5743a9	PHONECODE	\N	55	55	COUNTRY	BR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
339	50dc3c3d-a4b1-4787-b2cd-f9bb554a9047	PHONECODE	\N	246	246	COUNTRY	IO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
340	5f6024cf-5fa7-4662-b509-a219a3919561	PHONECODE	\N	673	673	COUNTRY	BN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
341	8b909d7c-0215-4e50-a4a8-2bce5d12cbc4	PHONECODE	\N	359	359	COUNTRY	BG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
342	567518ff-0692-42b5-ac93-5bcaf0e4a08f	PHONECODE	\N	226	226	COUNTRY	BF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
343	d437c438-7264-4666-b06b-0605aeed9a52	PHONECODE	\N	257	257	COUNTRY	BI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
344	9970c918-ab00-4626-abf7-826591a45e76	PHONECODE	\N	855	855	COUNTRY	KH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
345	5f0ff384-1a59-405c-8693-eeaff9444787	PHONECODE	\N	237	237	COUNTRY	CM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
346	387855f9-960e-4f60-83ea-ea76d67610de	PHONECODE	\N	1	1	COUNTRY	CA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
347	c7004586-268f-4cf9-b7a3-a31f15fa8548	PHONECODE	\N	238	238	COUNTRY	CV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
348	e3d28591-fb4c-413e-99b4-7d63c2b63792	PHONECODE	\N	1345	1345	COUNTRY	KY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
349	1e60083a-2041-4515-a98d-a7a5a215268b	PHONECODE	\N	236	236	COUNTRY	CF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
350	1aa12015-cdb8-4ea3-971e-2678248fa382	PHONECODE	\N	235	235	COUNTRY	TD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
351	f704c4ed-4eab-400d-b442-514309a5a14b	PHONECODE	\N	56	56	COUNTRY	CL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
352	56da76e8-973f-4d76-b9c7-d1740ddccf48	PHONECODE	\N	86	86	COUNTRY	CN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
353	0fcb0a0e-119f-42a8-b16d-b63317672582	PHONECODE	\N	61	61	COUNTRY	CX	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
354	c81c441e-a3bf-4a3d-8d95-adf64e569e98	PHONECODE	\N	672	672	COUNTRY	CC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
355	fe8e156c-c040-4de8-a16c-c455da6c04d1	PHONECODE	\N	57	57	COUNTRY	CO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
356	5712e484-d3a3-4641-9dfc-2f081598fddd	PHONECODE	\N	269	269	COUNTRY	KM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
357	9f138573-a824-42bf-805c-950ce98e9c9c	PHONECODE	\N	242	242	COUNTRY	CG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
358	3a897540-e810-48d2-9d1e-fd9421c9e5ac	PHONECODE	\N	242	242	COUNTRY	CD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
359	7a96c475-5413-4d6c-a276-c74d5ddf36b7	PHONECODE	\N	682	682	COUNTRY	CK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
360	d2e301d3-8597-4806-8c6f-e7e72bad2c3b	PHONECODE	\N	506	506	COUNTRY	CR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
361	a4546c16-4f5d-42f3-859d-84fa3f26a860	PHONECODE	\N	225	225	COUNTRY	CI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
362	186a2f8d-d397-459d-9b78-7e36ff157aa3	PHONECODE	\N	385	385	COUNTRY	HR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
363	c31642ed-7175-4517-a6f5-bccc8cbf7142	PHONECODE	\N	53	53	COUNTRY	CU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
364	3d27511c-226e-4374-8a6f-3dc052f2723c	PHONECODE	\N	357	357	COUNTRY	CY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
365	a98ca4b2-e871-4dea-bc70-d1fd04ef3e89	PHONECODE	\N	420	420	COUNTRY	CZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
366	0c98d5bb-9cbb-41c1-80c5-1efaa9912128	PHONECODE	\N	45	45	COUNTRY	DK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
367	6c80a67f-77a2-41af-9a1c-40e0634bb343	PHONECODE	\N	253	253	COUNTRY	DJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
368	68d61ec7-1e6c-4093-82da-251c1e249a4d	PHONECODE	\N	1767	1767	COUNTRY	DM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
369	11105769-b8a0-4a3a-84d6-197011a84119	PHONECODE	\N	1809	1809	COUNTRY	DO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
370	9e76c93e-21d6-4389-a1a6-1fadcd4e951f	PHONECODE	\N	593	593	COUNTRY	EC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
371	cabeb1e3-610b-4404-b8f9-c25f794d7774	PHONECODE	\N	20	20	COUNTRY	EG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
372	fada434b-791e-406e-8ecc-88f98b3bcc78	PHONECODE	\N	503	503	COUNTRY	SV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
373	3f8a1278-a433-4d2f-b54c-1f3f68553001	PHONECODE	\N	240	240	COUNTRY	GQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
374	12245db6-ccae-4009-82c3-1347cdc82f3d	PHONECODE	\N	291	291	COUNTRY	ER	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
375	813136c8-99b7-41f6-a115-492fa110277c	PHONECODE	\N	372	372	COUNTRY	EE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
376	415fc707-dd6c-4a23-8ffc-d3a6e04abedc	PHONECODE	\N	251	251	COUNTRY	ET	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
377	f404b853-2bbd-4a0f-90b6-dc8b6d9b9e32	PHONECODE	\N	500	500	COUNTRY	FK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
378	80d98524-889b-4457-910a-dc2a2f3b75b1	PHONECODE	\N	298	298	COUNTRY	FO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
379	327a3ef8-68d0-4072-9e30-58f6227ee717	PHONECODE	\N	679	679	COUNTRY	FJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
380	9072ee85-50d1-47d6-ad64-9897dfdc1566	PHONECODE	\N	358	358	COUNTRY	FI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
381	244cd211-d2ae-4c51-b0a1-e436f8506d6a	PHONECODE	\N	33	33	COUNTRY	FR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
382	aecc8d3f-b6d0-40e7-a2b7-122526760d6f	PHONECODE	\N	594	594	COUNTRY	GF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
383	1de4e309-f562-437b-880e-96eb664d88b9	PHONECODE	\N	689	689	COUNTRY	PF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
384	7a8ffeef-1178-4d75-b354-a11faf79db9f	PHONECODE	\N	0	0	COUNTRY	TF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
385	8590067a-1557-4bd0-baca-81dce4b20b9c	PHONECODE	\N	241	241	COUNTRY	GA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
386	2fcc913d-59b6-4dc7-898e-dd718d031adc	PHONECODE	\N	220	220	COUNTRY	GM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
387	d575e721-f347-4c3d-a5fa-f2779627a18a	PHONECODE	\N	995	995	COUNTRY	GE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
388	f250abbc-1741-4596-a900-cff3f3d529cc	PHONECODE	\N	49	49	COUNTRY	DE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
389	c1bcbcc9-3925-4e3e-b2d8-49571434d8eb	PHONECODE	\N	233	233	COUNTRY	GH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
390	3f84ec69-a125-42ea-9514-a18db162aea5	PHONECODE	\N	350	350	COUNTRY	GI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
391	4317c71a-4e83-440e-83a6-698e6122cd1f	PHONECODE	\N	30	30	COUNTRY	GR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
392	e393cc96-2ac8-4e23-a02c-02e6f7718b94	PHONECODE	\N	299	299	COUNTRY	GL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
393	5d920a5b-1cda-40b8-a99e-f09500d8b719	PHONECODE	\N	1473	1473	COUNTRY	GD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
394	41e4f7f9-3abf-48f7-8c89-47f3c5ac7d02	PHONECODE	\N	590	590	COUNTRY	GP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
395	a029f652-c8c5-4522-8327-ba2914461799	PHONECODE	\N	1671	1671	COUNTRY	GU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
396	931fd79e-609a-4f1e-84ed-0077d24b8683	PHONECODE	\N	502	502	COUNTRY	GT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
397	c584936f-5119-4025-8d7c-d35e1c17c0ff	PHONECODE	\N	224	224	COUNTRY	GN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
398	57a27c15-a0d0-4944-82a9-7d695b275ef9	PHONECODE	\N	245	245	COUNTRY	GW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
399	351e55e5-ade1-46c3-922a-8ac64d4d6563	PHONECODE	\N	592	592	COUNTRY	GY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
400	74610ef2-7eb9-4a8f-8db3-34456ed8c41f	PHONECODE	\N	509	509	COUNTRY	HT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
401	b9ee621e-958e-42ff-bebd-6f670903320e	PHONECODE	\N	0	0	COUNTRY	HM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
402	20eb54f4-ae8c-45fc-80b5-7d1ea8169267	PHONECODE	\N	39	39	COUNTRY	VA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
403	5d3e51ea-c490-4c51-a915-a62af21c2c57	PHONECODE	\N	504	504	COUNTRY	HN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
404	4e073106-a434-4ecd-9e33-569406fb25a8	PHONECODE	\N	852	852	COUNTRY	HK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
405	29476842-a3c4-4b5f-bc60-ad8ac9c8c6a0	PHONECODE	\N	36	36	COUNTRY	HU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
406	def7aaf4-782d-4234-9933-12292588a735	PHONECODE	\N	354	354	COUNTRY	IS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
407	0bf66a86-6f71-4739-beeb-378313221a55	PHONECODE	\N	91	91	COUNTRY	IN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
408	3556e2be-c0af-443d-8ab3-dac76b489fb4	PHONECODE	\N	62	62	COUNTRY	ID	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
409	73b1b287-9c71-4371-909a-2c4a25f541b6	PHONECODE	\N	98	98	COUNTRY	IR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
410	ce7b89e3-e0a5-4b2c-8e13-c94fdb689b89	PHONECODE	\N	964	964	COUNTRY	IQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
411	b6f567e4-6ea7-479c-af89-2faa4c63e147	PHONECODE	\N	353	353	COUNTRY	IE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
412	d6fbc47d-72cb-4c28-9e1a-265fdb24f3dc	PHONECODE	\N	972	972	COUNTRY	IL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
413	379ddf59-88a1-49f6-a0f9-a489fbd3cd9d	PHONECODE	\N	39	39	COUNTRY	IT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
414	c756b890-d19d-4c0f-8651-4427d09bbbc1	PHONECODE	\N	1876	1876	COUNTRY	JM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
415	c43ce52d-4c59-40f5-a8b0-b5788656f162	PHONECODE	\N	81	81	COUNTRY	JP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
416	cdaf1a29-c162-4b58-a262-b5d5628ba11d	PHONECODE	\N	962	962	COUNTRY	JO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
417	20f0aa20-4be9-4dc6-821d-884f30911afb	PHONECODE	\N	7	7	COUNTRY	KZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
418	344d120f-11a9-4008-a74f-43291df4ae53	PHONECODE	\N	254	254	COUNTRY	KE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
419	8f02d5c8-f250-43fe-a1ee-8ebc6894911c	PHONECODE	\N	686	686	COUNTRY	KI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
420	6710887e-fc12-4c0c-ad20-ea22812a80ab	PHONECODE	\N	850	850	COUNTRY	KP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
421	0985c012-f73b-4c5f-af41-41434fb701eb	PHONECODE	\N	82	82	COUNTRY	KR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
422	5149de7b-c380-47e5-8671-ed3e16f9a6ec	PHONECODE	\N	965	965	COUNTRY	KW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
423	94aba85b-9cf2-4461-91ae-1c8f43fddc55	PHONECODE	\N	996	996	COUNTRY	KG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
424	85f46716-0cdb-4029-9122-cac5ffbb6f3a	PHONECODE	\N	856	856	COUNTRY	LA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
425	bf9b174c-cbda-4c7e-b9c5-3cc2eaedc2e2	PHONECODE	\N	371	371	COUNTRY	LV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
426	bf748498-f705-4dc6-a030-5640b295c87a	PHONECODE	\N	961	961	COUNTRY	LB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
427	69e44c05-0747-4421-a7b8-2809285620ce	PHONECODE	\N	266	266	COUNTRY	LS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
428	3cb13752-37b9-4000-9bf0-5b6231db8534	PHONECODE	\N	231	231	COUNTRY	LR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
429	b25f564a-4360-4e18-82d9-7f056b83cdaa	PHONECODE	\N	218	218	COUNTRY	LY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
430	42f5c676-1561-4d19-84d5-673969164030	PHONECODE	\N	423	423	COUNTRY	LI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
431	4e8a3389-6394-4c49-84a5-5ffca9679db0	PHONECODE	\N	370	370	COUNTRY	LT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
432	7b01f345-a433-4ed4-aa47-964a2484cb93	PHONECODE	\N	352	352	COUNTRY	LU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
433	c316c3cb-c95d-421e-b86e-267f05f119ab	PHONECODE	\N	853	853	COUNTRY	MO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
434	c1bf3a06-30b8-45d6-8d59-94b2da090225	PHONECODE	\N	389	389	COUNTRY	MK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
435	bed26447-d98a-45b6-939c-84cd30fd0eb8	PHONECODE	\N	261	261	COUNTRY	MG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
436	469c96f2-d257-4b43-9957-3bafe4bd0c63	PHONECODE	\N	265	265	COUNTRY	MW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
437	a5dd8194-11a8-4dff-9582-f398d97d5fa5	PHONECODE	\N	60	60	COUNTRY	MY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
438	66cf5c3a-e60d-4870-873e-57bf76c5adde	PHONECODE	\N	960	960	COUNTRY	MV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
439	4319c483-b60d-4b06-aeb2-d8e5bfd8238b	PHONECODE	\N	223	223	COUNTRY	ML	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
440	559db834-293a-41d6-a8e3-35b455d554af	PHONECODE	\N	356	356	COUNTRY	MT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
441	0b9e6241-d05f-4e64-b5df-c21714f23181	PHONECODE	\N	692	692	COUNTRY	MH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
442	d32d286a-971a-486e-9d74-e91924af6291	PHONECODE	\N	596	596	COUNTRY	MQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
443	67986076-f7ce-4538-b743-c073db15da13	PHONECODE	\N	222	222	COUNTRY	MR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
444	d889f3c3-8910-4a34-8448-ea177c55e2ee	PHONECODE	\N	230	230	COUNTRY	MU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
445	5de7c9a7-fb7b-4f74-9bbe-38767d72af98	PHONECODE	\N	269	269	COUNTRY	YT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
446	bb0c52cb-f1bc-436d-831d-00dfff7e7222	PHONECODE	\N	52	52	COUNTRY	MX	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
447	f60f766f-8ddb-4e44-a741-ce26e95c49d3	PHONECODE	\N	691	691	COUNTRY	FM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
448	9305bc94-94ae-4b2c-aa5f-3266f260d52d	PHONECODE	\N	373	373	COUNTRY	MD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
449	c3c02b1a-958e-497c-a24a-44425f9e752b	PHONECODE	\N	377	377	COUNTRY	MC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
450	c07b3c3c-1ca0-4da9-8d47-3894ea4ffc96	PHONECODE	\N	976	976	COUNTRY	MN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
451	0f1ebca3-0009-4d0b-b417-e65fca3e5f0a	PHONECODE	\N	1664	1664	COUNTRY	MS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
452	132dced9-878a-47d5-aea5-33f2c11cd129	PHONECODE	\N	212	212	COUNTRY	MA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
453	77700a14-2619-4338-ab2a-0be95b380274	PHONECODE	\N	258	258	COUNTRY	MZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
454	4006dfc5-6d5f-440e-83e5-d28aebab1293	PHONECODE	\N	95	95	COUNTRY	MM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
455	7b39b369-f372-489e-b085-69b4e92c3e41	PHONECODE	\N	264	264	COUNTRY	NA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
456	e34c75d4-a630-49cd-8167-8b701d4cba7f	PHONECODE	\N	674	674	COUNTRY	NR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
457	979cdeff-419b-4c1b-9175-df48d9882c72	PHONECODE	\N	977	977	COUNTRY	NP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
458	086bd02e-2c03-425a-b607-582e9b6f6012	PHONECODE	\N	31	31	COUNTRY	NL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
459	d9b4d37b-84f9-4741-819c-a3942b9d7fbf	PHONECODE	\N	599	599	COUNTRY	AN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
460	b03d9fd8-5e4f-40de-b449-e387e2f5a502	PHONECODE	\N	687	687	COUNTRY	NC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
461	5421237c-5d78-4f88-ae58-9ab5df19d42f	PHONECODE	\N	64	64	COUNTRY	NZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
462	ab779857-216d-4fc8-9a8a-f0809b592da3	PHONECODE	\N	505	505	COUNTRY	NI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
463	b12faecc-ca54-4a45-8cd2-113defd377da	PHONECODE	\N	227	227	COUNTRY	NE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
464	0eeaaa7c-7d2c-4403-ad63-99ba821be059	PHONECODE	\N	234	234	COUNTRY	NG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
465	db1416f0-2069-4f39-922c-528d11f3934a	PHONECODE	\N	683	683	COUNTRY	NU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
466	42fda4be-7bd7-4acf-8f08-5a4022657478	PHONECODE	\N	672	672	COUNTRY	NF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
467	b021749d-d924-4a26-9563-70ad2090d365	PHONECODE	\N	1670	1670	COUNTRY	MP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
468	c73dcbe7-4795-45c2-b10b-e30f0c8d55ad	PHONECODE	\N	47	47	COUNTRY	NO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
469	d16abcf1-db28-4894-81ad-fd591d563478	PHONECODE	\N	968	968	COUNTRY	OM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
470	eeb79e52-fece-493c-b12f-922eaf60e5a9	PHONECODE	\N	92	92	COUNTRY	PK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
471	6ae4ef53-f3b0-4256-9a23-7d8fa69beaeb	PHONECODE	\N	680	680	COUNTRY	PW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
472	71337fc2-e471-4e20-b96a-5b191a0f82ef	PHONECODE	\N	970	970	COUNTRY	PS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
473	5caf1782-1f87-4a3d-b3a7-85503b827684	PHONECODE	\N	507	507	COUNTRY	PA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
474	c0925601-4880-4adb-b435-c3567599e4f0	PHONECODE	\N	675	675	COUNTRY	PG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
475	cf62f42f-7569-4411-94e4-854ebb66193d	PHONECODE	\N	595	595	COUNTRY	PY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
476	c08944e2-c8d6-40f8-acd0-9a4119e3fc16	PHONECODE	\N	51	51	COUNTRY	PE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
477	03deba1f-a8a6-43f0-90e0-1f971904b145	PHONECODE	\N	63	63	COUNTRY	PH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
478	1b599774-2192-47b6-9a70-dff87bbd4659	PHONECODE	\N	0	0	COUNTRY	PN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
479	8b666b5d-069b-4907-8f30-0b0e761d0db8	PHONECODE	\N	48	48	COUNTRY	PL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
480	931953b5-f0e0-4637-ba7c-e883436d2f2d	PHONECODE	\N	351	351	COUNTRY	PT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
481	63526e87-f524-420e-8f4a-866d6ac562c8	PHONECODE	\N	1787	1787	COUNTRY	PR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
482	20293d46-cfda-4ddb-bbb3-e96462b879eb	PHONECODE	\N	974	974	COUNTRY	QA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
483	a6f7f8f6-202c-4072-bb1d-2b4df899a604	PHONECODE	\N	262	262	COUNTRY	RE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
484	70e1e140-f260-4901-a99b-ce4054decc03	PHONECODE	\N	40	40	COUNTRY	RO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
485	d3135822-baaf-416d-aaaa-799ac5475f07	PHONECODE	\N	70	70	COUNTRY	RU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
486	f3c05bbf-c297-465d-ad97-d42f8005a6e2	PHONECODE	\N	250	250	COUNTRY	RW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
487	2a441622-a292-4173-867a-0a1060d347bc	PHONECODE	\N	290	290	COUNTRY	SH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
488	c98f5ead-86e8-4c42-b22c-58f80d739b83	PHONECODE	\N	1869	1869	COUNTRY	KN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
489	c2222e6d-f517-4de8-af89-883805b54689	PHONECODE	\N	1758	1758	COUNTRY	LC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
490	99f37173-a275-4191-b97d-9c3ba8388027	PHONECODE	\N	508	508	COUNTRY	PM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
491	bee1d3d5-785d-435c-9c6d-5d6b2286d069	PHONECODE	\N	1784	1784	COUNTRY	VC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
492	56d430fc-69df-43a3-88fa-b533a92b4a18	PHONECODE	\N	684	684	COUNTRY	WS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
493	559fe8b5-f772-4264-9b5a-dbeb61f07ec0	PHONECODE	\N	378	378	COUNTRY	SM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
494	2b1fd65f-2873-484e-9dd8-56ea5719957a	PHONECODE	\N	239	239	COUNTRY	ST	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
495	b72ea9d7-5ac3-4237-93fd-3e508409a940	PHONECODE	\N	966	966	COUNTRY	SA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
496	415c2493-ac57-44b7-ad1e-a2841ba75703	PHONECODE	\N	221	221	COUNTRY	SN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
497	4a282d6c-3f73-471a-a9a2-4d210ac40d7e	PHONECODE	\N	381	381	COUNTRY	CS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
498	f434a454-dede-4f06-9aff-934b9d57c952	PHONECODE	\N	248	248	COUNTRY	SC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
499	638895ce-d6ed-4ac2-82e8-c486e99d308c	PHONECODE	\N	232	232	COUNTRY	SL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
500	42702aff-f5a9-408a-b62a-7e8c939736c9	PHONECODE	\N	65	65	COUNTRY	SG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
501	cd05f69f-1831-4300-9538-9d38ad997441	PHONECODE	\N	421	421	COUNTRY	SK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
502	f99e716a-7bf7-473b-9fca-8c6de5a9c6a4	PHONECODE	\N	386	386	COUNTRY	SI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
503	5254bc71-8c99-438e-af1a-67cfca249958	PHONECODE	\N	677	677	COUNTRY	SB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
504	a2a480ef-b6db-4e79-8de0-7d0a46377b2a	PHONECODE	\N	252	252	COUNTRY	SO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
505	27f6a620-4b87-4578-89a7-92506be33cf1	PHONECODE	\N	27	27	COUNTRY	ZA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
506	2eaf937c-3896-4a01-bb1f-4a23a549d9f5	PHONECODE	\N	0	0	COUNTRY	GS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
507	581450c6-7c75-4afd-914e-504defca0f76	PHONECODE	\N	34	34	COUNTRY	ES	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
508	8525a306-eb37-45ff-b088-3dedabdb8aad	PHONECODE	\N	94	94	COUNTRY	LK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
509	e8ab1c1b-8b4b-4605-a75e-eb47ee6063db	PHONECODE	\N	249	249	COUNTRY	SD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
510	2f42bf60-6d29-445a-af31-7284663235e4	PHONECODE	\N	597	597	COUNTRY	SR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
511	034794f6-e1a2-435b-b237-ac9e404f843c	PHONECODE	\N	47	47	COUNTRY	SJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
512	da7bebc4-21a6-40bc-bb2b-e49354601365	PHONECODE	\N	268	268	COUNTRY	SZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
513	c6eeb047-d64f-4685-909a-bf98ac45d407	PHONECODE	\N	46	46	COUNTRY	SE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
514	efd75e5f-c408-4372-9fa7-8193f770cf23	PHONECODE	\N	41	41	COUNTRY	CH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
515	a85e47af-3544-45f4-b454-96cb6a86a364	PHONECODE	\N	963	963	COUNTRY	SY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
516	7d7c7cce-c7f8-4674-8d8b-75b260593e05	PHONECODE	\N	886	886	COUNTRY	TW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
517	87c3ac2a-da72-4245-b838-b4a43a43a868	PHONECODE	\N	992	992	COUNTRY	TJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
518	712131d8-fc29-46e7-bc12-e501c700f00f	PHONECODE	\N	255	255	COUNTRY	TZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
519	978028f3-36ea-4cba-b8db-1aa09243d3a7	PHONECODE	\N	66	66	COUNTRY	TH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
520	cd1bf339-8fea-4c82-9d56-ea05f7314b0f	PHONECODE	\N	670	670	COUNTRY	TL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
521	a85e14b4-f090-406c-95c7-1c90b6c4e0cc	PHONECODE	\N	228	228	COUNTRY	TG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
522	075aa229-5aa8-4d56-991c-bb79af6642ec	PHONECODE	\N	690	690	COUNTRY	TK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
523	d51c5adf-4a1c-48e6-98e2-a1a80507f6cf	PHONECODE	\N	676	676	COUNTRY	TO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
524	2ba0608d-40be-42b7-86b3-393ea05173b7	PHONECODE	\N	1868	1868	COUNTRY	TT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
525	2e13736d-b41e-41c9-88ae-26708f476f42	PHONECODE	\N	216	216	COUNTRY	TN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
526	1483cf30-eb7b-4b4d-b076-1bff2e23d03e	PHONECODE	\N	90	90	COUNTRY	TR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
527	2ebad3d5-8c1c-41a2-aff1-b1fa61b647d0	PHONECODE	\N	7370	7370	COUNTRY	TM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
528	b0e234f3-08e5-42d6-9a20-acc7539aba64	PHONECODE	\N	1649	1649	COUNTRY	TC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
529	bd76ead1-2eff-488b-abbb-7b70e8d8903e	PHONECODE	\N	688	688	COUNTRY	TV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
530	0423963c-84a6-4c56-bafd-035723d00542	PHONECODE	\N	256	256	COUNTRY	UG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
531	4b9516f4-ddb3-414d-aa6f-83a506fc4ee5	PHONECODE	\N	380	380	COUNTRY	UA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
532	2c8b89e7-db4d-4c74-bca1-0d2f0ae62730	PHONECODE	\N	971	971	COUNTRY	AE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
533	57d27b4f-1ba8-42ff-9706-61e3f33b74c4	PHONECODE	\N	44	44	COUNTRY	GB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
534	301f8c22-3dab-4661-912c-45e9187fbd27	PHONECODE	\N	1	1	COUNTRY	US	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
535	4a00e38c-e126-4acd-988b-09d06b003757	PHONECODE	\N	1	1	COUNTRY	UM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
536	9d87d17e-561e-4e60-9c6c-e2119e92853c	PHONECODE	\N	598	598	COUNTRY	UY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
537	bbdd0f2d-1cf2-460a-8e26-df5d54b195a8	PHONECODE	\N	998	998	COUNTRY	UZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
538	9659c230-6768-4c1d-909b-09057a2f2802	PHONECODE	\N	678	678	COUNTRY	VU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
539	c9264655-cd01-4142-a4da-069f724eb653	PHONECODE	\N	58	58	COUNTRY	VE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
540	94d6d910-2588-46c0-b430-8cb32ed4a51e	PHONECODE	\N	84	84	COUNTRY	VN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
541	ba8acdee-5c56-43e5-b68a-c7cbe8ad2e71	PHONECODE	\N	1284	1284	COUNTRY	VG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
542	5d0ec330-d192-4ac9-a220-2d034b42e425	PHONECODE	\N	1340	1340	COUNTRY	VI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
543	0fb227c7-18c6-4a46-b30b-2dfce2190513	PHONECODE	\N	681	681	COUNTRY	WF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
544	4eff1522-345d-494f-b412-42c55e2ce06a	PHONECODE	\N	212	212	COUNTRY	EH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
545	b666837f-fbca-4754-826e-5cb4d47b2e93	PHONECODE	\N	967	967	COUNTRY	YE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
546	f9df99a3-f681-44fd-94d2-3a3448f80eb3	PHONECODE	\N	260	260	COUNTRY	ZM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
547	b89c2f16-de0e-4771-ad18-6a5202feee88	PHONECODE	\N	263	263	COUNTRY	ZW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:46:34.632	2023-09-29 07:46:34.632+05:45	\N	\N	\N	\N	\N	\N
548	873ec30b-804b-465d-afc6-9e8209097d63	NATIONALITY	\N	Peruvian	Peruvian	COUNTRY	PE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
549	45662ee3-e096-488d-8927-35120d456a72	NATIONALITY	\N	Papua New Guinean, Papuan	Papua New Guinean, Papuan	COUNTRY	PG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
550	f0624903-539f-44ee-bb84-4a1a7952bb9a	NATIONALITY	\N	Swazi	Swazi	COUNTRY	SZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
551	a332fc0c-228e-4964-a567-4a3243b9c4e6	NATIONALITY	\N	Philippine, Filipino	Philippine, Filipino	COUNTRY	PH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
552	969703bd-5f8b-4afd-8659-6fc53a962cfd	NATIONALITY	\N	Romanian	Romanian	COUNTRY	RO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
553	4aa0d3b3-b347-4482-be2f-8129540abcc2	NATIONALITY	\N	I-Kiribati	I-Kiribati	COUNTRY	KI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
554	73ab0847-c80d-4f34-a9f5-aa3da1567db7	NATIONALITY	\N	Djiboutian	Djiboutian	COUNTRY	DJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
555	a9e4bb00-7259-46b5-a38e-c2cd27873175	NATIONALITY	\N	Namibian	Namibian	COUNTRY	NA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
556	1fb2d16b-c467-478e-b43b-5471a2601bbe	NATIONALITY	\N	Chinese	Chinese	COUNTRY	CN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
557	08a87cb5-0bc2-444c-a072-192fc5916f7f	NATIONALITY	\N	Austrian	Austrian	COUNTRY	AT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
558	1514be45-4a3d-4f26-aba3-33c5d808a06c	NATIONALITY	\N	Motswana, Botswanan	Motswana, Botswanan	COUNTRY	BW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
559	2055c67b-45de-4afb-9ffd-76bef25b9adf	NATIONALITY	\N	Norfolk Island	Norfolk Island	COUNTRY	NF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
560	a7588978-d8a5-468d-95b9-289fe4fc9fd3	NATIONALITY	\N	Macanese, Chinese	Macanese, Chinese	COUNTRY	MO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
561	49bda5a9-7fc5-4b99-a520-710a3a7a9650	NATIONALITY	\N	Cypriot	Cypriot	COUNTRY	CY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
562	33a5fdeb-ee7d-4590-b7f9-84b31cd28ac3	NATIONALITY	\N	Malian, Malinese	Malian, Malinese	COUNTRY	ML	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
563	ced16061-a281-4299-9ea0-2dca03323012	NATIONALITY	\N	Gibraltar	Gibraltar	COUNTRY	GI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
564	1089f117-b36a-4775-b1ba-03aeb2d01a9a	NATIONALITY	\N	Costa Rican	Costa Rican	COUNTRY	CR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
565	0db7a04d-78be-496f-bccd-203eed4ca1a6	NATIONALITY	\N	Lao, Laotian	Lao, Laotian	COUNTRY	LA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
566	93464c71-8e54-4099-9940-3ede5996b595	NATIONALITY	\N	Eritrean	Eritrean	COUNTRY	ER	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
567	50487183-13f5-4ed5-be0f-0ab65f30a3af	NATIONALITY	\N	British Virgin Island	British Virgin Island	COUNTRY	VG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
568	b85cfa93-7637-4684-9a0a-6fd7780b287f	NATIONALITY	\N	Saint Vincentian, Vincentian	Saint Vincentian, Vincentian	COUNTRY	VC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
569	06dc98d7-7502-4f58-9b67-4216328f95a0	NATIONALITY	\N	Jamaican	Jamaican	COUNTRY	JM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
570	40975611-5ddd-4980-8056-8e0592a8e1c1	NATIONALITY	\N	Vatican	Vatican	COUNTRY	VA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
571	79ffb4a4-2e54-469f-8d6c-aa3e92a3397b	NATIONALITY	\N	South Korean	South Korean	COUNTRY	KR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
572	c1c5b95c-5354-4fa1-b550-a080d76d44dd	NATIONALITY	\N	Icelandic	Icelandic	COUNTRY	IS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
573	eb7d324d-ac20-4b6a-8e1c-4f3a59985949	NATIONALITY	\N	Canadian	Canadian	COUNTRY	CA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
574	bb4f62c1-ac34-4414-abc0-01d9794af60c	NATIONALITY	\N	Andorran	Andorran	COUNTRY	AD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
575	606873b1-cdd0-453d-a609-8969c9c241ac	NATIONALITY	\N	Turkish	Turkish	COUNTRY	TR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
576	bd1dfdaf-47db-4ff9-b0e3-b938a878006c	NATIONALITY	\N	Micronesian	Micronesian	COUNTRY	FM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
577	616f8cdb-64cf-4a91-af4f-86525ebb36d3	NATIONALITY	\N	Finnish	Finnish	COUNTRY	FI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
578	b3c57240-6e68-46dd-abc7-52d2350d8cc0	NATIONALITY	\N	Antiguan or Barbudan	Antiguan or Barbudan	COUNTRY	AG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
579	92000a32-8afc-4c79-92a9-fb77173d7970	NATIONALITY	\N	Swedish	Swedish	COUNTRY	SE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
580	2063ff87-3842-49d8-b914-f32740872e0e	NATIONALITY	\N	Surinamese	Surinamese	COUNTRY	SR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
581	9cf748e7-e7bb-4e35-baf8-b5ebce44c557	NATIONALITY	\N	Salvadoran	Salvadoran	COUNTRY	SV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
582	a85311d8-32cb-4d91-931c-a44d78a7db23	NATIONALITY	\N	Australian	Australian	COUNTRY	AU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
583	c82bdec1-cfd6-4cd5-aa69-b045206234e9	NATIONALITY	\N	Timorese	Timorese	COUNTRY	TL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
584	0fa487e1-00ba-4da0-ad09-e90bf9120db5	NATIONALITY	\N	Armenian	Armenian	COUNTRY	AM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
585	e8addd44-ae74-4b74-8a77-88ec56f885d2	NATIONALITY	\N	Israeli	Israeli	COUNTRY	IL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
586	717b952e-e3a0-458a-8b7d-3cfe2aed6cc3	NATIONALITY	\N	Colombian	Colombian	COUNTRY	CO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
587	831e10b4-f222-480e-b2b9-1193f1681463	NATIONALITY	\N	Bahraini	Bahraini	COUNTRY	BH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
588	06e70a3a-6060-4e12-9002-e8b9c644317c	NATIONALITY	\N	BIOT	BIOT	COUNTRY	IO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
589	d7a57560-3d54-43d2-8745-5d09e739aa90	NATIONALITY	\N	Latvian	Latvian	COUNTRY	LV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
590	be6f2b73-b4a4-426c-adcd-a61f0eabda24	NATIONALITY	\N	Zimbabwean	Zimbabwean	COUNTRY	ZW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
591	431452c8-89ea-4326-b1ca-27fb31e89ce9	NATIONALITY	\N	Slovak	Slovak	COUNTRY	SK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
592	85c6c069-2434-4867-a0e4-2ebb0f426d3c	NATIONALITY	\N	Bolivian	Bolivian	COUNTRY	BO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
593	d60e2732-6104-4057-ba98-9e44ea4e05f0	NATIONALITY	\N	Mozambican	Mozambican	COUNTRY	MZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
594	8b0ccc10-bce6-40d5-a5c3-657d6a983788	NATIONALITY	\N	Omani	Omani	COUNTRY	OM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
595	fe9c204b-9663-42e9-bb19-fa443e367339	NATIONALITY	\N	British, UK	British, UK	COUNTRY	GB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
596	620c4404-9467-443a-b98e-b11c3b585104	NATIONALITY	\N	Norwegian	Norwegian	COUNTRY	NO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
597	1dc427c7-addc-410a-84c3-a08b8f351e8a	NATIONALITY	\N	Aruban	Aruban	COUNTRY	AW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
598	0df7b4a1-a0f7-46cd-8c0a-414d533b443d	NATIONALITY	\N	Bhutanese	Bhutanese	COUNTRY	BT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
599	afda2274-a7a5-47dc-8727-f6601c2ffbd8	NATIONALITY	\N	Beninese, Beninois	Beninese, Beninois	COUNTRY	BJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
600	a3e42509-20b0-433b-a8cc-e5b9a7483140	NATIONALITY	\N	Grenadian	Grenadian	COUNTRY	GD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
601	ea8479a9-7d6c-4faf-b245-5209360f68ac	NATIONALITY	\N	Seychellois	Seychellois	COUNTRY	SC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
602	ba7fe319-cbde-4331-a00e-fbd8bf352e18	NATIONALITY	\N	Mauritian	Mauritian	COUNTRY	MU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
603	3ead65b5-6697-4a4b-9dd7-2911b43e21e2	NATIONALITY	\N	Comoran, Comorian	Comoran, Comorian	COUNTRY	KM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
604	4a362d60-49ba-4459-a9d5-ac6f70ce1c5e	NATIONALITY	\N	Chilean	Chilean	COUNTRY	CL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
605	1969315d-a45b-4c35-bb85-9f9a57d3b30a	NATIONALITY	\N	Tuvaluan	Tuvaluan	COUNTRY	TV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
606	2925aa90-cc42-47d1-bb44-93c00fc5453a	NATIONALITY	\N	Antarctic	Antarctic	COUNTRY	AQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
607	6aa5ff41-ef42-4b60-a933-2c9378927955	NATIONALITY	\N	Ugandan	Ugandan	COUNTRY	UG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
608	95c1c063-072b-4c70-b98c-a36f71f3b853	NATIONALITY	\N	Tokelauan	Tokelauan	COUNTRY	TK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
609	56044393-54cf-48d8-a04e-a2f035bb7f02	NATIONALITY	\N	Panamanian	Panamanian	COUNTRY	PA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
610	d1e7608a-7247-4787-a1ec-8fb250696bf8	NATIONALITY	\N	Pakistani	Pakistani	COUNTRY	PK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
611	d3fe4d61-5408-4988-8edd-87ed8febf0d7	NATIONALITY	\N	Guinean	Guinean	COUNTRY	GN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
612	205307f9-3d5b-45f4-9e0c-dcb34d3b66fd	NATIONALITY	\N	Azerbaijani, Azeri	Azerbaijani, Azeri	COUNTRY	AZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
613	debbb58b-1395-41c0-8694-c46f75acbfd9	NATIONALITY	\N	Hungarian, Magyar	Hungarian, Magyar	COUNTRY	HU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
614	6f429022-4f9e-49c8-a5f8-e6270ef2e23a	NATIONALITY	\N	Uruguayan	Uruguayan	COUNTRY	UY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
615	bdd4b6d1-2677-48cc-9a7e-3d67462d35dc	NATIONALITY	\N	Saudi, Saudi Arabian	Saudi, Saudi Arabian	COUNTRY	SA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
616	6e13a42a-ae0a-4b25-9980-02e92bb03c74	NATIONALITY	\N	Libyan	Libyan	COUNTRY	LY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
617	4dca0231-e1b2-487b-9491-84009e1c6372	NATIONALITY	\N	Belarusian	Belarusian	COUNTRY	BY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
618	74ddb955-bb74-4f67-a6d1-f03065ec26d1	NATIONALITY	\N	Christmas Island	Christmas Island	COUNTRY	CX	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
619	e79f363d-9928-46fe-89dd-5c94add55984	NATIONALITY	\N	Nigerian	Nigerian	COUNTRY	NG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
620	59fa5621-62c8-4a6c-84a3-502a24984dc7	NATIONALITY	\N	Bulgarian	Bulgarian	COUNTRY	BG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
621	0b59337a-b353-418b-a56b-5eccc3893c4c	NATIONALITY	\N	Heard Island or McDonald Islands	Heard Island or McDonald Islands	COUNTRY	HM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
622	9d87bbea-fc89-49cf-9558-e328043b6168	NATIONALITY	\N	American	American	COUNTRY	UM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
623	c4dfc469-2ce0-4f4d-9548-aeb4fa3fa1cc	NATIONALITY	\N	Niuean	Niuean	COUNTRY	NU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
624	e36a75b2-d833-4b3e-90f0-ae3434491a72	NATIONALITY	\N	Iranian, Persian	Iranian, Persian	COUNTRY	IR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
625	0c3d09f3-b389-41cb-9e04-4245b29305e4	NATIONALITY	\N	Albanian	Albanian	COUNTRY	AL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
626	c76ecba9-572d-40df-9427-caa957668248	NATIONALITY	\N	Chinese, Taiwanese	Chinese, Taiwanese	COUNTRY	TW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
627	e2cad999-1e48-46d7-bc0e-a0e96155ef0a	NATIONALITY	\N	Belizean	Belizean	COUNTRY	BZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
628	9dbd92ce-5f65-4ef4-ba86-8aafd9464c03	NATIONALITY	\N	American	American	COUNTRY	US	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
629	eef60c36-b23e-4cb1-963f-752613ca27e2	NATIONALITY	\N	Brazilian	Brazilian	COUNTRY	BR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
630	2c9c9084-595d-4af8-b5f4-b7501e4a8089	NATIONALITY	\N	Polish	Polish	COUNTRY	PL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
631	65a771e3-9b4e-4267-8e2f-1e10225c0d0a	NATIONALITY	\N	Kyrgyzstani, Kyrgyz, Kirgiz, Kirghiz	Kyrgyzstani, Kyrgyz, Kirgiz, Kirghiz	COUNTRY	KG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
632	5b74dd87-1568-4daa-b3ed-23c9dbadbac6	NATIONALITY	\N	Guyanese	Guyanese	COUNTRY	GY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
633	f5a663aa-e042-4fdb-81ef-358f2db97184	NATIONALITY	\N	Puerto Rican	Puerto Rican	COUNTRY	PR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
634	d00f8f87-02b3-43be-8efa-f316edc0054c	NATIONALITY	\N	Pitcairn Island	Pitcairn Island	COUNTRY	PN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
635	e94c2828-4756-4143-9d03-349a29b4a88c	NATIONALITY	\N	Singaporean	Singaporean	COUNTRY	SG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
636	3e4952e7-e0a9-466d-831f-c8b7cff3cfdb	NATIONALITY	\N	Qatari	Qatari	COUNTRY	QA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
637	2a5799d2-592e-4430-8c0f-725e35487ab3	NATIONALITY	\N	Bissau-Guinean	Bissau-Guinean	COUNTRY	GW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
638	92771f02-b05c-4c6b-9e97-2a99365af04b	NATIONALITY	\N	Maltese	Maltese	COUNTRY	MT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
639	8e708cc0-cda0-4dc0-919d-7c986ed6e8ec	NATIONALITY	\N	Tajikistani	Tajikistani	COUNTRY	TJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
640	63d9d2af-ba9a-405e-95e4-37b4c5c8a602	NATIONALITY	\N	Maldivian	Maldivian	COUNTRY	MV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
641	6287fe0b-7171-4dc4-b13f-c3c9c383d944	NATIONALITY	\N	Honduran	Honduran	COUNTRY	HN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
642	d584c412-afe2-4aab-b0c6-0172c2bb9169	NATIONALITY	\N	Italian	Italian	COUNTRY	IT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
643	4d0875be-5bf3-4272-b23c-4a868f346e14	NATIONALITY	\N	Greenlandic	Greenlandic	COUNTRY	GL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
644	a9787f24-0dec-41ff-88a6-24d4695b5d12	NATIONALITY	\N	Barbadian	Barbadian	COUNTRY	BB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
645	6a884e18-7ce4-4af6-9e6a-c06062402062	NATIONALITY	\N	Palestinian	Palestinian	COUNTRY	PS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
646	7b07efd2-bdae-4ac4-bbad-800f0037dbbd	NATIONALITY	\N	Lithuanian	Lithuanian	COUNTRY	LT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
647	9969cc53-e856-477b-8762-c1d19a0d0d37	NATIONALITY	\N	Emirati, Emirian, Emiri	Emirati, Emirian, Emiri	COUNTRY	AE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
648	d7c3ba6e-103a-48c9-9079-ba3317c88fc3	NATIONALITY	\N	Martiniquais, Martinican	Martiniquais, Martinican	COUNTRY	MQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
649	b503d702-4c22-4ad2-9305-4a109924e8bb	NATIONALITY	\N	Turks and Caicos Island	Turks and Caicos Island	COUNTRY	TC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
650	109eaacf-bf09-4bb8-b74f-ffe70db11f75	NATIONALITY	\N	Nigerien	Nigerien	COUNTRY	NE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
651	7edd9ff7-0df2-4da7-ad79-0087fb254eb1	NATIONALITY	\N	Guamanian, Guambat	Guamanian, Guambat	COUNTRY	GU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
652	57052221-c18c-48ac-b37c-08c836462904	NATIONALITY	\N	Spanish	Spanish	COUNTRY	ES	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
653	8a683cb2-ad6e-447c-82b3-ceff35e81b41	NATIONALITY	\N	Afghan	Afghan	COUNTRY	AF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
654	8b5d6e02-e0f9-410d-b228-d735d33e0b86	NATIONALITY	\N	Cocos Island	Cocos Island	COUNTRY	CC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
655	19437941-3414-482f-b0f1-96c0cc284570	NATIONALITY	\N	Argentine	Argentine	COUNTRY	AR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
656	d0faf3af-85dc-41ff-a956-6d7fb432e99f	NATIONALITY	\N	Estonian	Estonian	COUNTRY	EE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
657	12ca34ff-5153-4dc3-be63-cd38a343882d	NATIONALITY	\N	U.S. Virgin Island	U.S. Virgin Island	COUNTRY	VI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
658	f3d5b4ee-2999-473c-8ab5-2697d6842f0e	NATIONALITY	\N	Iraqi	Iraqi	COUNTRY	IQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
659	ba852c73-ad70-4916-8d4e-2d970a62d423	NATIONALITY	\N	Sammarinese	Sammarinese	COUNTRY	SM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
660	dd3cbe24-a90b-4bf8-aa0f-6584f2c3915b	NATIONALITY	\N	Basotho	Basotho	COUNTRY	LS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
661	cb98cebf-11e5-4be1-b539-0320070f1eb2	NATIONALITY	\N	Bermudian, Bermudan	Bermudian, Bermudan	COUNTRY	BM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
662	cdd84040-a0ac-4f6e-9073-30d8dd892beb	NATIONALITY	\N	Dominican	Dominican	COUNTRY	DO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
663	7b1727b5-cf96-48e6-874f-99796d24a83d	NATIONALITY	\N	Bahamian	Bahamian	COUNTRY	BS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
664	9139f173-f1e8-40bc-8849-e654c479ef28	NATIONALITY	\N	Rwandan	Rwandan	COUNTRY	RW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
665	fea43999-f618-41a7-9e95-efaaf93a602d	NATIONALITY	\N	Liberian	Liberian	COUNTRY	LR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
666	6477c421-497d-48a5-b998-cfa687f34103	NATIONALITY	\N	South African	South African	COUNTRY	ZA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
667	83751273-e9a0-4356-aa04-2f803848e7df	NATIONALITY	\N	French Southern Territories	French Southern Territories	COUNTRY	TF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
668	d6e93c05-4927-4ade-8c6a-771fbb3175f0	NATIONALITY	\N	Angolan	Angolan	COUNTRY	AO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
669	04d217d8-b05d-4e2d-bd0b-24c66132e1ce	NATIONALITY	\N	Mexican	Mexican	COUNTRY	MX	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
670	52d35715-a870-456e-93cb-f7152fdeb3e6	NATIONALITY	\N	Malawian	Malawian	COUNTRY	MW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
671	088098a9-c59d-489a-8be1-0bd61bb71179	NATIONALITY	\N	Ecuadorian	Ecuadorian	COUNTRY	EC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
672	7e9059e7-3dd4-4405-a406-f4cff5273c4f	NATIONALITY	\N	Wallis and Futuna, Wallisian or Futunan	Wallis and Futuna, Wallisian or Futunan	COUNTRY	WF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
673	711f166f-2849-4603-99e9-19e6f0df550f	NATIONALITY	\N	Senegalese	Senegalese	COUNTRY	SN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
674	0e741625-4a5e-46a9-8d70-379901f7fe4b	NATIONALITY	\N	Somali, Somalian	Somali, Somalian	COUNTRY	SO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
675	14022ff6-e073-43fd-aa32-bdd1edf39caf	NATIONALITY	\N	São Toméan	São Toméan	COUNTRY	ST	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
676	013d6fe1-fc70-4c62-9542-3560159771e6	NATIONALITY	\N	Macedonian	Macedonian	COUNTRY	MK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
677	885614f5-99ff-4667-8d73-011965a88701	NATIONALITY	\N	Ghanaian	Ghanaian	COUNTRY	GH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
678	17be697c-bce4-421f-b096-d4f948458c84	NATIONALITY	\N	Cabo Verdean	Cabo Verdean	COUNTRY	CV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
679	8d63cdf0-24af-45de-bcf5-c0455007fa0b	NATIONALITY	\N	Guadeloupe	Guadeloupe	COUNTRY	GP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
680	533135c7-f997-4bea-8463-9f59faf88411	NATIONALITY	\N	Sudanese	Sudanese	COUNTRY	SD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
681	725a72e5-9590-40dd-8a6d-0a74906de13e	NATIONALITY	\N	Syrian	Syrian	COUNTRY	SY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
682	6dea6c92-cad8-4435-8771-f5f6727322ae	NATIONALITY	\N	Falkland Island	Falkland Island	COUNTRY	FK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
683	244f1df1-16e1-47bc-9ae0-b4756c292c62	NATIONALITY	\N	Tunisian	Tunisian	COUNTRY	TN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
684	5cb41d31-365b-475f-9625-ff4bc64a39b9	NATIONALITY	\N	Bruneian	Bruneian	COUNTRY	BN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
685	403717c1-160c-472e-b815-07129eb6b0eb	NATIONALITY	\N	Gabonese	Gabonese	COUNTRY	GA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
686	e64a306e-8e16-4f78-b2c1-3f231da31604	NATIONALITY	\N	Congolese	Congolese	COUNTRY	CD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
687	9af8e71a-68be-48aa-923f-9b5c2a62916e	NATIONALITY	\N	Svalbard	Svalbard	COUNTRY	SJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
688	c6657932-5ff9-4a2f-8c1f-2c53e89fc504	NATIONALITY	\N	Monégasque, Monacan	Monégasque, Monacan	COUNTRY	MC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
689	1405fded-1c14-44b9-a2d0-edd003969a63	NATIONALITY	\N	Central African	Central African	COUNTRY	CF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
690	ecf5e567-9d5f-49e9-b4e0-ca91e163d595	NATIONALITY	\N	Malaysian	Malaysian	COUNTRY	MY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
691	448b5fa6-1c6b-467b-ab2a-4f6926188125	NATIONALITY	\N	North Korean	North Korean	COUNTRY	KP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
692	a9529456-2605-4dd2-b934-84ab6b6d8e3e	NATIONALITY	\N	French Guianese	French Guianese	COUNTRY	GF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
693	889f0b0c-553b-4c96-a626-0f5d17569266	NATIONALITY	\N	Sahrawi, Sahrawian, Sahraouian	Sahrawi, Sahrawian, Sahraouian	COUNTRY	EH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
694	dc4926eb-e47f-40e5-9d3f-7962cf7b042c	NATIONALITY	\N	Ni-Vanuatu, Vanuatuan	Ni-Vanuatu, Vanuatuan	COUNTRY	VU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
695	a696026b-458f-4558-988b-33af1a9bd9a5	NATIONALITY	\N	Cameroonian	Cameroonian	COUNTRY	CM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
696	6a7360d5-d623-4451-8c06-7b1c811e3e9a	NATIONALITY	\N	Portuguese	Portuguese	COUNTRY	PT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
697	4b90c18e-eb24-4875-9577-85a317fdd40f	NATIONALITY	\N	French Polynesian	French Polynesian	COUNTRY	PF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
698	65e46962-2579-4e8e-b987-345dde46f6a4	NATIONALITY	\N	Ivorian	Ivorian	COUNTRY	CI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
699	4e9ecbf7-d841-48c3-b8e4-a68b4f61da9b	NATIONALITY	\N	Cambodian	Cambodian	COUNTRY	KH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
700	a3778e0b-fa02-4e23-8c75-db13b7913be7	NATIONALITY	\N	Togolese	Togolese	COUNTRY	TG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
701	acfa5b02-c1b4-43a1-b02f-0d76217560de	NATIONALITY	\N	Nicaraguan	Nicaraguan	COUNTRY	NI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
702	06fa3047-8b5d-4bb1-904b-ada995301dff	NATIONALITY	\N	Irish	Irish	COUNTRY	IE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
703	8144ff1d-6b12-4bdb-9a9d-7fe16c99d93d	NATIONALITY	\N	Ethiopian	Ethiopian	COUNTRY	ET	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
704	9a8c28ff-47a7-4a2a-bc19-e9ef546f0902	NATIONALITY	\N	Croatian	Croatian	COUNTRY	HR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
705	81fb8cbc-6000-4287-82f1-ad33e62523c4	NATIONALITY	\N	Kittitian or Nevisian	Kittitian or Nevisian	COUNTRY	KN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
706	fee261d7-ab69-415c-acf4-abea4b9b6a11	NATIONALITY	\N	Paraguayan	Paraguayan	COUNTRY	PY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
707	19dea2cd-a735-4e0d-a71e-a24099574b38	NATIONALITY	\N	New Caledonian	New Caledonian	COUNTRY	NC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
708	a8eb2284-33ba-4678-a47c-83649e98af18	NATIONALITY	\N	Nauruan	Nauruan	COUNTRY	NR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
709	c07fd114-3e8e-416e-aff2-5958820048e2	NATIONALITY	\N	Dutch, Netherlandic	Dutch, Netherlandic	COUNTRY	NL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
710	69801d93-8cd2-46c0-b91a-001d5244326b	NATIONALITY	\N	Nepali, Nepalese	Nepali, Nepalese	COUNTRY	NP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
711	5835fbe6-e410-4437-bfa2-69c8aa9a7be3	NATIONALITY	\N	Luxembourg, Luxembourgish	Luxembourg, Luxembourgish	COUNTRY	LU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
712	46b44a19-2246-44ca-b229-137618fc331c	NATIONALITY	\N	Algerian	Algerian	COUNTRY	DZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
713	5e054f81-08fb-449b-9232-3f9268b03746	NATIONALITY	\N	Russian	Russian	COUNTRY	RU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
714	872a5101-07ec-40cc-9183-9bea6b50f029	NATIONALITY	\N	Vietnamese	Vietnamese	COUNTRY	VN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
715	547e0c72-3863-422e-bee7-28ea4c4a9ad9	NATIONALITY	\N	Indonesian	Indonesian	COUNTRY	ID	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
716	f5d30192-4aad-487c-a655-c8884b796b79	NATIONALITY	\N	Marshallese	Marshallese	COUNTRY	MH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
717	7ee27f1a-3534-4f6d-a2a4-26a38b34dfc2	NATIONALITY	\N	Cook Island	Cook Island	COUNTRY	CK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
718	33ba6f31-54b9-4b7f-9a0a-2cf596a134ff	NATIONALITY	\N	Indian	Indian	COUNTRY	IN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
719	d328b074-deb1-4c23-af04-b4ef2479c76b	NATIONALITY	\N	Mauritanian	Mauritanian	COUNTRY	MR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
720	87d51506-3410-4c5a-b70c-355ffa65038e	NATIONALITY	\N	Yemeni	Yemeni	COUNTRY	YE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
721	074da7bf-5066-4674-be6d-d83101722167	NATIONALITY	\N	Kuwaiti	Kuwaiti	COUNTRY	KW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
722	5e62a5c7-bba3-43f3-ba48-b6074a35c251	NATIONALITY	\N	Hong Kong, Hong Kongese	Hong Kong, Hong Kongese	COUNTRY	HK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
723	27bef2b1-18a9-4d3d-abc1-37bbf3697b83	NATIONALITY	\N	Kenyan	Kenyan	COUNTRY	KE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
724	c567a5c8-3c21-4d5a-8df3-c428804c8aa0	NATIONALITY	\N	Kazakhstani, Kazakh	Kazakhstani, Kazakh	COUNTRY	KZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
725	df93c21a-e051-410f-a806-b5b566a5a16a	NATIONALITY	\N	Cuban	Cuban	COUNTRY	CU	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
726	f7fa40bf-ce0e-4c6d-b81c-57941a183501	NATIONALITY	\N	Sierra Leonean	Sierra Leonean	COUNTRY	SL	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
727	c661d6ed-5cd7-49e1-931c-24874bde6803	NATIONALITY	\N	Faroese	Faroese	COUNTRY	FO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
728	e2bf465d-9032-451a-947b-51b3012aa48f	NATIONALITY	\N	Chadian	Chadian	COUNTRY	TD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
729	d99118b0-6fce-44b9-b7a8-eb640fe6689e	NATIONALITY	\N	Mongolian	Mongolian	COUNTRY	MN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
730	47a52cfe-d04f-4df5-afc9-ba9009289145	NATIONALITY	\N	Bouvet Island	Bouvet Island	COUNTRY	BV	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
731	c6db82fe-288e-4902-9619-6f5d87609fc3	NATIONALITY	\N	Samoan	Samoan	COUNTRY	WS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
732	7d483b3d-5c45-40d6-9834-d281655f3db0	NATIONALITY	\N	Liechtenstein	Liechtenstein	COUNTRY	LI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
733	8ae40d57-61f1-4184-af6d-50d6983de5a0	NATIONALITY	\N	Anguillan	Anguillan	COUNTRY	AI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
734	0b074286-9e8a-4573-a2dc-c2c5d8a7f13f	NATIONALITY	\N	Belgian	Belgian	COUNTRY	BE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
735	d82841d5-2958-4d8e-9b43-d79624226b3f	NATIONALITY	\N	Bangladeshi	Bangladeshi	COUNTRY	BD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
736	e08eaacf-d8d0-4e51-9dc5-a0d5bb5081b9	NATIONALITY	\N	American Samoan	American Samoan	COUNTRY	AS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
737	73081eb1-c4ee-4879-bd3e-3ef5c27fe97e	NATIONALITY	\N	South Georgia or South Sandwich Islands	South Georgia or South Sandwich Islands	COUNTRY	GS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
738	f52904d3-4d14-4a87-b00c-801771564616	NATIONALITY	\N	Ukrainian	Ukrainian	COUNTRY	UA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
739	37fb3e95-0fdc-443d-bc55-817a62bd467c	NATIONALITY	\N	Sri Lankan	Sri Lankan	COUNTRY	LK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
740	84c7aa23-ee7f-4cd6-a462-782f2e00b6ff	NATIONALITY	\N	Congolese	Congolese	COUNTRY	CG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
741	3f955123-af1e-4598-be81-4a25f23216ae	NATIONALITY	\N	Moroccan	Moroccan	COUNTRY	MA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
742	95b08985-03f0-42ed-bc11-e50362e30e6d	NATIONALITY	\N	Tanzanian	Tanzanian	COUNTRY	TZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
743	e742ad03-a4a1-412f-badb-8ef3520f6ad0	NATIONALITY	\N	Malagasy	Malagasy	COUNTRY	MG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
744	8d6dca72-4555-47a5-8561-789f28b07e80	NATIONALITY	\N	German	German	COUNTRY	DE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
745	d394d231-c06a-452f-ad3e-96663cab575f	NATIONALITY	\N	Czech	Czech	COUNTRY	CZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
746	62833ed6-7bcc-433c-9067-58402adadff4	NATIONALITY	\N	Burmese	Burmese	COUNTRY	MM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
747	817e3b2f-5141-44e8-bc32-ed61899744de	NATIONALITY	\N	Georgian	Georgian	COUNTRY	GE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
748	1f1526d1-ed09-4fb9-96dc-b82f3d061309	NATIONALITY	\N	Egyptian	Egyptian	COUNTRY	EG	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
749	315fc6c1-6753-4013-bb5a-b463d6d745c9	NATIONALITY	\N	Uzbekistani, Uzbek	Uzbekistani, Uzbek	COUNTRY	UZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
750	4c213dce-a60d-4ee3-8720-4edfc428e95f	NATIONALITY	\N	Moldovan	Moldovan	COUNTRY	MD	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
751	eeb5a1d2-1c69-45e2-990f-b43ec3bc8407	NATIONALITY	\N	Japanese	Japanese	COUNTRY	JP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
752	a929369e-2927-4aa5-8282-42385541e9d6	NATIONALITY	\N	Danish	Danish	COUNTRY	DK	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
753	fa5649f9-6cca-4f41-a8b0-b9e368168294	NATIONALITY	\N	Gambian	Gambian	COUNTRY	GM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
754	9e7cba06-efa8-42d3-b620-3f087ea0c617	NATIONALITY	\N	Jordanian	Jordanian	COUNTRY	JO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
755	3a7a6f8b-6d5c-4a4e-b6fc-d4981bc1070c	NATIONALITY	\N	Slovenian, Slovene	Slovenian, Slovene	COUNTRY	SI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
756	6408654f-ccaf-49ef-816b-9a44162b775e	NATIONALITY	\N	Northern Marianan	Northern Marianan	COUNTRY	MP	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
757	3c90fc89-07d9-49c7-8473-e0bafe3f7ac0	NATIONALITY	\N	Equatorial Guinean, Equatoguinean	Equatorial Guinean, Equatoguinean	COUNTRY	GQ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
758	5d8004f2-ac62-4de5-bd4a-787df1dd2d4c	NATIONALITY	\N	Turkmen	Turkmen	COUNTRY	TM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
759	0d4aca6f-b0f2-4bed-8d1a-18e1b23d05d7	NATIONALITY	\N	Solomon Island	Solomon Island	COUNTRY	SB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
760	d89f0cfb-5ef0-4ed8-b5f3-1cf994142249	NATIONALITY	\N	Burundian	Burundian	COUNTRY	BI	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
761	4c7f18b5-1c24-4d45-9634-7346ae68aa3a	NATIONALITY	\N	Tongan	Tongan	COUNTRY	TO	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
762	55f13589-9d82-4e31-9e3b-55b003f3d8a0	NATIONALITY	\N	Bosnian or Herzegovinian	Bosnian or Herzegovinian	COUNTRY	BA	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
763	f64d75f7-782b-424f-baf6-f72ae5a962e1	NATIONALITY	\N	Mahoran	Mahoran	COUNTRY	YT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
764	766ad068-0dbb-4436-a8e1-150a2d659562	NATIONALITY	\N	Trinidadian or Tobagonian	Trinidadian or Tobagonian	COUNTRY	TT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
765	7ed071c4-4532-46e1-88b2-113800d6dde3	NATIONALITY	\N	Montserratian	Montserratian	COUNTRY	MS	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
766	a18f0a7d-7c9f-4db2-8c82-cbf00b3b5e72	NATIONALITY	\N	Saint Lucian	Saint Lucian	COUNTRY	LC	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
767	7e8984b2-b86f-46e7-9224-0a50d3daa867	NATIONALITY	\N	Palauan	Palauan	COUNTRY	PW	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
768	766f406d-bf9c-4b03-bae7-2b563ac1e65b	NATIONALITY	\N	Fijian	Fijian	COUNTRY	FJ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
769	be8b0b7e-c679-4116-a390-c5a9ea5feb7d	NATIONALITY	\N	Burkinabé	Burkinabé	COUNTRY	BF	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
770	afa3eaff-81a0-4a8d-8762-3312e711e699	NATIONALITY	\N	Saint-Pierrais or Miquelonnais	Saint-Pierrais or Miquelonnais	COUNTRY	PM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
771	7b77b5aa-0c76-4b11-bc0e-50f0259e775e	NATIONALITY	\N	Greek, Hellenic	Greek, Hellenic	COUNTRY	GR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
772	9c7a977a-bc04-4048-9c62-64ae61b600a9	NATIONALITY	\N	Dominican	Dominican	COUNTRY	DM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
773	1cd08981-139d-4479-85d7-327078f004d6	NATIONALITY	\N	Saint Helenian	Saint Helenian	COUNTRY	SH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
774	5b7873d5-5c95-435d-86a8-b0d1cba6dcfc	NATIONALITY	\N	Caymanian	Caymanian	COUNTRY	KY	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
775	1a84df6c-508b-4851-bdb5-ef2cf394bc85	NATIONALITY	\N	Lebanese	Lebanese	COUNTRY	LB	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
776	24136466-c114-4ce2-b6fb-6b926a8ae5ec	NATIONALITY	\N	Haitian	Haitian	COUNTRY	HT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
777	29444d64-c516-4180-a5ec-0e2a1552fad1	NATIONALITY	\N	New Zealand, NZ	New Zealand, NZ	COUNTRY	NZ	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
778	fddf6759-7457-43eb-a57d-6a89c3fad17a	NATIONALITY	\N	Venezuelan	Venezuelan	COUNTRY	VE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
779	a1f763f5-2327-429b-81dd-1c9032190129	NATIONALITY	\N	Swiss	Swiss	COUNTRY	CH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
780	b69850f9-45b6-4123-b13a-a4234922bd61	NATIONALITY	\N	Guatemalan	Guatemalan	COUNTRY	GT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
781	9b4d42e7-e8ce-4bc8-add3-752d85b8cb23	NATIONALITY	\N	Thai	Thai	COUNTRY	TH	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
782	1a1fb5c1-219d-41e9-a3f4-6a161f00adef	NATIONALITY	\N	Réunionese, Réunionnais	Réunionese, Réunionnais	COUNTRY	RE	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
783	9eb16a14-288e-4f6b-9a60-9fa40e88e8d1	NATIONALITY	\N	French	French	COUNTRY	FR	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
784	fb509219-4cf8-4730-8be2-115de76bd196	NATIONALITY	\N	Zambian	Zambian	COUNTRY	ZM	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 07:59:18.253	2023-09-29 07:59:18.253+05:45	\N	\N	\N	\N	\N	\N
785	a40a8b7b-e14b-4f02-9c92-87847ef2fe23	USER TYPE	\N	ADMIN	ADMIN	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:39:23.747	2023-09-29 06:54:23.747+05:45	\N	\N	\N	\N	\N	\N
786	51c6d6a3-75d1-478b-ab48-74ad38b8276a	USER TYPE	\N	MERCHANT_USER	MERCHANT_USER	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:39:23.747	2023-09-29 06:54:23.747+05:45	\N	\N	\N	\N	\N	\N
787	ecaa475e-ce52-43fb-9f85-f5bcfd3982f5	USER TYPE	\N	ADMIN_USER	ADMIN_USER	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:39:23.747	2023-09-29 06:54:23.747+05:45	\N	\N	\N	\N	\N	\N
788	40b6c432-e613-4708-8326-bef970cc3a71	USER TYPE	\N	MERCHANT	MERCHANT	\N	\N	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:39:23.747	2023-09-29 06:54:23.747+05:45	\N	\N	\N	\N	\N	\N
789	8a580090-ff8e-40e0-8371-b9e7a81f0115	ROLE	\N	ROLE_MERCHANT_USER	ROLE_MERCHANT_USER	USER TYPE	MERCHANT_USER	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:44:30.891	2023-09-29 06:59:30.891+05:45	\N	\N	\N	\N	\N	\N
790	b833f234-f4aa-4e14-bf26-ac548cdc26a5	ROLE	\N	ROLE_ADMIN	ROLE_ADMIN	USER TYPE	ADMIN	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:44:30.891	2023-09-29 06:59:30.891+05:45	\N	\N	\N	\N	\N	\N
791	04e3a35f-378b-4554-8bac-90a14e29126d	ROLE	\N	ROLE_MERCHANT	ROLE_MERCHANT	USER TYPE	MERCHANT	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:44:30.891	2023-09-29 06:59:30.891+05:45	\N	\N	\N	\N	\N	\N
792	7e80fd80-d226-4dfe-9c4b-8e028d766db0	ROLE	\N	ROLE_ADMIN_USER	ROLE_ADMIN_USER	USER TYPE	ADMIN_USER	\N	\N	\N	\N	\N	\N	ACTIVE	t	\N	system	2023-09-29 12:44:30.891	2023-09-29 06:59:30.891+05:45	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 4007 (class 0 OID 25629)
-- Dependencies: 242
-- Data for Name: category_roots_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category_roots_log (id, category_root_id, category_type, sub_type, category_code, description, parent_type, parent_code, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 3984 (class 0 OID 25450)
-- Dependencies: 219
-- Data for Name: documents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.documents (id, doc_id, doc_type, sub_type, source_type, source_id, association_type, association_id, doc_path, doc_name, doc_alias, doc_id_number, issue_date, start_date, expiry_date, issuing_country, issuing_place, issuing_authority, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc, approved_by, approved_date, approved_date_utc) FROM stdin;
\.


--
-- TOC entry 3986 (class 0 OID 25463)
-- Dependencies: 221
-- Data for Name: error_codes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.error_codes (id, error_code, message, description, module_id, lang, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4009 (class 0 OID 25644)
-- Dependencies: 244
-- Data for Name: error_possible_actions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.error_possible_actions (id, error_code, possible_actions, lang, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 3988 (class 0 OID 25477)
-- Dependencies: 223
-- Data for Name: merchants; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants (id, merchant_id, merchant_type, email_id, phone_code, phone_number, incorporation_country, business_name, business_name_native, business_type, industry_type, product_type, business_nature, incorporation_date, bizz_reg_no, corp_reg_no, business_profile, postal_code, address1, address2, city, website, currency_preference, approx_txn_monthly_volume, approx_txn_yearly_volume, kyc_status, kyc_remarks, rba_status, rba_remarks, compliance_status, compliance_remarks, doc_path, notification_method, preferred_date_format, preferred_time_zone, security_stamp, terms_conditions_accepted, privacy_policy_accepted, pricing_policy_accepted, marketing_email_subscription, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc, approved_by, approved_date, approved_date_utc) FROM stdin;
1	27b99c9a-4006-4298-aa17-0d711a74a6f4	Business	suraj1@yopmail.com	977	9869136764	NP	ABC Company Limited	\N	Partnership Company	Retailers	Clothing	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	6c06c6f1-ee41-4c18-9a04-946a36601356	t	t	t	t	\N	PENDING	t	\N	\N	\N	\N	\N	\N	\N	\N	A6EE0BC6598333243248BAB74C92B558	system	2023-09-18 15:36:10.305	2023-09-18 09:51:10.305+05:45	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 4011 (class 0 OID 25661)
-- Dependencies: 246
-- Data for Name: merchants_bank_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_bank_details (id, merchant_bank_id, merchant_id, bank_id, account_name, account_number, swift_code, bic_code, ifsc_code, iban_cbu_card_number, is_verified, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4012 (class 0 OID 25679)
-- Dependencies: 247
-- Data for Name: merchants_bank_details_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_bank_details_log (id, merchant_id, bank_id, account_name, account_number, swift_code, bic_code, ifsc_code, iban_cbu_card_number, is_verified, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4014 (class 0 OID 25694)
-- Dependencies: 249
-- Data for Name: merchants_directors_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_directors_details (id, merchant_director_id, merchant_id, full_name, full_name_native, nationality, dob, phone_code, phone_number, email_id, residence_country, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4015 (class 0 OID 25711)
-- Dependencies: 250
-- Data for Name: merchants_directors_details_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_directors_details_log (id, merchant_id, full_name, full_name_native, nationality, dob, phone_code, phone_number, email_id, residence_country, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4017 (class 0 OID 25725)
-- Dependencies: 252
-- Data for Name: merchants_documents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_documents (id, merchant_document_id, merchant_id, document_type, document_path, document_name, document_alias, document_id_number, issue_date, expiry_date, issue_place, issuing_authority, document_map_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc, approved_by, approved_date, approved_date_utc) FROM stdin;
\.


--
-- TOC entry 4018 (class 0 OID 25742)
-- Dependencies: 253
-- Data for Name: merchants_documents_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_documents_log (id, merchant_id, document_type, document_path, document_name, document_alias, document_id_number, issue_date, expiry_date, issue_place, issuing_authority, document_map_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4019 (class 0 OID 25755)
-- Dependencies: 254
-- Data for Name: merchants_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_log (id, merchant_type, email_id, phone_code, phone_number, incorporation_country, business_name, business_name_native, business_type, industry_type, product_type, business_nature, incorporation_date, bizz_reg_no, corp_reg_no, business_profile, postal_code, address1, address2, city, website, currency_preference, approx_txn_monthly_volume, approx_txn_yearly_volume, kyc_status, kyc_remarks, rba_status, rba_remarks, compliance_status, compliance_remarks, doc_path, notification_method, preferred_date_format, preferred_time_zone, security_stamp, terms_conditions_accepted, privacy_policy_accepted, pricing_policy_accepted, marketing_email_subscription, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4021 (class 0 OID 25774)
-- Dependencies: 256
-- Data for Name: merchants_owners_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_owners_details (id, merchant_owener_id, merchant_id, full_name, full_name_native, role, nationality, residence_country, phone_code, phone_number, email_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4022 (class 0 OID 25791)
-- Dependencies: 257
-- Data for Name: merchants_owners_details_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_owners_details_log (id, merchant_id, full_name, full_name_native, role, nationality, residence_country, phone_code, phone_number, email_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4024 (class 0 OID 25805)
-- Dependencies: 259
-- Data for Name: merchants_representative_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_representative_details (id, merchant_representative_id, merchant_id, first_name, middle_name, last_name, full_name, full_name_native, designation, nationality, phone_code, phone_number, email_id, dob, residence_country, postal_code, address1, address2, city, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4025 (class 0 OID 25822)
-- Dependencies: 260
-- Data for Name: merchants_representative_details_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_representative_details_log (id, merchant_id, first_name, middle_name, last_name, full_name, full_name_native, designation, nationality, phone_code, phone_number, email_id, dob, residence_country, postal_code, address1, address2, city, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4027 (class 0 OID 25836)
-- Dependencies: 262
-- Data for Name: merchants_service_preferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_service_preferences (id, merchant_service_preference_id, merchant_id, service_type, service_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4028 (class 0 OID 25853)
-- Dependencies: 263
-- Data for Name: merchants_service_preferences_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_service_preferences_log (id, merchant_id, service_type, service_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4030 (class 0 OID 25867)
-- Dependencies: 265
-- Data for Name: merchants_stockholders_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_stockholders_details (id, merchant_stockholder_id, merchant_id, full_name, full_name_native, nationality, dob, phone_code, phone_number, email_id, residence_country, percentage_of_share, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4031 (class 0 OID 25884)
-- Dependencies: 266
-- Data for Name: merchants_stockholders_details_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_stockholders_details_log (id, merchant_id, full_name, full_name_native, nationality, dob, phone_code, phone_number, email_id, residence_country, percentage_of_share, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4033 (class 0 OID 25898)
-- Dependencies: 268
-- Data for Name: merchants_wallets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_wallets (id, wallet_id, merchant_id, wallet_type, account_number, virtual_account, currency_code, balance, risk_score, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, last_transaction_date, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4034 (class 0 OID 25919)
-- Dependencies: 269
-- Data for Name: merchants_wallets_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchants_wallets_log (id, merchant_id, wallet_type, account_number, virtual_account, currency_code, balance, risk_score, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, last_transaction_date, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 3990 (class 0 OID 25497)
-- Dependencies: 225
-- Data for Name: message_queue; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.message_queue (id, message_queue_id, message_type, priority, contact_info, cc, bcc, content, subject, schedule_time, schedule_time_utc, reference, association_id, association_type, purpose_id, purpose_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
1	6a03e0c3-4ea9-45bd-a310-850d6b0bb7fc	1	\N	pramodparajuli@gmail.com	\N	\N	817693	GME Remit: OTP code for user signup 	\N	\N	\N	0	\N	0	\N	\N	SENT	f	\N	suraj1@yopmail.com	2023-10-01 17:07:29.483192	2023-10-01 11:22:29.483192+05:45	\N	\N	\N
2	8642dc89-52f5-4a02-8a44-ba1dcac48294	1	\N	pramodparajuli@gmail.com	\N	\N	344923	GME Remit: OTP code for user signup 	\N	\N	\N	0	\N	0	\N	\N	SENT	f	AA3DE4F26F52C8FCFB4BF2CED2EA5A70	suraj1@yopmail.com	2023-10-01 17:33:16.576325	2023-10-01 11:48:16.576325+05:45	\N	\N	\N
3	413f9524-f53b-4bee-9c4f-102aa2f28cf6	1	\N	pramodparajuli@gmail.com	\N	\N	876879	GME Remit: OTP code for user signup 	\N	\N	\N	0	\N	0	\N	\N	SENT	f	32145BC1ADEA66964E417C5DAE0DBBB3	suraj1@yopmail.com	2023-10-01 17:40:04.023345	2023-10-01 11:55:04.023345+05:45	\N	\N	\N
4	95abd0f9-94b3-41b2-9f45-1da9b9650acf	1	\N	pramodparajuli@gmail.com	\N	\N	292682	GME Remit: OTP code for user signup 	\N	\N	\N	0	\N	0	\N	\N	SENT	f	18EDC154C721A5ABF539F23264570FA5	suraj1@yopmail.com	2023-10-01 17:41:35.603567	2023-10-01 11:56:35.603567+05:45	\N	\N	\N
5	090bcdb1-2094-4d5b-87a4-73f4213c8723	1	\N	rajkabee@gmail.com	\N	\N	134285	GME Remit: OTP code for user signup 	\N	\N	\N	0	\N	0	\N	\N	SENT	f	F73C571DDF0F34579CDEF2163A86C672	suraj1@yopmail.com	2023-10-01 17:42:24.348974	2023-10-01 11:57:24.348974+05:45	\N	\N	\N
\.


--
-- TOC entry 3992 (class 0 OID 25510)
-- Dependencies: 227
-- Data for Name: message_templates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.message_templates (id, message_tempate_id, template_type, purpose, template, description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject) FROM stdin;
\.


--
-- TOC entry 3994 (class 0 OID 25523)
-- Dependencies: 229
-- Data for Name: otp; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.otp (id, otp_id, otp_type, purpose, contact_info, otp_code, otp_expire_time, otp_expire_time_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 3996 (class 0 OID 25536)
-- Dependencies: 231
-- Data for Name: partners; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partners (id, partner_id, partner_name, partner_name_native, biz_reg_number, email_id, phone_code, phone_number, settlement_currency_code, website, country_code, prefered_language_code, biz_license_type, city, street_address, postal_code, business_model, partner_type, contract_date, contract_expiry_date, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc, approved_by, approved_date, approved_date_utc) FROM stdin;
\.


--
-- TOC entry 4036 (class 0 OID 25938)
-- Dependencies: 271
-- Data for Name: partners_bank_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partners_bank_details (id, partner_id, bank_code, bank_name, bank_account_number, bank_account_name, bank_bic, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4037 (class 0 OID 25956)
-- Dependencies: 272
-- Data for Name: partners_bank_details_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partners_bank_details_log (id, partner_id, bank_code, bank_name, bank_account_number, bank_account_name, bank_bic, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4038 (class 0 OID 25974)
-- Dependencies: 273
-- Data for Name: partners_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partners_log (id, partner_name, partner_name_native, biz_reg_number, email_id, phone_code, phone_number, settlement_currency_code, website, country_code, prefered_language_code, biz_license_type, city, street_address, postal_code, business_model, partner_type, contract_date, contract_expiry_date, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4040 (class 0 OID 25988)
-- Dependencies: 275
-- Data for Name: partners_pocs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partners_pocs (id, partner_id, full_name, full_name_native, phone_code, phone_number, email_id, roles, country, city, address1, postal_code, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4041 (class 0 OID 26004)
-- Dependencies: 276
-- Data for Name: partners_pocs_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partners_pocs_log (poc_id, partner_id, full_name, full_name_native, phone_code, phone_number, email_id, roles, country, city, address1, postal_code, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4068 (class 0 OID 26326)
-- Dependencies: 303
-- Data for Name: password_reset_requests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.password_reset_requests (id, user_id, token, token_expiration_timestamp, request_timestamp, request_ip_address, reset_timestamp, reset_ip_address, reset_method, reset_source, additional_info, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4069 (class 0 OID 26345)
-- Dependencies: 304
-- Data for Name: receivers_receipt_methods; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.receivers_receipt_methods (id, transaction_id, payment_method, wallet_id, bank_code, bank_branch, account_name, account_name_native, account_number, account_type, routing_number, swift_bic, payment_method_provider, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 3998 (class 0 OID 25551)
-- Dependencies: 233
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, role_id, role_name, role_description, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4043 (class 0 OID 26023)
-- Dependencies: 278
-- Data for Name: roles_claims; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles_claims (id, role_id, claim_type, claim_value, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4045 (class 0 OID 26045)
-- Dependencies: 280
-- Data for Name: roles_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles_log (id, role_id, user_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4000 (class 0 OID 25564)
-- Dependencies: 235
-- Data for Name: roles_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles_permissions (id, slug, display_name, main_group, sub_group, group_display_order, sub_group_display_order, permission_status, display_icon, menu_group_type, menu_category, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4071 (class 0 OID 26361)
-- Dependencies: 306
-- Data for Name: senders_payment_methods; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.senders_payment_methods (id, transaction_id, payment_method, wallet_id, bank_code, bank_branch, account_name, account_name_native, account_number, routing_number, swift_bic, payment_method_provider, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4047 (class 0 OID 26060)
-- Dependencies: 282
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (id, transaction_id, merchant_id, s_agent_id, r_agent_id, s_payment_method, r_receipt_method, invoice_number, invoice_path, s_country, r_country, collection_currency, collection_amount, settlement_currency, settlement_amount, exchange_rate, exchange_quote_id, service_charge, commission, wire_charges, txn_cost, txn_gain, txn_source, txn_category, txn_auth_code, txn_risk_score, promo_code, pass_through_info, txn_status, txn_purpose, txn_type, control_number, external_code, txn_hash, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, processing_date, processing_date_utc, posted_status, posted_reference, posted_date, posted_date_utc, updated_by, updated_date, updated_date_utc, approved_by, approved_date, approved_date_utc) FROM stdin;
\.


--
-- TOC entry 4048 (class 0 OID 26077)
-- Dependencies: 283
-- Data for Name: transactions_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions_log (id, transaction_id, merchant_id, s_agent_id, r_agent_id, s_payment_method, r_receipt_method, invoice_number, invoice_path, s_country, r_country, collection_currency, collection_amount, settlement_currency, settlement_amount, exchange_rate, exchange_quote_id, service_charge, commission, wire_charges, txn_cost, txn_gain, txn_source, txn_category, txn_auth_code, txn_risk_score, promo_code, pass_through_info, txn_status, txn_purpose, txn_type, control_number, external_code, txn_hash, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, remarks, status, is_active, entity_hash, processing_date, processing_date_utc, posted_status, posted_reference, posted_date, posted_date_utc, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4050 (class 0 OID 26096)
-- Dependencies: 285
-- Data for Name: transactions_receivers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions_receivers (id, transaction_id, beneficiary_id, merchant_id, full_name, full_name_native, country, state, city, address1, address2, zip_code, phone_code, phone_number, email_id, dob, nationality, biz_registration_no, legal_person_name, receiver_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4051 (class 0 OID 26122)
-- Dependencies: 286
-- Data for Name: transactions_receivers_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions_receivers_log (id, transaction_id, beneficiary_id, merchant_id, full_name, full_name_native, country, state, city, address1, address2, zip_code, phone_code, phone_number, email_id, dob, nationality, biz_registration_no, legal_person_name, receiver_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4053 (class 0 OID 26151)
-- Dependencies: 288
-- Data for Name: transactions_senders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions_senders (id, transaction_id, full_name, full_name_native, country, state, city, address1, address2, zip_code, phone_code, phone_number, email_id, dob, nationality, sender_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4054 (class 0 OID 26167)
-- Dependencies: 289
-- Data for Name: transactions_senders_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions_senders_log (id, transaction_id, full_name, full_name_native, country, state, city, address1, address2, zip_code, phone_code, phone_number, email_id, dob, nationality, sender_type, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4056 (class 0 OID 26186)
-- Dependencies: 291
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, user_id, username, email_id, user_type, password, txn_pwd, security_stamp, phone_code, phone_number, salutation, first_name, middle_name, last_name, full_name, full_name_native, gender, dob, country, address1, address2, profile_image, language_preference, notification_preference, source_id, source_type, association_id, association_type, session_timeout_period, pwd_warning_days, pwd_expiry_days, is_email_id_verified, is_force_pwd_change, is_2fa_enabled, access_failed_count, is_api_user, roles, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, last_login_date, last_login_date_utc, last_ip_address, last_pwd_changed_date, last_pwd_changed_date_utc, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
1	c8773e79-cecf-49a1-9227-c2f7903f8613	suraj1@yopmail.com	suraj1@yopmail.com	MERCHANT	$2a$12$XAPorRds6bc6pkXZ9zsIG.JHSEd07qPjjad3s/16qXLtoZZpvjODm	\N	\N	977	9869136764	Mr	Suraj	\N	Goud	Suraj Goud	\N	Male	1995-11-29	NP	Kathmandu	\N	\N	\N	\N	1	MERCHANT	\N	\N	\N	\N	\N	t	f	f	0	f	ROLE_MERCHANT	\N	ACTIVE	t	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	system	2023-09-29 14:58:38.823	2023-09-29 14:58:38.823+05:45	\N	\N	\N	\N	\N	\N
2	535457ca-a25a-41f5-9e16-dca67ee5bbc5	usuraj1@yopmail.com	usuraj1@yopmail.com	MERCHANT_USER	$2a$12$XAPorRds6bc6pkXZ9zsIG.JHSEd07qPjjad3s/16qXLtoZZpvjODm	\N	\N	977	9869136762	Mr	Usuraj	\N	Ugoud	Usuraj Ugoud	\N	Male	1995-11-29	NP	Kathmandu	\N	\N	\N	\N	1	MERCHANT	\N	\N	\N	\N	\N	t	f	f	0	f	ROLE_MERCHANT_USER	\N	ACTIVE	t	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	system	2023-09-29 14:58:38.823	2023-09-29 14:58:38.823+05:45	\N	\N	\N	\N	\N	\N
3	f5b11de3-aaa2-491b-b851-6167c7e9a3e6	1suraj1@yopmail.com	1suraj1@yopmail.com	MERCHANT_USER	$2a$12$XAPorRds6bc6pkXZ9zsIG.JHSEd07qPjjad3s/16qXLtoZZpvjODm	\N	\N	977	9869136712	Mr	Usuraj	\N	Ugoud	Usuraj Ugoud	\N	Male	1995-11-29	NP	Kathmandu	\N	\N	\N	\N	1	MERCHANT	\N	\N	\N	\N	\N	f	f	f	0	f	ROLE_MERCHANT_USER	\N	LOCK	t	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	system	2023-09-29 14:58:38.823	2023-09-29 14:58:38.823+05:45	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 4058 (class 0 OID 26208)
-- Dependencies: 293
-- Data for Name: users_activity_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_activity_log (id, user_id, activity_timestamp, activity_type, activity_description, activity_duration, related_entity_id, additional_info, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4060 (class 0 OID 26226)
-- Dependencies: 295
-- Data for Name: users_blocked_devices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_blocked_devices (device_id, user_id, device_type, device_name, ip_adress, mac_address, block_reason, device_location, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4061 (class 0 OID 26243)
-- Dependencies: 296
-- Data for Name: users_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_log (id, user_id, username, email_id, user_type, password, txn_pwd, security_stamp, phone_code, phone_number, salutation, first_name, middle_name, last_name, full_name, full_name_native, gender, dob, country, address1, address2, profile_image, language_preference, notification_preference, source_id, source_type, association_id, association_type, session_timeout_period, pwd_warning_days, pwd_expiry_days, is_email_id_verified, is_force_pwd_change, is_2fa_enabled, access_failed_count, is_api_user, roles, remarks, status, is_active, ext_map_id_1, ext_map_id_2, ext_map_id_3, ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, last_login_date, last_login_date_utc, last_ip_address, last_pwd_changed_date, last_pwd_changed_date_utc, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4063 (class 0 OID 26262)
-- Dependencies: 298
-- Data for Name: users_login_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_login_history (id, user_id, login_timestamp, login_status, ip_address, user_agent, session_id, login_method, location, device_type, browser_version, operating_system, login_duration, logout_timestamp, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4065 (class 0 OID 26281)
-- Dependencies: 300
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles (id, role_id, user_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc) FROM stdin;
\.


--
-- TOC entry 4066 (class 0 OID 26302)
-- Dependencies: 301
-- Data for Name: users_roles_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles_log (id, role_id, user_id, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc) FROM stdin;
\.


--
-- TOC entry 4002 (class 0 OID 25578)
-- Dependencies: 237
-- Data for Name: users_signup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_signup (id, user_signup_id, source_type, incorporation_country, contact_info, phone_code, phone_number, is_email_otp_verified, is_sms_otp_verified, ip_address, signup_source, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, updated_by, updated_date, updated_date_utc, association_type, contact_type) FROM stdin;
5	\N	MERCHANT	NP	someone105@gmail.com	\N	\N	f	f	\N	\N	\N	CREATED	f	\N	someone105@gmail.com	2023-10-01 16:52:38.854448	2023-10-01 11:07:38.854448+05:45	\N	\N	\N	BUSINESS	EMAIL
12	\N	0	NP	pramodparajuli@gmail.com	\N	\N	f	f	\N	\N	\N	CREATED	f	\N	pramodparajuli@gmail.com	2023-10-01 17:41:28.11471	2023-10-01 11:56:28.11471+05:45	\N	\N	\N	BUSINESS	EMAIL
13	\N	MERCHANT	NP	rajkabee@gmail.com	\N	\N	f	f	\N	\N	\N	CREATED	f	\N	rajkabee@gmail.com	2023-10-01 17:42:17.44592	2023-10-01 11:57:17.44592+05:45	\N	\N	\N	BUSINESS	EMAIL
\.


--
-- TOC entry 4114 (class 0 OID 0)
-- Dependencies: 238
-- Name: beneficiary_profiles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.beneficiary_profiles_id_seq', 1, false);


--
-- TOC entry 4115 (class 0 OID 0)
-- Dependencies: 216
-- Name: category_roots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_roots_id_seq', 792, true);


--
-- TOC entry 4116 (class 0 OID 0)
-- Dependencies: 241
-- Name: category_roots_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_roots_log_id_seq', 1, false);


--
-- TOC entry 4117 (class 0 OID 0)
-- Dependencies: 218
-- Name: documents_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.documents_id_seq', 1, false);


--
-- TOC entry 4118 (class 0 OID 0)
-- Dependencies: 220
-- Name: error_codes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.error_codes_id_seq', 1, false);


--
-- TOC entry 4119 (class 0 OID 0)
-- Dependencies: 243
-- Name: error_possible_actions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.error_possible_actions_id_seq', 1, false);


--
-- TOC entry 4120 (class 0 OID 0)
-- Dependencies: 245
-- Name: merchants_bank_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_bank_details_id_seq', 1, false);


--
-- TOC entry 4121 (class 0 OID 0)
-- Dependencies: 248
-- Name: merchants_directors_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_directors_details_id_seq', 1, false);


--
-- TOC entry 4122 (class 0 OID 0)
-- Dependencies: 251
-- Name: merchants_documents_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_documents_id_seq', 1, false);


--
-- TOC entry 4123 (class 0 OID 0)
-- Dependencies: 222
-- Name: merchants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_id_seq', 1, true);


--
-- TOC entry 4124 (class 0 OID 0)
-- Dependencies: 255
-- Name: merchants_owners_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_owners_details_id_seq', 1, false);


--
-- TOC entry 4125 (class 0 OID 0)
-- Dependencies: 258
-- Name: merchants_representative_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_representative_details_id_seq', 1, false);


--
-- TOC entry 4126 (class 0 OID 0)
-- Dependencies: 261
-- Name: merchants_service_preferences_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_service_preferences_id_seq', 1, false);


--
-- TOC entry 4127 (class 0 OID 0)
-- Dependencies: 264
-- Name: merchants_stockholders_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_stockholders_details_id_seq', 1, false);


--
-- TOC entry 4128 (class 0 OID 0)
-- Dependencies: 267
-- Name: merchants_wallets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchants_wallets_id_seq', 1, false);


--
-- TOC entry 4129 (class 0 OID 0)
-- Dependencies: 224
-- Name: message_queue_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.message_queue_id_seq', 5, true);


--
-- TOC entry 4130 (class 0 OID 0)
-- Dependencies: 226
-- Name: message_templates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.message_templates_id_seq', 1, false);


--
-- TOC entry 4131 (class 0 OID 0)
-- Dependencies: 228
-- Name: otp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.otp_id_seq', 1, false);


--
-- TOC entry 4132 (class 0 OID 0)
-- Dependencies: 270
-- Name: partners_bank_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.partners_bank_details_id_seq', 1, false);


--
-- TOC entry 4133 (class 0 OID 0)
-- Dependencies: 230
-- Name: partners_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.partners_id_seq', 1, false);


--
-- TOC entry 4134 (class 0 OID 0)
-- Dependencies: 274
-- Name: partners_pocs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.partners_pocs_id_seq', 1, false);


--
-- TOC entry 4135 (class 0 OID 0)
-- Dependencies: 302
-- Name: password_reset_requests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.password_reset_requests_id_seq', 1, false);


--
-- TOC entry 4136 (class 0 OID 0)
-- Dependencies: 277
-- Name: roles_claims_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_claims_id_seq', 1, false);


--
-- TOC entry 4137 (class 0 OID 0)
-- Dependencies: 232
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 1, false);


--
-- TOC entry 4138 (class 0 OID 0)
-- Dependencies: 279
-- Name: roles_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_log_id_seq', 1, false);


--
-- TOC entry 4139 (class 0 OID 0)
-- Dependencies: 234
-- Name: roles_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_permissions_id_seq', 1, false);


--
-- TOC entry 4140 (class 0 OID 0)
-- Dependencies: 305
-- Name: senders_payment_methods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.senders_payment_methods_id_seq', 1, false);


--
-- TOC entry 4141 (class 0 OID 0)
-- Dependencies: 281
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_id_seq', 1, false);


--
-- TOC entry 4142 (class 0 OID 0)
-- Dependencies: 284
-- Name: transactions_receivers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_receivers_id_seq', 1, false);


--
-- TOC entry 4143 (class 0 OID 0)
-- Dependencies: 287
-- Name: transactions_senders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_senders_id_seq', 1, false);


--
-- TOC entry 4144 (class 0 OID 0)
-- Dependencies: 292
-- Name: users_activity_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_activity_log_id_seq', 1, false);


--
-- TOC entry 4145 (class 0 OID 0)
-- Dependencies: 294
-- Name: users_blocked_devices_device_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_blocked_devices_device_id_seq', 1, false);


--
-- TOC entry 4146 (class 0 OID 0)
-- Dependencies: 290
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 3, true);


--
-- TOC entry 4147 (class 0 OID 0)
-- Dependencies: 297
-- Name: users_login_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_login_history_id_seq', 1, false);


--
-- TOC entry 4148 (class 0 OID 0)
-- Dependencies: 299
-- Name: users_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_roles_id_seq', 1, false);


--
-- TOC entry 4149 (class 0 OID 0)
-- Dependencies: 236
-- Name: users_signup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_signup_id_seq', 13, true);


--
-- TOC entry 3722 (class 2606 OID 25604)
-- Name: beneficiary_profiles beneficiary_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.beneficiary_profiles
    ADD CONSTRAINT beneficiary_profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 3692 (class 2606 OID 25448)
-- Name: category_roots category_roots_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_roots
    ADD CONSTRAINT category_roots_pkey PRIMARY KEY (id);


--
-- TOC entry 3694 (class 2606 OID 25461)
-- Name: documents documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (id);


--
-- TOC entry 3696 (class 2606 OID 25475)
-- Name: error_codes error_codes_error_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.error_codes
    ADD CONSTRAINT error_codes_error_code_key UNIQUE (error_code);


--
-- TOC entry 3698 (class 2606 OID 25473)
-- Name: error_codes error_codes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.error_codes
    ADD CONSTRAINT error_codes_pkey PRIMARY KEY (id);


--
-- TOC entry 3724 (class 2606 OID 25654)
-- Name: error_possible_actions error_possible_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.error_possible_actions
    ADD CONSTRAINT error_possible_actions_pkey PRIMARY KEY (id);


--
-- TOC entry 3726 (class 2606 OID 25673)
-- Name: merchants_bank_details merchants_bank_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_bank_details
    ADD CONSTRAINT merchants_bank_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3728 (class 2606 OID 25705)
-- Name: merchants_directors_details merchants_directors_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_directors_details
    ADD CONSTRAINT merchants_directors_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3730 (class 2606 OID 25736)
-- Name: merchants_documents merchants_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_documents
    ADD CONSTRAINT merchants_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 3700 (class 2606 OID 25495)
-- Name: merchants merchants_email_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants
    ADD CONSTRAINT merchants_email_id_key UNIQUE (email_id);


--
-- TOC entry 3732 (class 2606 OID 25785)
-- Name: merchants_owners_details merchants_owners_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_owners_details
    ADD CONSTRAINT merchants_owners_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3702 (class 2606 OID 25493)
-- Name: merchants merchants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants
    ADD CONSTRAINT merchants_pkey PRIMARY KEY (id);


--
-- TOC entry 3734 (class 2606 OID 25816)
-- Name: merchants_representative_details merchants_representative_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_representative_details
    ADD CONSTRAINT merchants_representative_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3736 (class 2606 OID 25847)
-- Name: merchants_service_preferences merchants_service_preferences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_service_preferences
    ADD CONSTRAINT merchants_service_preferences_pkey PRIMARY KEY (id);


--
-- TOC entry 3738 (class 2606 OID 25878)
-- Name: merchants_stockholders_details merchants_stockholders_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_stockholders_details
    ADD CONSTRAINT merchants_stockholders_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3740 (class 2606 OID 25911)
-- Name: merchants_wallets merchants_wallets_account_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets
    ADD CONSTRAINT merchants_wallets_account_number_key UNIQUE (account_number);


--
-- TOC entry 3742 (class 2606 OID 25909)
-- Name: merchants_wallets merchants_wallets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets
    ADD CONSTRAINT merchants_wallets_pkey PRIMARY KEY (id);


--
-- TOC entry 3744 (class 2606 OID 25913)
-- Name: merchants_wallets merchants_wallets_virtual_account_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets
    ADD CONSTRAINT merchants_wallets_virtual_account_key UNIQUE (virtual_account);


--
-- TOC entry 3704 (class 2606 OID 25508)
-- Name: message_queue message_queue_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_queue
    ADD CONSTRAINT message_queue_pkey PRIMARY KEY (id);


--
-- TOC entry 3706 (class 2606 OID 25521)
-- Name: message_templates message_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_templates
    ADD CONSTRAINT message_templates_pkey PRIMARY KEY (id);


--
-- TOC entry 3708 (class 2606 OID 25534)
-- Name: otp otp_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.otp
    ADD CONSTRAINT otp_pkey PRIMARY KEY (id);


--
-- TOC entry 3746 (class 2606 OID 25950)
-- Name: partners_bank_details partners_bank_details_bank_account_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_bank_details
    ADD CONSTRAINT partners_bank_details_bank_account_number_key UNIQUE (bank_account_number);


--
-- TOC entry 3748 (class 2606 OID 25948)
-- Name: partners_bank_details partners_bank_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_bank_details
    ADD CONSTRAINT partners_bank_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3710 (class 2606 OID 25549)
-- Name: partners partners_email_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners
    ADD CONSTRAINT partners_email_id_key UNIQUE (email_id);


--
-- TOC entry 3712 (class 2606 OID 25547)
-- Name: partners partners_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners
    ADD CONSTRAINT partners_pkey PRIMARY KEY (id);


--
-- TOC entry 3750 (class 2606 OID 25998)
-- Name: partners_pocs partners_pocs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_pocs
    ADD CONSTRAINT partners_pocs_pkey PRIMARY KEY (id);


--
-- TOC entry 3774 (class 2606 OID 26337)
-- Name: password_reset_requests password_reset_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_requests
    ADD CONSTRAINT password_reset_requests_pkey PRIMARY KEY (id);


--
-- TOC entry 3776 (class 2606 OID 26339)
-- Name: password_reset_requests password_reset_requests_token_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_requests
    ADD CONSTRAINT password_reset_requests_token_key UNIQUE (token);


--
-- TOC entry 3778 (class 2606 OID 26354)
-- Name: receivers_receipt_methods receivers_receipt_methods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receivers_receipt_methods
    ADD CONSTRAINT receivers_receipt_methods_pkey PRIMARY KEY (id);


--
-- TOC entry 3752 (class 2606 OID 26033)
-- Name: roles_claims roles_claims_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_claims
    ADD CONSTRAINT roles_claims_pkey PRIMARY KEY (id);


--
-- TOC entry 3716 (class 2606 OID 25574)
-- Name: roles_permissions roles_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_permissions
    ADD CONSTRAINT roles_permissions_pkey PRIMARY KEY (id);


--
-- TOC entry 3718 (class 2606 OID 25576)
-- Name: roles_permissions roles_permissions_slug_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_permissions
    ADD CONSTRAINT roles_permissions_slug_key UNIQUE (slug);


--
-- TOC entry 3714 (class 2606 OID 25562)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3780 (class 2606 OID 26371)
-- Name: senders_payment_methods senders_payment_methods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.senders_payment_methods
    ADD CONSTRAINT senders_payment_methods_pkey PRIMARY KEY (id);


--
-- TOC entry 3754 (class 2606 OID 26071)
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);


--
-- TOC entry 3756 (class 2606 OID 26106)
-- Name: transactions_receivers transactions_receivers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers
    ADD CONSTRAINT transactions_receivers_pkey PRIMARY KEY (id);


--
-- TOC entry 3758 (class 2606 OID 26161)
-- Name: transactions_senders transactions_senders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_senders
    ADD CONSTRAINT transactions_senders_pkey PRIMARY KEY (id);


--
-- TOC entry 3766 (class 2606 OID 26219)
-- Name: users_activity_log users_activity_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_activity_log
    ADD CONSTRAINT users_activity_log_pkey PRIMARY KEY (id);


--
-- TOC entry 3768 (class 2606 OID 26237)
-- Name: users_blocked_devices users_blocked_devices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_blocked_devices
    ADD CONSTRAINT users_blocked_devices_pkey PRIMARY KEY (device_id);


--
-- TOC entry 3760 (class 2606 OID 26204)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (user_id);


--
-- TOC entry 3770 (class 2606 OID 26274)
-- Name: users_login_history users_login_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_login_history
    ADD CONSTRAINT users_login_history_pkey PRIMARY KEY (id);


--
-- TOC entry 3762 (class 2606 OID 26202)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3772 (class 2606 OID 26291)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3720 (class 2606 OID 25591)
-- Name: users_signup users_signup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_signup
    ADD CONSTRAINT users_signup_pkey PRIMARY KEY (id);


--
-- TOC entry 3764 (class 2606 OID 26206)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 3782 (class 2606 OID 25618)
-- Name: beneficiary_profiles_log beneficiary_profiles_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.beneficiary_profiles_log
    ADD CONSTRAINT beneficiary_profiles_log_id_fkey FOREIGN KEY (id) REFERENCES public.beneficiary_profiles(id);


--
-- TOC entry 3783 (class 2606 OID 25623)
-- Name: beneficiary_profiles_log beneficiary_profiles_log_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.beneficiary_profiles_log
    ADD CONSTRAINT beneficiary_profiles_log_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3781 (class 2606 OID 25605)
-- Name: beneficiary_profiles beneficiary_profiles_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.beneficiary_profiles
    ADD CONSTRAINT beneficiary_profiles_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL;


--
-- TOC entry 3784 (class 2606 OID 25638)
-- Name: category_roots_log category_roots_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_roots_log
    ADD CONSTRAINT category_roots_log_id_fkey FOREIGN KEY (id) REFERENCES public.category_roots(id);


--
-- TOC entry 3785 (class 2606 OID 25655)
-- Name: error_possible_actions error_possible_actions_error_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.error_possible_actions
    ADD CONSTRAINT error_possible_actions_error_code_fkey FOREIGN KEY (error_code) REFERENCES public.error_codes(error_code);


--
-- TOC entry 3787 (class 2606 OID 25688)
-- Name: merchants_bank_details_log merchants_bank_details_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_bank_details_log
    ADD CONSTRAINT merchants_bank_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_bank_details(id);


--
-- TOC entry 3786 (class 2606 OID 25674)
-- Name: merchants_bank_details merchants_bank_details_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_bank_details
    ADD CONSTRAINT merchants_bank_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3789 (class 2606 OID 25719)
-- Name: merchants_directors_details_log merchants_directors_details_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_directors_details_log
    ADD CONSTRAINT merchants_directors_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_directors_details(id);


--
-- TOC entry 3788 (class 2606 OID 25706)
-- Name: merchants_directors_details merchants_directors_details_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_directors_details
    ADD CONSTRAINT merchants_directors_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3791 (class 2606 OID 25750)
-- Name: merchants_documents_log merchants_documents_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_documents_log
    ADD CONSTRAINT merchants_documents_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_documents(id);


--
-- TOC entry 3790 (class 2606 OID 25737)
-- Name: merchants_documents merchants_documents_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_documents
    ADD CONSTRAINT merchants_documents_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3792 (class 2606 OID 25768)
-- Name: merchants_log merchants_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_log
    ADD CONSTRAINT merchants_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants(id);


--
-- TOC entry 3794 (class 2606 OID 25799)
-- Name: merchants_owners_details_log merchants_owners_details_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_owners_details_log
    ADD CONSTRAINT merchants_owners_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_owners_details(id);


--
-- TOC entry 3793 (class 2606 OID 25786)
-- Name: merchants_owners_details merchants_owners_details_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_owners_details
    ADD CONSTRAINT merchants_owners_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3796 (class 2606 OID 25830)
-- Name: merchants_representative_details_log merchants_representative_details_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_representative_details_log
    ADD CONSTRAINT merchants_representative_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_representative_details(id);


--
-- TOC entry 3795 (class 2606 OID 25817)
-- Name: merchants_representative_details merchants_representative_details_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_representative_details
    ADD CONSTRAINT merchants_representative_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3798 (class 2606 OID 25861)
-- Name: merchants_service_preferences_log merchants_service_preferences_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_service_preferences_log
    ADD CONSTRAINT merchants_service_preferences_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_service_preferences(id);


--
-- TOC entry 3797 (class 2606 OID 25848)
-- Name: merchants_service_preferences merchants_service_preferences_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_service_preferences
    ADD CONSTRAINT merchants_service_preferences_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3800 (class 2606 OID 25892)
-- Name: merchants_stockholders_details_log merchants_stockholders_details_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_stockholders_details_log
    ADD CONSTRAINT merchants_stockholders_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_stockholders_details(id);


--
-- TOC entry 3799 (class 2606 OID 25879)
-- Name: merchants_stockholders_details merchants_stockholders_details_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_stockholders_details
    ADD CONSTRAINT merchants_stockholders_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3802 (class 2606 OID 25927)
-- Name: merchants_wallets_log merchants_wallets_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets_log
    ADD CONSTRAINT merchants_wallets_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_wallets(id);


--
-- TOC entry 3803 (class 2606 OID 25932)
-- Name: merchants_wallets_log merchants_wallets_log_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets_log
    ADD CONSTRAINT merchants_wallets_log_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3801 (class 2606 OID 25914)
-- Name: merchants_wallets merchants_wallets_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchants_wallets
    ADD CONSTRAINT merchants_wallets_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id);


--
-- TOC entry 3805 (class 2606 OID 25964)
-- Name: partners_bank_details_log partners_bank_details_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_bank_details_log
    ADD CONSTRAINT partners_bank_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.partners_bank_details(id);


--
-- TOC entry 3806 (class 2606 OID 25969)
-- Name: partners_bank_details_log partners_bank_details_log_partner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_bank_details_log
    ADD CONSTRAINT partners_bank_details_log_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id);


--
-- TOC entry 3804 (class 2606 OID 25951)
-- Name: partners_bank_details partners_bank_details_partner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_bank_details
    ADD CONSTRAINT partners_bank_details_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id);


--
-- TOC entry 3807 (class 2606 OID 25982)
-- Name: partners_log partners_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_log
    ADD CONSTRAINT partners_log_id_fkey FOREIGN KEY (id) REFERENCES public.partners(id);


--
-- TOC entry 3809 (class 2606 OID 26012)
-- Name: partners_pocs_log partners_pocs_log_partner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_pocs_log
    ADD CONSTRAINT partners_pocs_log_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id);


--
-- TOC entry 3810 (class 2606 OID 26017)
-- Name: partners_pocs_log partners_pocs_log_poc_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_pocs_log
    ADD CONSTRAINT partners_pocs_log_poc_id_fkey FOREIGN KEY (poc_id) REFERENCES public.partners_pocs(id);


--
-- TOC entry 3808 (class 2606 OID 25999)
-- Name: partners_pocs partners_pocs_partner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partners_pocs
    ADD CONSTRAINT partners_pocs_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id);


--
-- TOC entry 3836 (class 2606 OID 26340)
-- Name: password_reset_requests password_reset_requests_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.password_reset_requests
    ADD CONSTRAINT password_reset_requests_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3837 (class 2606 OID 26355)
-- Name: receivers_receipt_methods receivers_receipt_methods_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receivers_receipt_methods
    ADD CONSTRAINT receivers_receipt_methods_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL;


--
-- TOC entry 3811 (class 2606 OID 26034)
-- Name: roles_claims roles_claims_claim_value_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_claims
    ADD CONSTRAINT roles_claims_claim_value_fkey FOREIGN KEY (claim_value) REFERENCES public.roles_permissions(id);


--
-- TOC entry 3812 (class 2606 OID 26039)
-- Name: roles_claims roles_claims_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_claims
    ADD CONSTRAINT roles_claims_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3813 (class 2606 OID 26054)
-- Name: roles_log roles_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_log
    ADD CONSTRAINT roles_log_id_fkey FOREIGN KEY (id) REFERENCES public.roles(id);


--
-- TOC entry 3838 (class 2606 OID 26372)
-- Name: senders_payment_methods senders_payment_methods_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.senders_payment_methods
    ADD CONSTRAINT senders_payment_methods_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL;


--
-- TOC entry 3815 (class 2606 OID 26085)
-- Name: transactions_log transactions_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_log
    ADD CONSTRAINT transactions_log_id_fkey FOREIGN KEY (id) REFERENCES public.transactions(id);


--
-- TOC entry 3814 (class 2606 OID 26072)
-- Name: transactions transactions_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL;


--
-- TOC entry 3816 (class 2606 OID 26090)
-- Name: transactions_log transactions_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_log
    ADD CONSTRAINT transactions_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL;


--
-- TOC entry 3817 (class 2606 OID 26107)
-- Name: transactions_receivers transactions_receivers_beneficiary_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers
    ADD CONSTRAINT transactions_receivers_beneficiary_id_fkey FOREIGN KEY (beneficiary_id) REFERENCES public.beneficiary_profiles(id) ON DELETE SET NULL;


--
-- TOC entry 3820 (class 2606 OID 26130)
-- Name: transactions_receivers_log transactions_receivers_beneficiary_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers_log
    ADD CONSTRAINT transactions_receivers_beneficiary_id_fkey FOREIGN KEY (beneficiary_id) REFERENCES public.beneficiary_profiles(id) ON DELETE SET NULL;


--
-- TOC entry 3821 (class 2606 OID 26135)
-- Name: transactions_receivers_log transactions_receivers_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers_log
    ADD CONSTRAINT transactions_receivers_log_id_fkey FOREIGN KEY (id) REFERENCES public.transactions_receivers(id);


--
-- TOC entry 3818 (class 2606 OID 26112)
-- Name: transactions_receivers transactions_receivers_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers
    ADD CONSTRAINT transactions_receivers_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL;


--
-- TOC entry 3822 (class 2606 OID 26140)
-- Name: transactions_receivers_log transactions_receivers_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers_log
    ADD CONSTRAINT transactions_receivers_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL;


--
-- TOC entry 3819 (class 2606 OID 26117)
-- Name: transactions_receivers transactions_receivers_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers
    ADD CONSTRAINT transactions_receivers_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id);


--
-- TOC entry 3823 (class 2606 OID 26145)
-- Name: transactions_receivers_log transactions_receivers_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_receivers_log
    ADD CONSTRAINT transactions_receivers_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id);


--
-- TOC entry 3825 (class 2606 OID 26175)
-- Name: transactions_senders_log transactions_senders_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_senders_log
    ADD CONSTRAINT transactions_senders_log_id_fkey FOREIGN KEY (id) REFERENCES public.transactions_senders(id);


--
-- TOC entry 3824 (class 2606 OID 26162)
-- Name: transactions_senders transactions_senders_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_senders
    ADD CONSTRAINT transactions_senders_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL;


--
-- TOC entry 3826 (class 2606 OID 26180)
-- Name: transactions_senders_log transactions_senders_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions_senders_log
    ADD CONSTRAINT transactions_senders_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL;


--
-- TOC entry 3827 (class 2606 OID 26220)
-- Name: users_activity_log users_activity_log_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_activity_log
    ADD CONSTRAINT users_activity_log_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3828 (class 2606 OID 26238)
-- Name: users_blocked_devices users_blocked_devices_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_blocked_devices
    ADD CONSTRAINT users_blocked_devices_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3829 (class 2606 OID 26256)
-- Name: users_log users_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_log
    ADD CONSTRAINT users_log_id_fkey FOREIGN KEY (id) REFERENCES public.users(id);


--
-- TOC entry 3830 (class 2606 OID 26275)
-- Name: users_login_history users_login_history_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_login_history
    ADD CONSTRAINT users_login_history_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3833 (class 2606 OID 26310)
-- Name: users_roles_log users_roles_log_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles_log
    ADD CONSTRAINT users_roles_log_id_fkey FOREIGN KEY (id) REFERENCES public.users_roles(id);


--
-- TOC entry 3834 (class 2606 OID 26315)
-- Name: users_roles_log users_roles_log_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles_log
    ADD CONSTRAINT users_roles_log_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3835 (class 2606 OID 26320)
-- Name: users_roles_log users_roles_log_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles_log
    ADD CONSTRAINT users_roles_log_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3831 (class 2606 OID 26292)
-- Name: users_roles users_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3832 (class 2606 OID 26297)
-- Name: users_roles users_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4077 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2023-10-01 18:11:14

--
-- PostgreSQL database dump complete
--

