$(document).ready(function () {
    fillSchema();
    fillStats(mystatsdummy);
    fillPlayers();
    fillRules();
    setDate();

    if(sessionStorage.length > 0){
        $("#Login").text('Logged in');
        $("#Login").toggleClass('bg-danger bg-success');
    }

    $(function(){
        $('.carousel').carousel({
            interval: 2000
        });
    });

});

//TODO: tekst slider op frontpage

var myschemadummy = [
    {"matchid": "51", "speler1": "Michael Prinsen", "speler2": "Sebastiaan van Rijn"},
    {"matchid": "11", "speler1": "Freek Verhoeven", "speler2": "Jan Versteeg"}
];

var mystatsdummy = [
    {
        "rank": "2",
        "spelernaam": "Michael Prinsen",
        "punten": "2",
        "doelsaldo": "12",
        "tegenpunten": "4",
        "matchesGespeeld": "1"
    },
    {
        "rank": "1",
        "spelernaam": "Sebastiaan van Rijn",
        "punten": "50",
        "doelsaldo": "4",
        "tegenpunten": "12",
        "matchesGespeeld": "1"
    }
];

var myplayersdummy = [
    {"spelerid": "1", "spelernaam": "Michael Prinsen"},
    {"spelerid": "2", "spelernaam": "Sebastiaan van Rijn"},
    {"spelerid": "3", "spelernaam": "Freek Verhoeven"},
    {"spelerid": "4", "spelernaam": "Jan Versteeg"},
    {"spelerid": "5", "spelernaam": "Pieter de Vos"},
    {"spelerid": "6", "spelernaam": "Kerel achternaam"}
];

var myrulesdummy = {"rule1": "1", "rule2": "2", "rule3": "3", "rule4": "4", "rule5": "5"};

//function to fill the Wedstrijdschema table.
function fillSchema() {
    var uri = "/restservices/sms/allgames";
    $.ajax(uri, {
        type: "get",
        success: function (json) {
            var schemalist = [];
            $.each(json, function (index, data) {
                var schema =
                    "<tr id='" + data.match_id + "'>" +
                    "<td class='match_id'>" + data.matchid + "</td>" +
                    "<td class='player1'>" + data.speler1 + "</td>" +
                    "<td class='player2'>" + data.speler2 + "</td>" +
                    "<td class='text-center'><input class='s1' type='number'></td>" +
                    "<td class='text-center'><input class='s2' type='number'></td>" +
                    "</tr>";
                schemalist = schemalist.concat(schema);
            });
            $("#wSchema").html(schemalist);
        }
    });
    console.log("fillschema executed");
}

//function to fill the stats table.
function fillStats() {
    var uri = "/restservices/sms/allstats/";
    $.ajax(uri, {
        type: "get",
        success: function (json) {
            var statslist = [];
            $.each(json, function (index, data) {
                var innerstatsllist =
                    "<tr id='" + data.spelernaam + "'>" +
                    "<td class='rank'>" + data.rank + "</td>" +
                    "<td class='spelernaam'>" + data.spelernaam + "</td>" +
                    "<td>" + data.punten + "</td>" +
                    "<td>" + data.doelsaldo + "</td>" +
                    "<td>" + data.tegenpunten + "</td>" +
                    "<td>" + data.matchesGespeeld + "</td>" +
                    "</tr>";
                statslist = statslist.concat(innerstatsllist);
            });
            $("#Pstats").html(statslist);
            console.log("fillstats executed");
        }
    });
}

//function to fill the players
function fillPlayers() {
    var uri = "/restservices/sms/allplayers/";
    $.ajax(uri, {
        type: "get",
        success: function (json) {
            var plyrlist = "";
            $.each(json, function (index, data) {
                var innerplyrlist =
                    "<tr id='" + data.spelerid + "'>" +
                    "<td class='spelerid'>" + data.spelerid + "</td>" +
                    "<td class='spelernaam'>" + data.spelernaam + "</td>" +
                    "<td class='text-center'><input type='checkbox'></td>" +
                    "</tr>";
                plyrlist = plyrlist.concat(innerplyrlist);
            });
            $("#Plist").html(plyrlist);
            console.log("fillplayers executed");
        }
    });
}

