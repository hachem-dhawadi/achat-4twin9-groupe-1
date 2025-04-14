package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits = produitRepository.findAll();
		log.info("✅ Retrieved {} products from database", produits.size());
		for (Produit produit : produits) {
			log.debug("📦 Product: {}", produit);
		}
		return produits;
	}

	@Transactional
	public Produit addProduit(Produit p) {
		Produit saved = produitRepository.save(p);
		log.info("➕ Added new product with ID: {}", saved.getIdProduit());
		return saved;
	}

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
		log.warn("🗑 Deleted product with ID: {}", produitId);
	}

	@Override
	public Produit updateProduit(Produit p) {
		Produit updated = produitRepository.save(p);
		log.info("♻️ Updated product with ID: {}", updated.getIdProduit());
		return updated;
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId).orElse(null);
		if (produit != null) {
			log.info("🔍 Retrieved product: {}", produit);
		} else {
			log.warn("⚠️ No product found with ID: {}", produitId);
		}
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);

		if (produit == null || stock == null) {
			log.error("❌ Failed to assign product ID {} to stock ID {} — product or stock not found.", idProduit, idStock);
			return;
		}

		produit.setStock(stock);
		produitRepository.save(produit);
		log.info("📦✅ Assigned product ID {} to stock ID {}", idProduit, idStock);
	}
}