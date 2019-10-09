import React from "react";
import { BrowserRouter as Router } from "react-router-dom";

import Routes from "../Routes";

import { Menu } from "../../components";

import "../../styles/tags.scss";

import "./app.scss";

function App() {
  return (
    <Router>
      <div className="app">
        <Menu />

        <div className="content">
          <Routes />
        </div>
      </div>
    </Router>
  );
}

export default App;
