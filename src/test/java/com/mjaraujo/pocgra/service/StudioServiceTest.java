package com.mjaraujo.pocgra.service;

import com.mjaraujo.pocgra.entity.Studio;
import com.mjaraujo.pocgra.repository.IStudioRepository;
import javafx.beans.binding.Bindings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class StudioServiceTest {

    @Autowired
    private StudioService service;

    @MockBean
    private IStudioRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        Studio studio = new Studio("Araújo Filmes");
        doReturn(Optional.of(studio)).when(repository).findById(1l);

        Optional<Studio> returnedStudio = service.findById(1l);

        Assertions.assertTrue(returnedStudio.isPresent(), "Studio was not found");
        Assertions.assertSame(returnedStudio.get(), studio, "The studio returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById(1l);

        Optional<Studio> returnedStudio = service.findById(1l);

        Assertions.assertFalse(returnedStudio.isPresent(), "Studio should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        Studio studio1 = new Studio("Studio Name");
        Studio studio2 = new Studio("Studio 2 Name");
        doReturn(Arrays.asList(studio1, studio2)).when(repository).findAll();

        List<Studio> studios = service.findAll();

        Assertions.assertEquals(2, studios.size(), "findAll should return 2 studios");
    }

    @Test
    @DisplayName("Test save studio")
    void testSave() {
        Studio studio = new Studio("Studio Name");
        doReturn(studio).when(repository).save(any());

        Studio returnedStudio = service.save(studio);

        Assertions.assertNotNull(returnedStudio, "The saved studio should not be null");
        Assertions.assertEquals("Studio Name", returnedStudio.getName(), "The name is ok");
    }
}