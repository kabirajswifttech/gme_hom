-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

CREATE TYPE entity_hash_set as (
	id int,
	hash text
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Drop table

-- DROP TABLE public.category_roots;

CREATE TABLE public.category_roots (
	id bigserial NOT NULL,
	category_root_id uuid NULL DEFAULT uuid_generate_v4(),
	category_type text NULL,
	sub_type text NULL,
	category_code text NULL,
	description text NULL,
	parent_type text NULL,
	parent_code text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,	
	CONSTRAINT category_roots_pkey PRIMARY KEY (id)
);


-- public.documents definition

-- Drop table

-- DROP TABLE public.documents;

CREATE TABLE public.documents (
	id bigserial NOT NULL,
	doc_id uuid NULL DEFAULT uuid_generate_v4(),
	doc_type text NULL,
	sub_type text NULL,
	source_type text NULL,
	source_id text NULL,
	association_type text NULL,
	association_id text NULL,
	doc_path text NULL,
	doc_name text NULL,
	doc_alias text NULL,
	doc_id_number text NULL,
	issue_date date NULL,
	start_date date NULL,
	expiry_date date NULL,
	issuing_country text NULL,
	issuing_place text NULL,
	issuing_authority text NULL,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	CONSTRAINT documents_pkey PRIMARY KEY (id)
);


-- public.error_codes definition

-- Drop table

-- DROP TABLE public.error_codes;

CREATE TABLE public.error_codes (
	id bigserial NOT NULL,
	error_code text NULL,
	message text NULL,
	description text NULL,
	module_id text NULL,	
	lang text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT error_codes_error_code_key UNIQUE (error_code),
	CONSTRAINT error_codes_pkey PRIMARY KEY (id)
);


-- public.merchants definition

-- Drop table

-- DROP TABLE public.merchants;

CREATE TABLE public.merchants (
	id bigserial NOT NULL,
	merchant_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_type text NULL,
	email_id text NULL,
	phone_code text NULL,
	phone_number text NULL,
	incorporation_country text NULL,
	business_name text NULL,
	business_name_native text NULL,
	business_type text NULL,
	industry_type text NULL,
	product_type text NULL,
	business_nature text NULL,
	incorporation_date date NULL,
	bizz_reg_no text NULL,
	corp_reg_no text NULL,
	business_profile text NULL,
	postal_code text NULL,
	address1 text NULL,
	address2 text NULL,
	city text NULL,
	website text NULL,
	currency_preference text NULL,
	approx_txn_monthly_volume int4 NULL,
	approx_txn_yearly_volume int4 NULL,
	kyc_status text NULL,
	kyc_remarks text NULL,
	rba_status text NULL,
	rba_remarks text NULL,
	compliance_status text NULL,
	compliance_remarks text NULL,
	doc_path text NULL,
	notification_method text NULL,
	preferred_date_format text NULL,
	preferred_time_zone text NULL,
	security_stamp uuid NULL DEFAULT uuid_generate_v4(),
	terms_conditions_accepted bool not null DEFAULT false,
	privacy_policy_accepted bool not null DEFAULT false,
	pricing_policy_accepted bool not null DEFAULT false,
	marketing_email_subscription bool not null DEFAULT false,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	CONSTRAINT merchants_email_id_key UNIQUE (email_id),
	CONSTRAINT merchants_pkey PRIMARY KEY (id)
);


-- public.message_queue definition

-- Drop table

-- DROP TABLE public.message_queue;

CREATE TABLE public.message_queue (
	id bigserial NOT NULL,
	message_queue_id uuid NULL DEFAULT uuid_generate_v4(),
	message_type text NULL,
	priority text NULL,
	contact_info text NULL,
	cc text NULL,
	bcc text NULL,
	"content" text NULL,
	subject text NULL,	
	schedule_time timestamp NULL,
	schedule_time_utc timestamp NULL,
	reference text NULL,
	association_id text NULL,
	association_type text NULL,
	purpose_id text NULL,
	purpose_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT message_queue_pkey PRIMARY KEY (id)
);


-- public.message_templates definition

-- Drop table

-- DROP TABLE public.message_templates;

CREATE TABLE public.message_templates (
	id bigserial NOT NULL,
	message_tempate_id uuid NULL DEFAULT uuid_generate_v4(),
	template_type text NOT NULL,
	purpose text NOT NULL,
	"template" text NOT NULL,
	description text NULL,	
	valid_from_date timestamp NULL,
	valid_from_date_utc timestamp NULL,
	valid_to_date timestamp NULL,
	valid_to_date_utc timestamp NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	subject text NULL,
	CONSTRAINT message_templates_pkey PRIMARY KEY (id)
);


-- public.otp definition

-- Drop table

-- DROP TABLE public.otp;

CREATE TABLE public.otp (
	id bigserial NOT NULL,
	otp_id uuid NULL DEFAULT uuid_generate_v4(),
	otp_type text NULL,
	purpose text NULL,
	contact_info text NULL,
	otp_code text NULL,	
	otp_expire_time timestamp NULL,
	otp_expire_time_utc timestamp NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT otp_pkey PRIMARY KEY (id)
);


-- public.partners definition

-- Drop table

-- DROP TABLE public.partners;

CREATE TABLE public.partners (
	id bigserial NOT NULL,
	partner_id uuid NULL DEFAULT uuid_generate_v4(),
	partner_name text NULL,
	partner_name_native text NULL,
	biz_reg_number text NULL,
	email_id text NULL,
	phone_code text NULL,
	phone_number text NULL,
	settlement_currency_code text NULL,
	website text NULL,
	country_code text NULL,
	prefered_language_code text NULL,
	biz_license_type text NULL,
	city text NULL,
	street_address text NULL,
	postal_code text NULL,
	business_model text NULL,	
	partner_type text NULL,
	contract_date date NULL,
	contract_expiry_date date NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	CONSTRAINT partners_email_id_key UNIQUE (email_id),
	CONSTRAINT partners_pkey PRIMARY KEY (id)
);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id bigserial NOT NULL,
	role_id uuid NULL DEFAULT uuid_generate_v4(),
	role_name text NULL,
	role_description text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- public.roles_permissions definition

-- Drop table

-- DROP TABLE public.roles_permissions;

CREATE TABLE public.roles_permissions (
	id bigserial NOT NULL,
	slug text NULL,
	display_name text NULL,
	main_group text NULL,
	sub_group text NULL,
	group_display_order int4 NULL,
	sub_group_display_order int4 NULL,
	permission_status text NULL,
	display_icon text NULL,
	menu_group_type text NULL,
	menu_category text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT roles_permissions_pkey PRIMARY KEY (id),
	CONSTRAINT roles_permissions_slug_key UNIQUE (slug)
);


-- public.users_signup definition

-- Drop table

-- DROP TABLE public.users_signup;

CREATE TABLE public.users_signup (
	id bigserial NOT NULL,
	user_signup_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_type text NULL,
	incorporation_country text NULL,
	email_id text NULL,
	phone_code text NULL,
	phone_number text NULL,
	is_email_otp_verified bool not null DEFAULT false,
	is_sms_otp_verified bool not null DEFAULT false,
	ip_address text NULL,
	signup_source text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT users_signup_pkey PRIMARY KEY (id)
);


-- public.beneficiary_profiles definition

-- Drop table

-- DROP TABLE public.beneficiary_profiles;

CREATE TABLE public.beneficiary_profiles (
	id bigserial NOT NULL,
	beneficiary_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NULL,
	full_name text NULL,
	full_name_native text NULL,
	country text NULL,
	state text NULL,
	city text NULL,
	address1 text NULL,
	address2 text NULL,
	zip_code text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	nationality text NULL,
	biz_registration_no text NULL,
	legal_person_name text NULL,
	beneficiary_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT beneficiary_profiles_pkey PRIMARY KEY (id),
	CONSTRAINT beneficiary_profiles_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL
);


-- public.beneficiary_profiles_log definition

-- Drop table

-- DROP TABLE public.beneficiary_profiles_log;

CREATE TABLE public.beneficiary_profiles_log (
	id int8 NULL,
	merchant_id int8 NULL,
	full_name text NULL,
	full_name_native text NULL,
	country text NULL,
	state text NULL,
	city text NULL,
	address1 text NULL,
	address2 text NULL,
	zip_code text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	nationality text NULL,
	biz_registration_no text NULL,
	legal_person_name text NULL,
	beneficiary_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT beneficiary_profiles_log_id_fkey FOREIGN KEY (id) REFERENCES public.beneficiary_profiles(id),
	CONSTRAINT beneficiary_profiles_log_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.category_roots_log definition

-- Drop table

-- DROP TABLE public.category_roots_log;

CREATE TABLE public.category_roots_log (
	id bigserial NOT NULL,
	category_root_id text NULL,
	category_type text NULL,
	sub_type text NULL,
	category_code text NULL,
	description text NULL,
	parent_type text NULL,
	parent_code text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),	
	CONSTRAINT category_roots_log_id_fkey FOREIGN KEY (id) REFERENCES public.category_roots(id)
);


-- public.error_possible_actions definition

-- Drop table

-- DROP TABLE public.error_possible_actions;

CREATE TABLE public.error_possible_actions (
	id bigserial NOT NULL,
	error_code text NULL,
	possible_actions text NULL,	
	lang text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT error_possible_actions_pkey PRIMARY KEY (id),
	CONSTRAINT error_possible_actions_error_code_fkey FOREIGN KEY (error_code) REFERENCES public.error_codes(error_code)
);


-- public.merchants_bank_details definition

-- Drop table

-- DROP TABLE public.merchants_bank_details;

CREATE TABLE public.merchants_bank_details (
	id bigserial NOT NULL,
	merchant_bank_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	bank_id text NULL,
	account_name text NULL,
	account_number text NULL,
	swift_code text NULL,
	bic_code text NULL,
	ifsc_code text NULL,
	iban_cbu_card_number text NULL,
	is_verified bool not null DEFAULT false,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_bank_details_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_bank_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_bank_details_log definition

-- Drop table

-- DROP TABLE public.merchants_bank_details_log;

CREATE TABLE public.merchants_bank_details_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	bank_id text NULL,
	account_name text NULL,
	account_number text NULL,
	swift_code text NULL,
	bic_code text NULL,
	ifsc_code text NULL,
	iban_cbu_card_number text NULL,
	is_verified bool not null DEFAULT false,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_bank_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_bank_details(id)
);


-- public.merchants_directors_details definition

-- Drop table

-- DROP TABLE public.merchants_directors_details;

CREATE TABLE public.merchants_directors_details (
	id bigserial NOT NULL,
	merchant_director_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	nationality text NULL,
	dob date NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	residence_country text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_directors_details_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_directors_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_directors_details_log definition

-- Drop table

-- DROP TABLE public.merchants_directors_details_log;

CREATE TABLE public.merchants_directors_details_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	nationality text NULL,
	dob date NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	residence_country text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_directors_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_directors_details(id)
);


-- public.merchants_documents definition

-- Drop table

-- DROP TABLE public.merchants_documents;

CREATE TABLE public.merchants_documents (
	id bigserial NOT NULL,
	merchant_document_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	document_type text NULL,
	document_path text NULL,
	document_name text NULL,
	document_alias text NULL,
	document_id_number text NULL,
	issue_date date NULL,
	expiry_date date NULL,
	issue_place text NULL,
	issuing_authority text NULL,
	document_map_id text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	CONSTRAINT merchants_documents_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_documents_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_documents_log definition

-- Drop table

-- DROP TABLE public.merchants_documents_log;

CREATE TABLE public.merchants_documents_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	document_type text NULL,
	document_path text NULL,
	document_name text NULL,
	document_alias text NULL,
	document_id_number text NULL,
	issue_date date NULL,
	expiry_date date NULL,
	issue_place text NULL,
	issuing_authority text NULL,
	document_map_id text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_documents_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_documents(id)
);


-- public.merchants_log definition

-- Drop table

-- DROP TABLE public.merchants_log;

CREATE TABLE public.merchants_log (
	id int8 NOT NULL,
	merchant_type text NULL,
	email_id text NULL,
	phone_code text NULL,
	phone_number text NULL,
	incorporation_country text NULL,
	business_name text NULL,
	business_name_native text NULL,
	business_type text NULL,
	industry_type text NULL,
	product_type text NULL,
	business_nature text NULL,
	incorporation_date date NULL,
	bizz_reg_no text NULL,
	corp_reg_no text NULL,
	business_profile text NULL,
	postal_code text NULL,
	address1 text NULL,
	address2 text NULL,
	city text NULL,
	website text NULL,
	currency_preference text NULL,
	approx_txn_monthly_volume int4 NULL,
	approx_txn_yearly_volume int4 NULL,
	kyc_status text NULL,
	kyc_remarks text NULL,
	rba_status text NULL,
	rba_remarks text NULL,
	compliance_status text NULL,
	compliance_remarks text NULL,
	doc_path text NULL,
	notification_method text NULL,
	preferred_date_format text NULL,
	preferred_time_zone text NULL,
	security_stamp uuid NULL DEFAULT uuid_generate_v4(),
	terms_conditions_accepted bool not null DEFAULT false,
	privacy_policy_accepted bool not null DEFAULT false,
	pricing_policy_accepted bool not null DEFAULT false,
	marketing_email_subscription bool not null DEFAULT false,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants(id)
);


-- public.merchants_owners_details definition

-- Drop table

-- DROP TABLE public.merchants_owners_details;

CREATE TABLE public.merchants_owners_details (
	id bigserial NOT NULL,
	merchant_owener_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	"role" text NULL,
	nationality text NULL,
	residence_country text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_owners_details_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_owners_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_owners_details_log definition

-- Drop table

-- DROP TABLE public.merchants_owners_details_log;

CREATE TABLE public.merchants_owners_details_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	"role" text NULL,
	nationality text NULL,
	residence_country text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_owners_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_owners_details(id)
);


-- public.merchants_representative_details definition

-- Drop table

-- DROP TABLE public.merchants_representative_details;

CREATE TABLE public.merchants_representative_details (
	id bigserial NOT NULL,
	merchant_representative_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	first_name text NULL,
	middle_name text NULL,
	last_name text NULL,
	full_name text NULL,
	full_name_native text NULL,
	designation text NULL,
	nationality text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	residence_country text NULL,
	postal_code text NULL,
	address1 text NULL,
	address2 text NULL,
	city text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_representative_details_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_representative_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_representative_details_log definition

-- Drop table

-- DROP TABLE public.merchants_representative_details_log;

CREATE TABLE public.merchants_representative_details_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	first_name text NULL,
	middle_name text NULL,
	last_name text NULL,
	full_name text NULL,
	full_name_native text NULL,
	designation text NULL,
	nationality text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	residence_country text NULL,
	postal_code text NULL,
	address1 text NULL,
	address2 text NULL,
	city text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_representative_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_representative_details(id)
);


-- public.merchants_service_preferences definition

-- Drop table

-- DROP TABLE public.merchants_service_preferences;

CREATE TABLE public.merchants_service_preferences (
	id bigserial NOT NULL,
	merchant_service_preference_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	service_type text NULL,
	service_id text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_service_preferences_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_service_preferences_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_service_preferences_log definition

-- Drop table

-- DROP TABLE public.merchants_service_preferences_log;

CREATE TABLE public.merchants_service_preferences_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	service_type text NULL,
	service_id text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_service_preferences_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_service_preferences(id)
);


-- public.merchants_stockholders_details definition

-- Drop table

-- DROP TABLE public.merchants_stockholders_details;

CREATE TABLE public.merchants_stockholders_details (
	id bigserial NOT NULL,
	merchant_stockholder_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	nationality text NULL,
	dob date NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	residence_country text NULL,
	percentage_of_share float8 NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_stockholders_details_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_stockholders_details_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_stockholders_details_log definition

-- Drop table

-- DROP TABLE public.merchants_stockholders_details_log;

CREATE TABLE public.merchants_stockholders_details_log (
	id int8 NOT NULL,
	merchant_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	nationality text NULL,
	dob date NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	residence_country text NULL,
	percentage_of_share float8 NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_stockholders_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_stockholders_details(id)
);


-- public.merchants_wallets definition

-- Drop table

-- DROP TABLE public.merchants_wallets;

CREATE TABLE public.merchants_wallets (
	id bigserial NOT NULL,
	wallet_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NULL,
	wallet_type text NULL,
	account_number text NULL,
	virtual_account text NULL,
	currency_code text NULL,
	balance numeric(18, 2) NULL,
	risk_score int4 NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	last_transaction_date timestamp NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT merchants_wallets_account_number_key UNIQUE (account_number),
	CONSTRAINT merchants_wallets_pkey PRIMARY KEY (id),
	CONSTRAINT merchants_wallets_virtual_account_key UNIQUE (virtual_account),
	CONSTRAINT merchants_wallets_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.merchants_wallets_log definition

-- Drop table

-- DROP TABLE public.merchants_wallets_log;

CREATE TABLE public.merchants_wallets_log (
	id int8 NULL,
	merchant_id int8 NULL,
	wallet_type text NULL,
	account_number text NULL,
	virtual_account text NULL,
	currency_code text NULL,
	balance numeric(18, 2) NULL,
	risk_score int4 NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,	
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	last_transaction_date timestamp NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT merchants_wallets_log_id_fkey FOREIGN KEY (id) REFERENCES public.merchants_wallets(id),
	CONSTRAINT merchants_wallets_log_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id)
);


-- public.partners_bank_details definition

-- Drop table

-- DROP TABLE public.partners_bank_details;

CREATE TABLE public.partners_bank_details (
	id bigserial NOT NULL,
	partner_id int8 NULL,
	bank_code text NULL,
	bank_name text NULL,
	bank_account_number text NULL,
	bank_account_name text NULL,
	bank_bic text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT partners_bank_details_bank_account_number_key UNIQUE (bank_account_number),
	CONSTRAINT partners_bank_details_pkey PRIMARY KEY (id),
	CONSTRAINT partners_bank_details_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id)
);


-- public.partners_bank_details_log definition

-- Drop table

-- DROP TABLE public.partners_bank_details_log;

CREATE TABLE public.partners_bank_details_log (
	id int8 NOT NULL,
	partner_id int8 NOT NULL,
	bank_code text NULL,
	bank_name text NULL,
	bank_account_number text NULL,
	bank_account_name text NULL,
	bank_bic text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT partners_bank_details_log_id_fkey FOREIGN KEY (id) REFERENCES public.partners_bank_details(id),
	CONSTRAINT partners_bank_details_log_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id)
);


