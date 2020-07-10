// import React, { Component }from 'react';
// import fetch from 'node-fetch'
// import _ from 'lodash'
// // import logo from './logo.svg';
// // import './App.css';

// class App extends Component{
//   constructor(props) {
//     super(props)
//     this.state = {
//       name: []
//     }
//   }

//   componentWillMount () {
//     const URL = 'http://localhost:8080/getBirthStoneList'
//     fetch(URL, {mode: 'cors'})
//     .then(res => res.json())
//     .then(data => {
//       this.setState({
//         name: data
//       })

//     });
//   }

  
//   renderStone() {
//     const namea = _.map(this.state.name,value => {
//       return(
//         <li key={value}>
//           誕生石: {value}
//         </li>
//       )
      
//     })
//     console.log(namea)
//     return namea;

//   }
 
//   render() {
//     return (
//       <ul>{this.renderStone()}</ul>
//     );
//   }
// }

// export default App;
