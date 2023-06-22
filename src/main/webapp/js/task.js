$(document).ready(function (){
    $(".btn-delete-task").click(function (){
        var userId = $(this).attr("userId");
        var roleId = $(this).attr("roleId");
        var jobId =  $(this).attr("jobId");
        var statusId =  $(this).attr("statusId");
        var projectId =  $(this).attr("projectId");
        var methodCustom =  $(this).attr("methodCustom");

        var This = $(this);
        $.ajax({
            url: "http://localhost:8080/CRM/task/delete?userId="+userId+"&roleId="+roleId+"&jobId="+jobId+"&statusId="+statusId+"&projectId="+projectId+"&methodCustom="+methodCustom,
            method : "get",
            success: function(response) {
                if(response.toString().localeCompare("true")){
                    This.closest("tr").remove();
                }else {
                    alert("You Don't have permission to delete")
                }
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    })
})