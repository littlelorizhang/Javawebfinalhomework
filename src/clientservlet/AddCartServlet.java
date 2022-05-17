package clientservlet;

import dao.CartDao;
import dao.RecordDao;
import domain.Cart;
import domain.Record;
import domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "AddCartServlet", value = "/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static CartDao dao=new CartDao();
    private static RecordDao dao2 = new RecordDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        User user=(User)request.getSession().getAttribute("user");
        String pid= (String) request.getSession().getAttribute("pid");
        String pname= (String) request.getSession().getAttribute("pname");
        int price= ((int) request.getSession().getAttribute("price"));
        int num= Integer.parseInt(request.getParameter("num"));
        String ptype= (String) request.getSession().getAttribute("ptype");
        long starttime= (long) request.getSession().getAttribute("starttime");
        long nowtime = new Date().getTime();
        int diffSeconds = (int) ((nowtime - starttime) / 1000);
        Record record=new Record();
        record.setR_pid(pid);
        record.setR_pname(pname);
        record.setR_ptype(ptype);
        record.setR_uid(user.getUid());
        record.setRtime(diffSeconds);
        record.setRid(RandomStringUtils.randomNumeric(10));
        //System.out.println(user.getUname());
        Cart cart=new Cart();
        //System.out.println(pid);
        cart.setCprice(price*num);
        cart.setC_pid(pid);
        cart.setC_pname(pname);
        cart.setC_uid(user.getUid());
        cart.setCid(RandomStringUtils.randomNumeric(10));
        cart.setCnum(num);
        //System.out.println(pname);

        boolean b=false;
        boolean c=false;
        try {
            b = dao.insert(cart);
            c= dao2.insert(record);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(b&&c){
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("message","成功添加到购物车");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else{

            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("/client/detail.jsp").forward(request,response);
        }
    }
}
