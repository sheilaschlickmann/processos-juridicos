package com.sheila.juridico.service;

import com.sheila.juridico.model.Participante;
import org.springframework.stereotype.Service;

@Service
public interface ParticipanteService {
    void deleteParticipanteDoProcesso(String numeroProcesso, Long participanteId);
}
