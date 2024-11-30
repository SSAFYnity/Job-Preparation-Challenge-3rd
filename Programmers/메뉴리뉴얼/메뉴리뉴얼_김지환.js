function solution(orders, course) {
  var answer = [];

  course.forEach((courseSize) => {
    const menuSet = new Map();

    orders.forEach((order) => {
      const foods = order.split('');
      foods.sort();

      combination(0, foods, 0, courseSize, '', menuSet);
    })

    const maxCount = Math.max(...menuSet.values());
    if (maxCount >= 2) {
      const maxMenus = [...menuSet]
        .filter(([_, count]) => count === maxCount)
        .map(([menu]) => menu);

      answer.push(...maxMenus);
    }
  })

  return answer.sort();
}

function combination(index, foods, curSize, courseSize, curCourse, menuSet) {
  if (curSize === courseSize) {
    menuSet.set(curCourse, (menuSet.get(curCourse) || 0) + 1);
    return;
  }

  for (let i = index; i < foods.length; i++) {
    combination(i + 1, foods, curSize + 1, courseSize, curCourse + foods[i], menuSet);

  }
}