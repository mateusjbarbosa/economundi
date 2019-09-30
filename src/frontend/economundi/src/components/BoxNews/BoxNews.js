import React from "react";

import "./boxNews.scss";

const BoxNews = ({ news }) => {
  return (
    <div className="box-news">
      <img src={news.titleToImage} alt="Portal EconoMundi" />
      <span />
      <div className="box-news-source">
        <h2>{news.source}</h2>
      </div>
      <div className="box-news-description">
        <p>{news.title}</p>
      </div>
    </div>
  );
};

export default BoxNews;
