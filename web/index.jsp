<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/2/16
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="dao.ProductDao,utils.JDBCUtils,domain.User"  %>
<%@ page import="domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.JDBCUtils" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="domain.User" %>
<%@ page import="java.util.Date" %>
<html>
  <head>
    <title>两元店首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/fonts/iconfont.css" type="text/css">
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/footer.js" type="text/javascript"></script>
  </head>
  <body>
  <%
    {
      String message= (String) request.getSession().getAttribute("message");
      if(message!=null)
        response.getWriter().write("<script language=javascript>alert('"+message+"');</script>");
    }
  %>
  <!--顶部登陆信息，购物车-->
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

            <li class="hd_menu_tit" data-addclass="hd_menu_hover"> <a href="${pageContext.request.contextPath}/CartServlet?user=${user}">购物车</a> </li>
          </c:otherwise>
            </c:choose>


        </ul>
      </div>
    </div>
    </div>
  </div>
  <!--搜索栏+logo-->
  <div id="header "  class="header page_style">
    <div class="logo"><a href="index.jsp"><img src="${pageContext.request.contextPath}/modelweb/images/logo.jpg" /></a></div>
    <div class="Search">
      <form action="${pageContext.request.contextPath}/SearchServlet?user=${user}" method="post">
      <p><input name="productname" type="text" value="${messages}" class="text"/>

        <input name="" type="submit" value="搜 索"  class="Search_btn" /></p>

      </form>
    </div>
  </div>

  <!--产品分类-->
  <div id="Menu" class="clearfix">
    <div class="index_style clearfix">
      <div id="allSortOuterbox">
        <div class="t_menu_img"></div>
        <div class="Category"><a href="#"><em></em>所有产品分类</a></div>
        <div class="hd_allsort_out_box_new">
          <ul class="Menu_list">
            <li class="name">
              <div class="Menu_name">
<c:choose >
  <c:when test="${ not empty user.getUname()}">
                <a href="${pageContext.request.contextPath}/RecommendServlet?user=${user}" >猜你喜欢</a> <span>&lt;</span>
</c:when>
</c:choose>
              </div>
              
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <!--欢迎标语-->
  <div id="slideBox" class="slideBox">
    <div class="hd">
      <ul class="smallUl"></ul>
    </div>
    <div class="bd">
      <ul>
        <li><div style="background:url(${pageContext.request.contextPath}/modelweb/images/AD_2.png) no-repeat; background-position:center; width:100%; height:460px;"></div></li>
      </ul>
    </div>

  </div>
<%
  JDBCUtils db = new JDBCUtils();
  ProductDao dao=new ProductDao();
  try {
    List<Product> ps= dao.listAll();
    request.setAttribute("ps",ps);
  } catch (SQLException e) {
    e.printStackTrace();
  }
  User user= (User) request.getSession().getAttribute("user");
  request.setAttribute("user",user);
    %>
  <!--产品展示-->
  <div class="like_p">
    <div class="title_name"><span>全部产品</span></div>
    <div class="list">
      <ul class="list_style">
<c:forEach items="${ps}" var="p">
  <%
    long startDate = new Date().getTime();
  %>
        <li class="p_info_u">
          <a href="${pageContext.request.contextPath}/DetailServlet?user=${user}&pid=${p.pid}&time=<%=startDate%>" class="p_img"><img src="${pageContext.request.contextPath}/modelweb/product/${p.pid}.jpg" width="220" height="220" /></a>
          <a href="${pageContext.request.contextPath}/DetailServlet?user=${user}&pid=${p.pid}&time=<%=startDate%>" class="name">${p.pname}</a>
          <div class="Numeral"><span class="price"><i>￥</i>${p.price}</span><span class="Sales">销量<i>${p.psale}</i>件</span></div>
        </li>
</c:forEach>

      </ul>
    </div>
  </div>
  <%request.getSession().removeAttribute("message");%>
  </body>
</html>
