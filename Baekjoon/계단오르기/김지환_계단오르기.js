const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

let N = Number(input[0]);
let steps = input.slice(1).map(Number);
const dp = Array(N).fill().map(() => 0);

dp[0] = steps[0];
dp[1] = steps[0] + steps[1];
dp[2] = Math.max(steps[0] + steps[2], steps[1] + steps[2])

for (let i = 3; i < N; i++) {
  dp[i] = Math.max(dp[i - 3] + steps[i - 1], dp[i - 2]) + steps[i];
}

console.log(dp[N - 1])