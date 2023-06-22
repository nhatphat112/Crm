$(document).ready(function (){
    $(".btn-delete-groupwork").click(function (){
        var projectId = $(this).attr("projectId");
        var This = $(this);
        $.ajax({
            method: "post",
            url: "http://localhost:8080/CRM/groupwork/delete",
            data:{"id":projectId}
        })
            .done(function (result) {
                if(result.toString().localeCompare("true")){
                    This.closest("tr").remove();
                }else {
                    alert("You Don't have permission to delete")
                }
            });
    })
})