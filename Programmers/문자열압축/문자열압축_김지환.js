function solution(s) {
  const len = s.length;
  let answer = len;

  for (let i = 1; i <= Math.floor(len / 2); i++) {
    let compressed = '';
    let j = 0;

    while (j < len) {
      let count = 1;
      const target = s.substr(j, i);
      let c = j + i;

      while (c < len && target === s.substr(c, i)) {
        count++;
        c += i;
      }

      if (count > 1) {
        compressed += count + target;
      } else {
        compressed += target;
      }

      j = c;
    }

    if (j < len) compressed += s.substr(j);

    answer = Math.min(answer, compressed.length);
  }

  return answer;
}