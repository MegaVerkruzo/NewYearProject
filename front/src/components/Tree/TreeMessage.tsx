import { FC } from 'react'
import cn from 'classnames'

type TreeMessageProps = {
  text: string | null
}

export const TreeMessage: FC<TreeMessageProps> = ({ text }) => {
  return (
    <div
      className={cn('tree-message', { 'tree-message--visible': text !== null })}
    >
      <div className="message">
        <span dangerouslySetInnerHTML={{ __html: text || '' }}></span>
      </div>
    </div>
  )
}
