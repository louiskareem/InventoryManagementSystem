import React, { Component } from 'react';
import {withRouter} from "react-router-dom";
import $ from "jquery";
import history from "../history";
import * as MySwal from "sweetalert2";

class Create extends Component
{
    constructor(props)
    {
        super(props);
        this.state = {name: '', description: '', price: '', quantity: '', category: ''};
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
        this.state.price = this.state.price.replace(/,/g, '.')

        $.ajax({
            url : "http://localhost:9090/amazon/api/products/create",
            type: "POST",
            data : JSON.stringify(this.state),
            contentType: "application/json",
            dataType: 'json',
            headers: {'Authorization':'Basic' + sessionStorage.getItem('base')},
            complete: function(data, textStatus, jqXHR)
            {
                if (data.responseText === "success")
                {
                    MySwal.fire("Operation successful", "Product is added to the inventory", "success");
                    history.push("/Products");
                }
                else if(data.responseText !== "success")
                {
                    MySwal.fire("Not authorized", "You are not authorized to make this request!", "error");
                    history.push("/Products");
                }
            }
        })

        e.preventDefault();
    }

    render() {
        return (
            <div className="App">
                <div className="row">
                    <div className="card">
                        <div className="card-body">
                            <h1 className="card-header-title"> Add product to the inventory </h1>
                            <br/>
                            <form className="form-signin" onSubmit={this.handleSubmit}>
                                <input type="text" value={this.state.name} onChange={this.handleChange} name="name" id="inputName" className="form-control" placeholder="Enter name..." required/>
                                <input type="text" value={this.state.description} onChange={this.handleChange} name="description" id="inputDescription" className="form-control" placeholder="Enter description..." required/>
                                <input type="text" value={this.state.price} onChange={this.handleChange} name="price" id="inputPrice" className="form-control" placeholder="Enter price..." required/>
                                {/*<input type="text" value={this.state.quantity} onChange={this.handleChange} name="quantity" id="inputQuantity" className="form-control" placeholder="Enter quantity" required/>*/}
                                {/*<input type="text" value={this.state.category} onChange={this.handleChange} name="category" id="inputCategory" className="form-control" placeholder="Select Category" required/>*/}
                                <br/>
                                <input className="btn btn-outline-primary btn-block" type="submit" value="Confirm" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(Create);