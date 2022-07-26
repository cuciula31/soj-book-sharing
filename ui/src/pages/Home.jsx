import React from "react";
import '../css/home.css';
import Button from "@mui/material/Button";


function Home(){
    return (
    <div id="homeContainer">
        <div id="welcomeContainer">
            <div id="welcomePhotoContainer"></div>
            <div id="welcomePhotoBlurContainer"></div>
            <div id="welcomeText">Are you ready to be part of a new story?</div>
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
            <div id="topPicsContainer">
            <div id="containerBackground"></div>
            </div>
        </div>
    </div>
    );
}

export default Home;