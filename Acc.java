/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLNS;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Acc {
    private String username;
    private String password;
    private String quyen;

    public Acc(String username, String password, String quyen) {
        this.username = username;
        this.password = password;
        this.quyen = quyen;
    }
    public Acc(String username, String password){
        this.username = username;
        this.password = password;
        this.quyen = "0";
    }
    
    public Acc(){
        
    }
    @Override
    public String toString() {
        return "Acc{" + "username=" + username + ", password=" + password + ", quyen=" + quyen + '}';
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getQuyen() {
        return quyen;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public static boolean DangNhap(String username, String password, ArrayList<Acc> acc){
        int count = 0;
        for(count = 0; count < acc.size(); count ++){
            if(acc.get(count).getUsername().equals(username) && acc.get(count).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
}
