import { FC, useCallback, useEffect, useRef, useState } from 'react'
import { TreeEntity } from './TreeEntity'
import * as PIXI from 'pixi.js'
import { useLoadAsset } from './useLoadAsset'
import { TreeMessage } from './TreeMessage'

type TreeProps = {
  activeGifts: number
  activePrizes?: string
  nonActivePrizes?: string
}

const SpriteSheet = '/spritesheet.json'

export const TreeCanvas: FC<TreeProps> = ({
  activeGifts,
  activePrizes,
  nonActivePrizes,
}) => {
  const containerRef = useRef<HTMLDivElement>(null)
  const [app, setApp] = useState<TreeEntity | null>(null)
  const [messageText, setMessageText] = useState<string | null>(null)

  const TreePath = `/Tree${activeGifts}.png`
  const spritesheet = useLoadAsset<PIXI.Spritesheet>(SpriteSheet)
  const treeImage = useLoadAsset<PIXI.Texture>(TreePath)

  useEffect(() => {
    if (!spritesheet || !treeImage) {
      return
    }

    const container = containerRef.current
    if (!container) return
    const canvas = document.createElement('canvas')
    container.appendChild(canvas)

    const app = new TreeEntity({
      textures: spritesheet,
      treeTexture: treeImage,
      activeGifts,
      activePrizes,
      nonActivePrizes,
      setMessage: setMessageText,
      width: window.innerWidth,
      // height: window.innerHeight,
      resolution: window.devicePixelRatio,
      backgroundAlpha: 0,
      antialias: true,
      view: canvas,
    })

    setApp(app)
  }, [spritesheet, treeImage])

  useEffect(() => {
    return () => {
      if (!app) return
      try {
        app.destroy(true)
        setApp(null)
      } catch (e) {
        //console.log(e)
      }
    }
  }, [app])

  const closeMessageHandler = useCallback(() => {
    setMessageText(null)
  }, [setMessageText])

  useEffect(() => {
    document.addEventListener('click', closeMessageHandler)

    return () => {
      document.removeEventListener('click', closeMessageHandler)
    }
  }, [closeMessageHandler])

  return (
    <div
      className="canvas-wrapper"
      ref={containerRef}
      onClick={(e) => {
        e.stopPropagation()
      }}
    >
      <TreeMessage text={messageText} />
    </div>
  )
}
