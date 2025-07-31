import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import "./App.css";
import { ToastContainer } from "react-toastify";
import InvoiceList from "./components/InvoiceList";
import InvoiceAdd from "./components/InvoiceAdd";
import InvoiceEdit from "./components/InvoiceEdit";

function App() {
  return (
    <Router>
      <ToastContainer />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<InvoiceList />} />
          <Route path="/add" element={<InvoiceAdd />} />
          <Route path="/edit/:id" element={<InvoiceEdit />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
