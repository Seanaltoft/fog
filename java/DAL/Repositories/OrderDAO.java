/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Repositories;

import DAL.DataAccessObject;
import Domain.Carport;
import Domain.CarportOrder;
import Domain.Carport_Has_Inventory;
import Domain.Inventory;
import Domain.Order;
import Domain.Purchase;
import Services.CustomExceptions.MissingInventoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sean
 */
public class OrderDAO {
    
    public List<Purchase> createPurchaseList(int userid)
    {
        Connection stmt = null;
        List<Purchase> purchaseList = new ArrayList<>();
        String sql ="SELECT *\n" +
        "FROM fog.orders\n" +
        "LEFT JOIN orders_has_carport ON orders.Order_ID=orders_has_carport.Order_ID\n" +
        "LEFT JOIN carport ON orders_has_carport.Carport_ID=Carport.Carport_ID\n" +
        "WHERE orders.id = ?\n" +
        "AND Status = 'Confirmed'";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, userid);
                
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                String title = rs.getString("Title");
                int orderid = rs.getInt("Order_ID");
                int carportid = rs.getInt("Carport_ID");
                int price = getCarportPrice(carportid);
                int height = rs.getInt("Height");
                int width = rs.getInt("Width");
                int length = rs.getInt("Length");
                String cptype = rs.getString("CPType");
                String rooftype = rs.getString("RoofType");
                String date = rs.getString("Order_Date");
                
