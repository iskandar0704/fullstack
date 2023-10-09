import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewWorker() {
  const [worker, setWorker] = useState({
    id: "",
    passportId: "",
    name: "",
    surname: "",
    workType: "",
    salary: 0.0,
    createdDate: Date,
  });

  const { id } = useParams();

  useEffect(() => {
    loadWorker();
  }, []);

  const loadWorker = async () => {
    const result = await axios.get(`http://localhost:8080/worker/byId/${id}`);
    setWorker(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Worker Details</h2>
          <div className="card">
            <div className="card-header">
              Details of worker id:
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Id:</b>
                  {worker.id}
                </li>
                <li className="list-group-item">
                  <b>PassportId:</b>
                  {worker.passportId}
                </li>
                <li className="list-group-item">
                  <b>Name:</b>
                  {worker.name}
                </li>
                <li className="list-group-item">
                  <b>Surname:</b>
                  {worker.surname}
                </li>
                <li className="list-group-item">
                  <b>WorkType:</b>
                  {worker.workType}
                </li>
                <li className="list-group-item">
                  <b>Salary:</b>
                  {worker.salary}
                </li>
                <li className="list-group-item">
                  <b>CreatedDate:</b>
                  {worker.createdDate}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}
