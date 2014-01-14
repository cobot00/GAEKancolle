<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>file upload</title>
<link rel="stylesheet" href="/css/simple.css" />
</head>
<body>
    <h4 class="css_title">CSVデータアップロード</h4>
    <form
        action="/gaesimple/upload"
        enctype="multipart/form-data"
        method="post"
        id="uploadform">

        <br>
        <br>
        <div class="file_type_area">
            <span>ファイルタイプ</span>
            <select name="file_type" class="file_type" >
                <option value="ships" selected>艦種</option>
                <option value="event_arp_2">アルペジオE-2</option>
                <option value="event_arp_3">アルペジオE-3</option>
            </select>

        </div>

        <input type="file" name="load_file" value="FILE" id="load_file" class="css_button">
        <input type="submit" value="upload" id="upload" class="css_button_ok">

    </form>
</body>
</html>
