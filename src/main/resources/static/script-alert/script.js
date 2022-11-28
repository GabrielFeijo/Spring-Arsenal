
alerta = false;
function showAlert(type, title, message) {
    if (type && title && message && alerta == false) {
        document.querySelector("#alert").innerHTML = `
        <div class="alert alert-`+ type + ` alert-dismissible d-flex align-items-center fade show">
            <i class="bi-exclamation-octagon-fill"></i>
            <strong class="mx-2">`+ title + `</strong> ` + message + `
        
        </div> `;
        alerta = true;
        setTimeout(closeAlert, 4000);
    }
}

function closeAlert() {
    var element = document.querySelector(".alert");
    // Create alert instance
    var myAlert = new bootstrap.Alert(element);
    myAlert.close();
    alerta = false
}


