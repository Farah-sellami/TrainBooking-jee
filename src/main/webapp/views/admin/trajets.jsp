<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<div class="container">
    <h1>Gestion des Trajets</h1>

    <!-- Bouton ajouter un nouveau trajet -->
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/TrajetController?action=new" class="btn">Ajouter un nouveau trajet</a>

    </div>

    <!-- Message de confirmation ou erreur -->
    <c:if test="${not empty message}">
        <div class="message ${messageType}">${message}</div>
    </c:if>

    <!-- Tableau des trajets -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Gare de départ</th>
                <th>Gare d'arrivée</th>
                <th>Distance (km)</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trajet" items="${trajets}">
                <tr>
                    <td>${trajet.id}</td>
                    <td>${trajet.gareDepart.nom}</td>
                    <td>${trajet.gareArrivee.nom}</td>
                    <td>${trajet.distanceKm}</td>
                    <td>
                      <a href="${pageContext.request.contextPath}/TrajetController?action=edit&id=${trajet.id}" class="btn" style="background-color: #27ae60;">Modifier</a>
                        <a href="${pageContext.request.contextPath}/TrajetController?action=delete&id=${trajet.id}" class="btn" style="background-color: #e74c3c;" 
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce trajet ?');">Supprimer
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty trajets}">
                <tr>
                    <td colspan="5" class="text-center">Aucun trajet trouvé.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
