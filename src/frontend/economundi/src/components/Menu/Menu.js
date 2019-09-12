import React from "react";
import { Link } from "react-router-dom";

import "./menu.scss";

const Menu = () => {
  return (
    <aside className="menu">
      <Link to="/">PE</Link>
      <Link to="/noticias">N</Link>
      <Link to="/indices">I</Link>
      <Link to="/simulacoes">S</Link>
      <Link to="/dicionario">D</Link>
      <Link to="/sobre">SN</Link>
      <Link to="/perfil">P</Link>
    </aside>
  );
};

export default Menu;
