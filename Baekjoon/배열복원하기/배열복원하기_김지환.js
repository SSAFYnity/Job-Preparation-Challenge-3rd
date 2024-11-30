const readline = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

readline.on('line', function (line) {
  input.push(line);
}).on('close', function () {
  let [H, W, X, Y] = input[0].split(" ").map(Number);
  let B = [];
  for (let i = 1; i <= H + X; i++) {
    B.push(input[i].split(" ").map(Number));
  }

  let A = Array(H).fill().map(() => Array(W).fill(0));


  for (let i = 0; i < H + X; i++) {
    for (let j = 0; j < W + Y; j++) {
      if (X <= i && i < H && Y <= j && j < W) {
        A[i][j] = B[i][j] - A[i - X][j - Y];
        continue;
      }
      if (i < H && j < W) {
        A[i][j] = B[i][j];
      }
    }
  }

  for (row of A) {
    console.log(row.join(" "));
  }

  process.exit();
});