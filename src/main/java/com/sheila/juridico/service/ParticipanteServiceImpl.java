package com.sheila.juridico.service;

import com.sheila.juridico.repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{

    final ParticipanteRepository participanteRepository;

    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Override
    @Transactional
    public void deleteParticipanteDoProcesso(String numeroProcesso, Long participanteId) {
        participanteRepository.deleteByIdAndProcessoNumeroProcesso(participanteId, numeroProcesso);
    }
}
