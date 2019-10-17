import React, { Component } from "react";

import api from "../../services/api";

import "./profileData.scss";

class ProfileData extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: 0,
      name: "",
      lastName: "",
      birth: "",
      email: "",

      newName: "",
      newLastName: "",
      newBirth: "",
      newEmail: "",

      loading: true
    };
  }

  componentDidMount() {
    this.loadData();
  }

  loadData = async () => {
    const response = await api.get("api/v1/public/getlogin", {
      headers: {
        Authorization: localStorage.getItem("tokenUser")
      }
    });

    if (response.status === 200) {
      this.setState({
        id: response.data.id,
        name: response.data.FirstName,
        lastName: response.data.LastName,
        birth: response.data.birth,
        email: response.data.email
      });
    }

    this.alterBirthFormat();
  };

  onFirstNameChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ newName: value }));
  };

  onLastNameChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ newLastName: value }));
  };

  onBirthChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ newBirth: value }));
  };

  onEmailChanged = async e => {
    const value = e.target.value;

    this.setState(() => ({ newEmail: value }));
  };

  onUpdateSubmit = () => {
    const { newBirth, newEmail, newLastName, newName } = this.state;

    const response = api.put("api/v1/protected/update", {
      date_birth: newBirth,
      email: newEmail,
      first_name: newName,
      last_name: newLastName
    });

    console.log(response);
  };

  alterBirthFormat = () => {
    const { birth } = this.state;

    const birthSplit = birth.split(" ");
    const birthFormat = birthSplit[1].replace(",", "");
    const newBirth = [birthSplit[0], birthFormat, birthSplit[2]];

    let monthNumber = 0;

    switch (newBirth[0]) {
      case "jan":
        monthNumber = 1;
        break;

      case "feb":
        monthNumber = 2;
        break;

      case "mar":
        monthNumber = 3;
        break;

      case "apr":
        monthNumber = 4;
        break;

      case "may":
        monthNumber = 5;
        break;

      case "jun":
        monthNumber = 6;
        break;

      case "jul":
        monthNumber = 7;
        break;

      case "aug":
        monthNumber = 8;
        break;

      case "sep":
        monthNumber = 9;
        break;

      case "oct":
        monthNumber = 10;
        break;

      case "nov":
        monthNumber = 11;
        break;

      case "dec":
        monthNumber = 12;
        break;

      default:
        break;
    }

    let dayNumber = parseInt(newBirth[1]);

    const birthSave =
      newBirth[2] +
      "-" +
      (monthNumber < 10
        ? "0" + monthNumber.toString()
        : monthNumber.toString()) +
      "-" +
      (dayNumber < 10 ? "0" + dayNumber.toString() : dayNumber);

    this.setState({ birth: birthSave, loading: false });
  };

  render() {
    const { name, lastName, birth, email, loading } = this.state;

    return (
      <>
        {loading ? (
          <h1>Carregando seus dados...</h1>
        ) : (
          <>
            <div className="update-title">
              <h1>Olá, {name}!</h1>
            </div>

            <div className="data">
              <h2>Seus dados</h2>
              <div className="data-user">
                <input
                  type="text"
                  onChange={this.onFirstNameChanged}
                  defaultValue={name}
                />
                <input
                  type="text"
                  onChange={this.onLastNameChanged}
                  defaultValue={lastName}
                />
                <input
                  type="date"
                  onChange={this.onBirthChanged}
                  defaultValue={birth}
                />
              </div>

              <h2>E-mail</h2>
              <div className="data-credentials">
                <input
                  type="email"
                  onChange={this.onEmailChanged}
                  defaultValue={email}
                />
              </div>

              <button className="btn-save" onClick={this.onUpdateSubmit}>
                Salvar alterações
              </button>
              <button className="btn-alter-pass" onClick={this.onAlterPassword}>
                Redefinir senha
              </button>
              <button className="btn-delete-user" onClick={this.onDeleteUser}>
                Deletar meu usuário
              </button>
            </div>
          </>
        )}
      </>
    );
  }
}

export default ProfileData;
