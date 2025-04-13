package com.monapp.model;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    public void generateStockReport() {
        try {
            // 1. Charger le modèle de rapport Jasper (JRXML)
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report_template.jrxml"));

            // 2. Obtenir les produits à jour depuis la base via DAO
            ProduitDAO produitDAO = new ProduitDAO();
            List<Produit> produits = produitDAO.getAllProduits();  // 🔥 données toujours à jour

            // 3. Créer une source de données Jasper
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(produits);

            // 4. Paramètres optionnels
            Map<String, Object> parameters = new HashMap<>();

            // 5. Remplir le rapport et exporter en PDF
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "stock_report.pdf");

            // Confirmation de génération
            System.out.println("✅ Rapport PDF généré avec succès.");
        } catch (JRException e) {
            System.out.println("❌ Erreur lors de la génération du rapport Jasper : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
