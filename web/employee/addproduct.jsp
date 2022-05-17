<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/3/6
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上新商品</title>
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
            <div class="Category"><a href="#"><em></em>上架商品</a></div>
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
<!--商品管理-->
<div class="right_style">

    <div class="frame user_address clearfix">
        <form  action="${pageContext.request.contextPath}/AddProductServlet?user=${user}" method="post" enctype="multipart/form-data">
            <div class="Add_Addresss">
                <div class="title_name"><i></i>添加商品</div>
                <table>

                    <tr>
                        <td class="label_name">商品id</td><td><input name="productid" type="text"  class="Add-text"/><i>（必填）</i></td>
                        <td class="label_name">商品名称</td><td><input name="productname" type="text"  class="Add-text"/><i>（选填）</i></td>
                    </tr>
                    <tr>
                        <td class="label_name">商品类别</td><td><input name="producttype" type="text" value="${user.getUtype()}"  class="Add-text"/><i>（必填）</i></td>
                        <td class="label_name">商品价格</td><td><input name="productprice" type="text"   class="Add-text"/><i>（必填）</i></td>

                    </tr>
                    <tr><td class="label_name">商品库存</td><td><input name="productnum" type="text"  class="Add-text"/><i>（必填）</i></td>
                        <td class="label_name">商品图片</td><td><input name="myfile" type="file" size="30" value="" class="Add-text"/><i>（必填）</i></td>
                    <tr><td class="label_name"></td><td></td><td class="label_name"></td><td></td>
                    </tr>
                </table>

                <div class="btn"><input name="1" type="submit" value="添加商品" class="Add_btn" /></div>

            </div>
        </form>
    </div>
</div>
</body>
</html>
