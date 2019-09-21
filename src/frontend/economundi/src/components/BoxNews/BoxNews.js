import React from "react";

import "./boxNews.scss";

const BoxNews = ({ news }) => {
  return (
    <div className="box-news">
      <img src={news.titleToImage} alt={news.description} />
      <div className="box-title">
        <h2>{news.title}</h2>
      </div>
      <div className="box-description">
        <p>{news.description}</p>
      </div>
    </div>
  );
};

export default BoxNews;
