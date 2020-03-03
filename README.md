# Weather Demo
Simple weather app sample

## Requirements & Tracking
Basic requirements are outlined in `requirements.md` and will tracked using GitHub Issues

## Assumptions
Assumptions will be documented in `assumptions.md`

## Architecture
Architecture and infrastructure decisions are documented in `adr/`

## Design
Fat marker sketches and wireframes of design iteration are included in `design/`

## Implementation
* Open app to immediately load forecast data for last searched zipcode
* If no last saved zipcode, a message prompting user to search for location
* User clicks on action button to open location search screen
* User is prompted to enter a valid zipcode
* Error feedback is given for invalid zipcodes
* Once valid search is submitted, new forecast data is loaded and displayed
* User can pull down in forecast screen to refresh forecast data

## Screenshots
Forecast             |  Search
:-------------------------:|:-------------------------:
![Loaded Forecast](https://github.com/n8ebel/PremiseWeather/blob/master/screenshots/loaded_forecast.png)  |  ![Loaded Forecast](https://github.com/n8ebel/PremiseWeather/blob/master/screenshots/invalid_zipcode.png)
