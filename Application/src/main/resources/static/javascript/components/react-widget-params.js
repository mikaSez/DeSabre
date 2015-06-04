/**
 * Created by MikaSez on 04/06/2015.
 */

var WidgetParamsHeader = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <th> {item.text}</th>
        );
    }
});


var WidgetParamsData = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <tr>
                <td>{item.id}</td>
                <td>{item.text}</td>
                <td>{item.size}</td>
                <td>
                    <i className="fa fa-times btn btn-outline btn-danger btn-xs btn-delete"></i>
                </td>
            </tr>
        );
    }
});


var WidgetParams = React.createClass({
    render: function () {
        var data = this.props.data;
        var header = this.props.header;

        var widgetParamsHeaderItems = header.headers.map(function (item) {
            var key = item.text + "widgetParams-header";
            return (
                <WidgetParamsHeader key={key} item={item}/>
            );
        });
        var widgetParamsDataItems = data.map(function (item) {
            var key = item.id + "widgetParams-data";
            return (
                <WidgetParamsData key={key} item={item}/>
            );
        });

        return (
            <div className="col-lg-6">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        {header.title}
                    </div>
                    <div className="panel-body">
                        <div className="table-responsive">
                            <table className="table">
                                <thead>
                                <tr>
                                    {widgetParamsHeaderItems}
                                </tr>
                                </thead>
                                <tbody>
                                {widgetParamsDataItems}
                                </tbody>
                            </table>
                        </div>
                        <button type="button" className="btn btn-outline btn-success btn-params"><i
                            className="fa fa-plus"></i> Ajouter
                        </button>
                        <button type="button" className="btn btn-outline btn-primary btn-params"><i
                            className="fa fa-list"></i> Choisir parmis les prédéfinis
                        </button>
                    </div>
                </div>

            </div>
        );
    }

});


React.render(
    <WidgetParams header={widgetParamsHeader} data={widgetParamsData}/>,
    document.getElementById('widgetParams')
);