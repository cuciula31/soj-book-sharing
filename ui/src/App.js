import React, {useState, useEffect} from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import NotFound from './pages/NotFound';
import Welcome from './pages/Welcome';
import PrivateRoute from './private/PrivateRoute';
import { useUser } from './util/userProvider';
import jwt from 'jwt-decode'
import Cookies from 'js-cookie';
// import Contact from './pages/contact';

function App() {

  const [roles, setRoles] = useState([]);
  const user = Cookies.get("user");

  useEffect(() => {
    setRoles(getRolesFromJWT());
    console.log(getRolesFromJWT());
  }, [user]);

  function getRolesFromJWT() {
    if (user) {
      const decodedJwt = jwt(user);
      console.log(decodedJwt);
      return decodedJwt.authorities;
    }
    return [];
  }
  return (
    
    <Routes>
        <Route exact path='/'  element={<Home/>} />
        <Route exact path='/login' element={<Login/>} />
        <Route exact path='/register' element={<Register/>} />
        
        <Route exact path='/home' element = {<Home/>} />
        
        <Route exact path='/welcome' element={<Welcome/>} />
        <Route path="*" element={<NotFound/>} />
    </Routes>
  );
}

export default App;
