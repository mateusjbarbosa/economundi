import React, { Component } from "react";

import api from "../../services/api";

class Dicionario extends Component {
  state = {
    searchedWord: {}
  };

  componentDidMount() {
    this.getWord();
  }

  getWord = async () => {
    const response = await api.get("api/v1/palavra/10");

    this.setState({ searchedWord: response.data });
  };

  render() {
    return (
      <div>
        <h1>Dicionário</h1>
        <div>
          <p>{this.state.searchedWord.description}</p>
        </div>
      </div>
    );
  }
}

export default Dicionario;
