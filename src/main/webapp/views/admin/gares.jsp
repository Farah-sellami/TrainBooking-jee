<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<div class="container">
    <h1>Gestion des Gares</h1>

<!-- Bouton ajouter une nouvelle gare -->
<a href="${pageContext.request.contextPath}/GareController?action=new"  class="btn" style="background-color: #27ae60; color: white; padding: 5px 10px; text-decoration: none; margin-right: 5px;">Ajouter une nouvelle gare</a>
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
                  
                    <a href="${pageContext.request.contextPath}/GareController?action=edit&id=${gare.id}" class="btn" style="background-color: #f39c12; color: white; padding: 5px 10px; text-decoration: none;">Modifier</a>
                    <a href="${pageContext.request.contextPath}/GareController?action=delete&id=${gare.id}" class="btn" style="background-color: #e74c3c; color: white; padding: 5px 10px; text-decoration: none; margin-right: 5px;" 
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
</div>
<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />

