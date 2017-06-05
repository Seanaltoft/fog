/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Sean
 */
public class Product {
    private int Productid;
    private int Empno;
    private int Orderid;
    private String Started;
    private String Completed;
    private String Status;
    private String Name; 
    private String Address;
    private String Zipcode;
    private String City;
    private int Userid;
    
    public Product(int pid, int empno, int oid, String started, String completed, String Status, String name, String address, String zipcode, String city, int userid)
    {
        this.Productid = pid;
        this.Empno = empno;
        this.Orderid = oid;
        this.Started = started;
        this.Completed = completed;
        this.Status = Status;
        this.Name = name;
        this.Address = address;
        this.Zipcode = zipcode;
        this.City = city;
        this.Userid = userid;
    }

    /**
     * @return the Productid
     */
    public int getProductid() {
        return Productid;
    }

    /**
     * @param Productid the Productid to set
     */
    public void setProductid(int Productid) {
        this.Productid = Productid;
    }

    /**
     * @return the Empno
     */
    public int getEmpno() {
        return Empno;
    }

    /**
     * @param Empno the Empno to set
     */
    public void setEmpno(int Empno) {
        this.Empno = Empno;
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
     * @return the Started
     */
    public String getStarted() {
        return Started;
    }

    /**
     * @param Started the Started to set
     */
    public void setStarted(String Started) {
        this.Started = Started;
    }

    /**
     * @return the Completed
     */
    public String getCompleted() {
        return Completed;
    }

    /**
     * @param Completed the Completed to set
     */
    public void setCompleted(String Completed) {
        this.Completed = Completed;
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

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the Zipcode
     */
    public String getZipcode() {
        return Zipcode;
    }

    /**
     * @param Zipcode the Zipcode to set
     */
    public void setZipcode(String Zipcode) {
        this.Zipcode = Zipcode;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
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
            
}
