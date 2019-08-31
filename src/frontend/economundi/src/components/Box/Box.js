import React from "react";

import "./box.scss";

function Box(props) {
  return (
    <div className="econo-box">
      <div className="econo-box-title">
        <h2>{props.title}</h2>
      </div>
      <div className="econo-box-content">
        <p className="econo-paragraph-text">{props.content}</p>
      </div>
    </div>
  );
}

export default Box;
