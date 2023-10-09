import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [users, setUsers] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/worker/list");
    setUsers(result.data);
  };

  const deleteWorker = async (id) => {
    await axios.delete(`http://localhost:8080/worker/delete/byId/${id}`);
    loadUsers();
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Id</th>
              <th scope="col">PassportId</th>
              <th scope="col">Name</th>
              <th scope="col">Surname</th>
              <th scope="col">WorkType</th>
              <th scope="col">Salary</th>
              <th scope="col">CreatedDate</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{user.id}</td>
                <td>{user.passportId}</td>
                <td>{user.name}</td>
                <td>{user.surname}</td>
                <td>{user.workType}</td>
                <td>{user.salary}</td>
                <td>{user.createdDate}</td>
                <td>
                  <Link
                    className="btn btn-primary mx-2"
                    to={`/viewworker/${user.id}`}
                  >
                    View
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/editworker/${user.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteWorker(user.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
