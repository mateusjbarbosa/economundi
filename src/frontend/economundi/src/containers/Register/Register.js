import React, { Component } from "react";

import FacebookIcon from "../../img/facebook-icon.png";
import GoogleIcon from "../../img/google-icon.png";

import "./register.scss";

class Register extends Component {
  render() {
    return (
      <>
        <h1>Cadastrar</h1>
        <div className="data">
          <h2>Nome:</h2>
          <input type="text" />
          <h2>Sobrenome:</h2>
          <input type="text" />
          <h2>Nascimento:</h2>
          <input type="date" />
          <h2>E-mail:</h2>
          <input type="email" />
          <h2>Confirmar e-mail:</h2>
          <input type="email" />
          <h2>Senha:</h2>
          <input type="password" />
          <h2>Confirmar senha:</h2>
          <input type="password" />

          <div className="social">
            <p>Ou então cadastre-se pelo:</p>
            <img src={FacebookIcon} alt="Facebook" />
            <img src={GoogleIcon} alt="Google" />
          </div>

          <button className="btn-login">Cadastrar</button>
        </div>

        <a href="/cadastrar">Esqueceu a senha? Relaxa, recupere-a aqui!</a>
      </>
    );
  }
}

export default Register;
