import React from "react";

import "./boxText.scss";

const BoxText = ({ title, content }) => {
  return (
    <div className="box">
      <div className="box-title">
        <h2>{title}</h2>
      </div>

      <p className="box-text">{content}</p>
    </div>
  );
};

export default BoxText;
