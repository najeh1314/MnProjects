// Récupérer les éléments du DOM
const taskInput = document.getElementById('taskInput');
const addTaskButton = document.getElementById('addTaskButton');
const taskList = document.getElementById('taskList');

// Fonction pour ajouter une tâche à la liste
function addTask() {
    const taskText = taskInput.value.trim();
    if (taskText !== '') {
        const taskItem = document.createElement('li');
        taskItem.className = 'list-group-item';
        taskItem.innerText = taskText;

        const deleteButton = document.createElement('button');
        deleteButton.className = 'btn btn-danger btn-sm float-right';
        deleteButton.innerText = 'Supprimer';
        deleteButton.addEventListener('click', function () {
            taskItem.remove();
        });

        taskItem.appendChild(deleteButton);
        taskList.appendChild(taskItem);
        taskInput.value = '';
    }
}

// Ajouter un événement de clic au bouton d'ajout de tâche
addTaskButton.addEventListener('click', addTask);

// Ajouter un événement de saisie pour permettre d'appuyer sur "Entrée" pour ajouter une tâche
taskInput.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        addTask();
    }
});
