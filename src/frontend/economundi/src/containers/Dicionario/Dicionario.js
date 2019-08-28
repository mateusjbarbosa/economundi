import React, { Component } from "react";

import api from "../../services/api";

import Box from "../../components/Box/Box";

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

    this.setState({ searchedWord: response.data });
  };

  render() {
    return (
      <div className="col-md-12">
        <h1>Dicionário</h1>
        <div className="econo-search-box">
          <input
            className="econo-search-input"
            type="text"
            placeholder="Algum termo deu um nó na cabeça?"
          />
        </div>
        <div className="econo-dicio">
          <Box title="Mais pesquisadas" content={"Lorem Ipsum"} />
          <Box
            title="Definição"
            content={this.state.searchedWord.description}
          />
        </div>
      </div>
    );
  }
}

export default Dicionario;
