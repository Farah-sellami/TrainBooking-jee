<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mon Espace</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f9fafb;
            margin: 0;
            padding: 0;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 2rem;
            background-color: white;
            border-bottom: 1px solid #e5e7eb;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .tabs {
            display: flex;
            justify-content: center;
            gap: 2rem;
            background-color: #f3f4f6;
            padding: 0.75rem 0;
            font-weight: bold;
        }

        .tabs div {
            cursor: pointer;
        }

        .content {
            max-width: 800px;
            margin: 2rem auto;
            background-color: white;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }

        .reservation {
            border-top: 1px solid #e5e7eb;
            padding-top: 1rem;
            margin-top: 1rem;
        }

        .reservation-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            margin-bottom: 1rem;
        }

        .reservation-info div {
            margin: 0.5rem 0;
        }

        .btn {
            padding: 0.4rem 0.8rem;
            margin: 0 0.25rem;
            border-radius: 6px;
            border: none;
            cursor: pointer;
        }

        .btn-modifier { background-color: #e0f2fe; color: #0284c7; }
        .btn-pdf { background-color: #f3f4f6; color: #111827; }
        .btn-annuler { background-color: #fee2e2; color: #dc2626; }

        .status {
            background-color: #d1fae5;
            color: #15803d;
            padding: 0.25rem 0.6rem;
            border-radius: 12px;
            font-size: 0.8rem;
            font-weight: bold;
        }

        .logout-btn {
            background-color: #111827;
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 8px;
            border: none;
        }
    </style>
</head>
<body>

<header>
    <div class="user-info">
        <span style="font-size: 1.5rem;">👤</span>
        <div>
            <div style="font-weight: bold;">Mon Espace</div>
            <div style="font-size: 0.9rem; color: #6b7280;">Bienvenue, <c:out value="${sessionScope.utilisateur.nom}" /></div>
        </div>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/AuthController?action=logout">
    <a class="logout-btn" href="${pageContext.request.contextPath}">Acceuil</a>
    <a class="logout-btn" href="${pageContext.request.contextPath}/AuthController?action=logout">Déconnexion</a>
        
    </form>
</header>

<div class="tabs">
    <div>Mes réservations (<c:out value="${fn:length(reservations)}" />)</div>
    <div>Historique (0)</div>
</div>

<div class="content">
    <h2>Billets achetés</h2>

    <c:forEach var="res" items="${reservations}">
        <div class="reservation">
            <div class="reservation-info">
                <div>
                    <div style="font-weight: bold;">${res.train}</div>
                    <div>${res.depart} → ${res.arrivee}</div>
                </div>
                <div>
                    <div>Date</div>
                    <div><strong>${res.date}</strong></div>
                </div>
                <div>
                    <div>Horaires</div>
                    <div><strong>${res.heureDepart} - ${res.heureArrivee}</strong></div>
                </div>
                <div>
                    <div>Classe</div>
                    <div><strong>${res.classe}</strong></div>
                </div>
                <div>
                    <div>Place</div>
                    <div><strong>${res.place}</strong></div>
                </div>
                <div>
                    <div style="color: green; font-size: 1.3rem;"><strong>${res.prix}€</strong></div>
                </div>
                <div class="status">acheté</div>
            </div>

            <div style="margin-top: 0.5rem;">
                <button class="btn btn-modifier">✏️ Modifier</button>
                <button class="btn btn-pdf">⬇️ PDF</button>
                <button class="btn btn-annuler">❌ Annuler</button>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
