/**
 * Created by MikaSez on 28/05/2015.
 */




var NavBarHeader = React.createClass({

    render: function () {
        return (
            <div className="navbar-header">
                <button type="button" className="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span className="sr-only">Afficher la navigation</span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                </button>
                <a className="navbar-brand" href="main.html">DeSaBre</a>
            </div>
        );
    }
});

var TopMessageItem = React.createClass({

    render: function () {
        var item = this.props.item;
        var rawMarkup = marked(item.message.toString(), {sanitize: true});
        return (
            <li>
                <a href={item.path}>
                    <div>
                        <strong>{item.title}</strong>
                                    <span className="pull-right text-muted">
                                        <em>{item.end}</em>
                                    </span>
                    </div>
                    <div><span dangerouslySetInnerHTML={{__html: rawMarkup}}/></div>
                </a>
                <ul>
                    <li className="divider" style={{width : 80 + "%"}}></li>
                </ul>
            </li>
        );

    }
});

var TopNavMessages = React.createClass({
    render: function () {

        var topMessages = this.props.data.map(function (item) {
            var key = item.title + "-topNavTaskId";

            return (
                <li className="divider" key={key}></li>,
                    <TopMessageItem item={item} key={key}/>

            );

        });
        return (
            <li className="dropdown">
                <a className="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i className="fa fa-bell fa-fw"></i> <i className="fa fa-caret-down"></i>
                </a>
                <ul className="dropdown-menu dropdown-messages">
                    {topMessages}
                    <li>
                        <a className="text-center" href="#">
                            <strong>Lire tous les messages </strong>
                            <i className="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
            </li>

        );
    }
});


var TopNavTaskItem = React.createClass({
    render: function () {
        var item = this.props.item;
        var barClassName = "progress-bar-" + item.status + " progress-bar";
        return (
            <li>
                <a href={item.path}>
                    <div>
                        <p>
                            <strong>{item.title}</strong>
                            <span className="pull-right text-muted">Etape : {item.state}</span>
                        </p>

                        <div className="progress progress-striped active">
                            <div className={barClassName} style={{width: item.progression +"%"}} role="progressbar"
                                 aria-valuenow={item.progression} aria-valuemin="0" aria-valuemax="100">
                                <span className="sr-only">{item.progression}% ({item.status})</span>
                            </div>
                        </div>
                    </div>
                </a>
            </li>

        );
    }
});


var TopNavTasks = React.createClass({
    render: function () {

        var topNavTaskItems = this.props.data.map(function (item) {
            var key = item.title + "-topNavTaskId";
            return (
                <TopNavTaskItem key={key} item={item}/>
            );

        });

        return (
            <li className="dropdown">
                <a className="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i className="fa fa-tasks fa-fw"></i> <i className="fa fa-caret-down"></i>
                </a>
                <ul className="dropdown-menu dropdown-tasks">
                    {topNavTaskItems}
                    <li className="divider"></li>

                    <li>
                        <a className="text-center" href="#">
                            <strong>Voir tous les jobs en cours </strong>
                            <i className="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>


            </li>
        );
    }
});

var UserOptionItem = React.createClass({
    render: function () {
        var item = this.props.item;
        var classString = item.icon + " fa fa-fw";
        return (
            <li>
                <a href={item.path}><i className={classString}></i> {item.title}</a>
            </li>
        );
    }
});

var TopNavUser = React.createClass({
    render: function () {

        var userOptions = this.props.data.map(function (item) {
            var key = item.title + "-topNavOptionId";
            return (
                <UserOptionItem key={key} item={item}/>

            );
        });
        return (
            <li className="dropdown">
                <a className="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i className="fa fa-user fa-fw"></i> <i className="fa fa-caret-down"></i></a>
                <ul className="dropdown-menu dropdown-user">
                    {userOptions}
                </ul>
            </li>
        );
    }
});

var NavBarLinks = React.createClass({
    render: function () {

        return (
            <ul className="nav navbar-top-links navbar-right">
                <TopNavMessages data={messageData}/>
                <TopNavTasks data={taskData}/>
                <TopNavUser data={userOptionsData}/>

            </ul>
        );
    }

});

var TopNav = React.createClass({
    render: function () {
        return (
            <nav className="navbar navbar-default navbar-static-top" role="navigation">
                <NavBarHeader />
                <NavBarLinks />

            </nav>
        );
    }
});


var SidebarSearch = React.createClass({
    render: function () {
        return (
            <li className="sidebar-search">
                <div className="input-group custom-search-form">
                    <input type="text" className="form-control" placeholder="Rechercher"></input>
                                    <span className="input-group-btn">
                                    <button className="btn btn-default" type="button">
                                        <i className="fa fa-search"></i>
                                    </button>

                                </span>

                </div>
            </li>
        );
    }

});


var SideItemFather = React.createClass({

    render: function () {
        var item = this.props.item;
        var sidebarChildNodes = item.childs.map(function (item) {
            var key = item.title + "-SideItemChildId";
            return (
                <SideItemChild key={key} item={item}/>

            );
        });

        var classString = "fa fa-dashboard fa-fw " + item.icon;
        return (
            <li>
                <a className="active" href={item.path}><i className={classString}></i>{item.title}</a>
                <ul className="nav nav-second-level">
                    {sidebarChildNodes}
                </ul>
            </li>
        );

    }

});

var SideItemChild = React.createClass({
    render: function () {
        var item = this.props.item;

        var classString = "fa fa-fw " + item.icon;
        return (
            <li>
                <a className="" href="index.html"><i className={classString}></i>{item.title}</a>
            </li>
        );
    }
});
var SidebarItems = React.createClass({
    render: function () {

        var sidebarItemNodes = this.props.data.map(function (item) {
            var key = item.title + "-SideItemFatherId";
            return (

                <SideItemFather key={key} item={item}>

                </SideItemFather>
            );
        });
        return (
            <ul className="nav" id="side-menu">
                <SidebarSearch />
                {sidebarItemNodes}
            </ul>
        );

    }
});
var SideNav = React.createClass({
    render: function () {
        return (
            <div className="navbar-default sidebar" role="navigation">
                <div className="sidebar-nav navbar-collapse">
                    <SidebarItems data={sideNavData}/>
                </div>
            </div>
        );
    }

});

var NavigationMenu = React.createClass({
    render: function () {
        return (

            <div className="Navigation">
                <TopNav />

                <div className="page-wrapper">
                    <SideNav />
                </div>
            </div>
        );
    }
});
React.render(
    <NavigationMenu />,
    document.getElementById('navigation')
);