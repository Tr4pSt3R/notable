import React, { Component } from 'react';
import './App.css';
import Notelist from './components/NoteList';

class App extends Component {
  render() {
    return (
        <div className="App">
          <header className="App-header">
            <h1 className="App-title">
              Notability
            </h1>
          </header>
          <Notelist />
        </div>
    );
  }
}

export default App;