-- public.partners_log definition

-- Drop table

-- DROP TABLE public.partners_log;

CREATE TABLE public.partners_log (
	id int4 NULL,
	partner_name text NULL,
	partner_name_native text NULL,
	biz_reg_number text NULL,
	email_id text NULL,
	phone_code text NULL,
	phone_number text NULL,
	settlement_currency_code text NULL,
	website text NULL,
	country_code text NULL,
	prefered_language_code text NULL,
	biz_license_type text NULL,
	city text NULL,
	street_address text NULL,
	postal_code text NULL,
	business_model text NULL,	
	partner_type text NULL,
	contract_date date NULL,
	contract_expiry_date date NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT partners_log_id_fkey FOREIGN KEY (id) REFERENCES public.partners(id)
);


-- public.partners_pocs definition

-- Drop table

-- DROP TABLE public.partners_pocs;

CREATE TABLE public.partners_pocs (
	id bigserial NOT NULL,
	partner_id int8 NULL,
	full_name text NULL,
	full_name_native text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	roles text NULL,
	country text NULL,
	city text NULL,
	address1 text NULL,
	postal_code text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT partners_pocs_pkey PRIMARY KEY (id),
	CONSTRAINT partners_pocs_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id)
);


-- public.partners_pocs_log definition

-- Drop table

