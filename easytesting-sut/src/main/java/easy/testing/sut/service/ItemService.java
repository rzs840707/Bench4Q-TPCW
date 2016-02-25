package easy.testing.sut.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Item;
import easy.testing.sut.entity.OrderLine;
import easy.testing.sut.helper.SessionHelper;

@Component
public class ItemService {
	private SessionHelper sessionHelper;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	public Item getItemById(int itemId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.eq("id", itemId));
			Item item = (Item) criteria.uniqueResult();
			session.getTransaction().commit();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> getItemsBySubject(String subject, int count) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.eq("subject", subject));
			criteria.addOrder(Order.asc("title"));
			criteria.setMaxResults(count);
			List<Item> items = criteria.list();
			session.getTransaction().commit();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> getNewItemsBySubject(String subject, int count) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.eq("subject", subject));
			criteria.addOrder(Order.desc("publishDate"));
			criteria.setMaxResults(count);
			List<Item> items = criteria.list();
			session.getTransaction().commit();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> getBestSellers(String subject, int count) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			criteria.setProjection(Projections.max("id"));
			Integer orderId = (Integer) criteria.uniqueResult();
			if (orderId == null) {
				session.getTransaction().commit();
				return new ArrayList<Item>();
			}

			criteria = session.createCriteria(OrderLine.class);
			criteria.createAlias("item", "i");
			criteria.createAlias("order", "o");
			criteria.add(Restrictions.eq("i.subject", subject));
			criteria.add(Restrictions.ge("o.id", orderId - 3333));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("i.id"));
			projectionList.add(Projections.sum("quantity"), "sum");
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.desc("sum"));
			criteria.setMaxResults(count);
			List<Item> items = criteria.list();
			session.getTransaction().commit();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> getItemsByTitle(String title, int count) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.like("title", title + "%"));
			criteria.addOrder(Order.asc("title"));
			criteria.setMaxResults(count);
			List<Item> items = criteria.list();
			session.getTransaction().commit();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> getItemsByAuthor(String lastName, int count) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.createAlias("author", "a");
			criteria.add(Restrictions.like("a.lastName", lastName + "%"));
			criteria.addOrder(Order.asc("title"));
			criteria.setMaxResults(count);
			List<Item> items = criteria.list();
			session.getTransaction().commit();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public List<Item> getRelatedItems(int itemId) {
		Item item = this.getItemById(itemId);
		List<Item> relatedItems = new ArrayList<Item>();
		relatedItems.add(this.getItemById(item.getRelated1()));
		relatedItems.add(this.getItemById(item.getRelated2()));
		relatedItems.add(this.getItemById(item.getRelated3()));
		relatedItems.add(this.getItemById(item.getRelated4()));
		relatedItems.add(this.getItemById(item.getRelated5()));
		return relatedItems;
	}
}
