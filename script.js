// Générer un nombre aléatoire entre 1 et 100
const randomNumber = Math.floor(Math.random() * 100) + 1;
console.log(randomNumber);

// Récupérer les éléments du DOM
const guessInput = document.getElementById('guessInput');
const guessButton = document.getElementById('guessButton');
const message = document.getElementById('message');

// Fonction pour afficher un message
function showMessage(msg, color) {
    message.textContent = msg;
    message.style.color = color;
}

// Fonction pour vérifier la devinette
function checkGuess() {
    const guess = parseInt(guessInput.value);
    if (isNaN(guess) || guess < 1 || guess > 100) {
        showMessage('Veuillez entrer un nombre entre 1 et 100.', 'red');
    } else if (guess === randomNumber) {
        showMessage(`Bravo! Vous avez deviné le nombre ${randomNumber} correctement!`, 'green');
        guessInput.disabled = true;
        guessButton.disabled = true;
    } else {
        const diff = Math.abs(randomNumber - guess);
        let msg;
        let color;
        if (diff > 50) {
            msg = 'Vous êtes très loin!';
            color = 'red';
        } else if (diff > 30) {
            msg = 'Vous êtes loin!';
            color = 'orange';
        } else if (diff > 10) {
            msg = 'Vous êtes proche!';
            color = 'yellow';
        } else {
            msg = 'Vous êtes très proche!';
            color = 'lightgreen';
        }
        showMessage(msg, color);
    }
}

// Ajouter un événement de clic au bouton de devinette
guessButton.addEventListener('click', checkGuess);

// Ajouter un événement de saisie pour permettre d'appuyer sur "Entrée" pour deviner
guessInput.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        checkGuess();
    }
});
