var describe = function() {
    $.ajax({
        type: "POST",
        cache: false,
        url: '/rest/bem/convert',
        data: {'bemJson': bem_data},
        success: function (response) {
            if (response.status == "success") {
                $('#result').html(response.data);
            }
            else {
                alert(response);
            }
        }
    });
}
