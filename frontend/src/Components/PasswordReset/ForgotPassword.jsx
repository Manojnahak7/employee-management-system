import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:9090/api/auth/forgot-password", { email })
      .then((res) => {
        setMessage(res.data.message);
        const url = new URL(res.data.resetLink);
        const token = url.searchParams.get("token");
        navigate(`/resetpassword?token=${token}`);
      })
      .catch(() => {
        setMessage("Error generating reset link. Please try again.");
      });
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="row justify-content-center">
        <div className="col-md-6 col-lg-4">
          <div className="card shadow rounded-4">
            <div className="card-body p-4">
              <h3 className="mb-4 text-center">Forgot Password</h3>
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
                <button type="submit" className="btn btn-primary w-100">
                  Send Reset Link
                </button>
              </form>
              {message && <p className="mt-3 text-center">{message}</p>}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
