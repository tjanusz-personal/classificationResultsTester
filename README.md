# Classification Results Tester

The classification results tester project is a simple Java 8 command line project I wrote to help validate some testing I'm doing for a work project.
It reads a custom CSV file and will perform some functions across the rows of data to locate specific errors in the CSV.

* Looks for duplicate industry definitions across a single lead_id
* Looks for incorrect classification across a lead_id using the count method
* Looks for incorrect classification across a lead_id using the total method

## Dependencies
* Junit - to run the unit tests
* Java 8 - I use streams in a lot of the processing to simplify my life

## Execution
* Simple main method with no arguments. The CSV column formats are documented in the code.

## Notes
* This only works for simple CSV files (no embedded commas in fields) but that's OK since my sample data set is restricted to that type
