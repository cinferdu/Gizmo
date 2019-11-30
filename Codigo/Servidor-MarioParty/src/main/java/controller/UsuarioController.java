package controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.Usuario;
import util.HibernateUtil;
import util.UtilesLog;

public class UsuarioController {
	
	public static Long save(Usuario usuario) {
		usuario.setPass(DigestUtils.md5Hex(usuario.getPass()));
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Long save = null;
		try {
			save = (Long) session.save(usuario);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			UtilesLog.loggerStackTrace(e, UsuarioController.class);
		} finally {
			session.close();
		}
		return save;
	}

	public static Usuario get(String userName) {
		Session session = HibernateUtil.getSession();
		Usuario usuario = null;
		try {
			usuario = get(session, userName);
		} catch (HibernateException e) {
			UtilesLog.loggerStackTrace(e, UsuarioController.class);
		} finally {
			session.close();
		}
		
		return usuario;
	}
	
	public static Usuario get(Session session, String userName) throws HibernateException {
		Usuario user = null;
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.like("user", userName));
		user = (Usuario) criteria.uniqueResult();
		return user;
	}

	public static boolean loggin(Usuario user) {
		Usuario usuario = get(user.getUser());
		if (usuario == null)
			return false;
		return usuario.getPass().equals(DigestUtils.md5Hex(user.getPass()));
	}
}
