<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Paiement Réussi</title></head>
<body>
    <h2>Merci pour votre achat !</h2>
    <p>Montant payé : ${paiement.montant} DT</p>
    <p>Mode de paiement : ${paiement.methode}</p>
    <p>Date : ${paiement.datePaiement}</p>
    <a href="BilletController?action=list">Voir mes billets</a>
</body>
</html>