-- DROP TABLE public.partners_pocs_log;

CREATE TABLE public.partners_pocs_log (
	poc_id int8 NOT NULL,
	partner_id int8 NOT NULL,
	full_name text NULL,
	full_name_native text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	roles text NULL,
	country text NULL,
	city text NULL,
	address1 text NULL,
	postal_code text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT partners_pocs_log_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES public.partners(id),
	CONSTRAINT partners_pocs_log_poc_id_fkey FOREIGN KEY (poc_id) REFERENCES public.partners_pocs(id)
);


-- public.roles_claims definition

-- Drop table

-- DROP TABLE public.roles_claims;

CREATE TABLE public.roles_claims (
	id bigserial NOT NULL,
	role_id int4 NULL,
	claim_type text NULL,
	claim_value int4 NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT roles_claims_pkey PRIMARY KEY (id),
	CONSTRAINT roles_claims_claim_value_fkey FOREIGN KEY (claim_value) REFERENCES public.roles_permissions(id),
	CONSTRAINT roles_claims_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id)
);


-- public.roles_log definition

-- Drop table

-- DROP TABLE public.roles_log;

CREATE TABLE public.roles_log (
	id bigserial NOT NULL,
	role_id int8 NOT NULL,
	user_id int8 NOT NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT roles_log_id_fkey FOREIGN KEY (id) REFERENCES public.roles(id)
);


