layui.use(['form', 'jquery', 'layer', 'code'], function(){

    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    layui.code();

    form.on('submit(searchBtn)', function (data) {
    data = data.field;
    //加载
    index = layer.load(1);
    $.ajax({
        type:"post",
        url:"mdItem/getMdItemTraceByCode",
        data:{
            code:data.code,
        },
        dataType:"json",
        success:function (res) {
            layer.close(index);
            let strs = res.data.substring(1, res.data.length - 1).split(",");
            let str = " ";
            for (let i = 0; i < strs.length; i++) {
                str += strs[i];
                str += "\n";
            }
            $(".layui-code").text("\n" + str + "\n");
        }
    })
});
})
