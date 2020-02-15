/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    int Id;
    private String created;
    private String fullname;
    private String productname;
    private String department;
   
    
    
    /**
     * Creates a new instance of Staffhistory
     */
    public Staffhistory() {
    }
    
    
     public void setFullname(String fullname) {
          this.fullname = fullname;
    }
    
      public String getFullname() {
        return fullname;
    }
      
         public void setDepartment(String department) {
          this.department = department;
    }
     
      public String getDepartment() {
        return department;
    }
      
        public void setProductname(String productname) {
          this.productname = productname;
    }
    
       public String getProductname() {
        return productname;
    }
    
    public void setId(int Id) {
          this.Id = Id;
    }
    
    public Integer getId() {
        return Id;
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
      
     public void setCreated(String created) {
          this.created = created;
    }
     
      public String getCreated() {
        return created;
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
     
     
      // Get the list of all storekeepers
    
         
     public List<Staffhistory> getListhistory(){      
            List listhistory = new ArrayList<Staffhistory>();
         
                try{
             conn = Connectordb.connectDb();
             String sql ="select a.*,b.*,c.* from store a, staff b, staff_history c where a.id = c.product_id and b.id = c.staff_id";
             PreparedStatement pststore = conn.prepareStatement(sql);
             ResultSet rs = pststore.executeQuery();
            while(rs.next()){
              
                 Staffhistory history = new Staffhistory();
 
                        history.setProductname(rs.getString("productname"));
                        history.setQuantity(rs.getString("quantity"));
                        history.setFullname(rs.getString("fullname"));
                        history.setDepartment(rs.getString("department"));
                        history.setCreated(rs.getString("created"));                      
                        listhistory.add(history);
                       
                      
             }      
       
           }catch (Exception e) {
            System.out.println(e);
        }    
                
    return listhistory;
    }
            
            
    
}
