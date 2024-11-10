import Foundation

let input = readLine()!.split(separator: " ").map { Int($0)! }
let H = input[0]
let W = input[1]
let X = input[2]
let Y = input[3]

var B = [[Int]]()
for _ in 0..<(H+X) {
    let row = readLine()!.split(separator: " ").map { Int($0)! }
    B.append(row)
}

var A = Array(repeating: Array(repeating: 0, count: W), count: H)

for i in 0..<H {
    for j in 0..<W {
        if i < X || j < Y {
            A[i][j] = B[i][j]
        } else {
            A[i][j] = B[i][j] - A[i-X][j-Y]
        }
    }
}

for i in 0..<H {
    let row = A[i].map(String.init).joined(separator: " ")
    print(row)
}