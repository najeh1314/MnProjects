const dateEl = document.querySelector("#date");
const currentDate = new Date();
dateEl.textContent = currentDate.getDate();
dateEl.textContent += "/"+currentDate.getMonth()+1;
dateEl.textContent += "/"+currentDate.getFullYear();

const coffeeMenuEL = document.querySelector("#coffee-menu");
coffeeMenuEL.innerHTML += `
<article class="item">
    <p class="flavor">French Vanilla</p><p class="price">3.00</p>
</article>
<article class="item">
    <p class="flavor">Caramel Macchiato</p><p class="price">3.75</p>
</article>
<article class="item">
    <p class="flavor">Pumpkin Spice</p><p class="price">3.50</p>
</article>
<article class="item">
    <p class="flavor">Hazelnut</p><p class="price">4.00</p>
</article>
<article class="item">
    <p class="flavor">Mocha</p><p class="price">4.50</p>
</article>
`;
const dessertMenuEL = document.querySelector("#dessert-menu");
dessertMenuEL.innerHTML += `
<article class="item">
            <p class="dessert">Donut</p><p class="price">1.50</p>
          </article>
          <article class="item">
            <p class="dessert">Cherry Pie</p><p class="price">2.75</p>
          </article>
          <article class="item">
            <p class="dessert">Cheesecake</p><p class="price">3.00</p>
          </article>
          <article class="item">
            <p class="dessert">Cinnamon Roll</p><p class="price">2.50</p>
          </article>
`;