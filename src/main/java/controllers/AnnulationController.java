package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DemandeAnnulation;
import services.AnnulationService;

import java.io.IOException;
import java.util.List;

@WebServlet("/AnnulationController")
public class AnnulationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AnnulationService annulationService;

    public AnnulationController() {
        super();
        annulationService = new AnnulationService(); // ✅ couche service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerDemandes(request, response);
                break;
            case "enAttente":
                listerDemandesEnAttente(request, response);
                break;
            case "utilisateur":
                listerParUtilisateur(request, response);
                break;
            case "voir":
                afficherDemande(request, response);
                break;
            default:
                listerDemandes(request, response);
                break;
        }
    }

    private void listerDemandes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DemandeAnnulation> demandes = annulationService.listerToutesDemandes();
        request.setAttribute("demandes", demandes);
        forward(request, response, "/listeAnnulations.jsp");
    }

    private void listerDemandesEnAttente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DemandeAnnulation> demandes = annulationService.listerDemandesEnAttente();
        request.setAttribute("demandes", demandes);
        forward(request, response, "/annulationsEnAttente.jsp");
    }

    private void listerParUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long utilisateurId = Long.parseLong(request.getParameter("utilisateurId"));
        List<DemandeAnnulation> demandes = annulationService.listerDemandesParUtilisateur(utilisateurId);
        request.setAttribute("demandes", demandes);
        forward(request, response, "/annulationsParUtilisateur.jsp");
    }

    private void afficherDemande(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        DemandeAnnulation demande = annulationService.trouverDemandeParId(id);
        request.setAttribute("demande", demande);
        forward(request, response, "/detailAnnulation.jsp");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
