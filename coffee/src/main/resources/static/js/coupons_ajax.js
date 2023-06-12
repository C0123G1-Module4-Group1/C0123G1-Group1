function showDetailCoupons(id) {
    $.ajax({
        headers: {
            'accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: 'http://localhost:8080/api/coupons/detail/' + encodeURIComponent(id),
        type: 'GET',
        success: function (data) {
            document.getElementById("couponsIdDetail").innerText=data.id;
            document.getElementById("couponsCodeCouponsDetail").innerText=data.codeCoupons;
            document.getElementById("couponsValueDetail").innerText=data.value;
            document.getElementById("couponsProvisoDetail").innerText=data.proviso;
            document.getElementById("couponsBeginTimeDetail").innerText=data.beginTime;
            document.getElementById("couponsEndTimeDetail").innerText=data.endTime;
            document.getElementById("couponsCreateAtDetail").innerText=data.createTime;
            document.getElementById("couponsUpdateAtDetail").innerText=data.updateTime;
        },
        error: function (data) {
            console.log(data);
        }
    })
}
function deletes(id, name) {
    swal({
        title: "Are you sure?",
        text: "Do you want to remove the code coupons " + " " + name + " " + "?",
        icon: "warning",
        buttons: true,
        dangerMode: true,

    })
        .then((result) => {
            if (result) {
                window.location = '/coupons/delete/' + id
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