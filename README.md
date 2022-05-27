# simple-game-service

<h1>How to setup and run </h1>
  <h2>Requirements</h2>

  Need to have Java installed in the PC you are going to run the JAR file. (In my case I used Java 11.0.12 version)

   <h2>Requirements</h2>
  In order to run the application go to "Jar File" folder and execute the common java -jar gameService-0.0.1-SNAPSHOT
  
  
<h1>Limitations </h1>
<ol>
  <li> No Login sessions have been implemented.</li>
</ol>


<h1>Assumptions </h1>
<ol>
  <li> Multi players can use this game</li>
  <li> If the user has zero balance, then user cannot bet.</li>
  <li> When calculating big , medium and small wins, following probabilities has been use
    <ul>
      <li>Big wins : 5% </li>
      <li>Medium wins : 20%</li>
      <li>Small wins: 75%</li>
   </ul>
    </li>
</ol>

<h1>API Documentation</h1>

API documentation avaialble here : <p>API documentation:  <a href="https://github.com/hima1993/simple-game-service/blob/f67ceaf0725ef05a8a017b9f9d04cae705876ef7/API%20Documentation.pdf">API Documentation.pdf</a></p>
Also, I have upload the postman collection called "Game Service.postman_collection.json" with the essential HTTP Requests.
