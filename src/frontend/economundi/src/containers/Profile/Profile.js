import React, { Component } from "react";
import { Link } from "react-router-dom";

import "./profile.scss";

class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      token: ""
    };
  }

  render() {
    return (
      <>
        <div className="profile-title">
          <h1>Olá!</h1>
        </div>

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
    );
  }
}

export default Profile;
