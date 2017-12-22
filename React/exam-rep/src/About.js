import React from 'react';

export default class About extends React.Component {
  constructor(props) {
    super(props);
    this.state = { "book": { title: "", info: "" }, isDirty: false }
  }

  render (){
    return (
      <div>
        <h2>About</h2>
      </div>
    )
  }
}
