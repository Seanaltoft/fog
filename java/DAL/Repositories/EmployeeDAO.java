/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Repositories;

import DAL.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sean
 */
public class EmployeeDAO {
    
    public EmployeeDAO()
    {
    }
    
    public boolean isUserEmployee (int userid) 
    {
        boolean isemp = false;
        Connection stmt = null;
        String sql ="select *\n" +
            "FROM employee \n" +
            "where id = ?;";
        try
        {
            stmt = DataAccessObject.getConnection();
            PreparedStatement ps = stmt.prepareStatement(sql);
                ps.setInt(1, userid);
                ResultSet rs = ps.executeQuery(); 
                isemp=rs.next();  
        }catch(SQLException ex)
        {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isemp;
    }
}
