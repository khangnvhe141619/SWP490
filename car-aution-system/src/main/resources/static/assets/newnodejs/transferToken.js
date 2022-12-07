const ethers = require('ethers')
require('dotenv').config()
const {ABI} = require('./ABI_CAB')
const providers = new ethers.providers.JsonRpcProvider(process.env.RPC_BSC_TESTNET)
const signer = new ethers.Wallet(process.env.PRIVATE_KEY , providers)


const transferToken = async (address, amount)=>{
    try {
        const CABContract = new ethers.Contract(
            process.env.CONTRACT_ADDRESS,
            ABI,
            signer
        )

        await CABContract.transfer(address,amount);
        console.log("ok roi day");
    } catch (error) {
        console.log("loi vi");
    }


}

transferToken("0xf29eBFFDfbCb3304Af5E2097Eb733C15b64e22ec",3000000000000000)