/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.CustomExceptions;

/**
 *
 * @author Sean
 */
public class MissingInventoryException extends Exception {
    
    public MissingInventoryException(String message)
  {
    super(message);
  }
}
