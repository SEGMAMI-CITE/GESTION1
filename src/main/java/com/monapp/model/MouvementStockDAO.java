package com.monapp.model;

import java.sql.*;

public class MouvementStockDAO {

    private Connection connection;

    public MouvementStockDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouterMouvement(MouvementStock mouvement) {
        try {
            String query = "INSERT INTO mouvements_stock (produit_id, type_mouvement, quantite) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, mouvement.getProduitId());
                stmt.setString(2, mouvement.getTypeMouvement());
                stmt.setInt(3, mouvement.getQuantite());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
