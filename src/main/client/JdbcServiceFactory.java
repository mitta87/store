/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client;

import main.business.CategoryJdbcService;
import main.business.GoodJdbcService;
import main.business.MakerJdbcService;

/**
 *
 * @author Митя
 */
public class JdbcServiceFactory {

    private GoodJdbcService goodJdbc;
    private MakerJdbcService makerJdbc;
    private CategoryJdbcService catJdbc;

    public JdbcServiceFactory() {
        goodJdbc = new GoodJdbcService();
        catJdbc = new CategoryJdbcService();
        makerJdbc = new MakerJdbcService();
    }

    public GoodJdbcService getGoodJdbc() {
        return goodJdbc;
    }

    public MakerJdbcService getMakerJdbc() {
        return makerJdbc;
    }

    public CategoryJdbcService getCatJdbc() {
        return catJdbc;
    }    
    
}
