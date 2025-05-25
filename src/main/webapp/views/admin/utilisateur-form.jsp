<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />

<div class="container">
    <h1>
        <c:choose>
            <c:when test="${not empty utilisateur}">Modifier Utilisateur</c:when>
            <c:otherwise>Ajouter Utilisateur</c:otherwise>
        </c:choose>
    </h1>

    <form action="${pageContext.request.contextPath}/UtilisateurController" method="post">
        <!-- Si on modifie, on ajoute un champ caché id -->
        <c:if test="${not empty utilisateur}">
            <input type="hidden" name="id" value="${utilisateur.id}" />
        </c:if>

        <!-- Action différente selon ajout ou modif -->
        <input type="hidden" name="action" value="<c:out value='${empty utilisateur ? "ajouter" : "modifier"}'/>" />

        <div class="form-group">
            <label for="nom">Nom :</label>
            <input type="text" id="nom" name="nom" value="${utilisateur.nom}" required />
        </div>

        <div class="form-group">
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" value="${utilisateur.email}" required />
        </div>

        <div class="form-group">
            <label for="motDePasse">
                Mot de passe :
                <c:if test="${not empty utilisateur}">
                    <small>(laisser vide pour ne pas changer)</small>
                </c:if>
            </label>
            <input type="password" id="motDePasse" name="motDePasse" />
        </div>

        <div class="form-group">
           <!-- <label for="role">Rôle :</label>
            <select id="role" name="role" required>
                <option value="">-- Sélectionnez un rôle --</option>
                <option value="ADMIN" <c:if test="${utilisateur.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
                <option value="USER" <c:if test="${utilisateur.role == 'USER' || empty utilisateur}">selected</c:if>>USER</option>
            </select> --> 
            
            <input type="hidden" name="role" value="USER" />
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${not empty utilisateur}">Modifier</c:when>
                <c:otherwise>Ajouter</c:otherwise>
            </c:choose>
        </button>
        <a href="${pageContext.request.contextPath}/UtilisateurController?action=list" class="btn btn-secondary">Annuler</a>
    </form>
</div>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
