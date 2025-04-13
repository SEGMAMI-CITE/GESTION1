package com.monapp.model;

public class MouvementStock {
    private int id;
    private int produitId;
    private String typeMouvement;
    private int quantite;

    public MouvementStock(int produitId, String typeMouvement, int quantite) {
        this.produitId = produitId;
        this.typeMouvement = typeMouvement;
        this.quantite = quantite;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
