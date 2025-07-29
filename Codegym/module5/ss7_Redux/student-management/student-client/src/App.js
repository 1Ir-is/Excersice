import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Home from "./components/Home";
import StudentList from "./components/student/StudentList";
import StudentAdd from "./components/student/StudentAdd";
import StudentEdit from "./components/student/StudentEdit";
import { ToastContainer } from "react-toastify";
import StudentDetail from "./components/student/StudentDetail";
import Register from "./components/auth/Register";
import Login from "./components/auth/Login";

function App() {
  return (
    <Router>
      <Header />
      <ToastContainer />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/student" element={<StudentList />} />
          <Route path="/add" element={<StudentAdd />} />
          <Route path="/edit/:id" element={<StudentEdit />} />
          <Route path="/detail/:id" element={<StudentDetail />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