-- public.transactions definition

-- Drop table

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions (
	id bigserial NOT NULL,
	transaction_id uuid NULL DEFAULT uuid_generate_v4(),
	merchant_id int8 NULL,
	s_agent_id int8 NULL,
	r_agent_id int8 NULL,
	s_payment_method text NULL,
	r_receipt_method text NULL,
	invoice_number text NULL,
	invoice_path text NULL,
	s_country text NULL,
	r_country text NULL,
	collection_currency text NULL,
	collection_amount numeric(18, 2) NULL,
	settlement_currency text NULL,
	settlement_amount numeric(18, 2) NULL,
	exchange_rate numeric(18, 6) NULL,
	exchange_quote_id text NULL,
	service_charge numeric(18, 2) NULL,
	commission numeric(18, 2) NULL,
	wire_charges numeric(18, 2) NULL,
	txn_cost numeric(18, 2) NULL,
	txn_gain numeric(18, 2) NULL,
	txn_source text NULL,
	txn_category text NULL,
	txn_auth_code text NULL,
	txn_risk_score int4 NULL,
	promo_code text NULL,
	pass_through_info text NULL,
	txn_status text NULL,
	txn_purpose text NULL,	
	txn_type text NULL,
	control_number text NULL,
	external_code text NULL,	
	txn_hash text NULL,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,	
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	processing_date timestamp NULL,
	processing_date_utc timestamp NULL,
	posted_status text NULL,
	posted_reference text NULL,
	posted_date timestamp NULL,
	posted_date_utc timestamp NULL,
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (id),
	CONSTRAINT transactions_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL
);


