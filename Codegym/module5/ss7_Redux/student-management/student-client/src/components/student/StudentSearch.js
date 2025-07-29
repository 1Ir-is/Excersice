function StudentSearch({ search, setSearch }) {
  return (
    <div className="mb-3">
      <input
        type="text"
        className="form-control"
        placeholder="Tìm kiếm theo tên hoặc email ...."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
    </div>
  );
}

export default StudentSearch;
