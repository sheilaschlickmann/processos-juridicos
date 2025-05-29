package com.sheila.juridico.mapper;


import com.sheila.juridico.dto.AcaoDto;
import com.sheila.juridico.dto.ParticipanteDto;
import com.sheila.juridico.dto.ProcessoRequestDto;
import com.sheila.juridico.dto.ProcessoResponseDto;
import com.sheila.juridico.model.Acao;
import com.sheila.juridico.model.Participante;
import com.sheila.juridico.model.Processo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessoMapper {

    private static Participante toEntityParticipante(ParticipanteDto dto) {
        Participante participante = new Participante();
        participante.setNomeCompleto(dto.nome());;
        participante.setCnpjCpf(dto.cnpjCpf());
        participante.setTipo(dto.tipo());
        participante.setEmail(dto.email());
        participante.setTelefone(dto.telefone());
        return participante;
    }

    private static Acao toEntityAcao(AcaoDto dto) {
        Acao acao = new Acao();
        acao.setTipo(dto.tipo());
        acao.setDataRegistro(dto.dataRegistro());
        acao.setDescricao(dto.descricao());
        return acao;
    }

    public static Processo toEntity(ProcessoRequestDto dto){
        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.numeroProcesso());
        processo.setDescricao(dto.descricao());
        processo.setDataAbertura(dto.dataAbertura());
        processo.setStatus(dto.status());

        List<Participante> participantes = dto.participantes() == null ? List.of() :
                dto.participantes().stream()
                        .map(ProcessoMapper::toEntityParticipante)
                        .collect(Collectors.toList());

        // Setar o processo pai para cada participante
        participantes.forEach(p -> p.setProcesso(processo));
        processo.setParticipantes(participantes);

        // Mapear ações
        List<Acao> acoes = dto.acoes() == null ? List.of() :
                dto.acoes().stream()
                        .map(ProcessoMapper::toEntityAcao)
                        .collect(Collectors.toList());

        // Setar o processo pai para cada ação
        acoes.forEach(a -> a.setProcesso(processo));
        processo.setAcoes(acoes);

        return processo;

    }

    private ParticipanteDto toParticipanteDto(Participante participante){
        return new ParticipanteDto(
                participante.getId(),
                participante.getNomeCompleto(),
                participante.getCnpjCpf(),
                participante.getTipo(),
                participante.getEmail(),
                participante.getTelefone()
        );
    }

    private AcaoDto toAcaoDto(Acao acao) {
        return new AcaoDto(
                acao.getId(),
                acao.getTipo(),
                acao.getDataRegistro(),
                acao.getDescricao()
        );
    }

    public ProcessoResponseDto toDto(Processo processo){
        return new ProcessoResponseDto(
                processo.getId(),
                processo.getNumeroProcesso(),
                processo.getDescricao(),
                processo.getDataAbertura(),
                processo.getStatus(),
                processo.getParticipantes()
                        .stream()
                        .map(this::toParticipanteDto)
                        .collect(Collectors.toList()),
                processo.getAcoes().stream()
                        .map(this::toAcaoDto)
                        .collect(Collectors.toList()));
    }



}
