# ➕ CONTRIBUTING.md — Branche `ajouter`
 
Bienvenue dans la branche dédiée à l'ajout de produits dans GESTION3.
 
---
 
## 📌 Règles
 
- Travailler uniquement sur les **fonctionnalités d'ajout**

- Modifier ou compléter les classes suivantes :

  - `ProduitController.java`

  - `ProduitDAO.java`

  - `produit_view.fxml`
 
---
 
## 🧪 Tests
 
- Vérifier que le formulaire valide bien les données

- S'assurer que les produits s'affichent après l'ajout

- La base de données doit être mise à jour
 
---
 
## 🔄 Processus Git
 
```bash

git checkout ajouter

git pull origin ajouter

# Travailler...

git add .

git commit -m "Ajout produit - formulaire OK"

git push origin ajouter

 
# ❌ CONTRIBUTING.md — Branche `supprimer`
 
Branche dédiée à la suppression des produits dans GESTION3.
 
---
 
## 📌 À faire
 
- Implémenter :

  - Bouton supprimer dans `produit_view.fxml`

  - Méthode `delete()` dans `ProduitDAO.java`

  - Action `handleDelete()` dans `ProduitController.java`
 
---
 
## 🧪 Vérification
 
- Confirmer que le produit est supprimé de la base

- Interface actualisée sans redémarrage
 
---
 
## 🔄 Étapes Git
 
```bash

git checkout supprimer

git pull origin supprimer

# Travailler...

git add .

git commit -m "Suppression produit opérationnelle"

git push origin supprimer

 
