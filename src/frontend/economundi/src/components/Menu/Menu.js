import React from "react";
import { Link } from "react-router-dom";

import LogoIcon from "../../img/logo-icon.png";
import NewsIcon from "../../img/news-icon.png";
import IndicatorsIcon from "../../img/indicators-icon.png";
import SimulationIcon from "../../img/simulation-icon.png";
import DictionaryIcon from "../../img/dictionary-icon.png";
import AboutIcon from "../../img/about-icon.png";
import AvatarIcon from "../../img/avatar-default.png";

import "./menu.scss";

const Menu = () => {
  return (
    <aside className="menu">
      <Link to="/">
        <img src={LogoIcon} alt="Portal EconoMundi" />
      </Link>
      <Link to="/noticias">
        <img src={NewsIcon} alt="Notícias" />
      </Link>
      <Link to="/indices">
        <img src={IndicatorsIcon} alt="Indíces econômicos" />
      </Link>
      <Link to="/simulacoes">
        <img src={SimulationIcon} alt="Simulações" />
      </Link>
      <Link to="/dicionario">
        <img src={DictionaryIcon} alt="Dicionário" />
      </Link>
      <Link to="/sobre">
        <img src={AboutIcon} alt="Sobre Nós" />
      </Link>
      <Link to="/perfil">
        <img src={AvatarIcon} alt="Portal EconoMundi" />
      </Link>
    </aside>
  );
};

export default Menu;