-- public.transactions_log definition

-- Drop table

-- DROP TABLE public.transactions_log;

CREATE TABLE public.transactions_log (
	id int8 NOT NULL,
	transaction_id text,
	merchant_id int8 NULL,
	s_agent_id int8 NULL,
	r_agent_id int8 NULL,
	s_payment_method text NULL,
	r_receipt_method text NULL,
	invoice_number text NULL,
	invoice_path text NULL,
	s_country text NULL,
	r_country text NULL,
	collection_currency text NULL,
	collection_amount numeric(18, 2) NULL,
	settlement_currency text NULL,
	settlement_amount numeric(18, 2) NULL,
	exchange_rate numeric(18, 6) NULL,
	exchange_quote_id text NULL,
	service_charge numeric(18, 2) NULL,
	commission numeric(18, 2) NULL,
	wire_charges numeric(18, 2) NULL,
	txn_cost numeric(18, 2) NULL,
	txn_gain numeric(18, 2) NULL,
	txn_source text NULL,
	txn_category text NULL,
	txn_auth_code text NULL,
	txn_risk_score int4 NULL,
	promo_code text NULL,
	pass_through_info text NULL,
	txn_status text NULL,
	txn_purpose text NULL,	
	txn_type text NULL,
	control_number text NULL,
	external_code text NULL,	
	txn_hash text NULL,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	processing_date timestamp NULL,
	processing_date_utc timestamp NULL,
	posted_status text NULL,
	posted_reference text NULL,
	posted_date timestamp NULL,
	posted_date_utc timestamp NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT transactions_log_id_fkey FOREIGN KEY (id) REFERENCES public.transactions(id),
	CONSTRAINT transactions_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL
);


