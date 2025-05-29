package com.sheila.juridico.dto;

import com.sheila.juridico.model.TipoAcao;

import java.time.LocalDateTime;

public record AcaoDto(Long id,
                      TipoAcao tipo,
                      LocalDateTime dataRegistro,
                      String descricao) {
}
