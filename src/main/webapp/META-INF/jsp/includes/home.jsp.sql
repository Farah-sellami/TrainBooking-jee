<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Accueil - TrainBooking</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: 'Inter', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fff;
            color: #1a1a1a;
        }

        .section {
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
    </style>
</head>
<body>

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

<footer>
    <div class="logo">
        <i class="fas fa-train"></i>
        TrainBooking
    </div>
    <p>© 2024 TrainBooking. Tous droits réservés.</p>
</footer>

</body>
</html>
