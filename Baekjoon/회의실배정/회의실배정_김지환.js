const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const meetings = input.slice(1).map(line => line.split(' ').map(Number));

meetings.sort((a, b) => a[1] - b[1] || a[0] - b[0])
let count = 0;
let endTime = 0;

for ([start, end] of meetings) {
  if (start >= endTime) {
    endTime = end;
    count++;
  }
}

console.log(count);
