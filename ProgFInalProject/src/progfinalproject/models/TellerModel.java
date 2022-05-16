/*
 * Teller model which converts database value into java values
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
