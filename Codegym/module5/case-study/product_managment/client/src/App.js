import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ListComponent from "./components/ListComponent";
import AddComponent from "./components/AddComponent";
import EditComponent from "./components/EditComponent";
import { ToastContainer } from "react-toastify";
import DetailComponent from "./components/DetailComponent";

function App() {
  return (
    <Router>
      <ToastContainer />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<ListComponent />} />
          <Route path="/add" element={<AddComponent />} />
          <Route path="/edit/:id" element={<EditComponent />} />
          <Route path="/detail/:id" element={<DetailComponent />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
