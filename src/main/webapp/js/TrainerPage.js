$(function () {
    $(".client-base__list").ready(function () {
        $.get("http://localhost:8080/FTH/start",
            {
                "command" : "IN_SYSTEM",
                "str": "TRAINER_AUTH",
                "condition" : "false"
            }, request).fail(function(xhr, status, error) {
            alert("Error");
        });
        function request(response) {
            if (response.page) {
                window.location.href = response.page;
            } else {
                $.get("http://localhost:8080/FTH/start",
                    {
                        "command": "TRAINER_PAGE"
                    }, request).fail(function(xhr, status, error) {
                    alert("Error");
                });
                //
                function request(response) {
                    if (response.errorRedirect) {
                        window.location.href = response.errorRedirect;
                    } else {
                        insertTrainerData(response.trainer);
                        var count = 1;
                        // $.each(response.client, function(index, value){
                        insertClientGroup(response.client);
                        // });
                    }
                }
            }
        }

    });

    function insertTrainerData(data) {
        var name = data[0].name;
        var surname = data[0].surname;
        var photo = data[0].photoPath;

        var eTable = "<img class=\"couch-info__photo\" width=\"70px\" height=\"70px\" alt=\"Фото тренера\" " +
            "src=\"" + photo + "\">\n" +
            "<p class=\"couch-info__name\">" + name + " " + surname + "</p>\n";
        $('#trainer').html(eTable);
    }

    function insertClientGroup(data)
    {
        var eTable = "";

        $.each(data, function(index, value) {
            var id = value.clientID;
            var name = value.firstName;
            var surname = value.lastName;
            var photo = value.photoPath;

            eTable += "<li class=\"client-base__actual client-" + index + "\" id=\"" + id + "\" >\n" +
                "<span class=\"client-base__notification\"></span>\n" +
                "<img class=\"client-base__photo\" width=\"30px\" height=\"30px\" alt=\"Фото клиента\" " +
                "src=\"" + photo + "\">\n" +
                "<p>" + name + " " + surname + "</p>\n" + "</li>\n";
            $('#clients').html(eTable);
        });
    }
});