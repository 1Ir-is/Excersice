import React, { useState } from "react";
import { addNew } from "../service/studentService";

function AddComponent({ onCancel }) {
  const [form, setForm] = useState({
    name: "",
    age: "",
    email: "",
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = () => {
    if (!form.name || !form.age || !form.email) return;
    addNew({
      id: Date.now().toString(),
      ...form,
    });
    setForm({
      name: "",
      age: "",
      email: "",
    });
    if (onCancel) onCancel();
  };

  return (
    <div style={{ marginTop: 16 }}>
      <h3>Thêm mới sinh viên</h3>
      <input
        name="name"
        placeholder="Họ tên"
        value={form.name}
        onChange={handleChange}
      />
      <input
        name="age"
        placeholder="Tuổi"
        value={form.age}
        onChange={handleChange}
      />
      <input
        name="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
      />
      <button onClick={handleSubmit}>Lưu</button>
      <button onClick={onCancel} style={{ marginLeft: 8 }}>
        Huỷ
      </button>
    </div>
  );
}

export default AddComponent;
