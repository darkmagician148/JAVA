/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLNS;

/**
 *
 * @author admin
 */
public class NhanVien {
    private String msnv;
    private String username;
    private String name;
    private String gender;
    private String birth;
    private String addr;
    private String IDjob;
    private String mspb;
    private String bhxh;

    public NhanVien(String msnv, String username, String name, String gender, String birth, String addr, String IDjob, String mspb, String bhxh) {
        this.msnv = msnv;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.addr = addr;
        this.IDjob = IDjob;
        this.mspb = mspb;
        this.bhxh = bhxh;
    }

    public NhanVien() {
        
    }

    public String getMsnv() {
        return msnv;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getAddr() {
        return addr;
    }

    public String getIDjob() {
        return IDjob;
    }

    public String getMspb() {
        return mspb;
    }

    public String getBhxh() {
        return bhxh;
    }

    public void setMsnv(String msnv) {
        this.msnv = msnv;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setIDjob(String IDjob) {
        this.IDjob = IDjob;
    }

    public void setMspb(String mspb) {
        this.mspb = mspb;
    }

    public void setBhxh(String bhxh) {
        this.bhxh = bhxh;
    }

    @Override
    public String toString() {
        return "NhanVien : \n" + "msnv= " + msnv + "\n username= " + username + "\n name= " + name +
                "\n gender= " + gender + "\n birth= " + birth + "\n addr= " + addr + "\n IDjob= " + IDjob + "\n mspb=" + mspb + "\n bhxh= " + bhxh ;
    }
    
}
