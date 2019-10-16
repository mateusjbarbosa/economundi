import React, { Component } from "react";

import "./boxIndexesList.scss";

class BoxIndexesList extends Component {
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

  render() {
    const { list } = this.props;

    const currenciesTitle = Object.keys(list);

    return (
      <div className="box-indexes-list">
        <h2>Moedas</h2>

        {currenciesTitle.map(currency =>
          list[currency].variation > 0 ? (
            <span key={currency} className="variation-item-positive">
              {this.getTitlePortuguese(currency)}
            </span>
          ) : (
            <span key={currency} className="variation-item-negative">
              {this.getTitlePortuguese(currency)}
            </span>
          )
        )}
      </div>
    );
  }
}

export default BoxIndexesList;
