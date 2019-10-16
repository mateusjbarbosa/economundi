import React, { Component } from "react";

import "./boxIndexes.scss";

class BoxIndexes extends Component {
  getTitlePortuguese = () => {
    const { title } = this.props;

    switch (title) {
      case "Dollar":
        return "Dólar";

      case "Euro":
        return title;

      default:
        return title;
    }
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
