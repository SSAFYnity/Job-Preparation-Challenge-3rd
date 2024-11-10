const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

let N = Number(input[0]);

const map = []
const teachers = [];

for (let i = 1; i <= N; i++) {
  map.push(input[i].split(" ").map(x => x.trim()));
  // map.push(input[i].split(" "))
}

// 학생들의 위치 정보
for (let x = 0; x < N; x++) {
  for (let y = 0; y < N; y++) {
    if (map[x][y] === 'T') {
      teachers.push({ x, y });
    }
  }
}

// 수직 이동 벡터
const dx = [-1, 1, 0, 0];
const dy = [0, 0, 1, -1];

// 3중 for문을 통해서 모든 좌표에 대입해본다.
for (let f = 0; f < N * N; f++) {
  let ox = parseInt(f / N);
  let oy = f % N;
  if (map[ox][oy] !== 'X') continue;

  let point_one = { x: ox, y: oy };
  for (let s = 1; s < N * N; s++) {
    let sx = parseInt(s / N);
    let sy = s % N;
    if (map[sx][sy] !== 'X') continue;

    let point_two = { x: sx, y: sy };

    for (let t = 2; t < N * N; t++) {
      let tx = parseInt(t / N);
      let ty = t % N;
      if (map[tx][ty] !== 'X') continue;

      let point_three = { x: tx, y: ty };

      // 장애물이 point_one, point_two, point_tree에 위치할 경우에 대한 유효성 검사 실행
      // 가능하면 YES를 출력한다.

      // 현재 맵에 대한 검증 로직
      const localMap = map.map(row => [...row]);

      localMap[point_one.x][point_one.y] = 'O';
      localMap[point_two.x][point_two.y] = 'O';
      localMap[point_three.x][point_three.y] = 'O';

      let counted = false;

      for (let t of teachers) {
        for (let d = 0; d < 4; d++) {
          let len = 1;
          while (true) {
            let nx = t.x + dx[d] * len;
            let ny = t.y + dy[d] * len;
            if (isIn(nx, ny) == false || localMap[nx][ny] === 'T' || localMap[nx][ny] === 'O') {
              break;
            }

            if (localMap[nx][ny] === 'S') {
              counted = true;
              break;
            }
            len++;
          }
        }
      }

      if (counted === false) {
        console.log("YES");

        process.exit(0);
      }

    }
  }
}

console.log("NO")

function isIn(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}