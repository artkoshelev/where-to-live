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

function drawDistrict(id, name, coords, color) {
    var myPolygon = new ymaps.Polygon([
        coords
    ], {
        id: id,
        hintContent: name
    }, {
        fillColor: color,
        strokeWidth: 1
    });
    window.myMap.geoObjects.add(myPolygon);
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

function drawAllDistricts(ids) {
    $.getJSON('/api/districts/' + ids.join(',') + '/polygons', function(data) {
        $.each(data, function(key, value) {
            var rank = parseInt($('#' + value.id + ' .badge').text());
            drawDistrict(value.id, value.name, value.coords, getColor(rank))
        });
    });
}

function drawSerp(data) {
    var items = [];
    $('.result').empty();

    clearMap();

    var ids = [];
    $.each(data, function (key, val) {
        ids.push(val.id);

        items.push('<li id="' + val.id + '" class="list-group-item">' +
                '<span class="badge">' + val.summ + '</span> ' + val.districtname +
                '</li>');
    });

    $('<ul/>', {
        'class': 'list-group',
        html: items.slice(0, 15).join('')
    }).appendTo('.result');

    drawAllDistricts(ids.slice(0, 15));
}

function getColor(rank) {
    var colors = [
        "#0000FF", "#0020FF", "#0040FF", "#0060FF", "#0080FF", "#00A0FF", "#00C0FF", "#00E0FF", "#00FFFF",
        "#00FFE0", "#00FFC0", "#00FFA0", "#00FF80", "#00FF60", "#00FF40", "#00FF20", "#00FF00", "#20FF00",
        "#40FF00", "#60FF00", "#80FF00", "#A0FF00", "#C0FF00", "#E0FF00", "#FFFF00", "#FFE000", "#FFC000",
        "#FFA000", "#FF8000", "#FF6000", "#FF4000", "#FF2000", "#FF0000"
    ];

    return colors[Math.round((colors.length - 1) * rank / 100.0)];
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
        var $this = $(this);
        var id = $this.attr('id');
        if (!deleteObjectFromMap(id)) {
            var rank = parseInt($this.find('.badge').text());

            $.getJSON("/api/districts/" + id + "/polygon", function (data) {
                drawDistrict(id, data.name, data.coords, getColor(rank))
            });
            drawDistrict.call(this, e);
        }
    });
});
