// script.js

document.addEventListener('DOMContentLoaded', function() {
  const featuresDiv = document.getElementById('features');
  const btnLearnMore = document.getElementById('btnLearnMore');

  const features = [
      "Fonctionnalité 1: Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      "Fonctionnalité 2: Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
      "Fonctionnalité 3: Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
  ];

  btnLearnMore.addEventListener('click', function() {
      featuresDiv.innerHTML = "";
      features.forEach(function(feature) {
          const p = document.createElement('p');
          p.textContent = feature;
          featuresDiv.appendChild(p);
      });
  });
});
