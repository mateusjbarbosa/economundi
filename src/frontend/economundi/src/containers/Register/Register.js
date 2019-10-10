import React, { Component } from "react";

import FacebookIcon from "../../img/facebook-icon.png";
import GoogleIcon from "../../img/google-icon.png";

import api from "../../services/api";

import "./register.scss";

const register = {
  WAIT: 0,
  CREATED: 1,
  ERROR: 2
};

class Register extends Component {
  constructor(props) {
    super(props);

    this.state = {
      confirmEmail: "",
      confirmPassword: "",
      dateBirth: "",
      email: "",
      firstName: "",
      lastName: "",
      password: "",

      error_credentials: false,
      error_dataUser: false,

      loading: false,

      statusRegister: register.WAIT
    };
  }

  onFirstNameChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ firstName: value }));
  };

  onLastNameChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ lastName: value }));
  };

  onBirthChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ dateBirth: value }));
  };

  onEmailChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ email: value }));
  };

  onConfirmEmailChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ confirmEmail: value }));
  };

  onPassChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ password: value }));
  };

  onConfirmPassChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ confirmPassword: value }));
  };

  validate = async () => {
    const {
      dateBirth,
      email,
      firstName,
      lastName,
      password,
      confirmEmail,
      confirmPassword
    } = this.state;

    let error = false;

    this.setState({ error_credentials: false, error_dataUser: false });

    if (firstName === "" || lastName === "" || dateBirth === "") {
      error = true;

      this.setState({ error_dataUser: true });
    }

    if (
      email === "" ||
      password === "" ||
      confirmEmail === "" ||
      confirmPassword === ""
    ) {
      error = true;

      this.setState({ error_credentials: true });
    } else {
      if (email !== confirmEmail || password !== confirmPassword) {
        error = true;

        this.setState({ error_credentials: true });
      }
    }

    return error;
  };

  onRegisterSubmit = e => {
    const { dateBirth, email, firstName, lastName, password } = this.state;

    this.setState({ loading: true }, async () => {
      const errors = await this.validate();

      if (errors) {
        this.setState({ render: true, errors: errors, loading: false });
      } else {
        const response = await api.post("/api/v1/public/create", {
          date_birth: dateBirth,
          date_hour_register: "",
          economic_profile: "NONE",
          email: email,
          first_name: firstName,
          last_name: lastName,
          password: password,
          permission: "USER"
        });

        if (response.status === 201) {
          this.setState({ statusRegister: register.CREATED, loading: false });

          window.scrollTo(0, window.innerHeight);
        } else {
          this.setState({ statusRegister: register.ERROR });
        }
      }
    });
  };

  renderStatusRegister = () => {
    const { statusRegister } = this.state;
    switch (statusRegister) {
      case register.WAIT:
        return <div></div>;

      case register.CREATED:
        return (
          <div className="pop-up-green">
            <h2>
              Usuário criado com sucesso, ative sua conta através do e-mail que
              enviamos!
            </h2>
          </div>
        );

      case register.ERROR:
        return (
          <div className="pop-up-red">
            <h2>
              Desculpe-nos, algo de errado aconteceu! Verifique os campos e
              tente novamente, por favor!
            </h2>
          </div>
        );

      default:
        return <div></div>;
    }
  };

  render() {
    const { error_credentials, error_dataUser, loading } = this.state;

    return (
      <>
        {loading ? (
          <h1>Enviando sua requisição...</h1>
        ) : (
          <>
            <div className="register-title">
              <h1>Cadastrar</h1>
            </div>

            <div className="data">
              <h2>Seus dados</h2>
              {error_dataUser ? (
                <div className="data-user-error">
                  <input
                    type="text"
                    onChange={this.onFirstNameChanged}
                    placeholder="Qual seu nome?"
                  />
                  <input
                    type="text"
                    onChange={this.onLastNameChanged}
                    placeholder="Qual o seu sobrenome?"
                  />
                  <input type="date" onChange={this.onBirthChanged} />
                </div>
              ) : (
                <div className="data-user">
                  <input
                    type="text"
                    onChange={this.onFirstNameChanged}
                    placeholder="Qual seu nome?"
                  />
                  <input
                    type="text"
                    onChange={this.onLastNameChanged}
                    placeholder="Qual o seu sobrenome?"
                  />
                  <input type="date" onChange={this.onBirthChanged} />
                </div>
              )}

              <h2>E-mail</h2>
              {error_credentials ? (
                <div className="data-credentials-error">
                  <input
                    type="email"
                    onChange={this.onEmailChanged}
                    placeholder="Qual o seu e-mail?"
                  />
                  <input
                    type="email"
                    onChange={this.onConfirmEmailChanged}
                    placeholder="Confirme o seu e-mail!"
                  />
                  <h2>Senha</h2>
                  <input
                    type="password"
                    onChange={this.onPassChanged}
                    placeholder="Insira uma senha!"
                  />
                  <input
                    type="password"
                    onChange={this.onConfirmPassChanged}
                    placeholder="Repita, por favor!"
                  />
                </div>
              ) : (
                <div className="data-credentials">
                  <input
                    type="email"
                    onChange={this.onEmailChanged}
                    placeholder="Qual o seu e-mail?"
                  />
                  <input
                    type="email"
                    onChange={this.onConfirmEmailChanged}
                    placeholder="Confirme o seu e-mail!"
                  />
                  <h2>Senha</h2>
                  <input
                    type="password"
                    onChange={this.onPassChanged}
                    placeholder="Insira uma senha!"
                  />
                  <input
                    type="password"
                    onChange={this.onConfirmPassChanged}
                    placeholder="Repita, por favor!"
                  />
                </div>
              )}

              <div className="register-social">
                <p>Ou então cadastre-se pelo:</p>
                <img src={FacebookIcon} alt="Facebook" />
                <img src={GoogleIcon} alt="Google" />
              </div>

              <button className="btn-login" onClick={this.onRegisterSubmit}>
                Cadastrar
              </button>
            </div>

            {this.renderStatusRegister()}
          </>
        )}
      </>
    );
  }
}

export default Register;
