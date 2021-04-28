import React from 'react';
import './App.css';
import Routes from './Routes';
import Navbar from "./components/Navbar";
import Partial from "./components/Partial";
import Login from "./Login/Login";
import Register from "./Register/Register";

function App()
{
    return (
        <div>
            {(!sessionStorage.getItem('loggedIn')) ? window.location.pathname === '/Register' ? <Register/> : <Login/>  :
            <div className="container-scroller">
                <Navbar />
                <div className="container-fluid page-body-wrapper">
                    <Partial/>
                    <div className="main-panel">
                        <div className="content-wrapper">
                            <Routes />
                        </div>
                    </div>
                </div>
            </div>}
        </div>
    )
}

export default App;
