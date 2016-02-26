package easy.testing.sut.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
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

	@SuppressWarnings({ "unchecked" })
	public List<Item> getBestSellers(String subject, int count) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(easy.testing.sut.entity.Order.class);
			criteria.setProjection(Projections.max("id"));
			Integer orderId = (Integer) criteria.uniqueResult();
			if (orderId == null) {
				session.getTransaction().commit();
				return new ArrayList<Item>();
			}

			criteria = session.createCriteria(OrderLine.class);
			criteria.createAlias("item", "item");
			criteria.createAlias("order", "order");
			criteria.add(Restrictions.eq("item.subject", subject));
			criteria.add(Restrictions.ge("order.id", orderId - 3333));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("item.id"));
			projectionList.add(Projections.sum("quantity"), "sum");
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.desc("sum"));
			criteria.setMaxResults(count);
			List<Object[]> results = criteria.list();
			List<Item> items = new ArrayList<Item>();
			for (Object[] orderLine : results) {
				items.add(session.get(Item.class, (Integer) orderLine[0]));
			}
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

	public boolean updateInformation(int itemId, double newCost, String newImage, String newThumbnail) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.eq("id", itemId));
			Item item = (Item) criteria.uniqueResult();
			if (item == null) {
				session.getTransaction().commit();
				return false;
			}
			item.updateInformation(newCost, newImage, newThumbnail);
			session.update(item);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateRelatedItems(int itemId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Item item = (Item) session.createCriteria(Item.class).add(Restrictions.eq("id", itemId)).uniqueResult();
			if (item == null) {
				session.getTransaction().commit();
				return false;
			}

			Integer maxOrderId = (Integer) session.createCriteria(easy.testing.sut.entity.Order.class)
					.setProjection(Projections.max("id")).uniqueResult();
			
			Integer maxItemId = (Integer) session.createCriteria(Item.class).setProjection(Projections.max("id"))
					.uniqueResult();
			Integer minItemId = (Integer) session.createCriteria(Item.class).setProjection(Projections.min("id"))
					.uniqueResult();
			
			DetachedCriteria subQuery = DetachedCriteria.forClass(OrderLine.class);
			subQuery.createAlias("order", "order");
			subQuery.createAlias("item", "item");
			subQuery.add(Restrictions.ge("order.id", (maxOrderId == null ? 0 : maxOrderId.intValue()) - 10000));
			subQuery.add(Restrictions.eq("item.id", itemId));
			subQuery.setProjection(Projections.property("order.customer.id"));
			Criteria query = session.createCriteria(OrderLine.class);
			query.createAlias("item", "item");
			query.createAlias("order", "order");
			query.add(Restrictions.ne("item.id", itemId));
			query.add(Property.forName("order.customer.id").in(subQuery));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("item.id"));
			projectionList.add(Projections.sum("quantity"), "sum");
			query.setProjection(projectionList);
			query.addOrder(Order.desc("sum"));
			query.setMaxResults(5);
			List<Object[]> results = query.list();
			int[] itemIds = new int[5];
			if (results.size() == 0) {
				int currentItemId = itemId;
				int i = 0;
				for (i = 0; i < 5; i++) {
					currentItemId += 7;
					if (currentItemId > maxItemId) {
						currentItemId = minItemId;
					}
					itemIds[i] = currentItemId;
				}

			}
			if (results.size() < 5) {
				int i = 0;
				for (Object[] result : results) {
					i++;
					itemIds[i] = (Integer) result[0];
				}
				while (i < 5) {
					itemIds[i] = itemIds[i - 1] + 1;
				}
			}
			if (results.size() == 5) {
				int i = 0;
				for (i = 0; i < 5; i++) {
					itemIds[i] = (Integer) results.get(i)[0];
				}
			}
			item.updateRelatedItems(itemIds[0], itemIds[1], itemIds[2], itemIds[3], itemIds[4]);
			session.update(item);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return false;
		}
	}
}
