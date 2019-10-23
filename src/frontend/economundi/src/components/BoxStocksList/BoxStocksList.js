import React, { Component } from "react";

import "./boxStocksList.scss";

class BoxStocksList extends Component {
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
    const { stocks } = this.props;
    const { isInfo } = this.state;

    const stocksTitle = Object.keys(stocks);

    return (
      <div className="box-stocks-list">
        <h2>Bolsas</h2>

        <button className="box-stocks-button" onClick={this.onChangeInfo}>
          Ver variação
        </button>

        {stocksTitle.map(stock =>
          stocks[stock].variation > 0 ? (
            !isInfo ? (
              <span key={stock} className="variation-stock-item-positive">
                {stock}
              </span>
            ) : (
              <span key={stock} className="variation-stock-item-positive">
                {stocks[stock].points > 0
                  ? stocks[stock].points.toLocaleString("pt-BR") + " pontos"
                  : "Pontuação não enviada"}
              </span>
            )
          ) : !isInfo ? (
            <span key={stock} className="variation-stock-item-negative">
              {stock}
            </span>
          ) : (
            <span key={stock} className="variation-stock-item-negative">
              {stocks[stock].points > 0
                ? stocks[stock].points.toLocaleString("pt-BR") + " pontos"
                : "Pontuação não enviada"}
            </span>
          )
        )}
      </div>
    );
  }
}

export default BoxStocksList;
