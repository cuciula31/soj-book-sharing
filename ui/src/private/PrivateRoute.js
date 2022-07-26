import React from 'react';
import { Navigate } from 'react-router-dom';
import {useLocalState} from '../util/UseLocalState';

const PrivateRoute = ({children}) => {
const [jwt,setJwt] = useLocalState("", "jwt");
return jwt ? children : <Navigate to="/welcome"/>
};

export default PrivateRoute;