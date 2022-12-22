package com.devsimple.helpdesk.controller;

import com.devsimple.helpdesk.service.SheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/sheets")
public class SheetController {

    private SheetService service;

    public SheetController(SheetService service) {
        this.service = service;
    }

    @GetMapping("/{value}")
    public ResponseEntity planilhaInscricaoXcurso(@PathVariable String value, HttpServletResponse response) throws Throwable {
        String filename = "";

        if (value.equals("0")) {
            service.sheetsAllClients();
            filename = "clients.xlsx";
        } else if (value.equals("1")) {
            service.sheetsAllTechnicians();
            filename = "technicians.xlsx";
        } else {
            service.sheetsAllCalled();
            filename = "calleds.xlsx";
        }

        Path arquivo = Paths.get(filename);

        if (Files.exists(arquivo)) {
            response.addHeader("Content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentType("application/" + filename);
        }
        try {
            Files.copy(arquivo, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .ok()
                .build();
    }
}
