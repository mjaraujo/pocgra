package com.mjaraujo.pocgra.service;

import com.mjaraujo.pocgra.entity.Producer;
import com.mjaraujo.pocgra.repository.IProducerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ProducerServiceTest {

    @Autowired
    private ProducerService service;

    @MockBean
    private IProducerRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        Producer producer = new Producer("José Araújo");
        doReturn(Optional.of(producer)).when(repository).findById(1l);

        Optional<Producer> returnedProducer = service.findById(1l);

        Assertions.assertTrue(returnedProducer.isPresent(), "Producer was not found");
        Assertions.assertSame(returnedProducer.get(), producer, "The producer returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById(1l);

        Optional<Producer> returnedProducer = service.findById(1l);

        Assertions.assertFalse(returnedProducer.isPresent(), "Producer should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        Producer producer1 = new Producer("Producer Name");
        Producer producer2 = new Producer("Producer 2 Name");
        doReturn(Arrays.asList(producer1, producer2)).when(repository).findAll();

        List<Producer> producers = service.findAll();

        Assertions.assertEquals(2, producers.size(), "findAll should return 2 producers");
    }

    @Test
    @DisplayName("Test save producer")
    void testSave() {
        Producer producer = new Producer("Producer Name");
        doReturn(producer).when(repository).save(any());

        Producer returnedProducer = service.save(producer);

        Assertions.assertNotNull(returnedProducer, "The saved producer should not be null");
        Assertions.assertEquals("Producer Name", returnedProducer.getName(), "The name is ok");
    }
}