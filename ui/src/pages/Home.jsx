import React from "react";
import "../css/home.css";
import Button from "@mui/material/Button";
import Navbar from "../navbar/Navbar";
import "react-alice-carousel/lib/alice-carousel.css";
import FeaturedWeek from "../dynamic/FeaturedWeek.jsx";

function Home() {

  return (
    <div id="homeContainer">
      <Navbar />
      <div id="greetingContainer">
        <FeaturedWeek/>
      </div>
       <div id="blindPickContainer">
        <div id="blindCoverPhoto" />
        <div id="blindTitle">Can't decide over all that good reads?</div>
        <Button
          variant="outlined"
          sx={{
            width: "40%",
            position: "absolute",
            fontSize: "20px",
            bottom: "40%",
            left: "28%",
            color: "white",
            borderColor: "white",
            zIndex: "11",
          }}
        >
          Blind pick a book!
        </Button>
      </div>
     {/* <div id="seeABookContainer">
        <video id="seeABookVideo" preload="auto" autoPlay={"true"} muted loop>
          <source src={require("../drawable/fire.mp4")} type="video/mp4" />
        </video>

        <div id="newEntriesText">New in town</div>
        <div id="newEntriesContainer">
          <AliceCarousel
            id="topPicsCarousel"
            responsive={responsive}
            autoHeight
            disableButtonsControls
            disableDotsControls
            mouseTracking
            items={lastBooks}
          />
        </div>

        <div id="topPicsText">Here are some top pics</div>
        <div id="topPicsContainer">
          <AliceCarousel
            id="topPicsCarousel"
            responsive={responsive}
            autoHeight
            disableButtonsControls
            disableDotsControls
            mouseTracking
            items={items}
          />
        </div>
      </div> */}
    </div>
  );
}

export default Home;
