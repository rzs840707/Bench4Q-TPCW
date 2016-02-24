package easy.testing.sut.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Address;
import easy.testing.sut.entity.Country;
import easy.testing.sut.helper.SessionHelper;

@Component
public class AddressService {
	private SessionHelper sessionHelper;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	public Address newAddress(String streetLine1, String streetLine2, String city, String state, String zipCode,
			String countryName) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("streetLine1", streetLine1));
			criteria.add(Restrictions.eq("streetLine2", streetLine2));
			criteria.add(Restrictions.eq("city", city));
			criteria.add(Restrictions.eq("state", state));
			criteria.add(Restrictions.eq("zipCode", zipCode));
			criteria.createAlias("country", "c");
			criteria.add(Restrictions.eq("c.name", countryName));
			Address address = (Address) criteria.uniqueResult();
			if (address != null) {
				session.getTransaction().commit();
				return address;
			}

			Country country = (Country) session.createCriteria(Country.class).add(Restrictions.eq("name", countryName))
					.uniqueResult();
			address = Address.create(streetLine1, streetLine2, city, state, zipCode, country);
			session.save(address);
			session.getTransaction().commit();
			return address;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}
}
