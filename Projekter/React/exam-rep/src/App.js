import React from 'react'
import {
  BrowserRouter as Router,
  Route,
  NavLink,
  Switch,
} from 'react-router-dom'
import About from './About';
import Repositories from './Repositories';

const Home = () => (
  <div>
    <h2>Home</h2>
    <p>Welcome to Xu Zhen Yangs React Page</p>
  </div>
)

const App = () => (
  <Router>
    <div>
      <div>
        <ul className="header">
          <li><NavLink exact to="/">Home</NavLink></li>
          <li><NavLink to="/repositories">Reposistories</NavLink></li>
          <li><NavLink to="/about">About</NavLink></li>
        </ul>
      </div>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/about" component={About} />
        <Route path="/Repositories" component={Repositories} />
      </Switch>
    </div>
  </Router>
)
export default App
