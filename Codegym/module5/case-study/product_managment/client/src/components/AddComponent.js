import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { addNew } from "../services/productService";
import { getAllCategories } from "../services/categoryService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect, useState } from "react";

const productValidationSchema = Yup.object().shape({
  name: Yup.string().required("Tên sản phẩm không được để trống!"),
  price: Yup.number()
    .min(0, "Giá phải lớn hơn hoặc bằng 0!")
    .required("Giá không được để trống!"),
  category: Yup.string().required("Vui lòng chọn danh mục!"),
  quantity: Yup.number()
    .min(0, "Số lượng phải lớn hơn hoặc bằng 0!")
    .required("Số lượng không được để trống!"),
  description: Yup.string().required("Mô tả không được để trống!"),
});

function AddComponent() {
  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    async function fetchCategories() {
      const data = await getAllCategories();
      setCategories(data);
    }
    fetchCategories();
  }, []);

  return (
    <div className="card">
      <div className="card-header">Thêm mới sản phẩm</div>
      <div className="card-body">
        <Formik
          initialValues={{
            name: "",
            price: "",
            category: "",
            quantity: "",
            description: "",
          }}
          validationSchema={productValidationSchema}
          onSubmit={(values) => {
            addNew(values);
            toast.success("Thêm mới sản phẩm thành công!");
            navigate("/");
          }}
        >
          {() => (
            <Form>
              <div className="mb-3">
                <label>Tên sản phẩm</label>
                <Field name="name" className="form-control" />
                <ErrorMessage
                  name="name"
                  component="div"
                  className="text-danger"
                />
              </div>

              <div className="mb-3">
                <label>Giá</label>
                <Field name="price" type="number" className="form-control" />
                <ErrorMessage
                  name="price"
                  component="div"
                  className="text-danger"
                />
              </div>

              <div className="mb-3">
                <label>Danh mục</label>
                <Field as="select" name="category" className="form-select">
                  <option value="">--Chọn danh mục--</option>
                  {categories.map((c) => (
                    <option key={c.id} value={c.name}>
                      {c.name}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  name="category"
                  component="div"
                  className="text-danger"
                />
              </div>

              <div className="mb-3">
                <label>Số lượng</label>
                <Field name="quantity" type="number" className="form-control" />
                <ErrorMessage
                  name="quantity"
                  component="div"
                  className="text-danger"
                />
              </div>

              <div className="mb-3">
                <label>Mô tả</label>
                <Field
                  name="description"
                  as="textarea"
                  className="form-control"
                />
                <ErrorMessage
                  name="description"
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

export default AddComponent;
