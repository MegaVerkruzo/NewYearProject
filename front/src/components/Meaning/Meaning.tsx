import { FC } from 'react'
import { Message } from '../Message.tsx/Message'

type MeaningProps = {
  text: string
}

export const Meaning: FC<MeaningProps> = ({ text }) => {
  return (
    <div>
      <h1>Значение слова</h1>
      <Message text={text} />
    </div>
  )
}
