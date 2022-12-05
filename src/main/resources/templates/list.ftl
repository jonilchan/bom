<!DOCTYPE html>
<html>
<head>
    <title>采购清单</title>
    <#include "./common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="ids"
                           class="layui-input
					searchVal" placeholder="ids,输入多个id用逗号分割"/>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="list" class="layui-table" lay-filter="datas"></table>

</form>
<script type="text/javascript" src="js/list.js"></script>

</body>
</html>