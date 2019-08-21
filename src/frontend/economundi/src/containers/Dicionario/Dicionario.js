import React, { Component } from "react";

import api from "../../services/api";

import "./dicionario.scss";

class Dicionario extends Component {
  state = {
    searchedWord: {}
  };
  componentDidMount() {
    this.getWord();
  }

  getWord = async () => {
    const response = await api.get("api/v1/palavra/10");

    console.log(response);
    this.setState({ searchedWord: response.data });
  };

  render() {
    return (
      <div>
        <h1>Dicionario</h1>
        <h2>{this.state.searchedWord.name}</h2>
        <div className="desc-box">
          <p>{this.state.searchedWord.description}</p>
        </div>
      </div>
    );
  }
}

export default Dicionario;