//function to fill the rules
function fillRules() {
    var uri = "/restservices/sms/allrules/";
    $.ajax(uri, {
        type: "get",
        success: function (json) {
            var r1 = json.rule1;
            var r2 = json.rule2;
            var r3 = json.rule3;
            var r4 = json.rule4;
            var r5 = json.rule5;
            //clear fields
            $("#rule1").val("");
            $("#rule2").val("");
            $("#rule3").val("");
            $("#rule4").val("");
            $("#rule5").val("");
            //set rule values
            $("#rule1").attr("placeholder", r1);
            $("#rule2").attr("placeholder", r2);
            $("#rule3").attr("placeholder", r3);
            $("#rule4").attr("placeholder", r4);
            $("#rule5").attr("placeholder", r5);
            //set slide rules
            $("#r1slide").html("Win je van iemand boven je in de ranking krijg je "+r1+" punten.");
            $("#r2slide").html("Win je van iemand onder je in de ranking krijg je "+r2+" punten.");
            $("#r3slide").html("Verlies je van iemand boven je in de ranking dan krijg je "+r3+" punten.");
            $("#r4slide").html("Verlies je van iemand onder je in de ranking krijg je "+r4+" punten.");
            $("#r5slide").html("Win je van iemand met 11-0 dan krijg je "+r5+" punten.");
            console.log("fillrules executed");
        }
    });
}

//submit the scores when scores opslaan is clicked.
$("#scoreSubmit").click(function () {
    var uri = "/restservices/sms/allrules/";
    $.ajax(uri, {
        type: "get",
        success: function (json) {
            var r1 = json.rule1;
            console.log(typeof r1);
            var r2 = json.rule2;
            var r3 = json.rule3;
            var r4 = json.rule4;
            var r5 = json.rule5;
            console.log(typeof r5);
            $('#wSchema').find('tr').each(function () {
                var p1Score = $(this).find(".s1").val();
                var p2Score = $(this).find(".s2").val();

                if (p1Score === "" || p2Score === "") {
                    console.log("vul iets in...<br>");
                } else {
                    var matchid = $(this).find(".match_id").html();
                    var p1name = $(this).find(".player1").html();
                    var p2name = $(this).find(".player2").html();
                    console.log(p1name);
                    console.log(p2name);
                    p1Score = parseInt(p1Score);
                    p2Score = parseInt(p2Score);
                    var p1Win = false;
                    var p2Win = false;
                    var p1Rank = 1;
                    var p2Rank = 2;
                    var p1Punten = 0;
                    var p2Punten = 0;
                    $('#Pstats').find('tr').each(function () {
                        var spelernaam = $(this).find(".spelernaam").html();
                        console.log(spelernaam);
                        if(spelernaam === p1name){
                            p1Rank = parseInt($(this).find(".rank").html());
                        }
                        if(spelernaam === p2name){
                            p2Rank = parseInt($(this).find(".rank").html());
                        }
                    });

                    //check who won
                    if (p1Score > p2Score) {
                        p1Win = true;
                    }
                    if (p2Score > p1Score) {
                        p2Win = true;
                    }
                    console.log("p1w " + p1Win + " p2w " + p2Win);
                    console.log("p2r " + p1Rank + " p2r " + p2Rank);

                    //5 Win je van iemand met 11-0 dan krijg je 2 bonuspunten.
                    if (p1Score === 11 && p2Score === 0) {
                        p1Punten += r5;
                    }
                    if (p2Score === 11 && p1Score === 0) {
                        p2Punten += r5;
                    }

                    //1	Win je van iemand boven je in de ranking krijg je 3 punten.
                    if (p1Win && p1Rank > p2Rank) {
                        //1- player 1 wint en heeft een lagere rank dan player 2
                        p1Punten += r1;
                    }
                    if (p2Win && p2Rank > p1Rank) {
                        //1- player 2 wint en heeft een lagere rank dan player 1
                        p2Punten += r1;
                    }

                    //2	Win je van iemand onder je in de ranking krijg je 2 punten.
                    if (p1Win && p1Rank < p2Rank) {
                        //2- player 1 wint en heeft een hogere rank dan player 2
                        p1Punten += r2;
                    }
                    if (p2Win && p2Rank < p1Rank) {
                        //2- player 2 wint en heeft een hogere rank dan player 1
                        p2Punten += r2;
                    }

                    //3	Verlies je van iemand boven je in de ranking dan krijg je 0.5 punt.
                    if (p1Win && p2Rank > p1Rank) {
                        //3- player 2 verliest maar heeft een lagere rank dan player 1
                        p2Punten += r3;
                    }
                    if (p2Win && p1Rank > p2Rank) {
                        //3- player 1 verliest maar heeft een lagere rank dan player 2
                        p1Punten += r3;
                    }

                    //4	Verlies je van iemand onder je in de ranking krijg je 0 punten.
                    if (p1Win && p2Rank < p1Rank) {
                        //4- player 2 verliest en heeft een lagere rank dan player 1
                        p2Punten += r4;
                    }
                    if (p2Win && p1Rank < p2Rank) {
                        //4- player 1 verliest en heeft een lagere rank dan player 2
                        p1Punten += r4;
                    }

                    console.log("matchid: " + matchid + " | player1 heeft een score van " + p1Score + " en " + p1Punten + " punten | player2 heeft een score van " + p2Score + " en " + p2Punten + " punten");
                    setScore(matchid, p1Score, p2Score, p1Punten, p2Punten)
                }
                setTimeout(function () {
                    fillSchema();
                }, 250);
                setTimeout(function () {
                    fillStats();
                }, 500);
            });
        }
    });
});

