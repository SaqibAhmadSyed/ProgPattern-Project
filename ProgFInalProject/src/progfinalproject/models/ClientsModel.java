/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.models;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ClientsModel(ResultSet rs) {
        try{
            this.clientId = rs.getInt("clientId");
            this.firstName = rs.getString("firstName");
            this.lastName = rs.getString("lastName");
            this.identification = rs.getString("identification");
            this.address = rs.getString("address");
        }catch(SQLException e){
            System.out.println("Error creating clients model [" + e.getMessage() + "]");
        }
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
        return "Clients=" + "clientId=" + clientId + ", First Name=" + firstName + ", Last Name=" + lastName + ", Identification=" + identification + ", Address=" + address;
    }
    
}
