function getTotalTime(level, diffs, times) {
  let result = diffs[0] * times[0];
  for (let i = 1; i < diffs.length; i++) {
    if (level >= diffs[i]) result += times[i];
    else {
      result += (times[i - 1] + times[i]) * (diffs[i] - level) + times[i];
    }
  }
  return result;
}

function solution(diffs, times, limit) {
  // 이분 탐색으로 lower bound를 찾아보자    
  let min = 1, max = 300000;

  while (min <= max) {
    let mid = parseInt((min + max) / 2);

    if (getTotalTime(mid, diffs, times) > limit) {
      min = mid + 1;
    } else {
      max = mid - 1;
    }
  }
  return min;
}


