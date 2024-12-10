const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const bracketList = input.slice(1);

for (brackets of bracketList) {
  const que = [];
  let flag = true;
  for (bracket of brackets) {
    switch (bracket) {
      case '(':
        que.push('(');
        break;
      case ')':
        if (que[que.length - 1] == '(') que.pop();
        else flag = false;
        break;
    }
  }
  if (flag) {
    console.log(que.length > 0 ? 'NO' : 'YES')
  } else {
    console.log('NO')
  }
}