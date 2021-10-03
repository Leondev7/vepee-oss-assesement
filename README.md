# Thoughts

# Architecture

## Repository pattern and use cases
Usually I design the application with clean architecture in mind, so I put my logic in the domain
layer, the fetching of the data on the data layer and the ui and MV* in the presentation layer. I
decided to not use this architecture and keep using the architecture previously used in this
exercise and not break the code style.

## Navigation
Personally I prefer to work one activity applications and multiple fragments. The navigation
stays in your navigation folders and graphs and is easily traceable if an error exits.

## Dependency injection
It's hard to go back to pure Dagger2 after tasting Koin and Hilt, personally I think it was the
hardest challenge of this exercise.

## Core module
I consider that a core module is very important for having your resources, databases and network
calls centralized, it helps you with debugging and dependency injection.

## Asynchrony
I really missed some kind of asynchrony in the detail and list modules other than call enqueue
directly in the ViewModels (as it does calling the DAOs in the favorites module), because it breaks
the clean architecture principles completely.

# Methodologies

## Trunk based workflow
After working with git-flow for a long time, I decided to test trunk based workflow to see if it
feels right and to check its pros and cons. I think it really needs testing to work well in a team
environment

# TODOS

## Bonus
I would have loved to complete these too, but I already have other technical tests too and my time
is limited, so I decided to split my time between these tasks.

## Testing
To have proper unit testing specially for the favorites module you need time, but as I said before
I cannot dedicate too much time to each test.

