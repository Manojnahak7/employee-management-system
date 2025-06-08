import React, { useState, useEffect, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../nav/Navbar.css";

export const Navbar = () => {
  const [username, setUsername] = useState(null);
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const navigate = useNavigate();
  const dropdownRef = useRef();

  useEffect(() => {
    const name = localStorage.getItem("username");
    setUsername(name);
  }, []);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setDropdownOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    setUsername(null);
    navigate("/login");
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-2">
        <Link className="navbar-brand" to="/">
          <span className="fw-bold fs-4">Employee Management</span>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse p-2" id="navbarNav">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0"></ul>
          <div className="btns">
            {username ? (
              <div
                className="user-dropdown"
                ref={dropdownRef}
                style={{ position: "relative" }}
              >
                <button
                  className="btn btn-secondary dropdown-toggle"
                  onClick={() => setDropdownOpen(!dropdownOpen)}
                >
                  {username}
                </button>
                {dropdownOpen && (
                  <ul
                    className="dropdown-menu show"
                    style={{
                      position: "absolute",
                      top: "100%",
                      right: 0,
                      backgroundColor: "#fff",
                      boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                      borderRadius: "4px",
                      minWidth: "150px",
                      zIndex: 1000,
                    }}
                  >
                    <li>
                      <Link
                        className="dropdown-item"
                        to="/profile"
                        onClick={() => setDropdownOpen(false)}
                      >
                        Profile
                      </Link>
                    </li>
                    <li>
                      <button className="dropdown-item" onClick={handleLogout}>
                        Logout
                      </button>
                    </li>
                  </ul>
                )}
              </div>
            ) : (
              <div className="btn-container d-flex">
                <Link className="btn btn-warning mx-2" to="/login">
                  Login
                </Link>
                <Link className="btn btn-primary" to="/register">
                  Register
                </Link>
              </div>
            )}
          </div>
        </div>
      </nav>
    </>
  );
};
