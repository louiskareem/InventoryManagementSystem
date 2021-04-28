import React, { Component } from "react";
import "./registerStyle.css";
import $ from 'jquery';
import { withRouter } from "react-router-dom";
import swal from "@sweetalert/with-react";
import {
    faBirthdayCake,
    faEnvelopeOpenText,
    faMapMarkedAlt,
    faUserLock,
    faUserTie
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

class Register extends Component
{
    constructor(props)
    {
        super(props);
        this.state = { firstname:'', lastname:'', age:'', address:'', email:'', password:'', role:'' };
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

    handleSubmit = (e) =>
    {
        e.preventDefault()

        $.ajax({
            url : "http://localhost:9090/amazon/api/register",
            type: "POST",
            mode: "no-cors",
            data : JSON.stringify(this.state),
            contentType: "application/json",
            dataType: 'json',
            complete: function(data, textStatus, jqXHR)
            {
                if (data.responseText === "success")
                {
                    window.location.href="/Login"
                }
                else
                {
                    swal("Password is incorrect!", "Please try again.", "error");
                }
            },
            error: function (jqXHR, textStatus, errorThrown)
            {

            }
        });
    }

    render()
    {
        return (
            <div className="App">
                <section class="signup">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h1 className="form-title">User Registration</h1>
                            <form onSubmit={this.handleSubmit} class="register-form" id="register-form">
                                <div class="form-group">
                                    <label className={"iconLabel"} for="firstname"><FontAwesomeIcon className={"material-icons-name"} icon={faUserTie} /></label>
                                    <input type="text" name="firstname" value={this.state.firstname} onChange={this.handleChange} id="inputFirstname" placeholder="First name" required autoFocus/>
                                </div>
                                <div class="form-group">
                                    <label className={"iconLabel"} for="lastname"><FontAwesomeIcon className={"material-icons-name"} icon={faUserTie} /></label>
                                    <input type="text" name="lastname" value={this.state.lastname} onChange={this.handleChange} placeholder="Last name" required autoFocus/>
                                </div>
                                <div class="form-group">
                                    <label className={"iconLabel"} for="age"><FontAwesomeIcon className={"material-icons-name"} icon={faBirthdayCake} /></label>
                                    <input type="text" name="age" value={this.state.age} onChange={this.handleChange} placeholder="Age" required autoFocus/>
                                </div>
                                <div class="form-group">
                                    <label className={"iconLabel"} for="address"><FontAwesomeIcon className={"material-icons-name"} icon={faMapMarkedAlt} /></label>
                                    <input type="text" name="address" value={this.state.address} onChange={this.handleChange} placeholder="Address" required autoFocus/>
                                </div>
                                <div class="form-group">
                                    <label className={"iconLabel"} for="email"><FontAwesomeIcon className={"material-icons-name"} icon={faEnvelopeOpenText} /></label>
                                    <input type="email" name="email" value={this.state.email} onChange={this.handleChange} placeholder="Email address" required autoFocus/>
                                </div>
                                <div class="form-group">
                                    <label className={"iconLabel"} for="password"><FontAwesomeIcon className={"material-icons-name"} icon={faUserLock} /></label>
                                    <input type="password" name="password" value={this.state.password} onChange={this.handleChange} placeholder="Password" required/>
                                </div>
                                <div className={"form-group"}>
                                    <select name="role" value={this.state.role} onChange={this.handleChange} className="custom-select ">
                                        <option defaultValue>Role</option>
                                        <option value="MANAGER">Manager</option>
                                        <option value="EMPLOYEE">Employee</option>
                                    </select>
                                </div>
                                <div class="form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src={require('../components/233733.gif')} alt="sign_up"/></figure>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

export default withRouter(Register);