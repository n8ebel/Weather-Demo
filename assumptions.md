# Assumptions

## Location Input
Will be assuming that any form of location input is acceptable as an initial implementation, and
will be focusing on zipcode to start.

Alternative option would be to accept multiple forms of location input.  This becomes more complex
more minimal added functionality as we now have to consider more input validation and process
several different input paths to eventually convert into the required api data.

## Location Validation
Will be assuming that some form of error handling will be required for invalid locations.

Ideally, this would take place at several points in the app:
* location input
* loading of location data

For an initial implementation, I will focus on displaying a useful error message if location is
invalid when loading/displaying the data.

If there is time, I would like to validate the location data at the input screen.