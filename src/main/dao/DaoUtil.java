/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Митя
 */
public class DaoUtil {
 private static final String PROP_FILE = "sql.xml";
      public static String getQueryFromProperties(String key) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(PROP_FILE);
        prop.loadFromXML(fis);
        fis.close();
        return prop.getProperty(key);}   
}
