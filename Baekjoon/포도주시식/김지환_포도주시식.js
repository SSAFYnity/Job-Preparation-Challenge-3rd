const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

let N = Number(input[0]);
let wines = input.slice(1).map(Number);
let dp = Array(wines.length).fill().map(() => 0);

dp[0] = wines[0];
dp[1] = wines[0] + wines[1];
dp[2] = Math.max(wines[0] + wines[1], wines[0] + wines[2], wines[1] + wines[2]);

for (let i = 3; i < wines.length; i++) {
  dp[i] = Math.max(dp[i - 3] + wines[i - 1] + wines[i], dp[i - 2] + wines[i], dp[i - 1]);
}

console.log(dp[N - 1]);
