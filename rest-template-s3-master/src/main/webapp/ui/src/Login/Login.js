import React, { Component } from "react";
import "./loginStyle.css";
import $ from "jquery";
import { withRouter } from "react-router-dom";
import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'
import {faEnvelopeOpenText, faUserLock} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

class Login extends Component
{
    constructor(props)
    {
        super(props);
        this.state = { email:'', password:'', warning: false};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = (e) =>
    {
        const target = e.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value,
        });
    }

    validation(email)
    {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
    }

    handleSubmit = (e) =>
    {
        e.preventDefault()
        const MySwal = withReactContent(Swal)

        $.ajax({
            url : "http://localhost:9090/amazon/api/login",
            type: "POST",
            data : JSON.stringify(this.state),
            contentType: "application/json",
            dataType: 'json',
            complete: function(data, textStatus, jqXHR)
            {
                if (data.responseText === "success")
                {
                    MySwal.fire({
                        text: 'Two-Step Verification',
                        timer: 900000, // wait 15 minutes
                        inputAttributes:
                        {
                            min: 6,
                            max: 6,
                            autocapitalize: 'off'
                        },
                        input: 'text',
                        // inputPlaceholder: 'Enter your code...',
                        timerProgressBar: true,
                        allowOutsideClick: true,
                        showConfirmButton: true,
                        showCancelButton: true,
                        // closeOnConfirm: false,
                        closeOnCancel: true,
                        inputValidator: (value) =>
                        {
                            if (!value)
                            {
                                return 'Please enter the correct code!'
                            }
                        },
                    })
                    .then(value =>
                    {
                        if (value.isConfirmed)
                        {
                            if (isNaN(value))
                            {
                                $.ajax({
                                    url : "http://localhost:9090/amazon/api/authentication",
                                    type: "POST",
                                    data : JSON.stringify(value['value']),
                                    contentType: "application/json",
                                    dataType: 'json',
                                    complete: function(data, textStatus, jqXHR)
                                    {
                                        if (data.responseText === "success")
                                        {
                                            sessionStorage.setItem('loggedIn', true)
                                            sessionStorage.setItem('Base', data.getResponseHeader('authorization'))
                                            window.location.href="/Dashboard"
                                        }
                                        else
                                        {
                                            MySwal.fire("Please enter the correct code!", "Wrong code", "error");
                                        }

                                    }
                                })
                            }
                        }
                        else if(value.isDismissed || value.isDenied)
                        {
                            MySwal.fire("Process canceled!", "Please try again.", "warning");
                        }
                        console.log(value.isConfirmed)
                    });
                }
                else
                {
                    MySwal.fire("Server error!", "Please try again.", "error");
                }
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                MySwal.fire("Server error!", "Please try again.", "error");
            }
        });
    }


    requestRegistration()
    {
        const MySwal = withReactContent(Swal)
        MySwal.fire({
            text: 'Request for registration',
            input: 'text',
            inputPlaceholder: 'Enter your email...',
            showConfirmButton: true,
            showCancelButton: true,
            closeOnConfirm: false,
            closeOnCancel: false,
            inputValidator: (value) =>
            {
                if (!value)
                {
                    return 'Please enter a valid email'
                }
            },
        })
        .then(value =>
        {
            if (value !== "")
            {
                $.ajax({
                    url : "http://localhost:9090/amazon/api/requests",
                    type: "POST",
                    data : JSON.stringify(value['value']),
                    contentType: "application/json",
                    dataType: 'json',
                    complete: function(data, textStatus, jqXHR)
                    {
                        if (data.responseText === "success")
                        {
                            MySwal.fire("Success", "Your registration is underway! An email has been sent to " + JSON.stringify(value['value']) + ". Please check your email for the latest updates.", "success")
                        }
                    }
                })
            }
        });
    }

    render()
    {
        return (
            <div className="App">
                <section class="signin">
                    <div class="signin-content">
                        <div class="signin-image">
                            <img src={require('../components/Animated-Lead.png')} alt="sign_in"/>
                            <input class="form-submit" onClick={this.requestRegistration} type={"button"} value={"Request Registration"}/>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Amazon Inventory Management System</h2>
                            <form onSubmit={this.handleSubmit} class="register-form" id="login-form">
                                <div class="form-group">
                                    <label className={"iconLabel"} for="email"><FontAwesomeIcon className={"material-icons-name"} icon={faEnvelopeOpenText} /></label>
                                    <input type="email" name="email" value={this.state.email} onChange={this.handleChange} id="inputEmail" placeholder="Email address" required autoFocus/>
                                </div>
                                <div class="form-group">
                                    <label className={"iconLabel"} for="password"><FontAwesomeIcon className={"material-icons-name"} icon={faUserLock} /></label>
                                    <input type="password" name="password" onk value={this.state.password} onChange={this.handleChange} id="inputPassword" placeholder="Password" required/>
                                </div>
                                <div class="form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Login"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

export default withRouter(Login);