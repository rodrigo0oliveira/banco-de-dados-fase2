CREATE OR REPLACE FUNCTION fn_medico_disponivel(
    p_medico_id INT,
    p_data_hora_inicio TIMESTAMP,
    p_data_hora_fim TIMESTAMP
)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
DECLARE
v_qtd INT;
BEGIN
    IF p_medico_id IS NULL OR p_data_hora_inicio IS NULL OR p_data_hora_fim IS NULL THEN
        RAISE EXCEPTION 'ID do médico e horários devem ser informados.';
END IF;

SELECT COUNT(*)
INTO v_qtd
FROM consulta
WHERE medico_id = p_medico_id
  AND p_data_hora_inicio < data_hora_fim
  AND p_data_hora_fim   > data_hora_inicio;

RETURN v_qtd = 0;
END;
$$;
