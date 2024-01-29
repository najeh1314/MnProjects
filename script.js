// Fonction pour générer un nombre aléatoire entre deux valeurs
function getRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Fonction pour vérifier si un nombre est pair ou impair
function checkEvenOrOdd(number) {
    if (number % 2 === 0) {
        return "pair";
    } else {
        return "impair";
    }
}

// Fonction pour calculer la factorielle d'un nombre
function calculateFactorial(number) {
    let factorial = 1;
    for (let i = 1; i <= number; i++) {
        factorial *= i;
    }
    return factorial;
}

// Fonction pour inverser une chaîne de caractères
function reverseString(str) {
    return str.split('').reverse().join('');
}

// Fonction pour vérifier si une chaîne est un palindrome
function checkPalindrome(str) {
    const reversedStr = reverseString(str);
    return str === reversedStr;
}

// Fonction pour trier un tableau de nombres par ordre croissant
function sortNumbersAscending(numbers) {
    return numbers.sort((a, b) => a - b);
}

// Exemple d'utilisation des fonctions
const randomNumber = getRandomNumber(1, 100);
console.log("Nombre aléatoire:", randomNumber);

const parity = checkEvenOrOdd(randomNumber);
console.log("Parité:", parity);

const factorial = calculateFactorial(randomNumber);
console.log("Factorielle:", factorial);

const exampleString = "radar";
console.log("Chaîne initiale:", exampleString);
console.log("Chaîne inversée:", reverseString(exampleString));
console.log("Est un palindrome:", checkPalindrome(exampleString));

const numbersArray = [5, 2, 8, 1, 3];
console.log("Tableau non trié:", numbersArray);
console.log("Tableau trié:", sortNumbersAscending(numbersArray));
