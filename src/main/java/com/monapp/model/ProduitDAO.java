package com.monapp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    private Connection connection;

    public ProduitDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouterProduit(Produit produit) {
        try {
            String query = "INSERT INTO produits (nom, description, quantite, prix) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, produit.getNom());
                stmt.setString(2, produit.getDescription());
                stmt.setInt(3, produit.getQuantite());
                stmt.setDouble(4, produit.getPrix());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierProduit(Produit produit) {
        try {
            String query = "UPDATE produits SET description = ?, quantite = ?, prix = ? WHERE nom = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, produit.getDescription());
                stmt.setInt(2, produit.getQuantite());
                stmt.setDouble(3, produit.getPrix());
                stmt.setString(4, produit.getNom());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProduit(String nom) {
        try {
            String query = "DELETE FROM produits WHERE nom = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nom);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer tous les produits
    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produits";  // Change cette requête selon la structure de ta base de données

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Récupération des données depuis la base
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                int quantite = resultSet.getInt("quantite");
                double prix = resultSet.getDouble("prix");

                // Création d'un objet Produit et ajout à la liste
                Produit produit = new Produit(id, nom, description, quantite, prix);
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    // Méthode pour récupérer un produit par son nom
    public Produit getProduitByNom(String produitNom) {
        Produit produit = null;
        String query = "SELECT * FROM produits WHERE nom = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produitNom);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String description = resultSet.getString("description");
                    int quantite = resultSet.getInt("quantite");
                    double prix = resultSet.getDouble("prix");

                    produit = new Produit(id, nom, description, quantite, prix);  // Créer un objet Produit à partir des données récupérées
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produit;  // Retourne le produit trouvé ou null si non trouvé
    }
}
