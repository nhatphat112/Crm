$(document).ready(function (){
   $(".btn-delete-role").click(function (){
       var roleId = $(this).attr("roleId");
       var This = $(this);
       $.ajax({
           url: "http://localhost:8080/CRM/role/delete",
            method : "post",
           data :{"id":roleId},
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