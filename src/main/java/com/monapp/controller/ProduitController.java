package com.monapp.controller;

// Importation des classes du modèle et de la génération de rapport
import com.monapp.model.Produit;
import com.monapp.model.ProduitDAO;
import com.monapp.model.ReportGenerator;

// Importation des classes JavaFX pour la gestion de l’interface graphique
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

// Contrôleur JavaFX qui gère les interactions entre l’utilisateur
// et l’interface liée aux produits
public class ProduitController {

    // Champs texte liés au formulaire de saisie (injectés via FXML)
    @FXML private TextField nomField;
    @FXML private TextField descriptionField;
    @FXML private TextField quantiteField;
    @FXML private TextField prixField;

    // Composants de la table pour afficher les produits
    @FXML private TableView<Produit> produitTable;
    @FXML private TableColumn<Produit, String> nomColumn;
    @FXML private TableColumn<Produit, String> descriptionColumn;
    @FXML private TableColumn<Produit, Integer> quantiteColumn;
    @FXML private TableColumn<Produit, Double> prixColumn;

    // DAO pour gérer la persistance des produits (base de données, fichier, etc.)
    private ProduitDAO produitDAO = new ProduitDAO();

    // Liste observable qui alimente dynamiquement la TableView
    private ObservableList<Produit> produitList = FXCollections.observableArrayList();

    // Outil de génération de rapports (PDF, etc.)
    private ReportGenerator reportGenerator = new ReportGenerator();

    // Méthode appelée automatiquement à l'initialisation de l’interface (FXML)
    @FXML
    public void initialize() {
        // Liaison des colonnes du tableau aux propriétés du modèle Produit
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        // Chargement initial des produits dans la table
        rafraichirTableau();
    }

    // Méthode appelée lors du clic sur le bouton "Ajouter Produit"
    @FXML
    public void ajouterProduit() {
        try {
            // Récupération et conversion des données saisies dans les champs texte
            String nom = nomField.getText();
            String description = descriptionField.getText();
            int quantite = Integer.parseInt(quantiteField.getText());
            double prix = Double.parseDouble(prixField.getText());

            // Création d’un nouvel objet Produit avec les données saisies
            Produit produit = new Produit(nom, description, quantite, prix);

            // Appel au DAO pour enregistrer le produit
            produitDAO.ajouterProduit(produit);

            // Rafraîchir le tableau pour afficher le nouveau produit
            rafraichirTableau();

            // Afficher une alerte de succès
            showAlert("Produit ajouté avec succès", "Le produit a été ajouté à l'inventaire.");

        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion (saisie invalide)
            showAlert("Erreur de saisie", "Veuillez entrer une quantité et un prix valides.");
        } catch (Exception e) {
            // Gérer toute autre erreur
            showAlert("Erreur", "Une erreur est survenue lors de l'ajout du produit.");
            e.printStackTrace();
        }
    }

    // Méthode pour modifier un produit existant
    @FXML
    public void modifierProduit() {
        try {
            // Récupération des données saisies
            String nom = nomField.getText();
            String description = descriptionField.getText();
            int quantite = Integer.parseInt(quantiteField.getText());
            double prix = Double.parseDouble(prixField.getText());

            // Recherche du produit existant par son nom
            Produit produitExistant = produitDAO.getProduitByNom(nom);

            if (produitExistant != null) {
                // Mise à jour des propriétés du produit
                produitExistant.setDescription(description);
                produitExistant.setQuantite(quantite);
                produitExistant.setPrix(prix);

                // Appel au DAO pour enregistrer les modifications
                produitDAO.modifierProduit(produitExistant);
                rafraichirTableau();
                showAlert("Produit modifié", "Les informations du produit ont été mises à jour.");
            } else {
                showAlert("Erreur", "Aucun produit trouvé avec ce nom.");
            }
        } catch (NumberFormatException e) {
            showAlert("Erreur de saisie", "Veuillez entrer une quantité et un prix valides.");
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur est survenue lors de la modification du produit.");
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un produit
    @FXML
    public void supprimerProduit() {
        try {
            String nom = nomField.getText();
            Produit produit = produitDAO.getProduitByNom(nom);

            if (produit != null) {
                produitDAO.supprimerProduit(produit.getNom());
                rafraichirTableau();
                showAlert("Produit supprimé", "Le produit a été supprimé de l'inventaire.");
            } else {
                showAlert("Erreur", "Aucun produit trouvé avec ce nom.");
            }
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur est survenue lors de la suppression du produit.");
            e.printStackTrace();
        }
    }

    // Méthode pour générer un rapport PDF du stock
    @FXML
    public void genererPDF() {
        try {
            // Génération du fichier PDF
            reportGenerator.generateStockReport();

            File pdfFile = new File("stock_report.pdf");

            // Vérifier si le fichier a bien été généré
            if (pdfFile.exists()) {
                // Tentative d'ouverture automatique du fichier PDF
                Desktop.getDesktop().open(pdfFile);
                showAlert("PDF généré", "Le rapport PDF a été généré et ouvert avec succès.");
            } else {
                showAlert("Erreur", "Le fichier PDF n'a pas pu être généré.");
            }
        } catch (IOException e) {
            showAlert("Erreur", "Impossible d'ouvrir le fichier PDF.");
            e.printStackTrace();
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur est survenue lors de la génération du PDF.");
            e.printStackTrace();
        }
    }

    // Méthode pour recharger les données de la TableView
    @FXML
    public void rafraichirTableau() {
        try {
            // Réinitialiser la liste des produits affichés
            produitList.clear();
            produitList.addAll(produitDAO.getAllProduits());

            // Rafraîchir la TableView avec les nouvelles données
            produitTable.setItems(produitList);
            produitTable.refresh();

            // Vérification de stocks faibles
            for (Produit produit : produitList) {
                if (produit.getQuantite() <= 15) {
                    showAlert("⚠️ Stock Faible", "Le produit '" + produit.getNom() + "' est bientôt en rupture (quantité: " + produit.getQuantite() + ")");
                }
            }
        } catch (Exception e) {
            showAlert("Erreur", "Impossible de charger les produits.");
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour afficher une alerte utilisateur
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);            // Titre de la boîte de dialogue
        alert.setHeaderText(null);        // Pas de sous-titre
        alert.setContentText(message);    // Message principal
        alert.showAndWait();              // Affichage bloquant de l’alerte
    }
}
