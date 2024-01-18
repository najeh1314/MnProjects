const dateEl = document.querySelector("#date");
const currentDate = new Date();
dateEl.textContent = `${currentDate.getDate()}/${currentDate.getMonth() + 1}/${currentDate.getFullYear()}`;

const coffeeMenuEL = document.querySelector("#coffee-menu");
const dessertMenuEL = document.querySelector("#dessert-menu");

// Coffee menu items
const coffeeMenuItems = [
  { flavor: "French Vanilla", price: 3.00 },
  { flavor: "Caramel Macchiato", price: 3.75 },
  { flavor: "Pumpkin Spice", price: 3.50 },
  { flavor: "Hazelnut", price: 4.00 },
  { flavor: "Mocha", price: 4.50 }
];

// Dessert menu items
const dessertMenuItems = [
  { dessert: "Donut", price: 1.50 },
  { dessert: "Cherry Pie", price: 2.75 },
  { dessert: "Cheesecake", price: 3.00 },
  { dessert: "Cinnamon Roll", price: 2.50 }
];

// Function to create menu items dynamically
function createMenuItems(menuEl, items) {
  items.forEach(item => {
    menuEl.innerHTML += `
      <article class="item">
        <p class="${item.flavor ? 'flavor' : 'dessert'}">${item.flavor || item.dessert}</p>
        <p class="price">${item.price.toFixed(2)}</p>
      </article>
    `;
  });
}

// Populate coffee and dessert menus
createMenuItems(coffeeMenuEL, coffeeMenuItems);
createMenuItems(dessertMenuEL, dessertMenuItems);
