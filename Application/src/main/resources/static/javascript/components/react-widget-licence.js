/**
 * Created by MikaSez on 04/06/2015.
 */

var WidgetLicencesItem = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <option> {item.text}</option>
        );
    }

});
var WidgetLicences = React.createClass({


    render: function () {

        var data = this.props.data;

        var widgetLicencesItems = data.items.map(function (item) {

            var key = item.text + "widgetLicences";
            return (
                <WidgetLicencesItem key={key} item={item}/>
            );
        });

        return (
            <div className="col-lg-4">
                <div className="form-group">
                    <label>{data.title}</label>
                    <select multiple="true" className="form-control">
                        {widgetLicencesItems}
                    </select>
                </div>
            </div>
        );
    }
});
React.render(
    <WidgetLicences data={widgetLicencesData}/>,
    document.getElementById('widgetLicences')
);