import React, { Component } from "react";
import axios from "axios";

import NewsImageDefault from "../../img/image-news-default.png";

import "./boxNews.scss";

class BoxNews extends Component {
  constructor(props) {
    super(props);

    this.state = {
      image: NewsImageDefault
    };
  }

  componentDidMount() {
    this.onLoadNews();
  }

  onLoadNews = async () => {
    const { news } = this.props;

    await axios.get(news.urlToImage).then(result => {
      if (result.status === 200) {
        this.setState({
          image: result.request.responseURL
        });
      }
    });
  };

  render() {
    const { news } = this.props;
    const { image } = this.state;
    return (
      <div className="box-news">
        <img src={image} alt="Portal EconoMundi" />
        <span />
        <div className="box-news-source">
          <h2>{news.source}</h2>
        </div>
        <div className="box-news-description">
          <p>{news.title}</p>
        </div>
        <div className="box-news-link">
          <a href={news.url} target="_blank" rel="noopener noreferrer">
            Ver notícia >>
          </a>
        </div>
      </div>
    );
  }
}

export default BoxNews;
