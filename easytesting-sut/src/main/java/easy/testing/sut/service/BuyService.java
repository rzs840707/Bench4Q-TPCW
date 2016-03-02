package easy.testing.sut.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easy.testing.sut.entity.Address;
import easy.testing.sut.entity.BuyResult;
import easy.testing.sut.entity.Customer;
import easy.testing.sut.entity.Order;
import easy.testing.sut.entity.ShoppingCartList;

@Component
public class BuyService {
	private ShoppingCartService shoppingCartService;
	private CustomerService customerService;
	private OrderService orderService;
	private CreditCardTransactionService creditCardTransactionService;
	private AddressService addressService;

	private ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	@Autowired
	private void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	private CustomerService getCustomerService() {
		return customerService;
	}

	@Autowired
	private void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private OrderService getOrderService() {
		return orderService;
	}

	@Autowired
	private void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	private CreditCardTransactionService getCreditCardTransactionService() {
		return creditCardTransactionService;
	}

	@Autowired
	private void setCreditCardTransactionService(CreditCardTransactionService creditCardTransactionService) {
		this.creditCardTransactionService = creditCardTransactionService;
	}

	private AddressService getAddressService() {
		return addressService;
	}

	@Autowired
	private void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public BuyResult buy(int shoppingCartId, int customerId, String creditCardType, long creditCardNumber,
			String cardHolderName, Date expirationDate, String shipType) {
		Customer customer = this.getCustomerService().getCustomerById(customerId);
		Address shipAddress = customer.getAddress();
		return this.doBuy(shoppingCartId, customerId, creditCardType, creditCardNumber, cardHolderName, expirationDate,
				shipType, customer, shipAddress);
	}

	public BuyResult buy(int shoppingCartId, int customerId, String creditCardType, long creditCardNumber,
			String cardHolderName, Date expirationDate, String shipType, String streetLine1, String streetLine2,
			String city, String state, String zipCode, String countryName) {
		Customer customer = this.getCustomerService().getCustomerById(customerId);
		Address shipAddress = this.getAddressService().newAddress(streetLine1, streetLine2, city, state, zipCode,
				countryName);
		return this.doBuy(shoppingCartId, customerId, creditCardType, creditCardNumber, cardHolderName, expirationDate,
				shipType, customer, shipAddress);
	}

	private BuyResult doBuy(int shoppingCartId, int customerId, String creditCardType, long creditCardNumber,
			String cardHolderName, Date expirationDate, String shipType, Customer customer, Address shipAddress) {
		ShoppingCartList shoppingCartList = this.getShoppingCartService().getShoppingCartList(shoppingCartId,
				customer.getDiscount());
		Order order = this.getOrderService().addOrder(customerId, shoppingCartList, shipAddress.getId(), shipType,
				customer.getDiscount());
		this.getCreditCardTransactionService().createCreditCardTransaction(order.getId(), creditCardType,
				creditCardNumber, cardHolderName, expirationDate, shoppingCartList.getTotalCost(), shipAddress.getId());
		this.getShoppingCartService().clearShoppingCart(shoppingCartId);
		return new BuyResult(shoppingCartList, order);
	}
}
