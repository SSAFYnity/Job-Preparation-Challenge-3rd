import Foundation

let input = readLine()!.split(separator: " ").map { Int($0)! }
let n = input[0]
let w = input[1]
let L = input[2]

let trucks = readLine()!.split(separator: " ").map { Int($0)! }

var bridge = Array(repeating: 0, count: w)
var time = 0
var currentWeight = 0
var truckIndex = 0

while true {
    if truckIndex >= n && currentWeight == 0 {
        break
    }
    
    let outgoingTruck = bridge.removeFirst()
    currentWeight -= outgoingTruck
    
    if truckIndex < n {
        let nextTruck = trucks[truckIndex]
        if currentWeight + nextTruck <= L {
            bridge.append(nextTruck)
            currentWeight += nextTruck
            truckIndex += 1
        } else {
            bridge.append(0)
        }
    } else {
        bridge.append(0)
    }
    
    time += 1
}

print(time)