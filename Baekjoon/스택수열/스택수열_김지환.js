const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const n = input[0];
const arr = input.slice(1).map((e) => Number(e));
let index = 0;
let stack = [];
let answer = '';

for (let i = 1; i <= n; i++) {
  stack.push(i);
  answer += '+\n';

  while (stack.length > 0 && stack[stack.length - 1] === arr[index]) {
    stack.pop();
    answer += '-\n';
    index++;
  }
}
console.log(stack.length > 0 ? 'NO' : answer);
