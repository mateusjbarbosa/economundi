import React from "react";

import "./box.scss";

function Box(props) {
  return (
    <section className="econo-box">
      <h2>{props.title}</h2>
      <p>{props.content}</p>
    </section>
  );
}

export default Box;
