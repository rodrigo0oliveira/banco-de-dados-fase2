package com.facisa.banco_fase2.repositories.paciente;

import com.facisa.banco_fase2.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {

}
