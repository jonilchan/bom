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
            url:"mdItem/getMdItemTreeStringById",
            data:{
                itemId:data.itemId,
                invisible:data.invisible,
            },
            dataType:"json",
            success:function (res) {
                layer.close(index);
                $(".layui-code").text("\n" + res.data + "\n");
            }
        })
})
})