/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import DAL.Repositories.GenerateCarportDAO;
import DAL.Repositories.OrderDAO;
import DAL.Repositories.UserRepository;
import Services.GenCarport;
import Web.BuildCarportServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sean
 */
public class maintester {
    
   public static void main(String [ ] args) throws ClassNotFoundException, Exception 
    {
//        System.out.println("hi");
//        UserRepository rp = new UserRepository();
//        User user = null;
//       try {
//           user = rp.get("s@hotmail.com", "sean");
//       } catch (NullPointerException ex) {
//           Logger.getLogger(maintester.class.getName()).log(Level.SEVERE, null, ex);
//       } catch (Exception ex) {
//           Logger.getLogger(maintester.class.getName()).log(Level.SEVERE, null, ex);
//       }
////       System.out.println(user);
//        GenCarport gener = new GenCarport();
//        String ud = gener.generateUUID();
//        System.out.println(ud);
//        Carport cp = new Carport(1, "tester", "222", 444, 444, 444);
               
        
//        List<Carport> orderList = new ArrayList(); 
//         orderList = odao.getAllUserOrders(1);
//         for(Carport cp : orderList)
//         {
//             System.out.println("HI");
//         }
//        odao.cancelOrder(5, "Cancelled");
          GenCarport dao = new GenCarport();
          Carport cdp = null;
       //Generate the UUID KEY
        String uuid = dao.generateUUID();
        Carport CusCP = null;
         GenerateCarportDAO gcdao = new GenerateCarportDAO();
//        //Generate Inventory & Push to DB
         try {
              cdp = new Carport("Custom", uuid, 1000, 333, 333, "Standard", "yes");
              dao.generateCP(cdp);
              CusCP = gcdao.CarportFromUUID(cdp.getUUID());
          } catch (Exception ex) {
              Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
         System.out.println(CusCP.getCarportID());
    }
}
