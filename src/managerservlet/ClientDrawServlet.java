package managerservlet;

import dao.OrderDao;
import dao.UserDao;
import domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ClientDrawServlet", value = "/ClientDrawServlet")
public class ClientDrawServlet extends HttpServlet {
    OrderDao dao1=new OrderDao();
    UserDao dao2=new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user= (User) request.getSession().getAttribute("user");
        String uid=request.getParameter("uid");
        try {
            String goumaili=dao1.purchaseability(uid);
            String diyu= dao2.region(uid);
            String pianhao=dao1.purchaseperference(uid);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("goumaili",goumaili);
            request.getSession().setAttribute("diyu",diyu);
            request.getSession().setAttribute("pianhao",pianhao);
            request.getRequestDispatcher("/managers/clientsdraw.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
