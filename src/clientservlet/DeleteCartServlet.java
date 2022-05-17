package clientservlet;

import dao.CartDao;
import domain.Cart;
import domain.User;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeleteCartServlet", value = "/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static CartDao dao=new CartDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        User user=(User)request.getSession().getAttribute("user");
        boolean b=false;
        System.out.println("hello");
        String cid=request.getParameter("cid");
        try {
            b= dao.delete(cid);
            List<Cart> cart=dao.listAll(user.getUid());
            if(b){
                request.getSession().setAttribute("user",user);
                request.getSession().setAttribute("c",cart);
                request.getSession().setAttribute("message","商品删除成功");
                request.getRequestDispatcher("/client/cart.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
