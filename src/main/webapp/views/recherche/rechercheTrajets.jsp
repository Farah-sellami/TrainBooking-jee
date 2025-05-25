<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>TrainBooking - Recherche de trajets</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">

   <!-- Font Awesome 6 (ou 5 si tu préfères) -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" referrerpolicy="no-referrer" />
   
    <style>
        body {
            margin: 0;
            font-family: 'Inter', sans-serif;
            background: linear-gradient(to bottom right, #0f2c79, #0c7256);
            color: #fff;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 50px;
        }

        .logo {
            font-size: 24px;
            font-weight: 800;
            display: flex;
            align-items: center;
        }

        .logo::before {
            content: "🚆";
            margin-right: 10px;
        }

        .actions a {
            margin-left: 20px;
            text-decoration: none;
            font-weight: 600;
            padding: 10px 20px;
            border-radius: 8px;
            background: white;
            color: #0f2c79;
        }

        .hero {
            text-align: center;
            padding: 100px 20px 50px;
        }

        .hero h1 {
            font-size: 3em;
            margin-bottom: 10px;
        }

        .hero h1 span {
            color: #3dd3a4;
        }

        .hero p {
            font-size: 1.2em;
            color: #dce3f0;
        }

        .search-box {
            background: #fff;
            padding: 30px;
            border-radius: 16px;
            max-width: 900px;
            margin: 30px auto;
            display: flex;
            gap: 20px;
            align-items: center;
            flex-wrap: wrap;
        }

        .search-box input[type="text"],
        .search-box input[type="date"] {
            flex: 1;
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 1em;
        }

        .search-box button {
            background: linear-gradient(to right, #0575e6, #00f260);
            color: white;
            border: none;
            padding: 12px 30px;
            font-size: 1em;
            font-weight: bold;
            border-radius: 8px;
            cursor: pointer;
        }

        h2 {
            text-align: center;
            margin-top: 40px;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            border-radius: 12px;
            overflow: hidden;
            color: #333;
        }

        th, td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #eee;
        }

        th {
            background-color: #0f2c79;
            color: white;
        }

        tr:hover {
            background-color: #f3f9ff;
        }

        .section {
            background: white;
            color: #1a1a1a;
            padding: 80px 20px;
            text-align: center;
        }

        .section h2 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }

        .section p {
            color: #555;
            font-size: 1.1em;
            margin-bottom: 50px;
        }

        .features {
            display: flex;
            justify-content: center;
            gap: 30px;
            flex-wrap: wrap;
        }

        .card {
            background: #fff;
            border: 1px solid #eee;
            border-radius: 12px;
            padding: 30px 20px;
            width: 300px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
            transition: transform 0.2s ease;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card i {
            font-size: 2.5em;
            margin-bottom: 15px;
        }

        .card h3 {
            margin: 10px 0;
            font-size: 1.3em;
        }

        .card p {
            font-size: 0.95em;
            color: #666;
        }

        footer {
            background-color: #0c0c1d;
            color: white;
            padding: 40px 20px;
            text-align: center;
        }

        footer .logo {
            font-size: 1.5em;
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;
            margin-bottom: 10px;
        }

        footer .logo i {
            font-size: 1.3em;
        }

        footer p {
            font-size: 0.9em;
            color: #ccc;
        }

        @media (max-width: 768px) {
            .search-box {
                flex-direction: column;
            }

            .search-box input,
            .search-box button {
                width: 100%;
            }

            .features {
                flex-direction: column;
                align-items: center;
            }
        }
    </style>
</head>
<body>

<header>
    <div class="logo">TrainBooking</div>
    <div class="actions">
        <a href="${pageContext.request.contextPath}/AuthController">Connexion</a>
        <a href="${pageContext.request.contextPath}/AuthController">Inscription</a>
    </div>
</header>

<section class="hero">
    <h1>Voyagez en toute <span>simplicité</span></h1>
    <p>Réservez vos billets de train en quelques clics. Plus de 10 000 destinations disponibles.</p>
</section>



<form  class="search-box"  method="get" action="${pageContext.request.contextPath}/TrajetController">
    <input type="hidden" name="action" value="search" />
    <input type="text" name="villeDepart" placeholder="Ville départ" required />
    <input type="text" name="villeArrivee" placeholder="Ville arrivée" required />
    <button type="submit">Rechercher</button>
</form>

<c:if test="${not empty voyages}">
    <h2>Résultats de la recherche</h2>
    <table>
        <thead>
            <tr>
                <th>Heure départ</th>
                <th>Heure arrivée</th>
             
                <th>Prix</th>
                <th>Disponibilité</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="voyage" items="${voyages}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${not empty voyage.dateDepartAsDate}">
                                <fmt:formatDate value="${voyage.dateDepartAsDate}" pattern="HH:mm" />
                            </c:when>
                            <c:otherwise>--:--</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty voyage.dateArriveeAsDate}">
                                <fmt:formatDate value="${voyage.dateArriveeAsDate}" pattern="HH:mm" />
                            </c:when>
                            <c:otherwise>--:--</c:otherwise>
                        </c:choose>
                    </td>
                    
                    <td>${voyage.prix} DT</td>
                    <td>
                        <c:choose>
                            <c:when test="${voyage.nombrePlaces > 0}">
                            Disponible
                              <!-- 🔘 Bouton Réserver -->
                        <form method="get" action="${pageContext.request.contextPath}/VoyageController" style="display:inline;">
    <input type="hidden" name="action" value="confirmationAchat" />
    <input type="hidden" name="voyageId" value="${voyage.id}" />
    <button type="submit">Réserver</button>
</form>

                            </c:when>
                            <c:otherwise>Complet</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>


<!-- Section Pourquoi choisir -->
<section class="section">
    <h2>Pourquoi choisir <strong>TrainBooking</strong> ?</h2>
    <p>Une plateforme moderne et sécurisée pour tous vos voyages en train</p>

    <div class="features">
        <div class="card">
            <i class="fas fa-search" style="color: #3366ff;"></i>
            <h3>Recherche simplifiée</h3>
            <p>Trouvez rapidement le trajet idéal parmi des milliers d'options disponibles</p>
        </div>
        <div class="card">
            <i class="fas fa-shield-alt" style="color: #28a745;"></i>
            <h3>Paiement sécurisé</h3>
            <p>Transactions 100% sécurisées avec les dernières technologies de cryptage</p>
        </div>
        <div class="card">
            <i class="fas fa-calendar-alt" style="color: #a638ff;"></i>
            <h3>Gestion flexible</h3>
            <p>Modifiez ou annulez vos réservations facilement depuis votre espace personnel</p>
        </div>
    </div>
</section>

<!-- Footer -->
<footer>
    <div class="logo">
        <i class="fas fa-train"></i>
        TrainBooking
    </div>
    <p>© 2025 TrainBooking. Tous droits réservés.</p>
</footer>

</body>
</html>
