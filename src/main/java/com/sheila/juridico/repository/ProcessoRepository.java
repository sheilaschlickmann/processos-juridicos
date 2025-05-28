package com.sheila.juridico.repository;

import com.sheila.juridico.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<Processo, Long>{

    List<Processo> findByStatus(String status);
    List<Processo> findByDataAbertura(LocalDate data);
    List<Processo> findByParticipantesCnpjCpf(String cnpjCpf);
    Optional<Processo> findByNumeroProcesso(String processo);

}
