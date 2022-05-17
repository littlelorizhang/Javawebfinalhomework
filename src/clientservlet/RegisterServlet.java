package clientservlet;

import domain.User;
import dao.UserDao;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static UserDao dao = new UserDao();
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        String useremail = request.getParameter("useremail");
        String useraddress=request.getParameter("useraddress");
        user.setUid(RandomStringUtils.randomNumeric(10));
        user.setUname(username);
        user.setUpassword(userpassword);
        user.setUemail(useremail);
        user.setUaddress(useraddress);
        user.setUrole("client");
        boolean b = dao.insert(user);
        if(b){
            //注册成功

            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("/client/register.jsp").forward(request,response);
        }
    }
}
