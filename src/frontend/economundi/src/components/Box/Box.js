import React from "react";

import "./box.scss";

function Box(props) {
  return (
    <div class="econo-box">
      <div class="econo-box-title">
        <h2>{props.title}</h2>
      </div>
      <div class="econo-box-content">
        <p class="econo-paragraph-text">{props.content}</p>
      </div>
    </div>
  );
}

export default Box;
