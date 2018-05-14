package pl.jzych.ticketreservation.service;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.jzych.ticketreservation.model.Rides;
import pl.jzych.ticketreservation.model.RidesFilter;
import pl.jzych.ticketreservation.util.HibernateUtil;

public class RideService {

	public List<String> fromCity() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();

		Query query = session.createQuery("SELECT distinct r.fromcity FROM Rides r");
		List<String> fromList = query.list();
		trx.commit();
		session.close();
		return fromList;
	}

	public List<String> toCity() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();

		Query query = session.createQuery("SELECT distinct r.tocity FROM Rides r");
		List<String> toList = query.list();
		trx.commit();
		session.close();
		return toList;
	}

	public List<Rides> filter(RidesFilter filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Rides.class);

		if (Objects.nonNull(filter.getFromcity()) && !filter.getFromcity().isEmpty()) {
			criteria.add(Restrictions.eq("fromcity", filter.getFromcity()));
		}
		if (Objects.nonNull(filter.getTocity()) && !filter.getTocity().isEmpty()) {
			criteria.add(Restrictions.eq("tocity", filter.getTocity()));
		}
		if (Objects.nonNull(filter.getDate_r()) && !filter.getDate_r().isEmpty()) {
			criteria.add(Restrictions.eq("date_r", filter.getDate_r()));
		}
		List<Rides> rides = criteria.list();
		session.close();
		return rides;
	}


}


