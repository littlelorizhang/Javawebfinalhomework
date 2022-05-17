package managerservlet;

import dao.OperateDao;
import dao.ProductDao;
import domain.Operate;
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

@WebServlet(name = "InventoryServlet", value = "/InventoryServlet")
public class InventoryServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static ProductDao dao=new ProductDao();
    private static OperateDao dao2=new OperateDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user= (User) request.getSession().getAttribute("user");
        boolean b= false;
        String pid=request.getParameter("productid");
        int pnum= Integer.parseInt(request.getParameter("productnum"));

        try {
            b= dao.change(pnum,pid);
            if(b){
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
                operate.setOtype("管理库存");
                operate.setOid(RandomStringUtils.randomNumeric(10));
                boolean c=dao2.insert(operate);
                if(c) {
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("message","操作成功");
                    request.getRequestDispatcher("/managers/index3.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
