package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Order;
import connection.ConnectionFactory;

/**
 * This class has different methods for queries: insert, findbyid and findall.
 */
public class OrderDAO {
	private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insert = "INSERT INTO schooldb.order (idClient, idProdus, cantitateCeruta) VALUES (?,?,?)";
	private static final String findById = "SELECT * FROM schooldb.order WHERE idOrder = ?";
	private static final String findAll = "SELECT * FROM schooldb.order";

	/**
	 * This method is used for finding an order by its id.
	 * @param idProdus
	 * @return
	 */
	public static Order findByIdOrder(int idProdus) {
		Order toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findById);
			findStatement.setLong(1, idProdus);
			rs = findStatement.executeQuery();
			rs.next();
			
			int idClient = rs.getInt("idClient");
			int cantitateCeruta = rs.getInt("cantitateCeruta");
			Order o = new Order(idClient, idProdus, cantitateCeruta);
			toReturn = o;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
	 * This method is used for displaying a list with all the orders.
	 * @return
	 */
	public static ArrayList<Order> findAllOrders () {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAll);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int idClient = rs.getInt("idClient");
				int idProdus = rs.getInt("idProdus");
				int cantitateCeruta = rs.getInt("cantitateCeruta");
				Order o = new Order(idClient, idProdus, cantitateCeruta);
				orders.add(o);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:findAll" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return orders;
	}

	/**
	 * This method is used for inserting an order in the db.
	 * @param order
	 * @return
	 */
	public static int insertOrders (Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getIdClient());
			insertStatement.setInt(2, order.getIdProdus());
			insertStatement.setInt(3, order.getCantitateCeruta());
			//insertStatement.setInt(4, order.getTotal());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

}
