# ✏ Modification de données — GESTION3

Cette documentation décrit la **fonctionnalité de modification** des données produit dans l'application de gestion de stock `GESTION3`.

---

##  Objectif

Permettre à l'utilisateur de **modifier les informations existantes** d’un produit (nom, prix, quantité, etc.) directement depuis l'interface JavaFX, et d'enregistrer ces modifications en base de données.

---

##  Étapes du fonctionnement

1. Il clique sur le bouton **Modifier**
2. Les champs de saisie sont remplis avec les données actuelles du produit
3. L'utilisateur modifie les champs puis clique sur **Mise a jour**
4. Les nouvelles données sont **mises à jour dans la base**
5. L'interface est actualisée automatiquement

---

##  Fichiers impliqués

| Fichier                  | Rôle                                                  |
|--------------------------|-------------------------------------------------------|
| `ProduitController.java` | Contient `modifierProduit()`              |
| `ProduitDAO.java`        | Fournit la méthode `modifierProduit(Produit p)`               |
| `produit_view.fxml`      | Contient les champs de formulaire et le bouton "✏️"  |

---

##  Code essentiel

### Dans `ProduitController.java`

```java
@FXML
private void toggleEdit() {
    Produit produit = tableProduits.getSelectionModel().getSelectedItem();
    if (produit != null) {
        txtNom.setText(produit.getNom());
        txtPrix.setText(String.valueOf(produit.getPrix()));
        txtQuantite.setText(String.valueOf(produit.getQuantite()));
        btnSauvegarder.setVisible(true);
    }
}

@FXML
private void saveEdit() {
    Produit produit = tableProduits.getSelectionModel().getSelectedItem();
    if (produit != null) {
        produit.setNom(txtNom.getText());
        produit.setPrix(Double.parseDouble(txtPrix.getText()));
        produit.setQuantite(Integer.parseInt(txtQuantite.getText()));

        ProduitDAO dao = new ProduitDAO();
        dao.update(produit);
        rafraichirListeProduits();
        btnSauvegarder.setVisible(false);
    }
}
