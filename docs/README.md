# Nova User Guide

## Introduction
Nova is a command line interface (CLI) based task management application, designed to help you 
manage and organise your daily tasks efficiently. 

```
   _____________________________________________
     __    _  
    |  \  | |  ___  __    __  ___ _  
    | |\\ | | / _ \ \ \  / / / _ \ |
    | | \\| || |_| | \ \/ / | |_|  |
    |_|  \__| \___/   \__/   \___/_|
   _____________________________________________
    Hey there!! I'm Nova 😚
    What can I do for you?
   _____________________________________________
```
<br>

## Features

### Greeting
Simply type `hi`, `hey`, or `hello` to start a conversation with Nova.

Output:
```angular2html
> hey
   _____________________________________________
    Hellooo! 😁 Hope you're feeling good today!
   _____________________________________________
```
<br>

### Adding Tasks
Nova allows you to manage different types of tasks:
- **Todos** — Simple tasks without due dates
- **Deadlines** — Tasks with a specified due date
- **Events** — Activities with start and end dates

<br>

### Adding Todos
Adds a _Todo_ to the _TaskList_.

**Format:** `todo DESCRIPTION`

**Example:** `todo plan overseas trip`

**Output:**
```
> todo plan overseas trip
   _____________________________________________   
    Gotcha! 🙂‍↕️ I've added a new task: 
    [T][ ] plan overseas trip
    Now we have 1 task!
   _____________________________________________   
```
<br>

### Adding Deadlines
Adds a _Deadline_ to the _TaskList_.

**Format:** `deadline DESCRIPTION /by DATE`

**Example:** `deadline schedule meeting /by tmr 12pm`

**Output:**
```
> deadline schedule meeting /by tmr 12pm
   _____________________________________________
    Gotcha! 🙂‍↕️ I've added a new task: 
    [D][ ] schedule meeting (by: tmr 12pm)
    Now we have 2 tasks!
   _____________________________________________
```
<br>

### Adding Events
Adds an _Event_ to the _TaskList_.

**Format:** `event DESCRIPTION /from START_DATE /to END_DATE`

**Example:** `event birthday party /from 7pm /to 11pm`

**Output:**
```
> event birthday party /from 7pm /to 11pm
   _____________________________________________
    Gotcha! 🙂‍↕️ I've added a new task:
    [E][ ] birthday party (from: 7pm, to: 11pm)
    Now we have 3 tasks!
   _____________________________________________
```
<br>

### Listing Tasks
Lists all the current tasks in the _TaskList_.

**Format:** `list`

**Output:**
```
> list
   _____________________________________________
    Hey girl~ What should we do today? 🤔
    1. [T][ ] plan overseas trip
    2. [D][ ] schedule meeting (by: tmr 12pm)
    3. [E][ ] birthday party (from: 7pm, to: 11pm)
   _____________________________________________
```
<br>

### Marking a Task
Marks a specific task as complete.

**Format:** `mark INDEX`

**Example:** `mark 2`

**Output:**
```
> mark 2
   _____________________________________________
    Yay! I've marked this task done: 
    [D][✖] schedule meeting (by: tmr 12pm)
   _____________________________________________
```
<br>

### Unmarking a Task
Marks a specific task as incomplete.

**Format:** `unmark INDEX`

**Example:** `unmark 2`

**Output:**
```
> unmark 2
   _____________________________________________
    Okay, I've marked this task undone: 
    [D][ ] schedule meeting (by: tmr 12pm)
   _____________________________________________
```
<br>

### Finding a Task
Finds and displays all tasks containing the specific keyword.

**Format:** `find KEYWORD`

**Example:** `find birthday`

**Output:**
```
> find birthday
   _____________________________________________
    Here are the matching tasks 🔍:
    1. [E][ ] birthday party (from: 7pm, to: 11pm)
   _____________________________________________
```
<br>

### Deleting a Task
Deletes a specific task from the _TaskList_.

**Format:** `delete INDEX`

**Example:** `delete 2`

**Output:**
```
   _____________________________________________
    Gotcha! 🙂‍↔️ I've removed this task: 
    [D][ ] schedule meeting (by: tmr 12pm)
    Now we have 2 tasks!
   _____________________________________________
```
<br>

### Clearing all Tasks
Clears all current tasks in _TaskList_.

**Format:** `clear`

**Output:**
```
> clear
   _____________________________________________
    Okie, tasks all cleared! 😁
   _____________________________________________
```
<br>

### Exiting Nova
Exits and closes the application.

**Format:** `bye`

**Output:**
```
> bye
   _____________________________________________
    Bye now! See you soon! 😉
   _____________________________________________
```
<br>


## Command Summary

| Command            | Description                         | Format                                            |
|--------------------|-------------------------------------|---------------------------------------------------|
| `hi`/`hey`/`hello` | Displays greeting                   | `hi`/`hey`/`hello`                                |
| `todo`             | Adds a Todo task                    | `todo DESCRIPTION`                                |
| `deadline`         | Adds a Deadline task                | `deadline DESCRIPTION /by DATE`                   |
| `event`            | Adds an Event task                  | `event DESCRIPTION /from START_DATE /to END_DATE` |
| `list`             | Displays all current tasks          | `list`                                            |
| `mark`             | Marks specific task as complete     | `mark INDEX`                                      |
| `unmark`           | Marks specific task as incomplete   | `unmark INDEX`                                    |
| `find`             | Finds tasks with specific keyword   | `find KEYWORD`                                    |
| `delete`           | Deletes a specific task             | `delete INDEX`                                    |
| `clear`            | Deletes all tasks from current list | `clear`                                           |
| `bye`              | Ends the application                | `bye`                                             |


<br> 

## Saving the data
Nova data are saved automatically in the hard disk after any command that changes the data. \
There is no need to save manually.

