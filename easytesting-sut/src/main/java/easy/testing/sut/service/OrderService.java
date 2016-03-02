package easy.testing.sut.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bench4q.servlet.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Address;
import easy.testing.sut.entity.Customer;
import easy.testing.sut.entity.Item;
import easy.testing.sut.entity.Order;
import easy.testing.sut.entity.OrderLine;
import easy.testing.sut.entity.ShoppingCartLine;
import easy.testing.sut.entity.ShoppingCartList;
import easy.testing.sut.helper.SessionHelper;

@Component
public class OrderService {
	private SessionHelper sessionHelper;
	private ItemService itemService;

	private SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	@Autowired
	private void setSessionHelper(SessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}

	private ItemService getItemService() {
		return itemService;
	}

	@Autowired
	private void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public Order getMostRecentOrder(String customerUserName) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			criteria.createAlias("customer", "customer");
			criteria.add(Restrictions.eq("customer.userName", customerUserName));
			criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
			criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
			criteria.setMaxResults(1);
			Order order = (Order) criteria.uniqueResult();
			session.getTransaction().commit();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<OrderLine> getOrderLines(int orderId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(OrderLine.class);
			criteria.createAlias("order", "order");
			criteria.add(Restrictions.eq("order.id", orderId));
			List<OrderLine> orderLines = criteria.list();
			session.getTransaction().commit();
			return orderLines;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public int addOrder(int customerId, ShoppingCartList shoppingCartList, int shipAddressId, String shipType,
			double discount) {
		Order order = this.doAddOrder(customerId, shoppingCartList.getSubTotal(), shoppingCartList.getTotalCost(),
				shipType, shipAddressId);
		int orderLineId = 0;
		for (ShoppingCartLine line : shoppingCartList.getShoppingCartLines()) {
			this.doAddOrderLine(orderLineId, order.getId(), line.getItem().getId(), line.getQuantity(), discount,
					Util.getRandomString(20, 100));
			orderLineId++;
			int stock = line.getItem().getStock();
			if ((stock - line.getQuantity()) < 10) {
				this.getItemService().updateStock(line.getItem().getId(), stock - line.getQuantity() + 21);
			} else {
				this.getItemService().updateStock(line.getItem().getId(), stock - line.getQuantity());
			}
		}
		return order.getId();
	}

	public Order doAddOrder(int customerId, double subTotal, double total, String shipType, int shipAddressId) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();

			Customer customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("id", customerId))
					.uniqueResult();
			if (customer == null) {
				session.getTransaction().commit();
				return null;
			}
			Address shipAddress = (Address) session.createCriteria(Address.class)
					.add(Restrictions.eq("id", shipAddressId)).uniqueResult();
			if (shipAddress == null) {
				session.getTransaction().commit();
				return null;
			}
			Date orderDate = new Date(System.currentTimeMillis());
			double tax = 8.25;
			Calendar shipCalendar = Calendar.getInstance();
			shipCalendar.setTime(new Date(System.currentTimeMillis()));
			shipCalendar.add(Calendar.DAY_OF_YEAR, Util.getRandom(7));
			Date shipDate = shipCalendar.getTime();
			Address billAddress = customer.getAddress();
			String status = "Pending";

			Order order = Order.create(customer, orderDate, subTotal, tax, total, shipType, shipDate, billAddress,
					shipAddress, status);

			session.save(order);
			session.getTransaction().commit();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}

	public OrderLine doAddOrderLine(int orderLineId, int orderId, int itemId, int quantity, double discount,
			String comments) {
		Session session = null;
		try {
			session = this.getSessionHelper().getSession();
			session.beginTransaction();

			Order order = (Order) session.createCriteria(Order.class).add(Restrictions.eq("id", orderId))
					.uniqueResult();
			if (order == null) {
				session.getTransaction().commit();
				return null;
			}

			Item item = (Item) session.createCriteria(Item.class).add(Restrictions.eq("id", itemId)).uniqueResult();
			if (item == null) {
				session.getTransaction().commit();
				return null;
			}

			OrderLine orderLine = OrderLine.create(orderLineId, order, item, quantity, discount, comments);

			session.save(orderLine);
			session.getTransaction().commit();
			return orderLine;
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return null;
		}
	}
}
