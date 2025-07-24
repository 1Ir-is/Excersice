import React, { useState } from "react";

function StudentList() {
  const [students, setStudents] = useState([
    {
      id: 1,
      code: "SV001",
      name: "Nguyễn Văn A",
      age: 20,
      email: "vana@gmail.com",
    },
    {
      id: 2,
      code: "SV002",
      name: "Nguyễn Văn B",
      age: 20,
      email: "vanb@gmail.com",
    },
  ]);

  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [email, setEmail] = useState("");
  const [showForm, setShowForm] = useState(false);

  const handleAdd = () => {
    if (!code || !name || !age || !email) {
      return;
    }

    setStudents([
      ...students,
      {
        id: students.length + 1,
        code,
        name,
        age,
        email,
      },
    ]);
    setCode("");
    setName("");
    setEmail("");
    setAge("");
    setShowForm(false);
  };

  return (
    <div>
      <h2>Danh sách sinh viên</h2>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Mã Sinh Viên</th>
            <th>Họ tên</th>
            <th>Tuổi</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
          {students.map((sinhVien) => (
            <tr key={sinhVien.id}>
              <td>{sinhVien.code}</td>
              <td>{sinhVien.name}</td>
              <td>{sinhVien.age}</td>
              <td>{sinhVien.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={() => setShowForm(true)}>Thêm mới</button>
      {showForm && (
        <div style={{ marginTop: "16px" }}>
          <h3>Thêm mới sinh viên</h3>
          <input
            placeholder="Mã sinh viên"
            value={code}
            onChange={(e) => setCode(e.target.value)}
          />
          <input
            placeholder="Họ tên"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <input
            placeholder="Tuổi"
            value={age}
            onChange={(e) => setAge(e.target.value)}
          />
          <input
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button onClick={handleAdd}>Lưu</button>
          <button
            onClick={() => setShowForm(false)}
            style={{ marginLeft: "8px" }}
          >
            Huỷ
          </button>
        </div>
      )}
    </div>
  );
}

export default StudentList;
