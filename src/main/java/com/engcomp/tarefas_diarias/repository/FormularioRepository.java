package com.engcomp.tarefas_diarias.repository;

import com.engcomp.tarefas_diarias.entity.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FormularioRepository extends JpaRepository<Formulario, UUID> {
    List<Formulario> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT p FROM Formulario p " +
            "WHERE p.operador = :operador " +
            "AND p.data >= :dataInicio " +
            "AND p.data <= :dataFim")
    List<Formulario> buscarPorNomeEDatas(
            @Param("operador") String operador,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);


}
