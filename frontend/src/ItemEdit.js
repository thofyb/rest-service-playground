import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class ItemEdit extends Component {

    emptyData = {
        id: 0,
        desc: null,
        price: 0.0
    };

    constructor(props) {
        super(props);
        this.state = {
            data: this.emptyData
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const item = await (await fetch(`/items/${this.props.match.params.id}/`)).json();
            this.setState({data: item});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let data = {...this.state.data};
        data[name] = value;
        this.setState({data});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {data} = this.state;

        console.log(data);
    
        await fetch('/items/' + (data.id ? 'update/' : ''), {
            // method: (data.id) ? 'PUT' : 'POST',
            method: (data.id) ? 'POST' : 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        });
        this.props.history.push('/items');
    }

    render() {
        const {data} = this.state;
        const title = <h2>{data.id ? 'Edit Item' : 'Add Item'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="desc">Description</Label>
                        <Input type="text" name="desc" id="desc" value={data.desc || ''}
                               onChange={this.handleChange} autoComplete="desc"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="price">Price</Label>
                        <Input type="text" name="price" id="price" value={data.price || ''}
                               onChange={this.handleChange} autoComplete="price"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/items">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(ItemEdit);