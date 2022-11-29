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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static String getUUid() {
        return UUID.randomUUID().toString();
    }

    public void instanceDatabase() {
        Technician t1 = new Technician(getUUid(), "Antônio Sousa", "702.830.330-61", "antonio@email.com", passwordEncoder.encode("123456"), null);
        Technician t2 = new Technician(getUUid(), "Guilherme Santana", "214.281.200-76", "guilherme@email.com", passwordEncoder.encode("123456"), null);
        Technician t3 = new Technician(getUUid(), "Maria Isis", "848.866.560-13", "maria@email.com", passwordEncoder.encode("123456"), null);
        Technician t4 = new Technician(getUUid(), "Donavan Santos", "474.862.540-69", "donavan@email.com", passwordEncoder.encode("123456"), null);
        Technician t5 = new Technician(getUUid(), "Elisa Martins", "501.341.040-13", "elisa@email.com", passwordEncoder.encode("123456"), null);
        Technician t6 = new Technician(getUUid(), "Leandro Araújo", "210.991.850-09", "leandro@email.com", passwordEncoder.encode("123456"), null);

        Client client1 = new Client(getUUid(), "José Rodrigues", "881.007.880-22", "jose@email.com", passwordEncoder.encode("123456"), null);
        Client client2 = new Client(getUUid(), "Renato Sousa", "258.639.400-34", "renato@email.com", passwordEncoder.encode("123456"), null);
        Client client3 = new Client(getUUid(), "Jardiele Santana", "312.838.240-94", "jardiele@email.com", passwordEncoder.encode("123456"), null);
        Client client4 = new Client(getUUid(), "Leonara Gomes", "102.237.210-69", "leonara@email.com", passwordEncoder.encode("123456"), null);

        Called called1 = new Called(getUUid(), "Trocar HD", "Colocar SDD", Priority.AVERAGE, Status.OPEN, t1, client1);

        technicianRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));
        clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4));
        calledRepository.saveAll(Arrays.asList(called1));
    }
}
