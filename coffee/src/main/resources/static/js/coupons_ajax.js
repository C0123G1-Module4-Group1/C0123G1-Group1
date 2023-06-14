function showDetailCoupons(id) {
    $.ajax({
        headers: {
            'accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: 'http://localhost:8080/api/coupons/detail/' + encodeURIComponent(id),
        type: 'GET',
        success: function (data) {
            document.getElementById("couponsCodeCouponsDetail").innerText=data.codeCoupons;
            document.getElementById("couponsValueDetail").innerText=data.valuee+ "%";
            document.getElementById("couponsProvisoDetail").innerText=data.proviso+" VND";
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

