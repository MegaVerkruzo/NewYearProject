import { FC } from 'react'
import MessageAngle from '../../assets/svgs/MessageAngleIcon'

type MessageProps = {
  text: string
}

export const Message: FC<MessageProps> = ({ text }) => {
  return (
    <div className="message-wrapper">
      <div className="message message--angle">
        <span dangerouslySetInnerHTML={{ __html: text }}></span>
      </div>
      <div className="message-svg">
        <MessageAngle />
      </div>
    </div>
  )
}
