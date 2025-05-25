<html>
<head>
  <title>Connexion</title>
  <style>
    body {
  font-family: Arial, sans-serif;
  background: #f0f2f5;
  display: flex;
  flex-direction: column; /* empile verticalement */
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  gap: 20px; /* espace entre h1 et container */
}

h1 {
  color: #333;
  font-weight: 700;
  font-size: 2.5rem;
  margin: 0;
}

    .container {
      background: white;
      padding: 30px 40px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
      width: 320px;
    }
    h2 {
      text-align: center;
      margin-bottom: 25px;
      color: #333;
    }
    label {
      font-weight: bold;
      display: block;
      margin-top: 15px;
      margin-bottom: 6px;
      color: #555;
    }
    input[type="email"], input[type="password"] {
      width: 100%;
      padding: 10px;
      border-radius: 4px;
      border: 1px solid #ccc;
      box-sizing: border-box;
      font-size: 14px;
      transition: border-color 0.3s;
    }
    input[type="email"]:focus, input[type="password"]:focus {
      border-color: #4a90e2;
      outline: none;
    }
    button {
      margin-top: 25px;
      width: 100%;
      padding: 12px;
      border: none;
      border-radius: 5px;
      background-color: #4a90e2;
      color: white;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    button:hover {
      background-color: #357ABD;
    }
    p {
      margin-top: 20px;
      text-align: center;
      font-size: 14px;
      color: #666;
    }
    p a {
      color: #4a90e2;
      text-decoration: none;
      font-weight: 600;
    }
    p a:hover {
      text-decoration: underline;
    }
    .error {
      color: red;
      text-align: center;
      margin-bottom: 15px;
      font-weight: 600;
    }
  </style>
</head>
<body>
<h1>TrainBooking</h1>
  <div class="container">
  
    <h2>Connexion</h2>

    <c:if test="${not empty erreur}">
      <p class="error">${erreur}</p>
    </c:if>

    <form action="AuthController" method="post">
      <input type="hidden" name="action" value="login" />
      <label>Email :</label>
      <input type="email" name="email" required/>
      <label>Mot de passe :</label>
      <input type="password" name="motDePasse" required/>
      <button type="submit">Se connecter</button>
    </form>

    <p>Pas encore inscrit ? <a href="AuthController?action=register">Créer un compte</a></p>
  </div>
</body>
</html>
