package clientservlet;

import dao.CartDao;
import dao.OrderDao;
import dao.ProductDao;
import domain.Cart;
import domain.Order;
import domain.Product;
import domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;
import utils.MailUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "PurchaseServlet", value = "/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static CartDao dao=new CartDao();
    private static ProductDao dao2=new ProductDao();
    private static OrderDao dao3=new OrderDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        User user=(User)request.getSession().getAttribute("user");
        String c_pid=request.getParameter("c_pid");
        String cid=request.getParameter("cid");
        int cprice= Integer.parseInt(request.getParameter("cprice"));
        int cnum= Integer.parseInt(request.getParameter("cnum"));

        try {
            boolean f= dao.canbuy(c_pid,cnum);
            if(f){
                boolean b= dao2.pruchase(c_pid,cnum);
                if(b){

                    Date date = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                    String oid= RandomStringUtils.randomNumeric(10);
                    Order order=new Order();
                    order.setOid(oid);
                    order.setO_pid(c_pid);
                    order.setO_uid(user.getUid());
                    String time=dateFormat.format(date);
                    order.setOtime(time);
                    Product product=new Product();
                    product= dao2.searchone(c_pid);
                    boolean d=dao3.insert(order,product.getPname(),product.getPtype(),cnum,cprice);
                    boolean c= dao.delete(cid);//购买成功的从购物车删除
                    request.getSession().setAttribute("user",user);
                    MailUtils mail= new MailUtils(user.getUemail());
                    request.getSession().setAttribute("message","成功购买");
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                }
                else {
                    System.out.println("不行2");
                }
            }
            else {
                System.out.println("不行1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
