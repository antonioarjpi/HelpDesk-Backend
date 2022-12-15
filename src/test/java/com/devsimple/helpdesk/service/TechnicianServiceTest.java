package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.dto.TechnicianUpdateDTO;
import com.devsimple.helpdesk.exceptions.DataIntegratyViolationException;
import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import com.devsimple.helpdesk.repository.TechnicianRepository;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Technician Service")
class TechnicianServiceTest {

    @SpyBean
    private TechnicanService service;
    @MockBean
    private TechnicianRepository repository;

    @BeforeEach
    void setUp() {
        List<Technician> technicians = new ArrayList<>(List.of(technicianBuilder()));

        BDDMockito.when(repository.findAll(ArgumentMatchers.any(Example.class)))
                .thenReturn(technicians);
        BDDMockito.when(repository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(technicianBuilder()));
        BDDMockito.when(repository.save(ArgumentMatchers.any(Technician.class)))
                .thenReturn(technicianBuilder());
    }

    @Test
    @DisplayName("findById Return Technician When Successful")
    void findById_ReturnTechnician_WhenSuccessful() {
        Technician technician = technicianBuilder();

        Technician result = service.findById(technician.getId());

        assertThat(result).isNotNull();

        assertThat(result.getId()).isNotNull();

        assertThat(result.getId()).isEqualTo(technician.getId());

        assertThat(result.getName()).isEqualTo(technician.getName());

        assertThat(result.getEmail()).isEqualTo(technician.getEmail());

        assertThat(result.getCpf()).isEqualTo(technician.getCpf());

        assertThat(result.getPassword()).isEqualTo(technician.getPassword());

        assertThat(result.getDateCadastre()).isEqualTo(technician.getDateCadastre());
    }

    @Test
    @DisplayName("findByIdDTO Return Technician DTO When Successful")
    void findByIdDTO_ReturnTechnicianDTO_WhenSuccessful() {
        Technician technician = technicianBuilder();

        TechnicianDTO result = service.findByIdDTO(technician.getId());

        assertThat(result).isNotNull();

        assertThat(result.getId()).isNotNull();

        assertThat(result.getId()).isEqualTo(technician.getId());

        assertThat(result.getName()).isEqualTo(technician.getName());

        assertThat(result.getEmail()).isEqualTo(technician.getEmail());

        assertThat(result.getCpf()).isEqualTo(technician.getCpf());

        assertThat(result.getPassword()).isNull();

        assertThat(result.getDateCadastre()).isEqualTo(technician.getDateCadastre());
    }

    @Test
    @DisplayName("FindAll Return List of Technicians When Successful")
    void findAll_ReturnListOfTechnicians_WhenSuccessful() {
        List<TechnicianDTO> result = service.findAll();

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Save Return Technician Saved")
    void save_ReturnTechnicianSaved_WhenSuccessful() {
        Technician result = service.save(technicianDTOBuilder());

        assertThat(result).isNotNull().isEqualTo(technicianBuilder());
    }

    @Test
    @DisplayName("Update Return Technician Saved")
    void update_UpdateTechnician_WhenSuccessful() {
        Technician technician = technicianBuilder();

        Technician update = service.update(technician.getId(), technicianDTOUpdateBuilder());

        assertThat(update).isNotNull().isEqualTo(technician);
    }

    @Test
    @DisplayName("Update Return Technician Saved")
    void update_FailureUpdate_WhenFail() {
        catchThrowableOfType(() -> service.update("notExist", technicianDTOUpdateBuilder()), NullPointerException.class);
    }

    @Test
    @DisplayName("Delete Technician When Succesfful")
    void delete_DeleteTechnician_WhenSuccessful() {
        Technician technician = technicianBuilder();

        service.delete(technician.getId());

        Mockito.verify(repository).deleteById(technician.getId());
    }

    @Test
    @DisplayName("Delete Not Delete Technician When Fail")
    void delete_NotDeleteTechnician_WhenFail() {
        Technician technician = technicianBuilder();

        catchThrowableOfType(() -> service.delete("NotExist"), NullPointerException.class);

        Mockito.verify(repository, Mockito.never()).deleteById(technician.getId());
    }

    @Test
    @DisplayName("Delete Do not delete when customer has tickets When Fail")
    void delete_DoNotDeleteWhenCustomerHasTickets_WhenFail() {
        Technician technician = technicianBuilder();

        technician.setCalleds(List.of(calledBuilder()));

        BDDMockito.when(repository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(technician));

        catchThrowableOfType(() -> service.delete("id"), DataIntegratyViolationException.class);

        Mockito.verify(repository, Mockito.never()).deleteById(technician.getId());
    }

    static Technician technicianBuilder() {
        Technician technician = new Technician();
        technician.setId("id");
        technician.setName("Antônio Teste");
        technician.setEmail("antonio@email.com");
        technician.setCpf("032.036.540-91");
        technician.setPassword("123456");
        return technician;
    }

    static TechnicianDTO technicianDTOBuilder() {
        TechnicianDTO technician = new TechnicianDTO();
        technician.setId("id");
        technician.setName("Antônio Teste");
        technician.setEmail("antonio@email.com");
        technician.setCpf("032.036.540-91");
        technician.setPassword("123456");
        return technician;
    }

    static TechnicianUpdateDTO technicianDTOUpdateBuilder() {
        TechnicianUpdateDTO technician = new TechnicianUpdateDTO();
        technician.setId("id");
        technician.setName("Antônio Teste");
        technician.setEmail("antonio@email.com");
        technician.setCpf("032.036.540-91");
        return technician;
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