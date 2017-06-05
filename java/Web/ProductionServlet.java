/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.ProductionDAO;
import Domain.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */

@WebServlet(name = "Production", urlPatterns = {"/production/production"})
public class ProductionServlet extends BaseServlet {
    ProductionDAO pdao;
    
    public ProductionServlet()
    {
       this.pdao = new ProductionDAO();
    }
    
      @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException 
   {
       response.setContentType("text/html;charset=UTF-8");
   
       List<Product> productList = null;
        try {
            productList = pdao.createPendingProducts();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       // Forward to /WEB-INF/views/homeView.jsp
       // (Users can not access directly into JSP pages placed in WEB-INF)
       request.setAttribute("productList", productList); 
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/production/productionlist.jsp");
       dispatcher.forward(request, response);
        
   }
    
}
