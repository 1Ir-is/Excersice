import { useSelector, useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { logoutActionAsync } from "../../redux/action/action";
import { toast } from "react-toastify";

function Header() {
  const user = useSelector((state) => state.login.user);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = async () => {
    await dispatch(logoutActionAsync());
    navigate("/");
    toast.success("Đăng xuất thành công!");
  };

  return (
    <nav className="navbar navbar-dark bg-dark">
      <div className="container d-flex justify-content-between align-items-center">
        <span className="navbar-brand">Quản lý học sinh</span>
        <div>
          {!user ? (
            <>
              <Link to="/register" className="btn btn-outline-light me-2">
                Đăng ký
              </Link>
              <Link to="/login" className="btn btn-outline-light">
                Đăng nhập
              </Link>
            </>
          ) : (
            <>
              <span className="text-light me-3">Xin chào, {user.username}</span>
              <button onClick={handleLogout} className="btn btn-danger">
                Đăng xuất
              </button>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Header;
