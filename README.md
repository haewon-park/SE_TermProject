## APP for University Library Reading Room Reservation 


### Concept
*This Application that provides a reservation system for reading facilities within Gachon University.*

<br>

### Differentation Point 
**1. Administrator Functions**
* Inquire the information of the person in use
* Forced checkout function
* Seat shift function

**2. Going Out Functions**
* Use for a specified time only.
* Can be extended once

**3. Provide Customized Information**
* Information provided in the reading room on the central library and colleges.
<br>

### Progress Plan
<p align = "center"> <img src = "https://user-images.githubusercontent.com/65820741/162388985-2d64c820-189f-4791-936f-d122ea215927.png"> </p>

<br>

### Function
<p align = "center"> <img src = "https://user-images.githubusercontent.com/65820741/162390752-42673e45-8565-48fc-b6ae-3c645bc68a42.png"  width = "400"></p>

**1. User**
* Login & Sign up
* Reservation
* Confirm user’s information
* Extension reservation time
* Outing

**2. Admin**
* Login & Sign up
* Check out
* Set closing Time

<br>

### Requirement
**1. Functional Requirement**

1-1. Reservation
* View seat information
* Choose and modify seat user want
* Replace empty seat

1-2. Outing & Extension
* Limit and View times outing and extension
* If user limit times, system announce user excess.
* If user use this function, system update DB and modify UI 

**2. Non Functional Requirement**

1-1. Usability
* UI seat clearly delimited
* UI should be created so that users can easily see it

1-2. Reliability
* Protect DB to avoid errors.
* Only authorized users access DB.

1-3. Performance
* Less than 3 sec to update DB
* Less than 3 sec to modify UI


### Member Roles
| 박해원 | 장휘준 | 이상운 | 오태호 |
| :---- | :---- | :---- | :---- |
| UML & Structure Design   | Detailed UI Design    | Time Extension & Outing Function | Debugging & Combine Features |
| Membership & Login Function  | Admin’s Functions | Database Interlink |  Reservation, Shift Move, Checkout function |
<br>

