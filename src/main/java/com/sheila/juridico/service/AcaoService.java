package com.sheila.juridico.service;

import com.sheila.juridico.model.Acao;

public interface AcaoService {
    Acao save(Acao acao);
    void deleteAcaoDoProcesso(String numeroProcesso, Long acaoId);
}

