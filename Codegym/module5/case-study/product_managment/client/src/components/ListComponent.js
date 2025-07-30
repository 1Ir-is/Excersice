import { getAll, deleteById } from "../services/productService";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import ProductSearch from "./SearchComponent";
import ConfirmDeleteModal from "./ConfirmDeleteModal";
import { filteredProducts } from "../utils/productUtils";
import { toast } from "react-toastify";
import { getAllCategories } from "../services/categoryService";
import Pagination from "./Pagination";

function ListComponent() {
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [deleteId, setDeleteId] = useState(null);
  const [deleteName, setDeleteName] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const [categoryList, setCategoryList] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const recordsPerPage = 5;

  const navigate = useNavigate();

  const fetchProducts = async () => {
    const data = await getAll();
    setProducts(data);
  };

  const fetchCategories = async () => {
    const data = await getAllCategories();
    setCategoryList(data);
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  useEffect(() => {
    fetchCategories();
  }, []);

  const handleDelete = async (id) => {
    await deleteById(id);
    await fetchProducts();
    setShowModal(false);
    toast.success("Xoá thành công!");
  };

  const handleShowModal = (id, name) => {
    setDeleteId(id);
    setDeleteName(name);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setDeleteId(null);
    setDeleteName("");
  };

  const filter = filteredProducts(products, search).filter(
    (product) => !selectedCategory || product.category === selectedCategory
  );
  const totalPages = Math.ceil(filter.length / recordsPerPage);
  const startIndex = (currentPage - 1) * recordsPerPage;
  const currentRecords = filter.slice(startIndex, startIndex + recordsPerPage);

  useEffect(() => {
    setCurrentPage(1);
  }, [search]);

  return (
    <div>
      <h2>Danh sách sản phẩm</h2>
      <ProductSearch search={search} setSearch={setSearch} />
      <div className="mb-3">
        <select
          className="form-select"
          value={selectedCategory}
          onChange={(e) => setSelectedCategory(e.target.value)}
        >
          <option value="">-- Lọc theo loại --</option>
          {categoryList.map((cat) => (
            <option key={cat.id} value={cat.name}>
              {cat.name}
            </option>
          ))}
        </select>
      </div>
      <Link to="/add" className="btn btn-success mb-2">
        Thêm mới
      </Link>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Tên</th>
            <th>Giá</th>
            <th>Loại</th>
            <th>Số lượng</th>
            <th>Mô tả</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          {currentRecords.map((product) => (
            <tr key={product.id}>
              <td>{product.name}</td>
              <td>{product.price}</td>
              <td>{product.category || "-"}</td>
              <td>{product.quantity}</td>
              <td>{product.description}</td>
              <td>
                <button
                  className="btn btn-danger btn-sm me-2"
                  onClick={() => handleShowModal(product.id, product.name)}
                >
                  Xoá
                </button>
                <button
                  className="btn btn-primary btn-sm me-2"
                  onClick={() => navigate(`/edit/${product.id}`)}
                >
                  Chỉnh sửa
                </button>
                <button
                  className="btn btn-info btn-sm me-2"
                  onClick={() => navigate(`/detail/${product.id}`)}
                >
                  Xem chi tiết
                </button>
              </td>
            </tr>
          ))}
          {currentRecords.length === 0 && (
            <tr>
              <td colSpan={7} className="text-center">
                Không tìm thấy sản phẩm!
              </td>
            </tr>
          )}
        </tbody>
      </table>
      <div className="d-flex justify-content-center mt-3">
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={setCurrentPage}
        />
      </div>
      <ConfirmDeleteModal
        show={showModal}
        name={deleteName}
        onClose={handleCloseModal}
        onDelete={() => handleDelete(deleteId)}
      />
    </div>
  );
}

export default ListComponent;
