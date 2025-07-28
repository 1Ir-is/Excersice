import { useNavigate, useParams } from "react-router-dom";
import { getById } from "../services/studentService";
import { useEffect, useState } from "react";

function StudentDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [student, setStudent] = useState(null);

  useEffect(() => {
    async function fetchStudent() {
      const data = await getById(Number(id));
      setStudent(data);
    }
    fetchStudent();
  }, [id]);

  if (!student) {
    return <div className="alert alert-danger">Không tìm thấy sinh viên!</div>;
  }

  return (
    <div className="card mt-4">
      <div className="card-header">Chi tiết sinh viên</div>
      <div className="card-body">
        <p>
          <strong>Tên: </strong> {student.name}
        </p>
        <p>
          <strong>Tuổi: </strong> {student.age}
        </p>
        <p>
          <strong>Email: </strong> {student.email}
        </p>
        <p>
          <strong>Giới tính: </strong> {student.gender || "-"}
        </p>
        <p>
          <strong>Khoá học: </strong> {student.courses?.join(", ") || "-"}
        </p>
        <p>
          <strong>Lớp: </strong> {student.class || "-"}
        </p>
        <button className="btn btn-primary" onClick={() => navigate(-1)}>
          Quay lại
        </button>
      </div>
    </div>
  );
}

export default StudentDetail;
