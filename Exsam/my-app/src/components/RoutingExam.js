import React from 'react'; // ES6 modules, features
//DOM forbinder med React Router, som en kollektion af navigation komponenter
//Forskellen med react-router og react-router-dom er, browseren skal selv køre router
//Slogen er "Learn once, route anywhere"
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom"; // BrowserRouter er til dynamisk request, hash bruger til static websiden
import Candidates from './Candidates';
import Municipalities from './Municipalities';
import Details from './Details';
import userStore from './UserStore'; // En komponent med data af en user list

// statelses funktion components i ES6
// ingen class need, kun en funktion, NO "This" keyword
// ingen state, lifecycle metoder, beskyt fra dovnskab, mindre kode, nemt at teste, nemt at forstå
const Home = () => {
  return (
    <div className="App">
      <Link to="/">Home</Link><br />
      <Link to="/candidates">Candidate</Link><br />
      <Link to="/municipalities">Municipalities</Link><br />
      <h1>These are the candidates</h1>

    </div>
)}


// Router: i react man skal wrap <Route> ind i <Router>
// så når den URL ændres, <Router> vil matche en branch af dens route, og render deres konfigurende komponenter

// Route: er brugt til klaregør map routes til din apps komponent hierachy
// så man skal klaregøre i path som blev brugt til URL
// og i component skal den single komponent være renderes når route mathe den URL

// {...props} sprædt props
const RoutingExam = () => (
        <Router>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/candidates" render={ (props) => { return (<Candidates {...props} userStore={userStore} />)}} />
            <Route path="/municipalities" component={Municipalities} />
            <Route path={`/details/:id`} render={ (props) => { return (<Details {...props} userStore={userStore} />)}} />
          </Switch>
        </Router>
)
// props: i html kalder vi attributer, f.eks. onclick, href
// {...props} betyder alle propeties sender videre, lige som *, og forbinder alle props i jsx

export default RoutingExam;

// BrowserRouter får vores URL til at kunne ændre sig
// JWT: json web token, er en compack URL sikker, som er repræsenter request til være transformer mellem to partner


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
