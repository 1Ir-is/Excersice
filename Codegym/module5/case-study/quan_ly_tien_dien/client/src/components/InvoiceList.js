import { getAll, deleteById } from "../services/invoiceService";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import ConfirmDeleteModal from "./ModalConfirm";
import { toast } from "react-toastify";

function InvoiceList() {
  const [invoices, setInvoices] = useState([]);
  const [searchCode, setSearchCode] = useState("");
  const [searchName, setSearchName] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [deleteId, setDeleteId] = useState(null);
  const [deleteCode, setDeleteCode] = useState("");

  const navigate = useNavigate();

  const fetchInvoices = async () => {
    const data = await getAll();
    setInvoices(data);
  };

  useEffect(() => {
    fetchInvoices();
  }, []);

  const handleDelete = async (id) => {
    await deleteById(id);
    await fetchInvoices();
    setShowModal(false);
    toast.success("Xóa thành công!");
  };

  const handleShowModal = (id, code) => {
    setDeleteId(id);
    setDeleteCode(code);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setDeleteId(null);
    setDeleteCode("");
  };

  const filtered = invoices.filter((invoice) => {
    const customerName = invoice.khachHang?.ten || "";
    const codeMatch = invoice.maHoaDon
      .toLowerCase()
      .includes(searchCode.toLowerCase());
    const nameMatch = customerName
      .toLowerCase()
      .includes(searchName.toLowerCase());
    return codeMatch && nameMatch;
  });

  return (
    <div>
      <h2>Danh sách hóa đơn tiền điện</h2>
      <div className="mb-3 d-flex gap-2">
        <input
          type="text"
          className="form-control"
          style={{ maxWidth: 250 }}
          placeholder="Tìm theo mã hóa đơn..."
          value={searchCode}
          onChange={(e) => setSearchCode(e.target.value)}
        />
        <input
          type="text"
          className="form-control"
          style={{ maxWidth: 250 }}
          placeholder="Tìm theo tên khách hàng..."
          value={searchName}
          onChange={(e) => setSearchName(e.target.value)}
        />
      </div>
      <Link to="/add" className="btn btn-success mb-2">
        Thêm mới hóa đơn
      </Link>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Mã hóa đơn</th>
            <th>Khách hàng</th>
            <th>Địa chỉ</th>
            <th>Số lượng KW</th>
            <th>Đơn giá (VNĐ)</th>
            <th>Tháng</th>
            <th>Tổng tiền</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          {filtered.map((invoice) => (
            <tr key={invoice.id}>
              <td>{invoice.maHoaDon}</td>
              <td>{invoice.khachHang?.ten}</td>
              <td>{invoice.khachHang?.diaChi}</td>
              <td>{invoice.soLuongKW}</td>
              <td>{invoice.donGia}</td>
              <td>{invoice.thang}</td>
              <td>{invoice.tongTien}</td>
              <td>
                <button
                  className="btn btn-danger btn-sm me-2"
                  onClick={() => handleShowModal(invoice.id, invoice.maHoaDon)}
                >
                  Xoá
                </button>
                <button
                  className="btn btn-primary btn-sm me-2"
                  onClick={() => navigate(`/edit/${invoice.id}`)}
                >
                  Chỉnh sửa
                </button>
              </td>
            </tr>
          ))}
          {filtered.length === 0 && (
            <tr>
              <td colSpan={8} className="text-center">
                Không tìm thấy hóa đơn!
              </td>
            </tr>
          )}
        </tbody>
      </table>
      <ConfirmDeleteModal
        show={showModal}
        name={deleteCode}
        onClose={handleCloseModal}
        onDelete={() => handleDelete(deleteId)}
      />
    </div>
  );
}

export default InvoiceList;
