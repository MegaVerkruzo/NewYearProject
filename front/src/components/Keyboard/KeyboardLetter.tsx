import cn from 'classnames'
import { FC } from 'react'
import CheckMark from '../../assets/svgs/CheckMarkIcon'
import LeftArrow from '../../assets/svgs/LeftArrowIcon'
import { ClickEvent } from '../../types/event'

type KeyboardLetterProps = {
  letter: string
  onClickLetter: (e: ClickEvent) => void
  id: number
  state?: string
  canAttempt?: boolean
}

export const KeyboardLetter: FC<KeyboardLetterProps> = ({
  letter,
  onClickLetter,
  id,
  state = '',
  canAttempt,
}) => {
  return (
    <button
      className={cn('keyboard__letter', state, {
        active: id === 52 && canAttempt,
      })}
      onClick={onClickLetter}
    >
      {id === 51 ? <LeftArrow /> : id === 52 ? <CheckMark /> : letter}
    </button>
  )
}
