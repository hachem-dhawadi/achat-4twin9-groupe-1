package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> 7f0ebff (initial commit)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "Gestion des reglements")
@RequestMapping("/reglement")
@CrossOrigin("*")
public class ReglementRestController {

<<<<<<< HEAD
    private static final Logger logger = LoggerFactory.getLogger(ReglementRestController.class);

    @Autowired
    IReglementService reglementService;

=======
    @Autowired
    IReglementService reglementService;


>>>>>>> 7f0ebff (initial commit)
    // http://localhost:8089/SpringMVC/reglement/add-reglement
    @PostMapping("/add-reglement")
    @ResponseBody
    public Reglement addReglement(@RequestBody Reglement r) {
<<<<<<< HEAD
        logger.debug("Adding a new Reglement: {}", r);
        Reglement reglement = reglementService.addReglement(r);
        logger.info("Reglement added successfully: {}", reglement);
        return reglement;
    }

    // http://localhost:8089/SpringMVC/reglement/retrieve-all-reglements
    @GetMapping("/retrieve-all-reglements")
    @ResponseBody
    public List<Reglement> getReglement() {
        logger.info("Fetching all Reglements...");
        List<Reglement> list = reglementService.retrieveAllReglements();
        logger.info("Retrieved {} Reglements", list.size());
=======
        Reglement reglement = reglementService.addReglement(r);
        return reglement;
    }
    @GetMapping("/retrieve-all-reglements")
    @ResponseBody
    public List<Reglement> getReglement() {
        List<Reglement> list = reglementService.retrieveAllReglements();
>>>>>>> 7f0ebff (initial commit)
        return list;
    }

    // http://localhost:8089/SpringMVC/reglement/retrieve-reglement/8
    @GetMapping("/retrieve-reglement/{reglement-id}")
    @ResponseBody
    public Reglement retrieveReglement(@PathVariable("reglement-id") Long reglementId) {
<<<<<<< HEAD
        logger.info("Fetching Reglement with ID: {}", reglementId);
        Reglement reglement = reglementService.retrieveReglement(reglementId);
        logger.info("Reglement retrieved successfully: {}", reglement);
        return reglement;
=======
        return reglementService.retrieveReglement(reglementId);
>>>>>>> 7f0ebff (initial commit)
    }

    // http://localhost:8089/SpringMVC/reglement/retrieveReglementByFacture/8
    @GetMapping("/retrieveReglementByFacture/{facture-id}")
    @ResponseBody
    public List<Reglement> retrieveReglementByFacture(@PathVariable("facture-id") Long factureId) {
<<<<<<< HEAD
        logger.info("Fetching Reglements for Facture ID: {}", factureId);
        List<Reglement> reglements = reglementService.retrieveReglementByFacture(factureId);
        logger.info("Retrieved {} Reglements for Facture ID: {}", reglements.size(), factureId);
        return reglements;
=======
        return reglementService.retrieveReglementByFacture(factureId);
>>>>>>> 7f0ebff (initial commit)
    }

    // http://localhost:8089/SpringMVC/reglement/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}
    @GetMapping(value = "/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    public float getChiffreAffaireEntreDeuxDate(
            @PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
<<<<<<< HEAD
        logger.info("Calculating Chiffre Affaire between {} and {}", startDate, endDate);
        try {
            float chiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
            logger.info("Calculated Chiffre Affaire: {}", chiffreAffaire);
            return chiffreAffaire;
        } catch (Exception e) {
            logger.error("Error while calculating Chiffre Affaire: {}", e.getMessage());
            return 0;
        }
    }
}
=======
        try {
            return reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        } catch (Exception e) {
            return 0;
        }
    }
}
>>>>>>> 7f0ebff (initial commit)
