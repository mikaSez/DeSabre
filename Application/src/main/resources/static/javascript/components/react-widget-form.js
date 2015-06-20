var WidgetModalListHeader = React.createClass({
    render: function () {
        var item = this.props.item;
        return (
            <th> {item.key}</th>
        );
    }
});


var WidgetModalListData = React.createClass({

    onClick: function () {
        this.props.removeHandler(this.props.item);
    },
    render: function () {
        var item = this.props.item;
        return (
            <tr>
                <td>{item.key}</td>
                <td>{item.value}</td>
                <td>
                    <i className="fa fa-times btn btn-outline btn-danger btn-xs btn-delete" onClick={this.onClick}></i>
                </td>
            </tr>
        );
    }
});

var WidgetModalListWindow = React.createClass({
    getInitialState: function () {
        return {key: "", value: "", id: 1};
    },
    mixins: [React.addons.LinkedStateMixin],
    onChangeKey: function (e) {
        this.setState({key: e.target.value});
    },
    onChangeValue: function (e) {
        this.setState({value: e.target.value});
    },

    onClick: function (e) {
        var item = {};
        item.id = this.state.id;
        item.key = this.state.key;
        item.value = this.state.value;
        this.setState({key: "", value: "", id: this.state.id + 1});
        $('#' + this.props.item.id + "Modal").modal("hide");
        return this.props.addHandler(item);
    },


    render: function () {
        var item = this.props.item;


        var modalId = item.id + "Modal";
        return (
            <div className="modal fade" id={modalId} tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                            <h4 className="modal-title" id="myModalLabel">Ajouter un nouveau couple clé - valeur</h4>
                        </div>
                        <div className="modal-body">
                            <div className="form-group">
                                <input id="ModalKey" className="form-control" name="ModalKey" type="text"
                                       autoSave="true" autoComplete="true" placeholder="Une nouvelle clé"
                                       valueLink={this.linkState('key')}/>
                            </div>
                            <div className="form-group">
                                <input id="ModalValue" className="form-control" name="ModalValue" type="text"
                                       autoSave="true" autoComplete="true" placeholder="Une nouvelle valeur"
                                       valueLink={this.linkState('value')}/>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" data-dismiss="modal">Fermer</button>
                            <button type="button" className="btn btn-primary" onClick={this.onClick}>Add</button>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
});

var WidgetModalList = React.createClass({
    getInitialState: function () {

        return {items: this.props.item.data || [], stringItems: JSON.stringify(this.props.item.data) || ""};
    },

    mixins: [React.addons.LinkedStateMixin],
    handleClick: function (element) {

        var nextItems = this.state.items.concat([element]);
        this.setState({items: nextItems, stringItems: JSON.stringify(nextItems)});
    },
    handleRemove: function (element) {
        if (this.props.readonly !== undefined && this.props.readonly === true) {
            return;
        }
        var temp;
        console.info(this.state.items);
        if (this.state.items.length === 1) {
            this.state.items.pop();
            temp = this.state.items;
        } else {
            this.state.items.splice(this.state.items.indexOf(element), 1);
            temp = this.state.items;
        }
        console.info(temp);
        this.setState({items: temp, stringItems: JSON.stringify(temp)});

    },
    render: function () {


        var data = this.props.item.data;
        var header = this.props.item.header;


        var modalId = "#" + this.props.item.id + "Modal";
        var handleRemove = this.handleRemove;
        var tab = [{key: header.key}, {key: header.value}, {key: ""}];

        var widgetModalListHeaderItems = tab.map(function (item) {
            var key = item.key + "widgetModalList-header";
            return (
                <WidgetModalListHeader key={key} item={item}/>
            );
        });

        var widgetModalListDataItems = this.state.items.map(function (item) {
            var key = item.id + "widgetModalList-data";
            return (
                <WidgetModalListData key={key} item={item} removeHandler={handleRemove}/>
            );
        });

        var button = "";
        if (this.props.readonly !== undefined && this.props.readonly == false) {
            button = <div>
                <hr/>
                <input type="hidden" name={this.props.item.id} name={this.props.item.id}
                       valueLink={this.linkState('stringItems')}/>
                <button type="button" className="btn btn-outline btn-success btn-params" data-toggle="modal"
                        data-target={modalId}><i
                    className="fa fa-plus"></i> Ajouter
                </button>
            </div>;

        }


        return (
            <div className="panel panel-default">
                <div className="panel-heading">
                    {header.title}
                </div>
                <div className="panel-body">
                    <div className="table-responsive">
                        <table className="table">
                            <thead>
                            <tr>
                                {widgetModalListHeaderItems}
                            </tr>
                            </thead>
                            <tbody>
                            {widgetModalListDataItems}
                            </tbody>
                        </table>
                    </div>

                    {button}
                </div>

                <WidgetModalListWindow item={this.props.item} addHandler={this.handleClick}/>
            </div>

        );
    }
});


var WidgetFormInput = React.createClass({

    getInitialState: function () {
        return {value: this.props.item.value};
    },
    handleChange: function (event) {
        this.setState({value: event.target.value});

    },
    render: function () {

        var item = this.props.item;
        var input = WidgetForm[item.type];
        var label = <label>{item.label}</label>;
        var value = this.state.value;
        var specialInputs = this.props.special;

        console.log(specialInputs);

        var input;
        if (item.name !== "special") {
            if (item.readonly) {
                input = <span className="form-control">{value}</span>
            } else {
                input = <input className="form-control" placeholder={item.placeholder} value={value} type={item.type}
                               id={item.name} name={item.name}
                               readOnly={item.readonly} required={item.required} hidden={item.hidden}
                               autoComplete={item.autocomplete} autoSave=
                                   {item.autosave} onChange={this.handleChange}/>;
            }
        } else {

            input = specialInputs.filter(function (element) {
                return (element.id === item.value);
            })[0];

            if (input !== undefined) {
                input = <WidgetModalList item={input} readonly={item.readonly}/>
            } else {
                input = "";
            }

            label = "";
        }


        return (
            <div className="form-group">
                {label}
                {input}
            </div>
        );
    }
});


var columnFilter = {};
columnFilter.col1 = React.createClass({
    render: function () {

        var special = this.props.special;
        var items = this.props.items.map(function (item) {
            return (
                <WidgetFormInput key={item.name} item={item} special={special}/>
            );

        });
        return (
            <fieldset>
                {items}
            </fieldset>

        );
    }
});

columnFilter.col2 = React.createClass({
    render: function () {

        var special = this.props.special;
        var firstColumn = function (element) {
            return element.column === undefined || element.column <= 1;
        };

        var secondColumn = function (element) {
            return element.column !== undefined && element.column >= 2;
        };


        var items1 = this.props.items.filter(firstColumn).map(function (item) {
            return (
                <WidgetFormInput key={item.name} item={item} special={special}/>
            );

        });

        console.warn(items1);

        var items2 = this.props.items.filter(secondColumn).map(function (item) {
            return (
                <WidgetFormInput key={item.name} item={item} special={special}/>
            );

        });

        return (
            <fieldset>
                <div className="col-lg-6">
                    {items1}
                </div>
                <div className="col-lg-6">
                    {items2}
                </div>
            </fieldset>


        );
    }
});
columnFilter.col3 = React.createClass({
    render: function () {
        var special = this.props.special;
        var firstColumn = function (element) {
            return element.column === undefined || element.column <= 1;

        };
        var secondColumn = function (element) {
            return element.column !== undefined && element.column == 2;
        };

        var thirdColumn = function (element) {
            return element.column !== undefined && element.column >= 3;
        };

        var items1 = this.props.items.filter(firstColumn).map(function (item) {
            return (
                <WidgetFormInput key={item.name} item={item} special={special}/>
            );

        });


        var items2 = this.props.items.filter(secondColumn).map(function (item) {
            return (
                <WidgetFormInput key={item.name} item={item} special={special}/>
            );

        });


        var items3 = this.props.items.filter(thirdColumn).map(function (item) {
            return (
                <WidgetFormInput key={item.name} item={item} special={special}/>
            );

        });
        return (
            <fieldset>
                <div className="col-lg-3 col-md-4 col-sd-2">
                    {items1}
                </div>
                <div className="col-lg-3 col-md-4  col-sd-2">
                    {items2}
                </div>
                <div className="col-lg-3 col-md-4">
                    {items3}
                </div>
            </fieldset>

        );
    }
});


var SubmitControls = React.createClass({
    render: function () {

        return (
            <div>
                <hr/>
                <button type="reset" className="btn btn-outilne btn-danger button-inline ">Reinitialiser</button>
                <button type="submit" className="btn btn-outline btn-success button-inline pull-right">Sauvegarder
                </button>
            </div>
        );
    }
});

var ReadOnlyControls = React.createClass({

    onEdit: function () {
        console.info("lol");
        this.props.onEdit();
    },
    render: function () {
        return (
            <div>
                <hr/>
                <button type="reset" className="btn btn-outilne btn-primary button-inline" onClick={this.onEdit}>
                    Modifer
                </button>
            </div>
        );
    }

});

var WidgetForm = React.createClass({

    getInitialState: function () {
        return {readonly: this.props.data.readonly};
    },

    editableHandler: function () {
        this.setState({
            readonly: false
        });
    },

    render: function () {

        var data = this.props.data;
        var nbColumn = data.nbColumn || 1;


        var controls = <SubmitControls />;


        //form si présente prime sur la valeur du champs
        if (this.state.readonly !== undefined && this.state.readonly) {
            data.inputs.forEach(function (element) {
                element.readonly = true;
            });

            controls = <ReadOnlyControls onEdit={this.editableHandler}/>
        } else if (this.state.readonly !== undefined && !this.state.readonly) {
            data.inputs.forEach(function (element) {
                element.readonly = false;
            })
        }


        if ([1, 2, 3].indexOf(nbColumn) === -1) {
            nbColumn = 1;
        }

        var formContent = columnFilter["col" + nbColumn];
        console.log(data.specialInputs);
        var contenu = React.createElement(formContent, {items: data.inputs, special: data.specialInputs});


        return (
            <div className="panel-body">
                <form role="form" method="POST" action={data.path}>
                    {contenu}
                    {controls}
                </form>
            </div>
        );
    }

});


React.render(
    <WidgetForm data={formData}/>,
    document.getElementById('widgetForm')
);