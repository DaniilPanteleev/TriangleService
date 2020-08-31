# Bugs

*It's not convenient to use md for BUG reporting so Steps to reproduce tab will be ignored!(See Allure steps)*

Test case ID|Actual result|Expected result|Comment
---|---|---|---
TRGL-15|Triangle was successfully created|Server response with: "Unprocessable Entity" error, code 422|Each triangle side must be greater than 0
TRGL-18|Server response with: "Internal Server Error", code 500|Server response with: "Unprocessable Entity" error, code 422| Need to validate input field(!null, !empty)
TRGL-10|Triangle was successfully created|Server response with: "Unprocessable Entity" error, code 422|Triangle can be exist only in positive bounds (0,]
TRGL-10|Triangle was successfully created|Server response with: "Invalid Separator" error, code 422|Now allowed to use numbers as separator for numbers