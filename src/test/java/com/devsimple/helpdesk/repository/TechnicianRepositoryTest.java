package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.Technician;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@DisplayName("Technician Repository")
@ActiveProfiles("test")
class TechnicianRepositoryTest {

    @Autowired
    private TechnicianRepository repository;

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    @Test
    @DisplayName("Persist technician when successful")
    void save_saveTechnician_whenSuccessful(){
        Technician technician = technicianBuilder();

        Technician save = repository.save(technician);

        assertThat(save).isNotNull();

        assertThat(save.getId()).isNotNull();

        assertThat(save.getName()).isEqualTo(technician.getName());

        assertThat(save.getEmail()).isEqualTo(technician.getEmail());

        assertThat(save.getPassword()).isEqualTo(technician.getPassword());
    }

    @Test
    @DisplayName("Return Technician When Successful")
    void findById_ReturnTechnician_WhenSuccessful(){
        Technician technician = technicianBuilder();

        Technician save = repository.save(technician);

        Optional<Technician> findTech = repository.findById(save.getId());

        assertThat(findTech.get()).isNotNull();

        assertThat(findTech.get().getId()).isEqualTo(save.getId());

        assertThat(findTech.get().getName()).isEqualTo(save.getName());

        assertThat(findTech.get().getEmail()).isEqualTo(save.getEmail());
    }

    @Test
    @DisplayName("Update Technician When Successful")
    void persist_UpdateTechnician_WhenSuccessful(){
        Technician technician = technicianBuilder();

        Technician newTechnician = repository.save(technician);

        newTechnician.setId(newTechnician.getId());

        newTechnician.setName("Novo Nome");

        newTechnician.setEmail("novo@novo.com");

        newTechnician.setCpf("349.720.220-78");

        Technician updated = repository.save(newTechnician);

        assertThat(updated).isNotNull();

        assertThat(updated.getId()).isEqualTo(newTechnician.getId());

        assertThat(updated.getName()).isEqualTo(newTechnician.getName());

        assertThat(updated.getEmail()).isEqualTo(newTechnician.getEmail());

        assertThat(updated.getCpf()).isEqualTo(newTechnician.getCpf());
    }

    @Test
    @DisplayName("Delete Technician When Successful")
    void delete_RemoveTechnician_WhenSuccessful(){
        Technician technician = technicianBuilder();

        Technician save = repository.save(technician);

        repository.deleteById(save.getId());

        Optional<Technician> deletedTechnician = repository.findById(save.getId());

        assertThat(deletedTechnician).isEmpty();
    }

    private static Technician technicianBuilder() {
        Technician technician = new Technician();
        technician.setId(getUUid());
        technician.setName("Antônio Teste");
        technician.setEmail("antonio@email.com");
        technician.setCpf("032.036.540-91");
        technician.setPassword("123456");
        return technician;
    }

}