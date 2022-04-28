package progfinalproject.Interfaces;

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
    public boolean createClient(int id, String fName, String lName, String identification, String address) throws Exception;

    /**
     * Displays the database of a selected client
     * @param id client id
     * @return client model object with data from the database
     */
    public ClientsModel readClients(int  id);

    /**
     * Updates the information of the client
     * @param client model object
     * @return true if query is successful
     */
    public boolean updateClient(ClientsModel client);
}