-- public.transactions_receivers definition

-- Drop table

-- DROP TABLE public.transactions_receivers;

CREATE TABLE public.transactions_receivers (
	id bigserial NOT NULL,
	transaction_id int8 NULL,
	beneficiary_id int8 NULL,
	merchant_id int8 NULL,
	full_name text NULL,
	full_name_native text NULL,
	country text NULL,
	state text NULL,
	city text NULL,
	address1 text NULL,
	address2 text NULL,
	zip_code text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	nationality text NULL,
	biz_registration_no text NULL,
	legal_person_name text NULL,
	receiver_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT transactions_receivers_pkey PRIMARY KEY (id),
	CONSTRAINT transactions_receivers_beneficiary_id_fkey FOREIGN KEY (beneficiary_id) REFERENCES public.beneficiary_profiles(id) ON DELETE SET NULL,
	CONSTRAINT transactions_receivers_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL,
	CONSTRAINT transactions_receivers_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id)
);


-- public.transactions_receivers_log definition

-- Drop table

-- DROP TABLE public.transactions_receivers_log;

CREATE TABLE public.transactions_receivers_log (
	id int8 NOT NULL,
	transaction_id int8 NULL,
	beneficiary_id int8 NULL,
	merchant_id int8 NULL,
	full_name text NULL,
	full_name_native text NULL,
	country text NULL,
	state text NULL,
	city text NULL,
	address1 text NULL,
	address2 text NULL,
	zip_code text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	nationality text NULL,
	biz_registration_no text NULL,
	legal_person_name text NULL,
	receiver_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT transactions_receivers_beneficiary_id_fkey FOREIGN KEY (beneficiary_id) REFERENCES public.beneficiary_profiles(id) ON DELETE SET NULL,
	CONSTRAINT transactions_receivers_log_id_fkey FOREIGN KEY (id) REFERENCES public.transactions_receivers(id),
	CONSTRAINT transactions_receivers_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchants(id) ON DELETE SET NULL,
	CONSTRAINT transactions_receivers_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id)
);


-- public.transactions_senders definition

-- Drop table

-- DROP TABLE public.transactions_senders;

CREATE TABLE public.transactions_senders (
	id bigserial NOT NULL,
	transaction_id int4 NULL,
	full_name text NULL,
	full_name_native text NULL,
	country text NULL,
	state text NULL,
	city text NULL,
	address1 text NULL,
	address2 text NULL,
	zip_code text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	nationality text NULL,
	sender_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT transactions_senders_pkey PRIMARY KEY (id),
	CONSTRAINT transactions_senders_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL
);


-- public.transactions_senders_log definition

-- Drop table

-- DROP TABLE public.transactions_senders_log;

CREATE TABLE public.transactions_senders_log (
	id int8 NOT NULL,
	transaction_id int4 NULL,
	full_name text NULL,
	full_name_native text NULL,
	country text NULL,
	state text NULL,
	city text NULL,
	address1 text NULL,
	address2 text NULL,
	zip_code text NULL,
	phone_code text NULL,
	phone_number text NULL,
	email_id text NULL,
	dob date NULL,
	nationality text NULL,
	sender_type text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT transactions_senders_log_id_fkey FOREIGN KEY (id) REFERENCES public.transactions_senders(id),
	CONSTRAINT transactions_senders_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	user_id uuid NULL DEFAULT uuid_generate_v4(),
	username text NULL,
	email_id text NULL,
	user_type text NULL,
	"password" text NULL,
	txn_pwd text NULL,
	security_stamp text NULL,
	phone_code text NULL,
	phone_number text NULL,
	salutation text NULL,
	first_name text NULL,
	middle_name text NULL,
	last_name text NULL,
	full_name text NULL,
	full_name_native text NULL,
	gender text NULL,
	dob date NULL,
	country text NULL,
	address1 text NULL,
	address2 text NULL,
	profile_image text NULL,
	language_preference text NULL,
	notification_preference text NULL,	
	source_id text null,
	source_type text null,
	association_id text NULL,
	association_type text NULL,	
	session_timeout_period int4 NULL,
	pwd_warning_days int4 NULL,
	pwd_expiry_days int4 NULL,
	is_email_id_verified bool NOT NULL DEFAULT false,
	is_force_pwd_change bool NOT NULL DEFAULT false,
	is_2fa_enabled bool NOT NULL DEFAULT false,
	access_failed_count int4 NULL DEFAULT 0,
	is_api_user bool NOT NULL DEFAULT false,
	roles text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool NOT NULL DEFAULT false,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	last_login_date timestamp NULL,
	last_login_date_utc timestamp NULL,
	last_ip_address text NULL,
	last_pwd_changed_date timestamp NULL,
	last_pwd_changed_date_utc timestamp NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT users_email_key UNIQUE (user_id),
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);


