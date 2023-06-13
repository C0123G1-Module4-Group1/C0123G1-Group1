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
            let values=data.valuee * 100;
            document.getElementById("couponsValueDetail").innerText=parseInt(values)+ "%";
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

