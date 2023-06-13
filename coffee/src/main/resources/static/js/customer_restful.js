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
            document.getElementById("customerAddressDetail").innerText = data.address;
            document.getElementById("customerEmailDetail").innerText = data.email;
            document.getElementById("customerCreateAtDetail").innerText = data.createTime;
            document.getElementById("customerUpdateAtDetail").innerText = data.updateTime;
        },
        error: function (data) {
            console.log(data);
        }
    })
}
