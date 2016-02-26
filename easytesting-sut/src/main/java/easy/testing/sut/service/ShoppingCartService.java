package easy.testing.sut.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.ShoppingCart;
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

}
