<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/META-INF/jsp/includes/headerAdmin.jsp" />
<div class="container">
    <h1>Dashboard</h1>

 

    <!-- Message de confirmation ou erreur -->
    <c:if test="${not empty message}">
        <div class="message ${messageType}">${message}</div>
    </c:if>


</div>

<jsp:include page="/META-INF/jsp/includes/footerAdmin.jsp" />
