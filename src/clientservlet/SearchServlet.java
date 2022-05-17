package clientservlet;

import dao.ProductDao;
import domain.Product;
import domain.User;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static ProductDao dao=new ProductDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user=(User) request.getSession().getAttribute("user");
        String pname=request.getParameter("productname");
        Product product=new Product();
        try {
            product=dao.search(pname);
            if(product==null) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("messages","没有找到这个商品");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else{
                //System.out.println(product.getPid());
                request.getSession().setAttribute("p", product);
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/client/detail.jsp").forward(request, response);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
