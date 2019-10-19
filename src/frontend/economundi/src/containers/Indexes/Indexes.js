import React, { Component } from "react";

import {
  BoxCurrency,
  BoxCurrenciesList,
  BoxStock,
  BoxStocksList
} from "../../components";

import api from "../../services/api";

import "./indexes.scss";

class Indexes extends Component {
  constructor(props) {
    super(props);

    this.state = { loading: true, currencies: {}, marketShares: {} };
  }

  componentDidMount() {
    this.loadData();
  }

  loadData = async () => {
    const response = await api.get("/api/v1/indexes/");

    if (response.status === 202) {
      this.setState(
        {
          currencies: response.data.currencies,
          marketShares: response.data.market_shares
        },
        () => {
          this.setState({ loading: false });
        }
      );
    }
  };

  copyObject = obj => {
    if (obj === null || typeof obj !== "object") {
      return obj;
    }

    let temp = obj.constructor();

    for (let key in obj) {
      temp[key] = this.copyObject(obj[key]);
    }

    return temp;
  };

  render() {
    const { currencies, loading, marketShares } = this.state;

    const currenciesTitle = Object.keys(currencies);
    let newsCurrenciesList = this.copyObject(currencies);
    delete newsCurrenciesList.Dollar;
    delete newsCurrenciesList.Euro;

    let marketSharesTitle = Object.keys(marketShares);
    let marketSharesList = this.copyObject(marketShares);
    marketSharesTitle = marketSharesTitle.sort();
    delete marketSharesList[marketSharesTitle[0]];

    return (
      <>
        <div className="indexes-title">
          <h1>Índices</h1>
        </div>

        {loading ? (
          <h1>Carregando...</h1>
        ) : (
          <>
            <div className="currencies-box">
              <BoxCurrency
                title={currenciesTitle[1]}
                index={currencies[currenciesTitle[1]]}
              />
              <BoxCurrency
                title={currenciesTitle[3]}
                index={currencies[currenciesTitle[3]]}
              />
              <BoxCurrenciesList currencies={newsCurrenciesList} />
            </div>

            <div className="stocks-box">
              <BoxStock
                title={marketSharesTitle[0]}
                stock={marketShares[marketSharesTitle[0]]}
              />
              <BoxStocksList stocks={marketSharesList} />
            </div>
          </>
        )}
      </>
    );
  }
}

export default Indexes;
