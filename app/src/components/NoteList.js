import React, {Component, Fragment} from 'react';
import {SERVER_URL} from "../constants";
import AddNote from "./AddNote";
import { ToastContainer, toast } from "react-toastify";
import EditNote from "./EditNote";

class Notelist extends Component {
  constructor(props) {
    super(props);
    this.state = { allNotes: [], editing: false, currentNote: null };
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

  fetchNote = (id) => {
    fetch(SERVER_URL + "api/note" + id)
  };

  addNote(note) {
    fetch(SERVER_URL + 'api/notes', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(note)
    })
    .then(() => toast.success("Note added successfully"))
    .then(() => this.fetchNotes())
    .catch((err) => console.log(err))
  };

  updateNote(note) {
    fetch(SERVER_URL + 'api/note/' + note.id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(note)
    })
    .then(() => toast.success("Note updated successfully"))
    .then(() => this.fetchNotes())
    .catch((err) => console.log(err))
  };

  deleteNote(value) {
    let id = value.target.id.split("__")[1];

    fetch(SERVER_URL + 'api/note/' + id, {
      method: 'DELETE'
    })
    .then( () => toast.success("Note deleted successfully"))
    .then( () => this.fetchNotes() )
    .catch( (err) => console.log(err));
  }

  editNote = (note) => {
    console.log(note);

    this.setState({editing: true, currentNote: note});
  };

  finishedEditing = () => {
    this.setState({editing: false});
  };

  render() {
    return (
        <div className="Notelist">
          {
            this.state.allNotes.map((note) =>
              <div key={note.id}>
                {note.content}
                <button id={'delete__' + note.id} onClick={ (value) => this.deleteNote(value)}>Delete</button>
                <button id={'edit__' + note.id} onClick={ () => {this.editNote(note)} }>Edit note</button>
              </div>
            )
          }
          <ToastContainer />
          {
            this.state.editing ?
                <Fragment>
                  <EditNote currentNote={this.state.currentNote}
                            updateNote={this.updateNote}
                            fetchNotes={this.fetchNotes}
                            finishedEditing={this.finishedEditing} />
                </Fragment>
                :
                <Fragment>
                  <AddNote addNote={this.addNote} fetchNotes={this.fetchNotes} />
                </Fragment>
          }
        </div>
    );
  }
}

export default Notelist;
