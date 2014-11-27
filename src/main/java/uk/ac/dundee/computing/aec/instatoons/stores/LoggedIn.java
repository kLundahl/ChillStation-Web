/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instatoons.stores;

/**
 *
 * @author Administrator
 */
public class LoggedIn {
    boolean logedin=false;
    String Username=null;
    String fullName=null;
    String DoB=null;
    String Email=null;
    String Avatar=null;
    public void LogedIn(){
        
    }
    
    //Using the Login.java, it reads the data from the database and it fills all the variables, so it can pass them if they are needed
    
    public void setFullName(String name){
        this.fullName=name;
    }
    public String getFullName(){
        return fullName;
    }
    public void setUsername(String name){
        this.Username=name;
    }
    public String getUsername(){
        return Username;
    }
    public void setDoB(String birth){
        this.DoB=birth;
    }
    public String getDoB(){
        return DoB;
    }
    public void setEmail(String email){
        this.Email=email;
    }
    public String getEmail(){
        return Email;
    }
    public void setAvatar(String avatar){
        this.Avatar=avatar;
    }
    public String getAvatar(){
        return Avatar;
    }
    public void setLogedin(){
        logedin=true;
    }
    public void setLogedout(){
        logedin=false;
    }
    
    public void setLoginState(boolean logedin){
        this.logedin=logedin;
    }
    public boolean getlogedin(){
        return logedin;
    }
}
