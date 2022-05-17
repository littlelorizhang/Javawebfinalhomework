package clientservlet;

import dao.RecordDao;
import dao.UserDao;
import domain.Product;
import domain.Record;
import domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "RecordServlet", value = "/RecordServlet")
public class RecordServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static RecordDao dao = new RecordDao();
    private static final long serialVersionUID = 1L;
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
        String pname=request.getParameter("pname");
        String ptype=request.getParameter("ptype");
        long starttime= Long.parseLong(request.getParameter("starttime"));
        long nowtime = new Date().getTime();
        int diffSeconds = (int) ((nowtime - starttime) / 1000);
        Record record=new Record();
        record.setR_pid(pid);
        record.setR_pname(pname);
        record.setR_ptype(ptype);
        if(user.getUid()!=null)
            record.setR_uid(user.getUid());
        else
            record.setR_uid("/");
        record.setRtime(diffSeconds);
        record.setRid(RandomStringUtils.randomNumeric(10));
        try {
            boolean b= dao.insert(record);
            if(b){
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
