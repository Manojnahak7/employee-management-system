import { LoginForm } from "./Components/Authentication/LoginForm";
import { RegistrationForm } from "./Components/Authentication/RegistrationForm";
import EmployeeList from "./Components/EmployeeList/EmployeeList";
import { Navbar } from "./Components/nav/Navbar";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Profile } from "./Components/Profile/Profile";
import { ForgotPassword } from "./Components/PasswordReset/ForgotPassword";
import { ResetPassword } from "./Components/PasswordReset/ResetPassword";
export const App = () => {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<EmployeeList />}></Route>
          <Route path="/login" element={<LoginForm />}></Route>
          <Route path="/register" element={<RegistrationForm />}></Route>
          <Route path="/profile" element={<Profile />} />
          <Route path="/resetpassword" element={<ResetPassword />}></Route>
          <Route path="/forgotpassword" element={<ForgotPassword />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
};
