package controllers;

import model.Voyage;
import model.Trajet;  // si besoin de récupérer le trajet dans le service ou DAO
import services.VoyageService;
import services.TrajetService; // si nécessaire pour récupérer un trajet

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(urlPatterns = {"/VoyageController", "/"})
public class VoyageController extends HttpServlet {

    private final VoyageService voyageService = new VoyageService();
    private final TrajetService trajetService = new TrajetService(); // si besoin

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Afficher le formulaire de recherche
            request.getRequestDispatcher("/views/recherche/rechercheTrajets.jsp").forward(request, response);
            return;
        }

        if ("list".equals(action)) {
            List<Voyage> voyages = voyageService.listerTousVoyages();
            request.setAttribute("voyages", voyages);
            request.getRequestDispatcher("/views/admin/voyages.jsp").forward(request, response);

        } else if ("details".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Voyage voyage = voyageService.trouverVoyageParId(id);
            request.setAttribute("voyage", voyage);
            request.getRequestDispatcher("/voyage-details.jsp").forward(request, response);

        } else if ("form".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                Long id = Long.parseLong(idStr);
                Voyage voyage = voyageService.trouverVoyageParId(id);
                request.setAttribute("voyage", voyage);
            }
            request.setAttribute("trajets", trajetService.listerTousLesTrajets());
            request.getRequestDispatcher("/views/admin/voyage-form.jsp").forward(request, response);

        } 
        else if ("confirmationAchat".equals(action)) {
            String idStr = request.getParameter("voyageId");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    Long id = Long.parseLong(idStr);
                    Voyage voyage = voyageService.trouverVoyageParId(id);
                    request.setAttribute("voyage", voyage);
                } catch (NumberFormatException e) {
                    request.setAttribute("messageErreur", "Identifiant de voyage invalide.");
                }
            }
            request.getRequestDispatcher("/views/utilisateurs/confirmationAchat.jsp").forward(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("ajouter".equals(action) || "modifier".equals(action)) {
            Voyage voyage = new Voyage();

            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                voyage.setId(Long.parseLong(idStr));
            }

            Long trajetId = Long.parseLong(request.getParameter("trajetId"));
            Trajet trajet = trajetService.trouverTrajetParId(trajetId);
            voyage.setTrajet(trajet);

            String dateDepartStr = request.getParameter("dateDepart");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateDepart = LocalDateTime.parse(dateDepartStr, formatter);
            voyage.setDateDepart(dateDepart);

            // Autres propriétés à définir...

            if ("ajouter".equals(action)) {
                voyageService.ajouterVoyage(voyage);
            } else {
                voyageService.modifierVoyage(voyage);
            }

            response.sendRedirect(request.getContextPath() + "/VoyageController?action=list");

        } else if ("supprimer".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            voyageService.supprimerVoyage(id);
            response.sendRedirect(request.getContextPath() + "/VoyageController?action=list");

        } else if ("rechercher".equals(action)) {
            String villeDepart = request.getParameter("villeDepart");
            String villeArrivee = request.getParameter("villeArrivee");
            String dateDepartStr = request.getParameter("dateDepart");

            // Parser la date au format yyyy-MM-dd (date seule) en LocalDateTime début de journée
            LocalDate dateDepart = java.time.LocalDate.parse(dateDepartStr);

            List<Voyage> voyages = voyageService.rechercherVoyagesParGareEtDate(villeDepart, villeArrivee, dateDepart);
            request.setAttribute("voyages", voyages);

            // Renvoyer vers la page de recherche avec résultats
            request.getRequestDispatcher("/views/recherche/rechercheTrajets.jsp").forward(request, response);

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue");
        }
    }
}