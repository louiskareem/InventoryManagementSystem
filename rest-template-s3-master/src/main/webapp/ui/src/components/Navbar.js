import React from 'react';
import './Navbar.css';
import {withRouter} from 'react-router-dom';
import Logout from "./Logout";

const Navigation = (props) =>
{
    return (
        <nav className="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div className="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
                <a className="navbar-brand brand-logo mr-5" href="/Dashboard">
                    <img className="mr-2" src="images/1200px-Amazon_logo.svg.png" height="30" alt="amazong.png"/>
                </a>
                <a className="navbar-brand brand-logo-mini" href="/Dashboard">
                    <img src="images/6739204_preview.png" alt="logo"/>
                </a>
            </div>
            <div className="navbar-menu-wrapper d-flex align-items-center justify-content-end">
                <button id="toggleIcon" className="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                    <i className="ti-view-list"/>
                </button>
                <ul className="navbar-nav navbar-nav-right">
                    <li className="nav-item nav-profile dropdown">
                        <a className="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                            <img src="https://thumbor.forbes.com/thumbor/fit-in/416x416/filters%3Aformat%28jpg%29/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5bb22ae84bbe6f67d2e82e05%2F0x0.jpg%3Fbackground%3D000000%26cropX1%3D560%26cropX2%3D1783%26cropY1%3D231%26cropY2%3D1455" alt="profile"/>
                        </a>
                        <div className="dropdown-menu dropdown-menu-left navbar-dropdown" aria-labelledby="profileDropdown">
                            <a className="dropdown-item" onClick={Logout}><i className="ti-power-off text-primary"/> Log out</a>
                        </div>
                    </li>
                </ul>
                <button className="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas"><span className="ti-view-list"/></button>
            </div>
        </nav>
    )
}

export default withRouter(Navigation);