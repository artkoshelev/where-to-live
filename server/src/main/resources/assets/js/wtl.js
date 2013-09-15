window.searchCategories = [];


function loadCategories() {
    $.getJSON("/api/categories", function (data) {
        var items = [];

        $.each(data, function (key, val) {
            window.searchCategories.push(val.searchparam);
            items.push('<li class="list-group-item">' + val.name +
                    '<span class="slider" id="' + val.searchparam + '"/></li>');
        });

        $('<ul/>', {
            'class': 'list-group',
            html: items.join('')
        }).appendTo('.params .list');

        $('.params .slider').slider({
            min: 0,
            max: 10,
            step: 1,
            value: 0,
            tooltip: 'hide'
        })
    });
}

function drawDistrict(e) {
    e.preventDefault();
    var id = $(this).attr('id');

    $.getJSON("/api/districts/" + id + "/polygon", function (data) {
        var myPolygon = new ymaps.Polygon([
            data.coords
        ], {
            id: id,
            hintContent: data.name
        }, {
            fillColor: '#88FF8888',
            strokeWidth: 1
        });

        // Добавляем многоугольник на карту.
        window.myMap.geoObjects.add(myPolygon);
    });
}

function clearMap() {
    window.myMap.geoObjects.each(function (geoObject) {
        window.myMap.geoObjects.remove(geoObject);
    });
}

function deleteObjectFromMap(id) {
    var deleted = false;
    window.myMap.geoObjects.each(function (geoObject) {
        if (geoObject.properties.get('id') == id) {
            deleted = true;
            window.myMap.geoObjects.remove(geoObject);
            return false;
        }
    });
    return deleted;
}

function drawSerp(data) {
    var items = [];
    $('.result').empty();

    clearMap();

    $.each(data, function (key, val) {
        items.push('<li id="' + val.id + '" class="list-group-item">' +
                '<span class="badge">' + val.summ + '</span> ' + val.districtname +
                '</li>');
    });

    $('<ul/>', {
        'class': 'list-group',
        html: items.slice(0, 15).join('')
    }).appendTo('.result');
}

$(document).ready(function() {
    loadCategories();

    $('.params button').click(function() {
        var data = {};
        $.each(window.searchCategories, function(key, val) {
            data[val] = $('#' + val).data('value');
        });

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/api/districts/search",
            data: JSON.stringify($.isEmptyObject(data) ? {params: null} : {params: data}),
            success: function (data) {
                drawSerp(data);
            },
            dataType: "json"
        });
    });

    $(".result").on("click", "li", function(e) {
        if (!deleteObjectFromMap($(this).attr('id'))) {
            drawDistrict.call(this, e);
        }
    });
});
