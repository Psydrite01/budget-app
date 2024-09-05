This is a simple budget app using kotlin with jetpack compose. It uses room database to store the data locally and the user can manipulate the data accordingly.
*Browse the screenshots folder for the UI design

It has many features, including:
1) Implemented room database
2) Implemented navigation using androidx.navigation:navigation-compose:2.7.7
3) Implemented viewmodel using androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3
4) Detailed history of all the transactions for each month
5) Automatically detect the ongoing month and calculate daily spending, balance left, and approximate expenditure
6) Archieve past months without deleting them
7) Note down important upcoming events for better usage experience ( for e.g. "mess fees -2000" etc)
8) Prevent accidental creation of duplicate months of the same year, or accidental deletion of a month.
