import React, { Component } from "react";

import "./boxCurrency.scss";

class BoxCurrency extends Component {
  constructor(props) {
    super(props);

    this.state = { isInfo: false };
  }

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

  onChangeInfo = () => {
    const { isInfo } = this.state;

    if (!isInfo) {
      this.setState({ isInfo: true });
    } else {
      this.setState({ isInfo: false });
    }
  };

  render() {
    const { index } = this.props;
    const { isInfo } = this.state;

    return (
      <div className="box-indexes">
        <h2>{this.getTitlePortuguese()}</h2>

        <div onClick={this.onChangeInfo}>
          {index.variation > 0 ? (
            !isInfo ? (
              <span className="variation-positive">
                +{index.variation.toFixed(2)}%
              </span>
            ) : (
              <>
                <span className="variation-info-positive">
                  Compra: {index.buy}
                </span>
                <span className="variation-info-positive">
                  Venda: {index.sell}
                </span>
              </>
            )
          ) : !isInfo ? (
            <span className="variation-negative">
              {index.variation.toFixed(2)}%
            </span>
          ) : (
            <>
              <span className="variation-info-negative">
                Compra: {index.buy}
              </span>
              <span className="variation-info-negative">
                Venda: {index.sell}
              </span>
            </>
          )}
        </div>
      </div>
    );
  }
}

export default BoxCurrency;
