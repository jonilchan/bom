
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>BOM模块</title>
    <link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="css/layuimini.css" media="all">
    <link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="css/formSelects-v4.css" media="all">
    <link rel="stylesheet" href="css/public.css" media="all">
    <link rel="stylesheet" href="css/iconfont.css">
    <script src="lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script src="js/lay-config.js" charset="utf-8"></script></head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <h1>BOM模块</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-indent" data-side-fold="0"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
            <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">

                <li class="layui-nav-item">
                    <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                       data-tab="notTree" target="_self"><i class="iconfont icon-sousuo">&nbsp;</i><span
                                class="layui-left-nav">物料清单</span></a>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                       data-tab="tree" target="_self"><i class="iconfont icon-sousuo">&nbsp;</i><span
                                class="layui-left-nav">物料清单（树状）</span></a>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                       data-tab="trace" target="_self"><i class="iconfont icon-quanwenjieyue">&nbsp;</i><span
                                class="layui-left-nav">物料追溯接口</span></a>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                       data-tab="mdItemList" target="_self"><i class="iconfont icon-paixingbang">&nbsp;</i><span
                                class="layui-left-nav">批量物料清单</span></a>
                </li>

                <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
