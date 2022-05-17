package clientservlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import dao.LogrecordDao;
import dao.UserDao;
import domain.Logrecord;
import domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static UserDao dao = new UserDao();
    private static LogrecordDao dao2=new LogrecordDao();
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        String urole=request.getParameter("userrole");
        User a=new User();

            try {
                a = dao.search(username, userpassword,urole);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        if(a!=null)
        {
            session.setAttribute("user",a);
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
            Logrecord logrecord=new Logrecord();
            logrecord.setL_uid(a.getUid());
            logrecord.setL_urole(a.getUrole());
            logrecord.setLip(ip);
            logrecord.setLogintime(time);
            logrecord.setLogouttime("/");
            logrecord.setLid(RandomStringUtils.randomNumeric(10));
            try {
                boolean b=dao2.insert(logrecord);
                if(b) {
                    if(Objects.equals(a.getUrole(), "client"))
                        request.getRequestDispatcher("/index.jsp").forward(request,response);
                    else if(Objects.equals(a.getUrole(), "employee"))
                        request.getRequestDispatcher("/employee/index2.jsp").forward(request,response);
                    else if(Objects.equals(a.getUrole(), "manager"))
                        request.getRequestDispatcher("/managers/index3.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else{
            request.getSession().setAttribute("message","密码或用户名错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
