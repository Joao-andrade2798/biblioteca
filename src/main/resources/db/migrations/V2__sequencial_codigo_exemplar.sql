CREATE SEQUENCE IF NOT EXISTS exemplar_codigo START 1 INCREMENT 1;

ALTER TABLE exemplar
  ALTER COLUMN codigo SET DEFAULT nextval('exemplar_codigo');