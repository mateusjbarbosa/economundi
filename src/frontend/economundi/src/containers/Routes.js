import React from "react";
import { Route, Switch } from "react-router-dom";

import { Dictionary, News } from "../containers";

const Routes = () => (
  <Switch>
    <Route path="/dicionario" component={Dictionary} />
    <Route path="/noticias" component={News} />
  </Switch>
);

export default Routes;
