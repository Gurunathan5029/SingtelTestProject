# SingtelTestProject
> Outline a brief description of your project.
> Live demo [_here_](https://drive.google.com/file/d/1zHfSOwHvuhC-x5_xnXe9zX_Hie1q8VGj/view?usp=sharing). 

## Table of Contents
* [General Info](#general-information)
* [Tools Used/Environment Requirement](#technologies-used)
* [Test Scenarios Automated](#features)
* [Framework Structure](#screenshots)
* [How To Run](#usage)
* [Bugs Found](#project-status)


## General Information
- SingtelTestProject is created to automate ToDo List Management system-https://todomvc.com/examples/vue/
- All the tests are run in chrome browser 

## Tools Used/Environment Requirement
- Java JDK 17
- Maven 3.8.3
- Chrome 97
- Chrome Webdriver [Attached directly under project]


## Test Scenarios Automated
- Verify user ia able to add different text types of inputs for ToDo List
- Verify user is able to edit the added ToDo List item
- Verify user is able to complete the added ToDo item
- Verify user is able to see valid items in selected list Type
- Verify user is able to remove added ToDo item without completing it
- Verify Items left count Of ToDo List Items


## Framework Structure
Run command [MVN] -> Serenity BDD -> Cucumber-> Run Settings File [Junit] -> Secenarios -> Step Definitions -> Steps -> Page Object Class 


## How To Run
- Directly it can be run in IntelliJi/Eclipse by running Junit file named "SerenityCucumberRunner'
- Can be run via maven command -"mvn clean verify"
## Bugs Found
- Able to add duplicate ToDo Items
- Able to add Blank ToDo item
