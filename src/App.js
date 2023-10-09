import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddWorker from "./pages/AddWorker";
import EditWorker from "./pages/EditWorker";
import ViewWorker from "./pages/ViewWorker";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addworker" element={<AddWorker />} />
          <Route exact path="/editworker/:id" element={<EditWorker />} />
          <Route exact path="/viewworker/:id" element={<ViewWorker />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
