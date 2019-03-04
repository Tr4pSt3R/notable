import React from 'react';

class EditNote extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: props.currentNote.id,
      content: props.currentNote.content
    }
  }

  handleChange = (event) => {
    const { name, value } = event.target;

    console.log(`Name: ${name}`);
    console.log(`Value: ${value}`);
    console.log(this.state.note);

    this.setState(
      { [event.target.name]: event.target.value }
    );
  };

  handleSubmit = (event) => {
    event.preventDefault();

    const note = { id: this.state.id, content: this.state.content };

    this.props.updateNote(note);
    this.props.fetchNotes();
    this.props.finishedEditing();
  };

  render() {
    return (
        <>
          <h1>Edit Note</h1>
          <form>
            <input type="text"
                   placeholder="Enter id"
                   name="id"
                   value={this.state.id}
                   onChange={this.handleChange} />
            <input type="text"
                   placeholder="Enter note"
                   name="content"
                   value={this.state.content}
                   onChange={this.handleChange} />
            <button id="btn__update-note"
                   onClick={this.handleSubmit}>Update</button>
          </form>
        </>
    );
  }
}

export default EditNote;