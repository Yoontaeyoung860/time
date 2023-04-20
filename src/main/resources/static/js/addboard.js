
document.addEventListener('DOMContentLoaded', function () {
  document.querySelector('.write-button').addEventListener('click', function (event) {
    event.preventDefault(); // 페이지 새로고침 방지

    const fileInput = document.getElementById('file-input');
    const file = fileInput.files[0];

    const jobBoardIdInput = document.getElementById('job-board-id');
    const jobBoardId = jobBoardIdInput.value;

    const formData = new FormData();
    formData.append('file', file);
    formData.append('jobBoardId', jobBoardId);

    fetch('http://localhost:9080/jobBoards/createBoard', {
      method: 'POST',
      body: formData,
    })
    .then((response) => {
      if (response.ok) {
        return response.text();
      } else {
        throw new Error('Failed to upload file');
      }
    })
    .then((text) => {
      console.log('File uploaded successfully:', text);
      document.querySelector('.layout').submit(); // 이미지 업로드가 완료된 후에 전체 폼을 제출합니다.
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  });

  const emailProviders = ["gmail.com", "naver.com", "daum.net", "yahoo.com", "outlook.com", "nate.com"];

function autoCompleteEmail(input) {
    const suggestions = document.getElementById("suggestions");
    suggestions.innerHTML = "";
    const emailParts = input.value.split("@");

    if (emailParts.length > 1 && emailParts[1] !== "") {
        const matches = emailProviders.filter(provider => provider.startsWith(emailParts[1]));

        if (matches.length > 0) {
            suggestions.style.display = "block";
            matches.forEach(match => {
                const li = document.createElement("li");
                li.innerText = emailParts[0] + "@" + match;
                li.onclick = () => {
                    input.value = emailParts[0] + "@" + match;
                    suggestions.innerHTML = "";
                    suggestions.style.display = "none";
                };
                suggestions.appendChild(li);
            });
        } else {
            suggestions.style.display = "none";
        }
    } else {
        suggestions.style.display = "none";
    }
}

         let map;
let input;
let searchBox;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 37.5665, lng: 126.9780 }, // 서울의 기본 좌표
    zoom: 10,
  });

  input = document.getElementById("location-input");
  searchBox = new google.maps.places.SearchBox(input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  // 지도를 클릭하면 검색된 위치로 이동
  map.addListener("bounds_changed", () => {
    searchBox.setBounds(map.getBounds());
  });

  // 사용자가 위치를 선택하면 지도의 중심을 해당 위치로 이동
  searchBox.addListener("places_changed", () => {
    const places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }

    const bounds = new google.maps.LatLngBounds();
    places.forEach((place) => {
      if (!place.geometry) {
        console.log("Returned place contains no geometry");
        return;
      }

      if (place.geometry.viewport) {
        bounds.union(place.geometry.viewport);
      } else {
        bounds.extend(place.geometry.location);
      }
    });
    map.fitBounds(bounds);
  });
}

        //학력
       function setEducation() {
            const educationSelect = document.getElementById("education");
            const selectedEducation = educationSelect.options[educationSelect.selectedIndex].text;
            const educationInput = document.getElementById("educationInput");
            educationInput.value = selectedEducation;
        }



