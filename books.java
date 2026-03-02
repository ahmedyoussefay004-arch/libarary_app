import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class books {
    private int id;
    private String name;
    private String author;
    private String catogory;
    private int avilable_copies;


  static int is_avilable(String name){
      try{  String sql ="select  available_copies from books where name=? ";
        PreparedStatement ps=shared.con.prepareStatement(sql);
        ps.setString(1,name);
        ResultSet rs =ps.executeQuery();
        if(rs.next()){
            int avialable=rs.getInt("available_copies");
            if(avialable>0) return avialable;
            else  return 0;
        } else return 0;
    } catch (SQLException e){  throw new RuntimeException(e);}}
   static int get_id(String name){
        try {
            String sql = "select id from books where name=?";
            PreparedStatement ps = shared.con.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
            int id =rs.getInt("id");

            return id;}
            else return 0;
        } catch (SQLException e){  throw new RuntimeException(e);}
   }

}
