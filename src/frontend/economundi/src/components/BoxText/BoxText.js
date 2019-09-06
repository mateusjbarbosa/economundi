import React from "react";

import "./boxText.scss";

const BoxText = ({ title, content }) => {
  if (content != null) {
    return (
      <div className="econo-box">
        <div className="econo-box-title">
          <h2>{title}</h2>
        </div>
        <div className="econo-box-content">
          <p className="econo-paragraph-text">{content}</p>
        </div>
      </div>
    );
  }
};

export default BoxText;
