import React, { Component } from "react";

import { Box } from "../../components";

import api from "../../services/api";

import "../../styles/components.scss";
import "./dictionary.scss";

class Dictionary extends Component {
  state = {
    searchedWord: "",
    topSearch: {}
  };

  componentDidMount() {
    this.getTopSearch();
    this.getWord();
  }

  getTopSearch = async () => {
    const response = await api.get("api/v1/word/top");

    console.log(response.data);
  };

  getWord = async () => {
    const response = await api.get("api/v1/word/10");

    this.setState({ searchedWord: response.data });

    console.log(response);
  };

  render() {
    return (
      <>
        <h1>Dicionário</h1>
        <div className="econo-search">
          <input
            className="econo-search-input"
            type="text"
            placeholder="Algum termo deu um nó na cabeça?"
          />
          <a className="econo-search-icon" href="#">
            <i className="fas fa-search"></i>
          </a>
        </div>
        <div className="econo-box-container">
          <Box title="Mais pesquisadas" />
          <Box title="Definição" content={this.state.searchedWord} />
        </div>
      </>
    );
  }
}

export default Dictionary;
