package main

import (
	"fmt"
	"testing"

	"github.com/hyperledger/fabric/core/chaincode/shim"
)

func checkSet(t *testing.T, stub *shim.MockStub, args [][]byte) {
	res := stub.MockInvoke("1", args)
	if res.Status != shim.OK {
		fmt.Println("Set", args, "failed", string(res.Message))
		t.FailNow()
	}
}

func checkGet(t *testing.T, stub *shim.MockStub, args [][]byte) {
	res := stub.MockInvoke("1", args)
	if res.Status != shim.OK {
		fmt.Println("Get", args, "failed", string(res.Message))
		t.FailNow()
	}
}

func checkChangeOwner(t *testing.T, stub *shim.MockStub, args [][]byte) {
	res := stub.MockInvoke("1", args)
	if res.Status != shim.OK {
		fmt.Println("ChangeOwner", args, "failed", string(res.Message))
		t.FailNow()
	}
}

func checkUpdateDiamond(t *testing.T, stub *shim.MockStub, args [][]byte) {
	res := stub.MockInvoke("1", args)
	if res.Status != shim.OK {
		fmt.Println("UpdateDiamond", args, "failed", string(res.Message))
		t.FailNow()
	}
}

func checkSetTheft(t *testing.T, stub *shim.MockStub, args [][]byte) {
	res := stub.MockInvoke("1", args)
	if res.Status != shim.OK {
		fmt.Println("SetTheft", args, "failed", string(res.Message))
		t.FailNow()
	}
}

func TestDiamondChaincode_Invoke(t *testing.T) {
	scc := new(DiamondChaincode)
	stub := shim.NewMockStub("mycc", scc)

	checkSet(t, stub, [][]byte{[]byte("set"), []byte("minsoo"), []byte("minsoo"), []byte("1000"), []byte("round"), []byte("6.52"), []byte("6.53"), []byte("4.00"), []byte("1.03"), []byte("E"), []byte("VVS1"), []byte("excellent"), []byte("64"), []byte("66.4"), []byte("Thin"), []byte("Medium"), []byte("D1000"), []byte("false")})

	checkGet(t, stub, [][]byte{[]byte("get"), []byte("D1000")})
	checkChangeOwner(t, stub, [][]byte{[]byte("changeOwner"), []byte("D1000"), []byte("gunhee"), []byte("gunhee")})
	checkUpdateDiamond(t, stub, [][]byte{[]byte("updateDiamond"), []byte("gunhee"), []byte("gunhee"), []byte("1000"), []byte("round"), []byte("6.52"), []byte("6.53"), []byte("4.00"), []byte("1.03"), []byte("E"), []byte("VVS1"), []byte("excellent"), []byte("64"), []byte("66.4"), []byte("Thin"), []byte("Medium"), []byte("D1000"), []byte("false")})
	checkSetTheft(t, stub, [][]byte{[]byte("setTheft"), []byte("D1000"), []byte("true")})
}
