import React, { Component } from "react";

import { BoxIndexes, BoxIndexesList } from "../../components";

import api from "../../services/api";

import "./indexes.scss";

class Indexes extends Component {
  constructor(props) {
    super(props);

    this.state = { loading: true, currencies: {}, stocks: {} };
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
          stocks: response.data.stocks
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
    const { currencies, loading, stocks } = this.state;

    const currenciesTitle = Object.keys(currencies);
    let newsCurrenciesList = this.copyObject(currencies);

    delete newsCurrenciesList.Dollar;
    delete newsCurrenciesList.Euro;

    return (
      <>
        <div className="indexes-title">
          <h1>Índices</h1>
        </div>

        {loading ? (
          <h1>Carregando...</h1>
        ) : (
          <div className="indexes-box">
            <BoxIndexes
              key={currenciesTitle[1]}
              title={currenciesTitle[1]}
              index={currencies[currenciesTitle[1]]}
            />
            <BoxIndexes
              key={currenciesTitle[3]}
              title={currenciesTitle[3]}
              index={currencies[currenciesTitle[3]]}
            />
            <BoxIndexesList list={newsCurrenciesList} />
          </div>
        )}
      </>
    );
  }
}

export default Indexes;
