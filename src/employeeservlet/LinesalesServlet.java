package employeeservlet;

import dao.OrderDao;
import domain.Sale;
import domain.User;
import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "LinesalesServlet", value = "/LinesalesServlet")
public class LinesalesServlet extends HttpServlet {
    OrderDao dao=new OrderDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user= (User) request.getSession().getAttribute("user");
        List<Sale > saleList=new ArrayList<>();
        List<String> dateList = new ArrayList<>();//放日期,从远到近
        List<Integer>datalist=new ArrayList<>();//放数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = -6; i <= 0; i++) {
            Date date = DateUtils.addDays(new Date(), i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }

        try {
            int totalesale=0;
            saleList=dao.salelist(user.getUtype(), request.getParameter("productname"));
            for(int i=0;i<7;i++) {
                int flag=0;
                for (Sale sales : saleList) {
                    if (Objects.equals(sales.getStime(), dateList.get(i))){
                        datalist.add(sales.getScount());
                        flag=1;
                        totalesale+=sales.getScount();
                    }
                }
                if(flag==0)
                    datalist.add(0);
            }
            //加一天为预测天
            Date date = DateUtils.addDays(new Date(), 1);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
            //根据简单移动平均模型对商品销量进行预测
            datalist.add(totalesale/7+1);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("time",dateList);
            request.getSession().setAttribute("datas",datalist);
            request.getRequestDispatcher("/employee/sevendaysale.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
