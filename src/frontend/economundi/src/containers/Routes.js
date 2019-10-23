import React from "react";
import { Route, Switch } from "react-router-dom";

import {
  Dictionary,
  Indexes,
  News,
  Login,
  Profile,
  ProfileData,
  Register
} from "../containers";

const Routes = () => (
  <Switch>
    <Route path="/noticias" component={News} />
    <Route path="/dicionario" component={Dictionary} />
    <Route path="/indices" component={Indexes} />
    <Route exact path="/perfil" component={Profile} />
    <Route path="/perfil/login" component={Login} />
    <Route path="/perfil/cadastrar" component={Register} />
    <Route path="/perfil/dados" component={ProfileData} />
  </Switch>
);

export default Routes;
