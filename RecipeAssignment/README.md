# Recipe Assignment

The objective is to create some API operations which allows users to manage their favourite recipes. Use the actions to create, update and delete a recipe. A recipe consists of the following attributes:
•	Date and time of creation (formatted as dd-MM-yyyy HH:mm);  
•	Indicator if the dish is vegetarian;  
•	Indicator displaying the number of people the dish is suitable for;  
•	Display ingredients as a list;  
•	Cooking instructions;  


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 


### Prerequisites

This project uses a maven build. Spring Tools is recommended to be installed in your IDE. 


### Installing

To get the standalone Spring JARs, simply run the plain old maven build command.

```sh
mvn clean install
```

Running this project involves using Spring Tools in your IDE to fire up application services 

Fire up your favourite REST client and create a recipe. 

`POST http://localhost:8080/recipes`

```json
{
    "created": "18-09-2020 10:00",
    "veg": true,
    "noOfServings": 5,
    "ingredients": [
        "sugar",
        "tea",
        "milk"
    ],
    "instructions": [
        "1",
        "2",
        "3"
    ]
}
```

## Decision

We decided to have 2 sets of models - API and DB so that both of these models can be modified independently if required. The service layer has been combined with rest layer looking at the complexity.

## Running the tests

```sh
mvn test
```

## Built With

* [Spring](https://spring.io) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](https://www.h2database.com/) - Embedded database
* [Junit](https://junit.org/junit5/) - Test Cases


## Authors

* **Ritesh Chopdekar** - *Assignment* - [Ritesh-Chopdekar](https://github.com/Ritesh-Chopdekar/)



