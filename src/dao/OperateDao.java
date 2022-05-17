package dao;

import domain.Operate;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperateDao {
//插入操作日志
   public boolean insert (Operate operate) throws SQLException, ClassNotFoundException {
        Connection conn= JDBCUtils.getConnection();
        PreparedStatement state = null;
        String sql="insert into homework.operates values(?,?,?,?,?)";
        state= conn.prepareStatement(sql);
        state.setString(1,operate.getOid());
        state.setString(2,operate.getO_uid());
        state.setString(3,operate.getOip());
        state.setString(4,operate.getOtime());
        state.setString(5,operate.getOtype());
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
}
