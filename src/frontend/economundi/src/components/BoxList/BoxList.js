import React from "react";

import "./boxList.scss";

const BoxList = ({ title, content }) => {
  const keys = Object.keys(content);

  if (content != null) {
    return (
      <div className="econo-box">
        <div className="econo-box-title">
          <h2>{title}</h2>
        </div>
        <div className="econo-box-content">
          {keys.map(key => (
            <p className="econo-paragraph-spotlight">{content[key].name}</p>
          ))}
        </div>
      </div>
    );
  }
};

export default BoxList;
