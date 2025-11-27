package com.facisa.banco_fase2.services;

import com.facisa.banco_fase2.domain.Consulta;
import com.facisa.banco_fase2.domain.Medico;
import com.facisa.banco_fase2.domain.Paciente;
import com.facisa.banco_fase2.dtos.consulta.ConsultaDto;
import com.facisa.banco_fase2.dtos.consulta.UpdateConsultaDto;
import com.facisa.banco_fase2.mappers.consulta.ConsultaMapper;
import com.facisa.banco_fase2.mappers.consulta.ConsultaRow;
import com.facisa.banco_fase2.repositories.consulta.ConsultaRepository;
import com.facisa.banco_fase2.repositories.medico.MedicoRepository;
import com.facisa.banco_fase2.repositories.paciente.PacienteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private MedicoRepository medicoRepository;
    private PacienteRepository pacienteRepository;
    private ConsultaRepository consultaRepository;

    public ConsultaService(MedicoRepository medicoRepository, ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Consulta createConsulta(ConsultaDto dto) throws BadRequestException {
        Medico medico = medicoRepository.findById(dto.medicoId()).orElseThrow(()-> new BadRequestException("Medico nao encontrado!"));
        Paciente paciente = pacienteRepository.findById(dto.pacienteId()).orElseThrow(()-> new BadRequestException("Paciente nao encontrado!"));

        Consulta consulta = ConsultaMapper.toDomain(new ConsultaRow(dto.valor(),dto.dataHoraInicio(),dto.dataHoraFim(),medico,paciente));

        return consultaRepository.save(consulta);
    }

    public Consulta getById(Integer id) throws BadRequestException {
        return consultaRepository.findById(id).orElseThrow(()-> new BadRequestException("Consulta nao encontrada"));
    }

    public List<Consulta> findAll(){
        return consultaRepository.findAll();
    }

    public Consulta updateById(Integer id, UpdateConsultaDto dto) throws BadRequestException {
        Consulta consulta = getById(id);

        Medico medico = medicoRepository.findById(dto.medicoId()).orElseThrow(()-> new BadRequestException("Medico nao encontrado!"));
        Paciente paciente = pacienteRepository.findById(dto.pacienteId()).orElseThrow(()-> new BadRequestException("Paciente nao encontrado!"));

        Optional.ofNullable(dto.realizado()).ifPresent(consulta::setRealizado);
        Optional.of(paciente).ifPresent(consulta::setPaciente);
        Optional.of(medico).ifPresent(consulta::setMedico);
        Optional.ofNullable(dto.dataHoraInicio()).ifPresent(consulta::setData_hora_inicio);
        Optional.ofNullable(dto.dataHoraFim()).ifPresent(consulta::setData_hora_fim);
        Optional.ofNullable(dto.valor()).ifPresent(consulta::setValor);

        return consultaRepository.save(consulta);
    }

    public void deleteById(Integer id){
        consultaRepository.deleteById(id);
    }
}
