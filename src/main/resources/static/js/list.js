layui.use(['table', 'layer', "form"], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //物料清单
    var tableIns = table.render({
        elem: '#list',
        url: 'mdItem/getMdItemListByIds',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "list",
        cols: [[
            {field: "item_id", title: '物料ID', fixed: "true", width: 150},
            {field: 'item_code', title: '物料编码', minWidth: 50, align: "center"},
            {field: 'item_name', title: '物料名称', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'item_class_code', title: '物料分类代码', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'item_class_name', title: '物料分类名称', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'item_quantity', title: '物料数量', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'crt_user', title: '创建者', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'crt_time', title: '创建时间', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'upd_user', title: '更新者', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"},
            {field: 'upd_time', title: '更新时间', minWidth: 300, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click", function () {
        table.reload("list", {
            page: {
                curr: 1
            },
            where: {
                ids: $("input[name='ids']").val().split(",")//  ids
            }
        })
    });

});
