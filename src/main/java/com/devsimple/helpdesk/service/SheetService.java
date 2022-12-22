package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.CalledDTO;
import com.devsimple.helpdesk.dto.ClientDTO;
import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.repository.CalledRepository;
import com.devsimple.helpdesk.repository.ClientRepository;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SheetService {

    private CalledRepository calledRepository;
    private ClientRepository clientRepository;
    private TechnicianRepository technicianRepository;

    public SheetService(CalledRepository calledRepository, ClientRepository clientRepository, TechnicianRepository technicianRepository) {
        this.calledRepository = calledRepository;
        this.clientRepository = clientRepository;
        this.technicianRepository = technicianRepository;
    }

    public void sheetsAllClients() throws Throwable {
        List<ClientDTO> clients = clientRepository.findAll()
                .stream()
                .map(x -> new ClientDTO(x))
                .collect(Collectors.toList());

        try (var workbook = new XSSFWorkbook();
             var outputStream = new FileOutputStream("clients.xlsx")) {
            var sheet = workbook.createSheet("Planilha 1");
            int numberLine = 0;

            List<String> header = new ArrayList<>();
            header.add("Nome");
            header.add("E-mail");
            header.add("CPF");
            header.add("Data de cadastro");

            addHeader(sheet, numberLine++, header, workbook);

            for (ClientDTO dto : clients) {
                var line = sheet.createRow(numberLine++);
                addCell(line, 0, dto.getName());
                addCell(line, 1, dto.getEmail());
                addCell(line, 2, dto.getCpf());
                addCell(line, 3, dto.getDateCadastre().toString());
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new Exception().getCause();
        }
    }

    public void sheetsAllTechnicians() throws Throwable {
        List<TechnicianDTO> technicians = technicianRepository.findAll()
                .stream()
                .map(x -> new TechnicianDTO(x))
                .collect(Collectors.toList());

        try (var workbook = new XSSFWorkbook();
             var outputStream = new FileOutputStream("technicians.xlsx")) {
            var sheet = workbook.createSheet("Planilha 1");
            int numberLine = 0;

            List<String> header = new ArrayList<>();
            header.add("Nome");
            header.add("E-mail");
            header.add("CPF");
            header.add("Data de cadastro");

            addHeader(sheet, numberLine++, header, workbook);

            for (TechnicianDTO dto : technicians) {
                var line = sheet.createRow(numberLine++);
                addCell(line, 0, dto.getName());
                addCell(line, 1, dto.getEmail());
                addCell(line, 2, dto.getCpf());
                addCell(line, 3, dto.getDateCadastre().toString());
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new Exception().getCause();
        }
    }

    public void sheetsAllCalled() throws Throwable {
        List<CalledDTO> calleds = calledRepository.findAll()
                .stream()
                .map(x -> new CalledDTO(x))
                .collect(Collectors.toList());

        try (var workbook = new XSSFWorkbook();
             var outputStream = new FileOutputStream("calleds.xlsx")) {
            var sheet = workbook.createSheet("Planilha 1");
            int numberLine = 0;

            List<String> header = new ArrayList<>();
            header.add("Titulo");
            header.add("Descrição");
            header.add("Prioridade");
            header.add("Status");
            header.add("Data de abertura");
            header.add("Técnico");
            header.add("Técnico E-mail");
            header.add("Cliente");
            header.add("Cliente e-mail");
            header.add("Data de fechamento");

            addHeader(sheet, numberLine++, header, workbook);

            for (CalledDTO dto : calleds) {
                var line = sheet.createRow(numberLine++);
                addCell(line, 0, dto.getTitle());
                addCell(line, 1, dto.getObservation());
                if (dto.getPriority().toString().equals("0")){
                    addCell(line, 2, "Baixa");
                }else if (dto.getPriority().toString().equals("1")){
                    addCell(line, 2, "Média");
                }else{
                    addCell(line, 2, "Alta");
                }
                if (dto.getStatus().toString().equals("0")){
                    addCell(line, 3, "Aberto");
                }else if (dto.getStatus().toString().equals("1")){
                    addCell(line, 3, "Em Andamento");
                }else{
                    addCell(line, 3, "Fechado");
                }
                addCell(line, 4, dto.getOpenDate().toString());
                addCell(line, 5, dto.getTechnicianName());
                addCell(line, 6, dto.getTechnicianEmail());
                addCell(line, 7, dto.getClientName());
                addCell(line, 8, dto.getClientEmail());
                if (dto.getCloseDate() != null) {
                    addCell(line, 9, dto.getCloseDate().toString());
                }
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new Exception().getCause();
        }
    }

    private void addHeader(XSSFSheet sheet, int numberLine, List<String> header, XSSFWorkbook wb) {
        var line = sheet.createRow(numberLine);
        for (int i = 0; i < header.size(); i++) {
            addCellHeader(line, i, header.get(i), wb);
        }
    }
    private void addCell(Row line, int column, String value) {
        Cell cell = line.createCell(column);
        cell.setCellValue(value);
    }

    private void addCellHeader(Row line, int column, String value, XSSFWorkbook wb) {
        XSSFCellStyle style1 = wb.createCellStyle();

        Font font = wb.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(51, 50, 50), new DefaultIndexedColorMap()));

        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style1.setFont(font);

        Cell cell = line.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style1);
    }
}
