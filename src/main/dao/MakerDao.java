/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.beans.Maker;

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
        Collections.sort(list);
        System.out.println(list);
        return list;
    }
}
