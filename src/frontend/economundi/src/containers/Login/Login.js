import React, { Component } from "react";

import api from "../../services/api";

import FacebookIcon from "../../img/facebook-icon.png";
import GoogleIcon from "../../img/google-icon.png";

import "./login.scss";

class Login extends Component {
  getLogin = async () => {
    const response = await api.get("");
  };

  render() {
    return (
      <>
        <h1>login</h1>
        <div className="login">
          <h2>E-mail:</h2>
          <input type="email" />

          <h2>Senha:</h2>
          <input type="password" />
          <button className="btn-login">Logar</button>

          <div className="social">
            <img src={FacebookIcon} alt="Facebook" />
            <img src={GoogleIcon} alt="Google" />
          </div>
        </div>

        <a href="#">Esqueceu a senha? Relaxa, recupere-a aqui!</a>
      </>
    );
  }
}

export default Login;
