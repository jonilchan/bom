layui.use(['form', 'jquery', 'layer'], function () {

    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    form.on('submit(searchBtn)', function (data) {
    data = data.field;
    //加载
    index = layer.load(1);
    $.ajax({
        type:"get",
        url:"mdItem/getMdItemTraceByCode?code=" + data.code,
        dataType:"json",
        success:function (data) {
            layer.close(index);
            layer.msg(data.data,{
                icon: 1,
                time: 4000, //2秒关闭（如果不配置，默认是3秒）
                shade: [0.4, '#000', true],
                maxWidth: 500
            })
        }
    })
});
})
