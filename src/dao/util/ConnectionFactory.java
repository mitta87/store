/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.util;

import javax.sql.*;
import com.sybase.jdbc4.jdbc.*;
import java.sql.*;

/**
 *
 * @author Митя
 */
public class ConnectionFactory {

    public static Connection getDefaultConnection() throws SQLException {
        SybDataSource ds = new SybDataSource();        
        ds.setServerName("127.0.0.1");
        ds.setPortNumber(5000);
        ds.setDatabaseName("store");
        ds.setUser("sa");
        ds.setPassword("");
        return ds.getConnection();
    }
}
