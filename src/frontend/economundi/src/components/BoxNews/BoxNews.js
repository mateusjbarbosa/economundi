import React from "react";

import "./boxNews.scss";

const BoxNews = ({ news }) => {
  return (
    <div className="box-news">
      <img src={news.urlToImage} alt="Portal EconoMundi" />
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
};

export default BoxNews;
