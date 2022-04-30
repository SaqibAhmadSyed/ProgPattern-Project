package progfinalproject.Interfaces;

import java.util.List;
import java.util.Map;

import progfinalproject.models.ClientsModel;

public interface Clients {

    /**
     *
     * Creates a new client in the database
     * @param fName client first name
     * @param lName client last name
     * @param identification client identification
     * @param address cilent address
     * @return true if query has been executed
     */
    public void createClient(String fName, String lName, String identification, String address) throws Exception;

    /**
     * Displays the database of a selected client
     * @param id client id
     * @return client model object with data from the database
     */
    public ClientsModel readClients(int  id);

    /**
     * Updates the information of the client identification
     * @param identification client identification
     */
    public void updateClientIdentification(int id, String identification);

    /**
     * Updates the information of the client address
     * @param address client address
     */
    public void updateClientAddress(int id, String address);
    
    /**
     * Prints out all the client database from a map sorted by first name
     * @return a map of all the clients
     */
    public void readAllClients();
}
