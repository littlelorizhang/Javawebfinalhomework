package managerservlet;

import dao.ProductDao;
import dao.UserDao;
import domain.User;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddEmployeeServlet", value = "/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static UserDao dao=new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user= (User) request.getSession().getAttribute("user");
        String uid=request.getParameter("uid");
        String utype=request.getParameter("utype");
        try {
            boolean b=dao.add(uid,utype);
            if(b) {
                request.getSession().setAttribute("user",user);
                request.getSession().setAttribute("message","操作成功");
                request.getRequestDispatcher("/managers/index3.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
