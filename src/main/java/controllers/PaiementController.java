package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Billet;
import model.Paiement;
import model.Utilisateur;
import model.Voyage;
import services.BilletService;
import services.PaiementService;
import services.VoyageService;

import java.io.IOException;
import java.util.List;

@WebServlet("/PaiementController")
public class PaiementController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaiementService paiementService;

    public PaiementController() {
        super();
        paiementService = new PaiementService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerPaiements(request, response);
                break;
            case "voir":
                afficherPaiement(request, response);
                break;
            case "new":
                afficherFormulaireAjout(request, response);
                break;
            case "delete":
                supprimerPaiement(request, response);
                break;
            case "validerPaiement":
                validerPaiement(request, response);
                break;

            default:
                listerPaiements(request, response);
                break;
        }
    }
    
    private void validerPaiement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long voyageId = Long.parseLong(request.getParameter("voyageId"));
            String methodePaiement = request.getParameter("methodePaiement");

            // ⚠️ Vérifie que l'utilisateur est connecté
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
            if (utilisateur == null) {
            	//response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // 🔍 Récupère les services nécessaires
            VoyageService voyageService = new VoyageService();
            BilletService billetService = new BilletService();
            Voyage voyage = voyageService.trouverVoyageParId(voyageId);

            if (voyage == null || voyage.getNombrePlaces() <= 0) {
                request.setAttribute("messageErreur", "Le voyage est complet ou introuvable.");
                request.getRequestDispatcher("erreurPaiement.jsp").forward(request, response);
                return;
            }

            // 📄 Création du billet
            Billet billet = new Billet();
            billet.setUtilisateur(utilisateur);
            billet.setVoyage(voyage);
            billet.setNumeroBillet("BIL" + System.currentTimeMillis()); // Numéro unique
            billet.setEstValide(true);
            billetService.ajouterBillet(billet);

            // 💰 Création du paiement
            Paiement paiement = new Paiement();
            paiement.setBillet(billet);
            paiement.setMontant(voyage.getPrix());
            paiement.setDatePaiement(new java.sql.Date(System.currentTimeMillis()));
            paiement.setMethodePaiement(methodePaiement);
            paiementService.ajouterPaiement(paiement);

            // 🎯 Mettre à jour le nombre de places
            voyage.setNombrePlaces(voyage.getNombrePlaces() - 1);
            voyageService.modifierVoyage(voyage);

            request.setAttribute("paiement", paiement);
            request.getRequestDispatcher("/views/utilisateurs/paiementReussi.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messageErreur", "Erreur lors de la validation du paiement.");
            request.getRequestDispatcher("/views/utilisateurs/erreurPaiement.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "ajouter":
                ajouterPaiement(request, response);
                break;
            case "validerPaiement":
                validerPaiement(request, response);
                break;

            default:
                doGet(request, response);
                break;
        }
    }

    private void listerPaiements(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Paiement> paiements = paiementService.listerTousLesPaiements();
        request.setAttribute("paiements", paiements);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listePaiements.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherPaiement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Paiement paiement = paiementService.trouverPaiementParId(id);
        request.setAttribute("paiement", paiement);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detailPaiement.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si tu as besoin de données liées à afficher dans le formulaire, les mets ici
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ajouterPaiement.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterPaiement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paiement paiement = new Paiement();

        // Exemple : récupérer des champs depuis le formulaire, adapte selon ton modèle
        paiement.setMontant(Double.parseDouble(request.getParameter("montant")));
        paiement.setDatePaiement(java.sql.Date.valueOf(request.getParameter("datePaiement")));
        // Ajoute d'autres champs selon ta classe Paiement

        paiementService.ajouterPaiement(paiement);
        response.sendRedirect("PaiementController?action=list");
    }

    private void supprimerPaiement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        // si tu as une méthode supprimer dans PaiementService, sinon tu peux l'ajouter
        // paiementService.supprimerPaiement(id);
        // ici juste redirection
        response.sendRedirect("PaiementController?action=list");
    }
}
