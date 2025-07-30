function ProductSearch({ search, setSearch }) {
  return (
    <div className="mb-3">
      <input
        type="text"
        className="form-control"
        placeholder="Tìm kiếm theo tên hoặc mô tả sản phẩm ..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
    </div>
  );
}

export default ProductSearch;
