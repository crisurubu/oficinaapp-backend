package com.engcomp.tarefas_diarias.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "formulario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formulario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "operador")
    private String operador;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private LocalDate data;
    @Column(name = "veiculo", length = 30, nullable = false)
    private String veiculo;
    @Column(name = "projeto")
    private String projeto;
    @Column(name = "inicio")
    private String inicio;
    @Column(name = "fim")
    private String fim;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
