import React, { useState } from "react";
import axios from "axios";
import "../Authentication/Auth.css";
import { useNavigate } from "react-router-dom";
export const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    mobile: "",
    permanentAddress: "",
    correspondenceAddress: "",
    officeName: "",
    officeAddress: "",
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:9090/api/auth/register",
        formData
      );
      alert("User registered successfully!");
      navigate("/");

      setFormData({
        name: "",
        email: "",
        password: "",
        mobile: "",
        permanentAddress: "",
        correspondenceAddress: "",
        officeName: "",
        officeAddress: "",
      });
    } catch (error) {
      console.error(error);
      alert("Registration failed, try again.");
    }
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="row justify-content-center">
        <div className="col-md-8 col-lg-6">
          <div className="card shadow-lg rounded-4">
            <div className="card-body p-4">
              <h2 className="mb-4 text-center">Registration Form</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="name" className="form-label">
                    Full Name
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="name"
                    placeholder="Enter your name"
                    required
                    value={formData.name}
                    onChange={handleChange}
                  />
                  <div className="form-text text-muted">
                    Please enter your full name.
                  </div>
                </div>

                <div className="mb-3">
                  <label htmlFor="email" className="form-label">
                    Email address
                  </label>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    placeholder="name@example.com"
                    required
                    value={formData.email}
                    onChange={handleChange}
                  />
                  <div className="form-text text-muted">
                    We'll never share your email with anyone else.
                  </div>
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
                    value={formData.password}
                    onChange={handleChange}
                  />
                  <div className="form-text text-muted">Password required.</div>
                </div>

                <div className="mb-3">
                  <label htmlFor="mobile" className="form-label">
                    Mobile Number
                  </label>
                  <input
                    type="tel"
                    className="form-control"
                    id="mobile"
                    placeholder="Enter mobile number"
                    pattern="[0-9]{10}"
                    required
                    value={formData.mobile}
                    onChange={handleChange}
                  />
                  <div className="form-text text-muted">
                    Enter a valid 10-digit number.
                  </div>
                </div>

                <div className="mb-3">
                  <label htmlFor="permanentAddress" className="form-label">
                    Permanent Address
                  </label>
                  <textarea
                    className="form-control"
                    id="permanentAddress"
                    rows="2"
                    placeholder="Enter permanent address"
                    required
                    value={formData.permanentAddress}
                    onChange={handleChange}
                  ></textarea>
                </div>

                <div className="mb-3">
                  <label htmlFor="correspondenceAddress" className="form-label">
                    Correspondence Address
                  </label>
                  <textarea
                    className="form-control"
                    id="correspondenceAddress"
                    rows="2"
                    placeholder="Enter correspondence address"
                    required
                    value={formData.correspondenceAddress}
                    onChange={handleChange}
                  ></textarea>
                </div>

                <div className="mb-3">
                  <label htmlFor="officeName" className="form-label">
                    Office Name
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="officeName"
                    placeholder="Enter office name"
                    required
                    value={formData.officeName}
                    onChange={handleChange}
                  />
                </div>

                <div className="mb-3">
                  <label htmlFor="officeAddress" className="form-label">
                    Office Address
                  </label>
                  <textarea
                    className="form-control"
                    id="officeAddress"
                    rows="2"
                    placeholder="Enter office address"
                    required
                    value={formData.officeAddress}
                    onChange={handleChange}
                  ></textarea>
                </div>

                <button type="submit" className="btn btn-primary w-100 mt-3">
                  Register
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
