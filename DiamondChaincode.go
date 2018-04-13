package Diamond

import (
"fmt"
"time"

"github.com/hyperledger/fabric/core/chaincode/shim"
pb "github.com/hyperledger/fabric/protos/peer"
	"strconv"
	"encoding/json"
)

// Diamond implements a simple chaincode to manage an asset
type DiamondChaincode struct {}

type userInfo struct {
	name string
	ID string
}

type Diamond struct {
	date time.Time
	number int
	shapeAndCut string
	measurement [3]float64
	carat float64
	color string
	clarity string
	cut string
	tableSize int
	totalDepth float64
	girdleThickness [2]string
	laserInscription string
	userInfo userInfo
	checkTheft bool
}

// set Diamond data
func setDiamondInfo(aUserInfo userInfo, aDate time.Time, aNumber int, aShapeAndCut string, aMeasurement [3]float64, aCarat float64, aColor string, aClarity string, aCut string, aTableSize int, aTotalDepth float64, aGirdleThickness [2]string, aLaserInscription string, aCheckTheft bool) Diamond {
	rDiamond := Diamond{userInfo: aUserInfo, date: aDate, number: aNumber, shapeAndCut: aShapeAndCut, measurement:aMeasurement, carat:aCarat, color:aColor, clarity:aClarity, cut:aCut, tableSize:aTableSize, totalDepth:aTotalDepth, girdleThickness:aGirdleThickness, laserInscription:aLaserInscription, checkTheft:aCheckTheft}
	return rDiamond
}
func setUserInfo(aName string, aID string) userInfo {
	rUserInfo := userInfo{name:aName, ID:aID}
	return rUserInfo
}
func setMeasurement(minR float64, maxR float64, height float64) [3]float64 {
	rMeasurement := [3]float64{minR, maxR, height}
	return rMeasurement
}
func setGirdleThickness(minT string, maxT string) [2]string  {
	rGirdleThickness := [2]string{minT, maxT}
	return rGirdleThickness
}

// Init is called during chaincode instantiation to initialize any
// data. Note that chaincode upgrade also calls this function to reset
// or to migrate data.
//
// argument definition :
// key
func (t *DiamondChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {
	// Get the args from the transaction proposal
	args := stub.GetStringArgs()
	if len(args) != 2 {
		return shim.Error("Incorrect arguments. Expecting a key and a value")
	}

	// Set up any variables or assets here by calling stub.PutState()

	// We store the key and the value on the ledger
	err := stub.PutState(args[0], []byte(args[1]))
	if err != nil {
		return shim.Error(fmt.Sprintf("Failed to create asset: %s", args[0]))
	}
	return shim.Success(nil)
}

// Invoke is called per transaction on the chaincode. Each transaction is
// either a 'get' or a 'set' on the asset created by Init function. The Set
// method may create a new asset by specifying a new key-value pair.
func (t *DiamondChaincode) Invoke(stub shim.ChaincodeStubInterface) pb.Response {
	// Extract the function and args from the transaction proposal
	fn, args := stub.GetFunctionAndParameters()

	var result string
	var err error
	if fn == "set" {
		result, err = set(stub, args)
	} else if fn == "get"{ // assume 'get' even if fn is nil
		result, err = get(stub, args)
	} else if fn == "query"{
		response := query(stub, args)
		return response
	} else {
		return shim.Error(err.Error())
	}
	if err != nil {
		return shim.Error(err.Error())
	}

	// Return the result as success payload
	return shim.Success([]byte(result))
}

// query modify user information of specified diamond key
//
// no argument, this function is used to init function for initialize chaincode
// or get history of key data change
func query(stub shim.ChaincodeStubInterface, args []string) pb.Response{
	if len(args) < 1 && len(args) > 2{
		return shim.Error("Incorrect arguments. Expecting a key")
	}



	var err error
	_, err = get(stub, args)
	if err != nil {
		return shim.Error("this key don't exist in ledger")
	}

	return shim.Error("this is function not defined")
}

// Set stores the asset (both key and value) on the ledger. If the key exists,
// it will override the value with the new one
// diamond information initialize
//
// argument definition :
// set username userID number shapeandcut minR maxR height carat color clarity cut tablesize totaldepth mingirdle maxgirdle laserinscription checkTheft
func set(stub shim.ChaincodeStubInterface, args []string) (string, error) {
	if len(args) != 17 {
		return "", fmt.Errorf("Incorrect arguments. Expecting a key and a value")
	}

	nUserInfo := setUserInfo(args[0], args[1])
	nMinR, err := strconv.ParseFloat(args[4], 64)
	nMaxR, err := strconv.ParseFloat(args[5], 64)
	nHeight, err := strconv.ParseFloat(args[6], 64)
	if err != nil {
		return "", fmt.Errorf("Failed to set asset: %s, %s, %s", args[4], args[5], args[6])
	}
	nMeasurement := setMeasurement(nMinR, nMaxR, nHeight)
	nGirdleThickness := setGirdleThickness(args[13], args[14])
	if err != nil {
		return "", fmt.Errorf("Failed to set asset: %s, %s", args[13], args[14])
	}

	nNumber, err := strconv.Atoi(args[2])
	ntableSize, err := strconv.Atoi(args[11])
	ntableDepth, err := strconv.ParseFloat(args[12], 64)
	if err != nil {
		return "", fmt.Errorf("Failed to set asset: %s, %s, %s", args[2], args[11], args[12])
	}

	nCarat, err := strconv.ParseFloat(args[7], 64)
	nCheckTheft, err := strconv.ParseBool(args[16])

	nDiamond := setDiamondInfo(nUserInfo, time.Now(), nNumber, args[3], nMeasurement, nCarat, args[8], args[9], args[10], ntableSize, ntableDepth, nGirdleThickness, args[15], nCheckTheft)

	bDiamond, err := json.Marshal(nDiamond)
	if err != nil {
		return "", fmt.Errorf("Failed to convert struct to byte")
	}

	err = stub.PutState(args[0], []byte(string(bDiamond)))
	if err != nil {
		return "", fmt.Errorf("Failed to set asset: %s", args[0])
	}

	return string(bDiamond), nil
}

// Get returns the value of the specified asset key
//
// argument definition :
// get specifiedKey
func get(stub shim.ChaincodeStubInterface, args []string) (string, error) {
	if len(args) != 1 {
		return "", fmt.Errorf("Incorrect arguments. Expecting a key")
	}

	value, err := stub.GetState(args[0])
	if err != nil {
		return "", fmt.Errorf("Failed to get asset: %s with error: %s", args[0], err)
	}
	if value == nil {
		return "", fmt.Errorf("Asset not found: %s", args[0])
	}
	return string(value), nil
}


// main function starts up the chaincode in the container during instantiate
func main() {
	if err := shim.Start(new(DiamondChaincode)); err != nil {
		fmt.Printf("Error starting SimpleAsset chaincode: %s", err)
	}
}