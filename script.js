// Générer un nombre aléatoire entre 1 et 100
const randomNumber = Math.floor(Math.random() * 100) + 1;
console.log(randomNumber);

// Récupérer les éléments du DOM
const guessInput = document.getElementById('guessInput');
const guessButton = document.getElementById('guessButton');
const message = document.getElementById('message');
let attempts = 5;

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
    } else {
        attempts--;
        if (guess === randomNumber) {
            showMessage(`Bravo! Vous avez deviné le nombre ${randomNumber} correctement!`, 'green');
            guessInput.disabled = true;
            guessButton.disabled = true;
        } else {
            let msg;
            let color;
            if (attempts === 0) {
                msg = `Désolé, vous avez utilisé toutes vos tentatives. Le nombre correct était ${randomNumber}.`;
                color = 'red';
                guessInput.disabled = true;
                guessButton.disabled = true;
            } else {
                if (guess < randomNumber) {
                    msg = `Le nombre que vous avez deviné est trop bas. Il vous reste ${attempts} tentative(s).`;
                } else {
                    msg = `Le nombre que vous avez deviné est trop haut. Il vous reste ${attempts} tentative(s).`;
                }
                color = 'black';
            }
            showMessage(msg, color);
        }
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
