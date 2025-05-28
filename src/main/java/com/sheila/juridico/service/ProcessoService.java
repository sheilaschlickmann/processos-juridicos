package com.sheila.juridico.service;

import com.sheila.juridico.model.Processo;
import com.sheila.juridico.model.StatusProcesso;
import com.sheila.juridico.repository.ProcessoRepository;

import java.util.List;
import java.util.Optional;

public class ProcessoService {
    private final ProcessoRepository processoRepository;

    public ProcessoService(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    public Processo save(Processo processo){
        return processoRepository.save(processo);
    }

    public void archive(Long id){
        Processo processo = processoRepository.findById(id).orElseThrow(()-> new RuntimeException("Processo não encontrado!"));
        processo.setStatus(StatusProcesso.valueOf("ARQUIVADO"));
        processoRepository.save(processo);
    }

    public List<Processo> findAll(){
        return processoRepository.findAll();
    }

    public Optional<Processo> finbByProcesso(String processo){
        return processoRepository.findByNumeroProcesso(processo);
    }

}
