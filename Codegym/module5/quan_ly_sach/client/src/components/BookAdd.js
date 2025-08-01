import React, { useState, useEffect } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { createBook, updateBook } from "../services/bookService";
import { getAllCategories } from "../services/categoryService";
import "./BookAdd.css";

const BookAdd = ({ editingBook, onSuccess, onCancel }) => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const data = await getAllCategories();
      setCategories(data);
    } catch (error) {
      console.error("Error fetching categories:", error);
      alert("Lỗi khi tải danh sách danh mục");
    }
  };

  // Validation schema với Yup
  const validationSchema = Yup.object({
    bookCode: Yup.string()
      .required("Mã sách là bắt buộc")
      .matches(
        /^BO-\d{4}$/,
        "Mã sách phải theo định dạng BO-XXXX (ví dụ: BO-0001)"
      ),
    title: Yup.string()
      .required("Tên sách là bắt buộc")
      .max(100, "Tên sách không được dài quá 100 ký tự"),
    categoryId: Yup.string().required("Vui lòng chọn danh mục"),
    quantity: Yup.number()
      .required("Số lượng là bắt buộc")
      .positive("Số lượng phải lớn hơn 0")
      .integer("Số lượng phải là số nguyên"),
    importDate: Yup.date()
      .required("Ngày nhập là bắt buộc")
      .max(new Date(), "Ngày nhập không được lớn hơn ngày hiện tại"),
  });

  // Initial values
  const initialValues = {
    bookCode: editingBook?.bookCode || "",
    title: editingBook?.title || "",
    categoryId: editingBook?.categoryId || "",
    quantity: editingBook?.quantity || "",
    importDate: editingBook?.importDate || "",
  };

  const handleSubmit = async (
    values,
    { setSubmitting, setFieldError, resetForm }
  ) => {
    try {
      setLoading(true);
      const bookData = {
        ...values,
        categoryId: parseInt(values.categoryId),
        quantity: parseInt(values.quantity),
      };

      if (editingBook) {
        await updateBook(editingBook.id, bookData);
        alert("Cập nhật sách thành công!");
      } else {
        await createBook(bookData);
        alert("Thêm sách thành công!");
      }

      resetForm();
      if (onSuccess) {
        onSuccess();
      }
    } catch (error) {
      console.error("Error saving book:", error);

      // Handle validation errors from server
      if (error.response && error.response.status === 400) {
        const serverErrors = error.response.data;
        if (typeof serverErrors === "object") {
          // Set field errors from server
          Object.keys(serverErrors).forEach((field) => {
            setFieldError(field, serverErrors[field]);
          });
        } else {
          alert("Dữ liệu không hợp lệ");
        }
      } else {
        alert("Lỗi khi lưu sách");
      }
    } finally {
      setLoading(false);
      setSubmitting(false);
    }
  };

  const handleCancel = (resetForm) => {
    resetForm();
    if (onCancel) {
      onCancel();
    }
  };

  return (
    <div className="book-add">
      <h2>{editingBook ? "Sửa thông tin sách" : "Thêm sách mới"}</h2>

      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
        enableReinitialize={true}
      >
        {({ isSubmitting, resetForm, errors, touched }) => (
          <Form className="book-form">
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="bookCode">Mã sách *</label>
                <Field
                  type="text"
                  id="bookCode"
                  name="bookCode"
                  placeholder="BO-0001"
                  className={errors.bookCode && touched.bookCode ? "error" : ""}
                  disabled={loading || isSubmitting}
                />
                <ErrorMessage
                  name="bookCode"
                  component="span"
                  className="error-message"
                />
              </div>

              <div className="form-group">
                <label htmlFor="title">Tên sách *</label>
                <Field
                  type="text"
                  id="title"
                  name="title"
                  className={errors.title && touched.title ? "error" : ""}
                  disabled={loading || isSubmitting}
                />
                <ErrorMessage
                  name="title"
                  component="span"
                  className="error-message"
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="categoryId">Danh mục *</label>
                <Field
                  as="select"
                  id="categoryId"
                  name="categoryId"
                  className={
                    errors.categoryId && touched.categoryId ? "error" : ""
                  }
                  disabled={loading || isSubmitting}
                >
                  <option value="">Chọn danh mục</option>
                  {categories.map((category) => (
                    <option key={category.id} value={category.id}>
                      {category.categoryName}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  name="categoryId"
                  component="span"
                  className="error-message"
                />
              </div>

              <div className="form-group">
                <label htmlFor="quantity">Số lượng *</label>
                <Field
                  type="number"
                  id="quantity"
                  name="quantity"
                  min="1"
                  className={errors.quantity && touched.quantity ? "error" : ""}
                  disabled={loading || isSubmitting}
                />
                <ErrorMessage
                  name="quantity"
                  component="span"
                  className="error-message"
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="importDate">Ngày nhập *</label>
              <Field
                type="date"
                id="importDate"
                name="importDate"
                className={
                  errors.importDate && touched.importDate ? "error" : ""
                }
                disabled={loading || isSubmitting}
              />
              <ErrorMessage
                name="importDate"
                component="span"
                className="error-message"
              />
            </div>

            <div className="form-actions">
              <button
                type="submit"
                className="submit-btn"
                disabled={loading || isSubmitting}
              >
                {loading || isSubmitting
                  ? "Đang xử lý..."
                  : editingBook
                  ? "Cập nhật"
                  : "Thêm mới"}
              </button>

              <button
                type="button"
                className="cancel-btn"
                onClick={() => handleCancel(resetForm)}
                disabled={loading || isSubmitting}
              >
                {editingBook ? "Hủy" : "Làm mới"}
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default BookAdd;
