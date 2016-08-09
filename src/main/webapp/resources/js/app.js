/**
 * Created by Bogdan on 8/1/2016.
 */
function OnHashChange (event) {
    var hash = document.location.hash;

    if(hash.indexOf("addTaskTop") > -1) {
        document.forms.addTaskTop.submit();
    } else if(hash.indexOf("addTask") > -1) {
        document.getElementById("EditTaskView").classList.add("active");
    } else if(hash.indexOf("back") > -1) {
        document.getElementById("EditTaskView").classList.remove("active");
    } else if(hash.indexOf("add") > -1) {
        document.forms.addTask.submit();
    }

    if(hash.indexOf("allusers") > -1) {
        document.getElementsByClassName("allusers")[0].classList.add("active");
        document.getElementsByClassName("task")[0].classList.remove("active");
    } else if(hash.indexOf("tasks") > -1){
        /*todo replace this code by universal function*/
        document.getElementsByClassName("task")[0].classList.add("active");
        document.getElementsByClassName("allusers")[0].classList.remove("active");
    }

    if(hash.indexOf("/task/") > -1) {
        showInCommentsTaskById(hash.substr(7));
    }
}

function showInCommentsTaskById(id) {

    var allTasksElements = document.querySelectorAll('[task-id]');
    for (var index = 0; index < allTasksElements.length; ++index) {
        allTasksElements[index].classList.remove("selected");
    }

    document.querySelector('[task-id="{0}"]'.replace('{0}', id)).classList.add("selected");
    
    var allTasksMores = document.querySelectorAll('[task-id-more]');
    for (var index = 0; index < allTasksMores.length; ++index) {
        allTasksMores[index].classList.remove("showMore");
        allTasksMores[index].classList.add("hideMore");
    }

    document.querySelector('[task-id-more="{0}"]'.replace('{0}', id)).classList.remove("hideMore");
    document.querySelector('[task-id-more="{0}"]'.replace('{0}', id)).classList.add("showMore");
}

var showFilesElements = document.querySelectorAll('[data-comp="show-comments"]');
for (var index = 0; index < showFilesElements.length; ++index) {
    showFilesElements[index].onclick = function () {
        document.getElementById("TaskDetail").classList.add("showing-comments");
    };
}

var showCommentsElements = document.querySelectorAll('[data-comp="show-files"]');
for (var index = 0; index < showCommentsElements.length; ++index) {
    showCommentsElements[index].onclick = function () {
        document.getElementById("TaskDetail").classList.remove("showing-comments");
    };
}

// for button
// function ChangeHash () {
//     document.location.hash = "myBookmark";
// }