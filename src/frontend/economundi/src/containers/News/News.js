import React, { Component } from "react";

import { BoxNews } from "../../components";

import api from "../../services/api";

import "./news.scss";

class News extends Component {
  constructor(props) {
    super(props);

    this.state = {
      listNews: [],
      currentNews: "BRAZIL",
      currentPage: 0
    };
  }

  async componentDidMount() {
    let listNews = [];

    listNews = await this.getNewsBrazil();

    this.setState({ listNews: listNews });
  }

  increasePage = async () => {
    const { currentNews } = this.state;
    let listNews = [];

    this.setState(
      {
        currentPage: this.state.currentPage + 1
      },
      async () => {
        if (currentNews === "BRAZIL") {
          listNews = await this.getNewsBrazil();
        } else {
          // listNews = await this.getNewsWorld();
        }

        if (
          !(
            Object.entries(listNews).length === 0 &&
            listNews.constructor === Object
          )
        ) {
          this.setState({ listNews: listNews });
        } else {
          this.setState({
            currentPage: this.state.currentPage - 1
          });
        }
      }
    );
  };

  decreasePage = () => {
    const { currentNews } = this.state;
    let listNews = [];

    this.setState(
      {
        currentPage: this.state.currentPage - 1
      },
      async () => {
        if (currentNews === "BRAZIL") {
          listNews = await this.getNewsBrazil();
        } else {
          // listNews = await this.getNewsWorld();
        }

        if (
          !(
            Object.entries(listNews).length === 0 &&
            listNews.constructor === Object
          )
        ) {
          this.setState({ listNews: listNews });
        } else {
          this.setState({
            currentPage: this.state.currentPage + 1
          });
        }
      }
    );
  };

  getNewsBrazil = async () => {
    const response = await api.get(
      `/noticias/brasil/${this.state.currentPage}`
    );

    return response.data;
  };

  // getNewsWorld = async () => {
  //   const response = await api.get(`/noticias/mundo/${this.state.currentPage}`);

  //   return response.data;
  // };

  render() {
    const { listNews } = this.state;
    const listKeys = Object.keys(listNews);

    return (
      <>
        <div className="news-title">
          <h1>Notícias</h1>
        </div>
        <div className="news-paginator">
          <button className="news-btn-prev" onClick={this.decreasePage}>
            Voltar!
          </button>
          <button className="news-btn-next" onClick={this.increasePage}>
            Próximo!
          </button>
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
