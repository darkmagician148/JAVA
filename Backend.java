/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLNS;

/**
 *
 * @author admin
 */

import static QLNS.ConnectionSQL.getConnection;
import java.sql.*;
import java.util.ArrayList;

public class Backend {
    private Statement statement;
    private PreparedStatement preparedStatement;
    private Connection connect;

    public Backend() throws SQLException, ClassNotFoundException {
        this.connect = getConnection();
        this.statement = connect.createStatement();
    }
    

    /**
     *
     *  For both staff and admin
     *
     * */

    /**
     * Chuc nang tra cuu, tim kiem thong tin
     */

    /**
     * Chuc nang tra cuu, tim kiem thong tin
     * @param idPhongBan
     */

    /**
     * Chuc nang tra cuu, tim kiem thong tin
     * @param idPhongBan
     * @return
     */
    public ArrayList<ArrayList<Object>> thong_tin_phong_ban(Object idPhongBan) {

        ArrayList<ArrayList<Object>> ttpb = new ArrayList<>();

        try {
            /* DDL */
            this.preparedStatement = connect.prepareStatement("select * from PHONGBAN where MaPhongBan=?");

            this.preparedStatement.setString(1, idPhongBan.toString());
            ResultSet resultSet = this.preparedStatement.executeQuery();

            /* Array to store thong tin phong ban */
            ArrayList<Object> thong_tin_pb = new ArrayList<>();

            /* write from database to array */
            while (resultSet.next()) {
                thong_tin_pb.add(resultSet.getString(1));
                thong_tin_pb.add(resultSet.getString(2));
                thong_tin_pb.add(resultSet.getString(3));
                thong_tin_pb.add(resultSet.getString(4));
            }
            resultSet.close();

            /* throw exception if MaPhongBan not exist in database --> array is empty */
            if (thong_tin_pb.isEmpty()) {
                throw new ValueNotAvailableInDatabase();
            } else {
                this.preparedStatement = connect.prepareStatement("select MaSoNV, username, HoTen " +
                        "from THONGTINNV inner join PHONGBAN on THONGTINNV.MaPhongBan=PHONGBAN.MaPhongBan " +
                        "where THONGTINNV.MaPhongBan=?");
                this.preparedStatement.setString(1, idPhongBan.toString());
                ResultSet resultSet1 = this.preparedStatement.executeQuery();

                /* Array to store column by column from data in database */
                ArrayList<Object> ms_nv_thuoc_phong_ban = new ArrayList<>();
                ArrayList<Object> username_nv_thuoc_phong_ban = new ArrayList<>();
                ArrayList<Object> hoten_nv_thuoc_phong_ban = new ArrayList<>();

                while (resultSet1.next()) {
                    ms_nv_thuoc_phong_ban.add(resultSet1.getString(1));
                    username_nv_thuoc_phong_ban.add(resultSet1.getString(2));
                    hoten_nv_thuoc_phong_ban.add(resultSet1.getString(3));
                }

                ttpb.add(thong_tin_pb);
                ttpb.add(ms_nv_thuoc_phong_ban);
                ttpb.add(username_nv_thuoc_phong_ban);
                ttpb.add(hoten_nv_thuoc_phong_ban);

                resultSet1.close();
            }

        } catch (SQLException e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("!! Query Error !!");
            ttpb.add(errMessage);
        } catch (ValueNotAvailableInDatabase e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("Ma So Phong Ban does not exist !!");
            ttpb.add(errMessage);
        }

        return ttpb;
    }

    public NhanVien thong_tin_nhan_vien(String input /* username or HoTen or MaSoNV */) {

        NhanVien ttnv = new NhanVien();

        try {
            /* DDL */
            this.preparedStatement = connect.prepareStatement("select * from THONGTINNV where ? in(MaSoNV, HoTen, username)");

            preparedStatement.setString(1, input);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ttnv.setMsnv(resultSet.getString(1));
                ttnv.setUsername(resultSet.getString(2));
                ttnv.setName(resultSet.getString(3));
                ttnv.setGender(resultSet.getString(4));
                ttnv.setBirth(resultSet.getString(5));
                ttnv.setAddr(resultSet.getString(6));
                ttnv.setIDjob(resultSet.getString(7));
                ttnv.setMspb(resultSet.getString(8));
                ttnv.setBhxh(resultSet.getString(9));
            }

            resultSet.close();

            /* Throw exception if Nhan Vien not available in database */
            

        } catch (SQLException e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("!! Query Error !!");
        }

