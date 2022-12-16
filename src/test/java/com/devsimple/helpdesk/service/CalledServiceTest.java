package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.CalledCreateDTO;
import com.devsimple.helpdesk.dto.CalledDTO;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import com.devsimple.helpdesk.repository.CalledRepository;
import com.devsimple.helpdesk.repository.ClientRepository;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Called Service")
class CalledServiceTest {

    @SpyBean
    private CalledService service;
    @MockBean
    private CalledRepository repository;
    @MockBean
    private TechnicianRepository technicianRepository;
    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        List<Called> calleds = new ArrayList<>(List.of(calledBuilder()));

        BDDMockito.when(repository.findAll(ArgumentMatchers.any(Example.class)))
                .thenReturn(calleds);

        BDDMockito.when(repository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(calledBuilder()));
        BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(clientBuilder()));
        BDDMockito.when(technicianRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(technicianBuilder()));
        BDDMockito.when(repository.save(ArgumentMatchers.any(Called.class)))
                .thenReturn(calledBuilder());
    }

    @Test
    @DisplayName("FindByIdDTO Return Called in DTO When Successful")
    void findByIdDTO_ReturnCalledDTO_WhenSuccessful() {
        Called called = calledBuilder();

        CalledDTO result = service.findByIdDTO(called.getId());

        assertThat(result).isNotNull();

        assertThat(result.getId()).isNotNull().isEqualTo(called.getId());

        assertThat(result.getTitle()).isEqualTo(called.getTitle());

        assertThat(result.getObservation()).isEqualTo(called.getObservation());

        assertThat(result.getClient()).isEqualTo(clientBuilder().getId());

        assertThat(result.getTechnician()).isEqualTo(technicianBuilder().getId());

        assertThat(result.getOpenDate()).isEqualTo(called.getOpenDate());
    }

    @Test
    @DisplayName("FindByIdDTO Returns Error When Not Found Id")
    void findByIdDTO_ReturnErrorWhenNotFoundID_WhenFail() {
        String id = calledBuilder().getId();

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        catchThrowableOfType(() -> service.findByIdDTO(id), ObjectNotFoundException.class);
    }

    @Test
    @DisplayName("FindAll Return list of Called When Successful")
    void findAll_ReturnListOfCalled_WhenSuccessful() {
        List<CalledDTO> result = service.findAll();

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Save Return Called When Successful")
    void save_ReturnCalledSaved_WhenSuccessful() {
        CalledCreateDTO dto = calledDTOBuilder();

        Called result = service.save(dto);

        assertThat(result).isNotNull();

        assertThat(result.getOpenDate()).isNotNull();

        assertThat(result.getTitle()).isEqualTo(dto.getTitle());

        assertThat(result.getObservation()).isEqualTo(dto.getObservation());

        assertThat(result.getStatus().toString()).isEqualTo("PROGRESS");

        assertThat(result.getPriority().toString()).isEqualTo("AVERAGE");

        assertThat(result.getClient()).isEqualTo(clientBuilder());

        assertThat(result.getTechnician()).isEqualTo(technicianBuilder());
    }

    @Test
    @DisplayName("Save Return Called When Status for Closed ")
    void save_ReturnCalledSaved_WhenStatusForClosed() {
        CalledCreateDTO dto = calledDTOBuilder();

        dto.setStatus(2);

        Called called = calledBuilder();

        called.setStatus(Status.CLOSED);

        called.setCloseDate(LocalDate.now());

        BDDMockito.when(repository.save(ArgumentMatchers.any(Called.class)))
                .thenReturn(called);

        Called result = service.save(dto);

        assertThat(result).isNotNull();

        assertThat(result.getCloseDate()).isNotNull();

        assertThat(result.getStatus().toString()).isEqualTo("CLOSED");
    }

    @Test
    @DisplayName("Update Called When Successful")
    void update_UpdateCalled_WhenSuccessful() {
        CalledCreateDTO dto = calledDTOBuilder();

        Called result = service.update(dto.getId(), dto);

        Mockito.verify(repository, Mockito.times(1)).save(result);
    }

    @Test
    @DisplayName("Update Called When Status for Closed Successful")
    void update_UpdateCalledWhenStatusForClosed_WhenSuccessful() {
        CalledCreateDTO dto = calledDTOBuilder();

        dto.setStatus(2);

        Called called = calledBuilder();

        called.setStatus(Status.CLOSED);

        called.setCloseDate(LocalDate.now());

        BDDMockito.when(repository.save(ArgumentMatchers.any(Called.class)))
                .thenReturn(called);

        Called result = service.update(dto.getId(), dto);

        assertThat(result.getCloseDate()).isNotNull();

        assertThat(result.getStatus().toString()).isEqualTo("CLOSED");
    }

    private static CalledCreateDTO calledDTOBuilder() {
        CalledCreateDTO called = new CalledCreateDTO();
        called.setId("getUUid()");
        called.setTitle("Colocar SSD");
        called.setObservation("Fazer a trocar do HD por SSD");
        called.setPriority(1);
        called.setStatus(1);
        called.setTechnician(technicianBuilder().getId());
        called.setClient(clientBuilder().getId());
        return called;
    }

    private static Called calledBuilder() {
        Called called = new Called();
        called.setId("getUUid()");
        called.setTitle("Colocar SSD");
        called.setObservation("Fazer a trocar do HD por SSD");
        called.setPriority(Priority.AVERAGE);
        called.setStatus(Status.PROGRESS);
        called.setTechnician(technicianBuilder());
        called.setClient(clientBuilder());
        return called;
    }

    private static Client clientBuilder() {
        Client client = new Client();
        client.setId("getUUid()");
        client.setName("José Client");
        client.setEmail("jose@client.com");
        client.setCpf("304.532.860-11");
        client.setPassword("123456");
        return client;
    }

    private static Technician technicianBuilder() {
        Technician technician = new Technician();
        technician.setId("getUUid()");
        technician.setName("Antônio Technician");
        technician.setEmail("antonio@technician.com");
        technician.setCpf("032.036.540-91");
        technician.setPassword("123456");
        return technician;
    }
}