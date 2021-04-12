# TeamPoint

## TeamPoint's Description

During our studies at school or even in our personal life, we make team projects, especially in computer science and management speciality. It came to me, that it will be obvious to design a solution to help students and others to manage their team collaboration in any project’s types and in an efficient way. But also to ensure the synchronization of the teams and their autonomy.

So, the project name is Team Point. Team Point is a project management software and its goal is to improve your team performance. Indeed, Team Point is going to help you meet your goals of team collaboration and communication.

Team Point’s users will be able to create projects and to add contributors and boards to it.
A board is basically a table containing tasks. You can customize it as much as you want by adding different types of columns. 
Also, users can create as many boards as they want, for example one board for tasks, another for issues, one for the backend team, one for the frontend team and another for marketing.
Assign your teammates easily by adding a person column on the board. But also set status, priority and other information such as deadlines or time/cost estimations.
Once your board is operational you will be able to generate different views, for example timeline or kanban.
Then all members can see what tasks they have to do sorted by multiple filters like priority, status, overdue etc...
You and your teammates will be able to communicate in a simple way with one click on one of the board tasks and chat, write comments, discuss and mention relevant team members in the context of your work. They will be notified on Team Point.

## TeamPoint's main features

_(not exhaustive)_ :

* CRUD project:
A project is equivalent to a workspace.

* Invite users in project:
You can invite your teammates in the project by username

* Add a Board (container of group item)
You can add as many boards as you want in your project

* Manage Board permission (Board Owner only)
The board owner can edit everything.
Choose what everyone can do on this board:
Edit everything (add/move groups, columns and rows,edit content in all rows and write updates.)
Edit content (add/move rows, edit content in all rows and write updates.)
Edit rows assigned to them in the Responsable column (Team members can only edit rows assigned to them in a selected person column.)
View only (View board content. Write updates.)

* Manage Board members
Anyone in your workspace/project can find and access your board. You can add other members and set one or plus board owners.

* CRUD group of items (new table)
A group of items is a table or if you prefer a part of your board. For example, one group can be “This week” and another “Next Week”.

* CRUD item (line of the table)
An item can represent whatever you want. It’s a line in the corresponding group. For example you can use them as tasks or tickets for bugs and issues or even as clients.

* Comment an item
If you comment on an item the person in charge will be notified plus all members you mentioned with “@” in your comment. A great way to communicate in context…

* CRUD column (of the table): (Status column,Text column,People column,Timeline column,Date column,Number column)
You are really free to customize everything.

* Change board view : (to main table, to Kanban, to Timeline/Gantt, to Workload)
By default the board view is set to the main table.

* Set user profile settings
Personal information, Working status, Password …

* Read/Answer notifications

* See team assignments
See all of your team assignments in one place.

* Login/logout


## How to run it ?

- git clone https://github.com/Salim-Azh/TeamPoint.git
- open Teampoint/prototypes as Java project
- add run config for class gui.view.LoginView
- edit the vm run config arguments with : `--module-path path/to/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml`
- run the project with that config
