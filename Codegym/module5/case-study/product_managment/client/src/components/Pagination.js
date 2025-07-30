function Pagination({ currentPage, totalPages, onPageChange }) {
  return (
    <nav>
      <ul className="pagination">
        <li className={`page-item${currentPage === 1 ? " disabled" : ""}`}>
          <button
            className="page-link"
            onClick={() => onPageChange(currentPage - 1)}
            disabled={currentPage === 1}
          >
            Trước
          </button>
        </li>
        {[...Array(totalPages)].map((_, idx) => (
          <li
            key={idx}
            className={`page-item${currentPage === idx + 1 ? " active" : ""}`}
          >
            <button className="page-link" onClick={() => onPageChange(idx + 1)}>
              {idx + 1}
            </button>
          </li>
        ))}
        <li
          className={`page-item${
            currentPage === totalPages ? " disabled" : ""
          }`}
        >
          <button
            className="page-link"
            onClick={() => onPageChange(currentPage + 1)}
            disabled={currentPage === totalPages}
          >
            Sau
          </button>
        </li>
      </ul>
    </nav>
  );
}

export default Pagination;
