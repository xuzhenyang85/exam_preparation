import React, { Component } from 'react';
import { Link,NavLink } from 'react-router-dom';
import './App.css';

export default class userList extends Component {

constructor(props){
  super(props);
  this.state = { userStore: props.userStore };
  console.log("props", props.userStore);
};

componentWillMount(){
  let userStore = this.props.userStore;
  this.setState({userStore,userStore});
  /*fetch('https://jsonplaceholder.typicode.com/users') // lav request til dette API
    .then( ( res ) => {
      return res.json(); // return 1. promis og lav om til json
    })
    .then( (json) => {
      let users = json.map( (user) => { // 2. promis, map til ny array med de info vi bestemmer
        return (
          <li key = {user.id}>
            ID: {user.id} -
            Name: {user.name} -
            Username: {user.username} -
            Email: {user.email} -
            <NavLink to={`/details/${user.id}`}>Details</NavLink>
          </li>
        )
      })
      this.setState({users: users}); // setState med den nye array
      console.log("state: ",this.state.users);
    })
    .catch( (err) => {
      console.log(err);
      alert("Can not get data from the server");
    });*/
}

    render() {
      const users = this.props.userStore._users;

      console.log("users ",users);
      return (
        <div className="App">
          <h1>All Users</h1>
          <ul>
              {users.map( (user) =>
                <li key={user.first}>
                  <img src={user.picture.large} />
                  <p>Name: {user.first} - {user.last}</p>
                  <Link to={`/details/${user.first}`} > Details </Link><br /><br />
                </li>
              )}
          </ul>
          <Link to="/">Home</Link>
        </div>
      );
    }
}
