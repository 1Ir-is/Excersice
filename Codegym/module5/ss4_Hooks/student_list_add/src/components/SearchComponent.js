import React from "react";

function SearchComponent({ searchTerm, setSearchTerm }) {
  return (
    <input
      placeholder="Tìm kiếm theo tên hoặc mã sinh viên"
      value={searchTerm}
      onChange={(e) => setSearchTerm(e.target.value)}
      style={{ marginBottom: "12px", display: "block" }}
    />
  );
}

export default SearchComponent;
