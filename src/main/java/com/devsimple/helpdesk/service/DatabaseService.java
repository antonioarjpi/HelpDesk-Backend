package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Profile;
import com.devsimple.helpdesk.model.enums.Status;
import com.devsimple.helpdesk.repository.CalledRepository;
import com.devsimple.helpdesk.repository.ClientRepository;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Technician t1 = new Technician(getUUid(), "Antônio Sousa Araújo", "702.830.330-61", "antonio@email.com", passwordEncoder.encode("123456"), null);
        t1.addProfile(Profile.ADMIN);
        Technician t2 = new Technician(getUUid(), "Guilherme Santana Alves", "214.281.200-76", "guilherme@email.com", passwordEncoder.encode("123456"), null);
        Technician t3 = new Technician(getUUid(), "Maria Isis Valverde Santos", "848.866.560-13", "maria@email.com", passwordEncoder.encode("123456"), null);
        Technician t4 = new Technician(getUUid(), "Donavan Santos da Silva", "474.862.540-69", "donavan@email.com", passwordEncoder.encode("123456"), null);
        Technician t5 = new Technician(getUUid(), "Elisa Martins Soares", "501.341.040-13", "elisa@email.com", passwordEncoder.encode("123456"), null);
        Technician t6 = new Technician(getUUid(), "Leandro Araújo", "967.009.080-60", "leandro@email.com", passwordEncoder.encode("123456"), null);

        Client client1 = new Client(getUUid(), "José Rodrigues", "881.007.880-22", "jose@email.com", passwordEncoder.encode("123456"), null);
        Client client2 = new Client(getUUid(), "Renato Sousa", "258.639.400-34", "renato@email.com", passwordEncoder.encode("123456"), null);
        Client client3 = new Client(getUUid(), "Jardiele Santana", "312.838.240-94", "jardiele@email.com", passwordEncoder.encode("123456"), null);
        Client client4 = new Client(getUUid(), "Leonara Gomes", "102.237.210-69", "leonara@email.com", passwordEncoder.encode("123456"), null);
        Client client5 = new Client(getUUid(), "Lahra Silva", "153.878.460-20", "larha@email.com", passwordEncoder.encode("123456"), null);
        Client client6 = new Client(getUUid(), "Miguel Santos Silva", "327.732.530-00", "miguel@email.com", passwordEncoder.encode("123456"), null);
        Client client7 = new Client(getUUid(), "Antônio Rodrigues Sousa", "320.991.210-60", "antoniorodrigues@email.com", passwordEncoder.encode("123456"), null);
        Client client8 = new Client(getUUid(), "Cândido José Araújo", "749.739.240-91", "candido@email.com", passwordEncoder.encode("123456"), null);
        Client client9 = new Client(getUUid(), "Hilson Pereira Sampaio", "699.143.310-17", "hilson@email.com", passwordEncoder.encode("123456"), null);
        Client client10 = new Client(getUUid(), "Robert Bandeira", "571.090.940-81", "robert@email.com", passwordEncoder.encode("123456"), null);
        Client client11 = new Client(getUUid(), "Daniel Sousa e Silva", "892.740.960-46", "daniel@email.com", passwordEncoder.encode("123456"), null);
        Client client12 = new Client(getUUid(), "Leopoldo Melo", "827.700.340-49", "leopoldo@email.com", passwordEncoder.encode("123456"), null);
        Client client13 = new Client(getUUid(), "José Francisco", "545.594.710-96", "josefrancisco@email.com", passwordEncoder.encode("123456"), null);
        Client client14 = new Client(getUUid(), "Caio Antunes Aragão", "397.088.970-78", "caio@email.com", passwordEncoder.encode("123456"), null);
        Client client15 = new Client(getUUid(), "Robert Antônio da Silveira", "302.250.110-26", "robertbandeira@email.com", passwordEncoder.encode("123456"), null);
        Client client16 = new Client(getUUid(), "Ricardo Melo Gomes da Silva", "876.381.590-71", "ricardo@email.com", passwordEncoder.encode("123456"), null);
        Client client17 = new Client(getUUid(), "Adenor Oliveira Martins", "042.180.410-67", "adenor@email.com", passwordEncoder.encode("123456"), null);
        Client client18 = new Client(getUUid(), "José Carlos Figueira", "618.500.970-62", "jcarlos@email.com", passwordEncoder.encode("123456"), null);
        Client client19 = new Client(getUUid(), "Alisson Santos Rodrigues", "360.048.020-00", "alisson@email.com", passwordEncoder.encode("123456"), null);
        Client client20 = new Client(getUUid(), "Gisele Silva", "569.789.870-34", "gisele@email.com", passwordEncoder.encode("123456"), null);
        Client client21 = new Client(getUUid(), "Maria Francisca da Silva Araújo", "509.703.550-09", "@email.com", passwordEncoder.encode("123456"), null);
        Client client22 = new Client(getUUid(), "Francisca Maria de Castro", "573.828.550-66", "fmaria@email.com", passwordEncoder.encode("123456"), null);
        Client client23 = new Client(getUUid(), "Rita de Cássia", "178.910.450-54", "rita@email.com", passwordEncoder.encode("123456"), null);

        Called called1 = new Called(getUUid(), "Verificar lentidão", "Verificar lentidão, liberado para colocar SSD", Priority.AVERAGE, Status.OPEN, t1, client1);
        Called called2 = new Called(getUUid(), "Trocar Bateria de carregamento", "Trocar Bateria", Priority.LOW, Status.PROGRESS, t2, client2);
        Called called3 = new Called(getUUid(), "Display Queimado", "Trocar tela", Priority.LOW, Status.OPEN, t3, client3);
        Called called4 = new Called(getUUid(), "Instalar SSD", "Trocar o HD por SSD", Priority.HIGH, Status.CLOSED, t1, client3);
        Called called5 = new Called(getUUid(), "Trocar teclado do notebook", "Efetuar troca do teclado", Priority.LOW, Status.CLOSED, t1, client19);
        Called called6 = new Called(getUUid(), "Verificar lentidão", "Verificar lentidão, liberado para colocar SSD", Priority.AVERAGE, Status.CLOSED, t4, client18);
        Called called7 = new Called(getUUid(), "Computador não liga", "Consertar computador que não liga", Priority.HIGH, Status.CLOSED, t4, client23);
        Called called8 = new Called(getUUid(), "Computador lento", "Melhorar o desempenho do computador", Priority.AVERAGE, Status.CLOSED, t1, client13);
        Called called9 = new Called(getUUid(), "Notebook apresenta bolas na tela", "Verificar display, e se necessário, trocar a tela do notebook", Priority.AVERAGE, Status.CLOSED, t4, client14);
        Called called10 = new Called(getUUid(), "Verificar lentidão", "Verificar lentidão, liberado para colocar SSD", Priority.AVERAGE, Status.CLOSED, t3, client21);
        Called called11 = new Called(getUUid(), "Touch Pad não funciona", "Consertar o touch do note", Priority.LOW, Status.OPEN, t5, client20);

        List<String> technicianList = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            technicianList.add("t" + i);
        }

        technicianRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));
        clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4, client5, client6, client7, client8, client9, client10, client11, client12, client13, client14, client15, client16,
                client17, client18, client19, client20, client21, client22, client23));
        calledRepository.saveAll(Arrays.asList(called1, called2, called5, called3, called4, called6, called7, called8, called9, called10, called11));
    }
}
