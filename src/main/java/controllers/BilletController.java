package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Billet;
import model.Utilisateur;
import model.Voyage;
import services.BilletService;
import services.UtilisateurService;
import services.VoyageService;

import java.io.IOException;
import java.util.List;

@WebServlet("/BilletController")
public class BilletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BilletService billetService;
    private UtilisateurService utilisateurService;
    private VoyageService voyageService;

    public BilletController() {
        super();
        billetService = new BilletService();
        utilisateurService = new UtilisateurService();
        voyageService = new VoyageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerTousLesBillets(request, response);
                break;
            case "voir":
                afficherBillet(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "new":
                afficherFormulaireAjout(request, response);
                break;
            case "delete":
                supprimerBillet(request, response);
                break;
            case "mes":
                afficherMesBillets(request, response);
                break;
            default:
                listerTousLesBillets(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "";

        switch (action) {
            case "ajouter":
                ajouterBillet(request, response);
                break;
            case "modifier":
                modifierBillet(request, response);
                break;
                
            default:
                doGet(request, response);
                break;
        }
    }
    
    private void afficherMesBillets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        
        if (utilisateur == null) {
            response.sendRedirect("AuthController?action=login");
            return;
        }

        List<Billet> billets = billetService.trouverBilletsParUtilisateur(utilisateur.getId());
        request.setAttribute("reservations", billets);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mon_espace.jsp");
        dispatcher.forward(request, response);
    }


    private void listerTousLesBillets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Billet> billets = billetService.listerTousLesBillets();
        request.setAttribute("billets", billets);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listeBillets.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherBillet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Billet billet = billetService.trouverBilletParId(id);
        request.setAttribute("billet", billet);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detailBillet.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("utilisateurs", utilisateurService.listerTousUtilisateurs());
        request.setAttribute("voyages", voyageService.listerTousVoyages());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ajouterBillet.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Billet billet = billetService.trouverBilletParId(id);
        request.setAttribute("billet", billet);
        request.setAttribute("utilisateurs", utilisateurService.listerTousUtilisateurs());
        request.setAttribute("voyages", voyageService.listerTousVoyages());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/modifierBillet.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterBillet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long utilisateurId = Long.parseLong(request.getParameter("utilisateurId"));
        Long voyageId = Long.parseLong(request.getParameter("voyageId"));

        Utilisateur utilisateur = utilisateurService.trouverUtilisateurParId(utilisateurId);
        Voyage voyage = voyageService.trouverVoyageParId(voyageId);

        Billet billet = new Billet();
        billet.setUtilisateur(utilisateur);
        billet.setVoyage(voyage);
        billet.setNumeroBillet(request.getParameter("numeroBillet"));
        billet.setEstValide(Boolean.parseBoolean(request.getParameter("estValide")));

        billetService.ajouterBillet(billet);
        response.sendRedirect("BilletController?action=list");
    }

    private void modifierBillet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Long utilisateurId = Long.parseLong(request.getParameter("utilisateurId"));
        Long voyageId = Long.parseLong(request.getParameter("voyageId"));

        Utilisateur utilisateur = utilisateurService.trouverUtilisateurParId(utilisateurId);
        Voyage voyage = voyageService.trouverVoyageParId(voyageId);

        Billet billet = billetService.trouverBilletParId(id);
        billet.setUtilisateur(utilisateur);
        billet.setVoyage(voyage);
        billet.setNumeroBillet(request.getParameter("numeroBillet"));
        billet.setEstValide(Boolean.parseBoolean(request.getParameter("estValide")));

        billetService.modifierBillet(billet);
        response.sendRedirect("BilletController?action=list");
    }

    private void supprimerBillet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        billetService.supprimerBillet(id);
        response.sendRedirect("BilletController?action=list");
    }
    
    private void afficherMesBilletsAccueil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if (utilisateur == null) {
            response.sendRedirect("AuthController?action=login");
            return;
        }

        List<Billet> billets = billetService.trouverBilletsParUtilisateur(utilisateur.getId());
        request.setAttribute("reservations", billets);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/utilisateurs/accueil.jsp");
        dispatcher.forward(request, response);
    }

}
