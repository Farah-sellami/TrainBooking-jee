package dao.impliment;

import dao.interfaces.IPaiementDao;
import model.Paiement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class PaiementDaoImpl implements IPaiementDao {

    @Override
    public void ajouter(Paiement paiement) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(paiement);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Paiement trouverParId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Paiement.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Paiement> listerTous() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Paiement> query = session.createQuery("from Paiement", Paiement.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
