package com.monapp.controller;

import com.monapp.model.ProduitDAO;
import com.monapp.model.Produit;  // Assure-toi d'importer la classe Produit
import java.util.List;

public class MouvementController {

    private ProduitDAO produitDAO;

    public MouvementController() {
        produitDAO = new ProduitDAO();
    }

    // Méthode pour afficher tous les produits
    public void afficherTousLesProduits() {
        List<Produit> produits = produitDAO.getAllProduits();

        // Affichage des produits dans l'interface (ici, c'est un simple affichage console)
        for (Produit produit : produits) {
            System.out.println("ID: " + produit.getId() + ", Nom: " + produit.getNom() + ", Prix: " + produit.getPrix() + ", Quantité: " + produit.getQuantite());
        }
    }
}
