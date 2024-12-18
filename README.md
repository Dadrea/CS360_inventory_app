# CS360_inventory_app
Inventory application developed for CS360 final

## Goals
The purpose of this assignment was to develop a mobile application to be used by a warehouse to track their inventory via a database. The application had to be able to be run on Android devices both newer and older utalizing coding best pratices to ensure scalable and maintainable code for future development. 

## What screens and features were necessary to support user needs and produce a user-centered UI for the app? 
The needs of this application meant that several items were required. First two databases one for users data and another for the inventory list itself. Second, a log in screen that also allowed for new users to be registered thus allowing them access to the program. Third, a way for users to manipulate the data on the inventory screen. 

![alt text](https://private-user-images.githubusercontent.com/2060421/396991170-34b199fc-4169-4a25-a8cb-16a98410d7d8.JPG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzQ1MzYyMTksIm5iZiI6MTczNDUzNTkxOSwicGF0aCI6Ii8yMDYwNDIxLzM5Njk5MTE3MC0zNGIxOTlmYy00MTY5LTRhMjUtYThjYi0xNmE5ODQxMGQ3ZDguSlBHP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI0MTIxOCUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNDEyMThUMTUzMTU5WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MDM0NTQzNjNjZjMzODJhOWQ2NDdkZjBjYTU3Mzk4MzQ5NDcxM2EzOWUwMDVjYmNhZWZiOTY4ODYxZDc2NTIyNyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.CrMkO6SPTFgFHKWiWpQLzJ8h5cZ4v78S5bf6ppltn7o)

This is an example of the inventory screen that was used, in it I created a list that was populated using the recycler functionality built into the android software. from this page users are able to go in and see detailed views of the items and make adjustments to the item such as changing the inventory count or modifying the discription. This can be seen in the image below.

![alt text](https://private-user-images.githubusercontent.com/2060421/396993350-3e310d6d-608d-4547-93cc-6f27b146afac.JPG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzQ1MzY0NTksIm5iZiI6MTczNDUzNjE1OSwicGF0aCI6Ii8yMDYwNDIxLzM5Njk5MzM1MC0zZTMxMGQ2ZC02MDhkLTQ1NDctOTNjYy02ZjI3YjE0NmFmYWMuSlBHP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI0MTIxOCUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNDEyMThUMTUzNTU5WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9OTQxYjk1MDhlYzcxZmY1ZjY5ZGJmNTY0NTkyZDJkYjZmMDY0YWU5NGJmYzA5YmM4ZGJhYWIxZGY1NzQ4NDg2MSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.CYAcMOR6ZcyV7peDTBBXBo0FwT9jygbeu91tos3orrc)

## How did you approach the process of coding your app?
For this project I broke it down into sections that I could build off. Initially I started with the log in screen and the transition to the inventory page, this was quickly followed up by the initial user database so I could begin getting a grasp on the easier of the two databases that I would need to build. The next item of focus was the inventory database followed by ironing out all the items that would be needed to call, modify, put, remove items from the database. After ironing out all these items and the issues that came along with them while testing, I worked on implementing a detailed view for each inventory item. The last items that I implemented were the notifications to alert users when the inventory for an item was low.

If I had attempted to complete this all in on go it would have been an utter failure, the systematic approach of designing an outline on paper of how I roughly wanted the app to appear and then working on the implementation not only saved my sanity, but it also allowed me to troubleshoot each issue as it arose. I plan to continue to use this type of application development in the future, I found it increadably efficent and by keeping organized I was able to accomplish a lot of work in a relatively short ammount of time.

## How did you test to ensure your code was functional? 
I spent a good many hours testing out each area of implementation before moving on to the next. It is entirely possible that there are some use cases that I missed and given the opportunity I would like to explore more options with managing user inputs and verifying their validity but overall I feel that I accomplished a well-structured application that will perform as intended until the next iteration could be deployed.

## Where did you have to innovate to overcome a challenge?



