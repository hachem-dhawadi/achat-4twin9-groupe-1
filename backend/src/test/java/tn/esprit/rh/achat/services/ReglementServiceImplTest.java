package tn.esprit.rh.achat.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {

    @Mock
    private ReglementRepository reglementRepository;

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Test
    void testRetrieveAllReglements() {
        Reglement r1 = new Reglement(1L, 100f, 0f, true, new Date(), null);
        Reglement r2 = new Reglement(2L, 200f, 50f, false, new Date(), null);
        List<Reglement> reglementList = Arrays.asList(r1, r2);

        when(reglementRepository.findAll()).thenReturn(reglementList);

        List<Reglement> result = reglementService.retrieveAllReglements();

        assertEquals(2, result.size());
        verify(reglementRepository, times(1)).findAll();
    }

    @Test
    void testAddReglement() {
        Reglement r = new Reglement(null, 150f, 50f, false, new Date(), null);
        Reglement saved = new Reglement(1L, 150f, 50f, false, new Date(), null);

        when(reglementRepository.save(any(Reglement.class))).thenReturn(saved);

        Reglement result = reglementService.addReglement(r);

        assertNotNull(result);
        assertEquals(1L, result.getIdReglement());
        verify(reglementRepository, times(1)).save(r);
    }

    @Test
    void testRetrieveReglement() {
        Reglement r = new Reglement(1L, 120f, 30f, true, new Date(), null);
        when(reglementRepository.findById(1L)).thenReturn(Optional.of(r));

        Reglement result = reglementService.retrieveReglement(1L);

        assertNotNull(result);
        assertEquals(120f, result.getMontantPaye());
        verify(reglementRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveReglementNotFound() {
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty());

        Reglement result = reglementService.retrieveReglement(1L);

        assertNull(result);
        verify(reglementRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveReglementByFacture() {
        Long idFacture = 1L;
        Reglement r1 = new Reglement(1L, 100f, 0f, true, new Date(), null);
        Reglement r2 = new Reglement(2L, 200f, 50f, false, new Date(), null);
        List<Reglement> expected = Arrays.asList(r1, r2);

        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(expected);

        List<Reglement> result = reglementService.retrieveReglementByFacture(idFacture);

        assertEquals(2, result.size());
        verify(reglementRepository, times(1)).retrieveReglementByFacture(idFacture);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        Date d1 = new GregorianCalendar(2023, Calendar.JANUARY, 1).getTime();
        Date d2 = new GregorianCalendar(2023, Calendar.DECEMBER, 31).getTime();

        when(reglementRepository.getChiffreAffaireEntreDeuxDate(d1, d2)).thenReturn(5000f);

        float result = reglementService.getChiffreAffaireEntreDeuxDate(d1, d2);

        assertEquals(5000f, result);
        verify(reglementRepository, times(1)).getChiffreAffaireEntreDeuxDate(d1, d2);
    }
}
