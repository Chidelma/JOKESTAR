# Java version #

## What do I need? ##

* [Java JDK](https://www.oracle.com/ca-en/java/technologies/javase-downloads.html)
* Your IDE of choice

## How do I run the code? ##

* Open the project in your IDE
* Run the `Main` class

## How do I test the code? ##

* Open the project in your IDE
* Run the `TestRunner` class


## Improvements Overview ##

The original code has been refactored and improved to enhance functionality, maintainability, and readability. This document covers the changes made to the JsonFeed and ConsolePrinter classes, which have been refactored into the Feed and Prompter classes respectively, as well as improvements to the Main class. The rationale behind each improvement is explained, illustrating how these changes benefit the overall codebase.

Key Improvements

1. URL and Endpoint Management

	Old Code:
	•	URLs were hardcoded and modified within methods.
	New Code:
	•	Defined jokeURL and jokeCategoryURL as static variables in the Feed class for better manageability.

    Rationale: Separating URLs from the logic allows for easier modifications and prevents potential issues arising from URL concatenation in different methods.

2. Dynamic URL Invocation with Parameters

	Old Code:
	•	URL parameters were concatenated manually, leading to potential errors.
	New Code:
	•	Introduced an invokeURL method in the Feed class to handle dynamic URL invocation with parameters.

    Rationale: Encapsulating URL invocation logic in a single method makes the code more modular and reusable, simplifying future modifications.

3. Constructor Simplification

	Old Code:
	•	Constructor accepted parameters that were not effectively utilized.
	New Code:
	•	Simplified the constructor in the Feed class to an empty one, as URLs and configurations are managed within methods.

    Rationale: A simpler constructor clarifies the class’s purpose and usage, making it easier to understand and maintain.

4. Enhanced Random Joke Retrieval

	Old Code:
	•	getRandomJokes did not support fetching multiple jokes and had potential issues with duplicate jokes.
	New Code:
	•	Improved getRandomJokes in the Feed class to accept the number of jokes to fetch and ensure no duplicates.

    Rationale: This enhancement provides more functionality and robustness, allowing users to fetch multiple unique jokes.

5. Proper JSON Parsing and Response Handling

	Old Code:
	•	JSON parsing logic was scattered and less modular.
	New Code:
	•	Centralized JSON parsing within the invokeURL method and in the getCategories method of the Feed class.

    Rationale: Centralizing JSON parsing ensures consistency and makes the code more maintainable and easier to understand.

6. Improved Category Retrieval

	Old Code:
	•	getCategories returned the entire response as a single string without parsing it into meaningful categories.
	New Code:
	•	Properly parses the JSON response into an array of category strings in the Feed class.

    Rationale: Parsing the response into meaningful data structures improves usability and aligns with typical API usage patterns.

7. Separation of Concerns

	Old Code:
	•	ConsolePrinter handled both setting and printing values, mixing responsibilities.
	•	Main class handled user input, API interaction, and result formatting.
	New Code:
	•	Introduced a Prompter class to handle user interactions and input validation.
	•	Main class focuses on core application logic, delegating tasks to Prompter and Feed.

    Rationale: Separating different responsibilities into distinct classes improves code organization, maintainability, and readability.

8. Improved User Interaction Handling

	•	Old Code:
	•	User interaction logic was intertwined with main application logic.
	•	New Code:
	•	Prompter class encapsulates user interaction logic, providing methods for prompting and validating input.

    Rationale: Encapsulating user interaction logic makes it easier to manage, modify, and test, improving the overall user experience.

9. Enhanced Input Validation

	Old Code:
	•	Input validation was minimal and scattered.
	New Code:
	•	ask method in the Prompter class includes input validation to ensure only allowed answers are accepted.

    Rationale: Centralizing input validation ensures consistency and improves user experience by providing clear feedback.

10. Better Result Formatting

	Old Code:
	•	Results were printed directly with minimal formatting.
	New Code:
	•	formatResults method in the Prompter class formats and prints results in a readable manner.

    Rationale: Improved result formatting enhances readability and user experience.