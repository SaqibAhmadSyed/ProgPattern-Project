/*
 * Client model which converts database value into java values
 */
package progfinalproject.models;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class ClientsModel {
    private int clientId;
    private String firstName;
    private String lastName;
    private String identification;
    private String address;

    public ClientsModel(int clientId, String firstName, String lastName, String identification, String address) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String str = "";
        str += String.format("%d %5s %10s %15s %20s\n", clientId, firstName, lastName, identification, address);
        return str;
    }
    
}
