package presentation;

import javax.swing.*;

/**
 * It displays the information from the model to the user.
 * This class contains all the elements for the user interface for products.
 */
public class ViewProduct extends JFrame{
	private JButton b1 = new JButton("Add new product");
	private JButton b2 = new JButton("Edit product");
	private JButton b3 = new JButton("Delete product");
	private JButton b4 = new JButton("View all products");
	
	private JLabel label1 = new JLabel("Id produs");
	private JTextField textfield1 = new JTextField(3);
	private JLabel label2 = new JLabel ("Nume produs");
	private JTextField textfield2 = new JTextField(10);
	private JLabel label3 = new JLabel ("Cantitate disponibila");
	private JTextField textfield3 = new JTextField(7);
	private JLabel label4 = new JLabel ("Pret/kg");
	private JTextField textfield4 = new JTextField(3);
	private static JTable table;

	/**
	 * This method is used for the Product's table.
	 */
	public ViewProduct() {
		JPanel calcPanel= new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 100);
		
		calcPanel.add(label1);
		calcPanel.add(textfield1);
		calcPanel.add(label2);
		calcPanel.add(textfield2);
		calcPanel.add(label3);
		calcPanel.add(textfield3);
		calcPanel.add(label4);
		calcPanel.add(textfield4);
		
		calcPanel.add(b1);
		calcPanel.add(b2);
		calcPanel.add(b3);
		calcPanel.add(b4);
		
		this.add(calcPanel);
		this.setTitle("Product");

		String[] columnNames = {"",
				"",
				"",
				""};
		String[][] data={{"","","",""}};
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
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
	public JButton getB4() { return b4; }

	/**
	 * @return
	 */
	public JTextField getTextfield1() { return textfield1; }

	/**
	 * @return
	 */
	public JTextField getTextfield2() { return textfield2; }

	/**
	 * @return
	 */
	public JTextField getTextfield3() { return textfield3; }

	/**
	 * @return
	 */
	public JTextField getTextfield4() {	return textfield4; }

	/**
	 * This method is used for the table that will be created using reflection.
	 * @param table
	 */
	public static void setTable(JTable table) { ViewProduct.table = table; }
}
