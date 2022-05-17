package dao;

import domain.*;
import utils.JDBCUtils;

import javax.xml.soap.Node;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDao {
    //插入订单
    public boolean insert(Order order,String pname,String ptype,int cnum,int price) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        conn= JDBCUtils.getConnection();
        String sql="insert into homework.orders values(?,?,?,?,?,?,?,?)";
        state= conn.prepareStatement(sql);
        state.setString(1,order.getOid());
        state.setString(2, order.getO_uid());
        state.setString(3, order.getO_pid());
        state.setString(4,order.getOtime());
        state.setString(5,pname);
        state.setString(6,ptype);
        state.setInt(7,cnum);
        state.setInt(8,price);
        int num=state.executeUpdate();
        if(num>0)
            return true;
        return false;

    }

    //查询一类商品购买记录
    public List<Orders> listonetype(String uid, String utype) throws SQLException {
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;

        try{
            conn=JDBCUtils.getConnection();
            String sql = "select * from homework.orders where  o_uid=? and o_ptype=?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,uid);
            stmt.setString(2,utype);
            rs=stmt.executeQuery();

           List<Orders> order=new ArrayList<>();
            while (rs.next()){
                Orders orders=new Orders();
                 orders.setO_pid(rs.getString("o_pid"));
                 orders.setO_uid(rs.getString("o_uid"));
                 orders.setOid(rs.getString("oid"));
                 orders.setO_pname(rs.getString("o_pname"));
                 orders.setO_ptype(rs.getString("o_ptype"));
                 orders.setOtime(rs.getString("otime"));
                 orders.setOnum(rs.getInt("onum"));
                orders.setOprice(rs.getInt("oprice"));
                order.add(orders);
            }
            return order;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return null;
    }
    //返回一个商品近7天销售额
    public List<Sale>salelist(String utype,String uname) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        conn=JDBCUtils.getConnection();
        String sql="SELECT count(*),DATE_FORMAT(otime,'%Y-%m-%d'),o_pname,o_ptype from homework.orders " +
                "where TO_DAYS(NOW())-TO_DAYS(otime) <= 7 and o_ptype=? and o_pname=? GROUP BY TO_DAYS(otime)order by(DATE_FORMAT(otime,'%Y-%m-%d'))";
        state= conn.prepareStatement(sql);
        state.setString(1,utype);
        state.setString(2,uname);

        List<Sale>list=new ArrayList<>();
        rs=state.executeQuery();
        while(rs.next()){
            Sale sale=new Sale();
            sale.setS_pname(rs.getString("o_pname"));
            sale.setS_ptype(rs.getString("o_ptype"));
            sale.setScount(rs.getInt("count(*)"));
            sale.setStime(rs.getString("DATE_FORMAT(otime,'%Y-%m-%d')"));
            list.add(sale);
        }
        return list;
    }
    //用户购买力
    public String purchaseability(String uid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        conn=JDBCUtils.getConnection();
        String sql="select oprice from homework.orders where o_uid=?";
        state= conn.prepareStatement(sql);
        state.setString(1,uid);
        rs=state.executeQuery();
        int money=0;
        while(rs.next()){
            money+=rs.getInt("oprice");
        }
        String answer = null;
        if(0<money&&money<=200)
            answer="低";
        else if(0<money&&money<=500)
            answer="中";
        else if(0<money&&money>500)
            answer="高";
        else if(money==0)
            answer="新用户未知";
        return answer;
    }
    //用户购物偏好
    public String purchaseperference(String uid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        conn=JDBCUtils.getConnection();
        String sql="select o_ptype from homework.orders where o_uid=?";
        state= conn.prepareStatement(sql);
        state.setString(1,uid);
        rs=state.executeQuery();
        int[] a =new int[7];
        //1.眼影 2.眼线 3.口红 4.底妆 5.睫毛 6.眉笔 7.修容
        int count=0;
        while(rs.next()){
            count++;
            if(Objects.equals(rs.getString("o_ptype"), "眼影"))
                a[0]+=1;
            if(Objects.equals(rs.getString("o_ptype"), "眼线"))
                a[1]+=1;
            if(Objects.equals(rs.getString("o_ptype"), "口红"))
                a[2]+=1;
            if(Objects.equals(rs.getString("o_ptype"), "底妆"))
                a[3]+=1;
            if(Objects.equals(rs.getString("o_ptype"), "睫毛"))
                a[4]+=1;
            if(Objects.equals(rs.getString("o_ptype"), "眉笔"))
                a[5]+=1;
            if(Objects.equals(rs.getString("o_ptype"), "修容"))
                a[6]+=1;

        }
        if(count==0)
            return "新用户未知偏好";
        int max=a[0];
        int index=0;
        for(int i=0;i<7;i++)
            if(max<a[i]){
                max=a[i];
                index=i;
            }
        String answer=null;
        if(index==0)
            answer="眼影";
        if(index==1)
            answer="眼线";
        if(index==2)
            answer="口红";
        if(index==3)
            answer="底妆";
        if(index==4)
            answer="睫毛";
        if(index==5)
            answer="眉笔";
        if(index==6)
            answer="修容";
        return answer;
    }
    //存放用户购买过的商品，用于最后推荐
    public class LinkList<T>{
        private Node<T> head; //链表的头节点
        private Node<T> tail; //链表的尾节点
        public LinkList() {
            head = tail = null;
        }
        private class Node<T> {
            T data;//节点的数据
            Node<T> next;//该节点的指向下一个节点的指针

            Node(T data) {
                this.data = data;
                this.next = null;
            }

        }

        public void addHead(T point) {//为空链表增加头结点
            this.head = new Node<T>(point);
            if(tail == null) {
                tail = head;
            }
        }
        public void addTail(T point){//为链表增加尾节点
            tail = new Node<T>(point);
            head.next = tail;
        }

        public void insert(T point) {
            if (this.head == null) {
                addHead(point);

            } else if (this.tail == this.head) {
                addTail(point);

            } else {
                Node<T> preNext = head.next;
                @SuppressWarnings({ "unchecked", "rawtypes" })
                Node<T> newNode = new Node(point);
                preNext = head.next;
                this.head.next = newNode;
                newNode.next = preNext;
            }

        }
        public boolean isEmpty(){//判断链表是否为空
            return this.head == null || this.tail == null;
        }
        public void selectLinkList( String pid) {    //筛选链表
            Node<T> curr = this.head;
            if (isEmpty()) {
                try {
                    throw new Exception("linklist is empty");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            while(curr != null){
                if(curr.data==pid)//用户购买过这个商品
                    delete(curr.data);//把它删掉
                curr = curr.next;
            }
        }
        public void delete(T data){//删除某一节点
            Node<T> curr = head, prev = null;
            boolean suc = false;//是否删除成功标志
            while (curr != null) {
                if (curr.data.equals(data)) {
                    //判断是什么节点
                    if (curr == head) {   //如果删除的是头节点
                        System.out.println('\n'+"delete head node");
                        head = curr.next;
                        suc = true;
                        return;
                    }
                    if (curr == tail) { //如果删除的是尾节点
                        System.out.println('\n'+"delete tail node");
                        tail = prev;
                        prev.next = null;
                        suc = true;
                        return;
                    } else {//如果删除的是中间节点（即非头节点或非尾节点）
                        System.out.println('\n'+"delete center node");
                        prev.next = curr.next;
                        suc = true;
                        return;
                    }
                }

                prev = curr;
                curr = curr.next;
            }

            if(suc == false) {
                System.out.println('\n'+"没有这个数据");
            }

        }
        public String[] printLinkList() {
            Node<T> curr = this.head;

            if (isEmpty()) {
                try {
                    throw new Exception("linklist is empty");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String array[]=new String[1000];
            int i=0;
            while(curr != null){
                array[i]= (String) curr.data;
                curr = curr.next;
                i++;



            }
            return array;
        }



    }
    //推荐系统
    public List<Product> recommend(String uid) throws SQLException, ClassNotFoundException {
        Connection conn=null;
        PreparedStatement state=null;
        ResultSet rs=null;
        conn=JDBCUtils.getConnection();
        ArrayList<Product> list=new ArrayList<Product>();
        //订单按照时间顺序从上到下
        String sql="select * from homework.orders where o_uid=?  order by otime";
        //浏览记录同理
        String sql2="select * from homework.record where r_uid=? ";
        state= conn.prepareStatement(sql);
        state.setString(1,uid);
        rs=state.executeQuery();
        int falg1=0;
        double[] a =new double[7];

        LinkList<String> mylist = new LinkList<String>();//获取用户购买过的商品
        //1.眼影 2.眼线 3.口红 4.底妆 5.睫毛 6.眉笔 7.修容
        //权重用0.7×购买的数量，这样可以看出用户对类商品的喜爱程度
        while(rs.next()){
            falg1++;
            if(Objects.equals(rs.getString("o_ptype"), "眼影"))
                a[0]+=0.7*rs.getInt("onum");
            if(Objects.equals(rs.getString("o_ptype"), "眼线"))
                a[1]+=0.7*rs.getInt("onum");
            if(Objects.equals(rs.getString("o_ptype"), "口红"))
                a[2]+=0.7*rs.getInt("onum");
            if(Objects.equals(rs.getString("o_ptype"), "底妆"))
                a[3]+=0.7*rs.getInt("onum");
            if(Objects.equals(rs.getString("o_ptype"), "睫毛"))
                a[4]+=0.7*rs.getInt("onum");
            if(Objects.equals(rs.getString("o_ptype"), "眉笔"))
                a[5]+=0.7*rs.getInt("onum");
            if(Objects.equals(rs.getString("o_ptype"), "修容"))
                a[6]+=0.7*rs.getInt("onum");
            mylist.insert(rs.getString("o_pid"));//把已经购买过的商品pid记录下来
        }
        rs=null;
        state= conn.prepareStatement(sql2);
        state.setString(1,uid);
        rs=state.executeQuery();
        int flag2=0;
        double []b=new double[7];
        //权重用0.3×停留时长
        while(rs.next()){
            flag2++;
            if(Objects.equals(rs.getString("r_ptype"), "眼影"))
                b[0]+=0.3*rs.getInt("rtime");
            if(Objects.equals(rs.getString("r_ptype"), "眼线"))
                b[1]+=0.3*rs.getInt("rtime");
            if(Objects.equals(rs.getString("r_ptype"), "口红"))
                b[2]+=0.3*rs.getInt("rtime");
            if(Objects.equals(rs.getString("r_ptype"), "底妆"))
                b[3]+=0.3*rs.getInt("rtime");
            if(Objects.equals(rs.getString("r_ptype"), "睫毛"))
                b[4]+=0.3*rs.getInt("rtime");
            if(Objects.equals(rs.getString("r_ptype"), "眉笔"))
                b[5]+=0.3*rs.getInt("rtime");
            if(Objects.equals(rs.getString("r_ptype"), "修容"))
                b[6]+=0.3*rs.getInt("rtime");
        }
        if(flag2!=0||falg1!=0){
            //不是新用户
            double []c=new double[7];
            for(int i=0;i<7;i++){
                c[i]=a[i]+b[i];
            }
            double max=c[0];
            int index=0;
            for(int i=0;i<7;i++)
                if(max<c[i]){
                    max=c[i];
                    index=i;
                }
            String answer=null;
            if(index==0)
                answer="眼影";
            if(index==1)
                answer="眼线";
            if(index==2)
                answer="口红";
            if(index==3)
                answer="底妆";
            if(index==4)
                answer="睫毛";
            if(index==5)
                answer="眉笔";
            if(index==6)
                answer="修容";
            rs=null;
            String sql3="select * from homework.product where ptype=?";//查找最受用户喜欢的那类商品
            state= conn.prepareStatement(sql3);
            state.setString(1,answer);
            rs=state.executeQuery();
            String d[]=new String[500];
            int count=0;
            while(rs.next()){
                d[count]=rs.getString("pid");//获得用户喜欢的那一类商品的pid
                count++;
            }
            //把已经购买过的产品pid换掉
            String e[]= mylist.printLinkList();
            int ii=0;
            for (ii=0;ii<e.length;ii++)
            {
                if(e[ii]==null)
                    break;
            }
            int i=0;
           for( i=0;i<d.length;i++){
               if(d[i]==null)
                   break;
           }
           for(int j=0;j<i;j++)
           {
               for (int jj=0;jj<ii;jj++)
                   if (Objects.equals(d[j], e[jj])) {
                       d[j] = "nothis";

                   }
           }
            rs=null;
            String sql4="select * from homework.product where pid=?";//最后把这些pid所代表的商品打包到集合里
            state= conn.prepareStatement(sql4);


            for (int j=0;j<i;j++)
                if(d[j]!="nothis"){
                    state.setString(1,d[j]);
                    rs=state.executeQuery();
                    while(rs.next()){
                        Product product=new Product();
                        product.setPid(rs.getString("pid"));
                        product.setPname(rs.getString("pname"));
                        product.setPnum(rs.getInt("pnum"));
                        product.setPtype(rs.getString("ptype"));
                        product.setPsale(rs.getInt("psale"));
                        product.setPrice(rs.getInt("price"));
                        list.add(product);
                    }

                }

        }

        else{//是没买过东西没浏览过的新用户
            //推荐销量前三的商品
            rs=null;
            String sql5="select psale from homework.product";
            state= conn.prepareStatement(sql5);
            rs=state.executeQuery();
            int xiaoliang[]=new int[100];
            int i=0;
            while(rs.next()){//把销量全部记下来
                xiaoliang[i]= rs.getInt("psale");
                i++;
            }
            int xulie[] = new int[3];//得到销量前三的数据
            for(int j=0;j<3;j++) {

                int max = xiaoliang[0];
                int index = 0;
                for (i = 0; i < 7; i++)
                    if (max < xiaoliang[i]) {
                        max = xiaoliang[i];
                        index = i;
                    }
                xulie[j]=max;//记录下当前最大的销量
                xiaoliang[index]=0;//把当前最大销量置零

            }
            rs=null;
            String sql6="select * from homework.product";
            state= conn.prepareStatement(sql6);
            rs=state.executeQuery();
            while(rs.next()){
                for(int j=0;j<3;j++)
                {
                    if(rs.getInt("psale")==xulie[j]){//如果找到一个商品销量是前三，则把它记下来
                        Product product=new Product();
                        product.setPid(rs.getString("pid"));
                        product.setPname(rs.getString("pname"));
                        product.setPnum(rs.getInt("pnum"));
                        product.setPtype(rs.getString("ptype"));
                        product.setPsale(rs.getInt("psale"));
                        product.setPrice(rs.getInt("price"));
                        list.add(product);
                        break;
                    }

                }
            }
        }
        return list;
    }


    }
