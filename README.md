## Drones

[[_TOC_]]

---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has:
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task.

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach.

---

### Requirements

While implementing your solution **please take care of the following requirements**:

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---
### Getting started
To build this solution you need 
- Firstly, clone this project from the repository
- After you cloned it, it is better to open the project with IDE (for my case it is Intellij IDEA)
- Use pom.xml to collect all dependencies
- Run maven build to start project.
---
### Entities
This service is focused on storing only required data. That's why it is designed to store registered drones and loaded Medications.

#### Entity "Drone":
- "serialNumber" is a unique value column for Drone (instead of ID). Installed on registration
- "model" is a ENUM value. Installed on registration. No usage by far
- "weightLimit" is a maximum weight. Installed on registration
- "batteryCapacity" is a battery level. It is updating every 10s by a scheduled task
- "state" is a drone state. No usage by far
- "weightLoaded" is a currently loaded weight. Updating on loading
---
#### Entity "Medication":
- "name" is a Medication name. Installed on registration. No usage by far
- "weight" is weight of a Medication 
- "code" is a Medication code. Installed on registration. No usage by far
- "droneSerialNumber" is a serial number of a drone which is storing current Medication
- "image" Installed on registration. No usage by far
- "id" is autoincrement. A unique ID for Medication
---
### Tests
To test API I recommend you to use Postman, **or** to use Swagger UI by following the link:
http://localhost:8080/swagger-ui/index.html#

List of possible requests:
- Get request to obtain a list of all drones **/api/v1/drones**
- Get request to obtain a certain drone **/api/v1/drones/{serialNumber}**
- Post request to register a new drone **/api/v1/drones**
- Post request to load medication on a drone **/api/v1/medications**
- Get request to obtain a list of drones available for loading **/api/v1/drones/available**
- Get request to obtain a list of Medications loaded on a certain load **/api/v1/medications/{serialNumber}**

The required JSON structure for each request is better to get from Swagger / Swagger UI

---
### Known issues
#### Wrong conversions
The default Jackson ObjectMapper converts float values to integer ones, if the target value type is Integer, for example:

if you try to register a drone using that JSON:

`{
"model": "CRUISERWEIGHT",
"serialNumber": "test_weight_float",
"weightLimit": 225.5
}`

the request will be succeeded and will return weightLimit as 225. From my point of view, it is not a right behavior. The main idea how to prevent it is to determine a right behavior by extending and creating a custom ObjectMapper, or to toggle off ACCEPT_FLOAT_AS_INT. 

#### ENUM JSON field model needs to be validated properly
By default, Jackson ObjectMapper just throws an exception(and 400 response) if you try to place a wrong value into ENUM field. But it is barely commented, and the response message does not explain what's wrong.

The only way to handle this situation is to create a custom ObjectMapper which will throw a proper response.
#### No test coverage
Unfortunately, there is no test coverage for this project.

It would be great to introduce bean unit tests and integration tests


**END** 
