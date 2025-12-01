CREATE OR REPLACE PROCEDURE prc_finaliza_consulta(p_consulta_id INT)
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM consulta WHERE id = p_consulta_id
    ) THEN
        RAISE EXCEPTION 'Consulta % não encontrada.', p_consulta_id;
END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM pagamento_consulta
        WHERE consulta_id = p_consulta_id
          AND realizado = TRUE
    ) THEN
        RAISE EXCEPTION
            'A consulta % não pode ser finalizada pois o pagamento não foi realizado.',
            p_consulta_id;
END IF;

UPDATE consulta
SET realizado = TRUE
WHERE id = p_consulta_id;

INSERT INTO log_finalizacao_consulta(consulta_id, data_finalizacao)
VALUES (p_consulta_id, NOW());

RAISE NOTICE 'Consulta % finalizada com sucesso.', p_consulta_id;

END;
$$;
