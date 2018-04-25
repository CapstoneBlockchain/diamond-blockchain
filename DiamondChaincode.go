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
	name string 					`json:"userName"`
	ID string 						`json:"userID"`
}

type measurement struct {
	minR float64					`json:"minR"`
	maxR float64					`json:"maxR"`
	height float64					`json:"height"`
}

type girdleThickness struct {
	minT string						`json:"minT"`
	maxT string						`json:"maxT"`
}

type Diamond struct {
	date time.Time 					`json:"createTime"`
	number int 						`json:"registerNumber"`
	shapeAndCut string 				`json:"shapeAndCut"`
	measurement measurement
	carat float64 					`json:"carat"`
	color string 					`json:"color"`
	clarity string					`json:"clarity"`
	cut string						`json:"cut"`
	tableSize int					`json:"tableSize"`
	totalDepth float64				`json:"totalDepth"`
	girdleThickness girdleThickness	`json:"girdleThickness"`
	laserInscription string			`json:"laserInscription"`
	userInfo userInfo
	checkTheft bool					`json:"checkTheft"`
}

// set Diamond data
func setDiamondInfo(aUserInfo userInfo, aDate time.Time, aNumber int, aShapeAndCut string, aMeasurement measurement, aCarat float64, aColor string, aClarity string, aCut string, aTableSize int, aTotalDepth float64, aGirdleThickness girdleThickness, aLaserInscription string, aCheckTheft bool) Diamond {
	rDiamond := Diamond{userInfo: aUserInfo, date: aDate, number: aNumber, shapeAndCut: aShapeAndCut, measurement:aMeasurement, carat:aCarat, color:aColor, clarity:aClarity, cut:aCut, tableSize:aTableSize, totalDepth:aTotalDepth, girdleThickness:aGirdleThickness, laserInscription:aLaserInscription, checkTheft:aCheckTheft}
	return rDiamond
}
func setUserInfo(aName string, aID string) userInfo {
	rUserInfo := userInfo{name:aName, ID:aID}
	return rUserInfo
}
func setMeasurement(minR float64, maxR float64, height float64) measurement {
	rMeasurement := measurement{minR, maxR, height}
	return rMeasurement
}
func setGirdleThickness(minT string, maxT string) girdleThickness  {
	rGirdleThickness := girdleThickness{minT, maxT}
	return rGirdleThickness
}

