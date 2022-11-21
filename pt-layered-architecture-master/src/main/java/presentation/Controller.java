package presentation;

import model.Client;
import model.Order;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;

/**
 *  It controls the data flow into a model object and updates the view whenever data changes
 */
public class Controller {
	public ViewClient  theView;
	public ViewOrder   theView2;
	public ViewProduct theView3;

	/**
	 * This method contains all the buttons and their actions.
	 * @param gui
	 * @param gui2
	 * @param gui3
	 */
	public Controller(ViewClient gui, ViewOrder gui2, ViewProduct gui3) {
		this.theView = gui;
		this.theView2 = gui2;
		this.theView3 = gui3;

		theView.getB1().addActionListener(e -> {
			Client c = null;
			ClientBLL cl = new ClientBLL();
			try {
				c = new Client(
						//Integer.parseInt(theView.getTextfield1().getText()),
						theView.getTextfield2().getText(),
						theView.getTextfield3().getText(),
						Integer.parseInt(theView.getTextfield4().getText())
				);

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			System.out.println(c.toString());
			cl.insertClient(c);
		});

		theView.getB2().addActionListener(e -> {
			Client c = null;
			ClientBLL cl = new ClientBLL();
			try {
				c = new Client(
						Integer.parseInt(theView.getTextfield1().getText()),
						theView.getTextfield2().getText(),
						theView.getTextfield3().getText(),
						Integer.parseInt(theView.getTextfield4().getText())
				);

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			System.out.println(c.toString());
			cl.updateClient(c);
		});

		theView.getB3().addActionListener(e -> {
			ClientBLL cl = new ClientBLL();
			int val = Integer.parseInt(theView.getTextfield1().getText());
			cl.deleteClient(val);
		});

		theView.getB4().addActionListener(e -> {
			ClientBLL cl = new ClientBLL();
			ArrayList<Client> c;
			c = cl.findAllClient();

			JFrame f = new JFrame();
			f.setTitle("View Clients");
			JTable j = generateThroughReflection((ArrayList) c);
			//System.out.println(c);
			JScrollPane sp = new JScrollPane(j);
			f.add(sp);
			f.setSize(400, 400);
			f.setVisible(true);
		});

		theView2.getB1().addActionListener(e -> {
			OrderBLL or = new OrderBLL();
			ProductBLL pr = new ProductBLL();
			try {
				Order o = new Order(
						Integer.parseInt(theView2.getC1().getItemAt(theView2.getC1().getSelectedIndex()).toString()),
						Integer.parseInt(theView2.getC2().getItemAt(theView2.getC2().getSelectedIndex()).toString()),
						Integer.parseInt(theView2.getTextfield3().getText())
				);
				or.insertOrder(o);
				Product p = pr.findProductById(Integer.parseInt(theView2.getC2().getItemAt(theView2.getC2().getSelectedIndex()).toString()));
				p.setCantitateDisponibila(p.getCantitateDisponibila() - Integer.parseInt(theView2.getTextfield3().getText()));
				pr.updateProduct(p);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		});

		theView2.getB2().addActionListener(e -> {
			OrderBLL or = new OrderBLL();
			ArrayList<Order> o;
			o = or.findAllOrder();

			JFrame f = new JFrame();
			f.setTitle("View Orders");
			JTable j = generateThroughReflection((ArrayList) o);
			//System.out.println(o);
			JScrollPane sp = new JScrollPane(j);
			f.add(sp);
			f.setSize(400, 400);
			f.setVisible(true);
		});

		theView2.getB3().addActionListener(e -> {
			Order o = null;
			OrderBLL or = new OrderBLL();
			try {
				o = new Order(
						Integer.parseInt(theView2.getC1().getItemAt(theView2.getC1().getSelectedIndex()).toString()),
						Integer.parseInt(theView2.getC2().getItemAt(theView2.getC2().getSelectedIndex()).toString()),
						Integer.parseInt(theView2.getTextfield3().getText())
				);

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("order1.pdf"));
			} catch (FileNotFoundException | DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			document.open();

			ProductBLL pr = new ProductBLL();
			Product p = pr.findProductById(Integer.parseInt(theView2.getC2().getItemAt(theView2.getC2().getSelectedIndex()).toString()));
			ClientBLL cl = new ClientBLL();
			Client c = cl.findClientById(Integer.parseInt(theView2.getC1().getItemAt(theView2.getC1().getSelectedIndex()).toString()));
			int val = p.getPretKilogram();
			try {
				document.add(Chunk.NEWLINE);
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(new Paragraph("Product's name " + p.getNumeProdus()));
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(Chunk.NEWLINE);
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(new Paragraph("Client's name " + c.getName()));
			} catch (NumberFormatException | DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(Chunk.NEWLINE);
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(new Paragraph("Product's price " + val));
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(Chunk.NEWLINE);
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(new Paragraph("Quantity " + Integer.parseInt(theView2.getTextfield3().getText())));
			} catch (NumberFormatException | DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(Chunk.NEWLINE);
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				document.add(new Paragraph("Total price " + Integer.parseInt(theView2.getTextfield3().getText()) * val));
			} catch (NumberFormatException | DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			document.close();
		});

		theView3.getB1().addActionListener(e -> {
			Product p = null;
			ProductBLL pr = new ProductBLL();
			try {
				p = new Product(
						//Integer.parseInt(theView3.getTextfield1().getText()),
						theView3.getTextfield2().getText(),
						Integer.parseInt(theView3.getTextfield3().getText()),
						Integer.parseInt(theView3.getTextfield4().getText())
				);

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			System.out.println(p.toString());
			pr.insertProduct(p);
		});

		theView3.getB2().addActionListener(e -> {
			Product p = null;
			ProductBLL pr = new ProductBLL();
			try {
				p = new Product(
						Integer.parseInt(theView3.getTextfield1().getText()),
						theView3.getTextfield2().getText(),
						Integer.parseInt(theView3.getTextfield3().getText()),
						Integer.parseInt(theView3.getTextfield4().getText())
				);

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			System.out.println(p.toString());
			pr.updateProduct(p);
		});

		theView3.getB3().addActionListener(e -> {
			ProductBLL pr = new ProductBLL();
			int val = Integer.parseInt(theView3.getTextfield1().getText());
			pr.deleteProduct(val);
		});

		theView3.getB4().addActionListener(e -> {
			ProductBLL pr = new ProductBLL();
			ArrayList<Product> p;
			p = pr.findAllProduct();

			JFrame f = new JFrame();
			f.setTitle("View Products");
			JTable j = generateThroughReflection((ArrayList) p);
			//System.out.println(p);
			JScrollPane sp = new JScrollPane(j);
			f.add(sp);
			f.setSize(400, 400);
			f.setVisible(true);
		});
	}

	/**
	 * This method is used for generating the table using reflection.
	 * @param list
	 * @return table
	 */
	public static JTable generateThroughReflection(List<Object> list)
	{
		int i = 0, j = 0, columnsNb = 0;
		for (Field field : list.get(0).getClass().getDeclaredFields()) { columnsNb++; }
		String[] columns = new String[columnsNb];
		for (Field field : list.get(0).getClass().getDeclaredFields()) {
			columns[i] = field.getName();
			i++;
		}
		i = 0;
		String[][] data = new String[list.size()][columnsNb];
		for(Object o: list) {
			j = 0;
			for (Field field : o.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					data[i][j] = String.valueOf(field.get(o));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				j++;
			}
			i++;
		}
		JTable table = new JTable(data, columns);
		table.setBounds(40, 50, 200, 300);
		return table ;
	}
}
