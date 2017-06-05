/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Repositories;

import DAL.DataAccessObject;
import Domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sean
 */
public class ProductionDAO {
    
    public ProductionDAO()
    {
    }
    
     public List<Product> createPendingProducts() throws ClassNotFoundException
    {
        Connection stmt = null;
        List<Product> productList = new ArrayList<>();
        String sql ="SELECT ProductionLine_ID, EMPNO, productionline.Order_ID, Started, Completed, productionline.Status, first_name, last_name, address, zipcode, city, orders.id\n" +
            "FROM fog.productionline\n" +
            "left join orders ON productionline.Order_ID = orders.Order_ID\n" +
            "left join user ON orders.id = user.id\n" +
            "WHERE productionline.Status = 'Pending' OR productionline.Status ='Processing' OR Started <= now() + INTERVAL 1 DAY";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
     
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                int pid = rs.getInt("ProductionLine_ID");
                int empno = rs.getInt("EMPNO");
                int oid = rs.getInt("Order_ID");
                String started = rs.getString("Started");
                String completed = rs.getString("Completed");
                String vstatus = rs.getString("Status");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String address = rs.getString("address");
                String zip = rs.getString("zipcode");
                String city = rs.getString("city");
                String name = fname+" "+lname;
                int userid = rs.getInt("id");
                
                productList.add(new Product(pid, empno, oid, started, completed, vstatus, name, address, zip, city, userid));
            }   
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
    }
     
      public List<Product> selectProduct(int orderid) throws ClassNotFoundException
      {
        Connection stmt = null;
        List<Product> productList = new ArrayList<>();
        String sql ="SELECT ProductionLine_ID, EMPNO, productionline.Order_ID, Started, Completed, productionline.Status, first_name, last_name, address, zipcode, city, orders.id\n" +
            "FROM fog.productionline\n" +
            "left join orders ON productionline.Order_ID = orders.Order_ID\n" +
            "left join user ON orders.id = user.id\n" +
            "WHERE productionline.Order_ID = ?";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, orderid);
                
            ResultSet rs = ps.executeQuery(); 
            while (rs.next()) 
            {
                int pid = rs.getInt("ProductionLine_ID");
                int empno = rs.getInt("EMPNO");
                int oid = rs.getInt("Order_ID");
                String started = rs.getString("Started");
                String completed = rs.getString("Completed");
                String vstatus = rs.getString("Status");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String address = rs.getString("address");
                String zip = rs.getString("zipcode");
                String city = rs.getString("city");
                int userid = rs.getInt("id");
                String name = fname+" "+lname;
                
                productList.add(new Product(pid, empno, oid, started, completed, vstatus, name, address, zip, city, userid));
            }   
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
    }
      
      public int getEMPNO(int userid) throws ClassNotFoundException
      {
          int empno = 0;
          Connection stmt = null;
          String sql ="SELECT *\n" +
            "FROM employee\n" +
            "WHERE id = ?";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, userid);
                
            ResultSet rs = ps.executeQuery(); 
            while (rs.next()) 
            {
                empno = rs.getInt("EMPNO");
            }
          
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          return empno;
      }
}
