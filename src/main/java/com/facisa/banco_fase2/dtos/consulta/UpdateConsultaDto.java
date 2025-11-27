package com.facisa.banco_fase2.dtos.consulta;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record UpdateConsultaDto(BigDecimal valor, Timestamp dataHoraInicio, Timestamp dataHoraFim, Integer medicoId, Integer pacienteId,Boolean realizado) {
}
