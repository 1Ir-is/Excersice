import axios from "axios";
import Link from "next/link";

export const dynamic = "force-dynamic";

const cities = [
  { name: "Hà Nội", lat: 21.0285, lon: 105.8542 },
  { name: "Hồ Chí Minh", lat: 10.7769, lon: 106.7009 },
  { name: "Đà Nẵng", lat: 16.0471, lon: 108.2068 },
];

export default async function WeatherSSR({ searchParams }: any) {
  const city = cities.find((c) => c.name === searchParams?.city) || cities[0];
  const res = await axios.get(
    `https://api.open-meteo.com/v1/forecast?latitude=${city.lat}&longitude=${city.lon}&current_weather=true`
  );
  const weather = res.data.current_weather;

  return (
    <div>
      <h2>Thời tiết {city.name}</h2>
      <div>
        {cities.map((c) => (
          <Link key={c.name} href={`/weather-ssr?city=${c.name}`}>
            {c.name}
          </Link>
        ))}
      </div>
      <p>Nhiệt độ: {weather.temperature}°C</p>
      <p>Gió: {weather.windspeed} km/h</p>
    </div>
  );
}
