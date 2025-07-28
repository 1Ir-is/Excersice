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
    return <div className="alert alert-danger">Khong tim thay sinh vien</div>;
  }

  return (
    <div className="card mt-4">
      <div className="card-header">Chi tiet sinh vien</div>
      <div className="card-body">
        <p>
          <strong>Ten: </strong> {student.name}
        </p>
        <p>
          <strong>Tuoi: </strong> {student.age}
        </p>
        <p>
          <strong>Email: </strong> {student.email}
        </p>
        <p>
          <strong>Gioi tinh: </strong> {student.gender || "-"}
        </p>
        <p>
          <strong>Khoa hoc: </strong> {student.courses?.join(", ") || "-"}
        </p>
        <p>
          <strong>Lop: </strong> {student.class || "-"}
        </p>
        <button className="btn btn-primary" onClick={() => navigate(-1)}>
          Quay lai
        </button>
      </div>
    </div>
  );
}

export default StudentDetail;
