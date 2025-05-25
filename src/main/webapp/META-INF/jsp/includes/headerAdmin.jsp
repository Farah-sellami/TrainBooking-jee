<!-- /WEB-INF/jsp/includes/header.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Mon Application de Voyage</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<header>
    <h1>Bienvenue sur Mon Site de Voyage</h1>
</header>
<nav>
    <a href="${pageContext.request.contextPath}/TrajetController?action=list">Trajets</a> | |
    <a href="${pageContext.request.contextPath}/GareController?action=list">Gares</a> | |
    <a href="${pageContext.request.contextPath}/VoyageController?action=list">Voyages</a> | |
 <a href="${pageContext.request.contextPath}/UtilisateurController?action=list">Utilisateurs</a>| |
    
     <a href="${pageContext.request.contextPath}/AuthController?action=logout">Déconnexion</a>
    
</nav>
<hr/>
