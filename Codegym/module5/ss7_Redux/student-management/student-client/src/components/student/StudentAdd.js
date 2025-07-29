import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { addNew } from "../../services/studentService";
import { getAllClasses } from "../../services/classService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect, useState } from "react";

const coursesList = ["ReactJS", "NodeJS", "Java", "Python"];

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

function StudentAdd() {
  const navigate = useNavigate();
  const [classes, setClasses] = useState([]);

  useEffect(() => {
    async function fetchClasses() {
      const data = await getAllClasses();
      setClasses(data);
    }
    fetchClasses();
  }, []);

  return (
    <div className="card">
      <div className="card-header">Thêm mới sinh viên</div>
      <div className="card-body">
        <Formik
          initialValues={{
            name: "",
            age: "",
            email: "",
            gender: "",
            courses: [],
            class: "",
          }}
          validationSchema={studentValidationSchema}
          onSubmit={(values) => {
            addNew(values);
            toast.success("Thêm mới thành công!");
            navigate("/student");
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
                      <Field type="checkbox" name="courses" value={course} />
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
                <label>Lớp</label>
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
                Thêm mới
              </button>
              <button
                type="button"
                className="btn btn-secondary ms-2"
                onClick={() => navigate("/student")}
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

export default StudentAdd;
