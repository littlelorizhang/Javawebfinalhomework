package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.Cart;
import utils.JDBCUtils;
public class CartDao {
    //添加商品
    public Boolean insert(Cart cart) throws SQLException, ClassNotFoundException {
        String sql = "insert into homework.cart values(?,?,?,?,?,?)";
        Connection conn=JDBCUtils.getConnection();
        PreparedStatement state = null;


        boolean f = false;

        try{
            state= conn.prepareStatement(sql);
            state.setString(1, cart.getCid());
            state.setString(2, cart.getC_uid());
            state.setInt(3,cart.getCnum());
            state.setInt(4,cart.getCprice());
            state.setString(5, cart.getC_pname());
            state.setString(6, cart.getC_pid());
            state.execute();
            return true;


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(state,conn);
        }

        return f;
    }
    //根据用户id展示购物车
    public List<Cart> listAll(String uid) throws SQLException {

        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Cart> list=new ArrayList<Cart>();
        try{
            conn=JDBCUtils.getConnection();

            String sql = "select * from homework.cart where  c_uid= '"+uid+"'";

            stmt=conn.prepareStatement(sql);
            //stmt.setString(1,uid);
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                Cart cart=new Cart();
                cart.setCid(rs.getString("cid"));
                cart.setC_pname(rs.getString("c_pname"));
                //System.out.print(cart.getCid());
                cart.setCnum(rs.getInt("cnum"));
                cart.setC_uid(rs.getString("c_uid"));
                cart.setC_pid(rs.getString("c_pid"));
                cart.setCprice(rs.getInt("cprice"));
                list.add(cart);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return null;
    }
    //删除商品
    public Boolean delete(String cid) throws SQLException, ClassNotFoundException {
        String sql = "delete from homework.cart where cid=?";
        Connection conn=JDBCUtils.getConnection();
        PreparedStatement state = null;


        boolean f = false;

        try{
            state= conn.prepareStatement(sql);
            state.setString(1, cid);
            state.execute();
            return true;


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(state,conn);
        }

        return f;
    }
    //判断能否购买
    public boolean canbuy(String pid,int cnum) throws SQLException, ClassNotFoundException {
        String sql="select * from homework.product where pid=?";
        Connection conn=null;
        PreparedStatement state = null;
        ResultSet rs=null;
        conn=JDBCUtils.getConnection();
        state=conn.prepareStatement(sql);
        state.setString(1, pid);
        rs=state.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getInt("pnum"));
            return rs.getInt("pnum") >= cnum;
        }
        return false;
    }
}
