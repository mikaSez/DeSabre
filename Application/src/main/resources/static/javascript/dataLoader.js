/**
 * Created by MikaSez on 07/06/2015.
 */

var dataLoader = function (global) {
    var state;

    var loadData = function (url, variable) {
        var from = url;
        $.ajax({
            url: from,
            dataType: 'json',
            async: false,
            cache: true,
            success: function (data) {
                global[variable] = data;

            }.bind(this),
            error: function (xhr, status, err) {
                console.error(from, status, err.toString());
            }.bind(this)
        });
    };


    var i = 0;
    global.urls.map(function (url) {
        loadData(url.from, url.to);
    });


};