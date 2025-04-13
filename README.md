#  Suppression de données — GESTION3

Cette partie du projet `GESTION3` décrit en détail le fonctionnement de la **suppression de produits** depuis l'interface JavaFX, avec une base de données connectée en direct.

---

##  Objectif

Permettre à l’utilisateur de supprimer un produit en un clic :
- depuis l'interface graphique (`produit_view.fxml`)
- et le retirer définitivement de la base de données (`produits`)

---

##  Processus de suppression

1. L'utilisateur sélectionne un produit dans le tableau
2. Il clique sur le bouton **Supprimer**
3. La méthode `supprimerProduit()` du contrôleur est appelée
4. Le produit est supprimé de la base avec la requête `DELETE`
5. L’interface se met à jour automatiquement

---

##  Fichiers concernés

| Fichier                 | Rôle                                                      |
|------------------------|-----------------------------------------------------------|
| `ProduitController.java` | Contient la méthode `supprimerProduit()`                 |
| `ProduitDAO.java`        | Contient la méthode `delete(int id)` pour le SQL DELETE |
| `produit_view.fxml`      | Fichier d’interface contenant le bouton `Supprimer`      |

---

##  Code essentiel

### Dans `ProduitController.java`
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
