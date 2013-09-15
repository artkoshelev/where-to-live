function loadCategories() {
    $.getJSON("/api/categories", function (data) {
        var items = [];

        $.each(data, function (key, val) {
            items.push('<li id="p' + val.id + '" class="list-group-item">' + val.name +
                    '<input type="text" name="' + val.searchparam + '" value="0"/></li>');
        });

        $('<ul/>', {
            'class': 'list-group',
            html: items.join('')
        }).appendTo('.params .list');
    });
}

function drawDistrict(e) {
    e.preventDefault();
    var id = $(this).attr('id');

    $.getJSON("/api/districts/" + id + "/polygon", function (data) {
        var myPolygon = new ymaps.Polygon([
            // Указываем координаты вершин многоугольника.
            // Координаты вершин внешнего контура.
            data.coords
        ], {
            // Описываем свойства геообъекта.
            // Содержимое балуна.
            hintContent: data.name
        }, {
            // Задаем опции геообъекта.
            // Цвет заливки.
            fillColor: '#99FF9988',
            // Ширина обводки.
            strokeWidth: 1
        });

        // Добавляем многоугольник на карту.
        window.myMap.geoObjects.add(myPolygon);
    });
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


$(document).ready(function() {
    loadCategories();

    $('.params button').click(function() {
        var data = $(".params .list").serializeObject();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/api/districts/search",
            data: JSON.stringify({params: data}),
            success: function (data) {
                console.log(data);
            },
            dataType: "json"
        });
    });

    $(".result").on("click", "li", function(e) {
        drawDistrict.call(this, e);
    });
});
