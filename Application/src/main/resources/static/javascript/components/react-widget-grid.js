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


var WidgetGridDataItem = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <td >{item}</td>
        );
    }

});

var WidgetGridBodyItem = React.createClass({
    render: function () {
        var item = this.props.item;

        //FIXME seems too ugly to be right
        var list = [];
        for (var a in item) {
            if (a !== "path")
                list.push(a);
        }

        var widgetGridDataItems = list.map(function (intern) {
            return (
                <WidgetGridDataItem key={intern} item={item[intern]}/>
            );
        });

        return (
            <tr className="gradeX">
                {widgetGridDataItems}
                <td ><i className="fa fa-eye fa-fw"></i>Visualiser</td>
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
                <WidgetGridHeader key={item.text} item={item}/>
            );

        });
        if (data === undefined) {
            data = [];
        }
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