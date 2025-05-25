package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;
import services.UtilisateurService;

import java.io.IOException;
import java.util.List;

@WebServlet("/UtilisateurController")
public class UtilisateurController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilisateurService utilisateurService;

    public UtilisateurController() {
        super();
        utilisateurService = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listUtilisateurs(request, response);
                break;
            case "new":
                showFormAjouter(request, response);
                break;
            case "edit":
                showFormModifier(request, response);
                break;
            case "delete":
                supprimerUtilisateur(request, response);
                break;
            case "bloquer":
                bloquerUtilisateur(request, response);
                break;
            case "debloquer":
                debloquerUtilisateur(request, response);
                break;
            default:
                listUtilisateurs(request, response);
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
                ajouterUtilisateur(request, response);
                break;
            case "modifier":
                modifierUtilisateur(request, response);
                break;
            
            default:
                doGet(request, response);
                break;
        }
    }

    private void listUtilisateurs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Utilisateur> utilisateurs = utilisateurService.listerTousUtilisateurs();
        request.setAttribute("utilisateurs", utilisateurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/utilisateurs.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormAjouter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/utilisateur-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormModifier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Utilisateur utilisateur = utilisateurService.trouverUtilisateurParId(id);
        request.setAttribute("utilisateur", utilisateur);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/utilisateur-form.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getParameter("nom"));
       
        utilisateur.setEmail(request.getParameter("email"));
        utilisateur.setMotDePasse(request.getParameter("motDePasse"));
        utilisateur.setRole(Utilisateur.Role.valueOf(request.getParameter("role")));

        // ajoute d'autres champs si besoin

        utilisateurService.ajouterUtilisateur(utilisateur);
        response.sendRedirect("UtilisateurController?action=list");
    }

    private void modifierUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Utilisateur utilisateur = utilisateurService.trouverUtilisateurParId(id);

        utilisateur.setNom(request.getParameter("nom"));
       
        utilisateur.setEmail(request.getParameter("email"));
        // Attention mot de passe : changer seulement si un nouveau est fourni
        String nouveauMdP = request.getParameter("motDePasse");
        if (nouveauMdP != null && !nouveauMdP.isEmpty()) {
            utilisateur.setMotDePasse(nouveauMdP);
        }
        utilisateur.setRole(Utilisateur.Role.valueOf(request.getParameter("role")));


        utilisateurService.modifierUtilisateur(utilisateur);
        response.sendRedirect("UtilisateurController?action=list");
    }

    private void supprimerUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        utilisateurService.supprimerUtilisateur(id);
        response.sendRedirect("UtilisateurController?action=list");
    }

    private void bloquerUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        utilisateurService.bloquerCompte(id);
        response.sendRedirect("UtilisateurController?action=list");
    }

    private void debloquerUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        utilisateurService.debloquerCompte(id);
        response.sendRedirect("UtilisateurController?action=list");
    }

}
