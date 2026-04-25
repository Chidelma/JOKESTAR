import { createInterface } from 'readline'

export class Prompter {
    private static rl = createInterface({
        input: process.stdin,
        output: process.stdout
    })

    static async ask(prompt: string, allowedAnswers: string[]): Promise<string> {
        return new Promise((resolve) => {
            this.rl.question(`${prompt}\n`, (answer) => {
                const normalizedAnswer = answer.toLowerCase().trim()
                if (allowedAnswers.includes(normalizedAnswer)) {
                    resolve(normalizedAnswer)
                } else {
                    process.stdout.write('\nInvalid answer. Please try again.\n ')
                    resolve(this.ask(prompt, allowedAnswers))
                }
            })
        })
    }

    static formatResults(results: string[]): void {
        console.log(`\n${results.map((res, idx) => `${idx + 1}. ${res}`).join('\n')}`)
    }

    static close(): void {
        this.rl.close()
    }
}
