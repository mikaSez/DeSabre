/**
 * Created by MikaSez on 30/05/2015.
 */

var WidgetNewsList = React.createClass({
    getInitialState: function () {
        console.info("initializing widget news");
        console.info(this.props);
        return {data: []};
    },


    loadNewsFromServer: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            cache: false,
            success: function (data) {
                console.info("got widgetbox data");
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },

    componentDidMount: function () {
        console.info("managed get the component");
        this.loadNewsFromServer();
        setInterval(this.loadNewsFromServer, this.props.pollInterval);
    },

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


        var data = this.state.data;
		if(data.length===0)
			var newsWidgetItem = <p>Aucune news</p>;
		else
			var newsWidgetItem = data.map(function (item) {
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
    <WidgetNewsList url="/news" pollInterval={10000}/>,
    document.getElementById('widgetNews')
);