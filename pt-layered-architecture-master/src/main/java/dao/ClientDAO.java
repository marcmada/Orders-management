package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Client;
import connection.ConnectionFactory;

/**
 * This class has different methods for queries: insert, findbyid, findall, update and delete.
 */
public class ClientDAO {
	private static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insert = "INSERT INTO client (name, email, age) VALUES (?,?,?)";
	private static final String findById = "SELECT * FROM client WHERE idClient = ?";
	private static final String findAll = "SELECT * FROM client";
	private static final String update = "UPDATE client SET name = ?, email = ?, age = ? WHERE idClient = ?";
	private static final String delete = "DELETE FROM client WHERE idClient = ?";

	/**
	 * This method is used for finding a client by its id.
	 * @param idClient
	 * @return
	 */
	public static Client findByIdClient(int idClient) {
		Client toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findById);
			findStatement.setLong(1, idClient);
			rs = findStatement.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			Client c = new Client(idClient, name, email, age);
			toReturn = c;
		} catch (SQLException e) { LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally { ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
	 * This method is used for displaying a list with all the clients.
	 * @return
	 */
	public static ArrayList<Client> findAllClients () {
		ArrayList<Client> clients = new ArrayList<Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAll);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int idClient = rs.getInt("idClient");
				String name = rs.getString("name");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				Client c = new Client(idClient, name, email, age);
				clients.add(c);
			}
		} catch (SQLException e) { LOGGER.log(Level.WARNING, "ClientDAO:findAll" + e.getMessage());
		} finally { ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);	}
		return clients;
	}

	/**
	 * This method is used for inserting a client in the db.
	 * @param client
	 * @return
	 */
	public static int insertClients (Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			//insertStatement.setInt(1, client.getIdClient());
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getEmail());
			insertStatement.setInt(3, client.getAge());
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) { LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally { ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);	}
		return insertedId;
	}

	/**
	 * This method is used for updating the db.
	 * @param client
	 * @return
	 */
	public static Client updateClients (Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int id = client.getIdClient();
		try {
			updateStatement = dbConnection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, client.getName());
			updateStatement.setString(2, client.getEmail());
			updateStatement.setInt(3, client.getAge());
			updateStatement.setInt(4, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) { LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally { ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return client;
	}

	/**
	 * This method is used for deleting a client using its id.
	 * @param idClient
	 */
	public static void deleteClients(int idClient) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, idClient);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
