package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.ClientDTO;
import com.devsimple.helpdesk.dto.ClientUpdateDTO;
import com.devsimple.helpdesk.exceptions.DataIntegratyViolationException;
import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import com.devsimple.helpdesk.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Client Service")
class ClientServiceTest {

    @SpyBean
    private ClientService service;

    @MockBean
    private ClientRepository repository;

    @BeforeEach
    void setUp() {
        List<Client> clients = new ArrayList<>(List.of(clientBuilder()));

        BDDMockito.when(repository.findAll(ArgumentMatchers.any(Example.class)))
                .thenReturn(clients);
        BDDMockito.when(repository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(clientBuilder()));
        BDDMockito.when(repository.save(ArgumentMatchers.any(Client.class)))
                .thenReturn(clientBuilder());
    }

    @Test
    @DisplayName("findById Return Client When Successful")
    void findById_ReturnClient_WhenSuccessful() {
        Client client = clientBuilder();

        Client result = service.findById(client.getId());

        assertThat(result).isNotNull();

        assertThat(result.getId()).isNotNull();

        assertThat(result.getId()).isEqualTo(client.getId());

        assertThat(result.getName()).isEqualTo(client.getName());

        assertThat(result.getEmail()).isEqualTo(client.getEmail());

        assertThat(result.getCpf()).isEqualTo(client.getCpf());

        assertThat(result.getPassword()).isEqualTo(client.getPassword());

        assertThat(result.getDateCadastre()).isEqualTo(client.getDateCadastre());
    }

    @Test
    @DisplayName("findByIdDTO Return Client DTO When Successful")
    void findByIdDTO_ReturnClientDTO_WhenSuccessful() {
        Client client = clientBuilder();

        ClientDTO result = service.findByIdDTO(client.getId());

        assertThat(result).isNotNull();

        assertThat(result.getId()).isNotNull();

        assertThat(result.getId()).isEqualTo(client.getId());

        assertThat(result.getName()).isEqualTo(client.getName());

        assertThat(result.getEmail()).isEqualTo(client.getEmail());

        assertThat(result.getCpf()).isEqualTo(client.getCpf());

        assertThat(result.getPassword()).isNull();

        assertThat(result.getDateCadastre()).isEqualTo(client.getDateCadastre());
    }

    @Test
    @DisplayName("FindAll Return List of Clients When Successful")
    void findAll_ReturnListOfClients_WhenSuccessful() {
        List<ClientDTO> result = service.findAll();

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Save Return Client Saved")
    void save_ReturnClientSaved_WhenSuccessful() {
        Client result = service.save(clientDTOBuilder());

        assertThat(result).isNotNull().isEqualTo(clientBuilder());
    }

    @Test
    @DisplayName("Update Return Client Saved")
    void update_UpdateClient_WhenSuccessful() {
        Client client = clientBuilder();

        Client update = service.update(client.getId(), clientDTOUpdateBuilder());

        assertThat(update).isNotNull().isEqualTo(client);
    }

    @Test
    @DisplayName("Update Return Client Saved")
    void update_FailureUpdate_WhenFail() {
        catchThrowableOfType(() -> service.update("notExist", clientDTOUpdateBuilder()), NullPointerException.class);
    }

    @Test
    @DisplayName("Delete Client When Succesfful")
    void delete_DeleteClient_WhenSuccessful() {
        Client client = clientBuilder();

        service.delete(client.getId());

        Mockito.verify(repository).deleteById(client.getId());
    }

    @Test
    @DisplayName("Delete Not Delete Client When Fail")
    void delete_NotDeleteClient_WhenFail() {
        Client client = clientBuilder();

        catchThrowableOfType(() -> service.delete("NotExist"), NullPointerException.class);

        Mockito.verify(repository, Mockito.never()).deleteById(client.getId());
    }

    @Test
    @DisplayName("Delete Do not delete when customer has tickets When Fail")
    void delete_DoNotDeleteWhenCustomerHasTickets_WhenFail() {
        Client client = clientBuilder();

        client.setCalleds(List.of(calledBuilder()));

        BDDMockito.when(repository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(client));

        catchThrowableOfType(() -> service.delete("id"), DataIntegratyViolationException.class);

        Mockito.verify(repository, Mockito.never()).deleteById(client.getId());
    }

    static Client clientBuilder() {
        Client client = new Client();
        client.setId("id");
        client.setName("Antônio Teste");
        client.setEmail("antonio@email.com");
        client.setCpf("032.036.540-91");
        client.setPassword("123456");
        return client;
    }

    static ClientDTO clientDTOBuilder() {
        ClientDTO client = new ClientDTO();
        client.setId("id");
        client.setName("Antônio Teste");
        client.setEmail("antonio@email.com");
        client.setCpf("032.036.540-91");
        client.setPassword("123456");
        return client;
    }

    static ClientUpdateDTO clientDTOUpdateBuilder() {
        ClientUpdateDTO client = new ClientUpdateDTO();
        client.setId("id");
        client.setName("Antônio Teste");
        client.setEmail("antonio@email.com");
        client.setCpf("032.036.540-91");
        return client;
    }

    private static Called calledBuilder() {
        Called called = new Called();
        called.setId("id");
        called.setTitle("Colocar SSD");
        called.setObservation("Fazer a trocar do HD por SSD");
        called.setPriority(Priority.AVERAGE);
        called.setStatus(Status.PROGRESS);
        return called;
    }
}