        return ttnv;
    }
    public ArrayList<NhanVien> Show_nhan_vien() throws SQLException {

        ArrayList<NhanVien> NV = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from THONGTINNV ");
        try {
            /* DDL */
                while (resultSet.next()) {
                    NhanVien std = new NhanVien(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)
                        ,resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                NV.add(std);
            }           
        } 
        catch (SQLException e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("!! Query Error !!");
          
        }
        resultSet.close();
        return NV;
    }
    public ArrayList<Acc> Accout() {

        ArrayList<Acc> acc = new ArrayList<>();
        try {
            /* DDL */
            ResultSet resultSet = statement.executeQuery("select * from ACC ");
            while (resultSet.next()) {
                Acc std = new Acc(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                acc.add(std);
            }
            resultSet.close();
        } catch (SQLException e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("!! Query Error !!");
          
        }
       
        return acc;
    }
    


    public ArrayList<ArrayList<Object>> thong_tin_chuc_vu(Object idChucVu /* id chuc vu */) {

        ArrayList<ArrayList<Object>> ttcv = new ArrayList<>();

        try {
            /* DDL */
            this.preparedStatement = connect.prepareStatement("select THONGTINNV.IDChucVu, THONGTINNV.MaSoNV, THONGTINNV.HoTen, " +
                    "THONGTINNV.MaPhongBan, PHONGBAN.TenPhong, PHONGBAN.DienThoaiPhong, PHONGBAN.DiaChiPhongBan" +
                    " from THONGTINNV inner join PHONGBAN on THONGTINNV.MaPhongBan=PHONGBAN.MaPhongBan where THONGTINNV.IDChucVu=?");

            preparedStatement.setString(1, idChucVu.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Object> idchucvu = new ArrayList<>();
            ArrayList<Object> msnv = new ArrayList<>();
            ArrayList<Object> hoten = new ArrayList<>();
            ArrayList<Object> mpb = new ArrayList<>();
            ArrayList<Object> tenphong = new ArrayList<>();
            ArrayList<Object> sdt = new ArrayList<>();
            ArrayList<Object> addr = new ArrayList<>();

            while (resultSet.next()) {
                idchucvu.add(resultSet.getString(1));
                msnv.add(resultSet.getString(2));
                hoten.add(resultSet.getString(3));
                mpb.add(resultSet.getString(4));
                tenphong.add(resultSet.getString(5));
                sdt.add(resultSet.getString(6));
                addr.add(resultSet.getString(7));
            }

            resultSet.close();

            if (idchucvu.isEmpty()) {
                throw new ValueNotAvailableInDatabase();
            } else {
                ttcv.add(idchucvu);
                ttcv.add(msnv);
                ttcv.add(hoten);
                ttcv.add(mpb);
                ttcv.add(tenphong);
                ttcv.add(sdt);
                ttcv.add(addr);
            }

        } catch (SQLException e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("!! Query Error !!");
            ttcv.add(errMessage);
        } catch (ValueNotAvailableInDatabase e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("Chuc Vu does not exist !!");
            ttcv.add(errMessage);
        }
        return ttcv;
    }

    public ArrayList<ArrayList<Object>> thong_tin_luong(Object input /* MaCong, MaSoNV */) {

        ArrayList<ArrayList<Object>> ttl = new ArrayList<>();

        try {
            /* DDL */
            this.preparedStatement = connect.prepareStatement("select THONGTINNV.MaSoNV, CHAMCONG.MaCong, CHAMCONG.Thang," +
                    " CHAMCONG.SoNgayLamViec, CHAMCONG.SoGioLamViec, CHUCVU.HSLuong, CHUCVU.Bonus, CHUCVU.PhuCap" +
                    " from CHAMCONG inner join THONGTINNV on THONGTINNV.MaSoNV=CHAMCONG.MaSoNV" +
                    " inner join CHUCVU on THONGTINNV.IDChucVu=CHUCVU.IDChucVu" +
                    " where ? in (CHAMCONG.MaCong, CHAMCONG.MaSoNV)");

            preparedStatement.setString(1, input.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Object> msnv = new ArrayList<>();
            ArrayList<Object> mc = new ArrayList<>();
            ArrayList<Object> thang = new ArrayList<>();
            ArrayList<Object> day = new ArrayList<>();
            ArrayList<Object> hour = new ArrayList<>();
            ArrayList<Object> hsl = new ArrayList<>();
            ArrayList<Object> bonus = new ArrayList<>();
            ArrayList<Object> phucap = new ArrayList<>();

            while (resultSet.next()) {
                msnv.add(resultSet.getString(1));
                mc.add(resultSet.getString(2));
                thang.add(resultSet.getString(3));
                day.add(resultSet.getInt(4));
                hour.add(resultSet.getInt(5));
                hsl.add(resultSet.getFloat(6));
                bonus.add(resultSet.getFloat(7));
                phucap.add(resultSet.getFloat(8));
            }

            resultSet.close();

            if (msnv.isEmpty()) {
                throw new ValueNotAvailableInDatabase();
            } else {
                ttl.add(msnv);
                ttl.add(mc);
                ttl.add(thang);
                ttl.add(day);
                ttl.add(hour);
                ttl.add(hsl);
                ttl.add(bonus);
                ttl.add(phucap);
            }

        } catch (SQLException e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("!! Query Error !!");
            ttl.add(errMessage);
        } catch (ValueNotAvailableInDatabase e) {
            ArrayList<Object> errMessage = new ArrayList<>();
            errMessage.add("Ma Cong or Ma So Nhan Vien does not exist !!");
            ttl.add(errMessage);
        }

        return ttl;
    }

    /**
     * Chuc nang sua doi, chinh sua databases
     */

    /**
     * suppose that front-end restricts staff must enter Ho ten into text filed or whatever to take input parameter for these functions
     * */

    /* a support function, not available in front end */
    public void xoa_tai_khoan(Object username, Object password) {
        try {
            this.preparedStatement = connect.prepareStatement("delete from ACC where username=?");
            this.preparedStatement.setString(1, (String) username);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
 
    // xoa tai khoan
    public void delete(String Msnv) throws SQLException, ClassNotFoundException {
        NhanVien nv = new Backend().thong_tin_nhan_vien(Msnv);
        try {
            // xoa trong bang cham cong
            this.preparedStatement = connect.prepareStatement("delete from CHAMCONG where MaSoNV=?");
            this.preparedStatement.setString(1, nv.getMsnv() );
            this.preparedStatement.executeUpdate();
            // xoa han
            this.preparedStatement = connect.prepareStatement("delete from THONGTINNV where MaSoNV=?");
            this.preparedStatement.setString(1, nv.getMsnv() );
            this.preparedStatement.executeUpdate();
            // xoa acc
            this.preparedStatement = connect.prepareStatement("delete from ACC where username=?");
            this.preparedStatement.setString(1, nv.getUsername());
            this.preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Delete succefull");
    }

    public Object dang_nhap(Object username, Object password) {

        Object quyen = null;

        try {
            this.preparedStatement = this.connect.prepareStatement("select * from ACC where username=?");

            this.preparedStatement.setString(1, (String) username);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            String current_username = "";
            String current_password = "";


            while (resultSet.next()) {
                current_username = resultSet.getString(1);
                current_password = resultSet.getString(2);
                quyen = resultSet.getInt(3);
            }

            resultSet.close();

            if (current_username.isEmpty())
                throw new ValueNotAvailableInDatabase();

            if (!current_password.equals(password.toString()))
                throw new VerifyFail();

        } catch (SQLException e) {
            return "!! Query Error !!";
        } catch (ValueNotAvailableInDatabase e) {
            return "Username does not exist !!";
        } catch (VerifyFail e) {
            return "Password is wrong !!";
        }

        return quyen;
    }

    public Object sua_tai_khoan(Object old_username, Object old_password, Object new_username, Object new_password) {
        try {
            /* Verify old username and password before change */
            this.preparedStatement = this.connect.prepareStatement("select username, password from ACC where username=?");

            this.preparedStatement.setString(1, (String) old_username);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            String current_username = "";
            String current_password = "";

            while (resultSet.next()) {
                current_username = resultSet.getString(1);
                current_password = resultSet.getString(2);
            }

            resultSet.close();

            if (current_username.isEmpty()) {
                throw new ValueNotAvailableInDatabase();
            } else if (!current_password.equals(old_password.toString())) {
                throw new VerifyFail();
            } else {
                this.preparedStatement = this.connect.prepareStatement("update ACC " +
                        "set username=?, password=? where username=?");

                this.preparedStatement.setString(1, (String) new_username);
                this.preparedStatement.setString(2, (String) new_password);
                this.preparedStatement.setString(3, (String) old_username);

                this.preparedStatement.executeUpdate();
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            return "New username already exist !!!";
        } catch (ValueNotAvailableInDatabase e) {
            return "Username does not exist !!";
        } catch (VerifyFail e) {
            return "Password is wrong";
        } catch (SQLException e) {
            return "!! Query & Update Error !!";
        }
        return "Successful";
    }

    public Object sua_thong_tin_nhan_vien_staff(Object maSoNhanVien, Object Hoten, Object gender, Object ngaySinh, Object address, Object BHXH) {
        try {
            /* check if Ma So NV exist in database */
            this.preparedStatement = this.connect.prepareStatement("select MaSoNV from THONGTINNV where MaSoNV=?");

            this.preparedStatement.setString(1, maSoNhanVien.toString());
            ResultSet resultSet = this.preparedStatement.executeQuery();

            String current_msnv = "";
            while (resultSet.next())
                current_msnv = resultSet.getString(1);

            resultSet.close();

            if (current_msnv.isEmpty())
                throw new ValueNotAvailableInDatabase();
            else {
                this.preparedStatement = connect.prepareStatement("update THONGTINNV " +
                        "set HoTen=?," +
                        "GioiTinh=?," +
                        "NgaySinh=?," +
                        "DiaChi=?," +
                        "BHXH=? " +
                        "where MaSoNV=?");

                this.preparedStatement.setString(1, Hoten.toString());
                this.preparedStatement.setString(2, gender.toString());
                this.preparedStatement.setString(3, ngaySinh.toString());
                this.preparedStatement.setString(4, address.toString());
                this.preparedStatement.setString(5, BHXH.toString());
                this.preparedStatement.setString(6, maSoNhanVien.toString());

                this.preparedStatement.executeUpdate();
            }
        } 
        catch (ValueNotAvailableInDatabase e) {
            return "Nhan Vien does not exist !!";
        } 
        catch (SQLException e) {
            return "!! Query & Update Error !!";
        }
        return "Successful";
    }

    /**
     * only for database admin
     * @param acc
     */
    public String Creat_NV (Acc acc, NhanVien nv){
        try {
            this.preparedStatement = connect.prepareStatement("insert into ACC values(?,?,?)");

            this.preparedStatement.setString(1, acc.getUsername());
            this.preparedStatement.setString(2, acc.getPassword());
            this.preparedStatement.setString(3, acc.getQuyen());

            this.preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException ex) {
           System.out.print("Username already exist !!" );
        } catch (SQLException e) {
            System.out.print("!! acc Update Error !!");
        }try {
            this.preparedStatement = connect.prepareStatement("insert into THONGTINNV values(?,?,?,?,?,?,?,?,?)");
            this.preparedStatement.setString(1, nv.getMsnv());
            this.preparedStatement.setString(2, nv.getUsername());
            this.preparedStatement.setString(3, nv.getName());
            this.preparedStatement.setString(4, nv.getGender());
            this.preparedStatement.setString(5,nv.getBirth());
            this.preparedStatement.setString(6, nv.getAddr());
            this.preparedStatement.setString(7, nv.getIDjob());
            this.preparedStatement.setString(8, nv.getMspb());
            this.preparedStatement.setString(9, nv.getBhxh());
            
            this.preparedStatement.executeUpdate();

            /* any error --> delete account is just created */
        } catch (SQLIntegrityConstraintViolationException e) {
            xoa_tai_khoan(acc.getUsername(), acc.getPassword());
            return """
                   Ma So Nhan Vien Or Username already exists !!!
                   Or ID Chuc Vu and Ma Phong Ban doesn't exist !!""";
        } catch (SQLException e) {
             xoa_tai_khoan(acc.getUsername(), acc.getPassword());
             System.out.print(e);
            return "!! nv Update Error !!";
        }
        return "Successful";
        
    }
    public Object them_nhan_vien_moi(Object[] account_nv_moi, Object[] nhan_vien_moi) {
        /* insert ACC first */
        try {
            this.preparedStatement = connect.prepareStatement("insert into ACC values(?,?,?)");

            this.preparedStatement.setString(1, account_nv_moi[0].toString());
            this.preparedStatement.setString(2, account_nv_moi[1].toString());
            this.preparedStatement.setInt(3, (Integer) account_nv_moi[2]);

            this.preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException ex) {
            return "Username already exist !!";
        } catch (SQLException e) {
            return "!! Update Error !!";
        }

        /* insert TTNV */
        try {
            this.preparedStatement = connect.prepareStatement("insert into THONGTINNV values(?,?,?,?,?,?,?,?,?)");
            for (int i = 0; i < nhan_vien_moi.length; i++) {
                if (nhan_vien_moi[i] == null)
                    this.preparedStatement.setString(i + 1, null);
                else
                    this.preparedStatement.setString(i + 1, nhan_vien_moi[i].toString());
            }

            this.preparedStatement.executeUpdate();

            /* any error --> delete account is just created */
        } catch (SQLIntegrityConstraintViolationException e) {
            xoa_tai_khoan(account_nv_moi[0], account_nv_moi[1]);
            return "Ma So Nhan Vien Or Username already exists !!!" +
                    "\nOr ID Chuc Vu and Ma Phong Ban doesn't exist !!";
        } catch (SQLException e) {
            xoa_tai_khoan(account_nv_moi[0], account_nv_moi[1]);
            return "!! Update Error !!";
        }
        return "Successful";
    }

    public Object sua_thong_tin_nhan_vien_admin(Object MaSoNV, Object IDChucVu_new, Object MaPhongBan_new) {
        try {
            /* check if Ma So NV not in database */
            this.preparedStatement = this.connect.prepareStatement("select MaSoNV from THONGTINNV where MaSoNV=?");

            this.preparedStatement.setString(1, (String) MaSoNV);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            String msnv = "";
            while (resultSet.next())
                msnv = resultSet.getString(1);

            if (msnv.isEmpty())
                throw new ValueNotAvailableInDatabase();
            else {
                this.preparedStatement = this.connect.prepareStatement("update THONGTINNV " +
                        "set IDChucVu=?," +
                        "MaPhongBan=?" +
                        " where ? in (MaSoNV)");

                this.preparedStatement.setString(1, (String) IDChucVu_new);
                this.preparedStatement.setString(2, (String) MaPhongBan_new);
                this.preparedStatement.setString(3, (String) MaSoNV);

                this.preparedStatement.executeUpdate();
            }
        } catch (ValueNotAvailableInDatabase e) {
            return "Ma So Nhan Vien already exists !!";
        } catch (SQLIntegrityConstraintViolationException e) {
            return "ID Chuc Vu or Ma Phong Ban doesn't exist !!!";
        } catch (SQLException e) {
            return "!! Update Error !!";
        }
        return "Successful";

    }

    public Object cham_cong_theo_thang(Object thang, Object maSoNV, int soNgayLV, int soGioLV) {
        try {
            /* take the count of Ma Cong to gen a new Ma COng */
            int count = 0;
            ResultSet resultSet = this.statement.executeQuery("select count(*) from CHAMCONG");
            while (resultSet.next())
                count = resultSet.getInt(1);

            resultSet.close();

            /* check if the staff already has salary for this month */
            this.preparedStatement = this.connect.prepareStatement("select Thang, MaSoNV from CHAMCONG where Thang=?");

            this.preparedStatement.setString(1, thang.toString());

            ResultSet resultSet1 = this.preparedStatement.executeQuery();

            String month = "";
            String msnv = "";

            while (resultSet1.next()) {
                month = resultSet1.getString(1);
                msnv = resultSet1.getString(2);
            }

            resultSet.close();

            if(msnv.isEmpty())
                throw new ValueNotAvailableInDatabase();

            if (month.equals(thang.toString()) && msnv.equals(maSoNV.toString()))
                throw new TwiceAMonth();

            /* Cham Cong */
            this.preparedStatement = connect.prepareStatement("insert into CHAMCONG values(?,?,?,?,?)");

            this.preparedStatement.setString(1, ("cc" + String.valueOf(count + 1)));
            this.preparedStatement.setString(2, (String) thang);
            this.preparedStatement.setInt(3, soNgayLV);
            this.preparedStatement.setInt(4, soGioLV);
            this.preparedStatement.setString(5, (String) maSoNV);

            this.preparedStatement.executeUpdate();

        } catch (ValueNotAvailableInDatabase e) {
            return "Ma So Nhan Vien doesn't exist !!";
        } catch (SQLException e) {
            return "!! Update error !!";
        } catch (TwiceAMonth e) {
            return "This staff already has salary for " + String.valueOf(thang);
        }
        return "Successful";
    }

    public Object them_phong_ban(Object tenPhong, Object sdt, Object diaChi) {
        try {
            /* count row to gen the next Ma Phong Ban */
            int count = 0;
            ResultSet resultSet = this.statement.executeQuery("select count(*) from PHONGBAN");

            while (resultSet.next())
                count = resultSet.getInt(1);

            resultSet.close();

            this.preparedStatement = this.connect.prepareStatement("insert into PHONGBAN values(?,?,?,?)");

            this.preparedStatement.setString(1, "pb" + String.valueOf(count + 1));
            this.preparedStatement.setString(2, (String) tenPhong);
            this.preparedStatement.setString(3, (String) sdt);
            this.preparedStatement.setString(4, (String) diaChi);

            this.preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Ten Phong Ban, SDT and Dia Chi Phong Ban can not be duplicated !!";
        } catch (SQLException e) {
            return "!! Update Error !!";
        }
        return "Successful";
    }

    public Object them_chuc_vu(Object tenChucVU, double hsLuong, double Bonus, double phuCap) {
        try {
            if (hsLuong == 0)
                return "He So Luong cannot equal 0 !!";

            /* count to gen new ID Chuc Vu */
            int count = 0;
            ResultSet resultSet = this.statement.executeQuery("select count(*) from CHUCVU");
            while (resultSet.next())
                count = resultSet.getInt(1);

            resultSet.close();

            /* insert new Chuc vu */
            this.preparedStatement = connect.prepareStatement("insert into CHUCVU values(?,?,?,?,?)");

            this.preparedStatement.setString(1, ("cv" + String.valueOf(count + 1)));
            this.preparedStatement.setString(2, tenChucVU.toString());
            this.preparedStatement.setDouble(3, hsLuong);
            this.preparedStatement.setDouble(4, Bonus);
            this.preparedStatement.setDouble(5, phuCap);

            this.preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Ten Chuc Vu already exists !!";
        } catch (SQLException e) {
            return "!! Update Error !!";
        }
        return "Successful";
    }

    private static class ValueNotAvailableInDatabase extends Exception {

        public ValueNotAvailableInDatabase() {
            super();
        }
    }

    private static class TwiceAMonth extends Exception {

        public TwiceAMonth() {
            super();
        }
    }

    private static class MysqlDataTruncation extends Exception{

        public MysqlDataTruncation() {
            super();
        }
    }

    private static class VerifyFail extends Exception{

        public VerifyFail() {
            super();
        }
    }
}