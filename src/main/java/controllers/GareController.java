package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gare;
import services.GareService;

import java.io.IOException;
import java.util.List;

@WebServlet("/GareController")
public class GareController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GareService gareService;

    public GareController() {
        super();
        gareService = new GareService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerGares(request, response);
                break;
            case "voir":
                afficherGare(request, response);
                break;
            case "new":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerGare(request, response);
                break;
            default:
                listerGares(request, response);
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
                ajouterGare(request, response);
                break;
            case "modifier":
                modifierGare(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listerGares(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Gare> gares = gareService.listerToutesLesGares();
        request.setAttribute("gares", gares);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/gares.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherGare(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Gare gare = gareService.trouverGareParId(id);
        request.setAttribute("gare", gare);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detailGare.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/gare-form.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Gare gare = gareService.trouverGareParId(id);
        request.setAttribute("gare", gare);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/gare-form.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterGare(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String ville = request.getParameter("ville");
       
        Gare gare = new Gare();
        gare.setNom(nom);
        gare.setVille(ville);
        
        gareService.ajouterGare(gare);
        response.sendRedirect("GareController?action=list");
    }

    private void modifierGare(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String ville = request.getParameter("ville");
        
        Gare gare = gareService.trouverGareParId(id);
        gare.setNom(nom);
        gare.setVille(ville);
        
        gareService.modifierGare(gare);
        response.sendRedirect("GareController?action=list");
    }

    private void supprimerGare(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        gareService.supprimerGare(id);
        response.sendRedirect("GareController?action=list");
    }
}
