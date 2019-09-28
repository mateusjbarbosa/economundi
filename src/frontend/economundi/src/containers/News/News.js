import React, { Component } from "react";

import { BoxNews } from "../../components";

import PrevIcon from "../../img/prev-icon.png";
import PrevIconDisable from "../../img/prev-icon-disable.png";
import NextIcon from "../../img/next-icon.png";
import NextIconDisable from "../../img/next-icon-disable.png";

import api from "../../services/api";

import "./news.scss";

class News extends Component {
  constructor(props) {
    super(props);

    this.state = {
      listNews: [],
      currentNews: "BRAZIL",
      currentPage: 0,
      firstPage: true,
      lastPage: false
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
          listNews = await this.getNewsWorld();
        }

        if (
          !(
            Object.entries(listNews).length === 0 &&
            listNews.constructor === Object
          )
        ) {
          this.setState({ listNews: listNews, firstPage: false });
        } else {
          this.setState({
            currentPage: this.state.currentPage - 1,
            lastPage: true
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
          listNews = await this.getNewsWorld();
        }

        if (
          !(
            Object.entries(listNews).length === 0 &&
            listNews.constructor === Object
          )
        ) {
          this.setState({ listNews: listNews, lastPage: false });
        } else {
          this.setState({
            currentPage: this.state.currentPage + 1,
            firstPage: true
          });
        }
      }
    );
  };

  getNewsBrazil = async () => {
    const response = await api.get(
      `/noticias/Brazil/${this.state.currentPage}`
    );

    return response.data;
  };

  getNewsWorld = async () => {
    const response = await api.get(`/noticias/World/${this.state.currentPage}`);

    return response.data;
  };

  render() {
    const { listNews, firstPage, lastPage } = this.state;
    const listKeys = Object.keys(listNews);

    return (
      <>
        <div className="news-title">
          <h1>Notícias</h1>
        </div>
        <div className="news-paginator">
          {firstPage ? (
            <img
              src={PrevIconDisable}
              alt="Página anterior"
              className="news-btn-prev"
            />
          ) : (
            <img
              src={PrevIcon}
              alt="Página anterior"
              className="news-btn-prev"
              onClick={this.decreasePage}
            />
          )}

          {lastPage ? (
            <img
              src={NextIconDisable}
              alt="Próxima página"
              className="news-btn-next"
            />
          ) : (
            <img
              src={NextIcon}
              alt="Próxima página"
              className="news-btn-next"
              onClick={this.increasePage}
            />
          )}
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
