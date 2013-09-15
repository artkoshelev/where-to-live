window.searchCategories = [];

var rainbow = new Rainbow(); // by default, range is 0 to 100
rainbow.setSpectrum('#ff2200', '#ffff00', '#00ff00');
//rainbow.setNumberRange(-0.5, 0.5);

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

function drawDistrict(id, name, coords, rank) {
    var myPolygon = new ymaps.Polygon([
        coords
    ], {
        id: id,
        hintContent: name + " (" + rank + ")"
    }, {
        fillColor: getColor(rank),
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
            drawDistrict(value.id, value.name, value.coords, rank)
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
                '<span class="badge" style="background-color: ' + getColor(val.summ) + '">'
                + val.summ + '</span> ' + val.districtname + '</li>');
    });

    $('<ul/>', {
        'class': 'list-group',
        html: items.slice(0, 17).join('')
    }).appendTo('.result');

    drawAllDistricts(ids.slice(0, 17));
}

function getColor(rank) {
    return '#' + rainbow.colourAt(rank);
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
                drawDistrict(id, data.name, data.coords, rank)
            });
            drawDistrict.call(this, e);
        }
    });
});
