package com.monapp.controller;

import com.monapp.model.Produit;
import com.monapp.model.ProduitDAO;
import com.monapp.model.ReportGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ProduitController {

    @FXML private TextField nomField;
    @FXML private TextField descriptionField;
    @FXML private TextField quantiteField;
    @FXML private TextField prixField;
    @FXML private TableView<Produit> produitTable;
    @FXML private TableColumn<Produit, String> nomColumn;
    @FXML private TableColumn<Produit, String> descriptionColumn;
    @FXML private TableColumn<Produit, Integer> quantiteColumn;
    @FXML private TableColumn<Produit, Double> prixColumn;

    private ProduitDAO produitDAO = new ProduitDAO();
    private ObservableList<Produit> produitList = FXCollections.observableArrayList();
    private ReportGenerator reportGenerator = new ReportGenerator();

    @FXML
    public void initialize() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        rafraichirTableau(); // Charge les données
    }

    @FXML
    public void ajouterProduit() {
        String nom = nomField.getText();
        String description = descriptionField.getText();
        int quantite = Integer.parseInt(quantiteField.getText());
        double prix = Double.parseDouble(prixField.getText());

        Produit produit = new Produit(nom, description, quantite, prix);
        produitDAO.ajouterProduit(produit);

        rafraichirTableau();
        showAlert("Produit ajouté avec succès", "Le produit a été ajouté à l'inventaire.");
    }

    @FXML
    public void modifierProduit() {
        String nom = nomField.getText();
        String description = descriptionField.getText();
        int quantite = Integer.parseInt(quantiteField.getText());
        double prix = Double.parseDouble(prixField.getText());

        Produit produitExistant = produitDAO.getProduitByNom(nom);

        if (produitExistant != null) {
            produitExistant.setDescription(description);
            produitExistant.setQuantite(quantite);
            produitExistant.setPrix(prix);

            produitDAO.modifierProduit(produitExistant);
            rafraichirTableau();
            showAlert("Produit modifié", "Les informations du produit ont été mises à jour.");
        } else {
            showAlert("Erreur", "Aucun produit trouvé avec ce nom.");
        }
    }

    @FXML
    public void supprimerProduit() {
        String nom = nomField.getText();
        Produit produit = produitDAO.getProduitByNom(nom);

        if (produit != null) {
            produitDAO.supprimerProduit(produit.getNom());
            rafraichirTableau();
            showAlert("Produit supprimé", "Le produit a été supprimé de l'inventaire.");
        } else {
            showAlert("Erreur", "Aucun produit trouvé avec ce nom.");
        }
    }

    @FXML
    public void genererPDF() {
        // Générer le rapport PDF
        reportGenerator.generateStockReport();

        try {
            // Ouvrir le PDF généré
            File pdfFile = new File("stock_report.pdf");
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                showAlert("Erreur", "Le fichier PDF n'a pas pu être généré.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir le fichier PDF.");
        }

        showAlert("PDF généré", "Le rapport PDF a été généré et ouvert avec succès.");
    }

    @FXML
    public void rafraichirTableau() {
        produitList.clear();
        produitList.addAll(produitDAO.getAllProduits());
        produitTable.setItems(produitList);
        produitTable.refresh();

        // Alerter si des produits ont une quantité ≤ 15
        for (Produit produit : produitList) {
            if (produit.getQuantite() <= 15) {
                showAlert("⚠️ Stock Faible", "Le produit '" + produit.getNom() + "' est bientôt en rupture (quantité: " + produit.getQuantite() + ")");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
