import { useDispatch, useSelector } from "react-redux";
import { loginActionAsync } from "../../redux/action/action";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

function Login() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { error } = useSelector((state) => state.login);

  const validationSchema = Yup.object({
    username: Yup.string().required("Tên đăng nhập không được để trống"),
    password: Yup.string().required("Mật khẩu không được để trống"),
  });

  const handleSubmit = async (values, { setSubmitting }) => {
    const success = await dispatch(loginActionAsync(values));
    setSubmitting(false);
    if (success) {
      toast.success("Đăng nhập thành công!");
      navigate("/student");
    } else {
      toast.error(error || "Đăng nhập thất bại!");
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
          <h2>Đăng nhập</h2>
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
            className="btn btn-primary w-100"
          >
            Đăng nhập
          </button>
        </Form>
      )}
    </Formik>
  );
}

export default Login;
