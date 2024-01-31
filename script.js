document.addEventListener('DOMContentLoaded', () => {
    const board = document.getElementById('board');
    const status = document.getElementById('status');
    const restartBtn = document.getElementById('restartBtn');

    let currentPlayer = 'X';
    let boardState = ['', '', '', '', '', '', '', '', ''];
    let gameActive = true;

    const winningConditions = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];

    function handleCellClick(clickedCell, clickedCellIndex) {
        boardState[clickedCellIndex] = currentPlayer;
        clickedCell.innerHTML = currentPlayer;
        let gameWon = checkWin();
        if (gameWon) {
            status.innerHTML = `Player ${currentPlayer} wins!`;
            gameActive = false;
            return;
        }
        if (!boardState.includes('')) {
            status.innerHTML = "It's a draw!";
            gameActive = false;
            return;
        }
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
        status.innerHTML = `Player ${currentPlayer}'s turn`;
    }

    function checkWin() {
        return winningConditions.some((winningCombo) => {
            return winningCombo.every((index) => {
                return boardState[index] === currentPlayer;
            });
        });
    }

    function handleCellHover(event) {
        if (!gameActive) return;
        const hoveredCell = event.target;
        const hoveredCellIndex = parseInt(hoveredCell.getAttribute('data-cell-index'));

        if (boardState[hoveredCellIndex] !== '') return;

        hoveredCell.innerHTML = currentPlayer;
    }

    function handleCellLeave(event) {
        if (!gameActive) return;
        const hoveredCell = event.target;
        const hoveredCellIndex = parseInt(hoveredCell.getAttribute('data-cell-index'));

        if (boardState[hoveredCellIndex] !== '') return;

        hoveredCell.innerHTML = '';
    }

    function handleRestartGame() {
        currentPlayer = 'X';
        boardState = ['', '', '', '', '', '', '', '', ''];
        gameActive = true;
        status.innerHTML = `Player ${currentPlayer}'s turn`;
        document.querySelectorAll('.cell').forEach(cell => cell.innerHTML = '');
    }

    function createBoard() {
        for (let i = 0; i < 9; i++) {
            const cell = document.createElement('div');
            cell.classList.add('cell');
            cell.setAttribute('data-cell-index', i);
            cell.addEventListener('click', () => {
                if (gameActive && boardState[i] === '') {
                    handleCellClick(cell, i);
                }
            });
            cell.addEventListener('mouseover', handleCellHover);
            cell.addEventListener('mouseleave', handleCellLeave);
            board.appendChild(cell);
        }
        status.innerHTML = `Player ${currentPlayer}'s turn`;
    }

    createBoard();

    restartBtn.addEventListener('click', handleRestartGame);
});
