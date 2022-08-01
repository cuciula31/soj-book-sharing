import React from "react";
import '../css/home.css';
import Button from "@mui/material/Button";
import Cookies from "js-cookie";
import jwt from "jwt-decode";
import Navbar from "../navbar/Navbar";
import ListElement from "../dynamic/ListElement";
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';

function Home(){

 const user = jwt(Cookies.get("user"));
 console.log(user);
 const handleDragStart = (e) => e.preventDefault();

 const items = [
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>,
<div style={{backgroundColor:"red", width: "100px", height:"100px"}}/>

 ];

 const responsive = {
    1024: { items: 15 },
};

    return (
    <div id="homeContainer">
        <Navbar/>
        <div id="greetingContainer">
            <div id="greetingPhotoContainer"></div>
            <div id="greetingPhotoBlurContainer"></div>
            <div id="greetingText">Are you ready to be part of a new story?</div>
        </div>
        <div id="blindPickContainer">
            <div id="blindCoverPhoto"/>
            <div id="blindTitle">Can't decide over all that good reads?</div>
            <Button variant="outlined" sx={{
                width:"40%",
                position:"absolute",
                fontSize:"20px",
                bottom:"40%",
                left: "28%",
                color: "white",
                borderColor: "white",
                zIndex: "11"
            }}>Blind pick a book!</Button>
        </div>
        <div id="seeABookContainer">
            <video  id="seeABookVideo" preload = "auto" autoPlay = {"true"} muted loop>
                {/* <source src={require('../drawable/fire.webm')} type="video/xwebm" /> */}
                <source src={require('../drawable/fire.mp4')} type="video/mp4" />

            </video>

            <div id="newEntriesText">New in town</div>
            <div id="newEntriesContainer">
                <div id="containerBackground"></div>
            </div>

            <div id="topPicsText">Here are some top pics</div>
            {/* <div id="topPicsContainer">
            

            </div> */}
            <AliceCarousel id = "carousel" responsive={responsive} autoHeight autoPlay  controlsStrategy="alternate" mouseTracking items={items}/>
        </div>
    </div>
    );
}

export default Home;