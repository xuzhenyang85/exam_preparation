import React from 'react';
import { Route, Link, NavLink } from "react-router-dom";
import Details from './Details'

const API = 'https://gist.githubusercontent.com/Thomas-Hartmann/1bab5f3b3ac8bbb6b4607db725e8ea20/raw/85fd536b2a9e2e25cac9ebcf9d2576686bfc7818/github_repos.json';
const resFromFirstPromise = null;

export default class Repositories extends React.Component {

  constructor(props) {
    super(props);
    this.state = { repos: [] }
  }

componentWillMount(){
  fetch("https://gist.githubusercontent.com/Thomas-Hartmann/1bab5f3b3ac8bbb6b4607db725e8ea20/raw/85fd536b2a9e2e25cac9ebcf9d2576686bfc7818/github_repos.json")
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            repos: result
          });
        },(error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
}

  render() {
    const { error, isLoaded, repos } = this.state;
    const match = this.props.match;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {

      if(this.state.repos){
        return (
          <ul>
            {repos.map(item => (
              <li key={item.name}>
                {item.name} <Link to={`/repository?name=${item.name}`} params={{ test: "hello" }}>Details</Link>
              </li>
            ))}
          </ul>

        );
      }else{
        return (<p>No data fetched</p>)
      }
    }
  }
}
