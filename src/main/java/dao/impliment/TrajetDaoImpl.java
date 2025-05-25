package dao.impliment;

import dao.interfaces.ITrajetDao;
import model.Trajet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class TrajetDaoImpl implements ITrajetDao {

    @Override
    public void ajouter(Trajet trajet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(trajet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Trajet trajet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(trajet);
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
            tx = session.beginTransaction();
            Trajet trajet = session.get(Trajet.class, id);
            if (trajet != null) {
                session.delete(trajet);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Trajet trouverParId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Trajet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Trajet> listerTous() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Trajet> query = session.createQuery("from Trajet", Trajet.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Trajet> rechercher(String villeDepart, String villeArrivee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Trajet t where t.gareDepart.ville = :villeDepart and t.gareArrivee.ville = :villeArrivee";
            Query<Trajet> query = session.createQuery(hql, Trajet.class);
            query.setParameter("villeDepart", villeDepart);
            query.setParameter("villeArrivee", villeArrivee);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // plus propre que null
        }
    }


}
