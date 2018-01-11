import React from 'react';//brug ES6 modules. og udviderer react funktioner
import ReactDOM from 'react-dom'; //Den pakke server indgang til den DOM-relateret rendering stier.
import './index.css';
import App from './App'; //component

ReactDOM.render(<App />, document.getElementById('root'));

//I react DOM starter altid renter index.js, i index.js render vi App komponent
// sætter root div ind i index.html
// der må kun være en komponent, hvis render metoden indeholder 2 divs fejler koden
