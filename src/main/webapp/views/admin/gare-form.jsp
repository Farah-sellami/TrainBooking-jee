<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />

<h2>
    <c:choose>
        <c:when test="${gare != null}">
            Modifier la gare
        </c:when>
        <c:otherwise>
            Ajouter une nouvelle gare
        </c:otherwise>
    </c:choose>
</h2>

<form action="${pageContext.request.contextPath}/GareController" method="post">

    <c:if test="${gare != null}">
        <input type="hidden" name="id" value="${gare.id}" />
    </c:if>

    <input type="hidden" name="action" value="${gare != null ? 'modifier' : 'ajouter'}" />

    <label for="nom">Nom de la gare :</label><br/>
    <input type="text" id="nom" name="nom" required
           value="<c:out value='${gare != null ? gare.nom : ""}'/>" />
    <br/><br/>

    <label for="ville">Ville :</label><br/>
    <input type="text" id="ville" name="ville" required
           value="<c:out value='${gare != null ? gare.ville : ""}'/>" />
    <br/><br/>

    <input type="submit" value="${gare != null ? 'Modifier' : 'Ajouter'}" />

</form>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
