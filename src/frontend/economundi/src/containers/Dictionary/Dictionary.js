import React, { Component } from "react";

import { Box } from "../../components";

import api from "../../services/api";

import "../../styles/components.scss";
import "./dictionary.scss";

class Dictionary extends Component {
  constructor(props) {
    super(props);

    this.state = {
      suggestions: [],
      text: "",
      description: ""
    };
  }

  getWord = async word => {
    const response = await api.get(`palavra/${word}`);

    const keys = Object.keys(response.data);
    const list = [];

    keys.map(key => {
      const item = {
        id: key,
        name: response.data[key]
      };
      list.push(item);
    });

    return list;
  };

  getDescriptionWord = async id => {
    const response = await api.get(`palavra/${id}`);

    this.setState({ description: response.data.description });
  };

  onTextChanged = async e => {
    const value = e.target.value;

    let suggestions = [];

    if (value.length > 0) {
      suggestions = await this.getWord(value);
    } else {
      this.setState({ suggestions: [] });
    }

    this.setState(() => ({ suggestions, text: value }));
  };

  suggestionSelected = item => {
    this.setState(() => ({ text: item.name, suggestions: [] }));

    this.getDescriptionWord(item.id);
  };

  renderSuggestions() {
    const { suggestions } = this.state;

    if (suggestions.length === 0) {
      return null;
    }

    return (
      <div className="econo-suggestions">
        <ul>
          {suggestions.map(item => (
            <li key={item.id} onClick={() => this.suggestionSelected(item)}>
              {item.name}
            </li>
          ))}
        </ul>
      </div>
    );
  }

  render() {
    const { text } = this.state;
    return (
      <>
        <h1>Dicionário</h1>
        <div className="econo-search">
          <input
            className="econo-search-input"
            type="text"
            onChange={this.onTextChanged}
            value={text}
            placeholder="Algum termo deu um nó na cabeça?"
          />
          <a className="econo-search-icon" href="/">
            <div className="fas fa-search"></div>
          </a>
          {this.renderSuggestions()}
        </div>
        <div className="econo-box-container">
          <Box title="Mais pesquisadas" />
          <Box title="Definição" content={this.state.description} />
        </div>
      </>
    );
  }
}

export default Dictionary;
