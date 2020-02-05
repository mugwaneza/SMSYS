/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Kelly
 */
@Named("Staffhistory")
@RequestScoped 
public class Staffhistory {

    
    String product_id;
    String staff_id;
    String quantity;
    Connection conn = null;
    PreparedStatement pst =null;
    /**
     * Creates a new instance of Staffhistory
     */
    
    public Staffhistory() {
    }
    
    
        public void setProduct_id(String product_id) {
          this.product_id = product_id;
    }
    
      public String getProduct_id() {
        return product_id;
    }
      
      public void setStaff_id(String staff_id) {
          this.staff_id = staff_id;
    }
    
      public String getStaff_id() {
        return staff_id;
    }
    
    
      public void setQuantity(String quantity) {
          this.quantity = quantity;
    }
    
      public String getQuantity() {
        return quantity;
    }
      
      
            public String DonnateTOstaff(){
      
                   conn = Connectordb.connectDb();
          
                
           try{ 
            pst = conn.prepareStatement("insert into staff_history values (?,?,?,?,?)");
            pst.setString(1,null);
            pst.setString(2,product_id);
            pst.setString(3,staff_id);
            pst.setString(4,quantity);
            pst.setString(5,null);
          int status = pst.executeUpdate();
            if (status > 0) {
            System.out.println("Successfully saved");

            }
         
            }           
        catch(Exception e){ 
            System.out.println(e);
                   }
               
        return "/manage_storekeeper.xhtml?faces-redirect=true" ;  
             
                  
      }
    
}
