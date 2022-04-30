
package progfinalproject.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class BAMSDBConnection {
    private static Connection connect;

    public static Connection getSingleBAMSCon() throws Exception{
        if (connect == null) {
            connect = createConnection();
        }
        return connect;
    }

    public static Connection createConnection() throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:BAMS.db"); //stores database in the main project folder
        return con;
    }
}
