# 📦 GESTION3 — Application de gestion de stock (JavaFX + JDBC)

`GESTION3` est une application Java développée avec JavaFX permettant de gérer un inventaire de produits et leurs mouvements de stock (entrées/sorties).  
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

GESTION3/
├── src/
│   └── com/monapp/
│       ├── controller/
│       │   ├── ProduitController.java           # Gère les actions sur les produits
│       │   └── MouvementController.java         # Gère les entrées/sorties de stock
│       └── model/
│           ├── Produit.java                     # Classe modèle représentant un produit
│           ├── ProduitDAO.java                  # Accès aux données des produits (CRUD)
│           ├── MouvementStock.java              # Classe modèle pour les mouvements
│           ├── MouvementStockDAO.java           # Accès aux données des mouvements
│           ├── DatabaseConnection.java          # Connexion à la base de données
│           ├── ReportGenerator.java             # Générateur de rapports PDF
│           └── MainApp.java                     # Point d’entrée principal de l’application
├── resources/
│   ├── views/
│   │   ├── produit_view.fxml                    # Interface graphique des produits
│   │   └── mouvements.fxml                      # Interface des mouvements de stock
│   └── report_template.jrxml                    # Template JasperReports (PDF)
├── README.md                                     # Documentation principale du projet
├── CONTRIBUTING.md                               # Règles de contribution au projet
└── contributions.txt                             # Liste des contributions des membres



---

## 🧰 Technologies utilisées

| Technologie      | Description                         |
|------------------|-------------------------------------|
| Java             | Langage principal                   |
| JavaFX           | UI graphique                        |
| JDBC             | Connexion à la base de données      |
| MySQL            | SGBD utilisé                        |
| JasperReports    | Génération de rapports PDF          |
| SceneBuilder     | Construction des vues `.fxml`       |

---

## 🚀 Lancer l'application

1. Ouvrir le projet dans IntelliJ ou Eclipse
2. S’assurer que la base de données est accessible (voir `DatabaseConnection.java`)
3. Lancer la classe `MainApp.java`
4. L’interface graphique s’ouvre avec le tableau de produits

---




---

## 📄 Rapport PDF

Un bouton permet de générer un **rapport imprimable PDF** des produits depuis l’interface grâce à JasperReports.

---

