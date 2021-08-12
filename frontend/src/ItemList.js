import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class ItemList extends Component {

    constructor(props) {
        super(props);
        this.state = {items: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/items/')
            .then(response => response.json())
            .then(data => this.setState({items: data}));
    }

    async remove(id) {
        await fetch(`/items/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedItems = [...this.state.items].filter(i => i.id !== id);
            this.setState({items: updatedItems});
        });
    }

    render() {
        const {items, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const itemList = items.map(item => {
            return <tr key={item.id}>
                <td style={{whiteSpace: 'nowrap'}}>{item.desc}</td>
                <td>{item.price}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/items/" + item.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(item.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/items/new/">Add Item</Button>
                    </div>
                    <h3>Items</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Description</th>
                            <th width="30%">Price</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {itemList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default ItemList;