import React, {useState, useEffect} from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import NotFound from './pages/NotFound';
import Welcome from './pages/Welcome';
import MyAccount from './pages/MyAccount';
import jwt from 'jwt-decode';
import Cookies from 'js-cookie';


// import Contact from './pages/contact';

function App() {

  const [roles, setRoles] = useState([]);
  const user = Cookies.get("user");

  useEffect(() => {
    setRoles(getRolesFromJWT());
  }, [user]);

  function getRolesFromJWT() {
    if (user) {
      const decodedJwt = jwt(user);
      return decodedJwt.authorities;
    }
    return [];
  }

  console.log(roles);
  
  return (
    
    <Routes>
        <Route exact path='/'  element={
          (roles[0] === "BASIC_USER") ? (<Home/>) : (<Welcome/>)
          } />
        <Route exact path='/login' element={<Login/>} />
        <Route exact path='/register' element={<Register/>} />
        
         <Route exact path='/home' element = {<Home/>}/> 
         <Route exact path='/myaccount' element = {<MyAccount/>}/>
        
        <Route exact path='/welcome' element={<Welcome/>} />
        <Route path="*" element={<NotFound/>} />
    </Routes>
  );
}

export default App;
