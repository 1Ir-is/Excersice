import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import StudentList from "./components/StudentList";
import StudentAdd from "./components/StudentAdd";
import StudentEdit from "./components/StudentEdit";
import { ToastContainer } from "react-toastify";
import StudentDetail from "./components/StudentDetail";

function App() {
  return (
    <Router>
      <Header />
      <ToastContainer />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<StudentList />} />
          <Route path="/add" element={<StudentAdd />} />
          <Route path="/edit/:id" element={<StudentEdit />} />
          <Route path="/detail/:id" element={<StudentDetail />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
