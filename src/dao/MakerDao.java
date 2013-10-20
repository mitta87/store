/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Митя
 */
public class MakerDao {

    public List<Maker> list(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select mkr_id, mkr_name from maker");
        ResultSet rs = ps.executeQuery();
        List<Maker> list = new ArrayList<>();
        while (rs.next()) {
            Maker maker = new Maker();
            maker.setMkrId(rs.getString(1));
            maker.setMkrName(rs.getString(2));
            list.add(maker);
        }
        return list;
    }
}
