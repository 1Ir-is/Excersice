function StudentSearch({ search, setSearch }) {
  return (
    <div className="mb-3">
      <input
        type="text"
        className="form-control"
        placeholder="Tim kiem theo ten hoac email ...."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
    </div>
  );
}

export default StudentSearch;
