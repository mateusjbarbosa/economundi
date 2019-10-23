import React, { Component } from "react";

import "./boxCurrenciesList.scss";

class BoxCurrenciesList extends Component {
  constructor(props) {
    super(props);

    this.state = { isInfo: false };
  }

  getTitlePortuguese = currency => {
    let newTitle = "";

    switch (currency) {
      case "Pound Sterling":
        newTitle = "Libra";
        break;

      case "Bitcoin":
        newTitle = currency;
        break;

      case "Argentine Peso":
        newTitle = "Peso Argentino";
        break;

      default:
        newTitle = currency;
        break;
    }

    return newTitle.toUpperCase();
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
    const { currencies } = this.props;
    const { isInfo } = this.state;

    const currenciesTitle = Object.keys(currencies);

    return (
      <div className="box-indexes-list">
        <h2>Moedas</h2>

        <button className="box-indexes-button" onClick={this.onChangeInfo}>
          Ver variação
        </button>

        {currenciesTitle.map(currency =>
          currencies[currency].variation > 0 ? (
            !isInfo ? (
              <span key={currency} className="variation-item-positive">
                {this.getTitlePortuguese(currency)}
              </span>
            ) : (
              <span key={currency} className="variation-item-positive">
                {currencies[currency].variation}%
              </span>
            )
          ) : !isInfo ? (
            <span key={currency} className="variation-item-negative">
              {this.getTitlePortuguese(currency)}
            </span>
          ) : (
            <span key={currency} className="variation-item-negative">
              {currencies[currency].variation}%
            </span>
          )
        )}
      </div>
    );
  }
}

export default BoxCurrenciesList;
