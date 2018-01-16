import React, { Component } from 'react';
import { Link } from 'react-router-dom';

//extends React.Component udviderer alt i forvejen og med mere funktioner
export default class Municipalities extends Component {


  render() {

      return (
        <div className="App">
          <h1>Municipalities</h1>
          <p>Lorem Ipsum er ganske enkelt fyldtekst fra print- og typografiindustrien. Lorem Ipsum har været standard fyldtekst siden 1500-tallet, hvor en ukendt trykker sammensatte en tilfældig spalte for at trykke en bog til sammenligning af forskellige skrifttyper. Lorem Ipsum har ikke alene overlevet fem århundreder, men har også vundet indpas i elektronisk typografi uden væsentlige ændringer. Sætningen blev gjordt kendt i 1960'erne med lanceringen af Letraset-ark, som indeholdt afsnit med Lorem Ipsum, og senere med layoutprogrammer som Aldus PageMaker, som også indeholdt en udgave af Lorem Ipsum.</p>
          <Link to="/">Home</Link><br />
        </div>
      );
    }
}
