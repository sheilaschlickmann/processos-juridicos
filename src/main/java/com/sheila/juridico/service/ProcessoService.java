package com.sheila.juridico.service;

import com.sheila.juridico.dto.ProcessoRequestDto;
import com.sheila.juridico.dto.ProcessoResponseDto;
import com.sheila.juridico.model.StatusProcesso;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProcessoService {
    ProcessoResponseDto save(ProcessoRequestDto processoRequestDto);
    List<ProcessoResponseDto> arquivarProcessos(List<String> numerosProcesso);
    List<ProcessoResponseDto> findAll();
    Optional<ProcessoResponseDto> findByProcesso(String numeroProcesso);
    List<ProcessoResponseDto> findWithFilter(StatusProcesso status, LocalDate dataAbertura, String cnpjCpf);
}
