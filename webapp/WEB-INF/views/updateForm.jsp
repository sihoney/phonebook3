<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[Phonebook2]</h1>
	
	<h2>전화번호 수정폼</h2>
	
	<p>
		전화번호를 수정하려면 아래 항복을 기입하고 '수정'버튼을 클릭하세요.
	</p>
	
	<form action="./update" method="get">
		이름(name): <input type="text" name="name" value="${requestScope.pvo.name }"><br/>
		핸드폰(hp): <input type="text" name="hp" value="${requestScope.pvo.hp }"><br/>
		회사(company): <input type="text" name="company" value="${requestScope.pvo.company }"><br/>
		<input type="hidden" name="personId" value="${requestScope.pvo.personId }">
		<button type="submit">수정</button>
	</form>
</body>
</html>