ALTER TABLE public.emprestimo DROP CONSTRAINT IF EXISTS fk_emprestimo_livro;
ALTER TABLE public.emprestimo DROP COLUMN IF EXISTS id_livro;

ALTER TABLE public.emprestimo ADD COLUMN IF NOT EXISTS id_exemplar_livro varchar(255) NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'emprestimo_exemplar_fk'
    ) THEN
        ALTER TABLE public.emprestimo
            ADD CONSTRAINT emprestimo_exemplar_fk FOREIGN KEY (id_exemplar_livro) REFERENCES public.exemplar(id);
    END IF;
END $$;