import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditWorker() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [workerModel, setWorkerModel] = useState({
    passportId: "",
    name: "",
    surname: "",
    workType: "",
    salary: 0.0,
  });

  const { passportId, name, surname, workType, salary } = workerModel;

  const onInputChange = (e) => {
    setWorkerModel({ ...workerModel, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/worker/update", workerModel);
    navigate("/");
  };

  useEffect(() => {
    loadWorker();
  }, []);

  const loadWorker = async () => {
    const result = await axios.get(`http://localhost:8080/worker/byId/${id}`);
    setWorkerModel(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Worker</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="PassportId" className="form-label">
                PassportId
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your passportID"
                name="passportId"
                value={passportId}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Name
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your name"
                name="name"
                value={name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Surname" className="form-label">
                Surname
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your surname"
                name="surname"
                value={surname}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="WorkType" className="form-label">
                WorkType
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your workType"
                name="workType"
                value={workType}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Salary" className="form-label">
                Salary
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your salary"
                name="salary"
                value={salary}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to={"/"}>
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
