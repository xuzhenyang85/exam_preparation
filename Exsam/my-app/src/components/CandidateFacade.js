import React, { Component } from 'react';
import { Link,NavLink } from 'react-router-dom';

//extends React.Component udviderer alt i forvejen og med mere funktioner
class DataStore {

  getData = () =>{
    fetch("https://jsonplaceholder.typicode.com/users")
    .then( (res) => {
      return res.json(); // får vores 1. promis her, og parsing body text til json, det kan være an object, a string, a number
    })
    .then( (json) => {
      let users = json.map((user) =>{
        return (
          <li key = {user.id} >
          <Link to={`/details/${user.id}`} >{user.name} </Link>
          </li>
        )
      })
      this.setState({data:users})
    })
    .catch(error => console.log("fejl i fetching getCountries",error))
  }
}

let dataStore = new DataStore();

//Only for debugging
//window.userStore = userStore;
export default dataStore;
