import axios from "axios";

export const dynamic = "force-static";

export default async function WeatherSSG() {
  const res = await axios.get(
    "https://api.open-meteo.com/v1/forecast?latitude=21.0285&longitude=105.8542&current_weather=true"
  );
  const weather = res.data.current_weather;

  return (
    <div>
      <h2>Thời tiết Hà Nội</h2>
      <p>Nhiệt độ: {weather.temperature}°C</p>
      <p>Gió: {weather.windspeed} km/h</p>
    </div>
  );
}
