<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/2/27
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
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
<%
{
    String message= (String) request.getSession().getAttribute("message");
    if(message!=null)
    response.getWriter().write("<script language=javascript>alert('"+message+"');</script>");
}
%>
<div class="log_bg">
    <div class="top">
        <div class="logo"><div class="logo_link"><a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/modelweb/images/logo.jpg"></a></div><div class="phone">张大忽悠两元店</div></div>
    </div>
    <div class="login">
        <div class="log_img"><img src="${pageContext.request.contextPath}/modelweb/images/register.jpg" width="611" height="425"></div>
        <div class="log_c">
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <table border="0"  cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr height="50" valign="top">
                        <td width="55">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;">登录</span>
                            <span class="fr">还没有商城账号，<a href="${pageContext.request.contextPath}/client/register.jsp" style="color:#ff4e00;">立即注册</a></span>
                        </td>
                    </tr>
                    <tr height="70">
                        <td>用户名</td>
                        <td><input type="text" value="" class="l_user" name="username"></td>
                    </tr>
                    <tr height="70">
                        <td>密&nbsp; &nbsp; 码</td>
                        <td><input type="password" value="" class="l_pwd" name="userpassword"></td>
                    </tr>
                    <tr height="70">
                        <td>身&nbsp; &nbsp; 份</td>
                        <td><input type="radio" value="client"  class="" name="userrole"/>普通用户
                            <input type="radio" value="employee" class="" name="userrole"/>销售人员
                            <input type="radio" value="manager" class="" name="userrole"/>管理人员
                        </td>

                    </tr>
                    <tr height="60">
                        <td>&nbsp;</td>
                        <td><input type="submit" value="登录" class="log_btn"></td>
                    </tr>

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
