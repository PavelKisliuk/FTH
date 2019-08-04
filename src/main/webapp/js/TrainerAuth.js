$(function () {
    var host = "/FTH/start";
    $(".wrapper").ready(function () {
        $.get(host,
            {
                "command" : "IN_SYSTEM",
                "str": "TRAINER_PAGE",
                "condition" : "true"
            }, request).fail(function(xhr, status, error) {
            alert("Error");
        });
        function request(response) {
            if (response.page) {
                window.location.href = response.page;
            }
        }
    });


    $(".login-button").click(function () {
        $.post(host,
            {
                "command": "TRAINER_SING_IN",
                "emailSingIn": $("#login").val(),
                "passwordSingIn": $("#password").val()
            }, request).fail(function(xhr, status, error) {
            alert("Error");
        });

        function request(response) {
            if(response.message) {
                alert(response.message);
            }
            if (response.redirect) {
                window.location.href = response.redirect;
            }
        }
    });
});