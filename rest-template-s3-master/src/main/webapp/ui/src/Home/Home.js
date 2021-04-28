import React, {Component} from "react";
import { RiMoneyEuroCircleLine } from "react-icons/ri";
import withReactContent from 'sweetalert2-react-content'
import $ from "jquery";
import Swal from 'sweetalert2'
import { withRouter  } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faInfoCircle} from '@fortawesome/free-solid-svg-icons'
import Chart from 'chart.js'

class Home extends Component
{
    constructor()
    {
        super();
        this.state = {data: []}
    }

    async componentDidMount()
    {
        await this.getData()
        this.displayChartData()
    }

    displayChartData()
    {
        let chartElement = document.getElementById('myChart');
        new Chart(chartElement,
        {
            type: 'bar',
            data:
            {
                labels: this.state.data.map(product => "PR-ID: " + product.products.ID),
                datasets:
                [{
                    label: 'Units in stock',
                    data: this.state.data.map(product => product.units),
                    backgroundColor: this.unitsChartBackGroundColor(this.state.data.length),
                    hoverBackgroundColor: "#F9E79F"
                }]
            },
            options:
            {
                title:
                {
                    display: true,
                    text: "Amount of product's units in stock"
                },
                legend:
                {
                  labels:
                  {
                      fontSize: 20,
                  }
                },
                scales:
                {
                    yAxes:
                    [{
                        ticks:
                        {
                            fontSize: 15,
                            beginAtZero: true
                        }
                    }],
                    xAxes:
                    [{
                        ticks:
                        {
                            fontSize: 14,
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    }

    getData = () =>
    {
        const url = 'http://localhost:9090/amazon/api/inventory'
        const MySwal = withReactContent(Swal)

        fetch(url)
        .then((response) =>
        {
            const contentType = response.headers.get("content-type");

            if ((contentType === "application/json") || (response.status !== 204) )
            {
                return response.json().then((response) =>
                {
                    console.table(response)
                    this.setState({
                        data: response
                    })

                    this.displayChartData()
                });
            }
            else
            {
                MySwal.fire
                ({
                    title: 'Server is disconnected!',
                    icon: 'error',
                    html: "<p>Please click on the button below to log back in!</p>" +
                        "<br/>" +
                        "<a class='btn btn-outline-danger' href='/Logout'>Login</a>",
                    showConfirmButton: false,
                })
            }
        })
        .catch(err => {
            if (err == 'TypeError: Failed to fetch')
            {
                MySwal.fire
                ({
                    title: 'Server is disconnected!',
                    icon: 'error',
                    html: "<p>Please click on the button below to log back in.</p>" +
                        "<br/>" +
                        "<a class='btn btn-outline-danger' href='/Logout'>Login</a>",
                    showConfirmButton: false,
                })
            }
        });
    }

    unitsChartBackGroundColor(dataLength)
    {
        var colors = [];
        for(let i = 0; i < dataLength; i++)
        {
            this.state.data.map(function (product)
            {
                if (product.units <= 100)
                {
                    // if units below 100 limit
                    colors.push('#EC7063');
                }
                else
                {
                    // if units above 100 limit
                    colors.push('#45B39D');
                }
                // to satisfy lint rules, return colors
                return colors
            })
        }
        return colors;
    }

    openProduct(product, e)
    {
        const MySwal = withReactContent(Swal)
        e.preventDefault();

        MySwal.fire({
            title: 'Product Information',
            showConfirmButton: false,
            html:
                "<textarea id='name' class='swal2-input'></textarea>" +
                "<textarea id='description' class='swal2-input'></textarea>" +
                "<input id='price' class='swal2-input'>",
            onOpen: function () {
                MySwal.getPopup().querySelector('#name').value = product.products.name
                MySwal.getPopup().querySelector('#description').value = product.products.description
                MySwal.getPopup().querySelector('#price').value = product.products.price
            },
            preConfirm: () => (
            {
                name: $('#name').val(),
                description: $('#description').val(),
                price: $('#price').val()
            })
        })
    }

    openInfoModal(e)
    {
        const MySwal = withReactContent(Swal)
        e.preventDefault();

        MySwal.fire({
            icon: 'info',
            html: "<p>Choose a product out of the name column to have a more compact display. (Column with the <i class='ti-eye'></i> icon)</p>"
        })
    }

    handleChange = (e) =>
    {

        // Write on keyup event of keyword input element
        let input = document.getElementById("searchInput");
        let filter = input.value.toLocaleLowerCase()

        // Show only matching TR, hide rest of them
        $.each($("#inventoryTable tbody tr"), function()
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

    render()
    {
        const {data} = this.state

        return (
            <div className="App">
                <h1 className="card-header-title">Inventory Management System <FontAwesomeIcon onClick={(e) => this.openInfoModal(e)} icon={faInfoCircle} /></h1>
                <br/>
                <div className={"card-deck"}>
                    <div style={{maxWidth: '30rem', maxHeight: '40rem'}} className={"card"}>
                        <div className={"card-body"}>
                            <button className={"btn btn-outline-info btn-sm btn-block"} onClick={this.getData} type={"button"}>Update Chart</button>
                            <canvas id="myChart" width="400" height="400"/>
                        </div>
                    </div>
                    <div style={{maxWidth: '15rem', maxHeight: '10rem'}} className={"card data_cards"}>
                        <div className={"card-body"}>
                            <h1 className={"card-title"}>Total number of products in the inventory:</h1>
                            <p className={"card-text"}>{data.length}</p>
                        </div>
                    </div>
                </div>
                <br/>
                <div className="row">
                    <div className="card dTable">
                        <h1 className="card-header-title">Inventory</h1>
                        <div className="card-body">
                            <input onChange={this.handleChange} type="text" className="form-control" id="searchInput" placeholder="Type here to search..." aria-label="search" aria-describedby="search"/>
                            <br/>
                            <table id={"inventoryTable"} className="table table-responsive table-hover table-bordered ">
                                <caption>
                                    List of products in the inventory
                                </caption>
                                <thead>
                                    <tr>
                                        <th>Product ID</th>
                                        <th>Product Name <i className={"ti-eye"}/></th>
                                        <th>Product Description</th>
                                        <th>Product Price in <RiMoneyEuroCircleLine /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                {data.map (product =>
                                    <tr key={product.ID}>
                                        <td>{product.products.ID}</td>
                                        <td className="openProduct" onClick={(e) => this.openProduct(product, e)}>{product.products.name}</td>
                                        <td>{product.products.description}</td>
                                        <td>&euro; {product.products.price.toFixed(2)}</td>
                                    </tr>
                                )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(Home);
