<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Confirmation d'achat</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            background-color: white;
            margin: 50px auto;
            padding: 30px 40px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
        }

        h2, h3 {
            text-align: center;
            color: #333;
        }

        p {
            font-size: 16px;
            margin-bottom: 12px;
        }

        strong {
            color: #555;
        }

        hr {
            margin: 25px 0;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        label {
            font-size: 15px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        button {
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .error {
            text-align: center;
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Confirmation d'achat</h2>

    <c:if test="${not empty voyage}">
        <p><strong>Départ :</strong>
            <fmt:formatDate value="${voyage.dateDepartAsDate}" pattern="yyyy-MM-dd HH:mm" />
        </p>
        <p><strong>Arrivée :</strong>
            <fmt:formatDate value="${voyage.dateArriveeAsDate}" pattern="yyyy-MM-dd HH:mm" />
        </p>
        <p><strong>Prix :</strong> ${voyage.prix} DT</p>
        <p><strong>Nombre de places restantes :</strong> ${voyage.nombrePlaces}</p>

        <hr/>

        <h3>Choisir un mode de paiement :</h3>

        <form method="post" action="${pageContext.request.contextPath}/PaiementController">
            <input type="hidden" name="action" value="validerPaiement" />
            <input type="hidden" name="voyageId" value="${voyage.id}" />

            <label>
                <input type="radio" name="methodePaiement" value="Carte bancaire" checked />
                Carte bancaire
            </label>

            <label>
                <input type="radio" name="methodePaiement" value="Espèces" />
                Espèces
            </label>

            <button type="submit">Valider et Payer</button>
        </form>
    </c:if>

    <c:if test="${empty voyage}">
        <p class="error">Aucun voyage sélectionné. Veuillez retourner à la recherche.</p>
    </c:if>
</div>
</body>
</html>
