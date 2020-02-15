/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kelly
 */
@Named("UserManagedBean")
@RequestScoped 
public class UserManagedBean {

    private String fullname;
    private String email;
    private String password;
    private String department;
    private String address;
    private String phonenumber;
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
   
   
        /**
* @param fullname the fullname to set
*/
      public void setFullname(String fullname) {
          this.fullname = fullname;
    }
    
/**
* @return the fullname
*/
      public String getFullname() {
        return fullname;
    }
    
   
       public void setEmail(String email) {
          this.email = email;
    }
     
      public String getEmail() {
        return email;
    }
    
     public void setPassword(String password) {
          this.password = password;
    }
     
      public String getPassword() {
        return password;
    }
      
     public void setAddress(String address) {
          this.address = address;
    }
     
      public String getAddress() {
        return address;
    }
     public void setPhonenumber(String phonenumber) {
          this.phonenumber = phonenumber;
    }
     
      public String getPhonenumber() {
        return phonenumber;
    }
      
      
     public void setDepartment(String department) {
          this.department = department;
    }
     
      public String getDepartment() {
        return department;
    }
     public void setCreated(String created) {
          this.created = created;
    }
     
      public String getCreated() {
        return created;
    }
      
   public String Adduser(){
       
             conn = Connectordb.connectDb();
             
             if(IsUserExist()){
             // if user full name or email exist do notjing 
             }
             else{
                
           try{ 
            pst = conn.prepareStatement("insert into users values (?,?,?,?,?)");
            pst.setString(1,null);
            pst.setString(2,fullname);
            pst.setString(3,email);
            pst.setString(4,password);
            pst.setString(5,null);
          int status = pst.executeUpdate();
            if (status > 0) {
            System.out.println("Successfully Registered");

            }
         
            }           
        catch(Exception e){ 
            System.out.println(e);
                   }
               
        return "/register.xhtml?faces-redirect=true" ;  
             }
                   
             return null;

      }
   
      public String AddStaff(){
      
                   conn = Connectordb.connectDb();
             
             if(IsUserExist()){
             // if user full name or email exist do notjing 
                         System.out.println("Email or names already exist");

             }
             else{
                
           try{ 
            pst = conn.prepareStatement("insert into staff values (?,?,?,?,?,?,?)");
            pst.setString(1,null);
            pst.setString(2,fullname);
            pst.setString(3,email);
            pst.setString(4,department);
            pst.setString(5,address);
            pst.setString(6,phonenumber);
            pst.setString(7,null);
          int status = pst.executeUpdate();
            if (status > 0) {
            System.out.println("Successfully Registered");

            }
         
            }           
        catch(Exception e){ 
            System.out.println(e);
                   }
               
        return "/manage_staff.xhtml?faces-redirect=true" ;  
             }
                   
             return null;

      }

           
    
    public String Login(){
       
       
        
              try{
             conn = Connectordb.connectDb();
              String sql ="select id,email,password from users where email=  '" + email + "'";
              PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int  myid = rs.getInt("id");
                String myemail = rs.getString("email");
                String mypassword =rs.getString("password");
//            sessionMap.put("editcategory", obj_Category);
              
              if(myemail.equals(email) && mypassword.equals(password)){
             return "/dashboard.xhtml?faces-redirect=true" ;  
              }
              else{
                  
             return "/index.xhtml?faces-redirect=true" ;   
              }
        
            }
         }catch (Exception e) {
            System.out.println(e);
        }

     return email;
    }
    
    public boolean IsUserExist(){
           try{
             conn = Connectordb.connectDb();
              String sql ="select id,email,fullname from staff where email=  '" + email + "' or fullname=  '" + fullname + "' ";
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
     
    // get the list of all staff
    public List<UserManagedBean> getAllUsers(){  
       List allUsers = new ArrayList<UserManagedBean>();
         try{
             conn = Connectordb.connectDb();      
              String sqlstaff ="select *  from staff ";
               pst = conn.prepareStatement(sqlstaff);
              ResultSet rs = pst.executeQuery();             
              while(rs.next()){
                          UserManagedBean user = new UserManagedBean();       

                        user.setId(rs.getInt("id"));
                        user.setFullname(rs.getString("fullname"));
                        user.setEmail(rs.getString("email"));
                        user.setPhonenumber(rs.getString("phonenumber"));
                        user.setAddress(rs.getString("address"));
                        user.setDepartment(rs.getString("department"));
                        user.setCreated(rs.getString("created"));                      
                        allUsers.add(user);
                     }
                    }catch (Exception e) {
                     System.out.println(e);
                     }

        return allUsers;   
      }
    
    
        // Get the list of all storekeepers
          public List<UserManagedBean> getStorekeeper(){
            
            List storekeeper = new ArrayList<UserManagedBean>();
         
                try{
             conn = Connectordb.connectDb();
             String sql ="select *  from users ";
             PreparedStatement pststore = conn.prepareStatement(sql);
              ResultSet rs = pststore.executeQuery();
            while(rs.next()){
              
                UserManagedBean user = new UserManagedBean();
 
                        user.setFullname(rs.getString("fullname"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setCreated(rs.getString("created"));                      
                        storekeeper.add(user);
             }      
           }catch (Exception e) {
            System.out.println(e);
        }                
    return storekeeper;
    }
}



