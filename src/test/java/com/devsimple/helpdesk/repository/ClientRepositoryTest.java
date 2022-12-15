package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Client Repository")
@ActiveProfiles("test")
class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    @Test
    @DisplayName("Persist client when successful")
    void save_saveClient_whenSuccessful(){
        Client client = clientBuilder();

        Client save = repository.save(client);

        assertThat(save).isNotNull();

        assertThat(save.getId()).isNotNull();

        assertThat(save.getName()).isEqualTo(client.getName());

        assertThat(save.getEmail()).isEqualTo(client.getEmail());

        assertThat(save.getPassword()).isEqualTo(client.getPassword());
    }

    @Test
    @DisplayName("Return Client When Successful")
    void findById_ReturnClient_WhenSuccessful(){
        Client client = clientBuilder();

        Client save = repository.save(client);

        Optional<Client> findClient = repository.findById(save.getId());

        assertThat(findClient.get()).isNotNull();

        assertThat(findClient.get().getId()).isEqualTo(save.getId());

        assertThat(findClient.get().getName()).isEqualTo(save.getName());

        assertThat(findClient.get().getEmail()).isEqualTo(save.getEmail());
    }

    @Test
    @DisplayName("Update Client When Successful")
    void persist_UpdateClient_WhenSuccessful(){
        Client client = clientBuilder();

        Client newClient = repository.save(client);

        newClient.setId(newClient.getId());

        newClient.setName("Novo Nome");

        newClient.setEmail("novo@novo.com");

        newClient.setCpf("349.720.220-78");

        Client updated = repository.save(newClient);

        assertThat(updated).isNotNull();

        assertThat(updated.getId()).isEqualTo(newClient.getId());

        assertThat(updated.getName()).isEqualTo(newClient.getName());

        assertThat(updated.getEmail()).isEqualTo(newClient.getEmail());

        assertThat(updated.getCpf()).isEqualTo(newClient.getCpf());
    }

    @Test
    @DisplayName("Delete Client When Successful")
    void delete_RemoveClient_WhenSuccessful(){
        Client client = clientBuilder();

        Client save = repository.save(client);

        repository.deleteById(save.getId());

        Optional<Client> deletedClient = repository.findById(save.getId());

        assertThat(deletedClient).isEmpty();
    }

    private static Client clientBuilder() {
        Client client = new Client();
        client.setId(getUUid());
        client.setName("Antônio Teste");
        client.setEmail("antonio@email.com");
        client.setCpf("032.036.540-91");
        client.setPassword("123456");
        return client;
    }
}