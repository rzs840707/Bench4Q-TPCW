package easy.testing.sut.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Order;
import easy.testing.sut.entity.OrderLine;
import easy.testing.sut.helper.SessionHelper;

@Component
public class OrderService {
	private SessionHelper sessionHelper;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	public Order getMostRecentOrder(String customerUserName) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			criteria.createAlias("customer", "customer");
			criteria.add(Restrictions.eq("customer.userName", customerUserName));
			criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
			criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
			criteria.setMaxResults(1);
			Order order = (Order) criteria.uniqueResult();
			session.getTransaction().commit();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<OrderLine> getOrderLines(int orderId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(OrderLine.class);
			criteria.createAlias("order", "order");
			criteria.add(Restrictions.eq("order.id", orderId));
			List<OrderLine> orderLines = criteria.list();
			session.getTransaction().commit();
			return orderLines;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}
}
