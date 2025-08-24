package com.engcomp.tarefas_diarias.service;

import com.engcomp.tarefas_diarias.entity.Formulario;
import com.engcomp.tarefas_diarias.repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository repository;

    public Formulario salvar(Formulario formulario){
        return repository.save(formulario);
    }
    public List<Formulario> buscarSemanal(LocalDate dtinicio, LocalDate dtfim){
        return repository.findByDataBetween(dtinicio, dtfim);

    }
    public List<Formulario> buscarPessoas(String operador, LocalDate dataInicio, LocalDate dataFim) {
        return repository.buscarPorNomeEDatas(operador, dataInicio, dataFim);
    }


}
