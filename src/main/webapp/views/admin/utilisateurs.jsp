<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<div class="container">
    <h1>Gestion des Utilisateurs</h1>

    <!-- Bouton ajouter un nouvel utilisateur -->
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/UtilisateurController?action=new" class="btn" style="background-color: #2980b9; color: white;">Ajouter un nouvel utilisateur</a>
    </div>

    <!-- Message de confirmation ou erreur -->
    <c:if test="${not empty message}">
        <div class="message ${messageType}">${message}</div>
    </c:if>

    <!-- Tableau des utilisateurs -->
    <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; border-collapse: collapse;">
        <thead style="background-color: #f4f4f4;">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Email</th>
                <th>Rôle</th>
                <th>Statut</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="utilisateur" items="${utilisateurs}">
                <tr>
                    <td>${utilisateur.id}</td>
                    <td>${utilisateur.nom}</td>
                    <td>${utilisateur.email}</td>
                    <td>${utilisateur.role}</td>
                    <td>
                        <c:choose>
                            <c:when test="${utilisateur.estBloque}">
                                <span style="color: red; font-weight: bold;">Bloqué</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green; font-weight: bold;">Actif</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/UtilisateurController?action=edit&id=${utilisateur.id}" 
                           class="btn" style="background-color: #27ae60; color: white; padding: 5px 10px; text-decoration: none; margin-right: 5px;">
                           Modifier
                        </a>

                        <a href="${pageContext.request.contextPath}/UtilisateurController?action=delete&id=${utilisateur.id}" 
                           class="btn" style="background-color: #e74c3c; color: white; padding: 5px 10px; text-decoration: none; margin-right: 5px;"
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?');">
                           Supprimer
                        </a>

                        <c:choose>
                            <c:when test="${!utilisateur.estBloque}">
                                <a href="${pageContext.request.contextPath}/UtilisateurController?action=bloquer&id=${utilisateur.id}"
                                   class="btn" style="background-color: #f39c12; color: white; padding: 5px 10px; text-decoration: none;">
                                   Bloquer
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/UtilisateurController?action=debloquer&id=${utilisateur.id}"
                                   class="btn" style="background-color: #2980b9; color: white; padding: 5px 10px; text-decoration: none;">
                                   Débloquer
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty utilisateurs}">
                <tr>
                    <td colspan="6" style="text-align: center;">Aucun utilisateur trouvé.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
