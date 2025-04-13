# 📦 GESTION3 — Application de gestion de stock (JavaFX + JDBC)

`GESTION3` est une application de bureau Java développée avec JavaFX permettant de gérer un inventaire de produits et leurs mouvements de stock (entrées/sorties).  
Elle permet d'ajouter, modifier, supprimer des produits, et de générer des rapports PDF avec JasperReports.

---

## 🧩 Fonctionnalités principales

- ✅ Ajout de nouveaux produits et mouvements de stock
- ✏️ Modification des données existantes
- ❌ Suppression d’un produit ou mouvement
- 📊 Génération de rapports avec JasperReports
- 💾 Connexion réelle à une base de données via JDBC

---

## 🗂️ Structure du projet

GESTION3/ ├── src/ │ └── com.monapp/ │ ├── controller/ │ │ ├── ProduitController.java │ │ └── MouvementController.java │ └── model/ │ ├── Produit.java │ ├── ProduitDAO.java │ ├── MouvementStock.java │ ├── MouvementStockDAO.java │ ├── DatabaseConnection.java │ ├── ReportGenerator.java │ └── MainApp.java ├── resources/ │ ├── views/ │ │ ├── produit_view.fxml │ │ └── mouvements.fxml │ └── report_template.jrxml ├── README.md ├── CONTRIBUTING.md └── contributions.tx



---

## 🧰 Technologies utilisées

| Technologie      | Description                         |
|------------------|-------------------------------------|
| Java             | Langage principal                   |
| JavaFX           | UI graphique                        |
| JDBC             | Connexion à la base de données      |
| MySQL / SQLite   | SGBD utilisé                        |
| JasperReports    | Génération de rapports PDF          |
| SceneBuilder     | Construction des vues `.fxml`       |

---

## 🚀 Lancer l'application

1. Ouvrir le projet dans IntelliJ ou Eclipse
2. S’assurer que la base de données est accessible (voir `DatabaseConnection.java`)
3. Lancer la classe `MainApp.java`
4. L’interface graphique s’ouvre avec le tableau de produits

---



Voir [`contributions.txt`](contributions.txt) pour plus de détails.

---

## 📄 Rapport PDF

Un bouton permet de générer un **rapport imprimable PDF** des produits depuis l’interface grâce à JasperReports.

---

