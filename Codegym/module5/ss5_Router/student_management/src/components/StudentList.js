import { getAll, deleteById } from "../services/studentService";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import StudentSearch from "./StudentSearch";
import ConfirmDeleteModal from "./ConfirmDeleteModal";
import { filteredStudents } from "../utils/studentUtils";
import { toast } from "react-toastify";

function StudentList() {
  const [students, setStudents] = useState(getAll());
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [deleteId, setDeleteId] = useState(null);
  const [deleteName, setDeleteName] = useState("");
  const navigate = useNavigate();

  const handleDelete = (id) => {
    deleteById(id);
    setStudents(getAll());
    setShowModal(false);
    toast.success("Xoa thanh cong!");
  };

  const handleShowModal = (id, name) => {
    setDeleteId(id);
    setDeleteName(name);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setDeleteId(null);
    setDeleteName("");
  };

  const filter = filteredStudents(students, search);

  return (
    <div>
      <h2>Danh sach sinh vien</h2>
      <StudentSearch search={search} setSearch={setSearch} />
      <Link to="/add" className="btn btn-success mb-2">
        Them moi
      </Link>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Ten</th>
            <th>Tuoi</th>
            <th>Email</th>
            <th>Gioi tinh</th>
            <th>Khoa hoc</th>
            <th>Lop</th>
            <th>Hanh dong</th>
          </tr>
        </thead>
        <tbody>
          {filter.map((student) => (
            <tr key={student.id}>
              <td>{student.name}</td>
              <td>{student.age}</td>
              <td>{student.email}</td>
              <td>{student.gender || "-"}</td>
              <td>
                {student.courses && student.courses.length > 0
                  ? student.courses.join(", ")
                  : "-"}
              </td>
              <td>{student.class || "-"}</td>
              <td>
                <button
                  className="btn btn-danger btn-sm me-2"
                  onClick={() => handleShowModal(student.id, student.name)}
                >
                  Xoa
                </button>
                <button
                  className="btn btn-primary btn-sm me-2"
                  onClick={() => navigate(`/edit/${student.id}`)}
                >
                  Chinh sua
                </button>
                <button
                  className="btn btn-info btn-sm me-2"
                  onClick={() => navigate(`/detail/${student.id}`)}
                >
                  Xem chi tiet
                </button>
              </td>
            </tr>
          ))}
          {filter.length === 0 && (
            <tr>
              <td colSpan={6} className="text-center">
                Khong tim thay hoc sinh!
              </td>
            </tr>
          )}
        </tbody>
      </table>
      <ConfirmDeleteModal
        show={showModal}
        name={deleteName}
        onClose={handleCloseModal}
        onDelete={() => handleDelete(deleteId)}
      />
    </div>
  );
}

export default StudentList;
