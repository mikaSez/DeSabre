//Flot Pie Chart
$(function () {

    var data = [{
        label: "CALC1",
        data: 50
    }, {
        label: "CALC2",
        data: 40
    }, {
        label: "CALC3",
        data: 12
    }, {
        label: "Serveur admin",
        data: 2
    },
        {
            label: "Vaisseau mère",
            data: 3
        }
    ];

    var plotObj = $.plot($("#flot-pie-chart"), data, {
        series: {
            pie: {
                show: true
            }
        },
        grid: {
            hoverable: true
        },
        tooltip: true,
        tooltipOpts: {
            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
            shifts: {
                x: 20,
                y: 0
            },
            defaultTheme: true
        }
    });

});
