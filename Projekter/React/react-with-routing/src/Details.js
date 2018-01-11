import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './App.css';

class Details extends Component {

  render () {
    let first  = this.props.match.params.id;
    let user = this.props.userStore._users.filter( (user) => {
      return user.first=== (first);
    })[0];
    return (
      <div className="App">
        <h4>Details for {user.title} {user.first} {user.last}</h4>
        <img src={user.picture.large} /><br />
        <p>Gender: {user.gender}</p>
        <p>Phone: {user.phone}</p>
        <p>State: {user.state}</p>
        <p>Street: {user.street}</p>
        <p>Zip: {user.zip}</p>
        <p>City: {user.city}</p>
        <p>Email: {user.email}</p>
        <br />
        <Link to="/all">Alle users</Link>
      </div>
    )
  }
}

export default Details;
