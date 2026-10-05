CREATE TABLE public.autor (
    id character varying(255) NOT NULL,
    nacionalidade character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    CONSTRAINT autor_pkey PRIMARY KEY (id)
);

CREATE TABLE public.livro (
    id character varying(255) NOT NULL,
    ano integer NOT NULL,
    genero character varying(255) NOT NULL,
    titulo character varying(255) NOT NULL,
    id_autor character varying(255) NOT NULL,
    CONSTRAINT livro_pkey PRIMARY KEY (id),
    CONSTRAINT fk_livro_autor FOREIGN KEY (id_autor) REFERENCES public.autor(id)
);

CREATE SEQUENCE public.exemplar_codigo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.exemplar (
    id character varying(255) NOT NULL,
    codigo integer DEFAULT nextval('public.exemplar_codigo'::regclass) NOT NULL,
    status character varying(255) NOT NULL,
    id_livro character varying(255) NOT NULL,
    CONSTRAINT exemplar_pkey PRIMARY KEY (id),
    CONSTRAINT uk_exemplar_codigo UNIQUE (codigo),
    CONSTRAINT exemplar_status_check CHECK (status IN ('DISPONIVEL', 'EMPRESTADO', 'PERDIDO')),
    CONSTRAINT fk_exemplar_livro FOREIGN KEY (id_livro) REFERENCES public.livro(id)
);

ALTER SEQUENCE public.exemplar_codigo OWNED BY public.exemplar.codigo;

CREATE TABLE public.usuario (
    id character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    telefone character varying(255) NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);