package com.facisa.banco_fase2.repositories.medico;

import com.facisa.banco_fase2.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Integer> {
}
