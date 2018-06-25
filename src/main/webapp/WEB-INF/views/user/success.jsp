<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">.errorClass{color: red}</style>
</head>
<body>
<form:form modelAttribute="user" method="post" action="">
    <form:errors path="*"/><br>
    日期类型：<form:input path="birthday"/><br>
    货币类型：<form:input path="salary"/><br>
    <form:errors path="salary" cssClass="errorClass"/>
</form:form>
</body>
</html>