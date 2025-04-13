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

## 🔧 Étapes de la suppression

### 1. Dans `ProduitController.java`

```java
@FXML
public void supprimerProduit() {
    String nom = nomField.getText(); // Récupère le nom du produit à supprimer
    Produit produit = produitDAO.getProduitByNom(nom); // Recherche dans la BDD

    if (produit != null) {
        produitDAO.supprimerProduit(produit.getNom()); // Supprime le produit
        rafraichirTableau(); // Rafraîchit la table pour refléter les modifications
        showAlert("Produit supprimé", "Le produit a été supprimé de l'inventaire.");
    } else {
        showAlert("Erreur", "Aucun produit trouvé avec ce nom.");
    }
}
```

---

### 2. Dans `ProduitDAO.java`

```java
// Méthode qui supprime un produit de la base de données selon son nom
public void supprimerProduit(String nom) {
    try {
        // Requête SQL préparée avec un paramètre (le nom du produit à supprimer)
        String query = "DELETE FROM produits WHERE nom = ?";

        // Préparation de la requête à partir de la connexion déjà établie
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            
            // On insère le nom du produit dans le "?"
            stmt.setString(1, nom); // le nom du produit à supprimer
            
            // On exécute la suppression
            stmt.executeUpdate();   // exécution de la requête
        }

    } catch (SQLException e) {
        // Affiche les erreurs SQL dans la console
        e.printStackTrace(); // en cas d'erreur SQL
    }
}
```

---

##  Message utilisateur

L'utilisateur reçoit une **alerte** :

-  Si le produit a bien été supprimé
-  Si aucun produit correspondant n'a été trouvé

---

##  Résultat attendu

- Le produit n'apparaît plus dans le tableau.
- Il est **définitivement supprimé** de la base de données.

