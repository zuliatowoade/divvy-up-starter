import React, { useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import RegistrationPage from "./RegistrationPage";
import ExpensePage from "./ExpensePage";
import SignInPage from "./SignInPage";
import NotificationComponent from "./NotificationComponent";
import "./App.css";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
  const navigate = useNavigate();

  return (
    <div className="App">
      <Routes>
        <Route
          path="/"
          element={
            <div className="menu-container">
              {isLoggedIn ? (
                <div className="menu">
                  <button
                    className="menu-item"
                    onClick={() => navigate("/expenses")}
                  >
                    Add Expense
                  </button>
                </div>
              ) : (
                <div className="menu">
                  <button
                    className="menu-item"
                    onClick={() => navigate("/register")}
                  >
                    Register
                  </button>
                  <button
                    className="menu-item"
                    onClick={() => navigate("/sign-in")}
                  >
                    Sign In
                  </button>
                </div>
              )}
            </div>
          }
        />
        <Route
          path="/register"
          element={<RegistrationPage onRegister={() => setIsLoggedIn(true)} />}
        />
        <Route
          path="/sign-in"
          element={<SignInPage onSignIn={() => setIsLoggedIn(true)} />}
        />
        <Route path="/expenses" element={<ExpensePage />} />
      </Routes>
      <NotificationComponent />
    </div>
  );
}

export default App;
