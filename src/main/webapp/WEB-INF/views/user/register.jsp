<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增用户</title>
</head>
<body>
    <!-- 将表单提交到/user.html控制器中 -->
    <form method="post" action="<c:url value="/user.html"/>">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="userName"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="realName"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="提交"/></td>
            </tr>
        </table>
    </form>

</body>

</html>