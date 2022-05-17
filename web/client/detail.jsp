<%@ page import="domain.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/2/28
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情</title>
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
<!--顶部信息-->
<div id="header_top">
    <div id="top">
        <div class="Inside_pages">
            <div class="Collection">欢迎光临张大忽悠两元店</div>
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

<%
    Product p=new Product();
    p= (Product) request.getSession().getAttribute("p");
    String pid=p.getPid();
    String pname=p.getPname();
    request.getSession().setAttribute("pid",pid);
    request.getSession().setAttribute("pname",pname);
    request.getSession().setAttribute("ptype",p.getPtype());
    request.getSession().setAttribute("price",p.getPrice());

%>

<!--商品详情-->
<div id="Menu" class="clearfix">
    <div class="index_style clearfix">
        <div id="allSortOuterbox" class="display">
            <div class="t_menu_img"></div>
            <div class="Category"><a href="#"><em></em>商品详情信息</a></div>
        </div>
        <div class="Navigation" id="Navigation">
            <ul class="Navigation_name">
                <li><a href="${pageContext.request.contextPath}/RecordServlet?user=${user}&pid=${pid}&pname=${pname}&ptype=${ptype}&starttime=${starttime}">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp">未登录点击此处返回</a></li>
            </ul>
        </div>
    </div>
</div>
<!--商品详情-->
<div class="Inside_pages">
    <div class="Location_link">
        <em></em> ${p.getPtype()}&lt;${p.getPname()}
    </div>
    <div class="Details_style clearfix" id="goodsInfo">
        <div class="mod_picfold clearfix">
            <div class="clearfix" id="detail_main_img">
                <div class="layout_wrap preview">
                    <div id="vertical" class="bigImg">
                        <img src="${pageContext.request.contextPath}/modelweb/product/${p.getPid()}.jpg" width="" height="" alt="" id="midimg" />
                    </div>

                    <div id="bigView" style="display:none;"><div><img width="800" height="800" alt="" src="" /></div></div>
                </div>

            </div>
        </div>



<div class="textInfo">
<form id="buy" action="${pageContext.request.contextPath}/AddCartServlet?user=${user}&ptype=${ptype}&starttime=${starttime}" >
    <div class="title"><h1>${p.getPname()}</h1><p>物美价廉，只要两元</p></div>
    <div class="mod_detailInfo_priceSales">
        <div class="margins">
            <div class="Price clearfix"><label>价格</label><span>¥${p.getPrice()}</span></div>
        </div>
    </div>

    <div class="buyinfo" id="detail_buyinfo">
        <dl>
            <dt>数量</dt>
            <dd>
                <div class="choose-amount left">

                    <input type="number" id="num" value="1" name="num" >

                </div>
                <div class="P_Quantity">剩余：${p.getPnum()}</div>
            </dd>
            <dd>
                <div class="wrap_btn">
                    <c:choose >
                        <c:when test="${ not empty user.getUname()}">
                            <a href="javascript:document:buy.submit();"  class="wrap_btn1" title="加入购物车" ></a>

                    </c:when>
                        <c:otherwise>
                            <a href="#" class="wrap_btn1" title="加入购物车" onclick="alert('请先登录')"></a>

                        </c:otherwise>
                    </c:choose>
                </div>
            </dd>
        </dl>
    </div>

</form>
</div>
</div>

</div>
</body>
</html>
