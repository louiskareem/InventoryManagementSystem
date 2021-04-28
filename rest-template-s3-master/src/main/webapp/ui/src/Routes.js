import React, { Component } from "react";
import { Router, Switch, Route } from "react-router-dom";
import Products from "./Product/Products";
import Home from "./Home/Home";
import history from './history';
import Login from "./Login/Login";
import Register from "./Register/Register";
import Create from "./Product/Create"
import Logout from "./components/Logout"

export default class Routes extends Component
{
    render()
    {
        return (
            <Router history={history}>
                <Switch>
                    <Route path="/Login" component={Login} />
                    <Route path="/Register" component={Register} />
                    {/*<Route path="/" exact component={Home} />*/}
                    <Route path="/Dashboard" component={Home} />
                    <Route path="/Products" component={Products} />
                    <Route path="/Create" component={Create} />
                    <Route path="/Logout" component={Logout} />
                </Switch>
            </Router>
        )
    }
}