
import dao.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.util.ConnectionFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Митя
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            Connection conn = ConnectionFactory.getDefaultConnection();
            List<Maker> list = new MakerDao().list(conn);
            for (Maker mkr : list){
                System.out.println(mkr);
            }
            List<Good> goods = new GoodDao().list(conn);
            for (Good good : goods){
                System.out.println(good);
            }
            
            goods = new GoodDao().list(conn, new Category(4));
             for (Good good : goods){
                System.out.println(good);
            }
            
            List<Category> cats = new CategoryDao().listOfLeafs(conn);
            for (Category cat : cats){
                System.out.println(cat);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
