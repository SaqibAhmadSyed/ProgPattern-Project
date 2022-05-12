/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.models;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class TellerModel {
    private int tellerId;
    private String pswd;

    public TellerModel(int tellerId, String pswd) {
        this.tellerId = tellerId;
        this.pswd = pswd;
    }

    public int getTellerId() {
        return tellerId;
    }

    public void setTellerId(int tellerId) {
        this.tellerId = tellerId;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "TellerModel{" +
                "tellerId=" + tellerId +
                ", pswd=" + pswd +
                '}';
    }
}
