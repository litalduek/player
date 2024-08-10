# Player Management Service

A simple service to retrieve player data.

## Prerequisites

- Java 17
- Maven 3.6+

## Running the Service

1. Clone the repository:
   ```sh
   git clone https://github.com/litalduek/demo
   cd <repository-directory>

2. Build the project:

   ```sh
    mvn clean install

3. Run the service:
   ```sh
    mvn spring-boot:run

## API Endpoints
* Get all players:

   ```sh
   GET /api/players
* Get player by ID:
   ```sh
  GET /api/players/{id}