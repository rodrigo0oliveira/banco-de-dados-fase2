CREATE OR REPLACE FUNCTION trg_create_pagamento_consulta_data()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
	INSERT INTO pagamento_consulta(data_hora,realizado,valor,consulta_id)
	VALUES (null,false,new.valor,new.id);

	RETURN NEW;

END;
$$;

CREATE TRIGGER trg_create_pagamento_consulta_data
AFTER INSERT ON consulta
FOR EACH ROW
EXECUTE FUNCTION trg_create_pagamento_consulta_data();
