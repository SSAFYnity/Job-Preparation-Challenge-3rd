const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const [N, M] = input[0].split(" ").map(Number);
let isPrime = Array(M + 1).fill(true);
isPrime[0] = isPrime[1] = false;

for (let i = 2; i * i <= M; i++) {
  if (isPrime[i]) {
    for (let j = i * i; j <= M; j += i) {
      isPrime[j] = false;
    }
  }
}

for (let i = N; i <= M; i++) {
  if (isPrime[i]) {
    console.log(i);
  }
}