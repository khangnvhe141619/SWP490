
const token_ABI = [
    {"inputs": [], "stateMutability": "nonpayable", "type": "constructor"}, {
        "anonymous": false,
        "inputs": [{"indexed": true, "internalType": "address", "name": "owner", "type": "address"}, {
            "indexed": true,
            "internalType": "address",
            "name": "spender",
            "type": "address"
        }, {"indexed": false, "internalType": "uint256", "name": "value", "type": "uint256"}],
        "name": "Approval",
        "type": "event"
    }, {
        "anonymous": false,
        "inputs": [{
            "indexed": true,
            "internalType": "address",
            "name": "previousOwner",
            "type": "address"
        }, {"indexed": true, "internalType": "address", "name": "newOwner", "type": "address"}],
        "name": "OwnershipTransferred",
        "type": "event"
    }, {
        "anonymous": false,
        "inputs": [{"indexed": true, "internalType": "address", "name": "from", "type": "address"}, {
            "indexed": true,
            "internalType": "address",
            "name": "to",
            "type": "address"
        }, {"indexed": false, "internalType": "uint256", "name": "value", "type": "uint256"}],
        "name": "Transfer",
        "type": "event"
    }, {
        "inputs": [],
        "name": "ETHER",
        "outputs": [{"internalType": "uint256", "name": "", "type": "uint256"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "owner", "type": "address"}, {
            "internalType": "address",
            "name": "spender",
            "type": "address"
        }],
        "name": "allowance",
        "outputs": [{"internalType": "uint256", "name": "", "type": "uint256"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "spender", "type": "address"}, {
            "internalType": "uint256",
            "name": "amount",
            "type": "uint256"
        }],
        "name": "approve",
        "outputs": [{"internalType": "bool", "name": "", "type": "bool"}],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "account", "type": "address"}],
        "name": "balanceOf",
        "outputs": [{"internalType": "uint256", "name": "", "type": "uint256"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [],
        "name": "decimals",
        "outputs": [{"internalType": "uint8", "name": "", "type": "uint8"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "spender", "type": "address"}, {
            "internalType": "uint256",
            "name": "subtractedValue",
            "type": "uint256"
        }],
        "name": "decreaseAllowance",
        "outputs": [{"internalType": "bool", "name": "", "type": "bool"}],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [],
        "name": "getOwner",
        "outputs": [{"internalType": "address", "name": "", "type": "address"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "spender", "type": "address"}, {
            "internalType": "uint256",
            "name": "addedValue",
            "type": "uint256"
        }],
        "name": "increaseAllowance",
        "outputs": [{"internalType": "bool", "name": "", "type": "bool"}],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [{"internalType": "uint256", "name": "amount", "type": "uint256"}],
        "name": "mint",
        "outputs": [{"internalType": "bool", "name": "", "type": "bool"}],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [],
        "name": "name",
        "outputs": [{"internalType": "string", "name": "", "type": "string"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [],
        "name": "owner",
        "outputs": [{"internalType": "address", "name": "", "type": "address"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [],
        "name": "renounceOwnership",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [],
        "name": "symbol",
        "outputs": [{"internalType": "string", "name": "", "type": "string"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [],
        "name": "totalSupply",
        "outputs": [{"internalType": "uint256", "name": "", "type": "uint256"}],
        "stateMutability": "view",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "recipient", "type": "address"}, {
            "internalType": "uint256",
            "name": "amount",
            "type": "uint256"
        }],
        "name": "transfer",
        "outputs": [{"internalType": "bool", "name": "", "type": "bool"}],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "sender", "type": "address"}, {
            "internalType": "address",
            "name": "recipient",
            "type": "address"
        }, {"internalType": "uint256", "name": "amount", "type": "uint256"}],
        "name": "transferFrom",
        "outputs": [{"internalType": "bool", "name": "", "type": "bool"}],
        "stateMutability": "nonpayable",
        "type": "function"
    }, {
        "inputs": [{"internalType": "address", "name": "newOwner", "type": "address"}],
        "name": "transferOwnership",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    }
]
let provider = new ethers.providers.Web3Provider(window.ethereum)
// const signer = provider.getSigner();


let amount = document.getElementById('amount-cab');
let roomId = document.getElementById('roomId-cab');

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

    const signer = await provider.getSigner();

    console.log("Account address s:", await signer.getAddress());
}

async function transferToken(roomID, price) {

    let u = output[1];
    let uID = u["val"];
    console.log(uID)
    console.log(roomID)
    var serverContext = getContextPath();
    fetch(serverContext + "isUserInRoom?setUserId=" + uID + "&roomId=" + roomID, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(async response => {
            //handle response
            if (response.status == 200) {
                alert("You bought this ticket!")

            } else {
                const tokenContract = new ethers.Contract("0xb025a25C903E423080e2422e4855AF904590CbfA", token_ABI, provider.getSigner())
                const signer = await provider.getSigner();
                const txn = await tokenContract.transfer("0x39b6e7891C62c313730E17223fCE3B4619eD7B37", ethers.utils.parseUnits(price))
                saveBidder(uID, roomID)
                updateTicket(roomID)
                console.log(txn.hash)
                saveTransaction(uID, 2, txn.hash,1)
            }

        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {
            alert("Unable to buy!")
        });
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

function saveBidder(idUser, idRoom) {
    var serverContext = getContextPath();
    fetch(serverContext + "saveParticipant?setUserId=" + idUser + "&roomId=" + idRoom, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => {
            //handle response
            console.log(response.status)
            Swal.fire(
                'Buy successfully!',
                'You clicked the button!',
                'success'
            )

        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {
            alert("Unable to save!")
        });
}

function updateTicket(idRoom) {
    var serverContext = getContextPath();
    fetch(serverContext + "updateTicket?roomId=" + idRoom, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => {
            //handle response
            response.status;
        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {
            alert("Unable to find!")
        });
}

function isUserInRoom(roomID) {

    let u = output[1];
    let uID = u["val"];
    console.log(uID)
    console.log(roomID)
    var serverContext = getContextPath();
    fetch(serverContext + "isUserInRoom?setUserId=" + uID + "&roomId=" + roomID, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(async response => {
            //handle response
            if (response.status == 200) {
                location.href = "/auctionRoom/" + roomID;

            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'You can\'t join this room!',
                });
            }

        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {
            alert("Unable to join!")
        });
}

async function getDataChart(carId){
    var serverContext = getContextPath();
    const response = await fetch(serverContext + "showAllBidByCar?carId=" + carId);

    // Storing data in form of JSON
    var l_data = await response.json();
    console.log(l_data);
    if (response) {
        alert("load")
    }
    for(let r of l_data.data){
        console.log(r)
    }
    return l_data.data;
}

function saveTransaction(id, carId, transactionHash, status) {
    var serverContext = getContextPath();
    fetch(serverContext + "saveTransaction?setUserId=" + id + "&carId=" + carId + "&transactionHash=" + transactionHash + "&status=" + status, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    })
        .then(response => {
            //handle response
        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {

        });
}
