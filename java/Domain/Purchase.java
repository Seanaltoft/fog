/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

public class Purchase {
    private String Title;
    private int OrderID;
    private int CarportID;
    private int Price;
    private int Height;
    private int Width;
    private int Length;
    private String Cptype;
    private String Rooftype;
    private String Date;

    
    public Purchase(String Title, int Order_ID, int CarportID, int Price, int Height, int Width, int Length, String CPType, String RoofType, String date)
    {
        this.Title = Title;
        this.OrderID = Order_ID;
        this.CarportID = CarportID;
        this.Price = Price;
        this.Height = Height;
        this.Width = Width;
        this.Length = Length;
        this.Cptype = CPType;
        this.Rooftype = RoofType;
        this.Date = date;
    }
    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the OrderID
     */
    public int getOrderID() {
        return OrderID;
    }

    /**
     * @param OrderID the OrderID to set
     */
    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    /**
     * @return the CarportID
     */
    public int getCarportID() {
        return CarportID;
    }

    /**
     * @param CarportID the CarportID to set
     */
    public void setCarportID(int CarportID) {
        this.CarportID = CarportID;
    }

    /**
     * @return the Price
     */
    public int getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(int Price) {
        this.Price = Price;
    }

    /**
     * @return the Height
     */
    public int getHeight() {
        return Height;
    }

    /**
     * @param Height the Height to set
     */
    public void setHeight(int Height) {
        this.Height = Height;
    }

    /**
     * @return the Width
     */
    public int getWidth() {
        return Width;
    }

    /**
     * @param Width the Width to set
     */
    public void setWidth(int Width) {
        this.Width = Width;
    }

    /**
     * @return the Length
     */
    public int getLength() {
        return Length;
    }

    /**
     * @param Length the Length to set
     */
    public void setLength(int Length) {
        this.Length = Length;
    }

    /**
     * @return the Cptype
     */
    public String getCptype() {
        return Cptype;
    }

    /**
     * @param Cptype the Cptype to set
     */
    public void setCptype(String Cptype) {
        this.Cptype = Cptype;
    }

    /**
     * @return the Rooftype
     */
    public String getRooftype() {
        return Rooftype;
    }

    /**
     * @param Rooftype the Rooftype to set
     */
    public void setRooftype(String Rooftype) {
        this.Rooftype = Rooftype;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

 
       
}
