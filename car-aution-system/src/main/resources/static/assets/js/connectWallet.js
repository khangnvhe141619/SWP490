import { ethers } from "ethers";
import {
    useAccount,
    useConnect,
    useDisconnect,
} from 'wagmi'
import { MetaMaskConnector } from 'wagmi/connectors/metaMask'
const provider = new ethers.providers.Web3Provider(window.ethereum);

    const connectButton = document.getElementById("connectButton");

    connectButton.addEventListener("click", () => {
        const { address, isConnected } = useAccount();
        const { connect } = useConnect({
            connector: new MetaMaskConnector(),
        })
        const { disconnect } = useDisconnect();
        const connectwalletHandler = async () => {
            let signer = await provider.getSigner();
            let userAddress =await signer.getAddress();
            connect();
        };
        const disconnectwalletHandler = async () => {
            if (isConnected) {
                disconnect();
            }
        };
    })


