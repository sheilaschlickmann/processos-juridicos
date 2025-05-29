package com.sheila.juridico.dto;

import com.sheila.juridico.model.StatusProcesso;

import java.time.LocalDateTime;
import java.util.List;

public record ProcessoRequestDto(String numeroProcesso,
                                 String descricao,
                                 LocalDateTime dataAbertura,
                                 StatusProcesso status,
                                 List<ParticipanteDto> participantes,
                                 List<AcaoDto> acoes) {
}
