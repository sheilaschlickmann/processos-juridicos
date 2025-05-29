package com.sheila.juridico.controller;

import com.sheila.juridico.dto.ProcessoRequestDto;
import com.sheila.juridico.dto.ProcessoResponseDto;
import com.sheila.juridico.model.StatusProcesso;
import com.sheila.juridico.service.AcaoService;
import com.sheila.juridico.service.ParticipanteService;
import com.sheila.juridico.service.ProcessoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Processos", description = "Endpoints para gerenciamento de processos jurídicos")
@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    final ProcessoServiceImpl processoService;
    final ParticipanteService participanteService;
    final AcaoService acaoService;

    public ProcessoController(ProcessoServiceImpl processoService, ParticipanteService participanteService, AcaoService acaoService) {
        this.processoService = processoService;
        this.participanteService = participanteService;
        this.acaoService = acaoService;
    }

    @Operation(summary = "Criar um processo")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcessoResponseDto> save(@RequestBody ProcessoRequestDto processo) {
        ProcessoResponseDto processoSave = processoService.save(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(processoSave);
    }

    @Operation(summary = "Consultar todos os processos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProcessoResponseDto>> findAll() {
        return ResponseEntity.ok(processoService.findAll());
    }

    @Operation(summary = "Consultar um processo pelo número")
    @GetMapping(value= "/{numeroProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcessoResponseDto> findByProcesso(@PathVariable String numeroProcesso){
        return processoService.findByProcesso(numeroProcesso)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Filtrar um processo")
    @GetMapping(value= "/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProcessoResponseDto>> findWithFilter(
            @RequestParam(required = false) StatusProcesso status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAbertura,
            @RequestParam(required = false) String cnpjCpf
    ) {
        return ResponseEntity.ok(processoService.findWithFilter(status, dataAbertura, cnpjCpf));
    }

    @Operation(summary = "Arquivar um ou mais processos")
    @PatchMapping(value= "/arquivar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProcessoResponseDto>> archiveProcesso(@RequestBody List<String> processos) {
        List<ProcessoResponseDto> processosArquivados = processoService.arquivarProcessos(processos);
        return ResponseEntity.ok(processosArquivados);
    }

    @Operation(summary = "Deletar participante do processo")
    @DeleteMapping("/{numeroProcesso}/participante/{participanteId}")
    public ResponseEntity<Void> removerParticipante(
            @PathVariable String numeroProcesso,
            @PathVariable Long participanteId) {

        participanteService.deleteParticipanteDoProcesso(numeroProcesso, participanteId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar ação do processo")
    @DeleteMapping("/{numeroProcesso}/acao/{acaoId}")
    public ResponseEntity<Void> removerAcao(
            @PathVariable String numeroProcesso,
            @PathVariable Long acaoId) {

        acaoService.deleteAcaoDoProcesso(numeroProcesso, acaoId);
        return ResponseEntity.noContent().build();
    }

}
