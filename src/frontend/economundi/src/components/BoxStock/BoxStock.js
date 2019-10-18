import React, { Component } from "react";

import "./boxStock.scss";

class BoxStock extends Component {
  constructor(props) {
    super(props);

    this.state = { isInfo: false };
  }

  onChangeInfo = () => {
    const { isInfo } = this.state;

    if (!isInfo) {
      this.setState({ isInfo: true });
    } else {
      this.setState({ isInfo: false });
    }
  };

  render() {
    const { stock, title } = this.props;
    const { isInfo } = this.state;

    return (
      <div className="box-stock">
        <h2>{title}</h2>

        <div onClick={this.onChangeInfo}>
          {stock.variation > 0 ? (
            !isInfo ? (
              <span className="variation-stock-positive">
                +{stock.variation.toFixed(2)}%
              </span>
            ) : (
              <span className="variation-stock-info-positive">
                {stock.points.toLocaleString("pt-BR")} pontos
              </span>
            )
          ) : !isInfo ? (
            <span className="variation-stock-negative">
              {stock.variation.toFixed(2)}%
            </span>
          ) : (
            <span className="variation-stock-info-negative">
              {stock.points.toLocaleString("pt-BR")} pontos
            </span>
          )}
        </div>
      </div>
    );
  }
}

export default BoxStock;
