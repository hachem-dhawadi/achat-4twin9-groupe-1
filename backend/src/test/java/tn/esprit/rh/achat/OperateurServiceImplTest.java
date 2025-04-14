package tn.esprit.rh.achat;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    private Operateur operateur;

    @BeforeEach
    void setUp() {
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("John");
        operateur.setPrenom("Doe");
        operateur.setPassword("secure123");
    }

    @Test
    void testRetrieveAllOperateurs() {
        when(operateurRepository.findAll()).thenReturn(Arrays.asList(operateur));

        var result = operateurService.retrieveAllOperateurs();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
    void testAddOperateur() {
        when(operateurRepository.save(any(Operateur.class))).thenReturn(operateur);

        var savedOperateur = operateurService.addOperateur(operateur);

        assertNotNull(savedOperateur);
        assertEquals("John", savedOperateur.getNom());
        verify(operateurRepository, times(1)).save(any());
    }

    @Test
    void testDeleteOperateur() {
        doNothing().when(operateurRepository).deleteById(1L);

        operateurService.deleteOperateur(1L);

        verify(operateurRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateOperateur() {
        when(operateurRepository.save(any(Operateur.class))).thenReturn(operateur);

        var updatedOperateur = operateurService.updateOperateur(operateur);

        assertNotNull(updatedOperateur);
        assertEquals("Doe", updatedOperateur.getPrenom());
        verify(operateurRepository, times(1)).save(any());
    }

    @Test
    void testRetrieveOperateur() {
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        var found = operateurService.retrieveOperateur(1L);

        assertNotNull(found);
        assertEquals(1L, found.getIdOperateur());
        verify(operateurRepository, times(1)).findById(1L);
    }
}