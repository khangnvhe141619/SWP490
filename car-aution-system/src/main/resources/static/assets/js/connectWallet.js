import { ethers } from "ethers";

const provider = new ethers.providers.Web3Provider(window.ethereum);

const connectButton = document.getElementById("connectButton");

connectButton.addEventListener("click", () => {
    connect();
})