//ajax function to save scores
function setScore(gid, p1s, p2s, p1p, p2p) {
    var uri = "/restservices/sms/submitscore/";
    var data = {
        "gid": "" + gid + "",
        "p1s": "" + p1s + "",
        "p2s": "" + p2s + "",
        "p1p": "" + p1p + "",
        "p2p": "" + p2p + ""
    };
    $.ajax(uri, {
        method: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function () {
            buildSuccessErrorMsgappend("#scoresuccess", "Score voor match: " + gid + " opgeslagen!")
        },
        error: function () {
            buildSuccessErrorMsgappend("#scoreerror", "Fout bij opslaan van scores!")
        }
    });
}

//Restart tournament
$("#RestartTournament").click(function () {
    var uri = "/restservices/sms/restart/";
    $.ajax(uri, {
        type: "POST",
        beforeSend: function (xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function () {
            var string = "RESTART!<br>";
            $("#scoreTest").append(string);
        }
    });
    setTimeout(function () {
        fillSchema();
    }, 250);
});

//skip week
$("#skipWeek").click(function () {
    var string = "Skip week!<br>";
    $("#scoreTest").append(string);
});

//create new player using input when maak nieuwe speler is clicked.
$("#CreatePlayer").click(function () {
    var pname = $("#playername").val();
    var uri = "/restservices/sms/newplayer/" + pname;
    $.ajax(uri, {
        type: "POST",
        beforeSend: function (xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function () {
            buildSuccessErrorMsg("#usercreatedsuccess","Speler is aangemaakt!");
        },
        error: function () {
            buildSuccessErrorMsg("#usercreatederror","Kon speler niet aanmaken!");
        }
    });
    setTimeout(function () {
        fillPlayers();
    }, 250);
    setTimeout(function () {
        fillSchema();
    }, 250);
});

//delete players that are checked when verwijder speler(s) is clicked.
$("#deletePlayer").click(function () {
    var dplayers = [];
    $("#Plist").find("tr").each(function () {
        if ($(this).find('input[type="checkbox"]').is(":checked")) {
            dplayers.push($(this).find('.spelerid').text());
        }
    });
    deleteplayer(dplayers);
    setTimeout(function () {
        fillPlayers();
    }, 250);
    setTimeout(function () {
        fillSchema();
    }, 250);
});

//ajax delete player function.
function deleteplayer(dplayers) {
    $.each(dplayers, function (i, value) {
        var uri = "/restservices/sms/deleteplayer/" + value;
        $.ajax(uri, {
            type: "delete",
            beforeSend: function (xhr) {
                var token = window.sessionStorage.getItem("sessionToken");
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function () {
                buildSuccessErrorMsgappend("#delplayersuccess","Speler met id " + value + " verwijderd!");
            },
            error: function () {
                buildSuccessErrorMsgappend("#delplayererror","Kon speler met id " + value + " niet verwijderen!");
            }
        });
    });
}

//save rules when regels opslaan is clicked.
$("#saverules").click(function () {
    var r1 = $("#rule1").val();
    if (r1 === "") {
        r1 = $("#rule1").attr("placeholder");
    }
    var r2 = $("#rule2").val();
    if (r2 === "") {
        r2 = $("#rule2").attr("placeholder");
    }
    var r3 = $("#rule3").val();
    if (r3 === "") {
        r3 = $("#rule3").attr("placeholder");
    }
    var r4 = $("#rule4").val();
    if (r4 === "") {
        r4 = $("#rule4").attr("placeholder");
    }
    var r5 = $("#rule5").val();
    if (r5 === "") {
        r5 = $("#rule5").attr("placeholder");
    }
    var data = {"r1": "" + r1 + "", "r2": "" + r2 + "", "r3": "" + r3 + "", "r4": "" + r4 + "", "r5": "" + r5 + ""};
    var uri = "/restservices/sms/changerules";
    $.ajax(uri, {
        method: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        beforeSend: function (xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        },
        success: function () {
            buildSuccessErrorMsg("#regelssucces", "Regels opgeslagen!")
        },
        error: function () {
            buildSuccessErrorMsg("#regelserror", "Fout bij opslaan Regels!")
        }
    });
    setTimeout(function () {
        fillRules()
    }, 250);
});

//login procedure | if password is empty error | else close modal and login.
$("#Loginbtn").click(function () {
    var pass = $("#loginpass").val();
    if (pass !== "") {
        var data = {"mPass":""+pass+""};
        var uri = "restservices/authentication";
        $.ajax(uri, {
            method: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
                window.sessionStorage.setItem("sessionToken", response);
                window.location.href = "index.html";
                console.log(response);
                alert("Login Succesvol!");
                $("#LoginModal").modal("toggle");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
                console.log(errorThrown);
                buildSuccessErrorMsg("#passwordcheck", "Wachtwoord incorrect!");
            }
        });
    } else {
        buildSuccessErrorMsg("#passwordcheck", "Voer iets in!");
    }
});

//change password when Wachtwoord opslaan is clicked.
$("#SavePass").click(function () {
    var pass = $("#pass_old").val();
    var pass1 = $("#pass_new").val();
    var pass2 = $("#pass_new1").val();
    console.log("pass: " + pass + " pass1: " + pass1 + " pass2: " + pass2);
    if (pass1 === pass2) {
        var data = {"pass": "" + pass + "", "pass1": "" + pass1 + ""};
        var uri = "/restservices/sms/changepass";
        $.ajax(uri, {
            method: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (xhr) {
                var token = window.sessionStorage.getItem("sessionToken");
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function () {
                buildSuccessErrorMsg("#passsuccess", "Masterwachtwoord opgeslagen!")
            },
            error: function () {
                buildSuccessErrorMsg("#passerror", "Fout bij opslaan Masterwachtwoord!")
            }
        });
    } else {
        buildSuccessErrorMsg("#passerror", "Nieuw Masterwachtwoord moet 2 maal hetzelfde worden getypt!")
    }
});

//date of today is fired when page loads
function setDate() {
    var n = new Date();
    var y = n.getFullYear();
    var m = n.getMonth() + 1;
    var d = n.getDate();
    document.getElementById("curdate").innerHTML = d + "-" + m + "-" + y;
    console.log("setdate executed.")
}

function buildSuccessErrorMsg(msgid, msgtext) {
    $(msgid).fadeOut(500, function () {
        $(this).html(msgtext).fadeIn(500);
    });
    setTimeout(function () {
        $(msgid).fadeOut(500, function () {
            $(this).html("").fadeIn(500)
        });
    }, 3000);
}

function buildSuccessErrorMsgappend(msgid, msgtext) {
    $(msgid).fadeOut(500, function () {
        $(this).append(msgtext+"<br>").fadeIn(500);
    });
    setTimeout(function () {
        $(msgid).fadeOut(500, function () {
            $(this).html("").fadeIn(500)
        });
    }, 3000);
}