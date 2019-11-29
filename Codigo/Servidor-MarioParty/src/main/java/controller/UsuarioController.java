package controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mensaje.Mensaje;
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
		Usuario user = null;
		try {
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.like("user", userName));
			return (Usuario) criteria.uniqueResult();
		} catch (HibernateException e) {
			UtilesLog.loggerStackTrace(e, UsuarioController.class);
		} finally {
			session.close();
		}
		return user;
	}

	public static boolean loggin(Usuario user) {
		Usuario usuario = get(user.getUser());
		if (usuario == null)
			return false;
		return usuario.getPass().equals(DigestUtils.md5Hex(user.getPass()));
	}
}
