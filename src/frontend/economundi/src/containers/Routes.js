import React from "react";
import { Route, Switch } from "react-router-dom";

import { Dictionary, News, Login, Profile, Register } from "../containers";

const Routes = () => (
  <Switch>
    <Route path="/noticias" component={News} />
    <Route path="/dicionario" component={Dictionary} />
    <Route exact path="/perfil" component={Profile} />
    <Route path="/perfil/login" component={Login} />
    <Route path="/perfil/cadastrar" component={Register} />
  </Switch>
);

export default Routes;
