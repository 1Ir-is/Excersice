import { getAll, deleteById } from "../services/studentService";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import StudentSearch from "./StudentSearch";
import ConfirmDeleteModal from "./ConfirmDeleteModal";
import { filteredStudents } from "../utils/studentUtils";
import { toast } from "react-toastify";
import { getAllClasses } from "../services/classService";

function StudentList() {
  const [students, setStudents] = useState([]);
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [deleteId, setDeleteId] = useState(null);
  const [deleteName, setDeleteName] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const [classList, setClassList] = useState([]);
  const [selectedClass, setSelectedClass] = useState("");
  const recordsPerPage = 5;

  const navigate = useNavigate();

  const fetchStudents = async () => {
    const data = await getAll();
    setStudents(data);
  };

  const fetchClasses = async () => {
    const data = await getAllClasses();
    setClassList(data);
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  useEffect(() => {
    fetchClasses();
  }, []);

  const handleDelete = async (id) => {
    await deleteById(id);
    await fetchStudents();
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

  const filter = filteredStudents(students, search).filter(
    (student) => !selectedClass || student.class === selectedClass
  );
  const totalPages = Math.ceil(filter.length / recordsPerPage);
  const startIndex = (currentPage - 1) * recordsPerPage;
  const currentRecords = filter.slice(startIndex, startIndex + recordsPerPage);

  useEffect(() => {
    setCurrentPage(1);
  }, [search]);

  return (
    <div>
      <h2>Danh sach sinh vien</h2>
      <StudentSearch search={search} setSearch={setSearch} />
      <div className="mb-3">
        <select
          className="form-select"
          value={selectedClass}
          onChange={(e) => setSelectedClass(e.target.value)}
        >
          <option value="">-- Loc theo lop --</option>
          {classList.map((cls) => (
            <option key={cls.id} value={cls.name}>
              {cls.name}
            </option>
          ))}
        </select>
      </div>
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
          {currentRecords.map((student) => (
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
          {currentRecords.length === 0 && (
            <tr>
              <td colSpan={7} className="text-center">
                Khong tim thay hoc sinh!
              </td>
            </tr>
          )}
        </tbody>
      </table>
      <div className="d-flex justify-content-center mt-3">
        <nav>
          <ul className="pagination">
            <li className={`page-item${currentPage === 1 ? " disabled" : ""}`}>
              <button
                className="page-link"
                onClick={() => setCurrentPage(currentPage - 1)}
              >
                Truoc
              </button>
            </li>
            {[...Array(totalPages)].map((_, idx) => (
              <li
                key={idx}
                className={`page-item${
                  currentPage === idx + 1 ? " active" : ""
                }`}
              >
                <button
                  className="page-link"
                  onClick={() => setCurrentPage(idx + 1)}
                >
                  {idx + 1}
                </button>
              </li>
            ))}
            <li
              className={`page-item${
                currentPage === totalPages ? " disabled" : ""
              }`}
            >
              <button
                className="page-link"
                onClick={() => setCurrentPage(currentPage + 1)}
              >
                Sau
              </button>
            </li>
          </ul>
        </nav>
      </div>
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
