import React, { useEffect, useState } from "react";
import axios from "axios";

export const Profile = () => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    mobile: "",
    permanentAddress: "",
    correspondenceAddress: "",
    officeName: "",
    officeAddress: "",
  });

  const token = localStorage.getItem("token");

  useEffect(() => {
    axios
      .get("http://localhost:9090/api/employees/me", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((res) => {
        const data = res.data;
        setForm({
          name: data.name || "",
          email: data.user?.email || "",
          mobile: data.mobile || "",
          permanentAddress: data.address?.permanentAddress || "",
          correspondenceAddress: data.address?.correspondenceAddress || "",
          officeName: data.office?.officeName || "",
          officeAddress: data.office?.officeAddress || "",
        });
      })
      .catch((err) => {
        console.error("Error fetching profile:", err);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const updatedFields = {};
    Object.keys(form).forEach((key) => {
      if (form[key] !== "" && form[key] !== null && form[key] !== undefined) {
        updatedFields[key] = form[key];
      }
    });

    axios
      .put("http://localhost:9090/api/employees/me", updatedFields, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        alert("Profile updated successfully!");
      })
      .catch((err) => {
        console.error("Update failed:", err);
        alert("Failed to update profile.");
      });
  };

  return (
    <div className="container mt-4">
      <h2>My Profile</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group mb-3">
          <label>Name</label>
          <input
            name="name"
            value={form.name}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="form-group mb-3">
          <label>Email</label>
          <input
            name="email"
            value={form.email}
            className="form-control"
            readOnly
          />
        </div>
        <div className="form-group mb-3">
          <label>Mobile</label>
          <input
            name="mobile"
            value={form.mobile}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="form-group mb-3">
          <label>Permanent Address</label>
          <input
            name="permanentAddress"
            value={form.permanentAddress}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="form-group mb-3">
          <label>Correspondence Address</label>
          <input
            name="correspondenceAddress"
            value={form.correspondenceAddress}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="form-group mb-3">
          <label>Office Name</label>
          <input
            name="officeName"
            value={form.officeName}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <div className="form-group mb-3">
          <label>Office Address</label>
          <input
            name="officeAddress"
            value={form.officeAddress}
            onChange={handleChange}
            className="form-control"
          />
        </div>
        <button className="btn btn-success mt-2">Save</button>
      </form>
    </div>
  );
};
