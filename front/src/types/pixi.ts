import * as PIXI from 'pixi.js'

export interface TreeEntityOptions extends Partial<PIXI.IApplicationOptions> {
  textures: PIXI.Spritesheet
  treeTexture: PIXI.Texture
  activeGifts: number
  activePrizes?: string
  nonActivePrizes?: string
  setMessage: (text: string | null) => void
}
