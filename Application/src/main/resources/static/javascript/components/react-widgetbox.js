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

    getInitialState: function () {
        console.info("initializing widget box");
        console.info(this.props);
        return {data: []};
    },


    loadWidgetFromServer: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            cache: false,
            success: function (data) {
                console.info("got box data");
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },

    componentDidMount: function () {
        console.info("managed get the component");
        this.loadWidgetFromServer();
        setInterval(this.loadWidgetFromServer, this.props.pollInterval);
    },

    render: function () {
        var data = this.state.data;
        var widgetBoxItem = data.map(function (item) {
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
    <WidgetBox url="/widgetBox" pollInterval={30000}/>,
    document.getElementById('widgetBox')
);