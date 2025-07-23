const { Component } = require("react");

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      list: [],
      item: "",
    };
  }

  handleChange = (event) => {
    this.setState({ item: event.target.value });
  };

  handleAddItem = () => {
    const { item, list } = this.state;
    if (item.trim() !== "") {
      this.setState({
        list: [...list, item],
        item: "",
      });
    }
  };

  render() {
    return (
      <div
        style={{
          maxWidth: 400,
          margin: "40px auto",
          padding: 20,
          border: "1px solid #ccc",
          borderRadius: 8,
        }}
      >
        <h2>Todo List</h2>
        <input
          type="text"
          value={this.state.item}
          onChange={this.handleChange}
          placeholder="Nhập công việc ..."
          style={{
            width: "70%",
            padding: 8,
            marginRight: 8,
          }}
        />
        <button onClick={this.handleAddItem} style={{ padding: "8px 16px" }}>
          Add
        </button>
        <ul style={{ marginTop: 20 }}>
          {this.state.list.map((todo, idx) => (
            <li key={idx}>{todo}</li>
          ))}
        </ul>
      </div>
    );
  }
}

export default App;
