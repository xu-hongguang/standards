$(function () {
    $("tr:odd").css("background-color", "#ccc");

    $(".add").click(function () {
        location.href = "/standard/add";
    });

    $("#checkAllOrNo").click(function () {
        /*if (this.checked) {
            //全选
            $('[name=id]:checkbox').prop("checked", true);
        } else {
            //全不选
            $('[name=id]:checkbox').prop("checked", false);
        }*/

        $('[name=id]:checkbox').prop("checked", this.checked);

    });

    /**还需要为每一个子checkbox绑定事件呢
     ，当下面的子checkbox全部都选中的时候，上面的全选按扭
     也应该要全部选中
     */
    $("[name=id]:checkbox").click(function () {//点击每一个子checkbox，都会触发这个事件

        /**获取到下面所有checkbox*/
        var boxs = $("[name=id]:checkbox");
        /*
          * 下面是为checkAllOrNo设置它是否checked属性
            boxs.length表示所有子checkbox的个数
            boxs.filter(":checked").length表示是过滤出所有
                   子checkbox当中已经被点击选中的checkbox的个数，如果个数相等，
                   那么就会返回true,如果不相等，就返回false
        */
        $("#checkAllOrNo").prop("checked", boxs.length === boxs.filter(":checked").length);
    });
});

function goListForm(pageNo) {
    $("#pageNo").val(pageNo);

    $("form:first").submit();
}

function update(id) {
    location.href = "/standard/update/" + id;
}

function deleteStandard() {
    //获取选择的的多选框
    var oCheck = $('[name=id]:checkbox:checked');
    if (oCheck.length === 0) {
        alert("没有选择删除标准！");
        return;
    } else {
        if (confirm("你确定要删除吗？？？")) {
            /**
             调用map方法，会把函数里面的返回值作为jquery对象--res返回
             注意，这里的res.toArray()等同于res.toArray().join(",")
             */
            var res = oCheck.map(function () {
                return this.value;
            });
            alert(res.toArray().join(","));
            location.href = "/standard/delete/" + res.toArray().join(",");
        }
    }
}