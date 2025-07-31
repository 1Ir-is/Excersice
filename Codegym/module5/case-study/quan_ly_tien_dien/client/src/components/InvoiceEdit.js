import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import * as Yup from "yup";
import { getById, updateById } from "../services/invoiceService";
import { getAllCustomers } from "../services/customerService";
import { Formik, Form, Field, ErrorMessage } from "formik";
import { toast } from "react-toastify";

const invoiceValidationSchema = Yup.object().shape({
  maHoaDon: Yup.string()
    .matches(/^MHD-\d{4}$/, "Mã hóa đơn phải theo định dạng MHD-XXXX")
    .required("Mã hóa đơn không được để trống!"),
  soLuongKW: Yup.number()
    .min(1, "Số lượng KW phải lớn hơn 0")
    .required("Số lượng KW không được để trống!"),
  donGia: Yup.number()
    .min(1001, "Đơn giá phải lớn hơn 1000 VND")
    .required("Đơn giá không được để trống!"),
  thang: Yup.string()
    .matches(/^(0[1-9]|1[0-2])\/\d{4}$/, "Tháng phải theo định dạng mm/yyyy")
    .required("Tháng không được để trống!"),
  khachHangId: Yup.number().required("Vui lòng chọn khách hàng!"),
});

function InvoiceEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [invoice, setInvoice] = useState(null);
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const inv = await getById(Number(id));
      setInvoice(inv);
      const cus = await getAllCustomers();
      setCustomers(cus);
    }
    fetchData();
  }, [id]);

  if (!invoice) {
    return <div className="alert alert-danger">Không tìm thấy hoá đơn</div>;
  }

  return (
    <div className="card">
      <div className="card-header">Chỉnh sửa hoá đơn tiền điện</div>
      <div className="card-body">
        <Formik
          initialValues={{
            maHoaDon: invoice.maHoaDon,
            soLuongKW: invoice.soLuongKW,
            donGia: invoice.donGia,
            thang: invoice.thang,
            khachHangId: invoice.khachHang?.id || "",
          }}
          validationSchema={invoiceValidationSchema}
          onSubmit={async (values) => {
            const selectedCustomer = customers.find(
              (c) => c.id === Number(values.khachHangId)
            );
            const tongTien = values.soLuongKW * values.donGia;
            await updateById(Number(id), {
              ...values,
              tongTien,
              khachHang: selectedCustomer,
            });
            toast.success("Cập nhập thành công!");
            navigate("/");
          }}
        >
          {({ setFieldValue }) => (
            <Form>
              <div className="mb-3">
                <label>Mã hoá đơn</label>
                <Field name="maHoaDon" className="form-control" />
                <ErrorMessage
                  name="maHoaDon"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Số lượng KW</label>
                <Field
                  name="soLuongKW"
                  type="number"
                  className="form-control"
                />
                <ErrorMessage
                  name="soLuongKW"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Đơn giá (VNĐ)</label>
                <Field name="donGia" type="number" className="form-control" />
                <ErrorMessage
                  name="donGia"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Tháng</label>
                <Field
                  name="thang"
                  placeholder="mm/yyyy"
                  className="form-control"
                />
                <ErrorMessage
                  name="thang"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="mb-3">
                <label>Khách hàng</label>
                <Field
                  as="select"
                  name="khachHangId"
                  className="form-select"
                  onChange={(e) => setFieldValue("khachHangId", e.target.value)}
                >
                  <option value="">--Chọn khách hàng--</option>
                  {customers.map((c) => (
                    <option key={c.id} value={c.id}>
                      {c.ten} - {c.diaChi}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  name="khachHangId"
                  component="div"
                  className="text-danger"
                />
              </div>
              <button type="submit" className="btn btn-primary">
                Cập nhập
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

export default InvoiceEdit;
