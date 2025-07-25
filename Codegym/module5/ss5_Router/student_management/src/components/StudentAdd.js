import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { addNew } from "../services/studentService";
import { getAllClasses } from "../services/classService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const coursesList = ["ReactJS", "NodeJS", "Java", "Python"];

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

function StudentAdd() {
  const navigate = useNavigate();
  const classes = getAllClasses();

  return (
    <div className="card">
      <div className="card-header">Them moi sinh vien</div>
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
          validationSchema={StudentSchema}
          onSubmit={(values) => {
            addNew(values);
            toast.success("Them moi thanh cong!");
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
                Them moi
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

export default StudentAdd;
