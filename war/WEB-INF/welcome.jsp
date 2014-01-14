<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome</title>
<link rel="stylesheet" href="/css/simple.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<script src="/js/simple.js"></script>
</head>
<body>
    <h1>Registory your information</h1>
    <form action="/gaesimple" enctype="multipart/form-data" method="post" id="uploadform">
        <br>
        ID:<input type="text" name="id">
        <br>
        名前:<input type="text" name="name">

        <br>
        <br>
        <input type="submit" value="entry">

        <br>
        <br>
        <input type="file" value="FILE" id="load_file" class="css_button">
        <input type="button" value="upload" id="upload" class="css_button_ok">
        <input type="submit" value="CANCEL" class="css_button_cancel">
    </form>
</body>
</html>
