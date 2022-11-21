package bll;

import bll.validators.AgeValidator;
import bll.validators.EmailValidator;
import bll.validators.IdValidator;
import dao.ClientDAO;
import model.Client;
import bll.validators.Validator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is used to check the values from the interface and to call other methods if some conditions are met.
 */
public class ClientBLL {
	private List<Validator<Client>> validators;

	/**
	 * This is a constructor for the validators accordingly for this class.
	 */
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new AgeValidator());
	}

	/**
	 * 	 This method is used for finding a client by his id.
	 * 	 If it can not find the client, it will display a message.
	 * @param id
	 * @return
	 */
	//find client by id
	public Client findClientById(int id) {
		Client c = ClientDAO.findByIdClient(id);
		if (c == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return c;
	}

	/**
	 * This method is used for displaying all clients.
	 * @return
	 */
	//find all clients
	public ArrayList<Client> findAllClient(){
		ArrayList<Client> c = ClientDAO.findAllClients();
		
		return c;
	}

	/**
	 * This method is used for inserting a client.
	 * If it can not, it will display a message.
	 * @param client
	 * @return
	 */
	//insert client
	public int insertClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		int c = ClientDAO.insertClients(client);

		if(c != -1) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Client added!","Message", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Client already exists","Error",JOptionPane.INFORMATION_MESSAGE);
		}

		return c;
	}

	/**
	 * This method is used for updating the db.
	 * It will display a message if the db can not be updated.
	 * @param client
	 * @return
	 */
	//update db
	public Client updateClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		Client c = ClientDAO.updateClients(client);
		if (c == null) {
			throw new NoSuchElementException("The database can not be updated!");
		}
		return c;
	}

	/**
	 * This method is used for deleting a client from the list.
	 * @param idClient
	 */
	//delete client
	public void deleteClient(int idClient) {
		ClientDAO.deleteClients(idClient);
	}
}
