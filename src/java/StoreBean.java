/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.corba.se.impl.naming.pcosnaming.NameService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Kelly
 */
@Named("StoreBean")
@RequestScoped 
public class StoreBean {
    /**
     * Creates a new instance of StoreBean
     */

    private String productname;
    private String quantity;
    private String description;
    private String created;
    Connection conn = null;
    PreparedStatement pst =null;
    private int Id;
  
      public void setId(int Id) {
          this.Id = Id;
    }
    
      public Integer getId() {
        return Id;
    }   
    
    public void setProductname(String productname) {
          this.productname = productname;
    }
    
       public String getProductname() {
        return productname;
    }
    
   
    public void setQuantity(String quantity) {
          this.quantity = quantity;
    }
    
      public String getQuantity() {
        return quantity;
    }

 
    public void setDescription(String description) {
          this.description = description;
    }
    
        public String getDescription() {
        return description;
    }

        
           public void setCreated(String created) {
          this.created = created;
    }
     
      public String getCreated() {
        return created;
    }
    
      public String AddTostore(){
      
                   conn = Connectordb.connectDb();
             
             if(IsProductExist()){
             // if user product exist do notjing 
                         System.out.println("Product already exist");
             }
             else{
                
           try{ 
            pst = conn.prepareStatement("insert into store values (?,?,?,?,?)");
            pst.setString(1,null);
            pst.setString(2,productname);
            pst.setString(3,quantity);
            pst.setString(4,description);
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
                   
             return null;

      }  
       
    
        public boolean IsProductExist(){
           try{
             conn = Connectordb.connectDb();
              String sql ="select id,productname from store where productname=  '" + productname + "' ";
              PreparedStatement pst = conn.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();
            if(rs.next()){
              return true  ;
             }
           }catch (Exception e) {
            System.out.println(e);
            
        }    
       return false;   
    }
        
        
 // Get the list of all storekeepers
    
          public List<StoreBean> getStoreproduct(){
            
            List storeproduct = new ArrayList<StoreBean>();
         
                try{
             conn = Connectordb.connectDb();
             String sql ="select *  from store ";
             PreparedStatement pststore = conn.prepareStatement(sql);
              ResultSet rs = pststore.executeQuery();
            while(rs.next()){
              
                 StoreBean store = new StoreBean();
 
                        store.setId(rs.getInt("id"));
                        store.setProductname(rs.getString("productname"));
                        store.setQuantity(rs.getString("quantity"));
                        store.setDescription(rs.getString("description"));
                        store.setCreated(rs.getString("created"));                      
                        storeproduct.add(store);
             }      
       
           }catch (Exception e) {
            System.out.println(e);
        }    
                 

    return storeproduct;
    }
        
    

    
}
