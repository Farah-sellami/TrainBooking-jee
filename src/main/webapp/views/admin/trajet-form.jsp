<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<h2>
    <c:choose>
        <c:when test="${trajet != null}">
            Modifier un trajet
        </c:when>
        <c:otherwise>
            Ajouter un nouveau trajet
        </c:otherwise>
    </c:choose>
</h2>

<form action="${pageContext.request.contextPath}/TrajetController" method="post">

    <!-- Champ caché pour l'action (ajouter ou modifier) -->
    <input type="hidden" name="action" value="${trajet != null ? 'modifier' : 'ajouter'}" />

    <!-- Si on modifie, on inclut l'id en hidden -->
    <c:if test="${trajet != null}">
        <input type="hidden" name="id" value="${trajet.id}" />
    </c:if>

    <label for="gareDepartId">Gare de départ :</label>
    <select name="gareDepartId" id="gareDepartId" required>
        <option value="">-- Sélectionnez --</option>
        <c:forEach var="gare" items="${gares}">
            <option value="${gare.id}" <c:if test="${trajet != null && trajet.gareDepart.id == gare.id}">selected</c:if>>
                ${gare.nom}
            </option>
        </c:forEach>
    </select>
    <br/><br/>

    <label for="gareArriveeId">Gare d'arrivée :</label>
    <select name="gareArriveeId" id="gareArriveeId" required>
        <option value="">-- Sélectionnez --</option>
        <c:forEach var="gare" items="${gares}">
            <option value="${gare.id}" <c:if test="${trajet != null && trajet.gareArrivee.id == gare.id}">selected</c:if>>
                ${gare.nom}
            </option>
        </c:forEach>
    </select>
    <br/><br/>

    <label for="distanceKm">Distance (km) :</label>
    <input type="number" step="0.01" name="distanceKm" id="distanceKm" required
        value="<c:out value='${trajet != null ? trajet.distanceKm : ""}'/>" />
    <br/><br/>

    <!-- Bouton submit simple avec texte dynamique -->
    <input type="submit" value="${trajet != null ? 'Modifier' : 'Ajouter'}" />

</form>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
