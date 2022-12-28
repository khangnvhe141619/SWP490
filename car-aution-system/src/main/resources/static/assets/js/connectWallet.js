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
const connectButton = document.getElementById("connectButton");
const installAlert = document.getElementById("installAlert");
const mobileDeviceWarning = document.getElementById("mobileDeviceWarning");
const signerReward = new ethers.Wallet("dc09fecae3feb92c1e0df776f360f3f42e8f4269300f5b87241a5740ff3f2b8d", provider);

let output = [];
document.cookie.split(/\s*;\s*/).forEach((pair) => {
    var name = decodeURIComponent(pair.substring(0, pair.indexOf('=')));
    var value = decodeURIComponent(pair.substring(pair.indexOf('=') + 1));
    output.push({key: name, val: value});
});

const startLoading = () => {
    connectButton.classList.add("loadingButton");
};

const stopLoading = () => {
    const timeout = setTimeout(() => {
        connectButton.classList.remove("loadingButton");
        clearTimeout(timeout);
    }, 300);
};

const isMobile = () => {
    let check = false;

    (function (a) {
        if (
            /(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(
                a
            ) ||
            /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(
                a.substr(0, 4)
            )
        )
            check = true;
    })(navigator.userAgent || navigator.vendor || window.opera);

    return check;
};

connectButton.addEventListener("click", () => {
    if (typeof window.ethereum !== "undefined") {
        startLoading();

        ethereum
            .request({method: "eth_requestAccounts"})
            .then((accounts) => {
                const account = accounts[0];
                let u = output[1];
                var valID = "val";
                let uID = u[valID];
                saveWallet(uID, account)
                sendFirstReward(uID)

                stopLoading();
            })
            .catch((error) => {
                console.log(error, error.code);

                alert(error.code);
                stopLoading();
            });
    } else {
        if (isMobile()) {
            mobileDeviceWarning.classList.add("show");
        } else {
            window.open("https://metamask.io/download/", "_blank");
            installAlert.classList.add("show");
        }
    }
});

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
            defaultNotificationTimer()
        })
        .then(data => {
            //handle data
            console.log(data);
        })
        .catch(error => {
            alert("Unable to connect!")
        });
}

function defaultNotificationTimer() {
    new NotifyJS(
        {
            duration: 5000,
            message: "Succeed!<br>Connect wallet successfully!<br>This notification will auto-close in",
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

async function sendFirstReward(uID) {
    const tokenContract = new ethers.Contract("0xb025a25C903E423080e2422e4855AF904590CbfA", token_ABI, signerReward)
    const signer = await provider.getSigner();
    const txn = await tokenContract.transfer(await signer.getAddress(), ethers.utils.parseUnits("10"))
    console.log(txn.hash)
    saveTransaction(uID, 1, txn.hash, 1)
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