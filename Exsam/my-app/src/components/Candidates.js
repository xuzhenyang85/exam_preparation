import React, { Component } from 'react';
import { Link } from 'react-router-dom';

//extends React.Component udviderer alt i forvejen og med mere funktioner
export default class Candidate extends Component {

  constructor(props){
    super(props); // aktiverer komponent og renderer
    this.state = { data: [] };
    console.log(props);
  };
    componentWillMount(){

      let userStore = this.props.userStore;
      this.setState({userStore,userStore});

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
  render() {

      return (
        <div className="App">
          <h1>Candidates</h1>
            {this.state.data}

          <br />
          <Link to="/">Home</Link><br />
        </div>
      );
    }
}
