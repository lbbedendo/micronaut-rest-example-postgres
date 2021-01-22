CREATE TABLE IF NOT EXISTS automaker (
                              id bigserial NOT NULL,
                              name character varying NOT NULL,
                              country varchar(100),
                              CONSTRAINT pk_automaker PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS collection (
                               id bigserial NOT NULL,
                               name character varying NOT NULL,
                               year int,
                               CONSTRAINT pk_collection PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS brand (
                          id bigserial NOT NULL,
                          name character varying NOT NULL,
                          CONSTRAINT pk_brand PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS model (
                          id bigserial NOT NULL,
                          name character varying NOT NULL,
                          model_year int,
                          scale varchar(10),
                          color_rgba varchar(7),
                          automaker_id bigint,
                          collection_id bigint,
                          brand_id bigint,
                          CONSTRAINT pk_model PRIMARY KEY (id),
                          CONSTRAINT fk_automaker FOREIGN KEY (automaker_id) REFERENCES automaker (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION,
                          CONSTRAINT fk_collection FOREIGN KEY (collection_id) REFERENCES collection (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION,
                          CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brand (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION
);