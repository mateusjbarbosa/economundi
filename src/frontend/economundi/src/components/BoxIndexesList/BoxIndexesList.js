import React, { Component } from "react";

import "./boxIndexesList.scss";

class BoxIndexesList extends Component {
  getTitlePortuguese = () => {
    const { title } = this.props;

    switch (title) {
      case "Pound Sterling":
        return "Libra Esterlina";

      case "Bitcoin":
        return title;

      case "Argentine Peso":
        return "Peso Argentino";

      default:
        return title;
    }
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
              {currency}
            </span>
          ) : (
            <span key={currency} className="variation-item-negative">
              {currency}
            </span>
          )
        )}
      </div>
    );
  }
}

export default BoxIndexesList;
