function getCombinations(arr) {
  const result = [];
  const generateCombinations = (start, currentCombo) => {
    if (currentCombo.length === 3) {
      result.push([...currentCombo]);
      return;
    }

    for (let i = start; i < arr.length; i++) {
      currentCombo.push(arr[i]);
      generateCombinations(i + 1, currentCombo);
      currentCombo.pop();
    }
  };

  generateCombinations(0, []);
  return result;
}

const fs = require('fs');
const file = process.platform === 'linux' ? 'dev/stdin' : '../stdin.txt';
const [N, ...rest] = fs.readFileSync(file).toString().trim().split('\n');
const board = rest.map((el) => el.split(' '));

const xPositions = [];
const tPositions = [];

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

for (let x = 0; x < N; x++) {
  for (let y = 0; y < N; y++) {
    if (board[x][y] === 'X') xPositions.push([x, y]);
    else if (board[x][y] === 'T') tPositions.push([x, y]);
  }
}

const allComb = getCombinations(xPositions);
let ans = 'NO';

for (let i = 0; i < allComb.length; i++) {
  const comb = allComb[i];
  let foundStudent = false;
  comb.forEach(([x, y]) => (board[x][y] = 'O'));

  tPositions.forEach(([x, y]) => {
    for (let d = 0; d < 4; d++) {
      if (foundStudent) break;
      let [nx, ny] = [x, y];
      while (!foundStudent) {
        if (nx >= N || ny >= N || nx < 0 || ny < 0) break;
        if (board[nx][ny] === 'O') break;
        if (board[nx][ny] === 'S') foundStudent = true;
        nx += dx[d];
        ny += dy[d];
      }
    }
  });

  comb.forEach(([x, y]) => (board[x][y] = 'X'));
  if (!foundStudent) {
    ans = 'YES';
    break;
  }
}

console.log(ans);
