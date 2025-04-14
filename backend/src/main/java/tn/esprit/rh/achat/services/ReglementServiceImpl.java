package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReglementServiceImpl implements IReglementService {

	@Autowired
	private FactureRepository factureRepository;

	@Autowired
	private ReglementRepository reglementRepository;

	@Override
	public List<Reglement> retrieveAllReglements() {
		log.debug("🔍 Débogage : Exécution de la méthode retrieveAllReglements()");
		log.trace("📍 Entrée dans la méthode retrieveAllReglements()");
		log.info("Récupération de tous les règlements.");

		List<Reglement> reglements = (List<Reglement>) reglementRepository.findAll();
		for (Reglement reglement : reglements) {
			log.info("Règlement récupéré : {}", reglement);
		}

		log.info("Tous les règlements ont été récupérés.");
		log.trace("📍 Sortie de la méthode retrieveAllReglements()");
		return reglements;
	}

	@Override
	public Reglement addReglement(Reglement r) {
		log.info("Ajout du règlement : {}", r);
		Reglement savedReglement = reglementRepository.save(r);
		log.info("Règlement ajouté avec succès : {}", savedReglement);
		return savedReglement;
	}

	@Override
	public Reglement retrieveReglement(Long id) {
		long start = System.currentTimeMillis();

		log.info("Récupération du règlement avec ID : {}", id);
		Reglement reglement = reglementRepository.findById(id).orElse(null);
		if (reglement != null) {
			log.info("Règlement récupéré : {}", reglement);
		} else {
			log.warn("Aucun règlement trouvé avec l'ID : {}", id);
		}

		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Temps d'exécution de la méthode retrieveReglement : {} ms", elapsedTime);

		return reglement;
	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		log.info("Récupération des règlements pour la facture ID : {}", idFacture);

		List<Reglement> reglements = reglementRepository.retrieveReglementByFacture(idFacture);

		for (Reglement reglement : reglements) {
			log.info("Règlement récupéré pour facture : {}", reglement);
		}

		log.info("Tous les règlements associés à la facture ID {} ont été récupérés.", idFacture);
		return reglements;
	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		log.info("Calcul du chiffre d'affaires entre les dates {} et {}", startDate, endDate);
		float chiffreAffaire = reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate);
		log.info("Chiffre d'affaires calculé : {}", chiffreAffaire);
		return chiffreAffaire;
	}
}