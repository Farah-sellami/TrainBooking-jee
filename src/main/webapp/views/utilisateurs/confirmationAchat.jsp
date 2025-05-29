<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmation d'achat</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f9;
            margin: 0; padding: 0;
        }
        .container {
            max-width: 600px;
            background-color: white;
            margin: 50px auto;
            padding: 30px 40px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
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
            gap: 12px;
        }
        label, select {
            font-size: 15px;
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
        .prix-affiche {
            font-weight: bold;
            font-size: 18px;
            color: #007bff;
        }
    </style>
    <script>
        // Met à jour le prix affiché en fonction de la classe choisie
        function updatePrix(basePrix) {
            const classeSelect = document.getElementById("classe");
            const prixElement = document.getElementById("prixAffiche");
            const selectedClasse = classeSelect.value;

            let finalPrix = basePrix;
            if (selectedClasse === "Première") {
                finalPrix = basePrix * 1.20; // +20%
            }
            prixElement.innerText = finalPrix.toFixed(2) + " DT";
        }
    </script>
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

        <p><strong>Nombre de places restantes :</strong> ${voyage.nombrePlaces}</p>

        <p><strong>Prix :</strong> 
            <span id="prixAffiche" class="prix-affiche">${voyage.prix} DT</span>
        </p>

        <hr/>

        <h3>Choisir vos options :</h3>

        <!-- Formulaire POST classique vers PaiementController -->
        <form id="paiementForm" method="post" action="${pageContext.request.contextPath}/PaiementController">
            <input type="hidden" name="action" value="validerPaiement" />
            <input type="hidden" name="voyageId" value="${voyage.id}" />

            <label for="classe">Classe :</label>
            <select name="classe" id="classe" onchange="updatePrix(${voyage.prix})">
                <option value="Économique">Économique</option>
                <option value="Deuxième">Deuxième</option>
                <option value="Première">Première (+20%)</option>
            </select>

            <label for="preference">Préférence :</label>
            <select name="preference" id="preference">
                <option value="Fenêtre">Place côté fenêtre</option>
                <option value="Famille">Espace famille</option>
                <option value="Non-fumeur">Wagon non-fumeur</option>
            </select>

            <h3>Mode de paiement :</h3>
            <label><input type="radio" name="methodePaiement" value="Carte bancaire" checked /> Carte bancaire</label>
            <label><input type="radio" name="methodePaiement" value="Espèces" /> Espèces</label>

            <button type="submit">Valider et Payer</button>
        </form>
    </c:if>

    <c:if test="${empty voyage}">
        <p class="error">Aucun voyage sélectionné. Veuillez retourner à la recherche.</p>
    </c:if>
</div>
</body>
</html>
