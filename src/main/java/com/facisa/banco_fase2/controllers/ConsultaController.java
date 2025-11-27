package com.facisa.banco_fase2.controllers;

import com.facisa.banco_fase2.domain.Consulta;
import com.facisa.banco_fase2.dtos.consulta.ConsultaDto;
import com.facisa.banco_fase2.repositories.consulta.ConsultaRepository;
import com.facisa.banco_fase2.repositories.medico.MedicoRepository;
import com.facisa.banco_fase2.repositories.paciente.PacienteRepository;
import com.facisa.banco_fase2.services.ConsultaService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    private MedicoRepository medicoRepository;

    private PacienteRepository pacienteRepository;

    private ConsultaRepository consultaRepository;

    private ConsultaService consultaService;

    @Autowired
    public ConsultaController(MedicoRepository medicoRepository,
                              PacienteRepository pacienteRepository,
                              ConsultaRepository consultaRepository,
                              ConsultaService consultaService) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.consultaRepository = consultaRepository;
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<?> createConsulta(@RequestBody ConsultaDto dto){
        try {
            Consulta consulta = consultaService.createConsulta(dto);

            return ResponseEntity.status(201).body(consulta);
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            Consulta consulta = consultaService.getById(id);

            return ResponseEntity.ok(consulta);
        }catch (BadRequestException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
