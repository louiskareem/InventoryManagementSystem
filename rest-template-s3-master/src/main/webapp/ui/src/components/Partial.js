import React from 'react';
import { withRouter } from 'react-router-dom';

const Partial = (props) =>
{
    return (
        <nav className="sidebar sidebar-offcanvas" id="sidebar">
            <ul className="nav">
                <li className="nav-item">
                    <a className="nav-link" href="/Dashboard">
                        <i className="ti-dashboard menu-icon"/>
                        <span className="menu-title">Dashboard</span>
                    </a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/Products">
                        <i className="ti-layout-list-post menu-icon"/>
                        <span className="menu-title">Products</span>
                    </a>
                </li>
            </ul>
        </nav>
    )
}

export default withRouter(Partial);