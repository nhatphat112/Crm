$(document).ready(function (){
    $(".btn-delete-user").click(function (){

        var userId = $(this).attr("userId");
        var This = $(this);
        var access = 1;
        console.log("---------------------")

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/CRM/user/delete?userId="+userId,
        })

            .done(function (result) {
                if(result.includes("true")){
                    access = 2;
                    This.closest("tr").remove()

                } else {
                    access =2;
                    alert(result.toString())
                    console.log(result.toString())


                }


            });


    })

})
