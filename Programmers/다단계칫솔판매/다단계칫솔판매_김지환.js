function solution(enroll, referral, seller, amount) {
  const map = enroll.reduce((acc, name, index) => {
    acc[name] = referral[index];
    return acc;
  }, {})

  const profit = new Map();
  enroll.forEach(e => profit.set(e, 0));

  seller.forEach((name, index) => {
    let money = amount[index] * 100;
    let current = name;

    while (money > 0) {
      let share = Math.floor(money * 0.1);
      let owner = money - share;

      profit.set(current, profit.get(current) + owner);

      if (map[current] === '-') break;
      current = map[current];
      money = share;
    }
  })

  return [...profit.values()];
}