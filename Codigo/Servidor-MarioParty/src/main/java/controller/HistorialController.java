package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.Historial;
import util.HibernateUtil;
import util.UtilesLog;

public class HistorialController {
	
	public static Long save(Historial historial) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Long save = null;
		historial.setUser(UsuarioController.get(session, historial.getUser().getUser()));//traigo al user perteneciente en la db
		try {
			save = (Long) session.save(historial);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			UtilesLog.loggerStackTrace(e, HistorialController.class);
		} finally {
			session.close();
		}
		return save;
	}

	@SuppressWarnings("unchecked")
	public static List<Historial> get(String userName) {
		Session session = HibernateUtil.getSession();
		List<Historial> list = new ArrayList<Historial>();
		try {
			Criteria criteria = session.createCriteria(Historial.class,"historial");
			criteria.createAlias("historial.user", "user"); // inner join by default
			criteria.add(Restrictions.like("user.user", userName));
			list = criteria.list();
		} catch (HibernateException e) {
			UtilesLog.loggerStackTrace(e, HistorialController.class);
		} finally {
			session.close();
		}
		return list;
	}

}
