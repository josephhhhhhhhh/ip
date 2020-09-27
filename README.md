# User Guide
Welcome to this guide to using **Duke**. **Duke** is a task-management program, capable of storing tasks to help the user keep track of things that need to be done.
**Duke** has a few functions to help the user with task-management.

## Setting up
- There needs to be a file labeled 'data' in the same location as the Duke.jar file
- This 'data' file should contain a 'duke.txt' file.
- On first use, it is best if this file is empty so that there are no errors in using the program.

## Adding a task
1. To begin with adding a new task to the list, the user may choose to type one of three commands:
    `todo`,`deadline` or `event`. To use the command simply type the command followed by the task description.
    If done correctly, typing `todo homework for school` in the command-line interface should result in a response
    like so:
    ```
   ____________________________________________________________
       Got it. I've added this task: 
        [T][✗] homework for school
       Now you have 1 task in the list.
   ____________________________________________________________
    ```
   The capital 'T' in square brackets refer to the task being a todo-type task. There are deadline-type tasks which
   are indicated by a 'D' in square brackets and event-type tasks which show an 'E' in square brackets instead.
   
2. For deadlines, the user may specify a date by which the task needs to be done. Add a `/by` followed by a due date
   at the end of the task description so that the task will end with a `(by: <insert due date here>)`. The due date 
   can either just be a bunch of words like `/by the day after tomorrow` or if you wish to search for the deadline by
   date at some point, you should write the date in the YYYY-MM-DD format. An example would be, when you type
    `deadline get christmas present /by 2020-12-25` the response should be:
    ```
   ____________________________________________________________
   Got it. I've added this task: 
    [D][✗] get christmas present (by: Dec 25 2020)
   Now you have 2 tasks in the list.
   ____________________________________________________________
   ```
   See? The date reformats appropriately at the end of your task description. There are other benefits to adding a date
   to the task description using the given YYYY-MM-DD format, but more on that later.
   
3. For events, much like the `/by` command above, you can use `/at` in your task description to specify a location for
   your event. It should show up as `(at: <insert event location here>)`. In this case, the event location does not need
   to be formatted in any way.
   
## Accessing your list of tasks
1. Type `list` to get a list of all the tasks stored in your program.

2. If you wish to look for a task based on a keyword you used in the task description, use the `find` command. The command
   works like so, after you type `find homework`:
   ```
    ____________________________________________________________
    Here are the matching tasks in your list: 
    1.  [T][✗] homework for school
    ____________________________________________________________
   ```
3. You may also search for tasks by a due date. Let's say you know you had to do something by Christmas, but cannot
   remember what it was, just type `date /by 2020-12-25` and all deadlines stored with this date format will be retrieved,
   like so:
   ```
   ____________________________________________________________
   Here are the tasks that are due on the queried date: 
   1.  [D][✗] get christmas present (by: Dec 25 2020)
   ____________________________________________________________
   ```

## Marking tasks as done
1. After listing out the tasks you have, you will notice each task has a task number. That task number
   refers to the corresponding task beside it, and can be used in the `done` command to mark a task being
   complete. Simply type the number of the task you are referring to after typing `done`, and that task
   will be marked as complete. An example would be if I typed `done 2` in reference to the christmas present
   task (which was the second task), this would show:
   ```
   ____________________________________________________________
   Nice! I've marked this task as done: 
     [✓] get christmas present (by: Dec 25 2020)
   ____________________________________________________________
   ```

## Deleting tasks
1. It's really simple. Like the done task earlier, simply type the corresponding task number after `delete`
   to delete that task. Here's what `delete 1` would show:
   ```
   ____________________________________________________________
   Noted. I've removed this task:
    [T][✗] homework for school
   Now you have: 1 task in the list.
   ____________________________________________________________
   ```

##HELP! I CANNOT REMEMBER ANY OF THE THINGS YOU HAVE WRITTEN ABOVE
1. Worry not, just type `help` into the command-line interface, and all the commands will be shown to you
   in a neat summary. Cool right?
   
##Saying bye-bye
1. Type in `bye` if you wish to terminate the program. It's really that simple.


   
   