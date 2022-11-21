package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Product;

/**
 * This class has different methods for queries: insert, findbyid, findall, update and delete.
 */
public class ProductDAO {
	private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	private static final String insert = "INSERT INTO product (numeProdus, cantitateDisponibila, pretKilogram) VALUES (?,?,?)";
	private static final String findById = "SELECT * FROM product WHERE idProduct = ?";
	private static final String findAll = "SELECT * FROM product";
	private static final String update = "UPDATE product SET numeProdus = ?, cantitateDisponibila = ?, pretKilogram = ? WHERE idProduct = ?";
	private static final String delete = "DELETE FROM product WHERE idProduct = ?";

	/**
	 * This method is used for finding a product by its id.
	 * @param idProduct
	 * @return
	 */
	public static Product findByIdProducts(int idProduct) {
		Product toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findById);
			findStatement.setLong(1, idProduct);
			rs = findStatement.executeQuery();
			rs.next();

			String numeProdus = rs.getString("numeProdus");
			int cantitateDisponibila = rs.getInt("cantitateDisponibila");
			int pretKilogram = rs.getInt("pretKilogram");
			Product p = new Product(idProduct, numeProdus, cantitateDisponibila, pretKilogram);
			toReturn = p;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
	 * This method is used for displaying a list with all the products.
	 * @return
	 */
	public static ArrayList<Product> findAllProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAll);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int idProduct = rs.getInt("idProduct");
				String numeProdus = rs.getString("numeProdus");
				int cantitateDisponibila = rs.getInt("cantitateDisponibila");
				int pretKilogram = rs.getInt("pretKilogram");
				Product p = new Product(idProduct, numeProdus, cantitateDisponibila, pretKilogram);
				products.add(p);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findAll" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return products;
	}

	/**
	 * This method is used for inserting a product in the db.
	 * @param product
	 * @return
	 */
	public static int insertProducts (Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			///insertStatement.setInt(1, product.getIdProduct());
			insertStatement.setString(1, product.getNumeProdus());
			insertStatement.setInt(2, product.getCantitateDisponibila());
			insertStatement.setInt(3, product.getPretKilogram());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
				System.out.println(insertedId);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	/**
	 * This method is used for updating the db.
	 * @param product
	 * @return
	 */
	public static Product updateProducts (Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int id = product.getIdProduct();
		try {
			updateStatement = dbConnection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, product.getNumeProdus());
			updateStatement.setInt(2, product.getCantitateDisponibila());
			updateStatement.setInt(3, product.getPretKilogram());
			updateStatement.setInt(4, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return product;
	}

	/**
	 * This method is used for deleting a product using its id.
	 * @param idProduct
	 */
	public static void deleteProducts(int idProduct) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, idProduct);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
