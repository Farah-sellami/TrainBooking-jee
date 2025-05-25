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

@WebServlet("/AuthController")
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilisateurService utilisateurService;

    public AuthController() {
        super();
        utilisateurService = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Affiche la page login ou register selon paramètre action
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/register.jsp");
            dispatcher.forward(request, response);
        } else if ("logout".equals(action)) {
            // Déconnexion : invalider la session et rediriger vers login
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/AuthController");
        } else {
            // Par défaut : page login
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            login(request, response);
        } else if ("register".equals(action)) {
            register(request, response);
        } else {
            response.sendRedirect("AuthController");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        Utilisateur utilisateur = utilisateurService.authentifierUtilisateur(email, motDePasse);
        if (utilisateur != null && !utilisateur.isEstBloque()) {
            request.getSession().setAttribute("utilisateurConnecte", utilisateur);
            
            String contextPath = request.getContextPath();  
            
            if (utilisateur.getRole() == Utilisateur.Role.ADMIN) {
                response.sendRedirect(contextPath + "/views/admin/dashboard.jsp");
            } else {
                response.sendRedirect(contextPath + "/views/utilisateurs/accueil.jsp");
            }
        } else {
            request.setAttribute("erreur", "Email ou mot de passe incorrect ou compte bloqué.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/login.jsp");
            dispatcher.forward(request, response);
        }
    }


    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
       // String roleStr = request.getParameter("role");

        if (nom == null || email == null || motDePasse == null  ||
            nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
            request.setAttribute("erreur", "Tous les champs sont obligatoires.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (utilisateurService.trouverUtilisateurParEmail(email) != null) {
            request.setAttribute("erreur", "Un compte avec cet email existe déjà.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setRole(Utilisateur.Role.USER);
        utilisateurService.ajouterUtilisateur(utilisateur);

        // Après inscription, rediriger vers login
        response.sendRedirect("AuthController");
    }
}
