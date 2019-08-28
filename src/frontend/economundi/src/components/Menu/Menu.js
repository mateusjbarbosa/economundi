import React from "react";
import { Link } from "react-router-dom";

import "./menu.scss";

function Menu() {
  return (
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
  );
}

export default Menu;
