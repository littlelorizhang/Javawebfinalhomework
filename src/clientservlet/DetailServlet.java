package clientservlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import domain.User;
import domain.Product;
import dao.ProductDao;
import utils.JDBCUtils;

@WebServlet(name = "DetailServlet", value = "/DetailServlet")
public class DetailServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static ProductDao dao=new ProductDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user=(User) request.getSession().getAttribute("user");
        String pid=request.getParameter("pid");
        long starttime= Long.parseLong(request.getParameter("time"));
        try {
            Product product=dao.searchone(pid);
            request.getSession().setAttribute("p",product);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("starttime",starttime);
            request.getRequestDispatcher("/client/detail.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
