import React from "react";

import "./boxList.scss";

const BoxList = ({ title, content }) => {
  const keys = Object.keys(content);

  return (
    <div className="box-list">
      <h2>{title}</h2>

      <div className="box-list-content">
        {keys.map(key => (
          <p key={key} className="box-list-item">
            {content[key].name}
          </p>
        ))}
      </div>
    </div>
  );
};

export default BoxList;
