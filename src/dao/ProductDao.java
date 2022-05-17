package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.prism.impl.Disposer;
import utils.JDBCUtils;
import domain.Product;
public class ProductDao {
    //查询全部商品，用于首页展示
    public List<Product> listAll() throws SQLException {

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<Product> list=new ArrayList<Product>();
        try{
            conn=JDBCUtils.getConnection();
            stmt=conn.createStatement();
            String sql = "select * from homework.product";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                Product product=new Product();
                product.setPid(rs.getString("pid"));
                product.setPname(rs.getString("pname"));
                product.setPnum(rs.getInt("pnum"));
                product.setPtype(rs.getString("ptype"));
                product.setPsale(rs.getInt("psale"));
                product.setPrice(rs.getInt("price"));
                list.add(product);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return null;
    }
    //查找一个商品by pid
    public Product searchone(String pid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        Product product=new Product();
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from homework.product where pid=?";
            state = conn.prepareStatement(sql);
            state.setString(1, pid);
            rs = state.executeQuery();
            while (rs.next()) {
                product.setPid(rs.getString("pid"));
                product.setPname(rs.getString("pname"));
                product.setPtype(rs.getString("ptype"));
                product.setPnum(rs.getInt("pnum"));
                product.setPsale(rs.getInt("psale"));
                product.setPrice(rs.getInt("price"));
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查找商品by name
    public Product search(String pname) throws SQLException {

        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        Product product=new Product();
        try{
            conn=JDBCUtils.getConnection();
            String sql = "select * from homework.product where pname=?";
            state= conn.prepareStatement(sql);
            state.setString(1, pname);
            rs=state.executeQuery();
            while (rs.next()){
                //System.out.println(rs.getString("pid"));
                product.setPid(rs.getString("pid"));
                product.setPname(rs.getString("pname"));
                product.setPtype(rs.getString("ptype"));
                product.setPnum(rs.getInt("pnum"));
                product.setPsale(rs.getInt("psale"));
                product.setPrice(rs.getInt("price"));
                return product;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    //购买商品
    public boolean pruchase(String pid,int cnum) throws SQLException, ClassNotFoundException {
        Connection conn=JDBCUtils.getConnection();
        PreparedStatement state=null;

        String sql="update homework.product set pnum=pnum-?,psale=psale+? where pid=?";
        state= conn.prepareStatement(sql);
        state.setInt(1,cnum);
        state.setInt(2,cnum);
        state.setString(3,pid);
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
    //上架商品
    public boolean insert(Product product) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn= JDBCUtils.getConnection();
        String sql="insert into homework.product values(?,?,?,?,?,?)";
        state= conn.prepareStatement(sql);
        state.setString(1, product.getPid());
        state.setString(2, product.getPname());
        state.setString(3, product.getPtype());
        state.setInt(4,product.getPnum());
        state.setInt(5,product.getPsale());
        state.setInt(6,product.getPrice());
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
    //展示某一类型的商品
    public List<Product> listonetype( String utype) throws SQLException {

        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        ArrayList<Product> list=new ArrayList<Product>();
        try{
            conn=JDBCUtils.getConnection();

            String sql = "select * from homework.product where ptype=?";
            state= conn.prepareStatement(sql);
            state.setString(1,utype);
            rs=state.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setPid(rs.getString("pid"));
                product.setPname(rs.getString("pname"));
                product.setPnum(rs.getInt("pnum"));
                product.setPtype(rs.getString("ptype"));
                product.setPsale(rs.getInt("psale"));
                product.setPrice(rs.getInt("price"));
                list.add(product);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs,state,conn);
        }
        return null;
    }
    //删除商品
    public boolean delete(String pid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        conn=JDBCUtils.getConnection();
        PreparedStatement state=null;
        String sql="delete from homework.product where pid=?";
        state= conn.prepareStatement(sql);
        state.setString(1,pid);
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
    //修改商品
    public  boolean change(int pnum,int price,String pid) throws SQLException, ClassNotFoundException {
        Connection conn=JDBCUtils.getConnection();
        PreparedStatement state=null;
        String sql="update homework.product set pnum=?,price=? where pid=?";
        state= conn.prepareStatement(sql);
        state.setInt(1,pnum);
        state.setInt(2,price);
        state.setString(3,pid);
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
    //修改库存
    public  boolean change(int pnum,String pid) throws SQLException, ClassNotFoundException {
        Connection conn=JDBCUtils.getConnection();
        PreparedStatement state=null;
        String sql="update homework.product set pnum=? where pid=?";
        state= conn.prepareStatement(sql);
        state.setInt(1,pnum);

        state.setString(2,pid);
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
    //返回所有类型的商品的销量
    public List<Integer> alltype() throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn=JDBCUtils.getConnection();
        ArrayList<Integer>list=new ArrayList<>();
        String a[]=new String[]{"眼影","眼线","眉笔","修容","口红","睫毛","底妆"};
        for(int j=0;j<7;j++) {
            ResultSet rs=null;
            String sql = "select psale from homework.product where ptype=?";
            state = conn.prepareStatement(sql);
            state.setString(1,a[j]);
            rs=state.executeQuery();
            int i = 0;
            while (rs.next()) {
                i += rs.getInt("psale");
            }
            list.add(i);
        }
        return list;
    }
    //返回所有类型的商品的钱数
    public List<Integer> alltypemoney() throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn=JDBCUtils.getConnection();
        ArrayList<Integer>list=new ArrayList<>();
        String a[]=new String[]{"眼影","眼线","眉笔","修容","口红","睫毛","底妆"};
        for(int j=0;j<7;j++) {
            ResultSet rs=null;
            String sql = "select psale , price from homework.product where ptype=?";
            state = conn.prepareStatement(sql);
            state.setString(1,a[j]);
            rs=state.executeQuery();
            int i = 0;
            while (rs.next()) {
                i += rs.getInt("psale")* rs.getInt("price");
            }
            list.add(i);
        }
        return list;
    }

}
