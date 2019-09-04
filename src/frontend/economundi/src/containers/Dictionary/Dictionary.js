import React, { Component } from "react";

import { Box } from "../../components";

import api from "../../services/api";

import "../../styles/components.scss";
import "./dictionary.scss";

class Dictionary extends Component {
  constructor(props) {
    super(props);

    this.state = {
      searchedWord: "",
      topSearch: {}
    };

    this.getWord = this.getWord.bind(this);
    this.handleSearchChange = this.handleSearchChange.bind(this);
  }

  getTopSearch = async () => {
    const response = await api.get("palavra/top");

    console.log(response.data);
  };

  getWord = async word => {
    const response = await api.get(`palavra/${word}`);

    this.setState({ searchedWord: response.data[0].description });
  };

  handleSearchChange = e => {
    let searchingWord = "";

    if (e.target.value !== "") {
      searchingWord = e.target.value;

      if (searchingWord.length > 3) {
        try {
          this.getWord(searchingWord);
        } catch (error) {
          console.log(error);
        }
      }
    }
  };

  render() {
    return (
      <>
        <h1>Dicionário</h1>
        <div className="econo-search">
          <input
            className="econo-search-input"
            type="text"
            onChange={this.handleSearchChange}
            placeholder="Algum termo deu um nó na cabeça?"
          />
          <a className="econo-search-icon" href="/">
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
