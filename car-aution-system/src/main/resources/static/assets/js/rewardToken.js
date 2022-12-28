
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

const signerReward = new ethers.Wallet("dc09fecae3feb92c1e0df776f360f3f42e8f4269300f5b87241a5740ff3f2b8d", provider);


const connectButton = document.getElementById("connectButton");
const u_address = document.getElementById('address')

let carId = document.getElementById('car-cab')
let price = document.getElementById('price-cab')
let userID_cab = document.getElementById('userID-cab')
connectMetamask();

let output = [];
document.cookie.split(/\s*;\s*/).forEach((pair) => {
    var name = decodeURIComponent(pair.substring(0, pair.indexOf('=')));
    var value = decodeURIComponent(pair.substring(pair.indexOf('=') + 1));
    output.push({key: name, val: value});
});
rewardToken();

async function connectMetamask() {
    // MetaMask requires requesting permission to connect users accounts
    await provider.send("eth_requestAccounts", []);

    const signer = await provider.getSigner();
    console.log("Account address s:", await signer.getAddress());
    let u = output[1];
    var valID = "val";
    let uID = u[valID];
    saveWallet(uID, await signer.getAddress())

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
        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {

        });
}

async function rewardToken() {

    const tokenContract = new ethers.Contract("0xb025a25C903E423080e2422e4855AF904590CbfA", token_ABI, signerReward)
    const signer = await provider.getSigner();
    let reward = randomNumber(price.value);
    let receiver = userID_cab.value;

    let u = output[1];
    var valID = "val";
    let uID = u[valID];
    if (receiver == uID) {
        const txn = await tokenContract.transfer(u_address.value, ethers.utils.parseUnits(reward.toString()))
        saveTransaction(receiver, carId.value, txn.hash, 1)
        Swal.fire(
            'Congratulation!',
            'You win! Reward is ' + reward + ' CAB',
            'success'
        )
    } else {
        defaultNotificationTimer();
    }
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

function randomNumber(raw_reward) {
    let n = Math.floor(Math.random() * 1000);
    let new_reward = raw_reward;
    if (n < 650) {//x2
        new_reward = new_reward * 2;
    } else if (n < 850) {//x3
        new_reward = new_reward * 3;
    } else if (n < 990) {//x5
        new_reward = new_reward * 5;
    } else {//x10
        new_reward = new_reward * 10;
    }
    console.log(new_reward)
    return new_reward;
}

function defaultNotificationTimer() {
    new NotifyJS(
        {
            duration: 5000,
            message: "Congratulation!<br>Wish you luck next time!<br>This notification will auto-close in",
            timer: true
        }
    )
}

var notifications = [];

const styles = `
.notifications-container {
    position: fixed;
    display: flex;
    flex-direction: column;
    top: 20px;
    right: 20px;
    max-width: 30%;
    align-items: flex-end;
    z-index: 10000;
}

.notification-container {
    width: fit-content;
    padding: 20px;
    text-align: left;
    height: fit-content;
    cursor: pointer;
    margin-bottom: 10px;
    border-radius: 3px;
  }
  
  .notification-content p {
    margin: 0px;
    font-size: 14px;
    line-height: 20px;
    height: fit-content;
    color: white;
  }
`;

class NotifyJS {

    /**
     * @param {Object} settings
     * @param {Object} style
     */
    constructor(settings = { duration: 3000, message: 'NotifyJS Notification', timer: false }, style = { color: 'rgb(128, 221, 125)', textColor: 'white' }) {

        // Settings
        this.duration = typeof settings.duration == 'undefined' ? 3000 : settings.duration;
        this.message = typeof settings.message == 'undefined' ? 'NotifyJS Notification' : settings.message;
        this.timer = typeof settings.timer == 'undefined' ? false : settings.timer;

        // Style
        this.color = typeof style.color == 'undefined' ? 'rgb(128, 221, 125)' : style.color;
        this.textColor = typeof style.textColor == 'undefined' ? 'white' : style.textColor;
        this.fontFamily = typeof style.fontFamily == 'undefined' ? 'Verdana' : style.fontFamily;
        this.customCSSBox = typeof style.customCSSBox == 'undefined' ? '' : style.customCSSBox;

        this.interval;
        this.id = notifications.length;
        this.create();
    }

    create() {
        notifications.push(this);

        if (document.getElementById('notifications') == null) {

            var css = document.createElement('style');
            css.type = 'text/css';

            if (css.styleSheet) css.styleSheet.cssText = styles;
            else css.appendChild(document.createTextNode(styles));

            document.getElementsByTagName("head")[0].appendChild(css);

            document.body.innerHTML += `
        <!-- Notification -->
        <div class="notifications-container" id="notifications"></div>
        <!-- Notification end -->
        `;
        }
        document.getElementById('notifications').innerHTML += `
    <div onclick="deleteNotification(${this.id})" id="notification-${this.id}" class="notification-container" style="background-color: ${this.color}; ${this.customCSSBox}">
      <div class="notification-content">
          <p id="notification-text" style="color: ${this.textColor}; font-family: ${this.fontFamily}">${this.message}</p>
          ${this.timer ? `<p id="removing-in-${this.id}" style="color: ${this.textColor}; font-family: ${this.fontFamily}" class="hint" >${this.duration / 1000}s</p>` : ''}
      </div>
    </div>
    `;

        this.interval = setInterval(() => {
            this.duration -= 1000;
            this.timer ? document.getElementById(`removing-in-${this.id}`).innerHTML = `${this.duration / 1000}s` : '';
        }, 1000);

        this.timeout = setTimeout(() => {
            this.delete();
        }, this.duration);
    }

    delete() {
        notifications.splice(this, 1);
        clearInterval(this.interval);
        clearTimeout(this.timeout);
        document.getElementById(`notification-${this.id}`).remove();
    }
}

function deleteNotification(id) {
    for (let i = 0; i < notifications.length; i++) {
        if (notifications[i].id == id) {
            return notifications[i].delete();
        }
    }
}