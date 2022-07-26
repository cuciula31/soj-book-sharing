import React from "react";
import '../css/register.css';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import styled from "styled-components";

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


  const registerButtonSx = {
    boxShadow: 3,
    backgroundColor: "green",
    left: "15%", 
    bottom: "10%",
     width: "70%",
     position:"absolute",
    "&:hover": {
        backgroundColor: "green",
      boxShadow: 8,
    },
  };

function Register(){
    return(
        <div id="registerContainer">
        <div id = "registerPhoto">
            <div id="registerPhotoContainer"></div>
            <div id="registerText">“you can't get away from yourself by moving from one place to another.”</div>
        <div id="authorText">― Ernest Hemingway, The Sun Also Rises</div>
        </div>
        <div id="fieldsContainer">
    
            <StyledTextField id="name"  label="Name" variant="outlined" required  sx={{left: "15%", top: "20%", width: "70%",position:"absolute"}} />
            <StyledTextField id="surname"  label="Surname" variant="outlined" required  sx={{left: "15%", top: "30%", width: "70%",position:"absolute"}} />
            <StyledTextField id="email"  label="Email" variant="outlined" required  sx={{left: "15%", top: "40%", width: "70%",position:"absolute"}} />
            <StyledTextField id="user"  label="Username" variant="outlined" required  sx={{left: "15%", top: "50%", width: "70%",position:"absolute"}} />
            <StyledTextField id="password" label="Password" variant="outlined" type={"password"} required hidden sx={{left: "15%", top: "60%", width: "70%",position:"absolute"}}/>
            <StyledTextField id="passwordAgain" label="Confirm password" variant="outlined" type={"password"} required hidden sx={{left: "15%", top: "70%", width: "70%",position:"absolute"}}/>
            <div id="terms">By registering you agree terms and conditions</div>
            <Button  variant="contained" sx={registerButtonSx}>Register</Button>
            

            <div id="registerWelcome">GET STARTED</div>
            
        </div>
    </div>
);
}

export default Register;