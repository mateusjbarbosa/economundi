import React, { Component } from "react";

import { BoxList, BoxText } from "../../components";

import api from "../../services/api";

import "./dictionary.scss";

class Dictionary extends Component {
  constructor(props) {
    super(props);

    this.state = {
      suggestions: [],
      topSearched: [],
      text: "",
      description: ""
    };
  }

  async componentDidMount() {
    const list = await this.getTopSearched();

    this.setState({
      topSearched: list,
      isLoadingComplete: true
    });
  }

  jsonByWordObject = data => {
    const keys = Object.keys(data);

    const list = [];

    keys.forEach(key => {
      const item = {
        id: key,
        name: data[key]
      };

      list.push(item);
    });

    return list;
  };

  getWord = async word => {
    const response = await api.get(`/api/v1/word/${word}`);

    return this.jsonByWordObject(response.data);
  };

  getDescriptionWord = async id => {
    const response = await api.get(`/api/v1/word/${id}`);

    this.setState({ description: response.data.description });
  };

  getTopSearched = async () => {
    const response = await api.get("/api/v1/word/top");
    const list = this.jsonByWordObject(response.data);

    return list;
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
      <div className="suggestions">
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
    const { description, text, topSearched } = this.state;

    return (
      <>
        <div className="dictionary-title">
          <h1>Dicionário</h1>
        </div>
        <div className="search">
          <input
            className="input"
            type="text"
            onChange={this.onTextChanged}
            value={text}
            placeholder="Algum termo deu um nó na cabeça?"
          />
          {this.renderSuggestions()}
        </div>
        <div className="dictionary-box-container">
          <BoxList title="Mais pesquisadas" content={topSearched} />
          <BoxText title="Definição" content={description} />
        </div>
      </>
    );
  }
}

export default Dictionary;
