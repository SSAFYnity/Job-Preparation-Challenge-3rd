const fs = require('fs');
const file = process.platform === 'linux' ? 'dev/stdin' : '../stdin.txt';
const [temp, ...rest] = fs.readFileSync(file).toString().trim().split('\n');

const [N, M] = temp.split(' ').map(Number);
const distance = rest.map((el) => el.split(' ').map(Number));

for (let i = 1; i < M; i++) {
  distance[0][i] += distance[0][i - 1];
}

for (let depth = 1; depth < N; depth++) {
  const newDistance = [];
  for (let i = 0; i < M; i++) {
    // 누적합 만들기
    let maxValue = -99999999;
    let leftTotal = distance[depth][i];
    for (let j = 0; j < i; j++) leftTotal += distance[depth][j];
    for (let j = 0; j < i; j++) {
      const currentValue = leftTotal + distance[depth - 1][j];
      leftTotal -= distance[depth][j];
      maxValue = Math.max(maxValue, currentValue);
    }

    let rightTotal = distance[depth][i];
    maxValue = Math.max(maxValue, rightTotal + distance[depth - 1][i]);
    for (let j = i; j < M - 1; j++) {
      rightTotal += distance[depth][j + 1];
      const currentValue = rightTotal + distance[depth - 1][j + 1];
      maxValue = Math.max(maxValue, currentValue);
    }

    newDistance.push(maxValue);
  }

  distance[depth] = newDistance;
}

console.log(distance[N - 1][M - 1]);
