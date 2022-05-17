<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/3/5
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<html>
<head>
    <title>员工首页</title>
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

        String message= (String) request.getSession().getAttribute("message");
        if(message!=null)
            response.getWriter().write("<script language=javascript>alert('"+message+"');</script>");

%>
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
            <div class="Category"><a href="#"><em></em>所有功能</a></div>
            <div class="hd_allsort_out_box_new">
                <ul class="Menu_list">
                    <li class="name">
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/addproduct.jsp?user=${user}" >上新商品</a> <span><</span></div>
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/deleteproduct.jsp?user=${user}" >下架商品</a> <span><</span></div>
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/changeproduct.jsp?user=${user}" >修改商品</a> <span>&lt;</span></div>
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/showsale.jsp?user=${user}" >查看销售状态</a> <span>&lt;</span></div>
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/showrecord.jsp?user=${user}" >查看浏览日志</a> <span>&lt;</span></div>
                        <div class="Menu_name"><a href="${pageContext.request.contextPath}/employee/showorder.jsp?user=${user}" >查看购买日志</a> <span>&lt;</span></div>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%request.getSession().removeAttribute("message");%>
</body>
</html>
