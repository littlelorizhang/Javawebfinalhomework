<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/2/27
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
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
<div class="log_bg">
    <div class="top">
        <div class="logo"><div class="logo_link"><a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/modelweb/images/logo.jpg"></a></div>
            <div class="phone">张大忽悠两元店</div></div>
    </div>

    <div class="regist">
        <div class="log_img"><img src="${pageContext.request.contextPath}/modelweb/images/register.jpg" width="611" height="425"></div>
        <div class="reg_c" id="registered">
            <div class="hd">
                <ul>
                    <li>普通注册</li>
                </ul>
            </div>
            <div class="bd">
                <ul>
                    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
                        <table border="0" style="width:420px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
                            <tbody><tr height="50" valign="top">
                                <td width="95">&nbsp;</td>
                                <td>
                                    <span class="fl" style="font-size:24px;">注册</span>
                                    <span class="fr">已有商城账号，<a href="${pageContext.request.contextPath}/login.jsp" style="color:#ff4e00;">我要登录</a></span>
                                </td>
                            </tr>

                            <tr height="50">
                                <td align="right"><span style="color: #ff4e00; ">*</span>&nbsp;用户名 &nbsp;</td>
                                <td><input type="text" value="" class="l_user" name="username"></td>
                            </tr>
                            <tr height="50">
                                <td align="right"><span style="color: #ff4e00; ">*</span>&nbsp;密码 &nbsp;</td>
                                <td><input type="password" value="" class="l_pwd" name="userpassword"></td>
                            </tr>
                            <tr height="50">
                                <td align="right"><span style="color: #ff4e00; ">*</span>&nbsp;邮箱 &nbsp;</td>
                                <td><input type="text" value="" class="l_email" name="useremail"></td>
                            </tr>
                            <tr height="50">
                                <td align="right"><span style="color: #ff4e00; ">*</span>&nbsp;省份 &nbsp;</td>
                                <td>
                                    <select name="useraddress">
                                        <option value="北京">北京</option><option value="天津">天津</option>
                                        <option value="上海">上海</option><option value="重庆">重庆</option>
                                        <option value="河北">河北</option><option value="山西">山西</option>
                                        <option value="辽宁">辽宁</option><option value="吉林">吉林</option>
                                        <option value="黑龙江">黑龙江</option><option value="江苏">江苏</option>
                                        <option value="浙江">浙江</option><option value="安徽">安徽</option>
                                        <option value="福建">福建</option><option value="江西">江西</option>
                                        <option value="山东">山东</option><option value="河南">河南</option>
                                        <option value="湖北">湖北</option><option value="湖南">湖南</option>
                                        <option value="广东">广东</option><option value="海南">海南</option>
                                        <option value="四川">四川</option><option value="贵州">贵州</option>
                                        <option value="云南">云南</option><option value="陕西">陕西</option>
                                        <option value="甘肃">甘肃</option><option value="青海">青海</option>
                                        <option value="台湾">台湾</option><option value="内蒙古">内蒙古</option>
                                        <option value="广西">广西</option><option value="西藏">西藏</option>
                                        <option value="宁夏">宁夏</option><option value="新疆">新疆</option>
                                        <option value="香港">香港</option><option value="澳门">澳门</option>
                                    </select>
                                </td>
                            </tr>
                            <tr height="60">
                                <td>&nbsp;</td>
                                <td><input type="submit" value="立即注册" class="log_btn"></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
