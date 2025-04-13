Application de Gestion de Stock
Bienvenue dans GESTION3, une application de gestion de stock développée en JavaFX.
Elle permet de gérer les produits, les mouvements de stock, et de générer des rapports.

Utilisation
Lancer l'application.

Naviguer via l’interface graphique :

Voir les produits existants.

Ajouter / Modifier / Supprimer un produit.

Voir les mouvements de stock.

Générer un rapport PDF des stocks.

GESTION3/ ├── src/ │ └── com.monapp/ │ ├── controller/ │ │ ├── ProduitController.java │ │ └── MouvementController.java │ └── model/ │ ├── Produit.java │ ├── ProduitDAO.java │ ├── MouvementStock.java │ ├── MouvementStockDAO.java │ ├── DatabaseConnection.java │ ├── ReportGenerator.java │ └── MainApp.java ├── resources/ │ ├── views/ │ │ ├── produit_view.fxml │ │ └── mouvements.fxml │ └── report_template.jrxml ├── README.md ├── CONTRIBUTING.md └── contributions.txt
