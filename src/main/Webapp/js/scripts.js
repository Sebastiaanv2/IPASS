$(document).ready(function () {
    fillSchema(myschemadummy);
    fillStats(mystatsdummy);
    fillPlayers(myplayersdummy);
    setDate();
});

var myschemadummy = [
    {"matchid": "51", "speler1": "Michael Prinsen", "speler2": "Sebastiaan van Rijn"},
    {"matchid": "11", "speler1": "Freek Verhoeven", "speler2": "Jan Versteeg"}
];

var mystatsdummy = [
    {"rank": "1", "spelernaam": "Michael Prinsen", "doelsaldo": "12", "tegenpunten": "4", "matchesGespeeld": "1"},
    {"rank": "2", "spelernaam": "Sebastiaan van Rijn", "doelsaldo": "4", "tegenpunten": "12", "matchesGespeeld": "1"}
];

var myplayersdummy = [
    {"spelerid": "1", "spelernaam": "Michael Prinsen"},
    {"spelerid": "2", "spelernaam": "Sebastiaan van Rijn"},
    {"spelerid": "3", "spelernaam": "Freek Verhoeven"},
    {"spelerid": "4", "spelernaam": "Jan Versteeg"},
    {"spelerid": "5", "spelernaam": "Pieter de Vos"},
    {"spelerid": "6", "spelernaam": "Kerel achternaam"}
];

//function to fill the Wedstrijdschema table.
function fillSchema(json) {
    $.each(json, function (index, data) {
        console.log(json);
        var schema =
            "<tr id='" + data.match_id + "'>" +
            "<td class='match_id'>" + data.matchid + "</td>" +
            "<td>" + data.speler1 + "</td>" +
            "<td>" + data.speler2 + "</td>" +
            "<td class='text-center'><input class='s1' type='number'></td>" +
            "<td class='text-center'><input class='s2' type='number'></td>" +
            "</tr>";
        $("#wSchema").append(schema);
    });
    console.log("fillschema executed");
}

//function to fill the stats table.
function fillStats(json) {
    $.each(json, function (index, data) {
        console.log(json);
        var stats =
            "<tr id='" + data.rank + "'>" +
            "<td class='rank'>" + data.rank + "</td>" +
            "<td>" + data.spelernaam + "</td>" +
            "<td>" + data.doelsaldo + "</td>" +
            "<td>" + data.tegenpunten + "</td>" +
            "<td>" + data.matchesGespeeld + "</td>" +
            "</tr>";
        $("#Pstats").append(stats);
    });
    console.log("fillstats executed");
}

//function to fill the players
function fillPlayers(json) {
    $.each(json, function (index, data) {
        console.log(json);
        var plyrlist =
            "<tr id='" + data.spelerid + "'>" +
            "<td class='spelerid'>" + data.spelerid + "</td>" +
            "<td>" + data.spelernaam + "</td>" +
            "<td class='text-center'><input type='checkbox'></td>" +
            "</tr>";
        $("#Plist").append(plyrlist);
    });
    console.log("fillplayers executed");
}

//submit the scores when scores opslaan is clicked.
$("#scoreSubmit").click(function () {
    $('#wSchema').find('tr').each(function () {
        var matchid = $(this).find(".match_id").html();
        var s1 = $(this).find(".s1").val();
        var s2 = $(this).find(".s2").val();
        console.log(s1);
        console.log(s2);
        if (s1 === "" || s2 === "") {
            var string = "vul iets in...<br>"
        } else {
            var string = "matchid: " + matchid + " | player1 heeft als score: " + s1 + " | player1 heeft als score: " + s2 + "<br>";
        }
        $("#scoreTest").append(string);
    });
});

//skip week
$("#skipWeek").click(function () {
    var string = "Skip week!<br>";
    $("#scoreTest").append(string);
});

//create new player using input when maak nieuwe speler is clicked
$("#CreatePlayer").click(function () {
    var pname = $("#playername").val();
    console.log(pname);
    $("#usercreatedtest").append(pname + "<br>");
});

//delete players that are checked when verwijder speler(s) is clicked.
$("#deletePlayer").click(function () {
    $("#Plist").find("tr").each(function () {
        if ($(this).find('input[type="checkbox"]').is(":checked")) {
            $("#SelectedPlayers").append($(this).find('.spelerid').text() + "<br>");
        }
    });
});

//login procedure | if password is empty error | else close modal and login.
$("#Loginbtn").click(function () {
    console.log("tryed to login");
    var pass = $("#loginpass").val();
    if (pass !== "") {
        $("#LoginModal").modal("toggle");
        alert("Login Succesvol!");
    } else {
        $("#passwordcheck").html("Wachtwoord incorrect!");
    }
});

//
function setDate() {
    var n = new Date();
    var y = n.getFullYear();
    var m = n.getMonth() + 1;
    var d = n.getDate();
    document.getElementById("curdate").innerHTML = d + "-" + m + "-" + y;
    console.log("setdate executed.")
}
