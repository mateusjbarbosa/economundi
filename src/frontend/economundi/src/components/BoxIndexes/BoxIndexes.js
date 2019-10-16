import React, { Component } from "react";

import "./boxIndexes.scss";

class BoxIndexes extends Component {
  getTitlePortuguese = () => {
    const { title } = this.props;

    let newTitle = "";

    switch (title) {
      case "Dollar":
        newTitle = "Dólar";
        break;

      case "Euro":
        newTitle = title;
        break;

      default:
        newTitle = title;
        break;
    }

    return newTitle;
  };

  render() {
    const { index } = this.props;

    return (
      <div className="box-indexes">
        <h2>{this.getTitlePortuguese()}</h2>

        {index.variation > 0 ? (
          <span className="variation-positive"> +{index.variation}</span>
        ) : (
          <span className="variation-negative"> -{index.variation}</span>
        )}
      </div>
    );
  }
}

export default BoxIndexes;
