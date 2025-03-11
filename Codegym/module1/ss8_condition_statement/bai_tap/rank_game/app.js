function calculateRank() {
  let mainScore = parseFloat(document.getElementById("mainScore").value);
  let matchesWon = parseFloat(document.getElementById("matchesWon").value);
  let matchesPlayed = parseFloat(
    document.getElementById("matchesPlayed").value
  );
  let rank;
  let winRate = (matchesWon / matchesPlayed) * 100;

  if (matchesPlayed < 5) {
    rank = "Tân binh";
  } else if (mainScore < 1000 || winRate < 50) {
    rank = "Đồng";
  } else if (mainScore >= 1000 && mainScore < 5000 && winRate >= 50) {
    rank = "Bạc";
  } else if (mainScore >= 5000 && mainScore < 10000 && winRate >= 60) {
    rank = "Vàng";
  } else if (mainScore >= 10000 && winRate >= 70) {
    rank = "Kim cương";
  } else {
    rank = "Không xác định";
  }
  document.getElementById(
    "result"
  ).innerText = `Hạng của người chơi là: ${rank}`;
}
