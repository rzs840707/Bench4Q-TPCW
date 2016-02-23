package easy.testing.sut.helper;

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
			Configuration configuration = new Configuration();

			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/bench4q");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "onceas");
			configuration.setProperty("hibernate.connection.pool_size", "1");
			configuration.setProperty("hibernate.show_sql", "false");
			configuration.setProperty("hibernate.format_sql", "true");
			configuration.setProperty("connection.useUnicode", "true");
			configuration.setProperty("hibernate.connection.characterEncoding", "utf-8");
			configuration.setProperty("hibernate.current_session_context_class", "thread");

			configuration.addAnnotatedClass(Address.class);
			configuration.addAnnotatedClass(Author.class);
			configuration.addAnnotatedClass(Country.class);
			configuration.addAnnotatedClass(CreditCardTransaction.class);
			configuration.addAnnotatedClass(Customer.class);
			configuration.addAnnotatedClass(Item.class);
			configuration.addAnnotatedClass(Order.class);
			configuration.addAnnotatedClass(OrderLine.class);

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
