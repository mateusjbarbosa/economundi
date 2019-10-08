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
      dateBirth: "",
      email: "",
      firstName: "",
      lastName: "",
      password: "",
      confirmEmail: "",
      confirmPassword: "",

      errors: [],
      render: false,

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

    let errors = [];

    if (firstName === "") {
      errors.push("Nome");
    }

    if (lastName === "") {
      errors.push("Sobrenome");
    }

    if (dateBirth === "") {
      errors.push("Nascimento");
    }

    if (email === "") {
      errors.push("E-mail");
    }

    if (!confirmEmail) {
      errors.push("Confirmar e-mail");
    } else {
      if (confirmEmail !== email) {
        errors.push("Confirmar e-mail");
      }
    }

    if (password === "") {
      errors.push("Senha");
    }

    if (!confirmPassword) {
      errors.push("Confirmar senha");
    } else {
      if (confirmPassword !== password) {
        errors.push("Confirmar senha");
      }
    }

    return errors;
  };

  onRegisterSubmit = async e => {
    const { dateBirth, email, firstName, lastName, password } = this.state;

    const errors = await this.validate();

    if (errors.length > 0) {
      this.setState({ render: true, errors: errors });
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
        this.setState({ statusRegister: register.CREATED });
        window.scrollTo(0, 0);
      } else {
        this.setState({ statusRegister: register.ERROR });
      }
    }
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
    const { render, errors } = this.state;

    return (
      <>
        <div className="register-title">
          <h1>Cadastrar</h1>
        </div>

        {this.renderStatusRegister()}

        {render ? (
          <div className="register-validation">
            <p>Alguns campos estão errados:</p>
            <ul>
              {errors.map(error => (
                <li key={error}>{error}</li>
              ))}
            </ul>
          </div>
        ) : (
          <div></div>
        )}
        <div className="data">
          <h2>Nome:</h2>
          <input
            type="text"
            onChange={this.onFirstNameChanged}
            placeholder="Qual seu nome?"
          />
          <h2>Sobrenome:</h2>
          <input
            type="text"
            onChange={this.onLastNameChanged}
            placeholder="Qual o seu sobrenome?"
          />
          <h2>Nascimento:</h2>
          <input type="date" onChange={this.onBirthChanged} />
          <h2>E-mail:</h2>
          <input
            type="email"
            onChange={this.onEmailChanged}
            placeholder="Qual o seu e-mail?"
          />
          <h2>Confirmar e-mail:</h2>
          <input
            type="email"
            onChange={this.onConfirmEmailChanged}
            placeholder="Confirme o seu e-mail!"
          />
          <h2>Senha:</h2>
          <input
            type="password"
            onChange={this.onPassChanged}
            placeholder="Insira uma senha!"
          />
          <h2>Confirmar senha:</h2>
          <input
            type="password"
            onChange={this.onConfirmPassChanged}
            placeholder="Repita, por favor!"
          />

          <div className="register-social">
            <p>Ou então cadastre-se pelo:</p>
            <img src={FacebookIcon} alt="Facebook" />
            <img src={GoogleIcon} alt="Google" />
          </div>

          <button className="btn-login" onClick={this.onRegisterSubmit}>
            Cadastrar
          </button>
        </div>
      </>
    );
  }
}

export default Register;
