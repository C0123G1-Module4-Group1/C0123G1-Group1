function showDetailCustomer(id) {
    $.ajax({
        headers: {
            'accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: 'http://localhost:8080/api/customer/detail/' + encodeURIComponent(id),
        type: 'GET',
        success: function (data) {
            document.getElementById("customerIdDetail").innerText = data.id;
            document.getElementById("customerNameDetail").innerText = data.name;
            document.getElementById("customerPhoneNumberDetail").innerText = data.phoneNumber;
            document.getElementById("customerAdressDetail").innerText = data.adress;
            document.getElementById("customerEmailDetail").innerText = data.email;
            document.getElementById("customerCreateAtDetail").innerText = data.createTime;
            document.getElementById("customerUpdateAtDetail").innerText = data.updateTime;
        },
        error: function (data) {
            console.log(data);
        }
    })
}

function deletes(id, name) {
    swal({
        title: "Are you sure?",
        text: "Do you want to remove the date name " + " " + name + " " + "?",
        icon: "warning",
        buttons: true,
        dangerMode: true,

    })
        .then((result) => {
            if (result) {
                window.location = '/customer/delete/' + id
            } else {
                swal("Thank you for not deleting")
            }
        });
}

async function detail(id, titles) {
    Swal.fire({
        text: 'Would you like to see the details of this title: ' + titles + "",
        icon: 'question',
        showCancelButton: true,
        cancelButtonColor: '#CCCCCC',
        confirmButtonColor: '#3366FF',
        confirmButtonText: 'May I try it out ?'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                window.location.href = '/detail/' + id
            )
        }
    })
}

<!--    thêm mới-->
function showAlertSuccess() {
    Swal.fire({
        icon: 'success',
        title: 'Successfully',
        text: 'Successfully added new!',
        confirmButtonText: 'Oke',
        timer: 2000,
    });
}

function showAlertFail() {
    Swal.fire({
        icon: 'error',
        title: 'Failure',
        text: 'Add new failure!',
        confirmButtonText: 'Oke',
        timer: 2000,
    });
}

let create = /*[[${check1}]]*/ true;
let delete1 =/*[[${check}]]*/ true;
let update =/*[[${check2}]]*/ true;

// thêm mới
if (create != null) {
    if (create) {
        showAlertSuccess();
    } else {
        showAlertFail();
    }
}
// xoá
if (delete1 != null) {
    if (delete1) {
        showAlertSuccess();
    } else {
        showAlertFail();
    }
}
// update
if (update != null) {
    if (update) {
        showAlertSuccess();
    } else {
        showAlertFail();
    }
}