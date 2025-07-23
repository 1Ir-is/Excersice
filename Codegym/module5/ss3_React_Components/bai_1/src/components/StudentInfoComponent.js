const { Component } = require("react");

class StudentInfoComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      students: [
        {
          id: 1,
          name: "Nguyễn Văn A",
          age: 30,
          address: "Hà Nội",
        },
      ],
    };
  }

  render() {
    return (
      <div>
        <h2>Danh sách sinh viên</h2>
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Age</th>
              <th>Address</th>
            </tr>
          </thead>
          <tbody>
            {this.state.students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.age}</td>
                <td>{student.address}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default StudentInfoComponent;
