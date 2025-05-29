package dao.impliment;

import dao.interfaces.IBilletDao;
import jakarta.jms.Connection;
import model.Billet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BilletDaoImpl implements IBilletDao {

    @Override
    public void ajouter(Billet billet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(billet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Billet billet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(billet);
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
            Billet billet = session.get(Billet.class, id);
            if (billet != null) {
                tx = session.beginTransaction();
                session.delete(billet);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Billet trouverParId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Billet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Billet> listerParUtilisateur(Long utilisateurId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Billet> query = session.createQuery(
                "from Billet where utilisateur.id = :id", Billet.class);
            query.setParameter("id", utilisateurId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Billet> listerTous() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Billet> query = session.createQuery("from Billet", Billet.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Billet> findByUtilisateurId(Long utilisateurId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Billet> query = session.createQuery(
                "from Billet b where b.utilisateur.id = :utilisateurId", Billet.class
            );
            query.setParameter("utilisateurId", utilisateurId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
