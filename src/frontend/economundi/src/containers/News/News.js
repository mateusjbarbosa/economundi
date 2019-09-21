import React, { Component } from "react";

import { BoxNews } from "../../components";

import api from "../../services/api";

import "./news.scss";

class News extends Component {
  constructor(props) {
    super(props);

    this.state = {
      listNews: []
    };
  }

  async componentDidMount() {
    const list = await this.getNewsBrazil();

    this.setState({
      listNews: list
    });
  }

  getNewsBrazil = async (page = 0) => {
    const response = await api.get(`/noticias/brasil/${page}`);

    return response.data;
  };

  getNewsWorld = async (page = 0) => {
    const response = await api.get(`/noticias/mundo/${page}`);

    return response.data;
  };

  render() {
    const { listNews } = this.state;
    const listKeys = Object.keys(listNews);

    return (
      <>
        <div className="news-title">
          <h1>Notícias</h1>
        </div>
        <div className="news-container">
          {listKeys.map(key => (
            <BoxNews key={key} news={listNews[key]} />
          ))}
        </div>
      </>
    );
  }
}

export default News;
