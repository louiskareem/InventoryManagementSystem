import React, {Component} from 'react';
import {RiMoneyEuroCircleLine} from "react-icons/ri";
import $ from "jquery";
import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'
import {faInfoCircle} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

class Products extends Component
{
    constructor(props)
    {
        super(props);
        this.state = {data: []}
    }

    // Code is invoked after the component is mounted/inserted into the DOM tree.
    componentDidMount()
    {
        const url = 'http://localhost:9090/amazon/api/products'
        const MySwal = withReactContent(Swal)

        $.ajax({
            url: url,
            headers:
            {
                'Authorization': sessionStorage.getItem('Base'),
            },
            dataType: 'json',
            type: 'GET',
            contentType: 'application/json'
        })
        .done((data) =>
        {
            // console.log(sessionStorage.getItem('Base').charAt(0))
            this.setState({
                data: data,
            })
        })
        .fail(function (data)
        {
            if (data.status === 404)
            {
                MySwal.fire({
                    title: 'Server is disconnected!',
                    icon: 'error',
                    html: "<p>Please click on the button below to log back in.</p>" +
                        "<br/>" +
                        "<a class='btn btn-outline-danger' href='/Logout'>Login</a>",
                    showConfirmButton: false,
                })
            }
        })
    }

    handleDelete = (id, e) =>
    {
        const MySwal = withReactContent(Swal)
        e.preventDefault();

        $.ajax({
            url : "http://localhost:9090/amazon/api/products/delete/"+ id,
            type: "DELETE",
            complete: function(data, textStatus, jqXHR)
            {
                if (data.responseText === "success")
                {
                    MySwal.fire({
                        title: 'Operation successful',
                        text: "Product is removed from the inventory",
                        icon: 'success',
                        allowOutsideClick: false,
                        showConfirmButton: true,
                        showCancelButton: false,
                        closeOnCancel: false
                    })
                    .then(value =>
                    {
                        if (value.isConfirmed)
                        {
                            window.location.reload(true);
                        }
                    })
                }
                else if(data.responseText !== "success")
                {
                    MySwal.fire("Not authorized", "You are not authorized to make this request!", "error");
                }
            }
        })
    }

    handleUpdate = (id, product, e) =>
    {
        const MySwal = withReactContent(Swal)
        e.preventDefault();

        MySwal.fire({
            title: 'Update product',
            showConfirmButton: true,
            confirmButtonText: 'Submit',
            html:
                "<textarea id='name' class='swal2-input'></textarea>" +
                "<textarea id='description' class='swal2-input'></textarea>" +
                "<input id='price' class='swal2-input'>",
            onOpen: function () {
                MySwal.getPopup().querySelector('#name').value = product.name
                MySwal.getPopup().querySelector('#description').value = product.description
                MySwal.getPopup().querySelector('#price').value = product.price
            },
            preConfirm: () => (
            {
                name: $('#name').val(),
                description: $('#description').val(),
                price: $('#price').val()
            })
        })
        .then(value =>
        {
            if (value.isConfirmed)
            {
                $.ajax({
                    url : "http://localhost:9090/amazon/api/products/update/"+ id,
                    type: "PUT",
                    data : JSON.stringify(value.value),
                    contentType: "application/json",
                    dataType: 'json',
                    complete: function(data, textStatus, jqXHR)
                    {
                        if (data.responseText === "success")
                        {
                            MySwal.fire({
                                title: 'Operation successful',
                                text: "Product is updated",
                                icon: 'success',
                                allowOutsideClick: false,
                                showConfirmButton: true,
                                showCancelButton: false,
                                timer: 2000,
                                closeOnCancel: false
                            })
                            .then(value =>
                            {
                                if (value.isConfirmed)
                                {
                                    window.location.reload(true);
                                }
                            })
                        }
                        else if(data.responseText !== "success")
                        {
                            MySwal.fire("Not authorized", "You are not authorized to make this request!", "error");
                            window.location.reload(false);
                        }
                    }
                })
            }
        })
    }

    openInfoModal(e)
    {
        const MySwal = withReactContent(Swal)
        e.preventDefault();

        MySwal.fire({
            icon: 'info',
            text: 'Scroll table to the right to update or remove product'
        })
    }

    handleChange = (e) =>
    {
        // const target = e.target;
        // const searchValue = target.type === 'checkbox' ? target.checked : target.value;
        // const name = target.name;

        // Write on keyup event of keyword input element
        let input = document.getElementById("searchInput");
        let filter = input.value.toLocaleLowerCase()

        // Show only matching TR, hide rest of them
        $.each($("#productsTable tbody tr"), function()
        {
            if($(this).text().toLocaleLowerCase().indexOf(filter) === -1)
            {
               // $('.notfound').show();
               $(this).hide();
            }
            else
            {
            	// $('.notfound').hide();
                $(this).show();
            }
        });
    }

    render() {
        return (
            <div className="App">
                <div className="row">
                    <div className="card">
                        <br/>
                        <h1 className="card-header-title">Products <FontAwesomeIcon onClick={(e) => this.openInfoModal(e)} icon={faInfoCircle} /></h1>
                        <div className="card-body">
                            <input onChange={this.handleChange} type="text" className="form-control" id="searchInput" placeholder="Type here to search..." aria-label="search" aria-describedby="search"/>
                            <br/>
                            <table id={"productsTable"} className="table table-responsive table-hover table-bordered">
                                <caption>
                                    List of products
                                </caption>
                                <thead>
                                    <tr>
                                        <th style={{ width: "100%" }}>Product ID</th>
                                        <th >Product Name</th>
                                        <th>Product Description</th>
                                        <th>Product Price in <RiMoneyEuroCircleLine/></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                {this.state.data.map
                                (product =>
                                    <tr key={product.ID}>
                                        <td>{product.ID}</td>
                                        <td>{product.name}</td>
                                        <td>{product.description}</td>
                                        <td>&euro; {product.price.toFixed(2)}</td>
                                        <td><input type="button" value='Update' onClick={(e) => this.handleUpdate(product.ID, product, e)} className="btn btn-outline-primary" /></td>
                                        <td><input type="button" value='Remove' onClick={(e) => this.handleDelete(product.ID, e)} className="btn btn-outline-danger" /></td>
                                    </tr>
                                )}
                                </tbody>
                            </table>
                            <a className="btn btn-block btn-outline-info" href="/Create">Add products to the system</a>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Products;