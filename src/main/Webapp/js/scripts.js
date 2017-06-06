//TODO: fix ready function
$(document).ready(function () {
    //login popup
    $("#Login").click(function () {
        console.log("clicked on 'Login'")
        $("#myModal").modal({backdrop: true});
    });
    fillSchema(myschemadummy);

});

var myschemadummy = [{"matchid": "1", "speler1": "henk verwijden", "speler2": "Michael Prinsen"}];


function fillSchema(json) {
    $.each(json, function (index, data) {
        console.log(json);
        var schema =
            "<tr id='"+data.match_id+"'>" +
            "<td class='match_id'>" + data.matchid + "</td>" +
            "<td>" + data.speler1 + "</td>" +
            "<td>" + data.speler2 + "</td>" +
            "<td><input class='s1' type='number'></td>" +
            "<td><input class='s2' type='number'></td>" +
            "</tr>";
        console.log("fillschema executed");
        $("#wSchema").append(schema);
    });
}

$("#score_submit").click(function(){
    $('#wSchema').find('tr').each(function() {
        var matchid = $(this).find(".match_id").html();
        var s1 = $(this).find(".s1").val();
        var s2 = $(this).find(".s2").val();
        var string = "<p>matchid: "+matchid+" | player1 heeft als score: "+s1+" | player1 heeft als score: "+s2+"</p><br>";
        $("#test_score").append(string);
    });

});

//TODO: fix setDate function
function setDate() {
    var today = moment().format("DD/MM/YYYY");
    console.log(today);
    $("#Date").innerHTML = today;
}
