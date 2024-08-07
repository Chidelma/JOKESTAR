export class Prompter {

    static async ask(prompt: string, allowedAnswers: string[]) {

        let answer: string = ''

        process.stdout.write(`${prompt}\n`)

        for await(const line of console) {

            if(allowedAnswers.includes(line.toLowerCase())) {
                answer = line.toLowerCase()
                break
            }
            else {
                process.stdout.write('\nInvalid answer. Please try again.\n ')
            } 
        }

        return answer
    }

    static formatResults(results: string[]) {
        console.log(`\n${results.map((res, idx) => `${idx + 1}. ${res}`).join('\n')}`)
    }
}