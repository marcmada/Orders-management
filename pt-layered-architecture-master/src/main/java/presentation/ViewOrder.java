package presentation;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;

/**
 * It displays the information from the model to the user.
 * This class contains all the elements for the user interface for orders.
 */
public class ViewOrder extends JFrame{
	private JButton b1 = new JButton("Add new order");
	private JButton b2 = new JButton("View all orders");
	private JButton b3 = new JButton("Create a bill");
	
	private JLabel label1 = new JLabel("Id client");
	public JComboBox c1 = new JComboBox();
	private JLabel label2 = new JLabel ("Id produs");
	public JComboBox c2 = new JComboBox();
	private JLabel label3 = new JLabel ("Cantitate ceruta");
	private JTextField textfield3 = new JTextField(5);

	/**
	 * This method is used for the Order's table.
	 */
	public ViewOrder() {
		JPanel calcPanel= new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 100);
		
		calcPanel.add(label1);
		calcPanel.add(c1);
		calcPanel.add(label2);
		calcPanel.add(c2);
		calcPanel.add(label3);
		calcPanel.add(textfield3);

		calcPanel.add(b1);
		calcPanel.add(b2);
		calcPanel.add(b3);
		
		ClientBLL cl  = new ClientBLL();
        ProductBLL pr = new ProductBLL();
        ArrayList<Client>  client  = cl.findAllClient();
        ArrayList<Product> product = pr.findAllProduct();
		
        for (Client i: client)
            c1.addItem(i.getIdClient());
        for (Product j: product)
            c2.addItem(j.getIdProduct());
        
		this.add(calcPanel);
		this.setTitle("Order");
	}

	/**
	 * @return
	 */
	public JButton getB1() { return b1; }

	/**
	 * @return
	 */
	public JButton getB2() { return b2; }

	/**
	 * @return
	 */
	public JButton getB3() { return b3; }

	/**
	 * @return
	 */
	public JComboBox getC1() {
		return c1;
	}

	/**
	 * @return
	 */
	public JComboBox getC2() {
		return c2;
	}

	/**
	 * @return
	 */
	public JTextField getTextfield3() {	return textfield3; }
	
}
