# Jezyki_programowania_pwr

Repository of all laboratories from "Języki programowania" class at Wrocław University of Science and Technology

### Key technologies:

- Java Swing
- Java standard library
- Java Concurrency (Thread, synchronized) [Only in lab5, lab6]
- Java net package (Socket) [lab6]

### All subprojects description:

- lab2: Simple CRUD app to managing animals including sorting and export
- lab3: extension of lab2. App allows to add animals to groups and choose collection that store these animals, and 
managing collection (set sum, common, difference)
- lab4: Application for adding / removing cities at map. Map is interactive, you can move cities and change sizes of points
at graph. Option to search the fastest path from one city to other using BFS algorithm
- lab5: Simulation of cars driving through bridge. Bridge has max drivers / directions limits, and simulation solves
 this problem using concurrency. Simulation includes graphical view.
- lab6_chat_application: simple client-server chat application using java Socket technology
- lab6_phone_book_client_server: simple phone book client-server application using java Socket technology 
- final_exam: final exercise to pass the class 

### Create all jars

These projects have no any dependencies. They are separate projects. To generate every app jars in one command, execute
gradle task "generateAllJars"