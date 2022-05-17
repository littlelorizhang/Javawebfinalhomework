package dao;

import domain.Logrecord;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogrecordDao {
    //插入
   public boolean insert(Logrecord logrecord) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn= JDBCUtils.getConnection();
        String sql="insert into homework.logrecord values(?,?,?,?,?,?)";
        state= conn.prepareStatement(sql);
        state.setString(1,logrecord.getLid());
        state.setString(2,logrecord.getL_urole());
        state.setString(3,logrecord.getLip());
        state.setString(4,logrecord.getLogintime());
        state.setString(5,logrecord.getL_uid());
        state.setString(6,logrecord.getLogouttime());
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;
    }
}
