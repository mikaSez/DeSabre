/**
 * Created by MikaSez on 01/06/2015.
 */
var WidgetGridHeader = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <th> {item.text}</th>
        );
    }
});

var WidgetGridBodyItem = React.createClass({
    render: function () {
        var item = this.props.item;

        return (
            <tr className="gradeX">
                <td>{item.title}</td>
                <td><i className="fa fa-calendar fa-fw"></i>{item.date}</td>
                <td><a className="" href={item.path}><i className="fa fa-eye fa-fw"></i>Visualiser</a></td>
            </tr>
        );
    }
});


var WidgetGrid = React.createClass({
    render: function () {
        var data = this.props.data;
        var header = this.props.header.headers;

        var widgetGridHeaderItems = header.map(function (item) {
            var key = item.text;
            return (
                <WidgetGridHeader key={item.text} item={header}/>
            );

        });

        var widgetGridBodyItems = data.map(function (item) {
            var key = item.id;
            return (
                <WidgetGridBodyItem key={key} item={item}/>
            );
        });


        return (
            <div className="row">
                <div className="col-lg-12">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            {this.props.header.title}
                        </div>
                        <div className="panel-body">
                            <div className="table-responsive">
                                <table className="datatable table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        {widgetGridHeaderItems}
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {widgetGridBodyItems}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

});

React.render(
    <WidgetGrid data={widgetGridData} header={widgetGridHeader}/>,
    document.getElementById('widgetGrid')
);