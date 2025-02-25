<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>getBoardList</title>

    <script defer>
        alert("isRemoved: ${requestParam.isRemoved}");
    </script>
</head>
<body>
    <h3> <%= request.getRequestURI() %> </h3>
    <hr>

    <p>${_BOARD_LIST_}</p>
</body>
</html>