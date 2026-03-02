import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class users {
    private String id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String role;




    public void register(String id,
                         String fname,
                         String lname,
                         String email,
                         String password) {
        String sql = "insert into users  (id,fname,lname,email,password) values(?,?,?,?,?) ";
        try {
            PreparedStatement ps = shared.con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    String login(String email, String password) {
        try {
            String sql = "select role from users where email= ? and password=? ";
            PreparedStatement ps = shared.con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                return role;
            } else return null;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    void borrow(String id, String name) {
       // int avilable = books.is_avilable(name);

        try {
            if (5 > 0) {
                String sql1 = "select borrowed_books_count from users where id=?";
                PreparedStatement ps = shared.con.prepareStatement(sql1);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int limit = rs.getInt("borrowed_books_count");
                    if (limit > 4) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("limit exceed");
                        alert.showAndWait();
                    } else {
                        int id_book = books.get_id(name);
                        String insert = "insert into userbooks (user_id,Bid) values (?,?)";
                        PreparedStatement ps1 = shared.con.prepareStatement(insert);
                        ps1.setString(1, id);
                        ps1.setInt(2, id_book);
                        ps1.executeUpdate();
                        String update = "update users set borrowed_books_count=borrowed_books_count+1 where id=? ";
                        PreparedStatement ps2 = shared.con.prepareStatement(update);
                        ps2.setString(1, id);

                        ps2.executeUpdate();

                        String update2 = "update books set available_copies=available_copies-1 where id=?";
                        PreparedStatement ps3 = shared.con.prepareStatement(update2);
                        ps3.setInt(1, id_book);
                        ps3.executeUpdate();

                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("id not valid");
                    alert.showAndWait();
                }
            } else { Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("book not found");
                alert.showAndWait();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;

    }
    String format(String s){
        while (s.length()<40){
            s+=" ";

        }
    return  s; }

    public String[] showBooks(String cat) throws SQLException {
        if (cat == null) return new String[0];

        List<String> list = new ArrayList<>();
           list.add(format("name")+"  |  "+format("author")+"  |  "+ "available_copies");
           list.add("--------------------------------------------------------------------------------------------");
        if (!cat.equals("all")) {
            String sql1 = "SELECT name, author, available_copies FROM books WHERE category = ?";
            PreparedStatement ps = shared.con.prepareStatement(sql1);
            ps.setString(1, cat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("available_copies") == 0) continue;
                list.add(format(rs.getString("name") )+"  |  " + format(rs.getString("author") )+ "  |  " + rs.getInt("available_copies"));
                list.add("-----------------------------------------------------------------------------------------");
            }
        } else {
            String sql = "SELECT name, author, available_copies FROM books";
            Statement stmt = shared.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("available_copies") == 0) continue;
                list.add(format(rs.getString("name") )+"  |  " + format(rs.getString("author") )+ "  |  " + rs.getInt("available_copies"));
                list.add("------------------------------------------------------------------------------------------");
            }
        }

        return list.toArray(new String[0]);
    }
    static boolean is_email_found(String em) {
        try {
            String sqlid = "select * from users where email=?";
            PreparedStatement ps = shared.con.prepareStatement(sqlid);
            ps.setString(1, em);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static boolean is_user_found(String id) {
        try {
            String sqlid = "select * from users where id=?";
            PreparedStatement ps = shared.con.prepareStatement(sqlid);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
