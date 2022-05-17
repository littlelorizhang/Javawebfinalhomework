package utils;
import java.sql.*;

public class JDBCUtils {
    //加载驱动，并建立数据库连接
    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://120.25.154.59:3306/homework";//这里是宝塔Linux里面的数据库不是本地的
        //String url = "jdbc:mysql://localhost:3306"; //这里是本地的
        String username = "homework";//宝塔
        String password = "123456";//宝塔
        //String username = "root";//本地
        //String password = "Zly12345";//本地

        Connection conn=null;
        conn = DriverManager.getConnection(url, username,
                password);
        return conn;
    }
    // 关闭数据库连接，释放资源
    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    public static void release(ResultSet rs, Statement stmt,
                               Connection conn){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        release(stmt, conn);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql ="select * from homework.product";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString("pid"));
        }
    }

}

