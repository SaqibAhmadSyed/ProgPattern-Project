package progfinalproject.dbhelper;

import progfinalproject.models.TellerModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TellerDAO {

    public TellerModel getCredential() {
        try {
            Connection con = BAMSDBConnection.getSingleBAMSCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TELLER");

            if (rs.next()) {
                return new TellerModel(rs.getInt("TELLERID"), rs.getString("PASSWORD"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error Connecting to the DB ["+e.getMessage()+"]");
            return null;
        }
    }
}
