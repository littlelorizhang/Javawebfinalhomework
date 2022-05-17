package managerservlet;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchonetypeServlet", value = "/SearchonetypeServlet")
public class SearchonetypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user= (User) request.getSession().getAttribute("user");
        String ptype=request.getParameter("ptype");
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("ptype",ptype);
        request.getRequestDispatcher("/managers/searchonetype.jsp").forward(request,response);
    }
}