                purchaseList.add(new Purchase(title, orderid, carportid, price, height, width, length, cptype, rooftype, date));
            }   
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchaseList;
    }
    
    public List<Purchase> createPurchase(int userid, int cpid) throws ClassNotFoundException
    {
        Connection stmt = null;
        List<Purchase> purchaseList = new ArrayList<>();
        String sql ="SELECT *\n" +
        "FROM fog.orders\n" +
        "LEFT JOIN orders_has_carport ON orders.Order_ID=orders_has_carport.Order_ID\n" +
        "LEFT JOIN carport ON orders_has_carport.Carport_ID=Carport.Carport_ID\n" +
        "WHERE orders.id = ?\n" +
        "AND Status = 'Confirmed'\n" +
        "AND orders_has_carport.Carport_ID = ?";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, userid);
                ps.setInt(2, cpid);
                
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                String title = rs.getString("Title");
                int orderid = rs.getInt("Order_ID");
                int carportid = rs.getInt("Carport_ID");
                int price = getCarportPrice(carportid);
                int height = rs.getInt("Height");
                int width = rs.getInt("Width");
                int length = rs.getInt("Length");
                String cptype = rs.getString("CPType");
                String rooftype = rs.getString("RoofType");
                String date = rs.getString("Order_Date");
                
                purchaseList.add(new Purchase(title, orderid, carportid, price, height, width, length, cptype, rooftype, date));
            }   
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchaseList;
    }
    
    
    
    public List<Inventory> createInventoryList(int cpid) throws MissingInventoryException, ClassNotFoundException
    {
        Connection stmt = null;
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT Inventory.Inventory_ID, Price*(Inventory_AMT) as Price, Carport_ID, Inventory_AMT, Inventory_Title, Length, Depth, Width, Material, Type, Box_AMT\n" +
            "FROM fog.carport_has_inventory\n" +
            "LEFT JOIN inventory ON carport_has_inventory.Inventory_ID=Inventory.Inventory_ID\n" +
            "WHERE carport_has_inventory.Carport_ID = ?";
        try
        {
              stmt = DataAccessObject.getConnection();
              PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, cpid);
                
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                int id= rs.getInt("Inventory_ID");
                String title = rs.getString("Inventory_Title");
                int length = rs.getInt("Length");
                int depth = rs.getInt("Depth");
                int width = rs.getInt("Width");
                String material = rs.getString("Material");
                String type = rs.getString("Type");
                int box_amt = rs.getInt("Box_AMT");
                int price = rs.getInt("Price");
                int inventoryamt = rs.getInt("Inventory_AMT");
                
                inventoryList.add(new Inventory(id, title, length, depth, width, material, type, box_amt, price, inventoryamt));
                
            }   
            if (inventoryList.isEmpty())
            {
                throw new MissingInventoryException("No Inventory");
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (MissingInventoryException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return inventoryList;
    }
    
    public int getCarportPrice(int cpid) throws ClassNotFoundException
    {
        int totalprice = 0;
        Connection stmt = null;
        String sql = "SELECT SUM(Price*Inventory_AMT) as TotalPrice\n" +
            "FROM fog.carport_has_inventory\n" +
            "LEFT JOIN inventory ON carport_has_inventory.Inventory_ID=Inventory.Inventory_ID\n" +
            "WHERE carport_has_inventory.Carport_ID = ?";
        try
        {
              stmt = DataAccessObject.getConnection();
              PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, cpid);
                
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                totalprice = rs.getInt("TotalPrice");
            }   
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalprice;
    }
    
     public void updateorderline(int oid, String stat) throws ClassNotFoundException
     {
        Connection stmt = null;
       
        String sql ="Update productionline set Status = ? WHERE Order_ID = ?";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setString(1, stat);
                ps.setInt(2, oid);
                ps.executeUpdate();         
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void updateorderlineemp(int oid, int empno) throws ClassNotFoundException
     {
        Connection stmt = null;
       
        String sql ="Update productionline set EMPNO = ? WHERE Order_ID = ?";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, empno);
                ps.setInt(2, oid);
                ps.executeUpdate();         
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
     
     public Order insertOrder(int userid, Carport carport) throws ClassNotFoundException
     {
         Order uorder = null;
         int cpid = 0;
         Connection stmt = null;
         String sql = "insert into orders(id, status) values\n" +
            "(?, 'Pending')";
         //generate order and obtain order id with status = 'Pending'
          try
        {
              stmt = DataAccessObject.getConnection();
              PreparedStatement ps = stmt.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userid);
                
              ps.executeUpdate();    
              ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) 
            {
                
                  int oid = rs.getInt(1);
                  uorder = getOrder(oid);
            }               
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         //add carport to db.carpot_has_order
         insertCPOrder(carport.getCarportID(), uorder.getOrderid());
         //return order object
         return uorder;
     }
     
     public void insertCPOrder(int cpid, int oid) throws ClassNotFoundException
     {
        Connection stmt = null;
        String sql = "insert into orders_has_carport(Carport_ID, Order_ID) values\n" +
            "(?, ?)";
        try
        {
              stmt = DataAccessObject.getConnection();
              PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, cpid);
                ps.setInt(2, oid);
                
            ps.executeUpdate();                        
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public Order getOrder(int oid) throws ClassNotFoundException
     {
         Order uorder = null;
         Connection stmt = null;
         String sql = "Select *\n" +
            "FROM orders\n" +
            "WHERE Order_ID = ?";
         try
        {
              stmt = DataAccessObject.getConnection();
              PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, oid);
                
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                int orderid = rs.getInt("Order_ID");
                String date = rs.getString("Order_Date");
                int uid = rs.getInt("id");
                String statu = rs.getString("Status");
                
                uorder = new Order(orderid, date, uid, statu);
            }   
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uorder;

     }
     
     public List getAllUserOrders(int userid) throws ClassNotFoundException
     {
         List<CarportOrder> orderList = new ArrayList();
         Connection stmt = null;
         String sql = "Select *\n" +
            "From orders\n" +
            "LEFT JOIN orders_has_carport on orders_has_carport.Order_ID=orders.Order_ID\n" +
            "LEFT JOIN carport on carport.Carport_ID=orders_has_carport.Carport_ID\n" +
            "WHERE id = ?\n" +
            "AND Status = 'Pending'";
         
         try
        {
              stmt = DataAccessObject.getConnection();
              PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, userid);
                
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) 
            {
                  int cpID = rs.getInt("Carport_ID");
                String cpTitle = rs.getString("Title");
                String cpUUID = rs.getString("UUID");
                int cpHeight = rs.getInt("Height");
                int cpWidth = rs.getInt("Width");
                int cpLength = rs.getInt("Length");
                String cpType = rs.getString("CPType");
                String rooftype = rs.getString("RoofType");
                int oid = rs.getInt("Order_ID");
                int price = getCarportPrice(cpID);
               
                orderList.add(new CarportOrder(cpID, cpTitle, cpUUID, cpHeight, cpWidth, cpLength, cpType, rooftype, oid, price));
            }   
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return orderList;
     }
     
     public void updateOrderStatus(int oid, String stat)
     {
         Connection stmt = null;
       
        String sql ="Update orders set Status = ? WHERE Order_ID = ?";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setString(1, stat);
                ps.setInt(2, oid);
                ps.executeUpdate();         
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     public void purchaseOrders(List<CarportOrder> orderList) throws ClassNotFoundException, SQLException
     {
         Connection stmt = DataAccessObject.getConnection();
         String stat = "Confirmed";
        String sql = "Update orders set Status = ? WHERE Order_ID = ?";
        PreparedStatement ps = stmt.prepareStatement(sql);
        
        final int batchSize = 1000;
        int cnt = 0;
        try
        {
            
            
            for (CarportOrder query : orderList) {
                ps.setString(1, stat);
                ps.setInt(2, query.getOrderid());
                ps.addBatch();
                
                if(++cnt % batchSize == 0) 
                {
                    ps.executeBatch();
                }
            }
            insertOrderLines(orderList);
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(GenerateCarportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.executeBatch();
        ps.close();
        DataAccessObject.releaseConnection(stmt);
     }
   

      public void insertOrderLines(List<CarportOrder> orderList) throws ClassNotFoundException, SQLException
     {
         Connection stmt = DataAccessObject.getConnection();
         String sql = "insert into productionline(Order_ID, Status) values\n" +
          "(?, 'Pending')";
        PreparedStatement ps = stmt.prepareStatement(sql);
        
        final int batchSize = 1000;
        int cnt = 0;
        try
        {        
            for (CarportOrder query : orderList) {
                ps.setInt(1, query.getOrderid());
                ps.addBatch();
                
                if(++cnt % batchSize == 0) 
                {
                    ps.executeBatch();
                }
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(GenerateCarportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.executeBatch();
        ps.close();
        DataAccessObject.releaseConnection(stmt);
     }
}
