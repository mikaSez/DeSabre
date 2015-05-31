/**
 * Created by MikaSez on 31/05/2015.
 */
var WidgetTimeLineItem = React.createClass({
    render: function () {
        var item = this.props.item;
        var positionClass = (item.step & 1 == 1) ? "timeline-inverted" : "";
        var iconClass = "fa fa-" + item.icon;
        var iconColor = "timeline-badge " + item.iconType;

        return (
            <li className={positionClass}>
                <div className={iconColor}><i className={iconClass}></i>
                </div>
                <div className="timeline-panel">
                    <div className="timeline-heading">
                        <h4 className="timeline-title">{item.title}</h4>
                    </div>
                    <div className="timeline-body">
                        <p>{item.text}</p>
                    </div>
                </div>
            </li>
        );
    }

});
var WidgetTimeline = React.createClass({
    render: function () {
        var data = this.props.data;

        var timeLineItems = data.steps.map(function (item) {
            var key = item.id;

            return (
                <WidgetTimeLineItem key={key} item={item}/>
            );

        });

        return (
            <div className="col-lg-8">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <i className="fa fa-clock-o fa-fw"></i> {data.title}
                    </div>
                    <div className="panel-body">
                        <ul className="timeline">
                            {timeLineItems}
                        </ul>
                    </div>
                </div>
            </div>
        );
    }

});


React.render(
    <WidgetTimeline data={widgetTimelineData}/>,
    document.getElementById('widgetTimeline')
);