-- public.users_activity_log definition

-- Drop table

-- DROP TABLE public.users_activity_log;

CREATE TABLE public.users_activity_log (
	id bigserial NOT NULL,
	user_id int8 NULL,
	activity_timestamp timestamptz NULL,
	activity_type text NULL,
	activity_description text NULL,
	activity_duration interval NULL,
	related_entity_id text NULL,
	additional_info text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL DEFAULT 'system'::text,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),	
	CONSTRAINT users_activity_log_pkey PRIMARY KEY (id),
	CONSTRAINT users_activity_log_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.users_blocked_devices definition

-- Drop table

-- DROP TABLE public.users_blocked_devices;

CREATE TABLE public.users_blocked_devices (
	device_id bigserial NOT NULL,
	user_id int8 NULL,
	device_type text NULL,
	device_name text NULL,
	ip_adress text NULL,
	mac_address text NULL,
	block_reason text NULL,
	device_location text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL DEFAULT 'system'::text,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),	
	CONSTRAINT users_blocked_devices_pkey PRIMARY KEY (device_id),
	CONSTRAINT users_blocked_devices_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.users_log definition

-- Drop table

-- DROP TABLE public.users_log;

CREATE TABLE public.users_log (
	id int8 NULL,
	user_id text,
	username text NULL,
	email_id text NULL,
	user_type text NULL,
	"password" text NULL,
	txn_pwd text NULL,
	security_stamp text NULL,
	phone_code text NULL,
	phone_number text NULL,
	salutation text NULL,
	first_name text NULL,
	middle_name text NULL,
	last_name text NULL,
	full_name text NULL,
	full_name_native text NULL,
	gender text NULL,
	dob date NULL,
	country text NULL,
	address1 text NULL,
	address2 text NULL,
	profile_image text NULL,
	language_preference text NULL,
	notification_preference text NULL,	
	source_id text null,
	source_type text null,
	association_id text NULL,
	association_type text NULL,	
	session_timeout_period int4 NULL,
	pwd_warning_days int4 NULL,
	pwd_expiry_days int4 NULL,
	is_email_id_verified bool NOT NULL DEFAULT false,
	is_force_pwd_change bool NOT NULL DEFAULT false,
	is_2fa_enabled bool NOT NULL DEFAULT false,
	access_failed_count int4 NULL DEFAULT 0,
	is_api_user bool NOT NULL DEFAULT false,
	roles text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool NOT NULL DEFAULT false,
	ext_map_id_1 text NULL,
	ext_map_id_2 text NULL,
	ext_map_id_3 text NULL,
	ref_col_1 text NULL,
	ref_col_2 text NULL,
	ref_col_3 text NULL,
	ref_col_4 text NULL,
	ref_col_5 text NULL,
	last_login_date timestamp NULL,
	last_login_date_utc timestamp NULL,
	last_ip_address text NULL,
	last_pwd_changed_date timestamp NULL,
	last_pwd_changed_date_utc timestamp NULL,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT users_log_id_fkey FOREIGN KEY (id) REFERENCES public.users(id)
	
);


-- public.users_login_history definition

-- Drop table

-- DROP TABLE public.users_login_history;

CREATE TABLE public.users_login_history (
	id bigserial NOT NULL,
	user_id int8 NULL,
	login_timestamp timestamptz NULL,
	login_status bool not null DEFAULT false,
	ip_address text NULL,
	user_agent text NULL,
	session_id text NULL,
	login_method text NULL,
	"location" text NULL,
	device_type text NULL,
	browser_version text NULL,
	operating_system text NULL,
	login_duration interval NULL,
	logout_timestamp timestamptz NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL DEFAULT 'system'::text,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),	
	CONSTRAINT users_login_history_pkey PRIMARY KEY (id),
	CONSTRAINT users_login_history_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.users_roles definition

-- Drop table

-- DROP TABLE public.users_roles;

CREATE TABLE public.users_roles (
	id bigserial NOT NULL,
	role_id int8 NOT NULL,
	user_id int8 NOT NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	approved_by text NULL,
	approved_date timestamp NULL,
	approved_date_utc timestamp NULL,
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,
	CONSTRAINT users_roles_pkey PRIMARY KEY (id),
	CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id),
	CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.users_roles_log definition

