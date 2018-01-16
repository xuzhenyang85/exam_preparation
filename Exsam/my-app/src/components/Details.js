import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Details extends Component {
  constructor(props){
    super(props); // aktiverer komponent og renderer
    this.state = { data: [] };
  };
  componentWillMount(){
      fetch("https://jsonplaceholder.typicode.com/users")
      .then( (res) => {
        return res.json(); // får vores 1. promis her, og parsing body text til json, det kan være an object, a string, a number
      })
      .then( (json) => {
        let users = json.map((user) =>{
          return (
            <li key = {user.id} >
            {user.id} - {user.name} - {user.email} - {user.phone}
            </li>
          )
        })
        this.setState({data:users})
      })
      .catch(error => console.log("fejl i fetching getCountries",error))
  }
  render () {
    let first  = this.props.match.params.id;
    let user = this.props.userStore._users;
    console.log(user);
    console.log(user);
    return (
      <div className="App">
        <h4>Details for</h4>
        {this.state.data}
        <br />
        <Link to="/candidates">Candidates</Link><br />
      </div>
    )
  }
}

export default Details;
