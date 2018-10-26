CREATE TABLE public.ranking
(
  id integer NOT NULL,
  nome character varying(50),
  score bigint
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ranking
  OWNER TO postgres;