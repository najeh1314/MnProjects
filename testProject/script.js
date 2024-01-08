const dateEl = document.querySelector("#date");
const currentDate = new Date();
dateEl.textContent = currentDate.getDate();
dateEl.textContent += "/"+currentDate.getMonth()+1;
dateEl.textContent += "/"+currentDate.getFullYear();