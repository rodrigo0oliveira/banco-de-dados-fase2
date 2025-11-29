CREATE OR REPLACE FUNCTION trg_proteger_valor_pago()
RETURNS trigger AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM pagamento_consulta
        WHERE consulta_id = OLD.id AND realizado = TRUE
    ) AND (OLD.valor <> NEW.valor) THEN
        RAISE EXCEPTION 'Não é possível alterar o valor de uma consulta com pagamento realizado.';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER proteger_valor_pago
    BEFORE UPDATE ON consulta
    FOR EACH ROW
    EXECUTE FUNCTION trg_proteger_valor_pago();
