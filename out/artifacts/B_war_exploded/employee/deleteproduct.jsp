<%@ page import="domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/3/6
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="dao.ProductDao" %>
<%@ page import="utils.JDBCUtils" %>
<%@ page import="domain.Product" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>下架商品</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/fonts/iconfont.css" type="text/css">
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/footer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/lrtk.js" type="text/javascript"></script>
</head>
<body>
<!--头部信息-->
<div id="header_top">
    <div id="top">
        <div class="Inside_pages">
            <div class="Collection">欢迎光临张大忽悠两元店 </div>
            <div class="hd_top_manu clearfix">
                <ul class="clearfix">
                    <li class="hd_menu_tit zhuce" data-addclass="hd_menu_hover">欢迎光临本店！
                        <c:choose >
                        <c:when test="${empty user.getUname()}">
                        <a href="${pageContext.request.contextPath}/login.jsp" class="red">[请登录]</a> 新用户<a href="${pageContext.request.contextPath}/client/register.jsp" class="red">[免费注册]</a></li>
                    </c:when>
                    <c:otherwise>
                        用户：${user.getUname()}<a href="${pageContext.request.contextPath}/LogoutServlet" class="red">[退出登录]</a>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"> 销售类型：${user.getUtype()}</li>
                    </c:otherwise>
                    </c:choose>


                </ul>
            </div>
        </div>
    </div>
</div>
<!--搜索栏+logo-->
<div id="header "  class="header page_style">
    <div class="logo"><a href="#"><img src="${pageContext.request.contextPath}/modelweb/images/logo.jpg" /></a></div>
</div>
<!--功能分类-->
<div id="Menu" class="clearfix">
    <div class="index_style clearfix">
        <div id="allSortOuterbox">
            <div class="t_menu_img"></div>
            <div class="Category"><a href="#"><em></em>下架商品</a></div>
            <div class="hd_allsort_out_box_new">
                <ul class="Menu_list">
                    <li class="name">
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/index2.jsp?user=${user}" >返回</a> <span><</span></div>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%
    User user= (User) request.getSession().getAttribute("user");
    String utype= user.getUtype();
    JDBCUtils db = new JDBCUtils();
    ProductDao dao=new ProductDao();
    List<Product> ps= dao.listonetype(utype);
    request.setAttribute("ps",ps);

%>
<!--此类商品-->
<div class="user_style  Inside_pages clearfix">
    <div class="user_center">
<div class="right_style">
    <div class="user_title_name"><span>下架商品</span></div>
    <div class="r_user_style">
        <div class="collect_list">
            <ul>
<c:forEach items="${ps}" var="p">
                <li class="collect_p">
                    <em class="iconfont icon-close2 delete"></em>
                    <div class="collect_info">
                        <div class="img_link"> <a href="${pageContext.request.contextPath}/DeleteProductServlet?user=${user}&pid=${p.pid}" class="center "><img src="${pageContext.request.contextPath}/modelweb/product/${p.pid}.jpg" /></a></div>
                        <dl class="xinxi">
                            <dt><a href="${pageContext.request.contextPath}/DeleteProductServlet?user=${user}&pid=${p.pid}" class="name">${p.pname}</a></dt>
                            <dd><span class="Price"><b>￥</b>${p.price}</span></dd>
                        </dl>
                    </div>
                </li>
</c:forEach>
            </ul>
        </div>
    </div>
</div>
    </div>
</div>
</body>
</html>
