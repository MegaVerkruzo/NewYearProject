export type Letter = {
  letter: string
  state: LetterState
}

export type LetterState = 'grey' | 'green' | 'yellow' | ''

export type TKeyboardLetter = {
  id: number
  letter: string
  state?: LetterState
}
