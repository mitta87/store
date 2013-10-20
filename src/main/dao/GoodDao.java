package main.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.beans.Category;
import main.beans.Good;
import main.beans.Maker;




public class GoodDao {

    private List<Good> handleResultSet(ResultSet rs) throws SQLException {
        List<Good> list = new ArrayList<Good>();
        while (rs.next()) {
            Good good = new Good();
            good.setId(rs.getInt(1));
            good.setMaker(new Maker(rs.getString(2), rs.getString(3)));
            good.setCatId(rs.getInt(4));
            good.setModel(rs.getString(5));
            good.setPrice(rs.getBigDecimal(6));
            good.setImage(rs.getString(7));
            good.setInfo(rs.getString(8));
            String available = rs.getString(9);
            if (available.equals("y")) {
                good.setIsAvailable(true);
            } else {
                good.setIsAvailable(false);
            }
            list.add(good);
        }
        return list;
    }

    public List<Good> list(Connection conn) throws SQLException {
        List<Good> list = new ArrayList<Good>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(DaoUtil.getQueryFromProperties("allGoods"));
            rs = ps.executeQuery();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoodDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return handleResultSet(rs);
    }

    public List<Good> list(Connection conn, Category category) throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(DaoUtil.getQueryFromProperties("goodsOfCategory"));
            ps.setInt(1, category.getCatID());
            rs = ps.executeQuery();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoodDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return handleResultSet(rs);
    }

    public int deleteGood(Connection conn, int goodId) throws SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = conn.prepareStatement(DaoUtil.getQueryFromProperties("deleteGood"));
        ps.setInt(1, goodId);
        int result = ps.executeUpdate();
        ps.close();
        return result;
    }
}
