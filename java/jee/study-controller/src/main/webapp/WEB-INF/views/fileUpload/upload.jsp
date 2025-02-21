<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File Upload Form</title>
</head>
<body>
    <!-- <h3>/WEB-INF/views/fileUpload/upload.jsp</h3> -->
     <h3><%= request.getRequestURI() %></h3>
     <hr>

     <form action="/fileUpload/doit" method="post" enctype="multipart/form-data">        
        <ul>
        	<li>File Uploader: <input type="text" name="uploader" size="10" maxlength="10"></li>
        	<li><input type="file" name="files" multiple="multiple"></li>
	        <li><input type="file" name="files"></li>
	        <li><input type="file" name="files"></li>
	        <li><input type="file" name="files"></li>
	        <li><input type="file" name="files"></li>
	        <li></li>
	        <input type="submit" value="Upload">
        </ul>
     </form>

</body>
</html>