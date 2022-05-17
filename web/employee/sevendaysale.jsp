<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.User" %>
<%@ page import="utils.JDBCUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="domain.Operate" %>
<%@ page import="org.apache.commons.lang3.RandomStringUtils" %>
<%@ page import="dao.OperateDao" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 28358
  Date: 2022/3/18
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品近七天销售量</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/modelweb/fonts/iconfont.css" type="text/css">
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/footer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/lrtk.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/modelweb/js/echarts.js" type="text/javascript"></script>
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
            <div class="Category"><a href="#"><em></em>查看销量</a></div>
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
    List<Integer> datas= (List<Integer>) request.getSession().getAttribute("datas");
    List<String>days= (List<String>) request.getSession().getAttribute("time");
    request.getSession().setAttribute("datas",datas);
    request.getSession().setAttribute("days",days);


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
    operate.setOtype("近七天商品销量");
    operate.setOid(RandomStringUtils.randomNumeric(10));
    OperateDao dao1=new OperateDao();
    try {
        dao1.insert(operate);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>

<!--近七天商品销售折线图-->
<div class="right_style">
    <div class="frame user_address clearfix">
        <div id="main" style="width: 900px;height:600px;"></div>
        <script type="text/javascript">
            var rate=new Array()
            var index=0;
            <c:forEach items="${datas}" var="p">
            rate[index]=${p};
            index++;
            </c:forEach>
            var myChart = echarts.init(document.getElementById('main'));
            option = {
                color: ['#F153AB'],
                title: {
                    text: '七天销售额',

                    // verticalAlign: top,
                    left:'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    //图例
                    // data: ['2011年', '2012年']
                    show: false
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    data:// ['巴西','印尼','美国','印度','中国','世界人口(万)']
                        [ <c:forEach items="${days}" var="p">
                            ["${p}"],
                            </c:forEach> ]
                },
                yAxis: {

                    type: 'value',
                    boundaryGap: [0, 1]

                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name: '2022年',
                        type: 'line',
                        data: rate
                    },
                ]

            };
            myChart.setOption(option);

        </script>
    </div>
</div>

</body>
</html>
