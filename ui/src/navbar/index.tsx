import React, {useState} from "react";
import IconButton from '@mui/material/IconButton';
import AccountCircle from '@mui/icons-material/AccountCircle'
import { Nav, NavLink, NavMenu }  from "./NavbarElements.js";
import Menu from '@mui/material/Menu';
import { MenuItem } from "@mui/material";

   
  

function Navbar()  {

 const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
 const open = Boolean(anchorEl);

  const handleMenu  = (event: React.MouseEvent<HTMLElement>) =>{
  setAnchorEl(event.currentTarget);
  }
  const handleClose = () => {
    setAnchorEl(null);
  }

  return (
    <>
      <Nav>
        <NavMenu>
          <NavLink to="/home" > 
            Home
          </NavLink>
          <NavLink to="/books" >
            Books
          </NavLink>
          
          
          <div>
            <IconButton 
            aria-controls="menu-appbar"
            aria-haspopup = "true"
            onClick={handleMenu}
            sx = {{color:"white"}}
            >
              <AccountCircle/>
            </IconButton>
            <Menu id = "menu-appbar" 
            anchorEl = {anchorEl}
            anchorOrigin = {{
              vertical:"top",
              horizontal: "right",
            }}
            keepMounted
            transformOrigin={{
              vertical:"top",
              horizontal:"right"
            }}
           open = {open}
           onClose = {handleClose} >
            <MenuItem  onClick={handleClose} href = "/login"  component = "a">Login</MenuItem>
            <MenuItem onClick={handleClose} href = "/register" component = "a">Register</MenuItem>
           </Menu>
          </div>
        </NavMenu>
        
      </Nav>
      
    </>
  );
};
  
export default Navbar;