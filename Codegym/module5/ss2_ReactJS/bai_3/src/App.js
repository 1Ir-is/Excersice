import React from "react";
import cityList from "./CityList";

function App() {
  return (
    <div>
      <h2>Danh sách các thành phố trực thuộc trung ương</h2>
      {cityList}
    </div>
  );
}

export default App;
