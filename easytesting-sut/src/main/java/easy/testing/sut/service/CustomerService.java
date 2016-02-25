package easy.testing.sut.service;

import java.util.Date;

import org.bench4q.servlet.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Address;
import easy.testing.sut.entity.Customer;
import easy.testing.sut.helper.SessionHelper;

@Component
public class CustomerService {
	private SessionHelper sessionHelper;
	private AddressService addressService;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	private AddressService getAddressService() {
		return addressService;
	}

	@Autowired
	private void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public Customer getCustomerByUserName(String userName) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("userName", userName));
			Customer customer = (Customer) criteria.uniqueResult();
			session.getTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public Customer getCustomerById(int customerId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("id", customerId));
			Customer customer = (Customer) criteria.uniqueResult();
			session.getTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public boolean refreshSession(int customerId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("id", customerId));
			Customer customer = (Customer) criteria.uniqueResult();
			if (customer == null) {
				session.getTransaction().commit();
				return false;
			}
			customer.refreshSession();
			session.update(customer);
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

	public Customer newCustomer(String firstName, String lastName, String phone, String email, Date birthDate,
			String data, String streetLine1, String streetLine2, String city, String state, String zipCode,
			String countryName) {
		Customer customer = this.doCreateCustomer(firstName, lastName, phone, email, birthDate, data, streetLine1,
				streetLine2, city, state, zipCode, countryName);
		String userName = Util.DigSyl(customer.getId(), 0);
		String password = userName.toLowerCase();
		Address address = this.getAddressService().newAddress(streetLine1, streetLine2, city, state, zipCode,
				countryName);
		customer = this.updateInformation(customer.getId(), userName, password, address);
		return customer;
	}

	public Customer updateInformation(int customerId, String userName, String password, Address address) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("id", customerId));
			Customer customer = (Customer) criteria.uniqueResult();
			if (customer == null) {
				session.getTransaction().commit();
				return null;
			}
			customer.updateInformation(userName, password, address);
			session.update(customer);
			session.getTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public Customer doCreateCustomer(String firstName, String lastName, String phone, String email, Date birthDate,
			String data, String streetLine1, String streetLine2, String city, String state, String zipCode,
			String countryName) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();

			double discount = (int) (java.lang.Math.random() * 51);
			double balance = 0.0;
			double yearToDatePayment = 0.0;
			Date now = new Date(System.currentTimeMillis());
			Date lastVisitDate = now;
			Date registrationDate = now;
			Date sessionStart = now;
			Date sessionExpiration = new Date(now.getTime() + 2 * 60 * 60 * 1000);

			Address address = null;
			String userName = "";
			String password = "";

			Customer customer = Customer.create(userName, password, firstName, lastName, address, phone, email,
					registrationDate, lastVisitDate, sessionStart, sessionExpiration, discount, balance,
					yearToDatePayment, birthDate, data);
			session.save(customer);

			session.getTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

}
