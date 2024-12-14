const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

let [su, dong] = input[0].split(" ").map((r) => Number(r));

if (su > dong) {
  console.log(su - dong);
  return;
}

let dp = Array(dong + 1).fill().map((e, index) => su - index)

for (let i = su + 1; i <= dong; i++) {
  let min;
  if (i % 2 == 0) {
    min = dp[i / 2]
  } else {
    min = Math.min(dp[(i + 1) / 2], dp[(i - 1) / 2]) + 1;
  }
  dp[i] = Math.min(min, dp[i - 1]) + 1;
}

console.log(dp[dong]);