/**
 * Created by MikaSez on 30/05/2015.
 */

var WidgetNewsList = React.createClass({
    render: function () {

        var WidgetNewsItem = React.createClass({
            render: function () {
                var item = this.props.item;
                var iconString = "fa fa-fw fa-" + item.icon;
                return (
                    <a href={item.path} className="list-group-item">
                        <i className={iconString}></i> {item.title}
                            <span className="pull-right text-muted small"><em>{item.time}</em>
                            </span>
                    </a>
                );
            }
        });
        var newsWidgetItem = this.props.data.map(function (item) {
            var key = item.id;

            return (
                <WidgetNewsItem key={key} item={item}/>
            );

        });
        return (
            <div className="col-lg-4">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <i className="fa fa-rss fa-fw"></i> Informations
                    </div>
                    <div className="panel-body">
                        <div className="list-group">
                            {newsWidgetItem}
                        </div>
                    </div>
                </div>
            </div>

        );
    }

});
React.render(
    <WidgetNewsList data={widgetNewsData}/>,
    document.getElementById('widgetNews')
);