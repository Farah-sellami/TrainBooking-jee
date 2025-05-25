<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<div class="container">
    <h1>Gestion des Voyages</h1>

    <!-- Bouton pour ajouter un nouveau voyage -->
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/VoyageController?action=form" class="btn">Ajouter un nouveau voyage</a>
    </div>

    <!-- Message de confirmation ou erreur -->
    <c:if test="${not empty message}">
        <div class="message ${messageType}">${message}</div>
    </c:if>

    <!-- Tableau des voyages -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Trajet</th>
                <th>Date de départ</th>
                <th>Date d'arrivée</th>
                <th>Places</th>
                <th>Prix (DT)</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="voyage" items="${voyages}">
                <tr>
                    <td>${voyage.id}</td>
                    <td>${voyage.trajet.gareDepart.nom} → ${voyage.trajet.gareArrivee.nom}</td>
                    <td>${voyage.dateDepart}</td>
                    <td>${voyage.dateArrivee}</td>
                    <td>${voyage.nombrePlaces}</td>
                    <td>${voyage.prix}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/VoyageController?action=form&id=${voyage.id}" class="btn" style="background-color: #27ae60;">Modifier</a>
                        <a href="${pageContext.request.contextPath}/VoyageController?action=supprimer&id=${voyage.id}" class="btn" style="background-color: #e74c3c;"
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce voyage ?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty voyages}">
                <tr>
                    <td colspan="7" class="text-center">Aucun voyage trouvé.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
