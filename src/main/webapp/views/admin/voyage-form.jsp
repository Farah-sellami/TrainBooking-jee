<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />

<div class="container">
	<h1>${voyage != null ? "Modifier un voyage" : "Ajouter un nouveau voyage"}</h1>

	<form method="post"
		action="${pageContext.request.contextPath}/VoyageController">
		<input type="hidden" name="action" value="${voyage != null ? " modifier" : "ajouter"}" />

		<c:if test="${voyage != null}">
			<input type="hidden" name="id" value="${voyage.id}" />
		</c:if>

		<!-- Sélection du trajet -->
		<div class="form-group">
			<label for="trajetId">Trajet :</label> <select name="trajetId"
				id="trajetId" required>
				<option value="">-- Sélectionner un trajet --</option>
				<c:forEach var="trajet" items="${trajets}">
					<option value="${trajet.id}"
						<c:if test="${voyage != null && voyage.trajet.id == trajet.id}">selected</c:if>>
						${trajet.gareDepart.nom} → ${trajet.gareArrivee.nom}</option>
				</c:forEach>
			</select>
		</div>

		<!-- Date de départ -->
		<div class="form-group">
			<label for="dateDepart">Date de départ :</label> <input
				type="datetime-local" name="dateDepart" id="dateDepart" required
				value="${voyage != null ? voyage.dateDepart.toString().substring(0,16) : ''}" />
		</div>

		<!-- Date d'arrivée -->
		<div class="form-group">
			<label for="dateArrivee">Date d'arrivée :</label> <input
				type="datetime-local" name="dateArrivee" id="dateArrivee" required
				value="${voyage != null ? voyage.dateArrivee.toString().substring(0,16) : ''}" />
		</div>


		<!-- Nombre de places -->
		<div class="form-group">
			<label for="nombrePlaces">Nombre de places :</label> <input
				type="number" name="nombrePlaces" id="nombrePlaces" required min="1"
				value="${voyage != null ? voyage.nombrePlaces : ''}" />
		</div>

		<!-- Prix -->
		<div class="form-group">
			<label for="prix">Prix (dt) :</label> <input type="number"
				name="prix" id="prix" step="0.01" required
				value="${voyage != null ? voyage.prix : ''}" />
		</div>

		<div class="form-actions">
			<button type="submit" class="btn">${voyage != null ? "Mettre à jour" : "Ajouter"}</button>
			<a
				href="${pageContext.request.contextPath}/VoyageController?action=list"
				class="btn btn-secondary">Annuler</a>
		</div>
	</form>
</div>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
