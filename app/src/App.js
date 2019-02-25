import React, { Component } from 'react';
import './App.css';

class App extends Component {
  state = {
    notes: []
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/notes')
    const body = await response.json();
    this.setState({ notes: body })
  }

  render() {
    return (
      <div className="App">
        <h1>Notes</h1>
        {this.state.notes.map(note =>
          <div key={note.id}>
            {note.content}
          </div>
        )}
      </div>
    );
  }
}

export default App;
