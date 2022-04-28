package progfinalproject.Interfaces;

import java.util.Map;

import progfinalproject.models.ClientsModel;

public interface Clients {

    /**
     *
     * Creates a new client in the database
     * @param id client id
     * @param fName client first name
     * @param lName client last name
     * @param identification client identification
     * @param address cilent address
     * @return true if query has been executed
     */
    public void createClient(int id, String fName, String lName, String identification, String address) throws Exception;

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
     * @param identification client address
     */
    public void updateClientAddress(int id, String addres);
    
    /**
     * Prints out all the client database from a map
     * @return a map of all the clients
     */
    public Map<Integer, String> readAllClients();
}
