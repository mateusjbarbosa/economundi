import React from "react";
import { Link } from "react-router-dom";

import "./menu.scss";

function Menu() {
  return (
    <nav class="col-1 econo-menu">
      <ul class="econo-menu-list">
        <li class="econo-menu-item">
          <Link to="/">PE</Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">N</Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">I</Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">S</Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/dicionario">D</Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">SN</Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">P</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Menu;
