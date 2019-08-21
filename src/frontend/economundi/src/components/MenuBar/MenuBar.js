import React from "react";
import { Link } from "react-router-dom";

import "./menu-bar.scss";

function MenuBar() {
  return (
    <aside className="nav">
      <div className="nav-item">
        <Link to="/">PE</Link>
      </div>
      <div className="nav-item">
        <Link to="/noticias">N</Link>
      </div>
      <div className="nav-item">
        <Link to="/indices">I</Link>
      </div>
      <div className="nav-item">
        <Link to="/simulacoes">S</Link>
      </div>
      <div className="nav-item">
        <Link to="/dicionario">D</Link>
      </div>
      <div className="nav-item">
        <Link to="/sobre">SN</Link>
      </div>
      <div className="nav-item">
        <Link to="/perfil">P</Link>
      </div>
    </aside>
  );
}

export default MenuBar;