-- Drop table

-- DROP TABLE public.users_roles_log;

CREATE TABLE public.users_roles_log (
	id int8 NOT NULL,
	role_id int8 NOT NULL,
	user_id int8 NOT NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT users_roles_log_id_fkey FOREIGN KEY (id) REFERENCES public.users_roles(id),
	CONSTRAINT users_roles_log_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id),
	CONSTRAINT users_roles_log_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.password_reset_requests definition

-- Drop table

-- DROP TABLE public.password_reset_requests;

CREATE TABLE public.password_reset_requests (
	id bigserial NOT NULL,
	user_id int8 NULL,
	"token" text NOT NULL,
	token_expiration_timestamp timestamptz NULL,
	request_timestamp timestamptz NULL DEFAULT CURRENT_TIMESTAMP,
	request_ip_address text NULL,
	reset_timestamp timestamptz NULL,
	reset_ip_address text NULL,
	reset_method text NULL,
	reset_source text NULL,
	additional_info text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	CONSTRAINT password_reset_requests_pkey PRIMARY KEY (id),
	CONSTRAINT password_reset_requests_token_key UNIQUE (token),
	CONSTRAINT password_reset_requests_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.receivers_receipt_methods definition

-- Drop table

-- DROP TABLE public.receivers_receipt_methods;

CREATE TABLE public.receivers_receipt_methods (
	id int8 NOT NULL,
	transaction_id int8 NULL,
	payment_method text NULL,
	wallet_id int8 NULL,
	bank_code text NULL,
	bank_branch text NULL,
	account_name text NULL,
	account_name_native text NULL,
	account_number text NULL,
	account_type text NULL,
	routing_number text NULL,
	swift_bic text NULL,
	payment_method_provider text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,	
	CONSTRAINT receivers_receipt_methods_pkey PRIMARY KEY (id),
	CONSTRAINT receivers_receipt_methods_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL
);


-- public.senders_payment_methods definition

-- Drop table

-- DROP TABLE public.senders_payment_methods;

CREATE TABLE public.senders_payment_methods (
	id bigserial NOT NULL,
	transaction_id int8 NULL,
	payment_method text NULL,
	wallet_id int8 NULL,
	bank_code text NULL,
	bank_branch text NULL,
	account_name text NULL,
	account_name_native text NULL,
	account_number text NULL,
	routing_number text NULL,
	swift_bic text NULL,
	payment_method_provider text NULL,
	remarks text NULL,
	status text NOT NULL,
	is_active bool not null DEFAULT false,
	entity_hash text NULL,
	created_by text NOT NULL,
	created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_date_utc timestamptz NULL DEFAULT (now() AT TIME ZONE 'UTC'::text),
	updated_by text NULL,
	updated_date timestamp NULL,
	updated_date_utc timestamp NULL,	
	CONSTRAINT senders_payment_methods_pkey PRIMARY KEY (id),
	CONSTRAINT senders_payment_methods_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transactions(id) ON DELETE SET NULL
);



CREATE OR REPLACE FUNCTION public.check_duplicate(_entity regclass, _hash text)
 RETURNS SETOF entity_hash_set
 LANGUAGE plpgsql
AS $function$
begin
	return QUERY execute 'select 1 as id, entity_hash as hash from ' || _entity || ' where entity_hash = ' || '''' || _hash || '''' ;
end;
$function$
;

CREATE OR REPLACE FUNCTION public.get_user_roles(_user_id integer, OUT result text)
 RETURNS text
 LANGUAGE plpgsql
AS $function$
begin
	select row_to_json(row) into result from (select * from user_roles where user_id = _user_id) row;
end;
$function$
;

CREATE OR REPLACE FUNCTION public.uuid_generate_v1()
 RETURNS uuid
 LANGUAGE c
 PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v1$function$
;

CREATE OR REPLACE FUNCTION public.uuid_generate_v1mc()
 RETURNS uuid
 LANGUAGE c
 PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v1mc$function$
;

CREATE OR REPLACE FUNCTION public.uuid_generate_v3(namespace uuid, name text)
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v3$function$
;

CREATE OR REPLACE FUNCTION public.uuid_generate_v4()
 RETURNS uuid
 LANGUAGE c
 PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v4$function$
;

CREATE OR REPLACE FUNCTION public.uuid_generate_v5(namespace uuid, name text)
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v5$function$
;

CREATE OR REPLACE FUNCTION public.uuid_nil()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_nil$function$
;

CREATE OR REPLACE FUNCTION public.uuid_ns_dns()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_dns$function$
;

CREATE OR REPLACE FUNCTION public.uuid_ns_oid()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_oid$function$
;

CREATE OR REPLACE FUNCTION public.uuid_ns_url()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_url$function$
;

CREATE OR REPLACE FUNCTION public.uuid_ns_x500()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_x500$function$
;