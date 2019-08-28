import React from "react";
import { Route, Switch } from "react-router-dom";

import { Dicionario } from "../containers";

const Routes = () => (
  <Switch>
    <Route path="/dicionario" component={Dicionario} />
  </Switch>
);

export default Routes;
