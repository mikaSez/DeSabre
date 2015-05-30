/**
 * Created by MikaSez on 30/05/2015.
 */
var WidgetBoxItem = React.createClass({
    render: function () {
        var item = this.props.item;
        var itemType = "panel panel-" + item.color;
        var itemIcon = "fa fa-5x fa-" + item.icon;
        return (
            <div className="col-lg-3 col-md-6">
                <div className={itemType}>
                    <div className="panel-heading">
                        <div className="row">
                            <div className="col-xs-3">
                                <i className={itemIcon}></i>
                            </div>
                            <div className="col-xs-9 text-right">
                                <div className="huge">{item.number}</div>
                                <div>{item.text}</div>
                            </div>
                        </div>
                    </div>
                    <a href={item.path}>
                        <div className="panel-footer">
                            <span className="pull-left">Voir en détails</span>
                            <span className="pull-right"><i className="fa fa-arrow-circle-right"></i></span>

                            <div className="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        );
    }
});
var WidgetBox = React.createClass({
    render: function () {
        var widgetBoxItem = this.props.data.map(function (item) {
            var key = item.id;

            return (
                <WidgetBoxItem key={key} item={item}/>
            );
        });
        return (
            <div className="widgets col-lg-12 col-md-12 col-xs-12">
                {widgetBoxItem}
            </div>
        );
    }
});
React.render(
    <WidgetBox data={widgetBoxData}/>,
    document.getElementById('widgetBox')
);