<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- ✅ Modal Succès -->
<c:if test="${paiementStatus == 'success'}">
    <div id="modal-success" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal('modal-success')">&times;</span>
            <h2>Paiement réussi !</h2>
            <p>Montant payé : <strong>${paiement.montant} DT</strong></p>
            <p>Numéro de billet : <strong>${paiement.billet.numeroBillet}</strong></p>
        </div>
    </div>
</c:if>

<!-- ❌ Modal Erreur -->
<c:if test="${paiementStatus == 'error'}">
    <div id="modal-error" class="modal">
        <div class="modal-content error">
            <span class="close" onclick="closeModal('modal-error')">&times;</span>
            <h2>Échec du paiement</h2>
            <p>${messageErreur}</p>
        </div>
    </div>
</c:if>

<!-- 💄 CSS et JS améliorés -->
<style>
  /* Fond semi-transparent couvrant tout l'écran */
  .modal {
    display: flex; /* centre horizontal + vertical */
    align-items: center;
    justify-content: center;
    position: fixed;
    z-index: 9999;
    left: 0; top: 0;
    width: 100%; height: 100%;
    background-color: rgba(0,0,0,0.5);
    animation: fadeIn 0.3s ease forwards;
  }

  /* Animation fade in */
  @keyframes fadeIn {
    from {opacity: 0;}
    to {opacity: 1;}
  }

  /* Boîte du contenu modal */
  .modal-content {
    background: #fff;
    padding: 30px 25px;
    border-radius: 10px;
    width: 400px;
    max-width: 90%;
    box-shadow: 0 5px 15px rgba(0,0,0,0.3);
    position: relative;
    text-align: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }

  /* Bordure rouge pour erreur */
  .modal-content.error {
    border-left: 8px solid #e74c3c;
  }

  /* Croix de fermeture */
  .close {
    position: absolute;
    right: 15px;
    top: 12px;
    font-size: 28px;
    font-weight: bold;
    color: #333;
    cursor: pointer;
    transition: color 0.2s ease;
  }
  .close:hover {
    color: #e74c3c;
  }

  /* Titres */
  h2 {
    margin-bottom: 15px;
    color: #2ecc71; /* vert succès */
  }
  .modal-content.error h2 {
    color: #e74c3c; /* rouge erreur */
  }

  /* Paragraphes */
  p {
    font-size: 1.1rem;
    margin: 8px 0;
    color: #555;
  }
</style>

<script>
  function closeModal(id) {
    const modal = document.getElementById(id);
    if (!modal) return;
    // Disparition progressive
    modal.style.animation = 'fadeOut 0.3s forwards';
    setTimeout(() => {
      modal.style.display = 'none';
      // Si c'est la modal succès, redirection vers l'accueil
      if(id === 'modal-success') {
        window.location.href = 'http://localhost:8081/Maven-projet/views/utilisateurs/accueil.jsp';
      }
    }, 300);
  }

  // Animation fadeOut (inverse du fadeIn)
  const styleSheet = document.createElement('style');
  styleSheet.type = 'text/css';
  styleSheet.innerText = `
    @keyframes fadeOut {
      from {opacity: 1;}
      to {opacity: 0;}
    }
  `;
  document.head.appendChild(styleSheet);
</script>
