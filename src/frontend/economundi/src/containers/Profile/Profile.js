import React, { Component } from "react";
import { Link } from "react-router-dom";

import api from "../../services/api";

import "./profile.scss";

class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      userLogged: false,
      greeting: "Olá!"
    };
  }

  componentDidMount() {
    this.setState({ userLogged: this.existsToken() }, async () => {
      if (this.state.userLogged) {
        const response = await api.get(
          `/user/${localStorage.getItem("@economundi/tokenUser")}`
        );

        this.setState({ greeting: `Olá, ${response.data.firstName}` });
      }
    });
  }

  existsToken = () => {
    const token = localStorage.getItem("tokenUser");
    let status = false;

    if (token !== null) status = true;

    return status;
  };

  render() {
    const { greeting, userLogged } = this.state;
    return (
      <>
        <div className="profile-title">
          <h1>{greeting}</h1>
        </div>

        {userLogged ? (
          <h1>Opa!</h1>
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
