
import './App.css';
import { BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import NotFound from './pages/NotFound';
import Welcome from './pages/Welcome';
import PrivateRoute from './private/PrivateRoute';
import { useEffect } from 'react';
import { useLocalState } from './util/UseLocalState';
// import Contact from './pages/contact';

function App() {

  const [jwt, setJwt] = useLocalState("","jwt");

  const requestBody = {
    username: "cuciula31",
    password: "123456789",
  };

    fetch("api/auth/login" , {
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
      },
      method: "post",
      body: JSON.stringify(requestBody),
    })
    .then((response)=> Promise.all(
      [response.json(), response.headers]))
    
    .then(([body, headers])=> headers.forEach((element) =>console.log(element)));
    


  return (
    <Router>
    <Routes>
        <Route exact path='/'  element={<Home/>} />
        <Route exact path='/login' element={<Login/>} />
        <Route exact path='/register' element={<Register/>} />
        
        <Route exact path='/home' element={<Home/>} />
        
        <Route exact path='/welcome' element={<Welcome/>} />
        <Route path="*" element={<NotFound/>} />

    </Routes>
    </Router>
  );
}

export default App;
