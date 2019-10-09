import React, { Component } from "react";

import FacebookIcon from "../../img/facebook-icon.png";
import GoogleIcon from "../../img/google-icon.png";

import api from "../../services/api";

import "./login.scss";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = { email: "", password: "" };
  }

  onEmailChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ email: value }));
  };

  onPassChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ password: value }));
  };

  onLoginSubmit = async e => {
    const response = await api.post("/login", {
      email: this.state.email,
      password: this.state.password
    });

    localStorage.setItem("tokenUser", response.data);

    window.history.back();
  };

  render() {
    return (
      <>
        <h1>Login</h1>

        <div className="login">
          <h2>E-mail:</h2>
          <input
            type="email"
            className="input"
            onChange={this.onEmailChanged}
            placeholder="Qual o seu e-mail?"
          />

          <h2>Senha:</h2>
          <input
            type="password"
            className="input"
            onChange={this.onPassChanged}
            placeholder="Insira uma senha!"
          />

          <button
            type="submit"
            className="btn-login"
            onClick={this.onLoginSubmit}
          >
            Logar
          </button>

          <div className="login-social">
            <img src={FacebookIcon} alt="Facebook" />
            <img src={GoogleIcon} alt="Google" />
          </div>
        </div>

        <a href="/login" className="link-reminder">
          Esqueceu a senha? Relaxa, recupere-a aqui!
        </a>
      </>
    );
  }
}

export default Login;