// Init is called during chaincode instantiation to initialize any
// data. Note that chaincode upgrade also calls this function to reset
// or to migrate data.
//
// argument definition :
// instantiate or upgrade
func (t *DiamondChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {
	fmt.Println("DiamondChaincode.go Intialization!!")
	// Get the args from the transaction proposal
	//args := stub.GetStringArgs()
	//if len(args) != 2 {
	//	return shim.Error("Incorrect arguments. Expecting a key and a value")
	//}

	//// Set up any variables or assets here by calling stub.PutState()
	//
	//
	//// We store the key and the value on the ledger
	//err := stub.PutState(args[0], []byte(args[1]))
	//if err != nil {
	//	return shim.Error(fmt.Sprintf("Failed to create asset: %s", args[0]))
	//}
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
	var response pb.Response

	// 새 다이아몬드 추가
	if fn == "set" {
		result, err = set(stub, args)
	// 다이아몬드 정보 조회
	} else if fn == "get"{ // assume 'get' even if fn is nil
		result, err = get(stub, args)
	// 거래 발생 혹은 감정서 업데이트 혹은 도난 신고
	} else if fn == "changeOwner"{
		response = changeOwner(stub, args)
		return response
	} else if fn == "setTheft" {
		response = setTheft(stub, args)
		return response
	} else if fn == "updateDiamond" {
		response = updateDiamond(stub, args)
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

// TODO : 다이아몬드 거래 반영 함수
//
// argument definition :
// changeOwner key username userID
func changeOwner(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 3 {
		return shim.Error("Incorrect arguments. Expecting a key")
	}

	beforeValue, err := stub.GetState(args[0])
	if err != nil {
		return shim.Error("Failed to get asset" + args[0])
	}
	if beforeValue != nil {
		return shim.Error("Asset not found" + args[0])
	}

	var tempDiamond Diamond
	json.Unmarshal(beforeValue, &tempDiamond)

	tempUserInfo := setUserInfo(args[1], args[2])
	tempDiamond.userInfo = tempUserInfo

	afterValue ,err := json.Marshal(tempDiamond)
	if err != nil {
		return shim.Error("Failed to convert struct to byte")
	}

	err = stub.PutState(args[0], afterValue)
	if err != nil {
		return shim.Error("Failed to set asset: " + args[0])
	}

	return shim.Success(afterValue)
}

// TODO : 다이아몬드 감정서 업데이트 함수
//
// argument definition :
// updateDiamond username userID number shapeandcut minR maxR height carat color clarity cut tablesize totaldepth mingirdle maxgirdle laserinscription checkTheft
func updateDiamond(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 17 {
		return shim.Error("Incorrect arguments. Expecting a key")
	}

	beforeValue, err := stub.GetState(args[0])
	if err != nil {
		return shim.Error("Failed to get asset" + args[0])
	}
	if beforeValue != nil {
		return shim.Error("Asset not found" + args[0])
	}

	nUserInfo := setUserInfo(args[0], args[1])
	nMinR, err := strconv.ParseFloat(args[4], 64)
	nMaxR, err := strconv.ParseFloat(args[5], 64)
	nHeight, err := strconv.ParseFloat(args[6], 64)
	if err != nil {
		return shim.Error("Failed to set asset: "+args[4]+", "+args[5]+", "+args[6])
	}
	nMeasurement := setMeasurement(nMinR, nMaxR, nHeight)
	nGirdleThickness := setGirdleThickness(args[13], args[14])
	if err != nil {
		return shim.Error("Failed to set asset: "+args[13]+", "+args[14])
	}

	nNumber, err := strconv.Atoi(args[2])
	ntableSize, err := strconv.Atoi(args[11])
	ntableDepth, err := strconv.ParseFloat(args[12], 64)
	if err != nil {
		return shim.Error("Failed to set asset: "+args[2]+", "+args[11]+", "+args[12])
	}

	nCarat, err := strconv.ParseFloat(args[7], 64)
	nCheckTheft, err := strconv.ParseBool(args[16])

	nDiamond := setDiamondInfo(nUserInfo, time.Now(), nNumber, args[3], nMeasurement, nCarat, args[8], args[9], args[10], ntableSize, ntableDepth, nGirdleThickness, args[15], nCheckTheft)

	bDiamond, err := json.Marshal(nDiamond)
	if err != nil {
		return shim.Error("Failed to convert struct to byte")
	}

	err = stub.PutState(args[15], bDiamond)
	if err != nil {
		return shim.Error("Failed to set asset: "+args[0])
	}

	return shim.Success(bDiamond)
}

// TODO : 도난 등록 함수
//
// arguments definition:
// setTheft key checkTheft
func setTheft(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 2 {
		return shim.Error("Incorrect arguments. Expecting a key")
	}

	beforeValue, err := stub.GetState(args[0])
	if err != nil {
		return shim.Error("Failed to get asset" + args[0])
	}
	if beforeValue != nil {
		return shim.Error("Asset not found" + args[0])
	}

	var tempDiamond Diamond
	json.Unmarshal(beforeValue, &tempDiamond)

	nCheckTheft, err := strconv.ParseBool(args[1])

	tempDiamond.checkTheft = nCheckTheft

	bDiamond, err := json.Marshal(tempDiamond)
	if err != nil {
		return shim.Error("Failed to convert struct to byte")
	}

	err = stub.PutState(args[0], bDiamond)
	if err != nil {
		return shim.Error("Failed to set asset: "+args[0])
	}

	return shim.Success(bDiamond)
}

// TODO : new diamond register
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

	err = stub.PutState(args[15], bDiamond)
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