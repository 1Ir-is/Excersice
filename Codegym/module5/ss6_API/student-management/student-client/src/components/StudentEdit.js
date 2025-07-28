import { useParams, useNavigate } from "react-router-dom";
import { getById, updateById } from "../services/studentService";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { toast } from "react-toastify";
import { getAllClasses } from "../services/classService";
import { useEffect, useState } from "react";

const coursesList = ["ReactJS", "NodeJS", "Java", "Python"];

function StudentEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [student, setStudent] = useState(null);
  const [classes, setClasses] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const stu = await getById(Number(id));
      setStudent(stu);
      const cls = await getAllClasses();
      setClasses(cls);
    }
    fetchData();
  }, [id]);

  if (!student) {
    return <div className="alert alert-danger">Không tìm thấy sinh viên</div>;
  }

  const studentValidationSchema = Yup.object().shape({
    name: Yup.string().required("Tên không được để trống!"),
    age: Yup.number()
      .min(1, "Tuổi không hợp lệ!")
      .required("Tuổi không được để trống!"),
    email: Yup.string()
      .email("Email không hợp lệ!")
      .required("Email không được để trống!"),
    gender: Yup.string().required("Vui lòng chọn giới tính!"),
    courses: Yup.array().min(1, "Vui lòng chọn ít nhất 1 khoá học!"),
    class: Yup.string().required("Vui lòng chọn lớp!"),
  });

  return (
    <div className="card">
      <div className="card-header">Chỉnh sửa thông tin</div>
      <div className="card-body">
        <Formik
          initialValues={{
            name: student.name,
            age: student.age,
            email: student.email,
            gender: student.gender || "",
            courses: student.courses || [],
            class: student.class || "",
          }}
          validationSchema={studentValidationSchema}
          onSubmit={(values) => {
            updateById(Number(id), values);
            toast.success("Cập nhật thành công!");
            navigate("/");
          }}
        >
          {({ values }) => (
            <Form>
              <div className="mb-3">
                <label>Tên</label>
                <Field name="name" className="form-control" />
                <ErrorMessage
                  name="name"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Tuổi</label>
                <Field name="age" type="number" className="form-control" />
                <ErrorMessage
                  name="age"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Email</label>
                <Field name="email" className="form-control" />
                <ErrorMessage
                  name="email"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Giới tính</label>
                <div>
                  <label className="me-3">
                    <Field type="radio" name="gender" value="Nam" />
                    Nam
                  </label>
                  <label>
                    <Field type="radio" name="gender" value="Nu" />
                    Nữ
                  </label>
                </div>
                <ErrorMessage
                  name="gender"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Khoá học</label>
                <div>
                  {coursesList.map((course) => (
                    <label key={course} className="me-3">
                      <Field
                        type="checkbox"
                        name="courses"
                        value={course}
                        checked={values.courses.includes(course)}
                      />
                      {course}
                    </label>
                  ))}
                </div>
                <ErrorMessage
                  name="courses"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Lop</label>
                <Field as="select" name="class" className="form-select">
                  <option value="">--Chọn lớp--</option>
                  {classes.map((c) => (
                    <option key={c.id} value={c.name}>
                      {c.name}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  name="class"
                  component="div"
                  className="text-danger"
                />
              </div>
              <button type="submit" className="btn btn-primary">
                Cập nhật
              </button>
              <button
                type="button"
                className="btn btn-secondary ms-2"
                onClick={() => navigate("/")}
              >
                Huỷ
              </button>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default StudentEdit;
