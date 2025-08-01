import React, { useState, useEffect } from "react";
import {
  getAllBooks,
  deleteBook,
  getBooksByCategory,
} from "../services/bookService";
import { getAllCategories } from "../services/categoryService";
import "./BookList.css";

const BookList = ({ onEdit }) => {
  const [books, setBooks] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [loading, setLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    fetchBooks();
    fetchCategories();
  }, []);

  const fetchBooks = async () => {
    try {
      setLoading(true);
      const data = await getAllBooks();
      console.log("Books data:", data); // Debug log
      setBooks(data || []);
    } catch (error) {
      console.error("Error fetching books:", error);
      alert("Lỗi khi tải danh sách sách");
      setBooks([]);
    } finally {
      setLoading(false);
    }
  };

  const fetchCategories = async () => {
    try {
      const data = await getAllCategories();
      console.log("Categories data:", data); // Debug log
      setCategories(data || []);
    } catch (error) {
      console.error("Error fetching categories:", error);
      setCategories([]);
    }
  };

  const handleCategoryFilter = async (categoryId) => {
    try {
      setLoading(true);
      setSelectedCategory(categoryId);
      if (categoryId === "") {
        await fetchBooks();
      } else {
        const data = await getBooksByCategory(categoryId);
        setBooks(data || []);
      }
    } catch (error) {
      console.error("Error filtering books:", error);
      alert("Lỗi khi lọc sách theo danh mục");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Bạn có chắc chắn muốn xóa sách này?")) {
      try {
        setLoading(true);
        await deleteBook(id);
        alert("Xóa sách thành công");
        // Refresh the list
        if (selectedCategory) {
          handleCategoryFilter(selectedCategory);
        } else {
          fetchBooks();
        }
      } catch (error) {
        console.error("Error deleting book:", error);
        alert("Lỗi khi xóa sách");
      } finally {
        setLoading(false);
      }
    }
  };

  const formatDate = (dateString) => {
    if (!dateString) return "Không có thông tin";
    try {
      const date = new Date(dateString);
      return date.toLocaleDateString("vi-VN");
    } catch (error) {
      return "Ngày không hợp lệ";
    }
  };

  const filteredBooks = books.filter(
    (book) =>
      book.title?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      book.bookCode?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      book.categoryName?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="book-list">
      <h2>Danh sách sách</h2>

      <div className="filters">
        <div className="search-box">
          <input
            type="text"
            placeholder="Tìm kiếm theo mã sách, tên sách hoặc danh mục..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>

        <div className="category-filter">
          <select
            value={selectedCategory}
            onChange={(e) => handleCategoryFilter(e.target.value)}
            disabled={loading}
          >
            <option value="">Tất cả danh mục</option>
            {categories.map((category) => (
              <option key={category.id} value={category.id}>
                {category.categoryName}
              </option>
            ))}
          </select>
        </div>
      </div>

      {loading && <p className="loading">Đang tải...</p>}

      {filteredBooks.length === 0 && !loading ? (
        <p className="no-books">Không có sách nào</p>
      ) : (
        <div className="books-table">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Mã sách</th>
                <th>Tên sách</th>
                <th>Danh mục</th>
                <th>Ngày nhập</th>
                <th>Số lượng</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              {filteredBooks.map((book) => (
                <tr key={book.id}>
                  <td>{book.id}</td>
                  <td>{book.bookCode || "Không có mã"}</td>
                  <td>{book.title || "Không có tiêu đề"}</td>
                  <td>{book.categoryName || "Không xác định"}</td>
                  <td>{formatDate(book.importDate)}</td>
                  <td>{book.quantity || 0}</td>
                  <td>
                    <button
                      className="edit-btn"
                      onClick={() => onEdit && onEdit(book)}
                      disabled={loading}
                    >
                      Sửa
                    </button>
                    <button
                      className="delete-btn"
                      onClick={() => handleDelete(book.id)}
                      disabled={loading}
                    >
                      Xóa
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default BookList;
