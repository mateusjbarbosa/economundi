import React, { Component } from "react";

import FacebookIcon from "../../img/facebook-icon.png";
import GoogleIcon from "../../img/google-icon.png";

import api from "../../services/api";

import "./login.scss";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",

      error_login: false,

      loading: false,

      statusLogin: true
    };
  }

  onEmailChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ email: value }));
  };

  onPassChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ password: value }));
  };

  validate = async () => {
    const { email, password } = this.state;

    let error = false;

    this.setState({ error_login: false });

    if (email === "" || password === "") {
      error = true;

      this.setState({ error_login: true });
    }

    return error;
  };

  onLoginSubmit = async e => {
    this.setState({ loading: true }, async () => {
      const errors = await this.validate();

      if (!errors) {
        const response = await api.post("/login", {
          email: this.state.email,
          password: this.state.password
        });

        if (response.status === 200) {
          localStorage.setItem("tokenUser", response.data);

          this.setState({ loading: false });

          window.history.back();
        } else {
          this.setState({ statusLogin: false, loading: false });
        }
      } else {
        this.setState({ loading: false });
      }
    });
  };

  render() {
    const { error_login, statusLogin, loading } = this.state;

    return (
      <>
        {loading ? (
          <h1>Enviando sua requisição...</h1>
        ) : (
          <>
            <div className="login-title">
              <h1>Login</h1>
            </div>

            {error_login ? (
              <div className="login-error">
                <h2>E-mail:</h2>
                <input
                  type="email"
                  onChange={this.onEmailChanged}
                  placeholder="Qual o seu e-mail?"
                />

                <h2>Senha:</h2>
                <input
                  type="password"
                  onChange={this.onPassChanged}
                  placeholder="Insira uma senha!"
                />

                <button type="submit" onClick={this.onLoginSubmit}>
                  Logar
                </button>

                <div className="login-social">
                  <img src={FacebookIcon} alt="Facebook" />
                  <img src={GoogleIcon} alt="Google" />
                </div>
              </div>
            ) : (
              <div className="login">
                <h2>E-mail:</h2>
                <input
                  type="email"
                  onChange={this.onEmailChanged}
                  placeholder="Qual o seu e-mail?"
                />

                <h2>Senha:</h2>
                <input
                  type="password"
                  onChange={this.onPassChanged}
                  placeholder="Insira uma senha!"
                />

                <button type="submit" onClick={this.onLoginSubmit}>
                  Logar
                </button>

                <div className="login-social">
                  <img src={FacebookIcon} alt="Facebook" />
                  <img src={GoogleIcon} alt="Google" />
                </div>
              </div>
            )}

            {!statusLogin ? (
              <div className="pop-up-red">
                <h2>Login não encontrado, tente novamente!</h2>
              </div>
            ) : (
              <div></div>
            )}

            <a href="/login" className="link-reminder">
              Esqueceu a senha? Relaxa, recupere-a aqui!
            </a>
          </>
        )}
      </>
    );
  }
}

export default Login;
