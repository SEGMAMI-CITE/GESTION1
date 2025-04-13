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

 
