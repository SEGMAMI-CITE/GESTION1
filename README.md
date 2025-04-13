# ✅ Ajout de données — GESTION3
 
Cette section documente la **fonctionnalité d’ajout de nouveaux produits ** dans le système.
 
---
 
## 🎯 Objectif
 
Permettre à l’utilisateur d’**enregistrer un nouveau produit ou un nouveau mouvement de stock** via un formulaire simple dans l’interface graphique.
 
---
 
## 📂 Fichiers concernés
 
| Fichier                         | Rôle                                                         |

|---------------------------------|--------------------------------------------------------------|

| `ProduitController.java`        | Méthode `ajouterProduit()` appelée au clic sur "Ajouter"     |

| `ProduitDAO.java`               | Méthode `insert(Produit p)` insérant les données en base     |

| `produit_view.fxml`             | Interface contenant le formulaire d’ajout + bouton `Ajouter` |


 
---
 
## 🧠 Fonctionnement
 
1. L’utilisateur remplit le formulaire avec les informations du produit.

2. Il clique sur **"Ajouter"**.

3. Les données sont lues, converties et validées.

4. La méthode `insert()` du DAO enregistre les données dans la base.

5. L’interface se met à jour pour afficher le nouveau produit.
 
---
 
## 🧪 Exemple de code — `ProduitController.java`
 
```java

@FXML

private void ajouterProduit() {

    String nom = txtNom.getText();

    double prix = Double.parseDouble(txtPrix.getText());

    int quantite = Integer.parseInt(txtQuantite.getText());
 
    Produit nouveauProduit = new Produit(nom, prix, quantite);

    ProduitDAO dao = new ProduitDAO();

    dao.insert(nouveauProduit);

    rafraichirListeProduits();

    effacerChamps();

}

 
