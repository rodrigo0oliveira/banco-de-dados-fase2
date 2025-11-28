package com.facisa.banco_fase2.repositories.medico;

import com.facisa.banco_fase2.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Integer> {

    @Query(value = "SELECT fn_medico_disponivel(:medicoId,:dataHoraInicio,:dataHoraFim)")
    Boolean callFunctionIsMedicoAvailable(@Param("medicoId") Integer medicoId, @Param("dataHoraInicio")Timestamp dataHoraInicio,@Param("dataHoraFim") Timestamp dataHoraFim);
}
