package easy.testing.sut.service;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Item;
import easy.testing.sut.entity.ShoppingCart;
import easy.testing.sut.entity.ShoppingCartLine;
import easy.testing.sut.helper.SessionHelper;

@Component
public class ShoppingCartService {
	private SessionHelper sessionHelper;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	public ShoppingCart newShoppingCart() {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			ShoppingCart shoppingCart = ShoppingCart.create();
			session.save(shoppingCart);
			session.getTransaction().commit();
			return shoppingCart;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public boolean resetShoppingCartTime(int shoppingCartId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(ShoppingCart.class);
			criteria.add(Restrictions.eq("id", shoppingCartId));
			ShoppingCart shoppingCart = (ShoppingCart) criteria.uniqueResult();
			if (shoppingCart == null) {
				session.getTransaction().commit();
				return false;
			}
			shoppingCart.resetTime();
			session.update(shoppingCart);
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

	public boolean addItemToShoppingCart(int shoppingCartId, int itemId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			ShoppingCart shoppingCart = (ShoppingCart) session.createCriteria(ShoppingCart.class)
					.add(Restrictions.eq("id", shoppingCartId)).uniqueResult();
			if (shoppingCart == null) {
				session.getTransaction().commit();
				return true;
			}
			Item item = (Item) session.createCriteria(Item.class).add(Restrictions.eq("id", itemId)).uniqueResult();
			if (item == null) {
				session.getTransaction().commit();
				return true;
			}

			Criteria criteria = session.createCriteria(ShoppingCartLine.class);
			criteria.createAlias("shoppingCart", "shoppingCart");
			criteria.createAlias("item", "item");
			criteria.add(Restrictions.eq("shoppingCart.id", shoppingCart.getId()));
			criteria.add(Restrictions.eq("item.id", item.getId()));
			ShoppingCartLine shoppingCartLine = (ShoppingCartLine) criteria.uniqueResult();
			if (shoppingCartLine == null) {
				shoppingCartLine = ShoppingCartLine.create(shoppingCart, item, 1);
				session.save(shoppingCartLine);
				session.getTransaction().commit();
				return true;
			} else {
				shoppingCartLine.updateQuantity(shoppingCartLine.getQuantity() + 1);
				session.update(shoppingCartLine);
				session.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean refreshShoppingCart(int shoppingCartId, Map<Integer, Integer> itemQuantityMap) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			ShoppingCart shoppingCart = (ShoppingCart) session.createCriteria(ShoppingCart.class)
					.add(Restrictions.eq("id", shoppingCartId)).uniqueResult();
			if (shoppingCart == null) {
				session.getTransaction().commit();
				return false;
			}

			for (int key : itemQuantityMap.keySet()) {
				Item item = (Item) session.createCriteria(Item.class).add(Restrictions.eq("id", key)).uniqueResult();
				if (item == null) {
					session.getTransaction().commit();
					return false;
				}
				int newQuantity = itemQuantityMap.get(key);

				Criteria criteria = session.createCriteria(ShoppingCartLine.class);
				criteria.createAlias("shoppingCart", "shoppingCart");
				criteria.createAlias("item", "item");
				criteria.add(Restrictions.eq("shoppingCart.id", shoppingCart.getId()));
				criteria.add(Restrictions.eq("item.id", item.getId()));
				ShoppingCartLine shoppingCartLine = (ShoppingCartLine) criteria.uniqueResult();
				if (shoppingCartLine == null) {
					session.getTransaction().commit();
					return false;
				} else {
					if (newQuantity == 0) {
						session.delete(shoppingCartLine);
					} else {
						shoppingCartLine.updateQuantity(newQuantity);
						session.update(shoppingCartLine);
					}
					session.getTransaction().commit();
					return true;
				}
			}
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
