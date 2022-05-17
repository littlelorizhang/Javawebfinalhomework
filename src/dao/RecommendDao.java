package dao;

import domain.Product;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecommendDao {
    //推荐系统
//    public List<Product> recommend(String uid) throws SQLException, ClassNotFoundException {//根据用户id定制推荐计划
//        //设定购买过的参数为0.7，浏览记录参数为0.3
//        Connection conn=null;
//        PreparedStatement state=null;
//        ResultSet rs=null;
//        conn= JDBCUtils.getConnection();
//        String sql="select o_ptype,r_ptype from homework.orders,homework.record where o_uid=? and r_uid=?";
//        state = conn.prepareStatement(sql);
//        state.setString(1,uid);
//        state.setString(2,uid);
//        //1.眼影2.眼线3.眉笔4.睫毛5.修容6.底妆7.口红
//        int a[]= new int[7];//订单
//        int b[]= new int[7];//浏览记录
//        rs=state.executeQuery();
//        while (rs.next()) {
//            if(Objects.equals(rs.getString("o_ptype"), "眼影"))
//               a[0]+=1;
//            if(Objects.equals(rs.getString("o_ptype"), "眼线"))
//                a[1]+=1;
//            if(Objects.equals(rs.getString("o_ptype"), "眉笔"))
//                a[2]+=1;
//            if(Objects.equals(rs.getString("o_ptype"), "睫毛"))
//                a[3]+=1;
//            if(Objects.equals(rs.getString("o_ptype"), "修容"))
//                a[4]+=1;
//            if(Objects.equals(rs.getString("o_ptype"), "底妆"))
//                a[5]+=1;
//            if(Objects.equals(rs.getString("o_ptype"), "口红"))
//                a[6]+=1;
//
//            if(Objects.equals(rs.getString("r_ptype"), "眼影"))
//                b[0]+=1;
//            if(Objects.equals(rs.getString("r_ptype"), "眼线"))
//                b[1]+=1;
//            if(Objects.equals(rs.getString("r_ptype"), "眉笔"))
//                b[2]+=1;
//            if(Objects.equals(rs.getString("r_ptype"), "睫毛"))
//                b[3]+=1;
//            if(Objects.equals(rs.getString("r_ptype"), "修容"))
//                b[4]+=1;
//            if(Objects.equals(rs.getString("r_ptype"), "底妆"))
//                b[5]+=1;
//            if(Objects.equals(rs.getString("r_ptype"), "口红"))
//                b[6]+=1;
//        }
//        //找到数字排前三高的
//        double c[]= new double[7];//用来排序
//        double d[]= new double[7];//用来找到原来的序号
//        for(int i=0;i<7;i++){
//            c[i]=  (a[i]*0.7+b[i]*0.3);
//            d[i]=c[i];//如果有出现零的说明用户对这一类商品不感冒
//        }
//        Arrays.sort(c);//小的在前大的在后
//        for(int i=0;i<3;i++){//找出哪几类受客户欢迎
//            if(d[i]==c[6-i]&&d[i]!=0){
//                d[i]=1;
//            }
//        }
//
//
//
//    }
}
