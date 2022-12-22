const token_ABI = [
    {"inputs":[],"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"owner","type":"address"},{"indexed":true,"internalType":"address","name":"spender","type":"address"},{"indexed":false,"internalType":"uint256","name":"value","type":"uint256"}],"name":"Approval","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"previousOwner","type":"address"},{"indexed":true,"internalType":"address","name":"newOwner","type":"address"}],"name":"OwnershipTransferred","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"from","type":"address"},{"indexed":true,"internalType":"address","name":"to","type":"address"},{"indexed":false,"internalType":"uint256","name":"value","type":"uint256"}],"name":"Transfer","type":"event"},{"inputs":[],"name":"ETHER","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"owner","type":"address"},{"internalType":"address","name":"spender","type":"address"}],"name":"allowance","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"spender","type":"address"},{"internalType":"uint256","name":"amount","type":"uint256"}],"name":"approve","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"account","type":"address"}],"name":"balanceOf","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"decimals","outputs":[{"internalType":"uint8","name":"","type":"uint8"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"spender","type":"address"},{"internalType":"uint256","name":"subtractedValue","type":"uint256"}],"name":"decreaseAllowance","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"getOwner","outputs":[{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"spender","type":"address"},{"internalType":"uint256","name":"addedValue","type":"uint256"}],"name":"increaseAllowance","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"uint256","name":"amount","type":"uint256"}],"name":"mint","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"name","outputs":[{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"owner","outputs":[{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"renounceOwnership","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"symbol","outputs":[{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"totalSupply","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"recipient","type":"address"},{"internalType":"uint256","name":"amount","type":"uint256"}],"name":"transfer","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"sender","type":"address"},{"internalType":"address","name":"recipient","type":"address"},{"internalType":"uint256","name":"amount","type":"uint256"}],"name":"transferFrom","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"newOwner","type":"address"}],"name":"transferOwnership","outputs":[],"stateMutability":"nonpayable","type":"function"}
]

let provider = new ethers.providers.Web3Provider(window.ethereum)

const signerReward = new ethers.Wallet("dc09fecae3feb92c1e0df776f360f3f42e8f4269300f5b87241a5740ff3f2b8d", provider);


let amount = document.getElementById('amount');
const connectButton = document.getElementById("connectButton");
let balance = document.getElementById('amount-acc');

connectMetamask();
let output = [];
document.cookie.split(/\s*;\s*/).forEach((pair) => {
    var name = decodeURIComponent(pair.substring(0, pair.indexOf('=')));
    var value = decodeURIComponent(pair.substring(pair.indexOf('=') + 1));
    output.push({key: name, val: value});
});
async function connectMetamask() {
    // MetaMask requires requesting permission to connect users accounts
    await provider.send("eth_requestAccounts", []);

    signer = await provider.getSigner();
    console.log("Account address s:", await signer.getAddress());
    let u = output[1];
    var valID = "val";
    let uID = u[valID];
    saveWallet(uID, await signer.getAddress())
    balanceOf()
}

async function transferToken() {
    const tokenContract = new ethers.Contract("0xb025a25C903E423080e2422e4855AF904590CbfA",token_ABI,signerReward)
    signer = await provider.getSigner();

    await tokenContract.transfer(signer, ethers.utils.parseUnits(amount.value))
}

async function balanceOf() {
    const tokenContract = new ethers.Contract("0xb025a25C903E423080e2422e4855AF904590CbfA",token_ABI,provider.getSigner())
    signer = await provider.getSigner();

    const balanceOf = await tokenContract.balanceOf(signer.getAddress())

    balance.textContent = (Number(balanceOf.toString()) / Math.pow(10, 18)).toFixed(5)
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

function saveWallet(id, address) {
    var serverContext = getContextPath();
    fetch(serverContext + "saveWallet?setUserId=" + id + "&address=" + address, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => {
            //handle response
            connectButton.hidden = true;
            alert("Connect successfully!")
        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {

        });
}

async function rewardToken() {
    const tokenContract = new ethers.Contract("0xb025a25C903E423080e2422e4855AF904590CbfA",token_ABI,signerReward)
    signer = await provider.getSigner();
    await tokenContract.transfer(signer.getAddress(),ethers.utils.parseUnits("10"))
}