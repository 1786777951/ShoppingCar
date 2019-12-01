<%--
  Created by IntelliJ IDEA.
  User: Exception
  Date: 2019/11/30
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>购物</title>
</head>
<body>
    <c:if test="${!empty user}">
        <div align="center">
            当前用户:${user.name}
        </div>
    </c:if>
    <table align="center" border="1" cellpadding="0">
        <tr>
            <td>id</td>
            <td>名称</td>
            <td>价格</td>
            <td>购买</td>
        </tr>

        <c:forEach items="${products}" var="product" varStatus="st">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>

                    <form action="addOrderItem" method="post">

                        数量<input type="text" value="1" name="num">
                        <input type="hidden" name="pid" value="${product.id}">
                        <input type="submit" value="购买">
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>
