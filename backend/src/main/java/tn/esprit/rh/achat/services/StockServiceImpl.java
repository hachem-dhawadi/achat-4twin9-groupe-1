package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StockServiceImpl implements IStockService {

	@Autowired

	private StockRepository stockRepository;

	@Override
	public List<Stock> retrieveAllStocks() {
		log.debug("🔍 Débogage : Exécution de la méthode retrieveAllStocks()");
		log.trace("📍 Entrée dans la méthode retrieveAllStocks()");
		log.info("Récupération de tous les stocks.");
		List<Stock> stocks = (List<Stock>) stockRepository.findAll();
		for (Stock stock : stocks) {
			log.info("Stock récupéré : {}", stock);
		}
		log.info("Tous les stocks ont été récupérés.");
		log.trace("📍 Sortie de la méthode retrieveAllStocks()");

		return stocks;
	}

	@Override
	public Stock addStock(Stock s) {

		log.info("Ajout du stock : {}", s);
		log.info("Ajout du stock avec libellé : {}", s.getLibelleStock());
		Stock savedStock = stockRepository.save(s);
		log.info("Stock ajouté avec succès : {}", savedStock);
		return savedStock;

	}

	@Override
	public void deleteStock(Long stockId) {

		log.info("Suppression du stock avec ID : {}", stockId);
		try {
			stockRepository.deleteById(stockId);
			log.info("Stock supprimé avec succès.");
		} catch (Exception e) {
			log.error("Erreur lors de la suppression du stock avec ID : {}", stockId, e);
		}

	}

	@Override
	public Stock updateStock(Stock s) {

		log.info("Mise à jour du stock : {}", s);
		Stock updatedStock = stockRepository.save(s);
		log.info("Stock mis à jour avec succès : {}", updatedStock);
		return updatedStock;

	}

	@Override
	public Stock retrieveStock(Long stockId) {

		long start = System.currentTimeMillis();  // mesure du temps

		log.info("Récupération du stock avec ID : {}", stockId);
		Stock stock = stockRepository.findById(stockId).orElse(null);
		if (stock != null) {
			log.info("Stock récupéré : {}", stock);
		} else {
			log.warn("Stock non trouvé avec ID : {}", stockId);
		}

		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Temps d'exécution de la méthode retrieveStock : {} ms", elapsedTime);


		return stock;
	}


	@Override
	public String retrieveStatusStock() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String msgDate = sdf.format(now);
		String finalMessage = "";
		String newLine = System.getProperty("line.separator");
		List<Stock> stocksEnRouge = (List<Stock>) stockRepository.retrieveStatusStock();


		log.info("Vérification du statut des stocks en rouge.");
		for (int i = 0; i < stocksEnRouge.size(); i++) {
			finalMessage = newLine + finalMessage + msgDate + newLine + ": Le stock "
					+ stocksEnRouge.get(i).getLibelleStock() + " a une quantité de " + stocksEnRouge.get(i).getQte()
					+ " inférieure à la quantité minimale à ne pas dépasser de " + stocksEnRouge.get(i).getQteMin()
					+ newLine;
			log.warn("Stock en rouge détecté : {}", stocksEnRouge.get(i));
		}

		log.info("Fin de la vérification des stocks en rouge.");
		return finalMessage;
	}
}


