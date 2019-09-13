import React from "react";

import "./boxList.scss";

const BoxList = ({ title, content }) => {
  const keys = Object.keys(content);

  return (
    <div className="box">
      <div className="box-title">
        <h2>{title}</h2>
      </div>
      <div className="box-content">
        {keys.map(key => (
          <p key={key} className="box-list">
            {content[key].name}
          </p>
        ))}
      </div>
    </div>
  );
};

export default BoxList;
