import React, { useState } from "react";
import { getAll, addNew, deleteById } from "../service/studentService";
import SearchComponent from "./SearchComponent";
import AddComponent from "./AddComponent";
import ConfirmModal from "./ConfirmModal";
import { filterStudents } from "../utils/studentUtils";

function StudentList() {
  const [students, setStudents] = useState(getAll());
  const [showForm, setShowForm] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [deleteId, setDeleteId] = useState(null);

  const handleAdd = (form) => {
    addNew({
      id: Date.now().toString(),
      ...form,
    });
    setStudents(getAll());
    setShowForm(false);
  };

  const handleDeleteClick = (id) => {
    setDeleteId(id);
    setShowModal(true);
  };

  const handleConfirmDelete = () => {
    deleteById(deleteId);
    setStudents(getAll());
    setShowModal(false);
    setDeleteId(null);
  };

  const handleCancelDelete = () => {
    setShowModal(false);
    setDeleteId(null);
  };

  const filteredStudents = filterStudents(students, searchTerm);

  return (
    <div>
      <h2>Danh sách sinh viên</h2>
      <SearchComponent searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Họ tên</th>
            <th>Tuổi</th>
            <th>Email</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          {filteredStudents.map((sinhVien) => (
            <tr key={sinhVien.id}>
              <td>{sinhVien.name}</td>
              <td>{sinhVien.age}</td>
              <td>{sinhVien.email}</td>
              <td>
                <button onClick={() => handleDeleteClick(sinhVien.id)}>
                  Xóa
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={() => setShowForm(true)}>Thêm mới</button>
      {showForm && (
        <AddComponent onAdd={handleAdd} onCancel={() => setShowForm(false)} />
      )}
      <ConfirmModal
        show={showModal}
        onConfirm={handleConfirmDelete}
        onCancel={handleCancelDelete}
        message="Bạn có chắc chắn muốn xóa sinh viên này?"
      />
    </div>
  );
}

export default StudentList;
