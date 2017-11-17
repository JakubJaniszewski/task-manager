function _(id) {
    return document.getElementById(id);
}

var droppedIn = false;
var buttonId;
var taskboxName;

function drag_start(event) {
    event.dataTransfer.dropEffect = "move";
    buttonId = event.target.getAttribute('id');
    event.dataTransfer.setData("text", buttonId );
}

function drag_drop(event) {
    event.preventDefault(); /* Prevent undesirable default behavior while dropping */

    if (!event.target.getAttribute("ondrop")) return false;

    var elem_id = event.dataTransfer.getData("text");
    event.target.appendChild( _(elem_id) );
    taskboxName = event.target.getAttribute('id');

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/", true);

    var myObj = { "buttonId":buttonId, "taskboxName": taskboxName}
    xhttp.send(JSON.stringify(myObj));
    droppedIn = true;
}