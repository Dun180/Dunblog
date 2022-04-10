import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from "path";
import {fileURLToPath, URL} from "url";
const setAlias = (alias: [string, string][]) => alias.map(v => {return { find: v[0], replacement: path.resolve(__dirname, v[1]) }})
// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    }
  },
  plugins: [vue()]
})
