package com.sheila.juridico.service;

import com.sheila.juridico.model.Acao;
import com.sheila.juridico.repository.AcaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AcaoServiceImpl implements AcaoService{

    final AcaoRepository acaoRepository;

    public AcaoServiceImpl(AcaoRepository acaoRepository) {
        this.acaoRepository = acaoRepository;
    }

    @Override
    public Acao save(Acao acao){
        return acaoRepository.save(acao);
    }

    @Override
    @Transactional
    public void deleteAcaoDoProcesso(String numeroProcesso, Long acaoId) {
        acaoRepository.deleteByIdAndProcessoNumeroProcesso(acaoId, numeroProcesso);
    }
}
