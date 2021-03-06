package easy.testing.sut.helper;

import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Address;
import easy.testing.sut.entity.Author;
import easy.testing.sut.entity.Country;
import easy.testing.sut.entity.CreditCardTransaction;
import easy.testing.sut.entity.Customer;
import easy.testing.sut.entity.Item;
import easy.testing.sut.entity.Order;
import easy.testing.sut.entity.OrderLine;
import easy.testing.sut.entity.ShoppingCart;
import easy.testing.sut.entity.ShoppingCartLine;

@Component
public final class SessionHelper {
	private SessionFactory sessionFactory;

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionHelper() {
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("/database.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Configuration configuration = new Configuration();
			configuration.setProperties(properties);

			configuration.addAnnotatedClass(Address.class);
			configuration.addAnnotatedClass(Author.class);
			configuration.addAnnotatedClass(Country.class);
			configuration.addAnnotatedClass(CreditCardTransaction.class);
			configuration.addAnnotatedClass(Customer.class);
			configuration.addAnnotatedClass(Item.class);
			configuration.addAnnotatedClass(Order.class);
			configuration.addAnnotatedClass(OrderLine.class);
			configuration.addAnnotatedClass(ShoppingCart.class);
			configuration.addAnnotatedClass(ShoppingCartLine.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			this.setSessionFactory(configuration.buildSessionFactory(serviceRegistry));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return this.getSessionFactory().getCurrentSession();
	}
}
