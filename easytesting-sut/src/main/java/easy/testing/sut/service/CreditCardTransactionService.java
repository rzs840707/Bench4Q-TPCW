package easy.testing.sut.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Address;
import easy.testing.sut.entity.CreditCardTransaction;
import easy.testing.sut.entity.Order;
import easy.testing.sut.helper.SessionHelper;

@Component
public class CreditCardTransactionService {
	private SessionHelper sessionHelper;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	public CreditCardTransaction createCreditCardTransaction(int orderId, String type, long number, String name,
			Date expirationDate, double amount, int addressId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();

			Order order = (Order) session.createCriteria(Order.class).add(Restrictions.eq("id", orderId))
					.uniqueResult();
			if (order == null) {
				return null;
			}

			Address address = (Address) session.createCriteria(Address.class).add(Restrictions.eq("id", addressId))
					.uniqueResult();
			if (address == null) {
				return null;
			}

			// TODO: Authorization ID
			CreditCardTransaction creditCardTransaction = CreditCardTransaction.create(order, type,
					String.valueOf(number), name, expirationDate, null, amount, new Date(System.currentTimeMillis()),
					address.getCountry());

			session.save(creditCardTransaction);
			session.getTransaction().commit();
			return creditCardTransaction;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

}
