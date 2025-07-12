# Knight Board Challenge

This project simulates a knight in his shiny armor moving on a board filled with obstacles, following commands from his king.
The program fetches two JSON files from provided APIs and returns the knight's final position and direction in a JSON format in the console.

## Technologies Used

- Java 11
- Maven
- Docker

## Requirements

Only Docker is required.

## How to Run the Project

From the root of the project (where the `Dockerfile` is located), run:

#### For Build
```bash 
docker build -t knight_board:latest . 
```

#### For Execute
```bash
docker run \
  -e BOARD_API=https://storage.googleapis.com/jobrapido-backend-test/board.json \
  -e COMMANDS_API=https://storage.googleapis.com/jobrapido-backend-test/commands.json \
  knight_board:latest
```

## Output

The JSON output shown in the console will be like:

```json 
{
  "position": {
    "x": 4,
    "y": 0,
    "direction": "SOUTH"
  },
  "status": "SUCCESS"
}
```

Or one of the following if there is an error

```json 
{
  "status": "INVALID_START_POSITION"
}
```
```json 
{
  "status": "OUT_OF_THE_BOARD"
}
```
```json 
{
  "status": "GENERIC_ERROR"
}
```
## Author

This project was developed by `Germán Furfori` as part of a technical evaluation.