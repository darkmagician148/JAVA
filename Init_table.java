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
import static javax.management.remote.JMXConnectorFactory.connect;

public class Init_table {
    private Statement statement;
    private PreparedStatement preparedStatement;
    private Connection connect;

    public Init_table (Connection connect) throws SQLException {
        this.connect = connect;
        this.statement = connect.createStatement();
    
        System.out.println("\n>>Database Connected");
        /* Bảng Account */
        try{
        statement.execute("use Java");
        statement.execute("create table ACC(username varchar(20) not null," +
                "password varchar(20) not null," +
                "Quyen int not null," +
                "primary key (username))");
        System.out.println("\n>>ACC table done !");

        /* Bang chuc vu */
        statement.execute("create table CHUCVU(IDChucVu varchar(20) not null," +
                "ChucVu varchar(30) not null unique," +
                "HSLuong float not null," +
                "Bonus float not null," +
                "PhuCap float not null," +
                "primary key (IDChucVu))");
        System.out.println("\n>>CHUCVU table done !");

        /* Bang phong ban */
        statement.execute("create table PHONGBAN(MaPhongBan varchar(20) not null," +
                "TenPhong varchar(50) not null unique," +
                "DienThoaiPhong varchar(15) not null unique," +
                "DiaChiPhongBan varchar(50) not null unique," +
                "primary key (MaPhongBan))");
        System.out.println("\n>>PHONGBAN table done !");

        /* Bang thong tin nhan vien */
        statement.execute("create table THONGTINNV(MaSoNV varchar(20) not null," +
                "username varchar(20) not null unique," +
                "HoTen varchar(30) not null," +
                "GioiTinh char(5)," +
                "NgaySinh DATE," +
                "DiaChi varchar(50)," +
                "IDChucVu varchar(20) not null," +
                "MaPhongBan varchar(20) not null," +
                "BHXH varchar(20)," +
                "primary key (MaSoNV)," +
                "foreign key (MaPhongBan) references PHONGBAN(MaPhongBan) on update cascade," +
                "foreign key (username) references ACC(username) on update cascade," +
                "foreign key (IDChucVu) references CHUCVU(IDChucVu) on update cascade)");
        System.out.println("\n>>THONGTINNV table done !");

        /* Bang cham cong */
        statement.execute("create table CHAMCONG(MaCong varchar(20) not null," +
                "Thang date not null," +
                "SoNgayLamViec int not null," +
                "SoGioLamViec int not null," +
                "MaSoNV varchar(20) not null," +
                "primary key(MaCong)," +
                "foreign key (MaSoNV) references  THONGTINNV(MaSoNV) on update cascade)");
        System.out.println("\n>>CHAMCONG table done !");
        }
        catch (SQLException e) {
            
            System.out.print("Database is already!!");
            
        }
        System.out.println(" All done !");
    }
}

    