<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/3/2
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/fonts/iconfont.css" type="text/css">
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/footer.js" type="text/javascript"></script>
</head>
<body >
<%
    {
        String message= (String) request.getSession().getAttribute("message");
        if(message!=null)
            response.getWriter().write("<script language=javascript>alert('"+message+"');</script>");
    }
%>
<!--顶部信息-->
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
<!--划分栏-->
<div id="Menu" class="clearfix">
    <div class="index_style clearfix">
        <div id="allSortOuterbox" class="display">
            <div class="t_menu_img"></div>
            <div class="Category"><a href="#"><em></em>商品详情信息</a></div>
        </div>
        <div class="Navigation" id="Navigation">
            <ul class="Navigation_name">
                <li><a href="${pageContext.request.contextPath}/index.jsp?user=${user}">首页</a></li>
            </ul>
        </div>
    </div>

</div>

<!--购物车内容-->
<div class="Inside_pages">
    <div class="Process_img"><img src="${pageContext.request.contextPath}/modelweb/images/Process_img_01.png" /></div>
    <div class="content mar_20">

        <table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
    <c:forEach items="${c}" var="c">
            <tbody><tr>
                <td class="car_th" width="490">商品名称</td>
                <td class="car_th" width="150">购买数量</td>
                <td class="car_th" width="130">小计</td>
                <td class="car_th" width="150">操作</td>
            </tr>

           <tr>
                <td>
                    <div class="c_s_img"><img src="${pageContext.request.contextPath}/modelweb/product/${c.c_pid}.jpg" width="73" height="73"></div>
                    ${c.c_pname}
                </td>

                <td align="center">
                    <div class="c_num">
                        <input type="n" value="${c.cnum}" name="num" class="car_ipt">
                    </div>
                </td>
                <td align="center" style="color:#ff4e00;">￥${c.cprice}.00</td>

                <td align="center"><a href="${pageContext.request.contextPath}/DeleteCartServlet?user=${user}&cid=${c.cid}">删除</a>&nbsp; &nbsp;<a href="${pageContext.request.contextPath}/PurchaseServlet?user=${user}&c_pid=${c.c_pid}&cnum=${c.cnum}&cid=${c.cid}&cprice=${c.cprice}">购买</a></td>
            </tr>
            </tbody>
            </c:forEach>
        </table>

    </div>
</div>


</body>
</html>
