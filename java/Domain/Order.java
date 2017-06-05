/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.List;

/**
 *
 * @author azurwular
 */
public class Order
{
    private int Orderid;
    private String Orderdate;
    private int Totalprice;
    private int Userid;
    private String Status;
    
    public Order(int oid, String date, int uid, String stat)
    {
        this.Orderid = oid;
        this.Orderdate = date;
        this.Userid = uid;
        this.Status = stat;
    }

    /**
     * @return the Orderid
     */
    public int getOrderid() {
        return Orderid;
    }

    /**
     * @param Orderid the Orderid to set
     */
    public void setOrderid(int Orderid) {
        this.Orderid = Orderid;
    }

    /**
     * @return the Orderdate
     */
    public String getOrderdate() {
        return Orderdate;
    }

    /**
     * @param Orderdate the Orderdate to set
     */
    public void setOrderdate(String Orderdate) {
        this.Orderdate = Orderdate;
    }

    /**
     * @return the Totalprice
     */
    public int getTotalprice() {
        return Totalprice;
    }

    /**
     * @param Totalprice the Totalprice to set
     */
    public void setTotalprice(int Totalprice) {
        this.Totalprice = Totalprice;
    }

    /**
     * @return the Userid
     */
    public int getUserid() {
        return Userid;
    }

    /**
     * @param Userid the Userid to set
     */
    public void setUserid(int Userid) {
        this.Userid = Userid;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
