import React, { useState, useEffect } from "react";
import {
  getAllCategories,
  createCategory,
  updateCategory,
  deleteCategory,
} from "../services/categoryService";
import "./CategoryManagement.css";

const CategoryManagement = () => {
  const [categories, setCategories] = useState([]);
  const [formData, setFormData] = useState({ name: "", description: "" });
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      setLoading(true);
      const data = await getAllCategories();
      setCategories(data);
    } catch (error) {
      console.error("Error fetching categories:", error);
      alert("Lỗi khi tải danh sách danh mục");
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      if (editingId) {
        await updateCategory(editingId, formData);
        alert("Cập nhật danh mục thành công");
      } else {
        await createCategory(formData);
        alert("Thêm danh mục thành công");
      }
      setFormData({ name: "", description: "" });
      setEditingId(null);
      fetchCategories();
    } catch (error) {
      console.error("Error saving category:", error);
      alert("Lỗi khi lưu danh mục");
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = (category) => {
    setFormData({ name: category.name, description: category.description });
    setEditingId(category.id);
  };

  const handleDelete = async (id) => {
    if (window.confirm("Bạn có chắc chắn muốn xóa danh mục này?")) {
      try {
        setLoading(true);
        await deleteCategory(id);
        alert("Xóa danh mục thành công");
        fetchCategories();
      } catch (error) {
        console.error("Error deleting category:", error);
        alert("Lỗi khi xóa danh mục");
      } finally {
        setLoading(false);
      }
    }
  };

  const handleCancel = () => {
    setFormData({ name: "", description: "" });
    setEditingId(null);
  };

  return (
    <div className="category-management">
      <h2>Quản lý danh mục sách</h2>

      <div className="category-form">
        <h3>{editingId ? "Sửa danh mục" : "Thêm danh mục mới"}</h3>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Tên danh mục:</label>
            <input
              type="text"
              id="name"
              value={formData.name}
              onChange={(e) =>
                setFormData({ ...formData, name: e.target.value })
              }
              required
              disabled={loading}
            />
          </div>
          <div className="form-group">
            <label htmlFor="description">Mô tả:</label>
            <textarea
              id="description"
              value={formData.description}
              onChange={(e) =>
                setFormData({ ...formData, description: e.target.value })
              }
              rows="3"
              disabled={loading}
            />
          </div>
          <div className="form-actions">
            <button type="submit" disabled={loading}>
              {loading ? "Đang xử lý..." : editingId ? "Cập nhật" : "Thêm mới"}
            </button>
            {editingId && (
              <button type="button" onClick={handleCancel} disabled={loading}>
                Hủy
              </button>
            )}
          </div>
        </form>
      </div>

      <div className="category-list">
        <h3>Danh sách danh mục</h3>
        {loading && <p>Đang tải...</p>}
        {categories.length === 0 && !loading ? (
          <p>Chưa có danh mục nào</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên danh mục</th>
                <th>Mô tả</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              {categories.map((category) => (
                <tr key={category.id}>
                  <td>{category.id}</td>
                  <td>{category.name}</td>
                  <td>{category.description}</td>
                  <td>
                    <button
                      onClick={() => handleEdit(category)}
                      disabled={loading}
                    >
                      Sửa
                    </button>
                    <button
                      onClick={() => handleDelete(category.id)}
                      disabled={loading}
                    >
                      Xóa
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default CategoryManagement;
