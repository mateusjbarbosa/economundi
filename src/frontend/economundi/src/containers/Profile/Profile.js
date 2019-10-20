import React, { Component } from "react";
import { Link } from "react-router-dom";

import api from "../../services/api";

import AvatarIcon from "../../img/avatar-default.png";
import Exit from "../../img/exit.png";

import "./profile.scss";

class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      greeting: "Olá!"
    };
  }

  componentDidMount() {
    this.existsToken();
  }

  existsToken = async () => {
    const token = localStorage.getItem("tokenUser");

    let response = "";

    if (token !== null) {
      response = await api.get("api/v1/public/getlogin", {
        headers: {
          Authorization: localStorage.getItem("tokenUser")
        }
      });

      this.setState({
        greeting: `Olá, ${response.data.FirstName}!`,
        userLogged: true
      });
    }
  };

  onExit = () => {
    localStorage.removeItem("tokenUser");
    document.location.reload(true);
  };

  render() {
    const { greeting, userLogged } = this.state;
    return (
      <>
        <div className="profile-title">
          <h1>{greeting}</h1>
        </div>

        {userLogged ? (
          <>
            <img
              className="exit-button"
              src={Exit}
              alt="Deseja sair do seu perfil?"
              onClick={this.onExit}
            />
            <div className="profile-photo">
              <img src={AvatarIcon} alt="Sua foto de perfil" />
            </div>

            <div className="profile-cards">
              <Link to="/perfil/dados">
                <div className="profile-box-my-data">
                  <h2>Meus dados</h2>
                </div>
              </Link>

              <Link to="/perfil/perfil-economico">
                <div className="profile-box-my-api">
                  <h2>Meu perfil econômico</h2>
                </div>
              </Link>
            </div>
          </>
        ) : (
          <>
            <Link to="/perfil/login">
              <div className="profile-box-login">
                <h2>Logar</h2>
              </div>
            </Link>

            <Link to="/perfil/cadastrar">
              <div className="profile-box-register">
                <h2>Criar conta</h2>
              </div>
            </Link>
          </>
        )}
      </>
    );
  }
}

export default Profile;
