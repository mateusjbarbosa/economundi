import React from "react";
import { Link } from "react-router-dom";

import { ReactComponent as IconeLogo } from "../../img/IconeLogo.svg";
import { ReactComponent as IconeNoticia } from "../../img/IconeNoticia.svg";
import { ReactComponent as IconeIndices } from "../../img/IconeIndice.svg";
import { ReactComponent as IconeSimulacoes } from "../../img/IconeSimulacao.svg";
import { ReactComponent as IconeDicionario } from "../../img/IconeDicionario.svg";
import { ReactComponent as IconeSobre } from "../../img/IconeSobre.svg";
import { ReactComponent as IconePerfil } from "../../img/IconePerfil.svg";

import "./menu.scss";

function Menu() {
  return (
    <nav class="col-1 econo-menu">
      <ul class="econo-menu-list">
        <li class="econo-menu-item">
          <Link to="/">
            <IconeLogo width="60" height="60" />
          </Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">
            <IconeNoticia width="60" height="60" />
          </Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">
            <IconeIndices width="60" height="60" />
          </Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">
            <IconeSimulacoes width="60" height="60" />
          </Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/dicionario">
            <IconeDicionario width="60" height="60" />
          </Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">
            <IconeSobre width="60" height="60" />
          </Link>
        </li>
        <li class="econo-menu-item">
          <Link to="/">
            <IconePerfil width="60" height="60" />
          </Link>
        </li>
      </ul>
    </nav>
  );
}

export default Menu;
