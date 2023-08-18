$(document).ready(function() {
    $('#BuyerAmount').on('input', function() {
        var amount = $(this).val();
        var price = parseInt($('.input-amount span').text());

        if (amount === "") {
            $('#total-cost').text("0");
        } else {
            var totalCost = parseInt(amount) * price;
            $('#total-cost').text(totalCost.toLocaleString('ko-KR'));
        }
    });
});