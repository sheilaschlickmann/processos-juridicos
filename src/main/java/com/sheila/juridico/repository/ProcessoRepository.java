package com.sheila.juridico.repository;

import com.sheila.juridico.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<Processo, Long>, JpaSpecificationExecutor<Processo> {

    Optional<Processo> findByNumeroProcesso(String processo);
    List<Processo> findAllByNumeroProcessoIn(List<String> processos);

}
