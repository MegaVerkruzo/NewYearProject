import { create } from 'zustand'

export type MainStore = {
  timer: null | string
  setTimer: (timer: null | string) => void
}

export const useMainStore = create<MainStore>()((set) => ({
  timer: null,
  setTimer: (timer: string | null) => set({ timer }),
}))
