package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Called Repository")
@ActiveProfiles("test")
class CalledRepositoryTest {

    @Autowired
    private CalledRepository repository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    @Test
    @DisplayName("Persist called when successful")
    void save_saveCalled_whenSuccessful(){
        Called called = calledBuilder();

        Client client = clientRepository.save(clientBuilder());

        Technician technician = technicianRepository.save(technicianBuilder());

        called.setClient(client);

        called.setTechnician(technician);

        Called save = repository.save(called);

        assertThat(save).isNotNull();

        assertThat(save.getId()).isNotNull();

        assertThat(save.getOpenDate()).isNotNull();

        assertThat(save.getClient()).isEqualTo(client);

        assertThat(save.getTechnician()).isEqualTo(technician);

        assertThat(save.getObservation()).isEqualTo(called.getObservation());

        assertThat(save.getTitle()).isEqualTo(called.getTitle());

        assertThat(save.getPriority()).isEqualTo(called.getPriority());

        assertThat(save.getStatus()).isEqualTo(called.getStatus());

        assertThat(save.getCloseDate()).isNull();
    }

    @Test
    @DisplayName("Return Called When Successful")
    void findById_ReturnCalled_WhenSuccessful(){
        Called called = calledBuilder();

        Client client = clientRepository.save(clientBuilder());

        Technician technician = technicianRepository.save(technicianBuilder());

        called.setClient(client);

        called.setTechnician(technician);

        Called save = repository.save(called);

        Optional<Called> findCalled = repository.findById(save.getId());

        assertThat(findCalled.get()).isNotNull();

        assertThat(findCalled.get().getOpenDate()).isNotNull();

        assertThat(findCalled.get().getCloseDate()).isNull();

        assertThat(findCalled.get().getTitle()).isEqualTo(called.getTitle());

        assertThat(findCalled.get().getObservation()).isEqualTo(called.getObservation());

        assertThat(findCalled.get().getStatus()).isEqualTo(called.getStatus());

        assertThat(findCalled.get().getPriority()).isEqualTo(called.getPriority());

        assertThat(findCalled.get().getTechnician()).isEqualTo(called.getTechnician());

        assertThat(findCalled.get().getClient()).isEqualTo(called.getClient());
    }

    @Test
    @DisplayName("Update Called When Successful")
    void persist_UpdateCalled_WhenSuccessful(){
        Called called = calledBuilder();

        Client client = clientRepository.save(clientBuilder());

        Technician technician = technicianRepository.save(technicianBuilder());

        called.setClient(client);

        called.setTechnician(technician);

        Called newCalled = repository.save(called);

        newCalled.setId(newCalled.getId());

        newCalled.setStatus(Status.CLOSED);

        newCalled.setTitle("Colocar SSD");

        newCalled.setObservation("HD inserido");

        newCalled.setPriority(Priority.HIGH);

        Called updated = repository.save(newCalled);

        assertThat(updated).isNotNull();

        assertThat(updated.getId()).isEqualTo(newCalled.getId());

        assertThat(updated.getTitle()).isEqualTo(newCalled.getTitle());

        assertThat(updated.getObservation()).isEqualTo(newCalled.getObservation());

        assertThat(updated.getOpenDate()).isEqualTo(newCalled.getOpenDate());

        assertThat(updated.getPriority()).isEqualTo(newCalled.getPriority());

        assertThat(updated.getStatus()).isEqualTo(newCalled.getStatus());
    }

    @Test
    @DisplayName("Delete Called When Successful")
    void delete_RemoveCalled_WhenSuccessful(){
        Called called = calledBuilder();

        Called save = repository.save(called);

        repository.deleteById(save.getId());

        Optional<Called> deletedCalled = repository.findById(save.getId());

        assertThat(deletedCalled).isEmpty();
    }

    private static Called calledBuilder() {
        Called called = new Called();
        called.setId(getUUid());
        called.setTitle("Colocar SSD");
        called.setObservation("Fazer a trocar do HD por SSD");
        called.setPriority(Priority.AVERAGE);
        called.setStatus(Status.PROGRESS);
        return called;
    }

    private static Client clientBuilder() {
        Client client = new Client();
        client.setId(getUUid());
        client.setName("José Client");
        client.setEmail("jose@client.com");
        client.setCpf("304.532.860-11");
        client.setPassword("123456");
        return client;
    }

    private static Technician technicianBuilder() {
        Technician technician = new Technician();
        technician.setId(getUUid());
        technician.setName("Antônio Technician");
        technician.setEmail("antonio@technician.com");
        technician.setCpf("032.036.540-91");
        technician.setPassword("123456");
        return technician;
    }
}