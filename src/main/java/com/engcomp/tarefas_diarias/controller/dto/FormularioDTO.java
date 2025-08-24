package com.engcomp.tarefas_diarias.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record FormularioDTO(
        UUID id,
        String operador,
        LocalDate data,
        String veiculo,
        String projeto,
        String inicio,
        String fim,
        String descricao
) {
}
