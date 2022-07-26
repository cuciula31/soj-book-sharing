import React from "react";

import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { ButtonGroup } from "@mui/material";
import styled from "styled-components";
import '../css/login.css';



const StyledTextField = styled(TextField)`
  background: transparent;
  & label.Mui-focused {
    color: white;
  }
  & label {
    color: white;
  }
  & .MuiInput-underline:after {
    border-bottom-color: white;
  }
  & .MuiOutlinedInput-input{
    color:white;
  }
  & .MuiOutlinedInput-root {
    & fieldset {
      border-color: white;
    }
    &:hover fieldset {
      border-color: white;
    }
    &.Mui-focused fieldset {
      border-color: white;
      text-color: white;

    }
  }
`;

const loginButtonSx = {
    boxShadow: 3,
    backgroundColor: "green",
    left: "15%", 
    top: "50%",
     width: "30%",
     position:"absolute",
    "&:hover": {
        backgroundColor: "green",
      boxShadow: 8,
    },
  };

  const registerButtonSx = {
    boxShadow: 3,
    backgroundColor: "red",
    left: "54%", 
    top: "50%",
     width: "30%",
     position:"absolute",
    "&:hover": {
        backgroundColor: "red",
      boxShadow: 8,
    },
  };

function Login(){
    return (
    
    <div id="pageContainer">
        <div id = "loginPhoto">
            <div id="photoContainer"></div>
            <div id="loginText">“When life itself seems lunatic, who knows where madness lies? Perhaps to be too practical is madness. To surrender dreams — this may be madness. Too much sanity may be madness — and maddest of all: to see life as it is, and not as it should be!”</div>
        <div id="authorText">― Miguel de Cervantes Saavedra, Don Quixote</div>
        </div>
        <div id="fieldsContainer">
        
            <StyledTextField id="user"  label="Username" variant="outlined" required  sx={{left: "15%", top: "30%", width: "70%",position:"absolute"}}  />
            <StyledTextField id="password" label="Password" variant="outlined" type={"password"} required hidden sx={{left: "15%", top: "40%", width: "70%",position:"absolute"}}  />
            <ButtonGroup variant="contained">
            <Button variant="contained" sx={loginButtonSx}>Log in
            </Button> 
            <Button href="/register" variant="contained" sx={registerButtonSx}>Register</Button>
            </ButtonGroup>

            <div id="loginWelcome">AHOY!</div>
            
        </div>
    </div>
    );
}

export default Login;