<!DOCTYPE html>
<html>
<head>
    <title>物料追溯接口</title>
    <#include "./common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label required">物料ID</label>
                    <div class="layui-input-inline">
                        <input type="text" name="code" lay-verify="required" lay-reqtext="参数不能为空" placeholder='物料ID' } class="layui-input"></div>
                    <div class="layui-input-inline">
                        <button type="button" class="layui-btn" lay-submit lay-filter="searchBtn"><i class="layui-icon">&#xe615;</i>查询</button>
                    </div>
                </div>
            </div>
        </form>
    </blockquote>
</form>

<script type="text/javascript" src="js/trace.js"></script>

</body>
</html>