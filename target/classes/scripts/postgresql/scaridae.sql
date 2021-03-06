
/* company */
CREATE SEQUENCE public.company_id_seq ;
CREATE TABLE public.company (
                id BIGINT NOT NULL DEFAULT nextval('public.company_id_seq'),
                name VARCHAR(50) NOT NULL,
                siret VARCHAR(50) NOT NULL,
                activity VARCHAR(50),
				siren VARCHAR(50),
				phone VARCHAR(50),
				legal_form VARCHAR(50),
				capital BIGINT,
				creation_time TIMESTAMP,
				serialized_properties TEXT,
				company_status_id BIGINT,
				address_id BIGINT,
                CONSTRAINT company_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.company_id_seq OWNED BY public.company.id;

CREATE UNIQUE INDEX company_name_idx
 ON public.company
 ( name );


/* company_status */

CREATE SEQUENCE public.company_status_id_seq;
CREATE TABLE public.company_status (
                id BIGINT NOT NULL DEFAULT nextval('public.company_status_id_seq'),
                name VARCHAR(50) NOT NULL,
				description VARCHAR(250),
                serialized_properties TEXT,
                CONSTRAINT company_status_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.company_status_id_seq OWNED BY public.company_status.id;

/* service*/
CREATE SEQUENCE public.service_id_seq;
CREATE TABLE public.service (
                id BIGINT NOT NULL DEFAULT nextval('public.service_id_seq'),
                name VARCHAR(50) NOT NULL,
				creation_time TIMESTAMP,
				activity VARCHAR(50),
				description VARCHAR(250),
                serialized_properties TEXT,
				company_id BIGINT,
                CONSTRAINT service_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.service_id_seq OWNED BY public.service.id;

/* address*/
CREATE SEQUENCE public.address_id_seq;
CREATE TABLE public.address (
                id BIGINT NOT NULL DEFAULT nextval('public.address_id_seq'),
                city VARCHAR(50),
                code VARCHAR(50),
                country VARCHAR(50),
				street VARCHAR(50),
	            CONSTRAINT address_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.address_id_seq OWNED BY public.address.id;

/* user_account_group */
CREATE SEQUENCE public.user_account_group_id_seq;
CREATE TABLE public.user_account_group (
                id BIGINT NOT NULL DEFAULT nextval('public.user_account_group_id_seq'),
                name VARCHAR(50),
				creation_time TIMESTAMP,
				activity VARCHAR(50),
				description VARCHAR(50),
                serialized_properties TEXT,
				service_id BIGINT,
                CONSTRAINT user_account_group_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.user_account_group_id_seq OWNED BY public.user_account_group.id;

/* user_account_function */
CREATE SEQUENCE public.user_account_function_id_seq;
CREATE TABLE public.user_account_function (
                id BIGINT NOT NULL DEFAULT nextval('public.user_account_function_id_seq'),
                name VARCHAR(50),
				start_time TIMESTAMP,
				description VARCHAR(50),
                serialized_properties TEXT,
				user_account_id BIGINT,
                CONSTRAINT user_account_function_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.user_account_function_id_seq OWNED BY public.user_account_function.id;

/* user_account_role */
CREATE SEQUENCE public.user_account_role_id_seq;
CREATE TABLE public.user_account_role (
                id BIGINT NOT NULL DEFAULT nextval('public.user_account_role_id_seq'),
                name VARCHAR(50),
				description VARCHAR(50),
				user_account_id BIGINT,
                CONSTRAINT user_account_role_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.user_account_role_id_seq OWNED BY public.user_account_role.id;

/* user_account_type */
CREATE SEQUENCE public.user_account_type_id_seq;
CREATE TABLE public.user_account_type (
                id BIGINT NOT NULL DEFAULT nextval('public.user_account_type_id_seq'),
                name VARCHAR(50),
				start_time TIMESTAMP,
				description VARCHAR(50),
                serialized_properties TEXT,
				user_account_id BIGINT,
                CONSTRAINT user_account_type_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.user_account_type_id_seq OWNED BY public.user_account_type.id;

/* user_account */
CREATE SEQUENCE public.user_account_id_seq;
CREATE TABLE public.user_account (
                id BIGINT NOT NULL DEFAULT nextval('public.user_account_id_seq'),
                login VARCHAR(50) NOT NULL,
				password_hash VARCHAR NOT NULL,
                first_name VARCHAR,
                last_name VARCHAR,
				mail VARCHAR,
				phone VARCHAR(50),
				creation_time TIMESTAMP,
				serialized_properties TEXT,
				service_id BIGINT,
				user_account_group_id BIGINT,
				address_id BIGINT,
				contract_id BIGINT,
                CONSTRAINT user_account_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;
CREATE UNIQUE INDEX user_account_login_idx
 ON public.user_account
 ( login );


/* project */
CREATE SEQUENCE public.project_id_seq;
CREATE TABLE public.project (
                id BIGINT NOT NULL DEFAULT nextval('public.project_id_seq'),
                name VARCHAR,
				description VARCHAR,
				type VARCHAR,
				creation_time TIMESTAMP,
				last_modification_time TIMESTAMP,
				serialized_properties TEXT,
				contract_id BIGINT,
				object_data_id BIGINT,
				CONSTRAINT project_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.project_id_seq OWNED BY public.project.id;

/* contract */
CREATE SEQUENCE public.contract_id_seq;
CREATE TABLE public.contract (
                id BIGINT NOT NULL DEFAULT nextval('public.contract_id_seq'),
                name VARCHAR,
				description VARCHAR,
				conditions VARCHAR,
				type VARCHAR,
				sign_time TIMESTAMP,
				effective_time TIMESTAMP,
				last_modification_time TIMESTAMP,
				serialized_properties TEXT,
				address_id BIGINT,
				CONSTRAINT contract_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.contract_id_seq OWNED BY public.contract.id;

/* object_data */
CREATE SEQUENCE public.object_data_id_seq;
CREATE TABLE public.object_data (
                id BIGINT NOT NULL DEFAULT nextval('public.object_data_id_seq'),
                name VARCHAR,
				description VARCHAR,
				file VARCHAR,
				creation_time TIMESTAMP,
				last_modification_time TIMESTAMP,
				serialized_properties TEXT,
				contract_id BIGINT,
				project_id BIGINT,
				CONSTRAINT object_data_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.object_data_id_seq OWNED BY public.object_data.id;

/* user_account_project_map */
CREATE TABLE public.project_user_account_map (
                project_id BIGINT NOT NULL,
                user_account_id BIGINT NOT NULL,
                CONSTRAINT project_user_account_map_pk PRIMARY KEY (project_id, user_account_id)
);

/* intervention */
CREATE SEQUENCE public.intervention_id_seq;
CREATE TABLE public.intervention (
                id BIGINT NOT NULL DEFAULT nextval('public.intervention_id_seq'),
                name VARCHAR,
				description VARCHAR,
				conditions VARCHAR,
				ref VARCHAR,
				start_time TIMESTAMP,
				end_time TIMESTAMP,
				duration_time TIMESTAMP,
				last_modification_time TIMESTAMP,
				serialized_properties TEXT,
				intervention_type_id BIGINT,
				customer_id BIGINT,
				billing_id BIGINT,
				CONSTRAINT intervention_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.intervention_id_seq OWNED BY public.intervention.id;

/* intervention_type */

CREATE SEQUENCE public.intervention_type_id_seq;
CREATE TABLE public.intervention_type (
                id BIGINT NOT NULL DEFAULT nextval('public.intervention_type_id_seq'),
                name VARCHAR(50),
				description VARCHAR(50),
                serialized_properties TEXT,
                CONSTRAINT intervention_type_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.intervention_type_id_seq OWNED BY public.intervention_type.id;

/* customer */

CREATE SEQUENCE public.customer_id_seq;
CREATE TABLE public.customer (
                id BIGINT NOT NULL DEFAULT nextval('public.customer_id_seq'),
                name VARCHAR(50),
				company_id BIGINT,
                serialized_properties TEXT,
                CONSTRAINT customer_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
/* customer_incident */

CREATE SEQUENCE public.customer_incident_id_seq;
CREATE TABLE public.customer_incident (
                id BIGINT NOT NULL DEFAULT nextval('public.customer_incident_id_seq'),
				name VARCHAR,
				type VARCHAR,
				description VARCHAR,
				happened_timestamp TIMESTAMP,
				serialized_properties TEXT,
				customer_id BIGINT,
				CONSTRAINT customer_incident_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.customer_incident_id_seq OWNED BY public.customer_incident.id;

/* billing */

CREATE SEQUENCE public.billing_id_seq;
CREATE TABLE public.billing (
                id BIGINT NOT NULL DEFAULT nextval('public.billing_id_seq'),
                ref VARCHAR,
				description VARCHAR,
				amount_incl_tax REAL,
				amount_excl_tax REAL,
				creation_time TIMESTAMP,
				validation_time TIMESTAMP,
				billing_type_id BIGINT,
				vat_id BIGINT,
				billing_currency_id BIGINT,
				CONSTRAINT billing_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.billing_id_seq OWNED BY public.billing.id;

/* billing_type */

CREATE SEQUENCE public.billing_type_seq;
CREATE TABLE public.billing_type (
                id BIGINT NOT NULL DEFAULT nextval('public.billing_type_seq'),
                ref VARCHAR,
				description VARCHAR,
				CONSTRAINT billing_type_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.billing_type_seq OWNED BY public.billing_type.id;

/* vat */

CREATE SEQUENCE public.vat_id_seq;
CREATE TABLE public.vat (
                id BIGINT NOT NULL DEFAULT nextval('public.vat_id_seq'),
				name VARCHAR,
				value REAL,
				description VARCHAR,
				creation_time TIMESTAMP,
				updated_time TIMESTAMP,
				CONSTRAINT vat_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.vat_id_seq OWNED BY public.vat.id;

/* billing_currency */

CREATE SEQUENCE public.billing_currency_seq;
CREATE TABLE public.billing_currency (
                id BIGINT NOT NULL DEFAULT nextval('public.billing_type_seq'),
                currency_code VARCHAR,
				default_fraction_digits integer,
				numeric_code integer,
				description VARCHAR,
				CONSTRAINT billing_currency_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.billing_currency_seq OWNED BY public.billing_currency.id;

/* user_account_intervention_map */

CREATE TABLE public.user_account_intervention_map (
                user_account_id BIGINT NOT NULL,
				intervention_id BIGINT NOT NULL,
                CONSTRAINT user_account_intervention_map_pk PRIMARY KEY (user_account_id,intervention_id)
);

/* project_intervention_map */

CREATE TABLE public.project_intervention_map (
                project_id BIGINT NOT NULL,
				intervention_id BIGINT NOT NULL,
                CONSTRAINT project_intervention_map_pk PRIMARY KEY (project_id,intervention_id)
);

/* product */

CREATE SEQUENCE public.product_id_seq;
CREATE TABLE public.product (
                id BIGINT NOT NULL DEFAULT nextval('public.product_id_seq'),
                name VARCHAR,
				description VARCHAR,
                price_incl REAL,
				price_excl REAL,
				creation_time TIMESTAMP,
				serialized_properties TEXT,
				product_use_id BIGINT,
				product_type_id BIGINT,
				product_category_id BIGINT,
			    product_group_id BIGINT,
				product_item_id BIGINT,
			    provider_id BIGINT,
			    vat_id BIGINT,
                CONSTRAINT product_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;

/* product_type */

CREATE SEQUENCE public.product_type_id_seq;
CREATE TABLE public.product_type (
                id BIGINT NOT NULL DEFAULT nextval('public.product_type_id_seq'),
				name VARCHAR,
				description VARCHAR,
				serialized_properties TEXT,
				CONSTRAINT product_type_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.product_type_id_seq OWNED BY public.product_type.id;

/* product_use */

CREATE SEQUENCE public.product_use_id_seq;
CREATE TABLE public.product_use (
                id BIGINT NOT NULL DEFAULT nextval('public.product_use_id_seq'),
				name VARCHAR,
				description VARCHAR,
				serialized_properties TEXT,
				CONSTRAINT product_use_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.product_use_id_seq OWNED BY public.product_use.id;

/* product_category */

CREATE SEQUENCE public.product_category_id_seq;
CREATE TABLE public.product_category (
                id BIGINT NOT NULL DEFAULT nextval('public.product_category_id_seq'),
				name VARCHAR,
				description VARCHAR,
				serialized_properties TEXT,
				CONSTRAINT product_category_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.product_category_id_seq OWNED BY public.product_category.id;

/* product_group */

CREATE SEQUENCE public.product_group_id_seq;
CREATE TABLE public.product_group (
                id BIGINT NOT NULL DEFAULT nextval('public.product_group_id_seq'),
				name VARCHAR,
				description VARCHAR,
				serialized_properties TEXT,
				CONSTRAINT product_group_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.product_group_id_seq OWNED BY public.product_group.id;

/* product_item */

CREATE SEQUENCE public.product_item_id_seq;
CREATE TABLE public.product_item (
                id BIGINT NOT NULL DEFAULT nextval('public.product_item_id_seq'),
				name VARCHAR,
				value VARCHAR,
				description VARCHAR,
				serialized_properties TEXT,
				CONSTRAINT product_item_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.product_item_id_seq OWNED BY public.product_item.id;

/* provider */

CREATE SEQUENCE public.provider_id_seq;
CREATE TABLE public.provider (
                id BIGINT NOT NULL DEFAULT nextval('public.provider_id_seq'),
				name VARCHAR,
				serialized_properties TEXT,
				company_id BIGINT,
				CONSTRAINT provider_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.provider_id_seq OWNED BY public.provider.id;

/* provider_incident */

CREATE SEQUENCE public.provider_incident_id_seq;
CREATE TABLE public.provider_incident (
                id BIGINT NOT NULL DEFAULT nextval('public.provider_incident_id_seq'),
				name VARCHAR,
				type VARCHAR,
				description VARCHAR,
				happened_timestamp TIMESTAMP,
				serialized_properties TEXT,
				provider_id BIGINT,
				CONSTRAINT provider_incident_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.provider_incident_id_seq OWNED BY public.provider_incident.id;

/* user_account_product_map */
CREATE TABLE public.user_account_product_map (
                user_account_id BIGINT NOT NULL,
                product_id BIGINT NOT NULL,
                CONSTRAINT product_user_account_map_pk PRIMARY KEY (user_account_id, product_id)
);

/* request_purchase */

CREATE SEQUENCE public.request_purchase_id_seq;
CREATE TABLE public.request_purchase (
                id BIGINT NOT NULL DEFAULT nextval('public.request_purchase_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				request_type_id BIGINT,
				response_purchase_id BIGINT,
				CONSTRAINT request_purchase_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.request_purchase_id_seq OWNED BY public.request_purchase.id;

/* user_account_request_purchase_map */

CREATE TABLE public.user_account_request_purchase_map (
                user_account_id BIGINT NOT NULL,
                request_purchase_id BIGINT NOT NULL,
                CONSTRAINT user_account_request_purchase_map_pk PRIMARY KEY (user_account_id, request_purchase_id)
);

/*request_type*/

CREATE SEQUENCE public.request_type_id_seq;
CREATE TABLE public.request_type (
                id BIGINT NOT NULL DEFAULT nextval('public.request_type_id_seq'),
		name VARCHAR,
		description VARCHAR,
		serialized_properties TEXT,
		CONSTRAINT request_type_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.request_type_id_seq OWNED BY public.request_type.id;

/* response_purchase */

CREATE SEQUENCE public.response_purchase_id_seq;
CREATE TABLE public.response_purchase (
                id BIGINT NOT NULL DEFAULT nextval('public.response_purchase_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT response_purchase_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.response_purchase_id_seq OWNED BY public.response_purchase.id;

/* action_purchase */

CREATE SEQUENCE public.action_purchase_id_seq;
CREATE TABLE public.action_purchase (
                id BIGINT NOT NULL DEFAULT nextval('public.action_purchase_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				response_purchase_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT action_purchase_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.action_purchase_id_seq OWNED BY public.action_purchase.id;

/* request_borrow */

CREATE SEQUENCE public.request_borrow_id_seq;
CREATE TABLE public.request_borrow (
                id BIGINT NOT NULL DEFAULT nextval('public.request_borrow_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				request_type_id BIGINT,
				response_borrow_id BIGINT,
				CONSTRAINT request_borrow_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.request_borrow_id_seq OWNED BY public.request_borrow.id;

/* user_account_request_borrow_map */
CREATE TABLE public.user_account_request_borrow_map (
                user_account_id BIGINT NOT NULL,
                request_borrow_id BIGINT NOT NULL,
                CONSTRAINT user_account_request_borrow_map_pk PRIMARY KEY (user_account_id, request_borrow_id)
);

/* response_borrow  */

CREATE SEQUENCE public.response_borrow_id_seq;
CREATE TABLE public.response_borrow (
                id BIGINT NOT NULL DEFAULT nextval('public.response_borrow_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT response_borrow_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.response_borrow_id_seq OWNED BY public.response_borrow.id;

/* action_borrow  */

CREATE SEQUENCE public.action_borrow_id_seq;
CREATE TABLE public.action_borrow (
                id BIGINT NOT NULL DEFAULT nextval('public.action_borrow_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				response_borrow_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT action_borrow_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.action_borrow_id_seq OWNED BY public.action_borrow.id;

/* request_access */

CREATE SEQUENCE public.request_access_id_seq;
CREATE TABLE public.request_access (
                id BIGINT NOT NULL DEFAULT nextval('public.request_access_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				request_type_id BIGINT,
				response_access_id BIGINT,
				CONSTRAINT request_access_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.request_access_id_seq OWNED BY public.request_access.id;

/* user_account_request_access_map */

CREATE TABLE public.user_account_request_access_map (
                user_account_id BIGINT NOT NULL,
                request_access_id BIGINT NOT NULL,
                CONSTRAINT user_account_request_access_map_pk PRIMARY KEY (user_account_id, request_access_id)
);

/* response_access */

CREATE SEQUENCE public.response_access_id_seq;
CREATE TABLE public.response_access (
                id BIGINT NOT NULL DEFAULT nextval('public.response_access_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT response_access_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.response_access_id_seq OWNED BY public.response_access.id;

/* action_access  */

CREATE SEQUENCE public.action_access_id_seq;
CREATE TABLE public.action_access (
                id BIGINT NOT NULL DEFAULT nextval('public.action_access_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				response_access_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT action_access_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.action_access_id_seq OWNED BY public.action_access.id;


/* request_quality */

CREATE SEQUENCE public.request_quality_id_seq;
CREATE TABLE public.request_quality (
                id BIGINT NOT NULL DEFAULT nextval('public.request_quality_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				request_type_id BIGINT,
				response_quality_id BIGINT,
				CONSTRAINT request_quality_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.request_quality_id_seq OWNED BY public.request_quality.id;

/* user_account_request_access_map */

CREATE TABLE public.user_account_request_quality_map (
                user_account_id BIGINT NOT NULL,
                request_quality_id BIGINT NOT NULL,
                CONSTRAINT user_account_request_quality_map_pk PRIMARY KEY (user_account_id, request_quality_id)
);

/* response_quality */

CREATE SEQUENCE public.response_quality_id_seq;
CREATE TABLE public.response_quality (
                id BIGINT NOT NULL DEFAULT nextval('public.response_quality_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT response_quality_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.response_quality_id_seq OWNED BY public.response_quality.id;

/* action_quality  */

CREATE SEQUENCE public.action_quality_id_seq;
CREATE TABLE public.action_quality (
                id BIGINT NOT NULL DEFAULT nextval('public.action_quality_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				creation_timestamp TIMESTAMP,
				serialized_properties TEXT,
				status_id BIGINT,
				response_quality_id BIGINT,
				owner_id BIGINT,
				CONSTRAINT action_quality_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.action_quality_id_seq OWNED BY public.action_quality.id;

/*request_status*/
CREATE SEQUENCE public.request_status_id_seq;
CREATE TABLE public.request_status (
                id BIGINT NOT NULL DEFAULT nextval('public.request_status_id_seq'),
				name VARCHAR,
				ref VARCHAR,
				description VARCHAR,
				serialized_properties TEXT,
				CONSTRAINT request_status_pk PRIMARY KEY (id)
);
ALTER SEQUENCE public.request_status_id_seq OWNED BY public.request_status.id;

/*-------------- create foreign keys-------------------*/

/* company */

ALTER TABLE public.company ADD CONSTRAINT company_status_id_fk
FOREIGN KEY (company_status_id)
REFERENCES public.company_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.company ADD CONSTRAINT address_id_fk
FOREIGN KEY (address_id)
REFERENCES public.address (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* service */

ALTER TABLE public.service ADD CONSTRAINT service_company_id_fk
FOREIGN KEY (company_id)
REFERENCES public.company(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* user_account_group */

ALTER TABLE public.user_account_group ADD CONSTRAINT group_service_id_fk
FOREIGN KEY (service_id)
REFERENCES public.service (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* user_account */

ALTER TABLE public.user_account ADD CONSTRAINT service_id_fk
FOREIGN KEY (service_id)
REFERENCES public.service(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT group_id_fk
FOREIGN KEY (user_account_group_id)
REFERENCES public.user_account_group(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT address_id_fk
FOREIGN KEY (address_id)
REFERENCES public.address(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT contract_id_fk
FOREIGN KEY (contract_id)
REFERENCES public.contract(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* project */

ALTER TABLE public.project ADD CONSTRAINT contract_id_fk
FOREIGN KEY (contract_id)
REFERENCES public.contract(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.project ADD CONSTRAINT object_data_id_fk
FOREIGN KEY (object_data_id)
REFERENCES public.object_data(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*contract*/

ALTER TABLE public.contract ADD CONSTRAINT address_id_fk
FOREIGN KEY (address_id)
REFERENCES public.address(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*object_data*/

ALTER TABLE public.object_data ADD CONSTRAINT project_id_fk
FOREIGN KEY (project_id)
REFERENCES public.project(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.object_data ADD CONSTRAINT contract_id_fk
FOREIGN KEY (contract_id)
REFERENCES public.contract(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* project_user_account_map */

ALTER TABLE public.project_user_account_map ADD CONSTRAINT project_user_account_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.project_user_account_map ADD CONSTRAINT user_account_project_map_fk
FOREIGN KEY (project_id)
REFERENCES public.project (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* user_account_intervention_map */

ALTER TABLE public.user_account_intervention_map ADD CONSTRAINT user_account_intervention_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_intervention_map ADD CONSTRAINT intervention_user_account_map_fk
FOREIGN KEY (intervention_id)
REFERENCES public.intervention (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* project_intervention_map */

ALTER TABLE public.project_intervention_map ADD CONSTRAINT project_intervention_map_fk
FOREIGN KEY (project_id)
REFERENCES public.project (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.project_intervention_map ADD CONSTRAINT intervention_project_map_fk
FOREIGN KEY (intervention_id)
REFERENCES public.intervention (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* intervention */

ALTER TABLE public.intervention ADD CONSTRAINT intervention_type_id_fk
FOREIGN KEY (intervention_type_id)
REFERENCES public.intervention_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.intervention ADD CONSTRAINT billing_id_fk
FOREIGN KEY (billing_id)
REFERENCES public.billing (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.intervention ADD CONSTRAINT customer_id_fk
FOREIGN KEY (customer_id)
REFERENCES public.customer (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*customer*/

ALTER TABLE public.customer ADD CONSTRAINT customer_company_id_fk
FOREIGN KEY (company_id)
REFERENCES public.company (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*billing*/
ALTER TABLE public.billing ADD CONSTRAINT billing_type_id_fk
FOREIGN KEY (billing_type_id)
REFERENCES public.billing_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.billing ADD CONSTRAINT vat_id_fk
FOREIGN KEY (vat_id)
REFERENCES public.vat (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.billing ADD CONSTRAINT billing_currency_id_fk
FOREIGN KEY (billing_currency_id)
REFERENCES public.billing_currency (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*provider*/

ALTER TABLE public.provider ADD CONSTRAINT provider_company_id_fk
FOREIGN KEY (company_id)
REFERENCES public.company (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* product */

ALTER TABLE public.product ADD CONSTRAINT product_use_id_fk
FOREIGN KEY (product_use_id)
REFERENCES public.product_use (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.product ADD CONSTRAINT product_type_id_fk
FOREIGN KEY (product_type_id)
REFERENCES public.product_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.product ADD CONSTRAINT product_category_id_fk
FOREIGN KEY (product_category_id)
REFERENCES public.product_category (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.product ADD CONSTRAINT product_group_id_fk
FOREIGN KEY (product_group_id)
REFERENCES public.product_group (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.product ADD CONSTRAINT product_item_id_fk
FOREIGN KEY (product_item_id)
REFERENCES public.product_item (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE public.product ADD CONSTRAINT provider_id_fk
FOREIGN KEY (provider_id)
REFERENCES public.provider (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.product ADD CONSTRAINT vat_id_fk
FOREIGN KEY (vat_id)
REFERENCES public.vat (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* request_purchase */

ALTER TABLE public.request_purchase ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_purchase ADD CONSTRAINT request_type_id_fk
FOREIGN KEY (request_type_id)
REFERENCES public.request_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_purchase ADD CONSTRAINT response_purchase_id_fk
FOREIGN KEY (response_purchase_id)
REFERENCES public.response_purchase (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* response_purchase */

ALTER TABLE public.response_purchase ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.response_purchase ADD CONSTRAINT response_purchase_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* action_purchase */
ALTER TABLE public.action_purchase ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_purchase ADD CONSTRAINT response_purchase_id_fk
FOREIGN KEY (response_purchase_id)
REFERENCES public.response_purchase (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_purchase ADD CONSTRAINT action_purchase_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* request_borrow */

ALTER TABLE public.request_borrow ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_borrow ADD CONSTRAINT request_type_id_fk
FOREIGN KEY (request_type_id)
REFERENCES public.request_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_borrow ADD CONSTRAINT response_borrow_id_fk
FOREIGN KEY (response_borrow_id)
REFERENCES public.response_borrow (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* response_borrow */

ALTER TABLE public.response_borrow ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.response_borrow ADD CONSTRAINT response_borrow_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* action_borrow */
ALTER TABLE public.action_borrow ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_borrow ADD CONSTRAINT response_borrow_id_fk
FOREIGN KEY (response_borrow_id)
REFERENCES public.response_borrow (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_borrow ADD CONSTRAINT action_borrow_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


/** request_access */

ALTER TABLE public.request_access ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_access ADD CONSTRAINT request_type_id_fk
FOREIGN KEY (request_type_id)
REFERENCES public.request_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_access ADD CONSTRAINT response_access_id_fk
FOREIGN KEY (response_access_id)
REFERENCES public.response_access (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* response_access */

ALTER TABLE public.response_access ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.response_access ADD CONSTRAINT response_access_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* action_access */

ALTER TABLE public.action_access ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_access ADD CONSTRAINT response_access_id_fk
FOREIGN KEY (response_access_id)
REFERENCES public.response_access (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.response_access ADD CONSTRAINT action_access_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* request_quality */

ALTER TABLE public.request_quality ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_quality ADD CONSTRAINT request_type_id_fk
FOREIGN KEY (request_type_id)
REFERENCES public.request_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.request_quality ADD CONSTRAINT response_quality_id_fk
FOREIGN KEY (response_quality_id)
REFERENCES public.response_quality(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* response_quality */

ALTER TABLE public.response_quality ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.response_quality ADD CONSTRAINT response_quality_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* action_quality */

ALTER TABLE public.action_quality ADD CONSTRAINT status_id_fk
FOREIGN KEY (status_id)
REFERENCES public.request_status (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_quality ADD CONSTRAINT response_quality_id_fk
FOREIGN KEY (response_quality_id)
REFERENCES public.response_quality (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.action_quality ADD CONSTRAINT action_quality_owner_id_fk
FOREIGN KEY (owner_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* user_account_request_purchase_map */

ALTER TABLE public.user_account_request_purchase_map ADD CONSTRAINT user_account_request_purchase_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_request_purchase_map ADD CONSTRAINT request_purchase_user_account_map_fk
FOREIGN KEY (request_purchase_id)
REFERENCES public.request_purchase (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*user_account_request_borrow_map*/

ALTER TABLE public.user_account_request_borrow_map ADD CONSTRAINT user_account_request_borrow_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_request_borrow_map ADD CONSTRAINT request_borrow_user_account_map_fk
FOREIGN KEY (request_borrow_id)
REFERENCES public.request_borrow (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


/*user_account_request_access_map*/

ALTER TABLE public.user_account_request_access_map ADD CONSTRAINT user_account_request_access_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_request_access_map ADD CONSTRAINT request_access_user_account_map_fk
FOREIGN KEY (request_access_id)
REFERENCES public.request_access (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*user_account_request_quality_map*/

ALTER TABLE public.user_account_request_quality_map ADD CONSTRAINT user_account_request_quality_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_request_quality_map ADD CONSTRAINT request_quality_user_account_map_fk
FOREIGN KEY (request_quality_id)
REFERENCES public.request_quality (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*user_account_function*/

ALTER TABLE public.user_account_function ADD CONSTRAINT user_account_function_user_account_id_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*user_account_role*/

ALTER TABLE public.user_account_role ADD CONSTRAINT user_account_role_user_account_id_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* user_account_type */

ALTER TABLE public.user_account_type ADD CONSTRAINT user_account_type_user_account_id_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* provider_incident */

ALTER TABLE public.provider_incident ADD CONSTRAINT provider_incident_id_fk
FOREIGN KEY (provider_id)
REFERENCES public.provider(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/* customer_incident */

ALTER TABLE public.customer_incident ADD CONSTRAINT customer_incident_id_fk
FOREIGN KEY (customer_id)
REFERENCES public.customer(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*user_account_product_map*/

ALTER TABLE public.user_account_product_map ADD CONSTRAINT user_account_product_map_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_product_map ADD CONSTRAINT product_user_account_map_fk
FOREIGN KEY (product_id)
REFERENCES public.product (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;