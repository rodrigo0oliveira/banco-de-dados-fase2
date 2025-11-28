package com.facisa.banco_fase2.repositories.consulta;

import com.facisa.banco_fase2.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Integer> {

    @Query(value = "call prc_finaliza_consulta(:id)" ,nativeQuery = true)
    boolean callProcedureFinalizaConsulta(@Param("id") Integer id);
}
