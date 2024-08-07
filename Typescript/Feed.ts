export class Feed {

    private static async invokeURL(urlString: string, params?: Record<string, string>) {

        let results: string = ''

        try {

            const url = new URL(urlString)

            url.search = params ? new URLSearchParams(params).toString() : ''

            const res = await fetch(url)

            if (res.status !== 200) throw new Error(`Error: ${await res.text()}`)

            results = await res.text()

        } catch (err) {
            if(err instanceof Error) throw new Error(`Feed.invokeURL -> ${err.message}`)
        }

        return results
    }

    static async getRandomJokes(url: string, total: number = 1, category?: string) {

        const jokes: string[] = []

        try {

            for(let i = 0; i < total; i++) {

                const results = category ? await this.invokeURL(url, { category }) : await this.invokeURL(url)

                const data: { value: string, icon_url: string } = JSON.parse(results)

                if(!jokes.includes(data.value)) jokes.push(data.value)
                else total++
            }

        } catch (err) {
            if(err instanceof Error) throw new Error(`Feed.getRandomJokes -> ${err.message}`)
        }
        
        return jokes
    }

    static async getCategories(url: string) {

        let categories: string[] = []

        try {

            const results = await this.invokeURL(url)

            categories = JSON.parse(results)

        } catch (err) {
            if(err instanceof Error) throw new Error(`Feed.getCategories -> ${err.message}`)
        }
        
        return categories
    }
}