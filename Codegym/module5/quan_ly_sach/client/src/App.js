import React, { useState } from "react";
import BookList from "./components/BookList";
import BookAdd from "./components/BookAdd";
import CategoryManagement from "./components/CategoryManagement";
import "./App.css";

function App() {
  const [currentView, setCurrentView] = useState("list");
  const [editingBook, setEditingBook] = useState(null);

  const handleEditBook = (book) => {
    setEditingBook(book);
    setCurrentView("add");
  };

  const handleBookSuccess = () => {
    setEditingBook(null);
    setCurrentView("list");
  };

  const handleBookCancel = () => {
    setEditingBook(null);
    setCurrentView("list");
  };

  const renderContent = () => {
    switch (currentView) {
      case "list":
        return <BookList onEdit={handleEditBook} />;
      case "add":
        return (
          <BookAdd
            editingBook={editingBook}
            onSuccess={handleBookSuccess}
            onCancel={handleBookCancel}
          />
        );
      case "categories":
        return <CategoryManagement />;
      default:
        return <BookList onEdit={handleEditBook} />;
    }
  };

  return (
    <div className="App">
      <header className="app-header">
        <h1>Hệ thống quản lý sách</h1>
        <nav className="app-nav">
          <button
            className={currentView === "list" ? "active" : ""}
            onClick={() => {
              setCurrentView("list");
              setEditingBook(null);
            }}
          >
            Danh sách sách
          </button>
          <button
            className={currentView === "add" ? "active" : ""}
            onClick={() => {
              setCurrentView("add");
              setEditingBook(null);
            }}
          >
            Thêm sách mới
          </button>
          <button
            className={currentView === "categories" ? "active" : ""}
            onClick={() => {
              setCurrentView("categories");
              setEditingBook(null);
            }}
          >
            Quản lý danh mục
          </button>
        </nav>
      </header>

      <main className="app-main">{renderContent()}</main>

      <footer className="app-footer">
        <p>&copy; 2025 Hệ thống quản lý sách. Phát triển bởi CodeGym.</p>
      </footer>
    </div>
  );
}

export default App;
