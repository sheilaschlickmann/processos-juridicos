package com.sheila.juridico.dto;

import com.sheila.juridico.model.TipoParticipante;

public record ParticipanteDto(Long id,
                              String nome,
                              String cnpjCpf,
                              TipoParticipante tipo,
                              String email,
                              String telefone) {
}
