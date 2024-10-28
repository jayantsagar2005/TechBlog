let message = document.getElementById('message');
message.addEventListener('click', messagePrint)

function messagePrint(msg) {
    console.log("Happy Coder");
    alert(msg);
}