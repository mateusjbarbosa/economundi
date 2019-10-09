import React from "react";

import "./boxText.scss";

const BoxText = ({ title, content }) => {
  return (
    <div className="box-text">
      <h2>{title}</h2>

      <p className="box-text-content">{content}</p>
    </div>
  );
};

export default BoxText;
