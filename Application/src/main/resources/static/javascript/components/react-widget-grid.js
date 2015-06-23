/**
 * Created by MikaSez on 01/06/2015.
 */
var WidgetGridHeader = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <th> {item}</th>
        );
    }
});


var WidgetGridDataItem = React.createClass({
    render: function () {
        var item = this.props.item;


        var print;
        var classString = "";
        console.info(item);
        if (item.type !== undefined) {
            if (item.type === "date") {
                print = moment(item.value).calendar();
            }
        } else {
            if (item !== null && item !== true && item !== false) {
                print = {item};
            } else if (item !== true) {
                print = "Non";
                classString = "danger";
            } else {
                print = "Oui";
                classString = "success";
            }
        }


        return (
            <td className={classString}>{print}</td>
        );
    }

});

var WidgetGridBodyItem = React.createClass({
    render: function () {
        var item = this.props.item;
        var type = this.props.type;
        var path = "#";
        var visualiser = "";
        //FIXME seems too ugly to be right
        var list = [];
        for (var a in item) {
            if (a !== "path" && a !== "headers"){
                list.push(a);
            } else if (a !== "headers") {
                visualiser = <td ><a href={item[a]}><i className="fa fa-eye fa-fw"></i>Visualiser</a></td>
            }
        }

        var widgetGridDataItems = list.map(function (intern) {
            return (
                <WidgetGridDataItem key={intern} item={item[intern]}/>
            );
        });
        
        var widgetGridType;
        
        if(type === "list") {
        	widgetGridType = <td ><a href={path}><i className="fa fa-eye fa-fw"></i> Visualiser</a></td>;
        } else if(type === "launch") {
        	widgetGridType = <td ><a href={path}><i className="fa fa-play fa-fw"></i> Lancer</a></td>;
        }

        return (
            <tr className="gradeX">
                {widgetGridDataItems}
                {widgetGridType}
                {visualiser}
            </tr>
        );
    }
});


var WidgetGrid = React.createClass({
    render: function () {
        var data = this.props.data;
        var type = this.props.type;

        if (data === undefined) {
            data = [];
        }
        if (type === undefined) {
            type = "list";
        }
        var widgetGridBodyItems;
        if(data.length !== 0){
        	var header = data[0].headers; 
        	
            
            var widgetGridHeaderItems = header.map(function (item) {
                var key = item;
                return (
                    <WidgetGridHeader key={item} item={item}/>
                );

            });
        	
        	 widgetGridBodyItems = (function(wtype) { //closure
        		 return data.map(function (item) {
	                var key = item.id;
	                var type = wtype;
	                return (
	                    <WidgetGridBodyItem key={key} item={item} type={type}/>
	                );
        		 })
        	 })(type);
        } else {
            widgetGridBodyItems = <tr>
                <td>Aucune donnée disponible pour le moment</td>
            </tr>;
            widgetGridHeaderItems = <th> Nous sommes désolés. </th>
        }

        return (
            <div className="row">
                <div className="col-lg-12">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            
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

var showGrid = function () {
    $(document).ready(function () {
        $('.datatable').dataTable();
    });
};

React.render(
    <WidgetGrid data={widgetGridData} type={widgetGridType}/>,
    document.getElementById('widgetGrid'),
    showGrid
);