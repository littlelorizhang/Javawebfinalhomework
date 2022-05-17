package clientservlet;

import dao.OrderDao;
import domain.Product;
import domain.User;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecommendServlet", value = "/RecommendServlet")
public class RecommendServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static OrderDao dao=new OrderDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        User user=(User)request.getSession().getAttribute("user");
        ArrayList<Product> list=new ArrayList<Product>();
        try {
            list= (ArrayList<Product>) dao.recommend(user.getUid());
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("ps",list);
            request.getRequestDispatcher("/client/recommend.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
