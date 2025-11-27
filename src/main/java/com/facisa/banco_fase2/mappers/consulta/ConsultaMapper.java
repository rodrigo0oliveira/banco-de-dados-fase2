package com.facisa.banco_fase2.mappers.consulta;

import com.facisa.banco_fase2.domain.Consulta;

public class ConsultaMapper {

    public static Consulta toDomain(ConsultaRow row){
        return new Consulta(row.valor(),row.dataHoraInicio(),row.dataHoraFim(),false,row.medico(),row.paciente());
    }
}
