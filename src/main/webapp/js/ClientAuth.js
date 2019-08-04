$(function () {
    $(".btn").click(function () {
        $(".form-signin").toggleClass("form-signin-left");
        $(".form-signup").toggleClass("form-signup-left");
        $(".frame").toggleClass("frame-long");
        $(".signin-active").toggleClass("signin-inactive");
        $(".signup-inactive").toggleClass("signup-active");
        $(".forgot").toggleClass("forgot-left");
        $(this).removeClass("idle").addClass("active");
    });
});

$(function () {
    var host = "/FTH/start";
    $(".btn-signup").click(function () {
        // $(".nav").toggleClass("nav-up");
        // $(".form-signup-left").toggleClass("form-signup-down");
        // $(".success").toggleClass("success-left");
        // $(".frame").toggleClass("frame-short");

        $.post(host,
            {
                "command": "SING_UP",
                "name": $("#nameSingUp").val(),
                "surname": $("#surnameSingUp").val(),
                "email": $("#emailSingUp").val(),
                "password": $("#passwordSingUp").val(),
                "confirmPassword": $("#confirmPasswordSingUp").val()
            }, request);

        function request(response) {
            alert(response.message);
            if (response.redirect) {
                window.location.href = response.redirect;
            }
        }
    });
});
//
$(function () {
    $(".btn-signin").click(function () {
        var stringParameter = $("#email").val();
        $.get("http://localhost:8080/FTH/start", {"stringParameter": stringParameter}, onAjaxSuccess, "text");

        function onAjaxSuccess(data) {
            // Здесь мы получаем данные, отправленные сервером и выводим их на экран.
            alert(data);
        }

        $('#email').val("213212");
        // alert('5445454');
        // document.location.href = "http://localhost:8080/FTH/start";
//   $(".btn-animate").toggleClass("btn-animate-grow");
//   $(".welcome").toggleClass("welcome-left");
//   $(".cover-photo").toggleClass("cover-photo-down");
//   $(".frame").toggleClass("frame-short");
//   $(".profile-photo").toggleClass("profile-photo-down");
//   $(".btn-goback").toggleClass("btn-goback-up");
//   $(".forgot").toggleClass("forgot-fade");
    });
});