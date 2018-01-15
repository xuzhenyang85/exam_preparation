import React from 'react'; // ES6 modules, features
import './App.css';
//DOM forbinder med React Router, som en kollektion af navigation komponenter
//Forskellen med react-router og react-router-dom er, browseren skal selv køre router
//Slogen er "Learn once, route anywhere"
import { HashRouter as Router, Route, Link, Switch } from "react-router-dom";
import All from './UserList';
import Details from './Details';
import userStore from './UserStore'; // En komponent med data af en user list


// statelses funktion components i ES6
// ingen class need, kun en funktion, NO "This" keyword
// ingen state, lifecycle metoder, beskyt fra dovnskab, mindre kode, nemt at teste, nemt at forstå
const Welcome = () => {
  return (
    <div className="App">
      <h1 className="greeting" >Welcome to our friends site</h1>
      <Link to="/all">See all users</Link><br />
    </div>
)}

// Router: i react man skal wrap <Route> ind i <Router>
// så når den URL ændres, <Router> vil matche en branch af dens route, og render deres konfigurende komponenter

// Route: er brugt til klaregør map routes til din apps komponent hierachy
// så man skal klaregøre i path som blev brugt til URL
// og i component skal den single komponent være renderes når route mathe den URL

// {...props} sprædt props
const App = () => (
        <Router>
          <Switch>
            <Route exact path="/" component={Welcome} />
            <Route path="/all" render={ (props) => { return (<All {...props} userStore={userStore} />)}} />
            <Route path={`/details/:id`} render={ (props) => { return (<Details {...props} userStore={userStore} />)}} />
          </Switch>
        </Router>
)
// props: i html kalder vi attributer, f.eks. onclick, href
// {...props} betyder alle propeties sender videre, lige som *, og forbinder alle props i jsx

export default App;

// BrowserRouter får vores URL til at kunne ændre sig
// JWT: json web token, er en compack URL sikker, som er repræsenter request til være transformer mellem to partner
