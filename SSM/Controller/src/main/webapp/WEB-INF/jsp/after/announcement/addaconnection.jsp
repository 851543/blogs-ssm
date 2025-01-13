<%--
  Created by IntelliJ IDEA.
  User: 611
  Date: 2023/5/15
  Time: 21:02
  To change this template use File | Settings | File Templates.

  这里是添加链接
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style type="text/css">
    .mc{
        height: 30px;
        margin-top: 5px;
    }
    .url{
        height: 30px;
        margin-top: 5px;
    }
    .lxfs{
        height: 30px;
        margin-top: 5px;
    }
    .ms{
        height: 30px;
        margin-top: 5px;
    }
    .or{
        height: 30px;
        margin-top: 5px;
    }
    .an{
        background-color: aqua;
        width: 70px;
        height: 40px;
        font-size: 20px;
    }
    #bg{
        margin-left: 2%;
        text-align: left;
        height: 150px;
    }
    .biaoge{
        margin-left: 450px;
        margin-top: -500px;
        width: 1000px;
        position: fixed;
        overflow: hidden;

    }
</style>
<body>
<form action="" class="lj">
        <ul style="list-style: none;">
            <li>编辑链接</li>
        </ul>
        <ul style="list-style: none;">
            <li>名称</li>
            <li><input type="text" class="mc"></li>
        </ul>
        <ul style="list-style: none;">
            <li>URL</li>
            <li><input type="text"class="url"></li>
        </ul>
        <ul style="list-style: none;">
            <li>联系方式</li>
            <li><input type="text" placeholder="" class="lxfs"></li>
        </ul>
        <ul style="list-style: none;">
            <li>描述</li>
            <li><input type="text" placeholder="" class="ms"></li>
        </ul>
        <ul style="list-style: none;">
            <li>Order</li>
            <li><input type="text" placeholder="" class="or"></li>
        </ul>
        <ul style="list-style: none;">
            <li><button class="an">添加</button></li>
        </ul>
        <table border="1" id=bg>
            <tr>
                <th>温馨提示<br />
                    URL:如http://www.baidu.com<br />
                    Order:默认是0,Order越大排名越靠前
                </th>
            </tr>
        </table>
        <table border="1" class="biaoge">
            <tr>
                <td>id</td>
                <td>名称</td>
                <td>URL</td>
                <td>Order</td>
                <td>操作</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <button style="background-color: aquamarine; color: aliceblue;">编辑</button>
                    <button style="background-color: red; color: aliceblue;">删除</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
