# User Guide

## Features 

### Add Task
User is able to add a task to Duke

#### Add ToDo
User is able to add a `ToDo`

#### Add Event
User is able to add an `Event` with the Date and Time

#### Add Deadline
User is able to add a `Deadline` with the Date and Time

### Delete Task
User is able to delete a task

### List Task
User is able to list out all tasks

### Done Task
User is able to mark a task as done

### Find Task
User is able to list out all tasks that match the search term specified

## Usage

### `todo <desc>`

Add a `ToDo` task to Duke

>Example of usage:
```
todo read math notes
```

>Expected outcome:
```
 Got it. I've added this task:
   [T][✘] read math notes
 Now you have 1 tasks in the list.
 Remember, you can enter "list" to view all tasks
```

### `event <desc> /at <date> <time>`

Add an `Event` task to Duke

>Example of usage:
```
event prom /at 20-2-2020 1800
```

>Expected outcome:
```
 Got it. I've added this task: 
   [E][✘] prom (at: Feb 20 2020 18:00)
 Now you have 1 tasks in the list.
 Remember, you can enter "list" to view all tasks
```

### `deadline <desc> /by <date> <time>`

Add a `Deadline` task to Duke

>Example of usage:
```
deadline do math quiz /by 2-12-2020 1215
```

>Expected outcome:
```
 Got it. I've added this task: 
   [D][✘] do quiz (by: Dec 2 2020 12:15)
 Now you have 1 tasks in the list.
 Remember, you can enter "list" to view all tasks
```

### `delete <task_index>`

Delete a `Task`

>Example of usage:
```
delete 1
```

>Expected outcome:
```
 Ok! I have removed this task!
   [T][✘] read math notes
```

### `list`

List all the `Task` entered

>Example of usage:
```
list
```

>Expected outcome:
```
1.[E][✘] prom (at: Feb 20 2020 18:00)
2.[D][✓] do quiz (by: Dec 2 2020 12:15)
```

### `done <task_index>`

Mark a `Task` as Done

>Example of usage:
```
done 1
```

>Expected outcome:
```
 Nice! I've marked this task as done:
   [D][✓] do quiz (by: Dec 2 2020 12:15)
```

### `find <search_term>`

List all of the `Task` that matches the search

>Example of usage:
```
find book
```

>Expected outcome:
```
Here are the matching tasks in your list:
1.[T][✓] read book
2.[D][✓] return book (by: June 6th)
```
