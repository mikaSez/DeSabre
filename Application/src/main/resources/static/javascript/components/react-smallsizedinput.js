/** @jsx React.DOM */
var LimitedMessage = React.createClass({

    getInitialState: function () {
        return {value: ''};
    },

    handleChange: function (event) {
        this.setState({value: event.target.value.substr(0, 50)});
    },
    render: function () {
        return (
            <input type="text" id="message" name="message" placeholder="Très court message" className="form-control"
                   value={this.state.value} onChange={this.handleChange}/>
        );
    }

});
React.render(
    <LimitedMessage />,
    document.getElementById('messageInput')
);
