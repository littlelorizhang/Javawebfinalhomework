package employeeservlet;

import dao.OperateDao;
import dao.OrderDao;
import dao.RecordDao;
import domain.Operate;
import domain.Order;
import domain.Orders;
import domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ShowOrderServlet", value = "/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static OrderDao dao=new OrderDao();
    private static OperateDao dao2=new OperateDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        String uid=request.getParameter("uid");

        try {
            List<Orders> order=dao.listonetype(uid,user.getUtype());
            request.getSession().setAttribute("order",order);
            request.getSession().setAttribute("user",user);
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String time=dateFormat.format(date);

            Operate operate=new Operate();
            operate.setO_uid(user.getUid());
            operate.setOip(ip);
            operate.setOtime(time);
            operate.setOtype("展示订单");
            operate.setOid(RandomStringUtils.randomNumeric(10));
            boolean c=dao2.insert(operate);
            if(c)
            request.getRequestDispatcher("/employee/showorder.jsp").forward(request,response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
