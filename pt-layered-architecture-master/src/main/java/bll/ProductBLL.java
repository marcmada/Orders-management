package bll;

import bll.validators.*;
import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is used to check the values from the interface and to call other methods if some conditions are met.
 */
public class ProductBLL {
	private List<Validator<Product>> validators;

	/**
	 * This is a constructor for the validators accordingly for this class.
	 */
	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new PretValidator());
	}

	/**
	 * This method is used for finding a product by its id.
	 * If it can not find it, it will display a message.
	 * @param id
	 * @return
	 */
	//find product by id
	public Product findProductById(int id) {
		Product p = ProductDAO.findByIdProducts(id);
		if (p == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return p;
	}

	/**
	 * This method is used for displaying all the products.
	 * @return
	 */
	//find all products
	public static ArrayList<Product> findAllProduct(){
		ArrayList<Product> p = ProductDAO.findAllProducts();
		
		return p;
	}

	/**
	 * This method is used for inserting a product.
	 * If it can not, it will display a message.
	 * @param product
	 * @return
	 */
	//insert product
	public int insertProduct(Product product) {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		int p = ProductDAO.insertProducts(product);

		if(p == -1) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Product added!","Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Product already exists","Error",JOptionPane.INFORMATION_MESSAGE);
		}

		return p;
	}

	/**
	 * This method is used for updating the db.
	 * It will display a message if the db can not be updated.
	 * @param product
	 * @return
	 */
	//update db
	public Product updateProduct(Product product) {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		Product p = ProductDAO.updateProducts(product);
		if (p == null) {
			throw new NoSuchElementException("The database can not be updated!");
		}
		return p;
	}

	/**
	 * This method is used for deleting a product from the list.
	 * @param idProduct
	 */
	//delete product
	public void deleteProduct(int idProduct) {
		ProductDAO.deleteProducts(idProduct);
	}
}
