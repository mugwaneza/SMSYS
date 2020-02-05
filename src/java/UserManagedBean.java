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
    
    Connection conn = null;
    PreparedStatement pst =null;
            
            
    

   private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
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
               
        return "/dashboard.xhtml?faces-redirect=true" ;  
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
              sessionMap.put("mysessionid", myid);          
             return "/register.xhtml?faces-redirect=true" ;  
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
    
    }



