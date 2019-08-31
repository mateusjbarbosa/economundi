import React from "react";
import { Route, Switch } from "react-router-dom";

import { Dictionary, Simulation } from "../containers";

const Routes = () => (
  <Switch>
    <Route path="/simulacoes" component={Simulation} />
    <Route path="/dicionario" component={Dictionary} />
  </Switch>
);

export default Routes;
