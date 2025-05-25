<!-- /WEB-INF/jsp/includes/header.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>TrainBooking</title>
    <link rel="stylesheet"/>
    
</head>
<body>
<head>
    <title>TrainBooking</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #fafafa;
            color: #222;
        }

        header {
            padding: 1.5rem 0;
            text-align: center;
            font-weight: 600;
            font-size: 2rem;
            color: #222;
            border-bottom: 1px solid #e0e0e0;
            letter-spacing: 0.05em;
        }

        nav {
            display: flex;
            align-items: center;
            justify-content: space-between;
            background: #fff;
            border-bottom: 1px solid #e0e0e0;
            padding: 0.75rem 2rem;
            user-select: none;
            gap: 1rem;
        }

        /* Titre tout à gauche */
        .nav-brand {
            font-weight: 700;
            font-size: 1.25rem;
            color: #0078d4;
            white-space: nowrap;
        }

        /* Conteneur central des liens */
        .nav-links {
            display: flex;
            gap: 2rem;
            flex-grow: 1;
            justify-content: center;
        }

        nav a {
            text-decoration: none;
            color: #555;
            font-weight: 500;
            font-size: 1rem;
            padding-bottom: 4px;
            border-bottom: 2px solid transparent;
            transition: color 0.25s ease, border-bottom-color 0.25s ease;
            white-space: nowrap;
        }

        nav a:hover,
        nav a:focus {
            color: #0078d4;
            border-bottom-color: #0078d4;
            outline: none;
        }

        /* Bouton déconnexion tout à droite */
        .nav-logout {
            font-weight: 600;
            color: #d9534f; /* rouge léger */
            cursor: pointer;
            white-space: nowrap;
            border-bottom: 2px solid transparent;
            transition: color 0.25s ease, border-bottom-color 0.25s ease;
        }
        .nav-logout:hover,
        .nav-logout:focus {
            color: #c9302c;
            border-bottom-color: #c9302c;
            outline: none;
        }

        hr {
            border: none;
            height: 1px;
            background: #e0e0e0;
            margin: 0 0 2rem 0;
        }

        @media (max-width: 600px) {
            nav {
                flex-wrap: wrap;
                padding: 0.75rem 1rem;
            }
            .nav-links {
                justify-content: center;
                gap: 1rem;
                flex-basis: 100%;
                order: 2;
                margin-top: 0.5rem;
            }
            .nav-logout {
                order: 3;
            }
            .nav-brand {
                order: 1;
            }
        }
    </style>
</head>
<body>

<nav>
    <div class="nav-brand">TrainBooking</div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/TrajetController?action=list">Trajets</a>
        <a href="${pageContext.request.contextPath}/GareController?action=list">Gares</a>
        <a href="${pageContext.request.contextPath}/VoyageController?action=list">Voyages</a>
        <a href="${pageContext.request.contextPath}/UtilisateurController?action=list">Utilisateurs</a>
    </div>
    <a href="${pageContext.request.contextPath}/AuthController?action=logout" class="nav-logout">Déconnexion</a>
</nav>
<header>
    <h2>Bienvenue sur TrainBooking</h2>
</header>
<hr/>
