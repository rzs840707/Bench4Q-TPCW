package easy.testing.sut.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Item;
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
