package com.monapp.model;

public class Produit {

    private int id;
    private String nom;
    private String description;
    private int quantite;
    private double prix;

    // Constructeur avec id (utilisé pour getAllProduits, modifier, etc.)
    public Produit(int id, String nom, String description, int quantite, double prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
    }

    // Constructeur sans id (utilisé pour ajouterProduit)
    public Produit(String nom, String description, int quantite, double prix) {
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", quantite=" + quantite +
                ", prix=" + prix +
                '}';
    }
}
