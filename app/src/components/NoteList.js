import React, { Component } from 'react';
import {SERVER_URL} from "../constants";
import AddNote from "./AddNote";
import {ToastContainer, toast} from "react-toastify";

class Notelist extends Component {
  constructor(props) {
    super(props);
    this.state = { allNotes: [] };
  };

  componentDidMount() {
    this.fetchNotes();
  };

  fetchNotes = () => {
    fetch(SERVER_URL+'api/notes')
    .then( (response) => response.json())
    .then((responseData) => {
      this.setState({
        allNotes: responseData
      });
    })
    .catch(err => console.log(err));
  };

  addNote(note) {
    console.log(note);
    fetch(SERVER_URL + 'api/notes', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(note)
    })
    .then(() => toast.success("Note added successfully"))
    .then((response) => this.fetchNotes())
    .catch(err => console.log(err))
  };

  render() {
    return (
        <div className="Notelist">
          { this.state.allNotes.map((note) =>
              <div key={note.id}>
                {note.content}
              </div>
          )}
          <ToastContainer/>
          <AddNote addNote={this.addNote} fetchNotes={this.fetchNotes} />
        </div>
    );
  }
}

export default Notelist;
