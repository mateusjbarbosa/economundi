import React from "react";
import { Route, Switch } from "react-router-dom";

import { Dictionary, HighlightNews, News, Login, Profile } from "../containers";

const Routes = () => (
  <Switch>
    <Route exact path="/" component={HighlightNews} />
    <Route path="/noticias" component={News} />
    <Route path="/dicionario" component={Dictionary} />
    <Route exact path="/perfil" component={Profile} />
    <Route path="/perfil/login" component={Login} />
  </Switch>
);

export default Routes;
