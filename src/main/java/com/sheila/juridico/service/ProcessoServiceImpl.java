package com.sheila.juridico.service;

import com.sheila.juridico.dto.ProcessoRequestDto;
import com.sheila.juridico.dto.ProcessoResponseDto;
import com.sheila.juridico.mapper.ProcessoMapper;
import com.sheila.juridico.model.Participante;
import com.sheila.juridico.model.Processo;
import com.sheila.juridico.model.StatusProcesso;
import com.sheila.juridico.repository.ProcessoRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessoServiceImpl implements ProcessoService{
    private final ProcessoRepository processoRepository;
    private final ProcessoMapper mapper;

    public ProcessoServiceImpl(ProcessoRepository processoRepository, ProcessoMapper mapper) {
        this.processoRepository = processoRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProcessoResponseDto save(ProcessoRequestDto processoRequestDto){
        Optional<Processo> existente = processoRepository.findByNumeroProcesso(processoRequestDto.numeroProcesso());
        if (existente.isPresent()) {
            throw new RuntimeException("Já existe um processo com este número cadastrado: " + processoRequestDto.numeroProcesso());
        }
        Processo processo = mapper.toEntity(processoRequestDto);
        Processo processoSave = processoRepository.save(processo);
        return mapper.toDto(processoSave);
    }

    @Override
    @Transactional
    public List<ProcessoResponseDto> arquivarProcessos(List<String> processo){
        List<Processo> processos = processoRepository.findAllByNumeroProcessoIn(processo);

        processos.forEach(numProcesso -> numProcesso.setStatus(StatusProcesso.ARQUIVADO));
        List<Processo> processosArquivados = processoRepository.saveAll(processos);
        return processosArquivados
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ProcessoResponseDto> findAll(){
        return processoRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<ProcessoResponseDto> findByProcesso(String numeroProcesso) {
        return processoRepository.findByNumeroProcesso(numeroProcesso)
                .map(mapper::toDto);
    }

    public List<ProcessoResponseDto> findWithFilter(StatusProcesso status, LocalDate dataAbertura, String cnpjCpf) {
        Specification<Processo> spec = (root, query, cb) -> cb.conjunction();

        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }

        if (dataAbertura != null) {
            LocalDateTime start = dataAbertura.atStartOfDay();
            LocalDateTime end = dataAbertura.atTime(LocalTime.MAX);
            spec = spec.and((root, query, cb) -> cb.between(root.get("dataAbertura"), start, end));
        }

        if (cnpjCpf != null && !cnpjCpf.isBlank()) {
            spec = spec.and((root, query, cb) -> {
                Join<Processo, Participante> join = root.join("participantes", JoinType.INNER);
                return cb.equal(join.get("cnpjCpf"), cnpjCpf);
            });
        }

        return processoRepository.findAll(spec)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


}
