function solution(cards) {
  const len = cards.length;
  let visited = Array(len).fill(false);

  let index = 0;
  let loops = [];

  while (index < len) {
    let localLoop = [];

    let i = index;
    while (visited[i] === false) {
      visited[i] = true;
      localLoop.push(cards[i]);
      i = cards[i] - 1;
    }
    index++;
    loops.push(localLoop)
  }

  loops.sort((a, b) => b.length - a.length);
  return loops.length == 1 ? 0 : loops[0].length * loops[1].length;
}