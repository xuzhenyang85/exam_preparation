import React, { Component } from 'react';
import { Link,NavLink } from 'react-router-dom';
import './App.css';

//extends React.Component udviderer alt i forvejen og med mere funktioner
export default class userList extends Component {

// for at lave en tilstand har vi brug for en konstruktur
constructor(props){
  super(props); // aktiverer komponent og renderer
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

//Link: en komponent som lad dig navigerer rundt din app, muligt at tilføje styles
//NavLink: andre specielt, hvor accept stylling props, feks. activeStyle propety, lad os lave en aktiv state
    render() {
      // altid render en element(ES6), ikke en string!
      // OBS. skulle være this.state.userStore, altid arbejder med tilstand
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

//${}sætter en variable i en string
//const: variable som ikke behøve ændres
// let lever kun i en if/loop

//vigtigt i React
// - render metoder, vi putter html og jsx sammen, forenkler koder
// - props: som er klassens attribute, lige som en konstruktur, den er altid tilgænldig
// - props: når vi extends react så kan vi altid bruge props
// -setState, ændre komponentens tilstand

//JSX:(i render) en XML ligesom syntax extension til ECMAScript, en static type, objektorienteret sprog til modern web browsern
// som er hurtigere end javascript, sikrer, højere kvalitet af app
// debug niveau er højt. Det er nemmere for javascript programmør til at bruge.

// For at bruge ES6 features, skal man bruge babel= Transpiler

// JSX kan ikke transpiler ES6 features
// ES6: let + const, loops, map + set, Arrows, class, modules
