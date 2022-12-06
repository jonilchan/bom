layui.use(['table', 'layer', "form"], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //物料清单
    var tableIns = table.render({
        elem: '#list',
        url: 'mdItem/getMdItemListByIds',
        cellMinWidth: 50,
        page: false,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "list",
        cols: [[
            {field: "itemId", title: '物料ID', fixed: "right", width: 150, align: "center"},
            {field: 'itemCode', title: '物料编码', minWidth: 50, align: "center"},
            {field: 'itemName', title: '物料名称', minWidth: 50, fixed: "right", align: "center"},
            {field: 'itemClassCode', title: '物料分类代码', minWidth: 50, fixed: "right", align: "center"},
            {field: 'itemClassName', title: '物料分类名称', minWidth: 50, fixed: "right", align: "center"},
            {field: 'childQuality', title: '物料数量', minWidth: 50, fixed: "right", align: "center"},
        ]],
        response: {
            statusName: 'code', //规定返回的状态码字段为code
            statusCode: 200 //规定成功的状态码味200
        },
    });

    // 多条件搜索
    $(".search_btn").on("click", function () {
        table.reload("list", {
            where: {
                ids: $("input[name='ids']").val()//  ids
            }
        })
    });

});
