import { useParams, useNavigate } from "react-router-dom";
import { getById, updateById } from "../services/studentService";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { toast } from "react-toastify";
import { getAllClasses } from "../services/classService";

const coursesList = ["ReactJS", "NodeJS", "Java", "Python"];

function StudentEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const student = getById(Number(id));
  const classes = getAllClasses();

  if (!student) {
    return <div className="alert alert-danger">Khong tim thay sinh vien</div>;
  }

  const StudentSchema = Yup.object().shape({
    name: Yup.string().required("Ten khong duoc de trong!"),
    age: Yup.number()
      .min(1, "Tuoi khong hop le!")
      .required("Tuoi khong duoc de trong!"),
    email: Yup.string()
      .email("Email khong hop le!")
      .required("Email khong duoc de trong!"),
    gender: Yup.string().required("Vui long chon gioi tinh!"),
    courses: Yup.array().min(1, "Vui long chon it nhat 1 khoa hoc!"),
    class: Yup.string().required("Vui long chon lop!"),
  });

  return (
    <div className="card">
      <div className="card-header">Chinh sua thong tin</div>
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
          validationSchema={StudentSchema}
          onSubmit={(values) => {
            updateById(Number(id), values);
            toast.success("Cap nhat thanh cong!");
            navigate("/");
          }}
        >
          {({ values }) => (
            <Form>
              <div className="mb-3">
                <label>Name</label>
                <Field name="name" className="form-control" />
                <ErrorMessage
                  name="name"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Age</label>
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
                <label>Gioi tinh</label>
                <div>
                  <label className="me-3">
                    <Field type="radio" name="gender" value="Nam" />
                    Nam
                  </label>
                  <label>
                    <Field type="radio" name="gender" value="Nu" />
                    Nu
                  </label>
                </div>
                <ErrorMessage
                  name="gender"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Khoa hoc</label>
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
                  <option value="">--Chon lop--</option>
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
                Cap nhat
              </button>
              <button
                type="button"
                className="btn btn-secondary ms-2"
                onClick={() => navigate("/")}
              >
                Huy
              </button>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default StudentEdit;
