import React from "react";
import { BrowserRouter as Router, Link, Route } from "react-router-dom";

import Dicionario from "./containers/Dicionario/Dicionario";

import "./App.scss";

function App() {
  return (
    <Router>
      <div className="App">
        <div className="container-fluid">
          <div className="row">
            <nav className="col-md-1 econo-nav">
              <div className="econo-nav-item">
                <Link to="/">PE</Link>
              </div>
              <div className="econo-nav-item">
                <Link to="/">N</Link>
              </div>
              <div className="econo-nav-item">
                <Link to="/">I</Link>
              </div>
              <div className="econo-nav-item">
                <Link to="/">S</Link>
              </div>
              <div className="econo-nav-item">
                <Link to="/dicionario">D</Link>
              </div>
              <div className="econo-nav-item">
                <Link to="/">SN</Link>
              </div>
              <div className="econo-nav-item">
                <Link to="/">P</Link>
              </div>
            </nav>
            <main className="col-md-11 econo-main">
              <Route path="/dicionario" component={Dicionario} />
            </main>
          </div>
        </div>
      </div>
    </Router>
  );
}

export default App;
