package dao;

import domain.Cart;
import domain.Record;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDao {
    //插入浏览记录
    public  boolean insert(Record record) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn= JDBCUtils.getConnection();
        String sql="insert into homework.record values(?,?,?,?,?,?)";
        state= conn.prepareStatement(sql);
        state.setString(1,record.getRid());
        state.setString(2,record.getR_uid());
        state.setString(3,record.getR_pid());
        state.setString(4,record.getR_pname());
        state.setString(5,record.getR_ptype());
        state.setInt(6,record.getRtime());
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
    //查询一类商品浏览记录
    public List<Record> listonetype(String uid,String utype) throws SQLException {
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Record> list=new ArrayList<Record>();
        try{
            conn=JDBCUtils.getConnection();
            String sql = "select * from homework.record where  r_uid=? and r_ptype=?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,uid);
            stmt.setString(2,utype);
            rs=stmt.executeQuery();
            while (rs.next()){
                Record record=new Record();
                record.setRid(rs.getString("rid"));
                record.setRtime(rs.getInt("rtime"));
                record.setR_pid(rs.getString("r_pid"));
                record.setR_ptype(rs.getString("r_ptype"));
                record.setR_uid(rs.getString("r_uid"));
                record.setR_pname(rs.getString("r_pname"));
                list.add(record);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return null;
    }
}
