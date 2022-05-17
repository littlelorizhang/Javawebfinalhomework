package dao;
import java.sql.*;
import java.util.ArrayList;
import utils.JDBCUtils;
import domain.User;

public class UserDao {
    //添加用户（这里是普通用户）
   public boolean insert(User user){
       Connection conn=null;
       PreparedStatement state=null;
       try{
           conn=JDBCUtils.getConnection();
           String sql="insert into homework.users values(?,?,?,?,?,?,?)";
           state=conn.prepareStatement(sql);
           state.setString(1, user.getUid());
           state.setString(2, user.getUname());
           state.setString(3, user.getUpassword());
           state.setString(4, user.getUemail());
           state.setString(5, user.getUrole());
           state.setString(6, user.getUaddress());
           state.setString(7,null);
           int num=state.executeUpdate();
           return num > 0;
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return false;
   }
   //查找
   public  User search(String x,String y ,String z) throws SQLException, ClassNotFoundException{
       Connection conn=null;
       PreparedStatement state=null;
       ResultSet rs=null;
       try{
           conn=JDBCUtils.getConnection();
           String sql="select * from homework.users where uname=? and upassword=? and urole=?";
           state= conn.prepareStatement(sql);
           state.setString(1,x);
           state.setString(2,y);
           state.setString(3,z);
           rs=state.executeQuery();
           while(rs.next()){
               User user=new User();
               user.setUid(rs.getString("uid"));
               user.setUname(rs.getString("uname"));
               user.setUpassword(rs.getString("upassword"));
               user.setUemail(rs.getString("uemail"));
               user.setUrole(rs.getString("urole"));
               user.setUaddress(rs.getString("uaddress"));
               user.setUtype(rs.getString("utype"));
               return user;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return null;
   }
   //删除销售人员
    public boolean delete(String uid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn=JDBCUtils.getConnection();
        String sql="update homework.users set utype=? , urole=? where uid=?";
        state= conn.prepareStatement(sql);
        state.setString(1,null);
        state.setString(2,"client");
        state.setString(3,uid);
        int num=state.executeUpdate();
        return num > 0;
    }
    //添加销售人员
    public boolean add(String uid,String utype) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn=JDBCUtils.getConnection();
        String sql="update homework.users set utype=? , urole=? where uid=? ";
        state= conn.prepareStatement(sql);
        state.setString(1,utype);
        state.setString(2,"employee");
        state.setString(3,uid);
        int num=state.executeUpdate();
        return num > 0;
    }
    //重置销售口令
    public boolean change(String uid,String upasswprd) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn=JDBCUtils.getConnection();
        String sql="update homework.users set upassword=? where uid=? and urole=?";
        state= conn.prepareStatement(sql);
        state.setString(1,upasswprd);
        state.setString(2,uid);
        state.setString(3,"employee");
        int num=state.executeUpdate();
        return num > 0;
    }
    //通过UID查找人员的地域
    public String region(String uid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        conn=JDBCUtils.getConnection();
        String sql="select uaddress from homework.users where uid=?";
        state= conn.prepareStatement(sql);
        state.setString(1,uid);
        rs=state.executeQuery();
        String regions = null;
        while(rs.next())
            regions= rs.getString("uaddress");
        return  regions;
    }
}
