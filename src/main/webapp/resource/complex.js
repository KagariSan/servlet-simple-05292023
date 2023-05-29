function validateFormComment() {
    removeAllErrorClass();
    let email = document.forms["formCmt"]["email"];
    let title = document.forms["formCmt"]["title"];
    let content = document.forms["formCmt"]["content"];

    validateRequired(email, "Email");
    validateRequired(title, "Title");
    validateRequired(content, "Content");

    return document.querySelectorAll(".error").length == 0;
}

function removeAllErrorClass() {
    document.querySelectorAll(".error").forEach(element => {
        element.parentNode.removeChild(element);
    })
     document.querySelectorAll("input").forEach(element => {
        element.style.border = "";
    })
}

function validateRequired(value, objectError) {
    if (value.value != "") return "";
    if (document.querySelectorAll(".error").length == 0) value.focus();
    value.style.border = "2px solid red";
    value.insertAdjacentHTML("afterend", `<p style='color:red; margin: 0;' class='error'>${objectError} is require</p>`); 
}