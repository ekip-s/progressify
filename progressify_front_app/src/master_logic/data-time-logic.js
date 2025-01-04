const formatDateTime = (inputDate) => {
  const date = new Date(inputDate);

  const day = String(date.getDate()).padStart(2, "0");
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const year = date.getFullYear();
  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");

  return `${day}.${month}.${year} ${hours}:${minutes}`;
};

const daysBetween = (start, end) => {
  const diffInMillis = Math.abs(end - start);
  const millisInHour = 1000 * 60 * 60;

  const millisInMinute = 1000 * 60;

  const hours = Math.floor(diffInMillis / millisInHour);
  const minutes = Math.floor((diffInMillis % millisInHour) / millisInMinute);
  if (hours === 0) {
    return `${minutes} минут`;
  }
  return `${hours} час${hours > 1 ? "а" : ""} ${minutes} минут`;
};

export { formatDateTime, daysBetween };
