package dao.impliment;

import dao.interfaces.IAnnulationDao;
import model.Billet;
import model.DemandeAnnulation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AnnulationDaoImpl implements IAnnulationDao {

    @Override
    public void ajouter(DemandeAnnulation demande) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(demande);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(DemandeAnnulation demande) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(demande);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DemandeAnnulation demande = session.get(DemandeAnnulation.class, id);
            if (demande != null) {
                tx = session.beginTransaction();
                session.delete(demande);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public DemandeAnnulation trouverParId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(DemandeAnnulation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DemandeAnnulation> listerToutes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DemandeAnnulation> query = session.createQuery("from DemandeAnnulation", DemandeAnnulation.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DemandeAnnulation> listerParUtilisateur(Long utilisateurId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DemandeAnnulation> query = session.createQuery(
                "from DemandeAnnulation where utilisateur.id = :id", DemandeAnnulation.class);
            query.setParameter("id", utilisateurId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<DemandeAnnulation> listerEnAttente() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DemandeAnnulation> query = session.createQuery(
                "from DemandeAnnulation where statut = :statut", DemandeAnnulation.class);
            query.setParameter("statut", "EN_ATTENTE");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void valider(Long demandeId, boolean accepter) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            DemandeAnnulation demande = session.get(DemandeAnnulation.class, demandeId);
            if (demande != null && "EN_ATTENTE".equals(demande.getStatut())) {
                if (accepter) {
                    demande.setStatut("ACCEPTEE");

                    // Annuler le billet en mettant estValide à false
                    Billet billet = demande.getBillet();
                    if (billet != null) {
                        billet.setEstValide(false);
                        session.update(billet);
                    }
                } else {
                    demande.setStatut("REFUSEE");
                }
                session.update(demande);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }


}
