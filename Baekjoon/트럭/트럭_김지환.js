const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

let [N, W, L] = input[0].split(" ").map(Number);
let trucks = input[1].split(" ").map(Number);
let answer = 0;

// 트럭의 순서를 바꿀 수 없다.
// 즉, 현재 순서에서 건너는대 걸리는 최소 시간을 구해라
const que = [];
let curWeight = 0;

let i = 0;
while (i < N) {
  answer++;

  // 순회하면서 time 소모 및 pop and curWeight 최신화 조회
  for (let q = 0; q < que.length; q++) {
    que[q].time--;
  }

  while (que.length > 0) {
    if (que[0].time <= 0) {
      curWeight -= que.shift().weight;
    }
    else break;
  }

  if (trucks[i] + curWeight <= L) {
    que.push({ weight: trucks[i], time: W });
    curWeight += trucks[i];
    i++;
  }
}

// 큐에 남아있는 마지막 트럭의 시간을 기준으로 더해준다.
answer += que[que.length - 1].time;

console.log(answer)