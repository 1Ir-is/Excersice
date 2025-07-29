import { useDispatch, useSelector } from "react-redux";
import { registerActionAsync } from "../../redux/action/action";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

function Register() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { error } = useSelector((state) => state.register);

  const validationSchema = Yup.object({
    username: Yup.string()
      .required("Tên đăng nhập không được để trống")
      .min(4, "Tên đăng nhập tối thiểu 4 ký tự"),
    password: Yup.string()
      .required("Mật khẩu không được để trống")
      .min(6, "Mật khẩu tối thiểu 6 ký tự"),
  });

  const handleSubmit = async (values, { setSubmitting }) => {
    const success = await dispatch(registerActionAsync(values));
    setSubmitting(false);
    if (success) {
      toast.success("Đăng ký thành công!");
      navigate("/login");
    } else {
      toast.error(error || "Đăng ký thất bại!");
    }
  };

  return (
    <Formik
      initialValues={{ username: "", password: "" }}
      validationSchema={validationSchema}
      onSubmit={handleSubmit}
    >
      {({ isSubmitting }) => (
        <Form style={{ maxWidth: 400, margin: "40px auto" }}>
          <h2>Đăng ký</h2>
          <Field
            type="text"
            name="username"
            placeholder="Tên đăng nhập"
            className="form-control mb-2"
          />
          <ErrorMessage
            name="username"
            component="div"
            className="text-danger mb-2"
          />
          <Field
            type="password"
            name="password"
            placeholder="Mật khẩu"
            className="form-control mb-2"
          />
          <ErrorMessage
            name="password"
            component="div"
            className="text-danger mb-2"
          />
          <button
            type="submit"
            disabled={isSubmitting}
            className="btn btn-success w-100"
          >
            Đăng ký
          </button>
        </Form>
      )}
    </Formik>
  );
}

export default Register;
