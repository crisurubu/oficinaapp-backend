package com.engcomp.tarefas_diarias.controller;


import com.engcomp.tarefas_diarias.controller.dto.FormularioDTO;
import com.engcomp.tarefas_diarias.entity.Formulario;
import com.engcomp.tarefas_diarias.service.FormularioService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/formulario")
@RequiredArgsConstructor
public class FormularioController {

    private final FormularioService service;
    private final FormularioMapper mapper;

    @PostMapping
    public ResponseEntity<Void>salvar(@RequestBody FormularioDTO dto){
        Formulario formulario = mapper.toEntity(dto);
        service.salvar(formulario);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/buscar")
    public List<Formulario> buscarPessoas(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "inicio", required = true) String dataInicio,
            @RequestParam(value = "fim", required = true) String dataFim) {

        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);
        if(nome == null || nome.isEmpty() || dataInicio == null || dataFim == null){
            throw new IllegalArgumentException("Nome e datas são obrigatórios");
        }

        return service.buscarPessoas(nome, inicio, fim);
    }
    @GetMapping("/semanal")
    public List<Formulario> buscarSemanal( @RequestParam(value = "nome", required = true) String nome,
                                           @RequestParam("inicio")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,

                                           @RequestParam("fim")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        if(nome == null || nome.isEmpty() || inicio == null || fim == null){
            throw new IllegalArgumentException("Nome e datas são obrigatórios");
        }

        return service.buscarPessoas(nome,inicio, fim);
    }

    @GetMapping("semanal-excel")
    public ResponseEntity<byte[]> exportarPedidosExcel(


            @RequestParam("nome") String nome,
            @RequestParam("inicio")
            @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            LocalDate inicio,

            @RequestParam("fim")
            @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            LocalDate fim) throws Exception {

        List<Formulario> semanal = service.buscarPessoas(nome, inicio, fim);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório semanal "+nome);

        // Estilo do cabeçalho
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        // Criando cabeçalho
        Row header = sheet.createRow(0);
        String[] colunas = {"Data", "Veículo", "Projeto", "Início", "Fim", "Descrição"};
        for (int i = 0; i < colunas.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(colunas[i]);
            cell.setCellStyle(headerStyle);
        }

        // Preenchendo linhas
        int rowIdx = 1;
        for (Formulario p : semanal) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(p.getData().toString());
            row.createCell(1).setCellValue(p.getVeiculo().toString());
            row.createCell(2).setCellValue(p.getProjeto().toString());
            row.createCell(3).setCellValue(p.getInicio().toString());
            row.createCell(4).setCellValue(p.getFim().toString());
            row.createCell(5).setCellValue(p.getDescricao().toString());
        }

        // Ajustando tamanho das colunas
        for (int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Convertendo para byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=semanal.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(baos.toByteArray());
    }



}
