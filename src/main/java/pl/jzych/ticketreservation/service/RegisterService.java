package pl.jzych.ticketreservation.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.jzych.ticketreservation.model.User;
import pl.jzych.ticketreservation.util.HibernateUtil;


public class RegisterService {

	public long save(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		long id = (long) session.save(user);
		transaction.commit();
		session.close();
		return id;
	}
}
