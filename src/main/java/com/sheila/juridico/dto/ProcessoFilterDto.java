package com.sheila.juridico.dto;

import com.sheila.juridico.model.StatusProcesso;

import java.time.LocalDate;

public record ProcessoFilterDto(StatusProcesso status,
                                LocalDate dataAbertura,
                                String cnpjCpf
) {
}
