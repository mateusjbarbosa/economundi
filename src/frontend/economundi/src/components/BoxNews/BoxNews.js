import React from "react";

import "./boxNews.scss";

const BoxNews = ({ news }) => {
  return (
    <div className="box-news">
      <img src={news.titleToImage} alt={news.description} />
      <span />
      <div className="box-news-title">
        <h2>{news.title}</h2>
      </div>
      <div className="box-news-description">
        <p>{news.description}</p>
      </div>
    </div>
  );
};

export default BoxNews;
