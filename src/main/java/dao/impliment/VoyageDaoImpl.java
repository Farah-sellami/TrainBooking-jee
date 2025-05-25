package dao.impliment;

import dao.interfaces.IVoyageDao;
import model.Trajet;
import model.Voyage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class VoyageDaoImpl implements IVoyageDao {

    @Override
    public void ajouter(Voyage voyage) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(voyage);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Voyage voyage) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(voyage);
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
            Voyage voyage = session.get(Voyage.class, id);
            if (voyage != null) {
                tx = session.beginTransaction();
                session.delete(voyage);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Voyage trouverParId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Voyage.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Voyage> listerTous() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Voyage> query = session.createQuery("from Voyage", Voyage.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Voyage> listerDisponibles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Voyage> query = session.createQuery("from Voyage where nombrePlaces > 0", Voyage.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Voyage> rechercherParGareEtDate(String villeDepart, String villeArrivee, LocalDate dateDepart) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDateTime debutJournee = dateDepart.atStartOfDay();
            LocalDateTime finJournee = dateDepart.atTime(LocalTime.MAX);

            String hql = "from Voyage v where v.trajet.villeDepart = :villeDepart " +
                         "and v.trajet.villeArrivee = :villeArrivee " +
                         "and v.dateDepart between :debutJournee and :finJournee";

            Query<Voyage> query = session.createQuery(hql, Voyage.class);
            query.setParameter("villeDepart", villeDepart);
            query.setParameter("villeArrivee", villeArrivee);
            query.setParameter("debutJournee", debutJournee);
            query.setParameter("finJournee", finJournee);

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Voyage> rechercherParTrajetEtDate(Long trajetId, String date) 
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Convertir la date string en LocalDate
            LocalDate localDate = LocalDate.parse(date); // "2025-05-21"
            // Définir le début et la fin de la journée
            LocalDateTime debutJournee = localDate.atStartOfDay();
            LocalDateTime finJournee = localDate.atTime(LocalTime.MAX);
            
            String hql = "from Voyage v where v.trajet.id = :trajetId " +
                         "and v.dateDepart between :debutJournee and :finJournee";
            Query<Voyage> query = session.createQuery(hql, Voyage.class);
            query.setParameter("trajetId", trajetId);
            query.setParameter("debutJournee", debutJournee);
            query.setParameter("finJournee", finJournee);
            
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Voyage> findByTrajet(Trajet trajet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Voyage v where v.trajet = :trajet";
            Query<Voyage> query = session.createQuery(hql, Voyage.class);
            query.setParameter("trajet", trajet);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
