<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>データ参照</title>
<link rel="stylesheet" href="/css/simple.css" />
<link rel="stylesheet" href="/css/flexigrid.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<script src="/js/flexigrid.js"></script>
<script src="/js/query.js"></script>

</head>
<body>
    <h4 class="css_title">艦これライブラリ</h4>
    <form
        action="/gaesimple/query"
        method="post"
        id="queryform">

        <br>
        <div>
            <span>データタイプ</span>
            <select name="file_type" id="file_type" class="file_type" >
                <option value="ships" selected>艦種</option>
                <option value="event_arp_2">アルペジオE-2</option>
                <option value="event_arp_3">アルペジオE-3</option>
            </select>
            <span id="sort_type_area">ソートタイプ
            <select name="sort_type" id="sort_type" class="file_type" >
                <option value="ship_no" selected>No</option>
                <option value="ship_type">艦種</option>
                <option value="rarity">レア度</option>
            </select>
            </span>
        </div>
        <br>
        <input type="button" value="query"" id="query" class="css_button_ok">

        <br>

        <div id="grid_ships_area">
        <table id="grid_ships">
            <tbody>
                <tr>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </tbody>
        </table>
        </div>

        <div id="grid_event2_area">
        <table id="grid_event2">
            <tbody>
                <tr>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </tbody>
        </table>
        </div>

        <div id="grid_event3_area">
        <table id="grid_event3">
            <tbody>
                <tr>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </tbody>
        </table>
        </div>
    </form>
</body>
</html>
