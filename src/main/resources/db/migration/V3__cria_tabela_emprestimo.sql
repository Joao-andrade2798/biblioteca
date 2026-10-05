CREATE TABLE public.emprestimo (
    id character varying(255) NOT NULL,
    id_usuario character varying(255) NOT NULL,
    id_livro character varying(255) NOT NULL,
    data_emprestimo date NOT NULL,
    data_devolucao date,
    CONSTRAINT emprestimo_pkey PRIMARY KEY (id),
    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id),
    CONSTRAINT fk_emprestimo_livro FOREIGN KEY (id_livro) REFERENCES public.livro(id)
);