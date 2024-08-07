import { Feed } from './Feed'
import { Prompter } from './Prompter'

const jokeURL = 'https://us-central1-geotab-interviews.cloudfunctions.net/joke'
const jokeCategoryURL = 'https://us-central1-geotab-interviews.cloudfunctions.net/joke_category'

async function main() {

    await Prompter.ask('Press ? to get instructions. ', ['?'])

    while(true) {

        const answer = await Prompter.ask('\nPress c to get categories.\nPress r to get random jokes. ', ['c', 'r'])

        if(answer === 'c') {

            const categories = await Feed.getCategories(jokeCategoryURL)

            Prompter.formatResults(categories)
        }

        if(answer === 'r') {

            const category = await Prompter.ask('\nWant to specify a category? y/n ', ['y', 'n'])

            if(category === 'y') {

                const categories = await Feed.getCategories(jokeCategoryURL)

                console.log("\nHere are the categories: ")

                Prompter.formatResults(categories)

                const categoryName = await Prompter.ask('\nEnter a category: ', categories)

                const number = await Prompter.ask('\nHow many jokes do you want? (1-9) ', ['1', '2', '3', '4', '5', '6', '7', '8', '9'])

                const jokes = await Feed.getRandomJokes(jokeURL, parseInt(number), categoryName)

                Prompter.formatResults(jokes)
            }
            else {

                const number = await Prompter.ask('\nHow many jokes do you want? (1-9) ', ['1', '2', '3', '4', '5', '6', '7', '8', '9'])

                const jokes = await Feed.getRandomJokes(jokeURL, parseInt(number))

                Prompter.formatResults(jokes)
            }
        }
    }
}

await main()