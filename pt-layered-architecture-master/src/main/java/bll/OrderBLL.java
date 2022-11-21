package bll;

import bll.validators.*;
import dao.OrderDAO;
import model.Order;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is used to check the values from the interface and to call other methods if some conditions are met.
 */
public class OrderBLL {
	private List<Validator<Order>> validators;

	/**
	 * This is a constructor for the validators accordingly for this class.
	 */
	public OrderBLL() {
		validators = new ArrayList<Validator<Order>>();
		validators.add(new CantitateValidator());
	}

	/**
	 * This method is used for finding an order bt its id.
	 * It will display a message if it can not find the order.
	 * @param id
	 * @return
	 */
	//find order by id
	public Order findOrderById(int id) {
		Order o = OrderDAO.findByIdOrder(id);
		if (o == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return o;
	}

	/**
	 * This method is used for displaying all the orders.
	 * @return
	 */
	//find all orders
	public ArrayList<Order> findAllOrder(){
		ArrayList<Order> o = OrderDAO.findAllOrders();
		
		return o;
	}

	/**
	 * This method is used for inserting an order.
	 * If it can not insert an order, it will display a message.
	 * @param order
	 * @return
	 */
	//insert order
	public int insertOrder(Order order) {
		for (Validator<Order> v : validators) {
			v.validate(order);
		}
		int o = OrderDAO.insertOrders(order);

		JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Order added!","Message", JOptionPane.INFORMATION_MESSAGE);

		return o;
	}

}
