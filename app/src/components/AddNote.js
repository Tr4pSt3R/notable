import React from 'react';

class AddNote extends React.Component {
  constructor(props) {
    super(props);
    this.state = { id: '', content: ''};
  }

  handleChange = (event) => {
    this.setState(
        { [event.target.name]: event.target.value }
    );
  };

  handleSubmit = (event) => {
    event.preventDefault();
    let newNote = { id: this.state.id, content: this.state.content };
    this.props.addNote(newNote);
  };

  render() {
    return(
      <div className="Add-note">
        <h2>Add new note</h2>
        <form>
          <input type="text" placeholder="Enter id" name="id" onChange={this.handleChange}/>
          <input type="text" placeholder="Enter note" name="content" onChange={this.handleChange}/>
          <button id="btn__add-note" onClick={this.handleSubmit}>Save</button>
        </form>
      </div>
    );
  }
}

export default AddNote;
