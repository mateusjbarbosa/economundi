import React from "react";
import { Link } from "react-router-dom";

import "./menu-bar.scss";

function MenuBar() {
  return (
    <section class="menu-container">
      <div class="menu-item">
        <Link to="/">PE</Link>
      </div>
      <div class="menu-item">N</div>
      <div class="menu-item">I</div>
      <div class="menu-item">S</div>
      <div class="menu-item">
        <Link to="/dicionario">D</Link>
      </div>
      <div class="menu-item">SN</div>
      <div class="menu-item">P</div>
    </section>
  );
}

export default MenuBar;
