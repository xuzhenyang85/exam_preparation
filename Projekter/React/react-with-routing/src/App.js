import React from 'react';
import './App.css';
import { HashRouter as Router, Route, Link, Switch } from "react-router-dom";
import All from './UserList';
import Details from './Details';
import userStore from './UserStore';

const Welcome = () => {
  return (
    <div className="App">
      <h1 className="greeting" >Welcome to our friends site</h1>
      <Link to="/all">See all users</Link><br />
    </div>
)}

const App = () => (
        <Router>
          <Switch>
            <Route exact path="/" component={Welcome} />
            <Route path="/all" render={ (props) => { return (<All {...props} userStore={userStore} />)}} />
            <Route path={`/details/:id`} render={ (props) => { return (<Details {...props} userStore={userStore} />)}} />
          </Switch>
        </Router>
)


export default App;
