package employeeservlet;

import dao.OperateDao;
import dao.ProductDao;
import domain.Operate;
import domain.Product;
import domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    JDBCUtils db = new JDBCUtils();
    private static final long serialVersionUID = 1L;
    private static ProductDao dao=new ProductDao();
    private static OperateDao dao2=new OperateDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Product product=new Product();
        User user= (User) request.getSession().getAttribute("user");
        boolean b= false;

        DiskFileItemFactory dfif=new DiskFileItemFactory();
        File f=new File("/temp");//文件缓存目录
        if(!f.exists()){
            f.mkdirs();
        }
        String path=request.getServletContext().getRealPath("/modelweb/product");
        dfif.setRepository(new File(path));
        dfif.setSizeThreshold(1024*1024*10);
        ServletFileUpload upload=new ServletFileUpload(dfif);
        upload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> items=upload.parseRequest(request);
            String pid=null;
            String pname=null;
            String ptype=null;
            String pnum=null;
            int psale=0;//这里新商品销量直接为0
            String price=null;
            for (int i=0;i<items.size();i++){
                {
                    FileItem item=items.get(i);
                    if(item.isFormField()){
                        //接收的是非文件
                        String filedName=item.getFieldName();
                        if(filedName.equals("productid")){
                            pid=item.getString("utf-8");

                        }
                        if(filedName.equals("productname")){
                            pname=item.getString("utf-8");

                        }
                        if(filedName.equals("producttype")){
                            ptype=item.getString("utf-8");

                        }
                        if(filedName.equals("productprice")){
                            price=item.getString("utf-8");

                        }
                        if(filedName.equals("productnum")){
                            pnum=item.getString("utf-8");

                        }
                    }
                    else{
                        //System.out.print("go1");
                        String filedName=item.getFieldName();
                        //System.out.print(filedName.substring(filedName.lastIndexOf("."),filedName.length()));
                        //filedName=filedName.substring(filedName.lastIndexOf("\\")+1);
                        String houzhui=filedName.substring(filedName.length()-4);

                        String webpath="/modelweb/product";//储存路径
                        String rename=pid+".jpg";
                        String filepath=getServletContext().getRealPath(webpath+filedName);
                        File dir=new File(webpath);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        File file=new File(path,rename);
                        try {
                            //System.out.print("go2");
                            item.write(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            request.setCharacterEncoding("utf-8");
            int price1=Integer.parseInt(price);
            int pnum1=Integer.parseInt(pnum);
            product.setPid(pid);
            product.setPname(pname);
            product.setPtype(ptype);
            product.setPnum(pnum1);
            product.setPsale(psale);
            product.setPrice(price1);
            b = dao.insert(product);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(b){
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String time=dateFormat.format(date);

            Operate operate=new Operate();
            operate.setO_uid(user.getUid());
            operate.setOip(ip);
            operate.setOtime(time);
            operate.setOtype("上架商品");
            operate.setOid(RandomStringUtils.randomNumeric(10));
            try {
                boolean c=dao2.insert(operate);
                if(c){
                    request.getSession().setAttribute("user",user);
                    request.getSession().setAttribute("message","操作成功");
                    request.getRequestDispatcher("/employee/index2.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
