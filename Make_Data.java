/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLNS;

/**
 *
 * @author admin
 */
import java.sql.*;
import java.util.Random;

public class Make_Data {

    private Statement statement;
    private PreparedStatement preparedStatement;
    private Connection connect;

    public Make_Data(Connection connect) throws SQLException, ClassNotFoundException {
        this.connect = connect;
        this.statement = connect.createStatement();
        Backend be = new Backend();
        /** Data pool for attributes of tables */
        /* Bang Chuc Vu */
        try{
        be.Creat_NV(new Acc("admin","admin","1"), new NhanVien("VIP","admin","Nguyen Tuan", "Nam", " ", " ", "cv7", "pb1", " "));
        System.out.println("\n>>Database Connected");
        String[] idCVpool = {"cv1", "cv2", "cv3", "cv4", "cv5", "cv6", "cv7", "cv8", "cv9",
                "cv10", "cv11", "cv12", "cv13"};
        String[] chucVupool = {"DataBase Developer", "Database Administrator", "BackEnd Developer",
                "FrontEnd Developer", "Tester", "QA", "Business Analyst", "Project Manager", "DevOPS",
                "Data Engineer", "Network Engineer", "Network Administrator", "IT guy"};
        double[] HSLuong = {2.3, 2.4, 3.1, 1.8, 1.4, 1.7, 2, 3.1, 2.7, 1.9, 2.5, 2.2, 4.5};
        double[] bonus = {3000000, 3200000, 3400000, 1900000, 1700000, 3000000,
                5000000, 3400000, 1200000, 1950000, 2300000, 2800000, 5000000};
        double[] phuCap = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        /* Bang Acc */
        String[] usernamePool = new String[150];
        String[] password = new String[150];
        for (int i = 0; i < usernamePool.length; i++) {
            usernamePool[i] = "acc" + String.valueOf((i + 1));
            password[i] = "password" + String.valueOf((i + 1));
        }

        /* Phong Ban table data pool */
        String[] idPB = {"pb1", "pb2", "pb3", "pb4"};
        String[] tenPhong = {"The One", "The Two", "The Three", "The Four"};
        String[] sdt = {"012345678", "01239876", "0987654321", "0088112756"};
        String[] addrPB = {"D9-300", "D8-001", "D5-011", "C9-110"};

        /* Cham Cong table data pool */
        // make Ma Cong for 2 months
        String[] idCong = new String[300];
        for (int i = 0; i < 300; i++) {
            idCong[i] = "cc" + String.valueOf((i + 1));
        }

        String[] thang = {"2022-01-01", "2022-01-02"};

        /* Bang TTNV */
        String[] msNVpool = new String[150];
        for (int i = 0; i < 150; i++) {
            msNVpool[i] = "nv" + String.valueOf((i + 1));
        }
        String[] ho = {"Nguyen", "Bui", "Tran", "Le", "Vu", "Hoang"}; // so then matching each ho with alphabet character
        String[] hoVaTen = new String[150];
        int index = 0;
        for (int i = 0; i < ho.length; i++) {
            for (char c = 'A'; c < 'Z'; c++) {
                hoVaTen[index] = ho[i] + " " + String.valueOf(c);
                index += 1;
            }
        }
        String[] gender = {"Nam", "Nu", "Other"};
        Random random = new Random();

        /* Insert into ACC table */
        int i = 0;
        preparedStatement = connect.prepareStatement("insert into ACC values(?,?,?)");
        while (i != msNVpool.length) { // each person just one account
            preparedStatement.setString(1, usernamePool[i]);
            preparedStatement.setString(2, password[i]);
            preparedStatement.setInt(3, 0);
            preparedStatement.executeUpdate();
            i++;
        }
        System.out.println("\n>>Make data for ACC table successfully !!");

        /* Insert into CHUCVU table */
        i = 0;
        preparedStatement = connect.prepareStatement("insert into CHUCVU values(?,?,?,?,?)");
        while (i != idCVpool.length) {
            preparedStatement.setString(1, idCVpool[i]);
            preparedStatement.setString(2, chucVupool[i]);
            preparedStatement.setDouble(3, HSLuong[i]);
            preparedStatement.setDouble(4, bonus[i]);
            preparedStatement.setDouble(5, phuCap[i]);
            preparedStatement.executeUpdate();
            i++;
        }
        System.out.println("\n>>Make data for CHUCVU table successfully !!");

        /* Insert into PHONGBAN table */
        i = 0;
        preparedStatement = connect.prepareStatement("insert into PHONGBAN values(?,?,?,?)");
        while (i != idPB.length) {
            preparedStatement.setString(1, idPB[i]);
            preparedStatement.setString(2, tenPhong[i]);
            preparedStatement.setString(3, sdt[i]);
            preparedStatement.setString(4, addrPB[i]);
            preparedStatement.executeUpdate();
            i++;
        }
        System.out.println("\n>>Make data for PHONGBAN table successfully !!");

        /* Insert into TTNV table */
        i = 0;
        preparedStatement = connect.prepareStatement("insert into THONGTINNV(MaSoNV, username, HoTen, GioiTinh, IDChucVu, MaPhongBan) values(?,?,?,?,?,?)");
        while (i != msNVpool.length) {
            preparedStatement.setString(1, msNVpool[i]);
            preparedStatement.setString(2, usernamePool[i]);
            preparedStatement.setString(3, hoVaTen[i]);
            preparedStatement.setString(4, gender[random.nextInt(gender.length)]);
            preparedStatement.setString(5, idCVpool[random.nextInt(idCVpool.length)]);
            preparedStatement.setString(6, idPB[random.nextInt(idPB.length)]);
            preparedStatement.executeUpdate();
            i++;
        }
        System.out.println("\n>>Make data for THONGTINNV successfully !!");

        /* Insert into ChamCong */
        i = 0;
        int j = 0; // table list Cham Cong of each staff in 2 months
        preparedStatement = connect.prepareStatement("insert into CHAMCONG values(?,?,?,?,?)");
        while (i != thang.length * msNVpool.length) {
            for (int t = 0; t < thang.length; t++) {
                preparedStatement.setString(1, idCong[i]);
                preparedStatement.setString(2, thang[t]);
                // suppose each staff work minimum 15 days, max 22 days (each week has two break days)
                preparedStatement.setInt(3, random.nextInt(8) + 15);
                // suppose each day staff must work maximum 8 hours with no OT be determined
                preparedStatement.setInt(4, random.nextInt(8) + 1);
                preparedStatement.setString(5, msNVpool[j]);
                preparedStatement.executeUpdate();
                i++;
            }
            j++;
        }
        }
        catch(Exception ex){
            System.out.print(ex);
        }
        
        System.out.println("\n>>Make data for CHAMCONG table successfully !!");
    }
}