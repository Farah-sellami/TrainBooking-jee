package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Trajet;
import model.Voyage;
import model.Gare;
import services.TrajetService;
import services.VoyageService;
import services.GareService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TrajetController")
public class TrajetController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private TrajetService trajetService;
    private GareService gareService;
    private VoyageService voyageService;

    public TrajetController() {
        super();
        trajetService = new TrajetService();
        gareService = new GareService();
        voyageService = new VoyageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listTrajets(request, response);
                break;
            case "new":
                showFormAjouter(request, response);
                break;
            case "edit":
                showFormModifier(request, response);
                break;
            case "delete":
                supprimerTrajet(request, response);
                break;
            case "search":  // <<-- nouvelle action pour la recherche
                rechercherTrajets(request, response);
                break;
            default:
                listTrajets(request, response);
                break;
        }
    }

    private void rechercherTrajets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");

        // Important : affiche les valeurs pour debug
        System.out.println("Recherche pour : " + villeDepart + " -> " + villeArrivee);

        // Appel du service pour trouver les trajets
        List<Trajet> trajets = trajetService.rechercherTrajets(villeDepart, villeArrivee);

        List<Voyage> voyages = new ArrayList<>();
        for (Trajet trajet : trajets) {
            voyages.addAll(voyageService.findByTrajet(trajet));
        }

        // Mise en attribut pour le JSP
        request.setAttribute("voyages", voyages);

        // Revenir sur la même page JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/recherche/rechercheTrajets.jsp");
        dispatcher.forward(request, response);
    }



	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "ajouter":
                ajouterTrajet(request, response);
                break;
            case "modifier":
                modifierTrajet(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listTrajets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Trajet> trajets = trajetService.listerTousLesTrajets();
        request.setAttribute("trajets", trajets);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/trajets.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormAjouter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Gare> gares = gareService.listerToutesLesGares();
        request.setAttribute("gares", gares);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/trajet-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormModifier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Trajet trajet = trajetService.trouverTrajetParId(id);
        List<Gare> gares = gareService.listerToutesLesGares();

        request.setAttribute("trajet", trajet);
        request.setAttribute("gares", gares);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/trajet-form.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterTrajet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long gareDepartId = Long.parseLong(request.getParameter("gareDepartId"));
        Long gareArriveeId = Long.parseLong(request.getParameter("gareArriveeId"));
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));

        Gare gareDepart = gareService.trouverGareParId(gareDepartId);
        Gare gareArrivee = gareService.trouverGareParId(gareArriveeId);

        Trajet trajet = new Trajet();
        trajet.setGareDepart(gareDepart);
        trajet.setGareArrivee(gareArrivee);
        trajet.setDistanceKm(distanceKm);

        trajetService.ajouterTrajet(trajet);
        response.sendRedirect("TrajetController?action=list");
    }

    private void modifierTrajet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long id = Long.parseLong(request.getParameter("id"));
        Long gareDepartId = Long.parseLong(request.getParameter("gareDepartId"));
        Long gareArriveeId = Long.parseLong(request.getParameter("gareArriveeId"));
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));

        Gare gareDepart = gareService.trouverGareParId(gareDepartId);
        Gare gareArrivee = gareService.trouverGareParId(gareArriveeId);

        Trajet trajet = trajetService.trouverTrajetParId(id);
        trajet.setGareDepart(gareDepart);
        trajet.setGareArrivee(gareArrivee);
        trajet.setDistanceKm(distanceKm);

        trajetService.modifierTrajet(trajet);
        response.sendRedirect("TrajetController?action=list");
    }

    private void supprimerTrajet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long id = Long.parseLong(request.getParameter("id"));
        trajetService.supprimerTrajet(id);
        response.sendRedirect("TrajetController?action=list");
    }
    
    
}
