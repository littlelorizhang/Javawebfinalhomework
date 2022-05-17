<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/4/5
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>推荐</title>
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

          </c:otherwise>
          </c:choose>


        </ul>
      </div>
    </div>
  </div>
</div>

<!--功能分类-->
<div id="Menu" class="clearfix">
  <div class="index_style clearfix">
    <div id="allSortOuterbox">
      <div class="t_menu_img"></div>
      <div class="Category"><a href="#"><em></em>猜你喜欢</a></div>
      <div class="hd_allsort_out_box_new">
        <ul class="Menu_list">
          <li class="name">
            <div class="Menu_name"><a href="${pageContext.request.contextPath}index.jsp?user=${user}" >返回</a> <span><</span></div>

          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!--推荐商品-->
<div class="user_style  Inside_pages clearfix">
  <div class="user_center">
    <div class="right_style">
      <div class="user_title_name"><span>猜你喜欢</span></div>
      <div class="r_user_style">
        <div class="collect_list">
          <ul>
            <c:forEach items="${ps}" var="p">
              <li class="collect_p">
                <%
                  long startDate = new Date().getTime();
                %>
                <div class="collect_info">
                  <div class="img_link"> <a href="${pageContext.request.contextPath}/DetailServlet?user=${user}&pid=${p.pid}&time=<%=startDate%>" class="center "><img src="${pageContext.request.contextPath}/modelweb/product/${p.pid}.jpg" /></a></div>
                  <dl class="xinxi">
                    <dt><a href="${pageContext.request.contextPath}/DetailServlet?user=${user}&pid=${p.pid}&time=<%=startDate%>" class="name">${p.pname}</a></dt>
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
