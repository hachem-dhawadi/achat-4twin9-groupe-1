package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllProduits() {
        Produit p1 = new Produit();
        Produit p2 = new Produit();
        List<Produit> produitList = Arrays.asList(p1, p2);

        when(produitRepository.findAll()).thenReturn(produitList);

        List<Produit> result = produitService.retrieveAllProduits();

        assertEquals(2, result.size());
        verify(produitRepository, times(1)).findAll();
    }
}
