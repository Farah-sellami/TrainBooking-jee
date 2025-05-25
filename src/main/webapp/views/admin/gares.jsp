<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<h2>Liste des gares</h2>

<!-- Bouton ajouter une nouvelle gare -->
<a href="${pageContext.request.contextPath}/GareController?action=new" class="btn btn-add">Ajouter une nouvelle gare</a>
<br> <br>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom de la gare</th>
             <th>Ville de la gare</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="gare" items="${gares}">
            <tr>
                <td>${gare.id}</td>
                <td>${gare.nom}</td>
                 <td>${gare.ville}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/GareController?action=voir&id=${gare.id}" class="btn btn-view">Voir</a>
                    <a href="${pageContext.request.contextPath}/GareController?action=edit&id=${gare.id}" class="btn btn-edit">Modifier</a>
                    <a href="${pageContext.request.contextPath}/GareController?action=delete&id=${gare.id}" class="btn btn-delete" 
                       onclick="return confirm('Voulez-vous vraiment supprimer cette gare ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty gares}">
            <tr>
                <td colspan="3">Aucune gare trouvée.</td>
            </tr>
        </c:if>
    </tbody>
</table>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />

