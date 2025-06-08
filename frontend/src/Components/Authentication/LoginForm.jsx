import React, { useState } from "react";
import axios from "axios";
import { jwtDecode } from "jwt-decode";
import { Link } from "react-router-dom";
export const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .post("http://localhost:9090/api/auth/login", { email, password })
      .then((res) => {
        localStorage.setItem("token", res.data.token);

        const decoded = jwtDecode(res.data.token);
        localStorage.setItem("username", decoded.name || decoded.sub || email);

        window.location.href = "/";
      })
      .catch(() => {
        alert("Login failed. Please check your email and password.");
      });
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="row justify-content-center">
        <div className="col-md-6 col-lg-4">
          <div className="card shadow rounded-4">
            <div className="card-body p-4">
              <h3 className="mb-4 text-center">Login</h3>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="email" className="form-label">
                    Email address
                  </label>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    placeholder="Enter your email"
                    required
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>

                <div className="mb-3">
                  <label htmlFor="password" className="form-label">
                    Password
                  </label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    placeholder="Enter your password"
                    required
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>

                <button type="submit" className="btn btn-primary w-100 mt-3">
                  Login
                </button>
              </form>

              <div className="text-center mt-3">
                <Link to="/forgotpassword" className="text-decoration-none">
                  Forgot password?
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
