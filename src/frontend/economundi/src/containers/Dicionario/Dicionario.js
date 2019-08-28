import React, { Component } from "react";

import { Box } from "../../components";

import api from "../../services/api";

import "./dicionario.scss";

class Dicionario extends Component {
  state = {
    searchedWord: "",
    topSearch: {}
  };

  componentDidMount() {
    this.getTopSearch();
    this.getWord();
  }

  getTopSearch = async () => {
    const response = await api.get("api/v1/palavra/top");

    console.log(response.data);
  };

  getWord = async () => {
    const response = await api.get("api/v1/palavra/10");

    this.setState({ searchedWord: response.data });
    console.log(response);
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
          <Box title="Mais pesquisadas" content={this.state.topSearch.word} />
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
