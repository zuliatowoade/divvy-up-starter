import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router } from "react-router-dom";
import App from "./App";
import "./index.css"; // Include global styles if you have any
import { FriendsProvider } from "./FriendsContext";

ReactDOM.render(
  <Router>
    <FriendsProvider>
      <App />
    </FriendsProvider>
  </Router>,
  document.getElementById("root")
);
