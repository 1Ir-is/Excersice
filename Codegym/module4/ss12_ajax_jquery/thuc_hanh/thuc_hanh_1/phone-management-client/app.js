function addNewSmartPhone() {
  let producer = $("#producer").val();
  let model = $("#model").val();
  let price = $("#price").val();

  if (producer === "" || model === "" || price === "") {
    alert("Please fill in all fields");
    return;
  }

  let newSmartphone = {
    producer: producer,
    model: model,
    price: price,
  };

  $.ajax({
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    type: "POST",
    data: JSON.stringify(newSmartphone),
    url: "http://localhost:8080/api/smartphones",
    success: function (data) {
      alert("Smartphone added successfully");
      $("#add-smartphone")[0].reset();
    },
    error: function (error) {
      alert("Error adding smartphone: " + error.responseText);
    },
  });
  event.preventDefault();
}

function getSmartphone(smartphone) {
  return (
    `<tr><td >${smartphone.producer}</td><td >${smartphone.model}</td><td >${smartphone.price}</td>` +
    `<td class="btn"><button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button></td></tr>`
  );
}

function displayFormCreate() {
  document.getElementById("smartphoneList").style.display = "none";
  document.getElementById("add-smartphone").style.display = "block";
  document.getElementById("display-create").style.display = "none";
  document.getElementById("title").style.display = "none";
}

function successHandler() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/smartphones",
    success: function (data) {
      let content =
        ' <table id="display-list" border="1"><tr>\n' +
        " <th>Producer</td>\n" +
        " <th>Model</td>\n" +
        " <th>Price</td>\n" +
        " <th>Delete</td>\n" +
        " </tr>";
      for (const element of data) {
        content += getSmartphone(element);
      }
      content += "</table>";
      document.getElementById("smartphoneList").innerHTML = content;
      document.getElementById("smartphoneList").style.display = "block";
      document.getElementById("add-smartphone").style.display = "none";
      document.getElementById("display-create").style.display = "block";
      document.getElementById("title").style.display = "block";
    },
  });
}

function deleteSmartphone(id) {
  $.ajax({
    type: "DELETE",
    url: `http://localhost:8080/api/smartphones/${id}`,
    success: function () {
      alert("Smartphone deleted successfully");
      successHandler();
    },
  });
}
