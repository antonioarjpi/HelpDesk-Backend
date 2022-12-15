package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("User Repository Test")
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Return User Type Technician When find CPF When Successful")
    void findByCpf_returnTechnicianWhenFindCPF_WhenSuccessful() {
        Technician technician = technicianBuilder();

        Optional<User> findTechnicianByEmail = repository.findByCpf("032.036.540-91");

        assertThat(findTechnicianByEmail).isNotNull();

        assertThat(findTechnicianByEmail.get().getId()).isEqualTo(technician.getId());

        assertThat(findTechnicianByEmail.get().getEmail()).isEqualTo(technician.getEmail());

        assertThat(findTechnicianByEmail.get().getCpf()).isEqualTo(technician.getCpf());

        assertThat(findTechnicianByEmail.get().getDateCadastre()).isEqualTo(technician.getDateCadastre());
    }

    @Test
    @DisplayName("Return User Type Client When find CPF When Successful")
    void findByCpf_returnClientWhenFindCPF_WhenSuccessful() {
        Client client = clientBuilder();

        Optional<User> findClientByEmail = repository.findByCpf("304.532.860-11");

        assertThat(findClientByEmail).isNotNull();

        assertThat(findClientByEmail.get().getId()).isEqualTo(client.getId());

        assertThat(findClientByEmail.get().getEmail()).isEqualTo(client.getEmail());

        assertThat(findClientByEmail.get().getCpf()).isEqualTo(client.getCpf());

        assertThat(findClientByEmail.get().getDateCadastre()).isEqualTo(client.getDateCadastre());
    }

    @Test
    @DisplayName("Return User Type Technician When find E-mail When Successful")
    void findByEmail_returnTechnicianWhenFindEmail_WhenSuccessful() {
        Technician technician = technicianBuilder();

        Optional<User> findTechnicianByEmail = repository.findByEmail("antonio@technician.com");

        assertThat(findTechnicianByEmail).isNotNull();

        assertThat(findTechnicianByEmail.get().getId()).isEqualTo(technician.getId());

        assertThat(findTechnicianByEmail.get().getEmail()).isEqualTo(technician.getEmail());

        assertThat(findTechnicianByEmail.get().getCpf()).isEqualTo(technician.getCpf());

        assertThat(findTechnicianByEmail.get().getDateCadastre()).isEqualTo(technician.getDateCadastre());
    }

    @Test
    @DisplayName("Return User Type Client When find E-mail When Successful")
    void findByEmail_returnClientWhenFindEmail_WhenSuccessful() {
        Client client = clientBuilder();

        Optional<User> findClientByEmail = repository.findByEmail("jose@client.com");

        assertThat(findClientByEmail).isNotNull();

        assertThat(findClientByEmail.get().getId()).isEqualTo(client.getId());

        assertThat(findClientByEmail.get().getEmail()).isEqualTo(client.getEmail());

        assertThat(findClientByEmail.get().getCpf()).isEqualTo(client.getCpf());

        assertThat(findClientByEmail.get().getDateCadastre()).isEqualTo(client.getDateCadastre());
    }

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    private Client clientBuilder() {
        Client client = new Client();
        client.setId(getUUid());
        client.setName("José Client");
        client.setEmail("jose@client.com");
        client.setCpf("304.532.860-11");
        client.setPassword("123456");
        return clientRepository.save(client);
    }

    private Technician technicianBuilder() {
        Technician technician = new Technician();
        technician.setId(getUUid());
        technician.setName("Antônio Technician");
        technician.setEmail("antonio@technician.com");
        technician.setCpf("032.036.540-91");
        technician.setPassword("123456");
        return technicianRepository.save(technician);
    }
}