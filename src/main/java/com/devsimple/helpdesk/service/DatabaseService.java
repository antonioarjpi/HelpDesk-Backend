package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import com.devsimple.helpdesk.repository.CalledRepository;
import com.devsimple.helpdesk.repository.ClientRepository;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class DatabaseService {

    @Autowired
    private CalledRepository calledRepository;
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private ClientRepository clientRepository;

    public static String getUUid() {
        return UUID.randomUUID().toString();
    }

    public void instanceDatabase() {
        Technician t1 = new Technician(getUUid(), "Antônio Sousa", "702.830.330-61", "antonio@email.com", "123456", null);
        Technician t2 = new Technician(getUUid(), "Guilherme Santana", "214.281.200-76", "guilherme@email.com", "123456", null);

        Client client1 = new Client(getUUid(), "Cliente", "619.664.570-65", "cliente@email.com", "123456", null);

        Called called1 = new Called(getUUid(), "Trocar HD", "Colocar SDD", Priority.AVERAGE, Status.OPEN, t1, client1);

        technicianRepository.saveAll(Arrays.asList(t1, t2));
        clientRepository.saveAll(Arrays.asList(client1));
        calledRepository.saveAll(Arrays.asList(called1));
    }
}
