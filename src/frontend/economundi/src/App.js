import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";

import {
  Destaque,
  Dicionario,
  Indices,
  Noticias,
  Perfil,
  Simulacoes,
  Sobre
} from "./containers";

import { MenuBar } from "./components";

import "./app.scss";

class App extends Component {
  render() {
    return (
      <Router>
        <div className="container">
          <MenuBar />

          <section className="main">
            <Route exact path="/" component={Destaque} />
            <Route path="/noticias" component={Noticias} />
            <Route path="/indices" component={Indices} />
            <Route path="/simulacoes" component={Simulacoes} />
            <Route path="/dicionario" component={Dicionario} />
            <Route path="/sobre" component={Sobre} />
            <Route path="/perfil" component={Perfil} />
          </section>
        </div>
      </Router>
    );
  }
}

export default App;
